package others;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;

public class GameMap extends GCompound{

	private BattleField own_bf, enemy_bf;
	private GLabel playerName;
	
	public GameMap(String name) {
		own_bf = new BattleField();
		enemy_bf = new BattleField();
		playerName = new GLabel(name);
		playerName.setFont("Arial-BOLD-50");
		playerName.setColor(Color.RED);
		
		char letter = 'A';
		for (int i = 0; i < 10; i++) {
			add(new GLabel(String.valueOf(letter)), (i+1.5)*BattleField.SIDE, 0.5*BattleField.SIDE);
			add(new GLabel(String.valueOf(letter)), (i+1.5)*BattleField.SIDE+1.1*BattleField.LENGTH, 0.5*BattleField.SIDE);
			add(new GLabel(String.valueOf(i+1)), 0.5*BattleField.SIDE, (i+0.5)*BattleField.SIDE+BattleField.SIDE);
			add(new GLabel(String.valueOf(i+1)), 0.5*BattleField.SIDE + 1.1*BattleField.LENGTH, (i+0.5)*BattleField.SIDE+BattleField.SIDE);
			
			letter ++;			
		}

		add(own_bf, BattleField.SIDE, BattleField.SIDE);
		add(enemy_bf, BattleField.LENGTH+2*BattleField.SIDE, BattleField.SIDE);
		add(playerName, BattleField.LENGTH + BattleField.SIDE, BattleField.LENGTH+2*BattleField.SIDE);
	}

	public BattleField getOwn_bf() {
		return own_bf;
	}

	public BattleField getEnemy_bf() {
		return enemy_bf;
	}

	
}
