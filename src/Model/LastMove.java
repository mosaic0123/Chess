package Model;

import java.awt.*;

public class LastMove {
	public ChessColor color;
	public ChessPiece piece;
	public Point position_1;
	public Point position_2;	
	public ChessPiece captured_piece;
	
	public LastMove(ChessPiece piece, Point position_1, Point position_2, ChessPiece captured_piece){
		this.piece = piece;
		this.position_1 = position_1;
		this.position_2 = position_2;
		this.captured_piece = captured_piece;
	};
}
