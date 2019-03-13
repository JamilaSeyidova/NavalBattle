package project;

public class Player {

	private boolean isTurn = false;
	private String name;
	
	public Player(String player_name) {
		this.name = player_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}
	
	
}
