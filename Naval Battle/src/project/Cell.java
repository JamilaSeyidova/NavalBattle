package project;

public class Cell {
	public static final double CELL_SIZE = 50.0;
	private boolean isTouched;
	private double numCor;
	private char charCor;

	public Cell(double number, char ch, double chInex) {
		this.numCor = number;
		this.charCor = ch;

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

	public void setNumCor(int numCor) {
		this.numCor = numCor;
	}

	public char getCharCor() {
		return charCor;
	}

	public void setCharCor(char charCor) {
		this.charCor = charCor;
	}

}
