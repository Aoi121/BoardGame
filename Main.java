import javax.swing.*;
import java.awt.event.*;
public class Main {
	public static void main(String[] args) {
		// System.out.println("\u001b[36mOutput");
		// System.out.println("\u001b[0m");
		Board board = new Board();
		Player player = new Player(new int[] {3,3}, board.getWidth());

		System.out.println(player);
		System.out.print(board);
		JFrame window = new JFrame("");

		window.addKeyListener(new KeyListener(){
			private String oldTile = "1";
			@Override
			public void keyPressed(KeyEvent e){
				char ch = e.getKeyChar();
				System.out.println(ch);
				player.calculateTruePos(board.getWidth());
				oldTile = board.doMove(ch, player, oldTile);
				if(oldTile.equals("-1")){
					window.dispose();
					System.exit(0);
				}
				System.out.println(player);
				System.out.print(board);
			}
			@Override
			public void keyReleased(KeyEvent e){
				;
			}
			@Override
			public void keyTyped(KeyEvent e){
				;
			}
		});

		window.setSize(100,100);
		window.setVisible(true);
		window.toFront();
		window.requestFocus();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
}