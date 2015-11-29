package Model;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

class Bishop extends ChessPiece {
	public Bishop (int rank, int file){
		super(rank, file);
	}
	
	public Bishop (int rank, int file, boolean isAlive, ChessColor color){
		super(rank, file, isAlive, color);
	}
	
	public Icon getIcon() {
		if(this.color == ChessColor.WHITE){
			return new ImageIcon("Image Assets/Bishop.png");
		}
		else{
			return new ImageIcon("Image Assets/bishop-black.png");
		}
	}
	// Return an array of possible moves, following the chess rules and checking no leap overs 
	public ArrayList<Point> possibleMoves(ChessBoard cb){
		ArrayList<Point> moves = new ArrayList<Point>();
		for(int i = -7; i<8; i++){
			 if(isWithinBounds(rank+i, file+i) && noLeapOver(cb, new Point(rank+i, file+i))){
				 moves.add(new Point(rank+i, file+i));
			 }
			 if(isWithinBounds(rank-i, file+i) && noLeapOver(cb, new Point(rank-i, file+i))){
				 moves.add(new Point(rank-i, file+i));
			 }
		}
		return moves;
	}
	
	/* A bishop can move any squares diagonally, check 4 input conditions */
	public boolean noLeapOver(ChessBoard cb, Point new_point){
		// Moves upper-left		
		int old_rank = rank;
		int old_file = file;
		int new_rank = new_point.x;
		int new_file = new_point.y;
		
		if(new_file<old_file && new_rank<old_rank){
			for(int i = 1; i<old_file-new_file; i++){
				if(cb.board[old_rank-i][old_file-i]!=null){
					return false;
				}
			}
		}
		// Moves lower-right
		else if(new_file>old_file && new_rank>old_rank){
			for(int i = 1; i<new_rank-old_rank; i++){
				if(cb.board[old_rank+i][old_file+i]!=null){
					return false;
				}
			}
		}
		// Moves upper-right
		else if(new_file>old_file && new_rank<old_rank){
			for(int i = 1; i<old_rank-new_rank; i++){
				if(cb.board[old_rank-i][old_file+i]!=null){
					return false;
				}
			}
		}
		// Moves lower-left
		// new_file<old_file, new_rank>old_rank
		else{
			for(int i = 1; i<new_rank-old_rank; i++){
				if(cb.board[old_rank+i][old_file-i]!=null){
					return false;
				}
			}
		}
		return true;
	}
}
