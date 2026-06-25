import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class Goliath extends Sprite implements ActionListener{

    public Goliath() {
        super();
        image = new ImageIcon("goliath.png");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        randomMovement();
    }

    //a method where goliath will move under 4 conditions
    private void randomMovement() {
        int randomDirection = (int)(Math.random() * 4);

        switch (randomDirection) {
            case 0: //move north
                if (currentRoom.getNorthExit() != null) {
                    currentRoom = currentRoom.getNorthExit();
                }
                break;
            case 1: //move south
                if (currentRoom.getSouthExit() != null) {
                    currentRoom = currentRoom.getSouthExit();
                }
                break;
            case 2: //move east
                if (currentRoom.getEastExit() != null) {
                    currentRoom = currentRoom.getEastExit();
                }
                break;
            case 3: //move west
                if (currentRoom.getWestExit() != null) {
                    currentRoom = currentRoom.getWestExit();
                }
                break;
            default:
                break;
        }   
    }
}
