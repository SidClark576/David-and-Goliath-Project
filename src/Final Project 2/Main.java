import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Main extends JPanel {

	private ArrayList<Room> rooms;
	private ArrayList<Sprite> stones;
	private Sprite david;
	private Sprite goliath;

	public Main() {
		rooms = new ArrayList<>();
		stones = new ArrayList<>();
		david = new David();
		goliath = new Goliath();
		//identifying gridSize
		var gridSize = 5;
		var spacing = 60;
		var x = 0;
		var y = 0;
		/*a loop where rooms are instantiated to its positions
		and added to the ArrayList*/
		for (int row = 0; row < gridSize; row++) {
			for(int col = 0; col < gridSize; col++) {
				x = col * spacing;
				y = row * spacing;
				rooms.add(new Room(30 + x, 30 + y));
			}
		}

		//loop where instantiating stones and added to ArrayList
		for (int i = 0; i < 5; i++) {
			stones.add(new Stone());
		}

		//first row
		rooms.get(0).setEastExit(rooms.get(1));
		rooms.get(2).setEastExit(rooms.get(3));
		rooms.get(3).setEastExit(rooms.get(4));
		rooms.get(4).setSouthExit(rooms.get(9));

		//second row
		rooms.get(5).setEastExit(rooms.get(6));
		rooms.get(5).setSouthExit(rooms.get(10));
		rooms.get(6).setNorthExit(rooms.get(1));
		rooms.get(6).setEastExit(rooms.get(7));
		rooms.get(7).setSouthExit(rooms.get(12));
		
		//third row
		rooms.get(8).setSouthExit(rooms.get(13));
		rooms.get(8).setEastExit(rooms.get(9));
		rooms.get(10).setEastExit(rooms.get(11));
		rooms.get(11).setSouthExit(rooms.get(16));
		rooms.get(12).setWestExit(rooms.get(11));
		rooms.get(12).setEastExit(rooms.get(13));
		rooms.get(13).setEastExit(rooms.get(14));
		rooms.get(14).setSouthExit(rooms.get(19));

		//fourth row
		rooms.get(18).setNorthExit(rooms.get(13));
		rooms.get(17).setEastExit(rooms.get(18));
		rooms.get(15).setEastExit(rooms.get(16));

		//fifth row
		rooms.get(20).setNorthExit(rooms.get(15));
		rooms.get(21).setNorthExit(rooms.get(16));
		rooms.get(22).setEastExit(rooms.get(23));
		rooms.get(23).setNorthExit(rooms.get(18));
		rooms.get(23).setEastExit(rooms.get(24));

		//David
		david.setCurrentRoom(rooms.get(20));

		//Goliath
		goliath.setCurrentRoom(rooms.get(2));
		
		//Stones
		stones.get(0).setCurrentRoom(rooms.get(0));
		stones.get(1).setCurrentRoom(rooms.get(12));
		stones.get(2).setCurrentRoom(rooms.get(19));
		stones.get(3).setCurrentRoom(rooms.get(21));
		stones.get(4).setCurrentRoom(rooms.get(22));

    }


	@Override
	public void paintComponent(Graphics g) {
		//for loop for drawing the rooms
		for(Room room : rooms) {
			room.draw(g);
		}

		//loop for drawing stones
		for (Sprite stone: stones) {
			stone.draw(g);
		}

		david.draw(g);
		goliath.draw(g);
	}

	public static void main(String[] args) {
		var window = new JFrame("David and Goliath Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(400,400);
		window.setContentPane(new Main());
		window.setVisible(true);
		window.setBackground(new Color(209, 250, 201));
	}
}