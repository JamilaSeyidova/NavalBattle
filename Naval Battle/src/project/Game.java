package project;


//import acm.graphics.GObject;
//import acm.graphics.GPoint;
import acm.program.*;

public class Game extends GraphicsProgram {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		setSize(21 * (int) Cell.CELL_SIZE, 15 * (int) Cell.CELL_SIZE);
		f = new Field();
		add(f);
		Boat b = new Boat(5, false, new Cell(5, 'k', 9));// poluchayetsya ccto mne bex raznici gde ya xochu ctobi cell naxodilsya 
														// ispravit eto
//		Cell cell = new Cell(1, c, chInex)
//		Boat d = new Boat(1, 0, 0, false);
		add(b, 50/2, 50/2);
//		add(d);
		// add(aircraftCarrier, 50*11, 50);
		// GRect carrier;
		// GRect battleship;
		// GRect cruiser;
		// GRect submarine;
		// GRect destroyer;

		/*
		 * for (int i = 0; i < 5; i++) { carrier = new GRect(Field.CELL_SIZE,
		 * Field.CELL_SIZE); carrier.setFilled(true);
		 * 
		 * carrier.setFillColor(Color.CYAN); add(carrier, Field.CELL_SIZE *
		 * Field.cellAlongHeight.length + Field.CELL_SIZE + 5 * i, Field.CELL_SIZE + 5 *
		 * i); }
		 * 
		 * for (int i = 0; i < 4; i++) { battleship = new GRect(Field.CELL_SIZE * 2,
		 * Field.CELL_SIZE); battleship.setFilled(true);
		 * battleship.setFillColor(Color.YELLOW); add(battleship, Field.CELL_SIZE *
		 * Field.cellAlongHeight.length + Field.CELL_SIZE + 5 * i, 3 * Field.CELL_SIZE +
		 * 5 * i); }
		 * 
		 * for (int i = 0; i < 3; i++) { cruiser = new GRect(Field.CELL_SIZE * 3,
		 * Field.CELL_SIZE); cruiser.setFilled(true);
		 * cruiser.setFillColor(Color.ORANGE); add(cruiser, Field.CELL_SIZE *
		 * Field.cellAlongHeight.length + Field.CELL_SIZE + 5 * i, 5 * Field.CELL_SIZE +
		 * 5 * i); } for (int i = 0; i < 3; i++) { submarine = new GRect(Field.CELL_SIZE
		 * * 4, Field.CELL_SIZE); submarine.setFilled(true);
		 * submarine.setFillColor(Color.RED); add(submarine, Field.CELL_SIZE *
		 * Field.cellAlongHeight.length + Field.CELL_SIZE + 5 * i, 7 * Field.CELL_SIZE +
		 * 5 * i); }
		 * 
		 * for (int i = 0; i < 2; i++) { destroyer = new GRect(Field.CELL_SIZE * 5,
		 * Field.CELL_SIZE); destroyer.setFilled(true);
		 * destroyer.setFillColor(Color.GREEN); add(destroyer, Field.CELL_SIZE *
		 * Field.cellAlongHeight.length + Field.CELL_SIZE, 9 * Field.CELL_SIZE + 5 * i);
		 * }
		 * 
		 * addMouseListeners();
		 */
	}

	/*
	 * public void mouseDragged(MouseEvent e) { if (gobj != null && !gobj.equals(f))
	 * {// !!! gobj.move(e.getX() - last.getX(), e.getY() - last.getY()); last = new
	 * GPoint(e.getPoint()); } }
	 * 
	 * public void mouseClicked(MouseEvent e) { if (gobj != null && !gobj.equals(f))
	 * gobj.sendToFront();
	 * 
	 * if (e.getClickCount() == 2) { // rotate } }
	 * 
	 * public void mousePressed(MouseEvent e) { last = new GPoint(e.getPoint()); //
	 * store the location where user clocked gobj = getElementAt(last); // get the
	 * object which sits on the "last" coordinate }
	 * 
	 */

	/* Constants specifying feature size as a fraction of the head size */

//	private GObject gobj;
//	private GPoint last;
	Field f;

	public static void main(String[] args) {
		new Game().start();
	}
}
