/**
 * The EventRectangle class extends the Rectangle class and is used to create event rectangles with
 * default coordinates and a boolean flag to indicate if the event is done.
 */
package Main;

import java.awt.Rectangle;

// EventRectangle class is used to create the event rectangles, it extends Rectangle
public class EventRectangle extends Rectangle {

    int eventRectDefaultX, eventRectDefaultY;// default x and y coordinates of event rectangle
    boolean eventDone = false;// boolean to check if event is done

}
