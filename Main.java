package board;

import javax.swing.*;
import java.awt.event.*;

public class Main {
	public static void main(String[] args) {
		Board board = new Board();
		Player player = new Player(new int[] {2,2}, board.getWidth());
		
		System.out.print(board);
		JFrame window = new JFrame("");

		window.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e){
				char ch = e.getKeyChar();
				System.out.println(ch);
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