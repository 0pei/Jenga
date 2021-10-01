package fianlProject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.swing.*; 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
//to record player and score then show rank(unfinish!!!)
public class Test extends JFrame implements KeyListener, ActionListener{
	JTextField textField = new JTextField(10);
	JLabel label = new JLabel("Please enter your name.");
	JButton bt1 = new JButton("LEADERBOARD"); 
	Player[] players = new Player[] {};
	
	public Test(){
		setSize(400, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		textField.setBounds(95, 190, 200, 20);
		textField.setBackground(Color.WHITE);
		label.setBounds(120, 170, 200, 20);
		bt1.setBounds(90, 100, 200, 20);
		this.setLayout(null);
		this.add(textField);
		this.add(label);
		this.add(bt1);
		
		bt1.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) 
			{ 
				new Rank();
			} 
		}); 
		
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						PrintWriter fo = new PrintWriter(new FileOutputStream("score.txt", true));
						fo.print(textField.getText());
						fo.flush();
						fo.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
					MainControl frame = new MainControl();
					//players = Arrays.copyOf(players, players.length + 1);
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
