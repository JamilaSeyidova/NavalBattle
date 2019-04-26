package main;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import others.BattleField;

public class Bomb extends GCompound {

	public static final double COEF = 0.5;
	
	GOval bomb;
	
	public Bomb() {
		bomb = new GOval(BattleField.SIDE*COEF, BattleField.SIDE*COEF);
		bomb.setFilled(true);
		add(bomb, -bomb.getWidth()/2, -bomb.getHeight()/2);
		
	}

}
