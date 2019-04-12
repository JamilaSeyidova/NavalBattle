package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JButton;

import acm.graphics.GCanvas;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.gui.TableLayout;
import acm.program.GraphicsProgram;
import others.Actor;
import others.BattleField;
import ships.BattleShip;
import ships.Cruiser;
import ships.Ship;

public class Game extends GraphicsProgram {
	private static final long serialVersionUID = 1L;

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public static final int APPLICATION_WIDTH = (int) screenSize.getWidth();
	public static final int APPLICATION_HEIGHT = (int) screenSize.getHeight();

	public JButton btnStart;

	private boolean gameStarted;

	private GPoint last;
	private GObject gobj;
	BattleField bf1, bf2;
	@Override
	public void init() {

		setLayout(new BorderLayout());
		// add(new SelectMenu(), BorderLayout.SOUTH);
		btnStart = new JButton("Start");
		add(btnStart, BorderLayout.SOUTH);

		 bf1 = new BattleField();
		 bf2 = new BattleField();

		Actor actor1 = new Actor("Tofig", bf1, bf2);
		// Actor actor2 = new Actor("Anar");

		add(actor1.getOwnBF(), 0, 0);

		add(actor1.getEnemyBF(), BattleField.LENGTH + 50, 0);

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
		
		int x = (int)gobj.getX()/BattleField.SIDE*BattleField.SIDE;
		int y = (int)gobj.getY()/BattleField.SIDE*BattleField.SIDE;
		
		if(!bf1.contains(x, y)) return;
		
//		System.out.println(bf2.getCellAt(bf2.getLocalPoint(x, y)));
		
		
		
		gobj.setLocation(x, y);
		((Ship)gobj).setFinal(true);
		bf1.setAreaTaken((Ship)gobj, x, y);
		gobj = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start"))
			gameStarted = true;
	}

	@Override
	public void run() {
		while (true) {

			if (gameStarted) {
				showMenu();
			}

			pause(10);
		}
	}

	void showMenu() {
		Ship[] ships = new Ship[3];

		ships[0] = new BattleShip("4.battleship_v.jpg");

		ships[1] = new Cruiser("3.cruiser_v.jpg");
		ships[2] = new Cruiser("3.cruiser_v.jpg");

		for (int i = 0; i < ships.length; i++) {
			add(ships[i], i * 50, APPLICATION_HEIGHT * 0.6);

		}

		gameStarted = false;
	}

	public static void main(String[] args) {
		new Game().start();
	}

}
