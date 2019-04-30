package others;
import acm.graphics.GCompound;
import acm.graphics.GPoint;
import main.Game;
import others.Cell.CellStatus;
import ships.Ship;

public class BattleField extends GCompound {

	public static final int MAP_LEN = 10;
	public static final double COEF = 0.5;
	
	private Cell[][] map = new Cell[MAP_LEN][MAP_LEN];

	public static int LENGTH = (int)(Game.APPLICATION_HEIGHT*COEF);
	public static final int SIDE = LENGTH/10;

	public BattleField() {
//		System.out.println(SIDE);
		
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map.length; col++) {
				Cell c = new Cell(SIDE);
//				c.setFilled(true);
//				c.setFillColor(Color.WHITE);
				map[row][col] = c;
				
				add(map[row][col], row*SIDE, col*SIDE);
			}
		}
	}
	

	public Cell[][] getMap() {
		return map;
	}
	
	public Cell getCellAt(GPoint p) {
		
		int i = (int)p.getX()/SIDE;
		int j = (int)p.getY()/SIDE;
		
		if(i>=MAP_LEN) i = MAP_LEN-1;
		if(j>=MAP_LEN) j = MAP_LEN-1;
		
		return map[i][j];
	}
	
	public void setAreaTaken(Ship s, GPoint ship_glb_coord) {
		
		GPoint ship_lcl_coord = this.getLocalPoint(ship_glb_coord);
		
		int row = (int)ship_lcl_coord.getX()/SIDE;
		int col = (int)ship_lcl_coord.getY()/SIDE;
		
//		for (int i = (row>0)?row-1:0; i < row + s.getLen_h()+ ((row + s.getLen_h() < MAP_LEN)?1:0); i++) {
//			for (int j = (col>0)?col-1:0; j < col + s.getLen_v()+ ((col + s.getLen_v() < MAP_LEN)?1:0); j++) {
//				map[i][j].setCellTaken(true);
//			}
//		}
		
		for (int i = row; i < row + s.getLen_h(); i++) {
			for (int j = col; j < col + s.getLen_v(); j++) {
				map[i][j].setCellStatus(CellStatus.SHIP_ORIGINAL);;
			}
		}
		
	}

	public boolean canAllocateShip(Ship ship, GPoint ship_glb_coord) {
		
		GPoint ship_lcl_coord = this.getLocalPoint(ship_glb_coord);
		
		ship_glb_coord.setLocation(new GPoint((int)ship_glb_coord.getX()/SIDE*SIDE, (int)ship_glb_coord.getY()/SIDE*SIDE));
		
		if(!this.contains(ship_glb_coord) || !this.contains(ship_glb_coord.getX()+ship.getLen_h()*SIDE, ship_glb_coord.getY()+ship.getLen_v()*SIDE))
			return false;
		
		
		int x = (int)ship_lcl_coord.getX()/SIDE*SIDE;
		int y = (int)ship_lcl_coord.getY()/SIDE*SIDE;
		
		for (int i = x/SIDE; i < x/SIDE + ship.getLen_h(); i++) {
			for (int j = y/SIDE; j < y/SIDE + ship.getLen_v(); j++) {
				if(map[i][j].getCellStatus() == CellStatus.SHIP_ORIGINAL)
					return false;
			}
		}
		return true;
	}

	
	public void allocateShip(Ship ship, GPoint ship_glb_coord) {
		
		GPoint ship_lcl_coord = this.getLocalPoint(ship_glb_coord);
		getCellAt(ship_lcl_coord).setInitial(true);
//		System.out.println(ship_lcl_coord);
		add(ship, ship_lcl_coord);
	}

	public void sendCellToFront(Cell attackedCell) {
		attackedCell.sendToFront();
	}


	
	
}
