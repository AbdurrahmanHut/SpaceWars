import javax.swing.ImageIcon;
import java.util.Random;


public class Enemy extends Sprite {

	private Bomb bomb;
	private int maxHP, currentHP;
	protected int deflection;

	public Enemy(int x, int y, int maxHP) {
		this.x = x;
		this.y = y;
		this.maxHP = maxHP;
		currentHP = maxHP;
		bomb = new Bomb(x, y);
		ImageIcon ii = new ImageIcon("src/resources/enemy.png");
		setImage(ii.getImage());
	}

	public void damage(int damage) {
		currentHP -= damage;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void act(int direction) {
		this.x += direction;
	}

	public Bomb getBomb() {
		return bomb;
	}

	public class Bomb extends Sprite {

		
		private boolean destroyed;

		public Bomb(int x, int y) {
			
			setDestroyed(true);
			this.x = x;
			this.y = y;
			
			ImageIcon ii = new ImageIcon("src/resources/redlaser.gif");
			setImage(ii.getImage());
			
		}

		public void setDestroyed(boolean destroyed) {
			this.destroyed = destroyed;
		}

		public boolean isDestroyed() {
			return destroyed;
		}
	}

}
