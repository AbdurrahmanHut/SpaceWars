import javax.swing.*;

public class SpaceWars extends JFrame implements CommonData {

	public SpaceWars() {
		add(new Main());
		setTitle("Space Wars");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(BOARD_WIDTH, BOARD_HEIGTH);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		
	}

	public static void main(String[] args) {
		new SpaceWars();
	}
}
