package Model;

public class Player {

	private ChessColor color;
	public String name;
	public int score;
	public LastMove moves;
	public LastMove redoMoves;
	
	public Player(ChessColor color, String name, int score){
		this.color = color;
		this.name = name;
		this.score = 0;
	}
	
	public String getName() {
		return name;
	}

	public ChessColor getColor() {
		return color;
	}

	public int getScore() {
		return score;
	}	
	
	public void setScore(int score) {
		score = score;
	}	
}
