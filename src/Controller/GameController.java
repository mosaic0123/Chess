package Controller;

import View.GamePanel;
import View.BoardView;
import Model.ChessBoard;
import Model.Queen;
import Model.Pawn;
import Model.King;
import Model.LastMove;
import Model.ChessPiece;
import Model.Competition;
import Model.Player;
import Model.ChessColor;

import java.awt.*;
import javax.swing.*;

public class GameController {
	
	// Model
	private ChessBoard chessboard;
	// View
	private GamePanel panelView;
	private JButton[][] squares;
	private Competition competition;
	
	public GameController(){
		panelView = new GamePanel();
		panelView.boardview.gc = this;
		panelView.control = this;
		chessboard = new ChessBoard();
	}
	
	/* First check if the first square is empty, if so no move happened
	 * Then check the player is moving his own's pieces
	 * Check destination color, cannot move the piece if the destination has a piece of same color
	 * Check whether the move is legal. If it is legal, change the ChessBoard data and redraw the GamePanel.
	 * If the move is successful, alternate the turn: If the destination is empty, no capture happened*/
	
	public void move(Point p1, Point p2){
		ChessPiece[][] board = chessboard.board;
		ChessPiece pc1 = board[p1.x][p1.y];
		ChessPiece pc2 = board[p2.x][p2.y];
		Player currentPlayer = competition.whiteTurn?competition.player1: competition.player2;
		Player otherPlayer = competition.whiteTurn?competition.player2: competition.player1;
		
		if(pc1==null){
			JOptionPane.showMessageDialog(panelView.mainFrame,
    				"No piece to move!",
                    "Move",
                    JOptionPane.INFORMATION_MESSAGE);
		}
		else if(competition.whiteTurn && pc1.color == ChessColor.BLACK){
			JOptionPane.showMessageDialog(panelView.mainFrame,
					"Please move a white piece!",
                    "Move",
                    JOptionPane.INFORMATION_MESSAGE);
		}
		else if(!competition.whiteTurn && pc1.color == ChessColor.WHITE){
			JOptionPane.showMessageDialog(panelView.mainFrame,
					"Please move a black piece!",
                    "Move",
                    JOptionPane.INFORMATION_MESSAGE);
		}
		else if(pc2!=null && pc1.color==pc2.color){
			JOptionPane.showMessageDialog(panelView.mainFrame,
					"Destination occupied by your piece. Cannot move!",
                    "Move",
                    JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			if(!pc1.isLegalMove(chessboard, p2)){
				JOptionPane.showMessageDialog(panelView.mainFrame,
        				"Not a legal move!",
                        "Move",
                        JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				System.out.println("Move successfully!");
				// Update the last move
				panelView.undoButton.setEnabled(true);
				currentPlayer.moves = new LastMove(pc1, p1, p2, pc2);
				if(pc2==null){
					chessboard.updatePosition(pc1, p2);
					panelView.boardview.removePiece(p1);
					panelView.boardview.addPiece(p2, pc1);
				}
				else{
					chessboard.capture(pc1, pc2);
					panelView.boardview.removePiece(p1);
					panelView.boardview.addPiece(p2, pc1);
					if(pc2 instanceof King){
						win(pc1.color);
					}
				}
				// Check whether the current player is in check before alternating turns
				// If check, pop out a dialog to inform the players
				if(chessboard.check(pc1.color)){
					panelView.alertCheck(ChessColor.WHITE, competition);
				}
				competition.whiteTurn = !competition.whiteTurn;
				panelView.updateTurn(competition);
				if(otherPlayer.moves==null){
					panelView.undoButton.setEnabled(false);
				}
			}
		}
	}
	
	// If a player wins, update the scores and reset the chess board
	public void win(ChessColor color){
		// Update competition info
		if(color==ChessColor.WHITE){
			competition.player1.score+=1;
			panelView.alertCheckmate(ChessColor.WHITE, competition);
		}
		else{
			competition.player2.score+=1;
			panelView.alertCheckmate(ChessColor.BLACK, competition);
		}
		resetGame();
	}
	
	public void forfeitButtonPushed(){
		int n = JOptionPane.showConfirmDialog(
			    panelView.mainFrame,
			    "Are you sure to forfeit the game?",
			    "Forfeit?",
			    JOptionPane.YES_NO_OPTION);
		if(n==0){
			if(competition.whiteTurn){
				competition.player2.score+=1;		
			}
			else{
				competition.player1.score+=1;
			}
			panelView.updateInformation(competition);
			JOptionPane.showMessageDialog(panelView.mainFrame,
    				"Game will restart!",
                    "Forfeit",
                    JOptionPane.INFORMATION_MESSAGE);
			resetGame();
		}
	}
	
	public void undoButtonPushed(){
		Player currentPlayer = competition.whiteTurn?competition.player1: competition.player2;
		Player otherPlayer = competition.whiteTurn?competition.player2: competition.player1;
	    LastMove moves = currentPlayer.moves;
	    LastMove otherMoves = otherPlayer.moves;
	    
	    currentPlayer.redoMoves = new LastMove(moves.piece, moves.position_2, moves.position_1, moves.captured_piece);
	    
	    System.out.println(moves.position_1);
	    System.out.println(moves.position_2);
	    
	    if(moves.captured_piece==null){
	    	chessboard.updatePosition(moves.piece, moves.position_1);
			panelView.boardview.removePiece(moves.position_2);
			panelView.boardview.addPiece(moves.position_1, moves.piece);
			
			if(otherMoves!=null){
				chessboard.updatePosition(otherMoves.piece, otherMoves.position_1);
				panelView.boardview.removePiece(otherMoves.position_2);
				panelView.boardview.addPiece(otherMoves.position_1, otherMoves.piece);
			}
	    }
	    panelView.undoButton.setEnabled(false);
	    panelView.redoButton.setEnabled(true);
	}
	
	// Pushed only right after undo
	public void redoButtonPushed(){
		
		Player currentPlayer = competition.whiteTurn?competition.player1: competition.player2;
		LastMove moves = currentPlayer.redoMoves;
		
		chessboard.updatePosition(moves.piece, moves.position_1);
		panelView.boardview.removePiece(moves.position_2);
		panelView.boardview.addPiece(moves.position_1, moves.piece);
		panelView.undoButton.setEnabled(true);
	    panelView.redoButton.setEnabled(false);
	}
	
	// Yes = 0, No = 1
	public void restartButtonPushed(){
    	int n = JOptionPane.showConfirmDialog(
	    panelView.mainFrame,
	    "Does " +  competition.getPlayer1().getName()+ " agree to restart the game?",
	    "Restart?",
	    JOptionPane.YES_NO_OPTION);
		
    	int m = 1;
    	if(n==0){
    		m = JOptionPane.showConfirmDialog(
    			    panelView.mainFrame,
    			    "Does " +  competition.getPlayer2().getName()+ " agree to restart the game?",
    			    "Restart?",
    			    JOptionPane.YES_NO_OPTION);
    		// Add 1 points to each player
    		if(m==0){
    			JOptionPane.showMessageDialog(panelView.mainFrame,
        				"Game will restart!",
                        "Restart",
                        JOptionPane.INFORMATION_MESSAGE);
    			competition.player1.score+=1;
    			competition.player2.score+=1;
    					
    			/* Here to restart game*/
    			panelView.updateInformation(competition);
    			panelView.mainFrame.repaint();
    			resetGame();
        	}
    		else{
    			JOptionPane.showMessageDialog(panelView.mainFrame,
        				competition.getPlayer2().getName() + " doesn't agree to restart! Game continues!",
                        "Restart",
                        JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	else{
    		JOptionPane.showMessageDialog(panelView.mainFrame,
    				competition.getPlayer1().getName() + " doesn't agree to restart! Game continues!",
                    "Restart",
                    JOptionPane.INFORMATION_MESSAGE);
    	}
    	
	}
	
//	public void newCompetitionButtonPushed(){
//		JOptionPane.showMessageDialog(panelView.mainFrame,
//				"Game will restart!",
//                "Restart",
//                JOptionPane.INFORMATION_MESSAGE);
//		competition.player1.score+=1;
//		competition.player2.score+=1;
//				
//		/* Here to restart game*/
//		panelView.updateInformation(competition);
//		panelView.mainFrame.repaint();
//		resetGame();
//	}
	
	public void resetGame(){
		// Reset the moves
		competition.player1.moves = null;
		competition.player2.moves = null;
		competition.player1.redoMoves = null;
		competition.player2.redoMoves = null;
		competition.whiteTurn = true;
		
		panelView.boardview.resetBoard();
		chessboard = new ChessBoard();
	}
	
    public static void main(String[] args) {
    	GameController control = new GameController();
    	
    	String player1Name = JOptionPane.showInputDialog(null,
                "Enter the name of Player 1",
                "Chess Game", JOptionPane.INFORMATION_MESSAGE);
    	Player player1 = new Player(ChessColor.WHITE, player1Name, 0);
    	
    	String player2Name = JOptionPane.showInputDialog(null,
                "Enter the name of Player 2",
                "Chess Game", JOptionPane.INFORMATION_MESSAGE);
    	
    	while(player2Name.equals(player1Name)){
    		player2Name = JOptionPane.showInputDialog(null,
                    "Enter the name of Player 2",
                    "You must enter a different name", JOptionPane.INFORMATION_MESSAGE);
    	}
    	
    	Player player2 = new Player(ChessColor.BLACK, player2Name, 0);
    	control.competition = new Competition(player1, player2);
    	control.panelView.updateInformation(control.competition);
    	control.panelView.updateTurn(control.competition);
    }
}
