import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

public class WindowManagement implements CommonData {

	private ImageIcon ii;
	private Image img, img1, imgc;
	private Dimension d;
	
	Color backgroundColor = Color.BLACK;
	private Main board;
	

	private int alienX = 150;
	private int alienY = 5;

	public WindowManagement(Main board) {
		
		this.board = board;
		
		d = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);
		ii = new ImageIcon("src/resources/enemy.png");
		img = Toolkit.getDefaultToolkit().createImage("src/resources/back.jpg");
		img1 = Toolkit.getDefaultToolkit().createImage("src/resources/Main.jpg");
		imgc = Toolkit.getDefaultToolkit().createImage("src/resources/maker.png");
	
	}

	public ArrayList<Enemy> initAliens()
	{
		ArrayList<Enemy> aliens = new ArrayList<Enemy>();
		
		if(board.hardMode){
	
			for (int i=0; i < 1; i++) 
	        {
	            for (int j=0; j < 5; j++) 
	            {
	                Enemy alien = new Enemy(alienX + 160*j, alienY + 36*i, board.alienHP);
	                alien.setImage(ii.getImage());
	                aliens.add(alien);
	            }
	        }
		}
		
		
		else {
			
			for (int i=0; i < 1; i++) 
	        {
	            for (int j=0; j < 3; j++) 
	            {
	                Enemy alien = new Enemy(alienX + 300*j, alienY + 36*i, board.alienHP);
	                alien.setImage(ii.getImage());
	                aliens.add(alien);
	            }
	        }
			
			
			
		}
        
        return aliens;
	}

	public void countdown(Graphics g) {
		long init;
		board.SM.playCountdown();
		g.drawImage(img, 0, 0, null);
		drawAliens(g);
		Font small = new Font("Helvetica", Font.BOLD, 14);
		g.setFont(small);
		g.setColor(Color.WHITE);
		g.drawLine(0, GROUND + 2, BOARD_WIDTH, GROUND + 2);
		drawPlayer(g);
		g.drawString("COINS: " + board.coins, 35, GROUND + 30);
		g.drawString("LEVEL " + board.level, BOARD_WIDTH/2 - 43, GROUND + 30);
		g.drawString("POINTS: " + board.points, 900, GROUND + 30);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		g.drawString(3 + "", BOARD_WIDTH / 2-22, BOARD_HEIGTH / 2);
		init = System.currentTimeMillis();
		while (System.currentTimeMillis() - init < 1000)
			;
		g.drawImage(img, 0, 0, null);
		drawAliens(g);
		g.setFont(small);
		g.setColor(Color.WHITE);
		g.drawLine(0, GROUND + 2, BOARD_WIDTH, GROUND + 2);
		drawPlayer(g);
		g.drawString("COINS: " + board.coins, 35, GROUND + 30);
		g.drawString("LEVEL " + board.level, BOARD_WIDTH/2 - 43, GROUND + 30);
		g.drawString("POINTS: " + board.points, 900, GROUND + 30);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		g.drawString(2 + "", BOARD_WIDTH / 2-22, BOARD_HEIGTH / 2);
		init = System.currentTimeMillis();
		while (System.currentTimeMillis() - init < 1000)
			;
		g.drawImage(img, 0, 0, null);
		drawAliens(g);
		g.setFont(small);
		g.setColor(Color.WHITE);
		g.drawLine(0, GROUND + 2, BOARD_WIDTH, GROUND + 2);
		drawPlayer(g);
		g.drawString("COINS: " + board.coins, 35, GROUND + 30);
		g.drawString("LEVEL " + board.level, BOARD_WIDTH/2 - 43, GROUND + 30);
		g.drawString("POINTS: " + board.points, 900, GROUND + 30);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		g.drawString(1 + "", BOARD_WIDTH / 2-22, BOARD_HEIGTH / 2);
		init = System.currentTimeMillis();
		while (System.currentTimeMillis() - init < 1000)
			;
		board.SM.playBackground();
	}

	public void drawAliens(Graphics g) {
		Iterator it = board.aliens.iterator();

		while (it.hasNext()) {
			Enemy alien = (Enemy) it.next();

			if (alien.isVisible())
				g.drawImage(alien.getImage(), alien.getX(), alien.getY(), board);

			if (alien.isDying())
				alien.die();
		}
	}

	public void drawPlayer(Graphics g) {
		if (board.player.isVisible())
			g.drawImage(board.player.getImage(), board.player.getX(), board.player.getY(), board);

		if (board.player.isDying()) {
			board.player.die();
			board.ingame = false;
		}
	}

	public void drawShot(Graphics g) {
		if (board.shot.isVisible())
			
			g.drawImage(board.shot.getImage(), board.shot.getX(), board.shot.getY(), board);
	}

	public void drawBombing(Graphics g) {
		Iterator i3 = board.aliens.iterator();

		while (i3.hasNext()) {
			Enemy a = (Enemy) i3.next();

			Enemy.Bomb b = a.getBomb();

			if (!b.isDestroyed())
				g.drawImage(b.getImage(), b.getX(), b.getY(), board);
		}
	}

	public void explosionImage(Sprite sprite) {
		ImageIcon ii2 = new ImageIcon("src/resources/destroyed.gif");
		sprite.setImage(ii2.getImage());
	}

	public void paint(Graphics g) {
	
		g.setColor(backgroundColor);
		g.fillRect(0, 0, d.width, d.height);
		g.setColor(Color.green);

		if (board.levelPassed) {
			
			g.drawImage(imgc, 0, 0, null);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 56));
			g.setColor(Color.WHITE);
			g.drawString("UPGRADE MENU", 60, 79);
			
			
			g.setFont(new Font("Helvetica", Font.BOLD, 24));
			g.setColor(Color.WHITE);
			g.drawString("Coins: " + board.coins, 150, BOARD_WIDTH / 2 - 100);
			
			g.setColor(Color.WHITE);
			
			g.drawRect(30, BOARD_WIDTH / 2 - 50, BOARD_WIDTH/2-100, 40);
			g.drawRect(30, BOARD_WIDTH / 2, BOARD_WIDTH/2-100, 40);
			g.drawRect(30, BOARD_WIDTH / 2 + 50, BOARD_WIDTH/2-100, 40);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Helvetica", Font.BOLD, 14));
			
			
			
			g.drawString("INCREASE ATTACK DAMAGE:" + "  150 COINS", 31, BOARD_WIDTH / 2 - 25);
			g.drawString("INCREASE ATTACK SPEED :" + "  125 COINS", 31, BOARD_WIDTH / 2 + 25);
			g.drawString("INCREASE SHIP'S SPEED :" + "  100 COINS", 31, BOARD_WIDTH / 2 + 75);
			
			
			g.setColor(Color.WHITE);
			g.drawRect(150, BOARD_WIDTH / 2 + 65 + 50, 50, 30);
			g.setColor(Color.WHITE);
			g.drawString("OK", 165, BOARD_WIDTH / 2 + 135);
			
		} else if (board.mainmenu) {
			g.drawImage(img1, 0, 0, null);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 36));
			g.setColor(Color.WHITE);
		
			
			g.drawString("PLAY", 75, BOARD_WIDTH / 2 - 70);
			g.drawString("HIGHSCORES", 75, BOARD_WIDTH / 2 - 20);
			g.drawString("HELP", 75, BOARD_WIDTH / 2 + 30);
			g.drawString("CREDITS", 75, BOARD_WIDTH / 2 + 80);
			g.drawString("QUIT", 75, BOARD_WIDTH / 2 + 130);
			
		} else if (board.difficulty) {
			
			g.drawImage(img1, 0, 0, null);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 30));
			g.setColor(Color.WHITE);
			
		
			g.drawString("CHOOSE DIFFICULTY", 50, BOARD_WIDTH/2-100);
		
			g.drawRect(30, BOARD_WIDTH / 2 - 50, BOARD_WIDTH/2-170, 40);
			g.drawRect(30, BOARD_WIDTH / 2, BOARD_WIDTH/2-170, 40);
			g.setFont(new Font("Helvetica", Font.BOLD, 24));
			
			g.drawString("EASY MODE", 120, BOARD_WIDTH / 2 - 25);
			g.drawString("HARD MODE", 120, BOARD_WIDTH / 2 + 25);
			
		} else if (board.highscore) {
			
			g.drawImage(imgc, 0, 0, null);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 56));
			g.setColor(Color.WHITE);
			g.drawString("HIGH SCORES ", 60, 79);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 24));
			g.setColor(Color.WHITE);
			
			
			for (int i = 0; i < 10; i++){
				
				g.drawString("" + (i + 1) + ") " + board.HM.getNameAt(i) + "   " + board.HM.getScoreAt(i), 150,
				300 + (i - 5) * 30);
				
			}
			

			g.drawRect(130, BOARD_WIDTH / 2 - 30, 120, 40);
			g.setColor(Color.WHITE);
			g.drawString("BACK", 150, BOARD_WIDTH / 2);
		
			
		} else if (board.help) {
			g.drawImage(imgc, 0, 0, null);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 56));
			g.setColor(Color.WHITE);
			g.drawString("HELP ", 60, 79);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 34));
			g.setColor(Color.WHITE);
			
			
			g.drawString("MOVING : ARROW KEYS", 60, 310);
			g.drawString("SHOOT : SPACE", 60, 360);
			g.drawString("PAUSE : ESC", 60, 410);
			
			g.setColor(Color.WHITE);
			g.drawRect(60, BOARD_WIDTH / 2 + 40, BOARD_WIDTH - 850, 50);
			
			g.setColor(Color.WHITE);
			g.drawString("BACK", 100, BOARD_WIDTH / 2 + 75);
			
		} else if (board.cds) {
			
			g.drawImage(imgc, 0, 0, null);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 56));
			g.setColor(Color.WHITE);
			g.drawString("CREDITS ", 60, 79);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 34));
			g.setColor(Color.WHITE);
			
			
			g.drawString("CONTRIBUTOR: ABDURRAHMAN HUT", 60, 360);
		
			g.setColor(Color.WHITE);
			g.drawRect(60, BOARD_WIDTH / 2 + 40, BOARD_WIDTH - 850, 50);
			
			g.setColor(Color.WHITE);
			g.drawString("BACK", 100, BOARD_WIDTH / 2 + 75);
			
		} else if (board.paused) {
			
			g.drawImage(imgc, 0, 0, null);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 56));
			g.setColor(Color.WHITE);
			g.drawString("PAUSED ", 60, 79);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 34));
			g.setColor(Color.WHITE);
			
	
			
			g.drawRect(30, BOARD_WIDTH / 2 - 50, BOARD_WIDTH-750, 40);
			g.drawRect(30, BOARD_WIDTH / 2, BOARD_WIDTH - 750, 40);
			g.drawRect(30, BOARD_WIDTH / 2 + 50, BOARD_WIDTH -750, 40);
			
			g.drawString("RESUME", 75, BOARD_WIDTH / 2 - 20);
			g.drawString("MAIN MENU", 75, BOARD_WIDTH / 2 + 30);
			
			if (board.sound)
				g.drawString("MUTE", 75, BOARD_WIDTH / 2 + 80);
			else
				g.drawString("UNMUTE", 75, BOARD_WIDTH / 2 + 80);
			
		} else if (board.ingame) {
			
			g.drawImage(img, 0, 0, null);
			g.setColor(Color.WHITE);
			g.drawLine(0, GROUND + 2, BOARD_WIDTH, GROUND + 2);
			drawAliens(g);
			drawPlayer(g);
			drawShot(g);
			drawBombing(g);
			Font small = new Font("Helvetica", Font.BOLD, 14);
			g.setFont(small);
			g.setColor(Color.WHITE);
			g.drawString("COINS: " + board.coins, 35, GROUND + 30);
			g.drawString("LEVEL " + board.level,  BOARD_WIDTH/2 - 43 , GROUND + 30);
			g.drawString("POINTS: " + board.points, 900, GROUND + 30);
		}
	}

}
