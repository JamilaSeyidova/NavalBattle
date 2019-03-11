package project;

import acm.graphics.*;

public class Field extends GCompound {

	public static int[] cellAlongHeight = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	char[] cellAlongWidth = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };

	/** Construct a new GFace object with the specified width and height. */
	public Field() {
		int chIndex = 1;
		for (int i : cellAlongHeight) {
			for (char ch : cellAlongWidth) {
				Cell cell = new Cell(i, ch, chIndex);
				GPolygon c = createCell();
				add(c, (i - 0.5) * Cell.CELL_SIZE, (chIndex - 0.5) * Cell.CELL_SIZE);
				chIndex++;
			}
			chIndex = 1;
		}
	}

	// draw rect using a gpolygon
	private GPolygon createCell() {
		GPolygon poly = new GPolygon();
		poly.addVertex(-Cell.CELL_SIZE / 2, -Cell.CELL_SIZE / 2);
		poly.addVertex(Cell.CELL_SIZE / 2, -Cell.CELL_SIZE / 2);
		poly.addVertex(Cell.CELL_SIZE / 2, Cell.CELL_SIZE / 2);
		poly.addVertex(-Cell.CELL_SIZE / 2, Cell.CELL_SIZE / 2);
		return poly;
	}
}
