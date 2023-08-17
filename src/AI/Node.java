package AI;

//Node class is used to create nodes for the A* pathfinding algorithm.
public class Node {

    Node parent;
    public int col;
    public int row;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;

    // Constructor for the Node class. It takes in the column and row of the node.
    public Node(int col, int row) {
        this.col = col;
        this.row = row;

    }
}
