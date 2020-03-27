package GameDevelopment;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class tictactoe extends JFrame implements ActionListener {
	public static int BOARD_SIZE = 3;
	
	public static enum GameStatus{
		Incomplete,XWins,Zwins,Tie
	}
    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    boolean crossTurn = true;
    public tictactoe() {
    	super.setTitle("TicTacToe");
    	super.setSize(600,600);
    	GridLayout grid = new GridLayout(BOARD_SIZE,BOARD_SIZE);
    	super.setLayout(grid);
    	Font font = new Font("Comic Sans", 1, 100);
    	for(int r=0; r<BOARD_SIZE; r++) {
    		for(int c=0; c<BOARD_SIZE; c++) {
    			JButton button = new JButton("");
    			buttons[r][c] = button;
    			button.setFont(font);
    			button.addActionListener(this);
    			super.add(button);
    		}
    	}
    	super.setResizable(false);
    	super.setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton)e.getSource();
		MakeMove(clickedButton);
		GameStatus gs = this.getGameStatus();
		if(gs==GameStatus.Incomplete) {
			return;
		}
		declareWinner(gs);
		int choice = JOptionPane.showConfirmDialog(this,"Do you want to restart the Game");
		if(choice==JOptionPane.YES_OPTION) {
			for(int r=0; r<BOARD_SIZE; r++) {
	    		for(int c=0; c<BOARD_SIZE; c++) {
	    		buttons[r][c].setText("");
	    		}
	    	}
			crossTurn = true;
		}else {
			super.dispose();
		}
	}
	
	private void MakeMove(JButton clickedButton) {
		String btntext = clickedButton.getText();
		if(btntext.length()>0) {
			JOptionPane.showMessageDialog(this,"Invalid Move");
		}else {
			if(crossTurn) {
				clickedButton.setText("X");
			}else {
				clickedButton.setText("0");
			}
			crossTurn=!crossTurn;
		}
	}
	
	private GameStatus getGameStatus() {
		String text1 ="",text2="";
		int r=0,c=0;
		
		//text inside rows
		
		r=0;
		while(r<BOARD_SIZE) {
			c=0;
			while(c<BOARD_SIZE-1) {
				text1 = buttons[r][c].getText();
				text2 = buttons[r][c+1].getText();
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
				c++;
			}
			if(c==BOARD_SIZE-1) {
				if(text1.equals("X")) {
					return GameStatus.XWins;
				}else {
					return GameStatus.Zwins;
				}
			}
			r++;
		}
		
		//text inside columns
		
		c=0;
		while(c<BOARD_SIZE) {
			r=0;
			while(r<BOARD_SIZE-1) {
				text1 = buttons[r][c].getText();
				text2 = buttons[r+1][c].getText();
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
				r++;
			}
			if(r==BOARD_SIZE-1) {
				if(text1.equals("X")) {
					return GameStatus.XWins;
				}else {
					return GameStatus.Zwins;
				}
			}
			c++;
		}
		
		//text in diagonal1
		
		r=0;
		c=0;
		while(r<BOARD_SIZE-1) {
			text1 = buttons[r][c].getText();
			text2 = buttons[r+1][c+1].getText();
			if(!text1.equals(text2) || text1.length()==0) {
				break;
		}
		r++;
		c++;
	}
     if(r==BOARD_SIZE-1) {
    	  if(text1.equals("X")) {
				return GameStatus.XWins;
			}else {
				return GameStatus.Zwins;
			}
      }
     
     //text in diagonal2
     
 	r=BOARD_SIZE-1;
	c=0;
	while(r>0) {
		text1 = buttons[r][c].getText();
		text2 = buttons[r-1][c+1].getText();
		if(!text1.equals(text2) || text1.length()==0) {
			break;
	}
	r--;
	c++;
 }
 if(r==0) {
	  if(text1.equals("X")) {
			return GameStatus.XWins;
		}else {
			return GameStatus.Zwins;
		}
     }
    String txt ="";
    for(r=0; r<BOARD_SIZE; r++) {
    	for(c=0; c<BOARD_SIZE; c++) {
    		txt = buttons[r][c].getText();
    		if(txt.length()==0) {
    			return GameStatus.Incomplete;
    		}
    	}
    }
    return GameStatus.Tie;
   }
	
	private void declareWinner(GameStatus gs) {
		if(gs==GameStatus.XWins) {
			JOptionPane.showMessageDialog(this,"X Wins");
		}else if(gs==GameStatus.Zwins) {
			JOptionPane.showMessageDialog(this,"Z Wins");
		}else {
			JOptionPane.showMessageDialog(this,"It is a Tie");
		}
	}
}
