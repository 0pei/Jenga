package fianlProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rank extends JFrame {
	Player[] players = new Player[] {};
	int i = 0;
	JLabel[] LeaderBoard = new JLabel[] {};
	JLabel Title = new JLabel();
    int count = 5;

	public Rank() {
		super("Rank");
		setSize(400, 400);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		readFile();
		setLeaderBoard();
	}

	private void readFile() {
		try {
			File PAndS = new File("score.txt");
			Scanner myReader = new Scanner(PAndS);
			while (myReader.hasNextLine()) {
				players = Arrays.copyOf(players, players.length + 1);
				Player player = new Player();
				String temp = myReader.nextLine();
				String tokens[] = temp.split(" ");
				player.setScore(Integer.parseInt(tokens[tokens.length - 1]));
				player.setName(tokens[0]);
				players[players.length - 1] = player;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		/*for (int k = 0; k < players.length; k++) {
			System.out.println(players[k].getName());
		}*/
	}

	public void swap(int a, int b) {
		Player t = players[b];
		players[b] = players[a];
		players[a] = t;
	}

	private void sort() {
		/*
		 * for(int j = 0;j < players.length;j ++) { score[j] = players[j].getScore(); }
		 */
		Player Max = new Player();
		int maxIndex = 0;

		int l = players.length;
		if(l < 5) count = l;
		for (int k = 0; k < count ; k++) {
			Max = players[k];
			for (int j = k+1; j < l; j++) {
				if (Max.getScore() < players[j].getScore()) {
					Max = players[j];
					maxIndex = j;
				}
			}
			swap(maxIndex, k);
		}

	}

	private void setLeaderBoard() {
		Title.setText("Leaderboard");
		Title.setBounds(85, 0, 300, 40);
		Title.setFont(new java.awt.Font("Dialog", 1, 30));
		Title.setVisible(true);
		Title.setOpaque(false);
		this.add(Title);
		sort();
		int count2 = 1;
		for (int j = 0; j < count + 1; j++) {
			if(j == count) {
				LeaderBoard = Arrays.copyOf(LeaderBoard, LeaderBoard.length + 1);
				JLabel LB = new JLabel();
				LB.setText("");
				LB.setBounds(0, 0, 0, 0);
				LB.setVisible(false);
				LeaderBoard[LeaderBoard.length - 1] = LB;
				this.add(LeaderBoard[LeaderBoard.length - 1]);
			}
			else {
				LeaderBoard = Arrays.copyOf(LeaderBoard, LeaderBoard.length + 1);
				JLabel LB = new JLabel();
				LB.setText((j + 1) + "." + players[j].getName() + ": " + players[j].getScore());
				LB.setBounds(100, count2 * 50, 200, 40);
				LB.setFont(new java.awt.Font("Dialog", 0, 25));
				LB.setVisible(true);
			    LB.setOpaque(false);
				LeaderBoard[LeaderBoard.length - 1] = LB;
				this.add(LeaderBoard[LeaderBoard.length - 1]);
				count2 ++;
			}
			
		}
	}

}
