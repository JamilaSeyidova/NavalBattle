package others;
import acm.graphics.GCompound;
import acm.graphics.GPoint;
import main.Game;
import ships.Ship;

public class BattleField extends GCompound {

	public static final int MAP_LEN = 10;
	public static final double COEF = 0.5;
	
	Cell[][] map = new Cell[MAP_LEN][MAP_LEN];
	
	public static int LENGTH = (int)(Game.APPLICATION_HEIGHT*COEF);
	public static final int SIDE = LENGTH/10;

	public BattleField() {
//		System.out.println(SIDE);
		
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map.length; col++) {
				Cell c = new Cell(SIDE, SIDE);
//				c.setFilled(true);
//				c.setFillColor(Color.WHITE);
				map[row][col] = c;
				
				add(map[row][col], row*SIDE, col*SIDE);
			}
		}
	}
	
	public Cell getCellAt(GPoint p) {
//		System.out.println(p);
		return map[(int)p.getX()/SIDE][(int)p.getY()/SIDE];
	}
	
	public void setAreaTaken(Ship s, GPoint p) {
		int row = (int)p.getX()/SIDE;
		int col = (int)p.getY()/SIDE;
		
//		for (int i = (row>0)?row-1:0; i < row + s.getLen_h()+ ((row + s.getLen_h() < MAP_LEN)?1:0); i++) {
//			for (int j = (col>0)?col-1:0; j < col + s.getLen_v()+ ((col + s.getLen_v() < MAP_LEN)?1:0); j++) {
//				map[i][j].setCellTaken(true);
//			}
//		}
		
		for (int i = row; i < row + s.getLen_h(); i++) {
			for (int j = col; j < col + s.getLen_v(); j++) {
				map[i][j].setCellTaken(true);
			}
		}
		
	}

	public boolean canAllocateShip(Ship ship, GPoint ship_coord) {
		
		int x = (int)ship.getX()/SIDE*SIDE;
		int y = (int)ship.getY()/SIDE*SIDE;
		
		ship_coord.setLocation(new GPoint(x, y));
		
		if(!this.contains(x, y) || !this.contains(x+ship.getLen_h()*SIDE, y+ship.getLen_v()*SIDE))
			return false;
		
		for (int i = x/SIDE; i < x/SIDE + ship.getLen_h(); i++) {
			for (int j = y/SIDE; j < y/SIDE + ship.getLen_v(); j++) {
				if(map[i][j].isCellTaken())
					return false;
			}
		}
		
		return true;
	}

	
	public void allocateShip(Ship ship, GPoint ship_coord) {
		add(ship, ship_coord);
		
	}
	
	
}
