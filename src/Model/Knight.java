package Model;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

class Knight extends ChessPiece {
	public Knight (int rank, int file){
		super(rank, file);
	}
	
	public Knight (int rank, int file, boolean isAlive, ChessColor color){
		super(rank, file, isAlive, color);
	}
	
	public Icon getIcon() {
		if(this.color == ChessColor.WHITE){
			return new ImageIcon("Image Assets/Knight.png");
		}
		else{
			return new ImageIcon("Image Assets/knight-black.png");
		}
	}
	
	/* The knight moves to any of the closest squares that are not on the same rank, file, or diagonal, thus the move forms an 
	 * "L"-shape: two squares vertically and one square horizontally, or two squares horizontally and one square vertically. 
	 * The knight is the only piece that can leap over other pieces. */

	public ArrayList<Point> possibleMoves(ChessBoard cb){
		ArrayList<Point> moves = new ArrayList<Point>();
		if(isWithinBounds(rank-2, file-1)){
			moves.add(new Point(rank-2, file-1));
		}
		if(isWithinBounds(rank-2, file+1)){
			moves.add(new Point(rank-2, file+1));
		}
		if(isWithinBounds(rank+2, file-1)){
			moves.add(new Point(rank+2, file-1));
		}
		if(isWithinBounds(rank+2, file+1)){
			moves.add(new Point(rank+2, file+1));
		}
		if(isWithinBounds(rank-1, file-2)){
			moves.add(new Point(rank-1, file-2));
		}
		if(isWithinBounds(rank-1, file+2)){
			moves.add(new Point(rank-1, file+2));
		}
		if(isWithinBounds(rank+1, file-2)){
			moves.add(new Point(rank+1, file-2));
		}
		if(isWithinBounds(rank+1, file+2)){
			moves.add(new Point(rank+1, file+2));
		}
		
		return moves;
	}
	
	/* Knight is the only piece that can leap over other pieces. Do not need to check leap over */
}
