package ships;

import ships.Ship.ShipType;

public class Cruiser extends Ship {
	
	public Cruiser(String name) {
		super(name);

		shipType = ShipType.CRUISER;
		
		len_h = 1;
		len_v = 4;
		normalize();
	}

}
