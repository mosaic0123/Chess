package Model;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

class Rook extends ChessPiece {
	
	public Icon getIcon() {
		if(this.color == ChessColor.WHITE){
			return new ImageIcon("Image Assets/Rook.png");
		}
		else{
			return new ImageIcon("Image Assets/rook-black.png");
		}
	}
	
	public Rook (int rank, int file){
		super(rank, file);
	}
	
	public Rook (int rank, int file, boolean isAlive, ChessColor color){
		super(rank, file, isAlive, color);
	}
	
	public ArrayList<Point> possibleMoves(ChessBoard cb){
		ArrayList<Point> moves = new ArrayList<Point>();
		for(int i = -7; i<8; i++){
			 if(isWithinBounds(rank+i, file) && noLeapOver(cb, new Point(rank+i, file))){
				 moves.add(new Point(rank+i, file));
			 }
			 if(isWithinBounds(rank, file+i) && noLeapOver(cb, new Point(rank, file+i))){
				 moves.add(new Point(rank, file+i));
			 }
		}
		return moves;
	} 

	public boolean noLeapOver(ChessBoard cb, Point new_point){
		
		int old_rank = rank;
		int old_file = file;		
		int new_rank = new_point.x;
		int new_file = new_point.y;

		// Move left
		if(new_file<old_file){
			for(int i = 1; i<old_file-new_file; i++){
				if(cb.board[rank][file-i]!=null){
					return false;
				}
			}
		}
		// Move right
		else if(new_file>old_file){
			for(int i = 1; i<new_file-old_file; i++){
				if(cb.board[rank][file+i]!=null){
					return false;
				}
			}
		}
		// Move down
		else if(new_rank>old_rank){
			for(int i = 1; i<new_rank-old_rank; i++){
				if(cb.board[rank+i][file]!=null){
					return false;
				}
			}
		}
		// Move up new_rank<old_rank
		else{
			for(int i = 1; i<old_rank-new_rank; i++){
				if(cb.board[rank-i][file]!=null){
					return false;
				}
			}
		}
		return true;
	}
}