package fianlProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.metal.MetalBorders.OptionDialogBorder;

public class MainControl extends JFrame implements KeyListener, ActionListener {
	int SCREEN_WIDTH = 600;
	int SCREEN_HEIGHT =800;
	int INIT_Y_POS = 160; // 30 + 25 + background 110 (25 is the height of menubar)
	int INIT_X_POS = 235; // 5 + 80 + background 150
	int down = 0, exit = 0;
	Timer blockTimer;
	int pauseState=0;
	
	int blockSpeedX = 3, blockSpeedY = 10;
	final int DELAY_MS = 50;//timer frequency

	int blockPosX = INIT_X_POS;
	int blockPosY = INIT_Y_POS;

	final int blockWidth = 60; 
	final int blockHeight = 20;
	int score = 0, max_score = score;
	String res = Integer.toString(score);
	static String res2;
	int space = 0, situation = 0; // check whether press space
	Block[] rects = new Block[] {};
	JLabel score_board = new JLabel();
	

	int color = 0;
	int level=1;

	JMenu menu1, menu2, submenu1, submenu2;
	// JMenuItem item1,item2,item3,item4,item5,item6,item7,item8,item9;
	JMenuItem[] item = new JMenuItem[9];

	public MainControl() {
		super("Box Stack");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		addKeyListener(this);
		JPopupMenu.setDefaultLightWeightPopupEnabled(false); // let menubar to be on the upper layer of block
		setMenuBar();
		setLayout(null);
		//add(new JLabel(new ImageIcon("src/bk.png")));
		setContentPane(new JLabel(new ImageIcon("src/bk.png"))); 
		pack();
		rects = Arrays.copyOf(rects, rects.length + 1);
		Block b = new Block();
		b.x = INIT_X_POS;
		b.y = INIT_Y_POS;
		rects[rects.length - 1] = b;
		initBlockTimer();
		res = Integer.toString(score);
		score_board.setText("score: "+res);
		score_board.setBounds(185, 80, 80, 40);
		score_board.setVisible(true);
		score_board.setOpaque(false);
		this.add(score_board);

	}
	public void initBlockTimer() {
		blockTimer = new Timer(DELAY_MS, this);
		blockTimer.setInitialDelay(200);
		blockTimer.start();
	}
	
	
	
