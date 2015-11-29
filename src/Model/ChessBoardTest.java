package Model;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ChessBoardTest extends TestCase {

	// Checks the initialization of ChessSet 
	public void testChessSet() {
		ChessSet cs;

		cs = new ChessSet(true);
		assertNotNull("Aliveset is not null", cs.aliveSet);
		
		assertEquals("Aliveset has size 16", cs.aliveSet.size(), 16);
	}
	
	public void testChessBoard() {
		ChessBoard cb;
		
		// Checks the initialization of ChessBoard
		cb = new ChessBoard();
		assertNotNull("ChessBoard is not null", cb);
		assertEquals("Aliveset has size 16", cb.blackSet.aliveSet.size(), 16);
		
//		for(ChessPiece cp: cb.blackSet.aliveSet){
//			System.out.println("Class at (" + cp.rank + "," + cp.file +") is " + cp.getClass());
//		}
		
		// Checks the check pieces are placed at the correct positions
		ChessPiece[][] board = cb.board;

		assertTrue(board[0][3] instanceof King);
		assertTrue(board[1][7] instanceof Pawn);
		assertTrue(board[7][4] instanceof Queen);
		assertTrue(board[7][7] instanceof Rook);
		assertNull(board[2][0]);
		assertNull(board[5][5]);
		
	}

	// (left, right, down, up)
	// Test that the king can only moves one square in any direction within the bounds
	public void testKingLegalMove(){
		ChessPiece king = new King(7,4);
		assertTrue(king.isLegalMove(1, 0, 0, 0));
		assertFalse(king.isLegalMove(2, 0, 0, 0));
		assertTrue(king.isLegalMove(1, 0, 0, 1));
		assertFalse(king.isLegalMove(0, 0, 1, 0));
	}
	
	// Test that the rook can only moves horizontally or vertically
	public void testRookLegalMove(){
		ChessPiece rook = new Rook(5,3);
		assertTrue(rook.isLegalMove(3, 0, 0, 0));
		assertTrue(rook.isLegalMove(0, 0, 0, 5));
		assertFalse(rook.isLegalMove(1, 0, 1, 0));
		assertFalse(rook.isLegalMove(4, 0, 0, 0));
	}
	
	// Test the rook cannot leap over other pieces
	public void testRookLeapOver(){
		ChessBoard cb = new ChessBoard();
		Pawn pawn_1 = (Pawn)cb.whiteSet.pawn_1;
		Rook rook_1 = (Rook)cb.whiteSet.rook_1;
		assertTrue(pawn_1.checksLeapOver(cb, 0, 0, 0, 2));
		// Test the rook cannot leap over the pawn
		assertFalse(rook_1.checksLeapOver(cb, 0, 0, 0, 3));
	}
	
	
	// Test the movement of pawn
	public void testPawnLeapOver(){
		ChessBoard cb = new ChessBoard();
		Pawn pawn_6 = (Pawn)cb.whiteSet.pawn_6;
		// On the first move, the pawn can move forward 2 squares
		assertTrue(pawn_6.checksLeapOver(cb, 0, 0, 0, 2));
		// After the first move, the pawn can only move forward 1 square
		assertFalse(pawn_6.checksLeapOver(cb, 0, 0, 0, 2));
		assertTrue(pawn_6.checksLeapOver(cb, 0, 0, 0, 1));
		
		// Test the capture of opponent's piece
		Pawn black_pawn = (Pawn)cb.blackSet.pawn_5;
		assertTrue(black_pawn.checksLeapOver(cb, 0, 0, 1, 0));
		assertTrue(pawn_6.checksLeapOver(cb, 1, 0, 0, 1));
		// Test the destination is occupied by the capturing piece
		assertEquals(ChessColor.WHITE, cb.board[2][4].color);
	}
	
	// Test the movement of knight
	public void testKnightLeapOver(){
		ChessBoard cb = new ChessBoard();
		Knight knight_1 = (Knight)cb.whiteSet.knight_1;
		// Check the knight could leap over other pieces
		assertTrue(knight_1.checksLeapOver(cb, 0, 1, 0, 2));
		assertTrue(knight_1.checksLeapOver(cb, 0, 1, 0, 2));
		// Check the knight capture opponent's pawn and the opponent's piece is removed after the capture
		assertTrue(knight_1.checksLeapOver(cb, 0, 1, 0, 2));
		assertEquals(ChessColor.WHITE, cb.board[1][4].color);
		assertEquals(15, cb.blackSet.aliveSet.size());
	}
	
	public void testMovesPiece(){
		ChessBoard cb = new ChessBoard();
		Knight knight_1 = (Knight)cb.whiteSet.knight_1;
		assertTrue(cb.movesPiece(knight_1, 7, 5, 1, 2));
		Pawn pawn_4 = (Pawn)cb.blackSet.pawn_4;
		assertTrue(cb.movesPiece(pawn_4, 1, 2, 4, 4));
	}
}
