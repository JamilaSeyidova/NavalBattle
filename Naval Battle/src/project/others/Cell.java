package others;
import java.awt.Color;

import acm.graphics.GRect;

public class Cell extends GRect{

	boolean cellTaken;
	
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

	public boolean isCellTaken( ) {
		return cellTaken;
	}
	
	public void setCellTaken(boolean flag) {
		setFilled(true);
		setColor(new Color(1, 159, 209));
		cellTaken = flag;
	}
	
	@Override
	public String toString() {
		return "" + cellTaken;
	}
}
