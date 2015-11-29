package Model;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class King extends ChessPiece {

	public Icon getIcon() {
		if(this.color == ChessColor.WHITE){
			return new ImageIcon("Image Assets/King.png");
		}
		else{
			return new ImageIcon("Image Assets/king-black.png");
		}
	}
	
	public ArrayList<Point> possibleMoves(ChessBoard cb){
		ArrayList<Point> moves = new ArrayList<Point>();
		if(isWithinBounds(rank-1, file)){
			moves.add(new Point(rank-1, file));
		}
		if(isWithinBounds(rank+1, file)){
			moves.add(new Point(rank+1, file));
		}
		if(isWithinBounds(rank, file-1)){
			moves.add(new Point(rank, file-1));
		}
		if(isWithinBounds(rank, file+1)){
			moves.add(new Point(rank, file+1));
		}
		if(isWithinBounds(rank-1, file-1)){
			moves.add(new Point(rank-1, file-1));
		}
		if(isWithinBounds(rank-1, file+1)){
			moves.add(new Point(rank-1, file+1));
		}
		if(isWithinBounds(rank+1, file+1)){
			moves.add(new Point(rank+1, file+1));
		}
		if(isWithinBounds(rank+1, file-1)){
			moves.add(new Point(rank+1, file-1));
		}
		
		return moves;
	}
	
	public King (int rank, int file){
		super(rank, file);
	}
	
	public King (int rank, int file, boolean isAlive, ChessColor color){
		super(rank, file, isAlive, color);
	}	
	
	public boolean noLeapOver(ChessBoard cb, Point new_point){
		return true;
	}	
}
