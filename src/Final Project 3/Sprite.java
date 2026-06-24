import java.awt.Graphics;
import javax.swing.ImageIcon;

public abstract class Sprite {
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

    public void draw(Graphics g) {
        int roomSize = 50;
        int offsetX = (roomSize - image.getIconWidth()) / 2;
        int offsetY = (roomSize - image.getIconHeight()) / 2;
        if (currentRoom != null) {
            image.paintIcon(null, g, currentRoom.getPosition().x + offsetX, currentRoom.getPosition().y + offsetY);
        }
    }
}
