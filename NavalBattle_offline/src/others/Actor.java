package others;

import ships.BattleShip;
import ships.Cruiser;
import ships.Destroyer;
import ships.Ship;
import ships.Submarine;
import ships.Submarine2;

public class Actor {

	public static final int MAX_SHIP = 5;
	
	private String name;
	private GameMap gm;
	private Ship[] ships = new Ship[MAX_SHIP];
	private int numOfUnloadedShips = MAX_SHIP;
	private int score;
	
	public Actor(String name, GameMap gm) {
		this.name = name;
		this.gm = gm;
		score  = 0;
		
		ships[0] = new BattleShip("4.battleship_v.png");

		ships[1] = new Cruiser("3.cruiser_v.png");

		ships[2] = new Submarine("2.submarine_v.png");
		ships[3] = new Submarine2("2.submarine_v.png");

		ships[4] = new Destroyer("1.destroyer_v.png");
	}

	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getName() {
		return name;
	}
	
	public GameMap getGameMap() {
		return gm;
	}

	public Ship [] getShips () {
		return ships;
	}
	

	public int getNumOfUnloadedShips() {
		return numOfUnloadedShips;
	}


	public void setNumOfUnloadedShips(int numOfUnloadedShips) {
		this.numOfUnloadedShips = numOfUnloadedShips;
	}
	
}
