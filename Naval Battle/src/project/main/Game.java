package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import others.Actor;
import others.BattleField;
import ships.BattleShip;
import ships.Cruiser;
import ships.Destroyer;
import ships.Ship;
import ships.Submarine;

public class Game extends GraphicsProgram {
	private static final long serialVersionUID = 1L;

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public static final int APPLICATION_WIDTH = (int) screenSize.getWidth();
	public static final int APPLICATION_HEIGHT = (int) screenSize.getHeight();

	

	JButton btn_start, btn_next;
	
	private boolean gameStarted, testtttttt;

	private GPoint orig, last;
	private GObject gobj;
	
//	BattleField actor1.getOwnBF(), bf2;
	Actor curr_actor, actor1, actor2;
	
	GLabel lbl_gameStatus;
	
	@Override
	public void init() {
		
		actor1 = new Actor("Tofig", new BattleField(), new BattleField());
		actor2 = new Actor("Ali", new BattleField(), new BattleField());
		
		curr_actor = actor1;
		
		add(actor1.getOwnBF(), 0, 0);
		add(actor1.getEnemyBF(), BattleField.LENGTH + 50, 0);
		
		add(actor2.getOwnBF(), 0, 0);
		add(actor2.getEnemyBF(), BattleField.LENGTH + 50, 0);
		actor2.getOwnBF().setVisible(false);
		actor2.getEnemyBF().setVisible(false);
		
		lbl_gameStatus = new GLabel(actor1.getName() + ", set your map ");
		lbl_gameStatus.setFont("Arial-BOLD-32");
		lbl_gameStatus.setColor(Color.RED);
		lbl_gameStatus.setLocation(APPLICATION_WIDTH - lbl_gameStatus.getWidth(), lbl_gameStatus.getHeight());
		lbl_gameStatus.setVisible(false);
		add(lbl_gameStatus);

//		setLayout(new BorderLayout());
		// add(new SelectMenu(), BorderLayout.SOUTH);
		btn_start = new JButton("Start");
		btn_next = new JButton("Next");
		add(btn_start, BorderLayout.SOUTH);
		add(btn_next, BorderLayout.SOUTH);
		btn_next.setVisible(false);

//		actor1.getOwnBF() = new BattleField();
//		bf2 = new BattleField();
		
		
		

		

//		btnStart.requestFocus();

		
		
		add( new JButton("TEST"), BorderLayout.SOUTH); ///////////////////////////////
		
		addMouseListeners();
		addActionListeners();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (gobj == null) return;
		if (! (gobj instanceof Ship) ) return;
		if (((Ship) gobj).isFinal()) return;
		
		((Ship)gobj).rotate();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
		
		if(gobj instanceof Ship) 
			orig = gobj.getLocation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (gobj == null) return;
		if (! (gobj instanceof Ship) ) return;
		if (((Ship) gobj).isFinal()) return;

		gobj.move(e.getX()-last.getX(), e.getY()-last.getY());
		last = new GPoint(e.getPoint());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (gobj == null) return;
		if (! (gobj instanceof Ship) ) return;
		
		Ship ship = ((Ship)gobj);
		
		GPoint ship_coord = new GPoint();
			
		
		if(!curr_actor.getOwnBF().canAllocateShip(ship, ship_coord)) {
			ship.setLocation(orig);
			return;
		}
		
	
		curr_actor.getOwnBF().allocateShip(ship, ship_coord);
		
		// if vertical and 
//		gobj.setLocation(ship_coord);
		ship.setFinal(true);
		curr_actor.getOwnBF().setAreaTaken(ship, ship_coord);
		gobj = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			
			loadShipMenu();
			
			lbl_gameStatus.setVisible(true);
			gameStarted = true;
			btn_start.setVisible(false);
			btn_next.setVisible(true);
		}
		
		if (e.getActionCommand().equals("Next")) {
			lbl_gameStatus.setLabel(actor2.getName() + ", set your map ");
			
			loadShipMenu();
			
			actor1.getOwnBF().setVisible(false);
			actor1.getEnemyBF().setVisible(false);
			
			actor2.getOwnBF().setVisible(true);
			actor2.getEnemyBF().setVisible(true);
			
			curr_actor = actor2;
		}
		
		if (e.getActionCommand().equals("TEST")) {
			
			actor1.getOwnBF().setVisible(testtttttt);
			testtttttt = ! testtttttt;
		}
			
	}

	@Override
	public void run() {
		while (true) {

//			if (gameStarted) {
//				showMenu();
//			}
			
//			if (testtttttt) {
//				actor1.getOwnBF().setVisible(false);
//				testtttttt = false;
//				System.out.println("llaaa");
//			}

			pause(10);
		}
	}

	void loadShipMenu() {
		Ship[] ships = new Ship[5];
		
		ships[0] = new BattleShip("4.battleship_v.png");
		
		ships[1] = new Cruiser("3.cruiser_v.png");
	
		ships[2] = new Submarine("2.submarine_v.png");
		ships[3] = new Submarine("2.submarine_v.png");
	
		ships[4] = new Destroyer("1.destroyer_v.png");
	
		
		for (int i = 0; i < ships.length; i++) {
			add(ships[i], i * 50, APPLICATION_HEIGHT * 0.6);

		}

		gameStarted = false;
	}

	public static void main(String[] args) {
		new Game().start();
	}

}
