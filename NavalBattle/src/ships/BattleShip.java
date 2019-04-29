package ships;

import ships.Ship.ShipType;

public class BattleShip extends Ship {

	public BattleShip(String name) {
		super(name);

		shipType = ShipType.BATTLESHIP;
		
		len_h = 1;
		len_v = 5;
		normalize();
	}

	
	
}
