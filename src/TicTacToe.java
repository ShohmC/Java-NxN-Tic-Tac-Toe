import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener {
	Scanner scan = new Scanner(System.in);
	ImageIcon circleIcon = new ImageIcon("Circle.png");
	ImageIcon xIcon = new ImageIcon("X.png");
	JOptionPane dialog = new JOptionPane();
	JFrame windowFrame = new JFrame();
	JButton[] button;
	boolean[] player1Mark;
	boolean[] player2Mark;
	boolean player1 = true;
	boolean player2 = false;
	int dimensions;
	int drawCounter = 0;
	int MAX_WIDTH = 900;
	int MAX_HEIGHT = 900;
	
	TicTacToe(int launchWindowDimensions){
		this.dimensions = launchWindowDimensions;

		int scaledWidth = ((MAX_WIDTH)/(dimensions*dimensions));
		int scaledHeight = ((MAX_HEIGHT)/(dimensions*dimensions));
		
		button = new JButton[dimensions*dimensions];
		player1Mark = new boolean[dimensions*dimensions];
		player2Mark = new boolean[dimensions*dimensions];
		circleIcon = new ImageIcon(circleIcon.getImage().getScaledInstance(scaledWidth * dimensions, scaledHeight * dimensions, java.awt.Image.SCALE_SMOOTH));
		xIcon = new ImageIcon(xIcon.getImage().getScaledInstance(scaledWidth * dimensions, scaledHeight * dimensions, java.awt.Image.SCALE_SMOOTH));
		
		for(int i = 0; i < button.length; i++) {
			button[i] = new JButton();
			button[i].setPreferredSize(new Dimension(scaledWidth,scaledHeight));
			button[i].addActionListener(this);
			button[i].setFocusable(false);
			this.add(button[i]);
		}
		
		
		this.setTitle("Tic Tac Toe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(MAX_WIDTH,MAX_HEIGHT);
		this.setLayout(new GridLayout(dimensions,dimensions,0,0));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(player1 == true) {
			for(int i = 0; i < button.length; i++) {
				if(e.getSource() == button[i]) {
					button[i].setIcon(circleIcon);
					button[i].setDisabledIcon(circleIcon);
					button[i].setEnabled(false);
					player1Mark[i] = true;
					player1 = false;
					player2 = true;
					drawCounter++;
					if(checkForWin(dimensions, player1Mark)) {
						playerOneWin();
					}
					else if(drawCounter == (dimensions * dimensions)) {
						drawGame();
					}
				}
			}
		}
		else if(player2 == true) {
			for(int i = 0; i < button.length; i++) {
				if(e.getSource() == button[i]) {
					button[i].setIcon(xIcon);
					button[i].setDisabledIcon(xIcon);
					button[i].setEnabled(false);
					player2Mark[i] = true;
					player2 = false;
					player1 = true;
					drawCounter++;
					if(checkForWin(dimensions, player2Mark)) {
						playerTwoWin();
					}
					else if(drawCounter == (dimensions*dimensions)) {
						drawGame();
					}
				}
			}
		}
	}
	
	public boolean checkForWin(int dimensions, boolean[] playerMark) {
	    for (int i = 0; i < dimensions; i++) {
	        boolean rowWin = true;
	        boolean colWin = true;

	        for (int j = 0; j < dimensions; j++) {
	            if (!playerMark[i * dimensions + j]) {
	                rowWin = false;
	            }
	            if (!playerMark[j * dimensions + i]) {
	                colWin = false;
	            }
	        }
	        if (rowWin || colWin) {
	            return true;
	        }
	    }
	    boolean diagonal1Win = true;
	    boolean diagonal2Win = true;
	    for (int i = 0; i < dimensions; i++) {
	        if (!playerMark[i * dimensions + i]) {
	            diagonal1Win = false;
	        }
	        if (!playerMark[i * dimensions + (dimensions - 1 - i)]) {
	            diagonal2Win = false;
	        }
	    }
	    if (diagonal1Win || diagonal2Win) {
	        return true;
	    }
	    return false;
	}
	
	public void playerOneWin() {
		int x = dialog.showConfirmDialog(this,"Player 1 Wins!, Do you want to play again?", "Winner!!!",JOptionPane.YES_NO_CANCEL_OPTION);
		switch(x) {
	    case 0:
	    	resetGame();
	    	break;
	    case 1:
	    	this.dispose();
	    	break;
	    case -1:
	    	this.dispose();
	    	break;
	    default:
	    	break;
	    }
	}
	
	public void playerTwoWin() {
		int x = dialog.showConfirmDialog(this,"Player 2 Wins!, Do you want to play again?", "Winner!!!",JOptionPane.YES_NO_CANCEL_OPTION);
		switch(x) {
	    case 0:
	    	resetGame();
	    	break;
	    case 1:
	    	this.dispose();
	    	break;
	    case -1:
	    	this.dispose();
	    	break;
	    default:
	    	break;
	    }
	}
	
	public void resetGame() {
		for(int i = 0; i < button.length; i++) {
			drawCounter = 0;
			button[i].setEnabled(true);
			button[i].setIcon(null);
			player1Mark[i] = false;
			player2Mark[i] = false;
		}
	}
	
	public void drawGame() {
		drawCounter = 0;
		int y = dialog.showConfirmDialog(this,"Game Draw do you want to play again?", "Draw",JOptionPane.YES_NO_CANCEL_OPTION);
		switch(y) {
	    case 0:
	    	resetGame();
	    	break;
	    case 1:
	    	this.dispose();
	    	break;
	    case -1:
	    	this.dispose();
	    	break;
	    default:
	    	break;
	    }
	}
}