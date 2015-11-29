package Model;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Pawn extends ChessPiece {
	
	public boolean hasMoved;
	public Pawn (int rank, int file){
		super(rank, file);
		hasMoved = false;
		name = "Pawn";
	}

	public Icon getIcon() {
		if(this.color == ChessColor.WHITE){
			return new ImageIcon("Image Assets/Pawn.png");
		}
		else{
			return new ImageIcon("Image Assets/pawn-black.png");
		}
	}
	
	public Pawn (int rank, int file, boolean isAlive, ChessColor color){
		super(rank, file, isAlive, color);
		hasMoved = false;
		name = "Pawn";
	}

	// If the pawn hasn't been moved, it could forward either one or two steps
	public ArrayList<Point> possibleMoves(ChessBoard cb){
		ArrayList<Point> moves = new ArrayList<Point>();
		if(color==ChessColor.WHITE){
			if(!hasMoved){
				if(isWithinBounds(rank-2, file) && cb.board[rank-2][file] == null){
				moves.add(new Point(rank-2, file));
				}
			}
			if(isWithinBounds(rank-1, file) && cb.board[rank-1][file] == null){
				moves.add(new Point(rank-1, file));
			}
			if(isWithinBounds(rank-1, file-1) && cb.board[rank-1][file-1] != null){
				moves.add(new Point(rank-1, file-1));
			}
			if(isWithinBounds(rank-1, file+1) && cb.board[rank-1][file+1] != null){
				moves.add(new Point(rank-1, file+1));
			}
		}
		else{
			if(!hasMoved){
				if(isWithinBounds(rank+2, file) && cb.board[rank+2][file] == null){
				moves.add(new Point(rank+2, file));
				}
			}
			if(isWithinBounds(rank+1, file) && cb.board[rank+1][file] == null){
				moves.add(new Point(rank+1, file));
			}
			if(isWithinBounds(rank+1, file-1) && cb.board[rank+1][file-1] != null){
				moves.add(new Point(rank+1, file-1));
			}
			if(isWithinBounds(rank+1, file+1) && cb.board[rank+1][file+1] != null){
				moves.add(new Point(rank+1, file+1));
			}
		}
		
		return moves;
	}

}
