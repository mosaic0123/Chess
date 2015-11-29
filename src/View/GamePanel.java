package View;

import javax.swing.*;

import Controller.GameController;

import java.awt.*;
import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A Panel that contains the scores, toolbar and board

public class GamePanel implements ActionListener{
    public JButton undoButton;
    public JButton redoButton;
    public JButton forfeitButton;
    public JButton restartButton;
    public JButton newButton;
	public JFrame mainFrame;
	public JPanel mainPanel;
	private JToolBar toolbar;
	private JLabel firstScore = new JLabel("0");
    private JLabel scoreSeparator = new JLabel(":");
    private JLabel secondScore = new JLabel("0");
    private JLabel firstPlayer = new JLabel("Player 1");
    private JLabel secondPlayer = new JLabel("Player 2");
    private JLabel currentPlayer = new JLabel("Player 1");
    private JLabel turnLabel = new JLabel("'s turn");
    public GameController control;
    
    public BoardView boardview;
    
	public GamePanel(){
		toolbar = new JToolBar();

        undoButton = new JButton("Undo");
        undoButton.setEnabled(false);
        undoButton.setActionCommand("Undo");
        undoButton.addActionListener(this);
        toolbar.add(undoButton);
        toolbar.addSeparator();

        redoButton = new JButton("Redo");
        redoButton.setEnabled(false);
        redoButton.setActionCommand("Redo");
        redoButton.addActionListener(this);
        toolbar.add(redoButton);
        toolbar.addSeparator();

        forfeitButton = new JButton("Forfeit Game");
        forfeitButton.setActionCommand("Forfeit");
        forfeitButton.addActionListener(this);
        toolbar.add(forfeitButton);
        toolbar.addSeparator();

        restartButton = new JButton("Restart Game");
        restartButton.setActionCommand("Restart");
        restartButton.addActionListener(this);
        toolbar.add(restartButton);
        toolbar.addSeparator();
        
        newButton = new JButton("New Competition");
        restartButton.setActionCommand("NewCompetition");
        restartButton.addActionListener(this);
        toolbar.add(newButton);
        toolbar.addSeparator();
        
        toolbar.add(firstPlayer);
        toolbar.addSeparator();
        toolbar.add(firstScore);
        toolbar.addSeparator();
        toolbar.add(scoreSeparator);
        toolbar.addSeparator();
        toolbar.add(secondScore);
        toolbar.addSeparator();
        toolbar.add(secondPlayer);
        toolbar.addSeparator();
        toolbar.addSeparator();
        toolbar.add(currentPlayer);
        toolbar.add(turnLabel);
        toolbar.addSeparator();
 
              
		mainFrame = new JFrame("Chess Game");
//		mainFrame.setSize(800, 800);

		// Use mainPanel to arrange the boardview, toolbar and score information
		mainPanel = new JPanel(new BorderLayout());
		boardview = new BoardView();
		boardview.setPreferredSize(new Dimension(640,640));

		mainPanel.add(toolbar, BorderLayout.SOUTH);
		mainPanel.add(boardview, BorderLayout.CENTER);
		mainPanel.setVisible(true);
		
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);   	
		mainFrame.pack();
		mainFrame.setMinimumSize(mainFrame.getSize());
	}
	
	public void updateInformation(Competition competition){
		Player player1 = competition.getPlayer1();
		Player player2 = competition.getPlayer2();
		firstPlayer.setText(player1.getName());
		firstScore.setText(player1.getScore()+"");
		secondPlayer.setText(player2.getName());
		secondScore.setText(player2.getScore()+"");
	}
	
	public void updateTurn(Competition competition){
		Player player1 = competition.getPlayer1();
		Player player2 = competition.getPlayer2();
		if(competition.whiteTurn){
			currentPlayer.setText(player1.getName());
		}
		else{
			currentPlayer.setText(player2.getName());
		}
	}
	
	public void alertCheck(ChessColor color, Competition competition){
		Player playerInCheck = (color==ChessColor.WHITE)? competition.getPlayer1():competition.getPlayer2();
		JOptionPane.showMessageDialog(this.mainFrame,
                playerInCheck.getName() + " is in check!",
                "Check!",
                JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void alertCheckmate(ChessColor color, Competition competition){
		Player playerInCheck = (color==ChessColor.WHITE)? competition.getPlayer1():competition.getPlayer2();
		JOptionPane.showMessageDialog(this.mainFrame,
                playerInCheck.getName() + " wins!",
                "Checkmate",
                JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Redo")) {
            control.redoButtonPushed();
        } else if (e.getActionCommand().equals("Undo")) {
            control.undoButtonPushed();
        } else if (e.getActionCommand().equals("Forfeit")) {
            control.forfeitButtonPushed();
        } else if (e.getActionCommand().equals("Restart")) {
            control.restartButtonPushed();
        } else if (e.getActionCommand().equals("NewCompetition")) {
            control.restartButtonPushed();
        }
	}
    
}
