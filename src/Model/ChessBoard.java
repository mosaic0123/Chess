package Model;

import java.awt.*;

public class ChessBoard {
	
	public ChessPiece[][] board = new ChessPiece[8][8];
	public ChessSet whiteSet;
	public ChessSet blackSet;
	public boolean whiteChecked = false;
	public boolean blackChecked = false;
	
	public ChessBoard(){
		whiteSet = new ChessSet(true);
		blackSet = new ChessSet(false);
		
		for(ChessPiece pc: whiteSet.aliveSet){
			board[pc.rank][pc.file] = pc;
		}
		
		for(ChessPiece pc: blackSet.aliveSet){
			board[pc.rank][pc.file] = pc;
		}
	}
	
	// Color - in attack
	public boolean check(ChessColor color){
		if(color==ChessColor.WHITE){
			for(ChessPiece pc: whiteSet.aliveSet){
				if(pc.possibleMoves(this).contains(blackSet.king)){
					System.out.println("White checkmate!");
					whiteChecked = true;
					return true;
				}
			}
			return false;
			}
		else{
			for(ChessPiece pc: blackSet.aliveSet){
				if(pc.possibleMoves(this).contains(whiteSet.king)){
					System.out.println("Black checkmate!");
					blackChecked = true;
					return true;
				}
			}
			return false;
		}
	}
	
	/* Pass the color what is being attacked*/
	public boolean checkMate(){
		return false;
	}
	
	/* Check of leap over applies to rook, bishop, queen. The method should be called after isLegalMove(), which checks the direction of movement is legal.
	 * Returns true if no leap over, return false if there is leap over
	 */
	
	/* For a legal move for Rook, exactly one of the direction is non-zero and within the bounds
	 *  Check whether there is piece that occupies along the rank or file and move the piece.
	 *  If no obstacles along the way, move the piece and possibly capture the opponent's piece then return true,
	 *  else return false 
	 */

	
	/* The predator chess piece captures the prey check piece */
	/* Before calling the function, must check two pieces are of different colors */
	public void capture(ChessPiece predator, ChessPiece prey){
		
		// White eats black
		if(predator.color == ChessColor.WHITE){
			blackSet.aliveSet.remove(prey);
		}
		// Black eats white
		else{
			whiteSet.aliveSet.remove(prey);
		}
		
		board[predator.rank][predator.file] = null;
		board[prey.rank][prey.file] = predator;
		predator.rank = prey.rank;
		predator.file = prey.file;
		prey.rank = -1;
		prey.file = -1;
	}
	
	/* The destination has no piece, so move the current piece to the destination and clear the current position*/
	public void updatePosition(ChessPiece pc, Point new_point){
		
		int old_rank = pc.rank;
		int old_file = pc.file;
		int new_rank = new_point.x;
		int new_file = new_point.y;
		
		board[new_rank][new_file] = pc;
		board[old_rank][old_file] = null;
		pc.rank = new_rank;
		pc.file = new_file;
	}
	
	/* Move a piece to the destination. If the destination empty, move the piece by calling movesWithoutCapture.
	 * Else check if the piece at destination is of same color, if not, capture the opponent's piece.
	 * When the user performs a move, it first checks whether the moving direction is correct by calling the
	 * polymorphic isLegalMove() method. Then it calls the corresponding checksLeapOver method to check whether there
	 * are obstacles on the way and move the piece.
	 */
	public boolean movePiece(ChessPiece pc, Point new_point){
		if(pc.isLegalMove(this,new_point)){
			/* Check whether the destination is occupied. If it is occupied by an opponent's piece, capture it and move the
			 * current piece; if it is occupied by the player's piece, return false.
			 */
			if(board[new_point.x][new_point.y]!=null){
				if(board[new_point.x][new_point.y].color==pc.color){
					return false;
				}
				else{
					capture(pc, board[new_point.x][new_point.y]);
					return true;
				}
			}
			else{
				updatePosition(pc, new_point);
				return true;
			}
		}
		else{
			return false;
		}
	}
}
