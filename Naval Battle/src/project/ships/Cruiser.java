package ships;

import others.BattleField;

public class Cruiser extends Ship {
	
	public Cruiser(String name) {
		super(name);
//		setSize(getWidth(), BattleField.SIDE*3);
		len_h = 1;
		len_v = 3;
		normalize();
	}

}
