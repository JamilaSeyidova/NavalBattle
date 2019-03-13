package project;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GPolygon;


public class Boat extends GCompound {
	public int lenght;
	private boolean isSunk;
	private double x;
	private double y;
	private boolean isHorizontal;

	public Boat(int lenght, boolean isHor, Cell cel) {
		this.lenght = lenght;
		this.isSunk = false;
		this.x = cel.getNumCor();
		this.y = cel.getCharIndex();
		this.isHorizontal = isHor;
		
		for(int i = 0; i < lenght; i++) {
			if(isHor) {
				GPolygon c = Cell.createCell();
				c.setFilled(true);
				c.setFillColor(Color.CYAN);
				add(c, i*Cell.CELL_SIZE, 0);
			}
			else {
				GPolygon c = Cell.createCell();
				c.setFilled(true);
				c.setFillColor(Color.CYAN);
				add(c, 0, i*Cell.CELL_SIZE);
			}
			
		}
//		add(boat, x, y);
	}


}
