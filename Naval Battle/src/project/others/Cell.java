package others;
import java.awt.Color;

import acm.graphics.GRect;

public class Cell extends GRect{

	CellStatus cellStatus;
	
	public enum CellStatus {
		EMPTY, 
		SHIP_ORIGINAL,
		SHIP_DESTRYOED,
		ATTACK_HIT,
		ATTACK_MISS
	}
	
	public Cell(double width, double height) {
		super(width, height);
		cellStatus = CellStatus.EMPTY;
	}

	public CellStatus getCellStatus( ) {
		return cellStatus;
	}
	
	public void setCellStatus(CellStatus cs) {
		cellStatus = cs;
		
		switch (cellStatus) {
		case EMPTY:
			setFilled(false);
			setColor(Color.BLACK);
			break;
		case SHIP_ORIGINAL:
			setFilled(true);
			setColor(Color.CYAN);
			break;
		case SHIP_DESTRYOED:
			setFilled(true);
			setColor(Color.RED);
			break;
		case ATTACK_HIT:
			setFilled(true);
			setColor(Color.BLUE);
			break;
		case ATTACK_MISS:
			setFilled(true);
			setColor(Color.GRAY);
			break;
		}
	}
	
	
	
	@Override
	public String toString() {
		return "" + cellStatus;
	}
}
