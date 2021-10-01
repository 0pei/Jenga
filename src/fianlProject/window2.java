package fianlProject;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; //事件

public class window2  extends JFrame {
	public window2() {
		super("how to play"); 
		setSize(250,200); 
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); 
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true); 
		add(new JLabel(new ImageIcon("src/howToplay.jpg")));
	}
	
	
		
		
}
