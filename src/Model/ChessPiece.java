package Model;

import javax.swing.*;
import java.util.ArrayList;

import Model.ChessBoard;

import java.awt.*;

/*
 A ChessPiece object represents the basic data structure for a generic chess piece on the chess board. It records the position
 information of a chess, and implements the movement and capturing of chess pieces. The movement methods must be implemented by
 the subclasses.
 */


public abstract class ChessPiece {
	
	// Rank (Row) ranges from 0 to 7, File (Column) ranges from 0 to 7 - corresponding to File a to h
	public int rank;
	public int file;
	public boolean isAlive;
	public ChessColor color;
	public String name;
	
	public abstract Icon getIcon();
	
	public ChessPiece (int _rank, int _file){
	// Need to implement bound check
		rank = _rank;
		file = _file;
	}
	
	public ChessPiece (int _rank, int _file, boolean _isAlive, ChessColor _color){
	// Need to implement bound check
		rank = _rank;
		file = _file;
		isAlive = _isAlive;
		color = _color;
	}
		
	public void move(int left, int right, int down, int up){
		file -= left;
		file += right;
		rank -= down;
		rank += up;
	}
	
	public boolean isWithinBounds(int rank, int file){
		if(file<0 || file>7 || rank>7 || rank<0 ){
			return false;
		}
		return true;
	}
	
	public boolean isLegalMove(ChessBoard cb, Point new_point) {
		ArrayList<Point> moves = possibleMoves(cb);
		return moves.contains(new_point);
	}
	
	public abstract ArrayList<Point> possibleMoves(ChessBoard cb);
	
	public boolean noLeapOver(ChessBoard cb, Point new_point){
		return true;
	}
	
	public void movesLeft(){
		file -= 1;
	}
	
	public void movesRight(){
		file += 1;
	}
	
	public void movesDown(){
		rank -= 1;
	}
	
	public void movesUp(){
		rank += 1;
	}	
}

















