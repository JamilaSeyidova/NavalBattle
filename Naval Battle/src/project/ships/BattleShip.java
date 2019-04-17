package ships;

public class BattleShip extends Ship {

	public BattleShip(String name) {
		super(name);
//		setSize(getWidth(), BattleField.SIDE*4);
		
		len_h = 1;
		len_v = 5;
		normalize();
	}

	
	
}
