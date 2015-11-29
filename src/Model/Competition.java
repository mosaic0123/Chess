package Model;

public class Competition {
	public Player player1;
	public Player player2;
	// Player 1's turn if whiteTurn is true, Player 2's turn if whiteTurn is false
	public boolean whiteTurn = true;
	
	public Competition(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
}

