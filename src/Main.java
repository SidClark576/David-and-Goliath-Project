import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main extends JPanel implements KeyListener{

	private ArrayList<Room> rooms;
	private ArrayList<Stone> stones;
	private David david;
	private Goliath goliath;

	private AudioPlayer background, pickupStone, win, lose;
	private Timer timer;

	public Main() {
		//Instantiating AudioPlayer
		background = new AudioPlayer("soundeffects/background.wav");
		pickupStone = new AudioPlayer("soundeffects/Pick up stone.wav");
		win = new AudioPlayer("soundeffects/You won.wav");
		lose = new AudioPlayer("soundeffects/You lose.wav");

		background.play();
		background.loop();

		//Instantiating Objects
		rooms = new ArrayList<>();
		stones = new ArrayList<>();

		david = new David();
		goliath = new Goliath();
		addKeyListener(this);

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
		rooms.get(2).setSouthExit(rooms.get(7));
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

		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goliath.actionPerformed(e);
				repaint();
			}
		});

		timer.start();

	}

	//Key Typed Events
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {

		//moving keys for David
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			//System.out.println("Key is Pressed N");
			david.moveNorth();
			break;

			case KeyEvent.VK_DOWN:
			//System.out.println("Key is Pressed S");
			david.moveSouth();
			break;

			case KeyEvent.VK_LEFT:
			//System.out.println("Key is Pressed W");
			david.moveWest();
			break;

			case KeyEvent.VK_RIGHT:
			//System.out.println("Key is Pressed E");
			david.moveEast();
			break;
		}

		/* conditions if david is in the room that has the stones */
		for (int i = 0; i < stones.size(); i++) {
			if (stones.get(i).getCurrentRoom() != null && david.getCurrentRoom().equals(stones.get(i).getCurrentRoom())) {
				pickupStone.stop();
				david.pickupStone();
				stones.get(i).currentRoom = null;
				pickupStone.play();
			}
		}
		
		/* conditions if david's location is the same as goliath's location */
		if (david.getCurrentRoom().equals(goliath.getCurrentRoom()) && goliath.getCurrentRoom().equals(david.getCurrentRoom())) {
			timer.stop();
			repaint();
			if (david.isArmed()) {
				//System.out.println("Congratulations David! You slew Goliath!"); 
				win.play();
				background.stop();
				JOptionPane.showMessageDialog(this, "Congratulations David! You slew Goliath!");
				win.stop();
			} else {
				//System.out.println("Oh no David! Goliath got you! Try again");
				lose.play();
				background.stop();
				JOptionPane.showMessageDialog(this, "Oh no David! Goliath got you! Try again");
				lose.stop();

			}

			reset();
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void paintComponent(Graphics g) {
		requestFocusInWindow();
		g.setColor(new Color(209, 250, 201));
		g.fillRect(0, 0, getWidth(), getHeight());

		//for loop for drawing the rooms
		for(Room room : rooms) {
			room.draw(g);
		}

		// loop for drawing stones
		for (Sprite stone: stones) {
			stone.draw(g);
		}

		david.draw(g);
		goliath.draw(g);

	}

	//resetting the positions of the sprites
	private void reset() {
		david.reset();
		goliath.setCurrentRoom(rooms.get(2));
		david.setCurrentRoom(rooms.get(20));
		stones.get(0).setCurrentRoom(rooms.get(0));
		stones.get(1).setCurrentRoom(rooms.get(12));
		stones.get(2).setCurrentRoom(rooms.get(19));
		stones.get(3).setCurrentRoom(rooms.get(21));
		stones.get(4).setCurrentRoom(rooms.get(22));

		background.play();
		background.loop();

		timer.start();

	}

	public static void main(String[] args) {
		var window = new JFrame("David and Goliath Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(400,400);
		window.setContentPane(new Main());
		window.setVisible(true);
	}

}