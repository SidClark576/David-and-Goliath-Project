import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Sprite {
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

    public void draw(Graphics g) {
        int roomSize = 50;
        int offsetX = (roomSize - image.getIconWidth()) / 2;
        int offsetY = (roomSize - image.getIconHeight()) / 2;
        if (currentRoom != null) {
            image.paintIcon(null, g, currentRoom.getPosition().x + offsetX, currentRoom.getPosition().y + offsetY);
        }
    }
}
