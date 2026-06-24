import java.awt.Graphics;

import javax.swing.ImageIcon;

public abstract class Sprite implements Drawable{
    protected Room currentRoom;
    protected ImageIcon image;

    public Sprite() {
        currentRoom = null;
        image = null;
    }

    public void setCurrentRoom(Room r) {
        currentRoom = r;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    /* method directions for sprites to move */
    public void moveNorth() {
        if (currentRoom.hasNorthExit()) {
            currentRoom = currentRoom.getNorthExit();
        }
    }
    
    public void moveSouth() {
        if (currentRoom.hasSouthExit()) {
            currentRoom = currentRoom.getSouthExit();
        }
    }

    public void moveEast() {
        if (currentRoom.hasEastExit()) {
            currentRoom = currentRoom.getEastExit();
        }
    }

    public void moveWest() {
        if (currentRoom.hasWestExit()) {
            currentRoom = currentRoom.getWestExit();
        }
    }

    
    //boolean where evaluating two objects if they are in the same room
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sprite other) {
            if (this.getCurrentRoom() == other.getCurrentRoom()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void draw(Graphics g) {
        int offsetX = (Room.roomSize - image.getIconWidth()) / 2;
        int offsetY = (Room.roomSize - image.getIconHeight()) / 2;
        if (currentRoom != null) {
            image.paintIcon(null, g, currentRoom.getPosition().x + offsetX, currentRoom.getPosition().y + offsetY);
        }
    }

}
