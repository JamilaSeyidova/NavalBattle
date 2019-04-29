package ships;

public class Destroyer  extends Ship{

	public Destroyer(String name) {
		super(name);
		
		shipType = ShipType.DESTROYER;
		
		len_h = 1;
		len_v = 2;
		normalize();
	}
}
