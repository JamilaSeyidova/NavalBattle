import acm.graphics.GCompound;

public class BattleField extends GCompound {

	public static final int MAP_LEN = 10;
	
	Cell[][] map = new Cell[MAP_LEN][MAP_LEN];
	public static double LENGTH = Game.APPLICATION_HEIGHT*0.6;

	public BattleField() {
		final double side = 0.6*Game.APPLICATION_HEIGHT/10;
		
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map.length; col++) {
				map[row][col] = new Cell(side, side);
				
				add(map[row][col], row*side, col*side);
			}
		}
	}
}
