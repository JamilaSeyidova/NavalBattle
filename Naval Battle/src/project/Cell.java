package project;

import acm.graphics.GPolygon;

public class Cell {
	public static final double CELL_SIZE = 50.0;
	private boolean isTouched;
	private double numCor;
	private char charCor;
	private double charIndex;

	

	public Cell(double number, char ch, double chInex) {
		this.numCor = number;
		this.charCor = ch;
		this.charIndex = chInex;
		

	}

	// draw rect using a gpolygon
	public static GPolygon createCell() {
		GPolygon poly = new GPolygon();
		poly.addVertex(-Cell.CELL_SIZE / 2, -Cell.CELL_SIZE / 2);
		poly.addVertex(Cell.CELL_SIZE / 2, -Cell.CELL_SIZE / 2);
		poly.addVertex(Cell.CELL_SIZE / 2, Cell.CELL_SIZE / 2);
		poly.addVertex(-Cell.CELL_SIZE / 2, Cell.CELL_SIZE / 2);
		return poly;
	}

	// getters&setters
	public boolean isTouched() {
		return isTouched;
	}

	public void setTouched(boolean isTouched) {
		this.isTouched = isTouched;
	}

	public double getNumCor() {
		return numCor;
	}

	public double getCharIndex() {
		return charIndex;
	}

	public void setCharIndex(double charIndex) {
		this.charIndex = charIndex;
	}

	public void setNumCor(double numCor) {
		this.numCor = numCor;
	}
	public char getCharCor() {
		return charCor;
	}

	public void setCharCor(char charCor) {
		this.charCor = charCor;
	}

}
