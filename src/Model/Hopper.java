package Model;

import java.awt.Point;
import java.util.ArrayList;

public class Hopper extends ChessPiece {

	public Hopper (int rank, int file){
		super(rank, file);
	}
	
	public Hopper (int rank, int file, boolean isAlive, ChessColor color){
		super(rank, file, isAlive, color);
	}
	
	@Override
	public ArrayList<Point> possibleMoves(ChessBoard cb) {
		ArrayList<Point> moves = new ArrayList<Point>();
		
		if(isWithinBounds(rank-2, file) && cb.board[rank-2][file]!=null){
			moves.add(new Point(rank-2, file));
		}
		if(isWithinBounds(rank+2, file) && cb.board[rank+2][file]!=null){
			moves.add(new Point(rank+2, file));
		}
		if(isWithinBounds(rank, file-2) && cb.board[rank][file-2]!=null){
			moves.add(new Point(rank, file-2));
		}
		if(isWithinBounds(rank, file+2) && cb.board[rank][file+2]!=null){
			moves.add(new Point(rank, file+2));
		}
		
		return moves;
	}

}
