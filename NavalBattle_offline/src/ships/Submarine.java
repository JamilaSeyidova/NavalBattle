package ships;

public class Submarine extends Ship {

	public Submarine(String name) {
		super(name);
		len_h = 1;
		len_v = 3;
		normalize();
	}
}
