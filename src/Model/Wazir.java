package Model;

import java.awt.Point;
import java.util.ArrayList;

public class Wazir extends ChessPiece {

	public Wazir (int rank, int file){
		super(rank, file);
	}
	
	public Wazir (int rank, int file, boolean isAlive, ChessColor color){
		super(rank, file, isAlive, color);
	}
	
	@Override
	public ArrayList<Point> possibleMoves(ChessBoard cb) {
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
		
		return moves;
	}

}
