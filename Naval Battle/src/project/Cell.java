import acm.graphics.GRect;

public class Cell extends GRect{

	enum CELL_TYPE {
		EMPTY, 
		SHIP_ORIGINAL,
		SHIP_DESTRYOED,
		ATTACK_HIT,
		ATTACK_MISS
	}
	
	public Cell(double width, double height) {
		super(width, height);
		
	}

}
