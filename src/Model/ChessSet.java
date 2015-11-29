package Model;

import java.util.*;

public class ChessSet {
	
	// 16 chess pieces as attributes
	public ChessPiece king;
	public ChessPiece queen;
	public ChessPiece rook_1, rook_2;
	public ChessPiece knight_1, knight_2;
	public ChessPiece bishop_1, bishop_2;
	public ChessPiece pawn_1, pawn_2, pawn_3, pawn_4, pawn_5, pawn_6, pawn_7, pawn_8;
	public ChessColor color;
	public Set<ChessPiece> aliveSet;
	
	// Initialize a chess set with 16 pieces
	public ChessSet(boolean isWhite){

		// If the set is white, it is placed at the bottom of the board
		if(isWhite){
			color = ChessColor.WHITE;
			rook_1 = new Rook(7,0, true, ChessColor.WHITE);
			knight_1 = new Knight(7,1, true, ChessColor.WHITE);
			bishop_1 = new Bishop(7,2, true, ChessColor.WHITE);
			king = new King(7,3, true, ChessColor.WHITE);
			queen = new Queen(7,4, true, ChessColor.WHITE);
			bishop_2 = new Bishop(7,5, true, ChessColor.WHITE);
			knight_2 = new Knight(7,6, true, ChessColor.WHITE);
			rook_2 = new Rook(7,7, true, ChessColor.WHITE);
			pawn_1 = new Pawn(6,0, true, ChessColor.WHITE);
			pawn_2 = new Pawn(6,1, true, ChessColor.WHITE);
			pawn_3 = new Pawn(6,2, true, ChessColor.WHITE);
			pawn_4 = new Pawn(6,3, true, ChessColor.WHITE);
			pawn_5 = new Pawn(6,4, true, ChessColor.WHITE);
			pawn_6 = new Pawn(6,5, true, ChessColor.WHITE);
			pawn_7 = new Pawn(6,6, true, ChessColor.WHITE);
			pawn_8 = new Pawn(6,7, true, ChessColor.WHITE);
		}
		else{
			color = ChessColor.BLACK;
			rook_1 = new Rook(0,0, true, ChessColor.BLACK);
			knight_1 = new Knight(0,1, true, ChessColor.BLACK);
			bishop_1 = new Bishop(0,2, true, ChessColor.BLACK);
			king = new King(0,3, true, ChessColor.BLACK);
			queen = new Queen(0,4, true, ChessColor.BLACK);
			bishop_2 = new Bishop(0,5, true, ChessColor.BLACK);
			knight_2 = new Knight(0,6, true, ChessColor.BLACK);
			rook_2 = new Rook(0,7, true, ChessColor.BLACK);
			pawn_1 = new Pawn(1,0, true, ChessColor.BLACK);
			pawn_2 = new Pawn(1,1, true, ChessColor.BLACK);
			pawn_3 = new Pawn(1,2, true, ChessColor.BLACK);
			pawn_4 = new Pawn(1,3, true, ChessColor.BLACK);
			pawn_5 = new Pawn(1,4, true, ChessColor.BLACK);
			pawn_6 = new Pawn(1,5, true, ChessColor.BLACK);
			pawn_7 = new Pawn(1,6, true, ChessColor.BLACK);
			pawn_8 = new Pawn(1,7, true, ChessColor.BLACK);
		}
		
		aliveSet = new HashSet<ChessPiece>();
		aliveSet.add(rook_1);
		aliveSet.add(knight_1);
		aliveSet.add(bishop_1);
		aliveSet.add(king);
		aliveSet.add(queen);
		aliveSet.add(bishop_2);
		aliveSet.add(knight_2);
		aliveSet.add(rook_2);
		aliveSet.add(pawn_1);
		aliveSet.add(pawn_2);
		aliveSet.add(pawn_3);
		aliveSet.add(pawn_4);
		aliveSet.add(pawn_5);
		aliveSet.add(pawn_6);
		aliveSet.add(pawn_7);
		aliveSet.add(pawn_8);
	}
}