	private void setMenuBar() {
		menu1 = new JMenu("help");
		item[0] = new JMenuItem("how to play?");
		menu2 = new JMenu("option");
		item[1] = new JMenuItem("Easy");
		item[2] = new JMenuItem("Medium");
		item[3] = new JMenuItem("Difficult");
		item[4] = new JMenuItem("Red");
		item[5] = new JMenuItem("Blue");
		item[6] = new JMenuItem("Green");
		item[7] = new JMenuItem("Yellow");
		item[8] = new JMenuItem("Black");
		submenu1 = new JMenu("Difficulty");
		submenu2 = new JMenu("Color");
		menu1.add(item[0]);
		menu2.add(submenu1);
		menu2.add(submenu2);
		for (int i = 1; i <= 3; i++) {
			submenu1.add(item[i]);
		}
		for (int i = 4; i <= 8; i++) {
			submenu2.add(item[i]);
		}
		JMenuBar menubar = new JMenuBar();
		menubar.add(menu1);
		menubar.add(menu2);
		setJMenuBar(menubar);
		for (int i = 1; i <= 3; i++) {
			submenu1.add(item[i]);
		}
		item[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				item0ActionPerformed(evt);
			}
		});
		item[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				item1ActionPerformed(evt);
			}
		});
		item[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				item2ActionPerformed(evt);
			}
		});
		item[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				item3ActionPerformed(evt);
			}
		});
		
		item[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				item4ActionPerformed(evt);
			}
		});
		item[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				item5ActionPerformed(evt);
			}
		});
		item[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				item6ActionPerformed(evt);
			}
		});
		item[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				item7ActionPerformed(evt);
			}
		});
		item[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				item8ActionPerformed(evt);
			}
		});
		

	}

	private void item0ActionPerformed(ActionEvent evt) {
		new window2();
	}
	
	private void item1ActionPerformed(ActionEvent evt) {
		if(blockSpeedX>0)
		blockSpeedX = 3;
		else if(blockSpeedX<0)
			blockSpeedX = -3;
		
	}
	private void item2ActionPerformed(ActionEvent evt) {
		if(blockSpeedX>0)
			blockSpeedX = 6;
			else if(blockSpeedX<0)
				blockSpeedX = -6;
	}
	private void item3ActionPerformed(ActionEvent evt) {
		if(blockSpeedX>0)
			blockSpeedX = 9;
			else if(blockSpeedX<0)
				blockSpeedX = -9;
	}
	
	private void item4ActionPerformed(ActionEvent evt) {
		color = 0;
	}

	private void item5ActionPerformed(ActionEvent evt) {
		color = 1;
	}
	
	private void item6ActionPerformed(ActionEvent evt) {
		color = 2;
	}
	
	private void item7ActionPerformed(ActionEvent evt) {
		color = 3;
	}
	
	private void item8ActionPerformed(ActionEvent evt) {
		color = 4;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawBlock(g);
	}

	private void drawBlock(Graphics g) {
		Color color1 = new Color(254, 86, 99); // red
		Color color2 = new Color(83, 133, 250); // blue
		Color color3 = new Color(83, 249, 133); // green
		Color color4 = new Color(255, 224, 99); // yellow
		Color color5 = Color.BLACK;
		switch (color) {
		case 0:
			for (int i = 0; i < rects.length; i++) {
				g.setColor(color1);
				g.fillRect(rects[i].x, rects[i].y, blockWidth, blockHeight);
				g.setColor(color1);
				g.drawRect(rects[i].x, rects[i].y, blockWidth, blockHeight);
			}
			break;
		case 1:
			for (int i = 0; i < rects.length; i++) {
				g.setColor(color2);
				g.fillRect(rects[i].x, rects[i].y, blockWidth, blockHeight);
				g.setColor(color2);
				g.drawRect(rects[i].x, rects[i].y, blockWidth, blockHeight);
			}
			break;
		case 2:
			for (int i = 0; i < rects.length; i++) {
				g.setColor(color3);
				g.fillRect(rects[i].x, rects[i].y, blockWidth, blockHeight);
				g.setColor(color3);
				g.drawRect(rects[i].x, rects[i].y, blockWidth, blockHeight);
			}
			break;
		case 3:
			for (int i = 0; i < rects.length; i++) {
				g.setColor(color4);
				g.fillRect(rects[i].x, rects[i].y, blockWidth, blockHeight);
				g.setColor(color4);
				g.drawRect(rects[i].x, rects[i].y, blockWidth, blockHeight);
			}
			break;
		case 4:
			for (int i = 0; i < rects.length; i++) {
				g.setColor(color5);
				g.fillRect(rects[i].x, rects[i].y, blockWidth, blockHeight);
				g.setColor(color5);
				g.drawRect(rects[i].x, rects[i].y, blockWidth, blockHeight);
			}
			break;
		}
	}

	public static void main(String[] args) {
		Test test = new Test();
//		MainControl frame = new MainControl();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE && situation == 0&&pauseState==0) { // new block
			rects = Arrays.copyOf(rects, rects.length + 1);
			Block block = new Block();
			block.x = INIT_X_POS;
			block.y = INIT_Y_POS;
			rects[rects.length - 1] = block;
			space = 1;
		}
		if (key == KeyEvent.VK_P) { // PAUSE
			blockTimer.stop();
			pauseState=1;
			
		}
		if (key == KeyEvent.VK_R&&pauseState==1) { // PAUSE
			blockTimer.restart();
			pauseState=0;
			
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) { // new block only can move on x axis
		rects[rects.length - 1].x += blockSpeedX;
		
		if (rects[rects.length - 1].x >= SCREEN_WIDTH - blockWidth - 160 || rects[rects.length - 1].x <= 160)
			blockSpeedX = -blockSpeedX;
		
		else {
			if(rects.length > 11) {
				for(int j = down + 11; j < rects.length; ++j) {
					if(rects[j].y == 310) {
						rects[down].y = 100000;
						++down;
					}
				}
			}
			
			for (int i = down; i < rects.length - 1; i++) {
				
				if (i == down && rects[i].y <= SCREEN_HEIGHT - blockHeight - 260 && space == 1) {
					rects[i].y += blockSpeedY;
				} else if (i != down && rects[i].y < rects[i - 1].y - blockHeight) {
					rects[i].y += blockSpeedY;
					situation = 1;
				} else if (i != down && rects[i].y >= rects[i - 1].y - blockHeight) {
					if (exit == 0 && i != down && ((rects[i].x < rects[i - 1].x - (blockWidth / 2)
							|| rects[i].x > rects[i - 1].x + (blockWidth / 2)))) {
						exit = 1;
						JOptionPane j = new JOptionPane();
						max_score = Math.max(max_score, score);
						String[] options = { "Yes", "No" , "Change player"};
						int r = j.showOptionDialog(null, "Play again?", "GAME OVER", j.DEFAULT_OPTION,
								j.QUESTION_MESSAGE, null, options, 1);
						if (r == 1) { // No
							PrintWriter fo;
							try {
								fo = new PrintWriter(new FileOutputStream("score.txt", true));
								fo.println(" " + max_score);
								fo.flush();
								fo.close();
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.exit(0);
						} else if (r == 0) { // Yes
							rects[i].x = rects[i - 1].x;
							rects[i].y = rects[i - 1].y;
							score = 0;
							this.dispose();
							MainControl frame = new MainControl(); // RESTART
						} else if (r == 2) {
							try {
								PrintWriter fo = new PrintWriter(new FileOutputStream("score.txt", true));
								if(max_score != 0) {
									fo.println(" " + max_score);
									fo.flush();
									fo.close();
								}
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							this.dispose();
							main(null);
						}
					}
				}
				
				if (i > down && rects[i].y == rects[i - 1].y - blockHeight) {
					situation = 0;
					score = i;
					res = Integer.toString(score);
					score_board.setText("score: " + res);
					score_board.setBounds(185, 80, 80, 40);
					score_board.setVisible(true);
					this.add(score_board);
				}
				/*
				//fish's try
				if(i % 12 == 0) {
					//System.out.println(i);
					situation = 0;
					score = i;
					res = Integer.toString(score);
					score_board.setText("score: " + res);
					score_board.setBounds(185, 80, 80, 40);
					score_board.setVisible(true);
					this.add(score_board);
					for(int j = i - 1; j >= 0; j--) {
						rects[j].y = 100000;
 						System.out.println(j);	
 												
					}
				}
				*/			
				
			}
		}
		
		this.repaint();
	}
			
}
