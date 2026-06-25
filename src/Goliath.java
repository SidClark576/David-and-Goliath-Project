import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.ArrayList;

public class Goliath extends Sprite implements ActionListener{

    private Sprite target;

    public Goliath() {
        super();
        image = new ImageIcon("goliath.png");
        target = null;
    }

    //set the target sprite that Goliath will chase
    public void setTarget(Sprite target) {
        this.target = target;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null && target.getCurrentRoom() != null) {
            chaseTarget();
        } else {
            randomMovement();
        }
    }

    //BFS pathfinding to chase the target
    private void chaseTarget() {
        Room targetRoom = target.getCurrentRoom();

        //if already in the same room, no need to move
        if (currentRoom == targetRoom) {
            return;
        }

        //BFS to find shortest path from currentRoom to targetRoom
        Queue<Room> queue = new LinkedList<>();
        HashMap<Room, Room> cameFrom = new HashMap<>();
        queue.add(currentRoom);
        cameFrom.put(currentRoom, null);

        while (!queue.isEmpty()) {
            Room current = queue.poll();

            if (current == targetRoom) {
                break;
            }

            //check all four directions
            Room[] neighbors = {
                current.getNorthExit(),
                current.getSouthExit(),
                current.getEastExit(),
                current.getWestExit()
            };

            for (Room neighbor : neighbors) {
                if (neighbor != null && !cameFrom.containsKey(neighbor)) {
                    cameFrom.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        //trace back from target to find the first step
        if (cameFrom.containsKey(targetRoom)) {
            Room step = targetRoom;
            while (cameFrom.get(step) != currentRoom) {
                step = cameFrom.get(step);
            }
            currentRoom = step;
        } else {
            //no path found, move randomly
            randomMovement();
        }
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
