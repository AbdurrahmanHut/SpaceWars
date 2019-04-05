import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player extends Sprite implements CommonData {

	private final int START_Y = 500;
	private final int START_X = BOARD_WIDTH/2 - 62;

	
	private int width;
	protected int speed;

	public Player() {

		ImageIcon ii = new ImageIcon("src/resources/player.png");

		width = ii.getImage().getWidth(null);
		speed = 1;

		setImage(ii.getImage());
		setStart();
	}

	public void setStart() {
		setX(START_X);
		setY(START_Y);
	}

	public void act() {
		x += dx;
		if (x < 2)
			x = BOARD_WIDTH - 2;
		if (x > BOARD_WIDTH - 2)
			x = 2;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
			dx = -1 * speed;

		if (key == KeyEvent.VK_RIGHT)
			dx = speed;

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
			dx = 0;

		if (key == KeyEvent.VK_RIGHT)
			dx = 0;
	}
}
