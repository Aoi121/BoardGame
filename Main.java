import javax.swing.*;
import java.awt.event.*;
class Main {
  public static void main(String[] args) {
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
		window.setLayout(null);
		window.setVisible(true);
		window.toFront();
		window.requestFocus();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}