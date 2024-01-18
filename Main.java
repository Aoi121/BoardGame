import javax.swing.*;
import java.awt.event.*;
public class Main {

	public static void clearScreen() {  
    	System.out.print("\033[H\033[2J");  
    	System.out.flush();  
	}
	public static void main(String[] args) {
		Board board = new Board();
		Player player = new Player(new int[] {3,7}, board.getWidth());
		
		clearScreen();
		System.out.println(board.drawBoard(player));
		JFrame window = new JFrame("");

		window.addKeyListener(new KeyListener(){
			private String oldTile = "1";
			@Override
			public void keyPressed(KeyEvent e){
				clearScreen();
				char ch = e.getKeyChar();
				player.calculateTruePos(board.getWidth());
				oldTile = board.doMove(ch, player, oldTile);
				if(oldTile.equals("-1")){
					window.dispose();
					System.exit(0);
				}
				System.out.println(player);
				System.out.println(board.drawBoard(player));
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