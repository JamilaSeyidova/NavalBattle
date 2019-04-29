package ships;

import ships.Ship.ShipType;

public class Submarine extends Ship {
	
	public Submarine(String name) {
		super(name);
		
		shipType = ShipType.SUBMARINE;
		
		len_h = 1;
		len_v = 3;
		normalize();
	}
}
