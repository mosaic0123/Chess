package Model;

import javax.swing.*;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Icon;

public class Queen extends ChessPiece {
	public Queen (int rank, int file){
		super(rank, file);
	}
	
	public Queen (int rank, int file, boolean isAlive, ChessColor color){
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
		
	public boolean noLeapOver(ChessBoard cb, Point new_point){

		int old_rank = rank;
		int old_file = file;
		int new_rank = new_point.x;
		int new_file = new_point.y;

		// Moves like bishops
		if((old_file-new_file==old_rank-new_rank && (new_file-old_file|new_rank-old_rank)==0) || (new_file-old_file==old_rank-new_rank && (old_file-new_file|new_rank-old_rank)==0) || (old_file-new_file==new_rank-old_rank && (new_file-old_file|old_rank-new_rank)==0) ||
				(new_file-old_file==new_rank-old_rank && (old_file-new_file|old_rank-new_rank)==0)){
			// Moves upper-left
			if(old_file-new_file>0 && old_rank-new_rank>0){
				for(int i = 1; i<old_file-new_file; i++){
					if(cb.board[rank-i][file-i]!=null){
						return false;
					}
				}
			}
			// Moves lower-left
			else if(old_file-new_file>0 && new_rank-old_rank>0){
				for(int i = 1; i<new_rank-old_rank; i++){
					if(cb.board[rank+i][file-i]!=null){
						return false;
					}
				}
			}
			// Moves lower-right
			else if(new_file-old_file>0 && old_rank-new_rank>0){
				for(int i = 1; i<old_rank-new_rank; i++){
					if(cb.board[rank-i][file+i]!=null){
						return false;
					}
				}
			}
			// Moves upper-right
			else{
				for(int i = 0; i<new_file-old_file; i++){
					if(cb.board[rank+i][file+i]!=null){
						return false;
					}
				}
			}
		}
		// Moves like rooks
		else{
			// Moves left
			if(old_file-new_file>0){
				for(int i = 1; i<old_file-new_file; i++){
					if(cb.board[rank][file-i]!=null){
						return false;
					}
				}
			}
			// Moves right
			else if(new_file-old_file>0){
				for(int i = 1; i<new_file-old_file; i++){
					if(cb.board[rank][file+i]!=null){
						return false;
					}
				}
			}
			// Moves down
			else if(new_rank-old_rank>0){
				for(int i = 1; i<new_rank-old_rank; i++){
					if(cb.board[rank+i][file]!=null){
						return false;
					}
				}
			}
			// Moves up
			else{
				for(int i = 1; i<old_rank-new_rank; i++){
					if(cb.board[rank-i][file]!=null){
						return false;
					}
				}
			}
		}
		
		return true;
	}

	public Icon getIcon() {
		if(this.color == ChessColor.WHITE){
			return new ImageIcon("Image Assets/Queen.png");
		}
		else{
			return new ImageIcon("Image Assets/queen-black.png");
		}
	}
}