import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

public class Room {
    private Point pos;
    private Room exitEast;
    private Room exitWest;
    private Room exitNorth;
    private Room exitSouth;

    public Point getPosition() {
        return pos;
    }

    //default constructor
    public Room (int x, int y) {
        pos = new Point(x, y);
        exitEast = null;
        exitWest = null;
        exitNorth = null;
        exitSouth = null;
    }

    //setters for North, South, East, and West
    public void setEastExit(Room r) {
        this.exitEast = r;
        r.exitWest = this;
        
    }
    
    public void setNorthExit(Room r) {
        this.exitNorth = r;
        r.exitSouth = this;

    }

    public void setSouthExit(Room r) {
        this.exitSouth = r;
        r.exitNorth = this;
    }

    public void setWestExit(Room r) {
        this.exitWest = r;
        r.exitEast = this;
    }

    //Draws a 50x50 box and for exits
    public void draw (Graphics g) {

        int roomSize = 50;
        final int side1 = 17;
        final int side2 = 33;

        Color background = new Color(209, 250, 201);
        Color maze = Color.BLACK;

        //main drawRect for the rooms
        g.setColor(maze);
        g.drawRect(pos.x, pos.y, roomSize, roomSize);

        if (exitNorth != null) {
            g.setColor(background);
            g.drawLine(pos.x + side1, pos.y, pos.x + side2, pos.y);
            //creating a Hallway
            g.setColor(maze);
            g.drawLine(pos.x + side1, pos.y, pos.x + side1, pos.y - 5);
            g.drawLine(pos.x + side2, pos.y, pos.x + side2, pos.y - 5);
        }
        if (exitSouth != null) {
            g.setColor(background);
            g.drawLine(pos.x + side1, pos.y + roomSize, pos.x + side2, pos.y + roomSize);
            //creating a Hallway
            g.setColor(maze);
            g.drawLine(pos.x + side1, pos.y + 5 + roomSize, pos.x + side1, pos.y + roomSize);
            g.drawLine(pos.x + side2, pos.y + 5 + roomSize, pos.x + side2, pos.y + roomSize);
        }
        if (exitEast != null) {
            g.setColor(background);
            g.drawLine(pos.x + roomSize, pos.y + side1, pos.x + roomSize, pos.y + side2);
            //creating a Hallway
            g.setColor(maze);
            g.drawLine(pos.x + roomSize, pos.y + side1, pos.x + roomSize + 5, pos.y + side1);
            g.drawLine(pos.x + roomSize, pos.y + side2, pos.x + roomSize + 5, pos.y + side2);
        }
        if (exitWest != null) {
            g.setColor(background);
            g.drawLine(pos.x, pos.y + side1, pos.x, pos.y + side2);
            //creating a Hallway
            g.setColor(maze);
            g.drawLine(pos.x - 5, pos.y + side1, pos.x, pos.y + side1);
            g.drawLine(pos.x - 5, pos.y + side2, pos.x, pos.y + side2);

        }
            
    }
}
