package AI;

import java.util.ArrayList;

import Main.GamePanel;

public class PathFinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode;
    Node endNode;
    Node currentNode;
    boolean endReached = false;
    int step = 0;

    // constructor
    public PathFinder(GamePanel gp) {
        this.gp = gp;
        // call method to instantiate node array
        instantiateNodeArray();
    }

    // method to instantiate node array
    public void instantiateNodeArray() {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[col][row] = new Node(col, row);
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    // method to reset nodes
    public void resetNodes() {
        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            // reset open, checked and solid state
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        // reset other settings
        openList.clear();
        pathList.clear();
        endReached = false;
        step = 0;
    }

    // method to set nodes
    public void setNodes(int startCol, int startRow, int endCol, int endRow) {
        resetNodes();

        // set start, end and current node
        startNode = node[startCol][startRow];
        endNode = node[endCol][endRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            // set solid nodes
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if (gp.tileM.tile[tileNum].collision == true) {
                node[col][row].solid = true;
            }
            // check interactive tiles
            for (int i = 0; i < gp.interactiveTile[1].length; i++) {
                if (gp.interactiveTile[gp.currentMap][i] != null
                        && gp.interactiveTile[gp.currentMap][i].destructible == true) {
                    int interactiveCol = gp.interactiveTile[gp.currentMap][i].worldX / gp.tileSize;
                    int interactiveRow = gp.interactiveTile[gp.currentMap][i].worldY / gp.tileSize;
                    node[interactiveCol][interactiveRow].solid = true;
                }
            }
            // set cost
            getCost(node[col][row]);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

    }

    // method to find cost
    public void getCost(Node node) {

        // get gCost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // get hCost
        xDistance = Math.abs(node.col - endNode.col);
        yDistance = Math.abs(node.row - endNode.row);
        node.hCost = xDistance + yDistance;

        // get fCost
        node.fCost = node.gCost + node.hCost;

    }

    public boolean search(){
        while(endReached == false && step < 500){
            int col = currentNode.col;
            int row = currentNode.row;

            //check the current node
            currentNode.checked = true;
            openList.remove(currentNode);
            //open the up node
            if(row -1 >= 0){
                openNode(node[col][row-1]);
            }
            //open the left node
            if(col -1 >= 0){
                openNode(node[col-1][row]);
            }
            //open the down node
            if(row +1 < gp.maxWorldRow){
                openNode(node[col][row+1]);
            }
            //open the right node
            if(col +1 < gp.maxWorldCol){
                openNode(node[col+1][row]);
            }
            //Find the best node (lowest fCost)
            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            for(int i = 0; i < openList.size(); i++){
                //check if this node's fCost is better than the current best
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //if two nodes have the same fCost, check which one has the lowest gCost
                else if(openList.get(i).fCost == bestNodefCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                        bestNodefCost = openList.get(i).fCost;
                    }
                }
            }
            //if there is no node in the openList, end loop
            if(openList.size() == 0){
                break;
            }

            //after the loop, openList[bestNodeIndex] is the best step (=currentNode)
            currentNode = openList.get(bestNodeIndex); 
            
            if(currentNode == endNode){
                endReached = true;
                trackPath();
            }
            step++;
        }
        return endReached;
       
    }

    public void openNode(Node node){
        if(node.solid == false && node.checked == false && node.solid == false){
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackPath(){
        Node current = endNode;

        while(current != startNode){
            pathList.add(0, current);
            current = current.parent;
        }

    }
}
