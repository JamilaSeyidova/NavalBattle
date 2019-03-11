package project;

import com.sun.corba.se.impl.oa.poa.Policies;

import acm.graphics.GCompound;
import acm.graphics.GPolygon;

public class Boat extends GCompound {
	public int lenght;
	private boolean isSunk;
	private int x;
	private int y;
	private boolean isHorizontal;

	public Boat(int lenght, int x, int y, boolean isHor) {
		this.lenght = lenght;
		this.isSunk = false;
		this.x = x;
		this.y = y;
		this.isHorizontal = isHor;
		GPolygon boat = drawBoat(lenght, isHor);
		add(boat, x, y);
	}

	public GPolygon drawBoat(int size, boolean isHor) {
		GPolygon poly = new GPolygon();
		if (isHor == true) {
			poly.addVertex(-(Cell.CELL_SIZE * size / 2), 0);
			poly.addArc(Cell.CELL_SIZE * size / 2, 0, 180, 0);
			poly.addArc(-(Cell.CELL_SIZE * size / 2), 0, 0, -180);
		}
		else {
			poly.addVertex(0, -(Cell.CELL_SIZE * size / 2));
			poly.addArc(0, Cell.CELL_SIZE * size / 2, 90, -90);
			poly.addArc(0, -(Cell.CELL_SIZE * size / 2), -90, -270);
		}
		return poly;
	}

}
