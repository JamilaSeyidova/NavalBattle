package others;

import ships.BattleShip;
import ships.Cruiser;
import ships.Destroyer;
import ships.Ship;
import ships.Submarine;

public class Actor {

	public static final int MAX_SHIP = 5;
	
	private String name;
	private GameMap gm;
	private Ship[] ships = new Ship[MAX_SHIP];
	private int numOfUnloadedShips = MAX_SHIP;
	
	public Actor(String name, GameMap gm) {
		this.name = name;
		this.gm = gm;
		
		ships[0] = new BattleShip("4.battleship_v.png");

		ships[1] = new Cruiser("3.cruiser_v.png");

		ships[2] = new Submarine("2.submarine_v.png");
		ships[3] = new Submarine("2.submarine_v.png");

		ships[4] = new Destroyer("1.destroyer_v.png");
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
