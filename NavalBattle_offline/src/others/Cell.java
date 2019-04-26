package others;
import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Cell extends GCompound{

	GRect rect;
	GLabel label;
	CellStatus cellStatus;
	
	
	public enum CellStatus {
		EMPTY, 
		SHIP_ORIGINAL,
		SHIP_DESTRYOED,
		ATTACK_HIT,
		ATTACK_MISS
	}
	
	public Cell(double side) {
		rect = new GRect(side, side);
		label = new GLabel("", side*0.2, side*0.9);
		label.setFont("Arial-BOLD-"+(int)side);
		add(rect);
		add(label);
//		super(width, height);
		cellStatus = CellStatus.EMPTY;
	}

	public CellStatus getCellStatus( ) {
		return cellStatus;
	}
	
	public void setCellStatus(CellStatus cs) {
		cellStatus = cs;
		
		switch (cellStatus) {
		case EMPTY:
			rect.setFilled(false);
			rect.setColor(Color.BLACK);
			break;
		case SHIP_ORIGINAL:
//			rect.setFilled(true);
//			rect.setColor(Color.CYAN);
			break;
		case SHIP_DESTRYOED:
			rect.setFilled(false);
//			rect.setColor(Color.RED);
			rect.setColor(new Color(0, 0, 0, 0));
			label.setLabel("X");
			label.setColor(Color.RED);
			label.sendToFront();
			break;
		case ATTACK_HIT:
			rect.setFilled(true);
			rect.setColor(Color.BLUE);
			break;
		case ATTACK_MISS:
			rect.setFilled(true);
			rect.setColor(Color.GRAY);
			break;
		}
	}
	
	
	
	@Override
	public String toString() {
		return "" + cellStatus;
	}
}
