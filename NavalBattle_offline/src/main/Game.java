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
import acm.graphics.GRoundRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import others.AI;
import others.Actor;
import others.BattleField;
import others.Cell;
import others.Cell.CellStatus;
import others.GameMap;
import ships.Ship;
import ships.Ship.POSE;

public class Game extends GraphicsProgram {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
//		rgen.setSeed(1);
		
		btn_2players = new JButton("2 Players");
		btn_ai = new JButton("1 Player");
		
		add(btn_2players, BorderLayout.NORTH);
		add(btn_ai, BorderLayout.NORTH);
		
		actor1 = new Actor(ACTOR1_NAME, new GameMap(ACTOR1_NAME));
		
		curr_actor = actor1;

		add(curr_actor.getGameMap());


		
		lbl_gameStatus = new GLabel(actor1.getName() + ", set your map ");
		lbl_gameStatus.setFont("Arial-BOLD-32");
		lbl_gameStatus.setColor(new Color(204, 0, 82));
		lbl_gameStatus.setLocation(APPLICATION_WIDTH - lbl_gameStatus.getWidth(), lbl_gameStatus.getHeight());
		lbl_gameStatus.setVisible(false);
		add(lbl_gameStatus);

		btn_setup = new JButton("Set Up");
		btn_random = new JButton("Randomize");
		btn_next = new JButton("Next");
		btn_start = new JButton("Start");
		
		add(btn_setup, BorderLayout.SOUTH);
		add(btn_random, BorderLayout.SOUTH);
		add(btn_next, BorderLayout.SOUTH);
		add(btn_start, BorderLayout.SOUTH);
		btn_next.setVisible(false);
		btn_next.setEnabled(false);
		btn_start.setVisible(false);
		btn_setup.setVisible(false);
		btn_random.setVisible(false);

		btn_setup.requestFocus();

		add(new JButton("TEST"), BorderLayout.SOUTH); ///////////////////////////////

		addMouseListeners();
		addActionListeners();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		if (gobj == null)
//			return;
		if(curr_actor instanceof AI) return;
		
		if (gobj instanceof Ship && !((Ship) gobj).isFinal()) {
			((Ship) gobj).rotate();
			return;
		}
		
		
		if(!gameStarted) return;
		
		BattleField enemy_bf = enemy_actor.getGameMap().getOwn_bf();
//		Cell [][] map = enemy_bf.getMap();
		
		Cell attackedCell = enemy_bf.getCellAt(currCell.getLocation());
		
		System.out.println(currCell.getLocation());
		
		if(attackedCell.getCellStatus() == CellStatus.SHIP_ORIGINAL) {
			currCell.setCellStatus(CellStatus.ATTACK_HIT);
			attackedCell.setCellStatus(CellStatus.SHIP_DESTRYOED); /////maybe here is the problem
			enemy_bf.sendCellToFront(attackedCell);
		

/*
			
//			for checking all vertical
			while (gobj instanceof Ship && !attackedCell.isInitial()) {
				if(((Ship)gobj).getPose() == POSE.VERTICAL) {
//				?????
				}
				else
				{
//					map[i][j--];
				}
			}
			
//			after finding init cell
			
//			if pose vertical 
//			iterate through ships len and count hits
//			num hits == num counted -> ship destroyed
	
*/	

			curr_actor.setScore(curr_actor.getScore()+10);
			if(curr_actor.getScore() == 170) {
				System.out.println(curr_actor.getName() + " WON");
				gameFinished = true;
			}
				
		}
		else if (attackedCell.getCellStatus() == CellStatus.EMPTY) {
			currCell.setCellStatus(CellStatus.ATTACK_MISS);
			attackedCell.setCellStatus(CellStatus.ATTACK_MISS);
			pause(500);
			switchTurnTo(enemy_actor);
			if(curr_actor instanceof AI)
				turnOfAI( enemy_actor.getGameMap().getOwn_bf() );
		}
	}

	private void turnOfAI(BattleField enemy_bf) {
//		Cell [][] map = enemy_bf.getMap();
		
		int i = rgen.nextInt(BattleField.MAP_LEN)*BattleField.SIDE;
		int j = rgen.nextInt(BattleField.MAP_LEN)*BattleField.SIDE;
		
		System.out.println(i/BattleField.SIDE + "       " + j/BattleField.SIDE);
		
		GPoint atck_coor = new GPoint(i, j); 
		
		Cell attackedCell = enemy_bf.getCellAt(atck_coor);
		
		if(attackedCell.getCellStatus() == CellStatus.SHIP_ORIGINAL) {
			currCell.setCellStatus(CellStatus.ATTACK_HIT);
			attackedCell.setCellStatus(CellStatus.SHIP_DESTRYOED); /////maybe here is the problem
			enemy_bf.sendCellToFront(attackedCell);

	

			curr_actor.setScore(curr_actor.getScore()+10);
			if(curr_actor.getScore() == 170) {
				System.out.println(curr_actor.getName() + " WON");
				gameFinished = true;
			}
			
		}
		else if (attackedCell.getCellStatus() == CellStatus.EMPTY) {
			currCell.setCellStatus(CellStatus.ATTACK_MISS);
			attackedCell.setCellStatus(CellStatus.ATTACK_MISS);
			pause(500);
			switchTurnTo(enemy_actor);
			return;
		}
		
		turnOfAI(enemy_bf);	
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if(curr_actor instanceof AI) return;
		
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);

		if (gobj instanceof Ship)
			orig = gobj.getLocation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(curr_actor instanceof AI) return;
		
		if (gobj == null)
			return;
		if (!(gobj instanceof Ship))
			return;
		if (((Ship) gobj).isFinal())
			return;

		gobj.move(e.getX() - last.getX(), e.getY() - last.getY());
		last = new GPoint(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if(curr_actor instanceof AI) return;
		
		if (gobj == null)
			return;
		if (!(gobj instanceof Ship))
			return;

		Ship ship = ((Ship) gobj);
		
		BattleField own_bf = curr_actor.getGameMap().getOwn_bf();

		// ship's local coordinate:
		GPoint ship_glb_coord = ship.getLocation(); 
		
		if (!own_bf.canAllocateShip(ship, ship_glb_coord)) {
			ship.setLocation(orig);
			return;
		}

		own_bf.allocateShip(ship, ship_glb_coord);

//		gobj.setLocation(ship_coord);
		ship.setFinal(true);
		own_bf.setAreaTaken(ship, ship_glb_coord);
		gobj = null;
		
		curr_actor.setNumOfUnloadedShips(curr_actor.getNumOfUnloadedShips()-1);
		if(actor1.getNumOfUnloadedShips() == 0) 
			btn_next.setEnabled(true);
		if(actor2.getNumOfUnloadedShips() == 0) 
			btn_start.setEnabled(true);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		if(curr_actor instanceof AI) return;
		
		if (bomb == null ) return;

		if(!curr_actor.getGameMap().getEnemy_bf().contains(e.getX(), e.getY())) {
//			currCell = null;
			return;
		}
		
		bomb.setLocation(e.getX(), e.getY());
		
		GPoint bomb_lcl_coord = curr_actor.getGameMap().getEnemy_bf().getLocalPoint(e.getX(), e.getY());
		
		if(currCell.getCellStatus() == CellStatus.EMPTY) {
			currCell.setColor(Color.BLACK);
		}
			
		currCell = curr_actor.getGameMap().getEnemy_bf().getCellAt(bomb_lcl_coord);
		
		if(currCell.getCellStatus() == CellStatus.EMPTY) {
			currCell.sendToFront();
			currCell.setColor(Color.CYAN);
		}
		
				

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		switch (e.getActionCommand()) {
		case "2 Players":
			btn_2players.setVisible(false);
			btn_ai.setVisible(false);
			btn_setup.setVisible(true);
			btn_random.setVisible(true);
			
			actor2 = new Actor(ACTOR2_NAME, new GameMap(ACTOR2_NAME));
			enemy_actor = actor2;
			add(enemy_actor.getGameMap());
			enemy_actor.getGameMap().setVisible(false);
			
			
			break;
		case "1 Player":
			btn_2players.setVisible(false);
			btn_ai.setVisible(false);
			btn_setup.setVisible(true);
			btn_random.setVisible(true);
			
			actor2 = new AI(new GameMap(ROBOT_NAME));
			enemy_actor = actor2;
//			add(enemy_actor.getGameMap());
//			enemy_actor.getGameMap().setVisible(false);
			break;
			
		case "Set Up":
			loadShipMenu();

			lbl_gameStatus.setVisible(true);
			btn_setup.setVisible(false);
			btn_random.setVisible(false);
			btn_next.setVisible(true);
			btn_next.setEnabled(false);
			break;
		case "Randomize":
			btn_setup.setVisible(false);
			btn_random.setVisible(false);
//			btn_start.setVisible(true);
			btn_next.setVisible(true);
			btn_next.setEnabled(true);
			
			
			randomMapSet(curr_actor);
			
			break;
		
		case "Next":
			lbl_gameStatus.setLabel(enemy_actor.getName() + ", set your map ");
			
			switchTurnTo(enemy_actor);
			
			if(isMapRandom == true)
			{
				btn_random.setVisible(false);
				btn_setup.setVisible(false);
				btn_next.setVisible(false);
				btn_start.setVisible(true);
				randomMapSet(curr_actor);
			}
			else if(curr_actor instanceof Actor) {
				
				btn_setup.setVisible(false);
				btn_random.setVisible(false);
				
	
	//			actor1.getGameMap().setVisible(false);
	//			actor2.getGameMap().setVisible(true);
	//			curr_actor = actor2;
				
				
	
				btn_next.setVisible(false);
				btn_start.setVisible(true);
				btn_start.setEnabled(false);
				
				loadShipMenu();
			}
			break;

		case "Start":

			if(shipArea != null) {
				remove(shipArea);
				shipArea = null;
			}
			
			bomb = new Bomb();
			add(bomb);
			
			gameStarted = true;
			
			switchTurnTo(actor1);
			
			btn_start.setEnabled(false);
		break;

		case"TEST":

//			actor1.getGameMap().getOwn_bf().setVisible(testtttttt);
//			testtttttt = !testtttttt;
			switchTurnTo(enemy_actor);
			break;
		}

	}

	public void randomMapSet(Actor actor) {
		
		Ship [] ships = actor.getShips();
		int i = 0;
		
		while(actor.getNumOfUnloadedShips() > 0) {
			Ship ship = ships[i];
			
			if(rgen.nextBoolean())
				ship.rotate();
			int x = rgen.nextInt(BattleField.MAP_LEN)*BattleField.SIDE;
			int y = rgen.nextInt(BattleField.MAP_LEN)*BattleField.SIDE;
			
			BattleField own_bf = curr_actor.getGameMap().getOwn_bf();

			// ship's local coordinate:
			GPoint ship_glb_coord = own_bf.getCanvasPoint(x, y); 
			
			if (!own_bf.canAllocateShip(ship, ship_glb_coord)) {
				continue;
			}

			own_bf.allocateShip(ship, ship_glb_coord);

//			gobj.setLocation(ship_coord);
			ship.setFinal(true);
			own_bf.setAreaTaken(ship, ship_glb_coord);
			
			curr_actor.setNumOfUnloadedShips(curr_actor.getNumOfUnloadedShips()-1);
			i++;
			
//			if(curr_actor.getNumOfUnloadedShips() == 0) {
//				curr_actor = actor2;
//				ships = curr_actor.getShips();
//				i = 0;
//			}
			
			isMapRandom = true;
			
		}
		
	}
	
	private void switchTurnTo(Actor actor) {
		
		if(actor instanceof AI) {
			enemy_actor = curr_actor;
			curr_actor = actor;
		}else {
			curr_actor.getGameMap().setVisible(false);
			
			enemy_actor = curr_actor;
			curr_actor = actor;
			
			curr_actor.getGameMap().setVisible(true);
		}
	}
	
	

	@Override
	public void run() {
		while(true) {
//			if(gameStarted)
			if(gameFinished) {
				lbl_gameStatus.setVisible(true);
				lbl_gameStatus.setLabel(curr_actor.getName() + " WON");
				pause(5000);
				exit();
			}
				
			
			pause(2000);
		}
	}


	void loadShipMenu() {
		Ship[] ships = curr_actor.getShips();

		if (shipArea == null) {
			shipArea = new GRoundRect(18 * BattleField.SIDE, ships[0].getLen_v() * BattleField.SIDE);
			shipArea.setFilled(true);
			shipArea.setColor(new Color(1, 159, 209));
			add(shipArea, 5, APPLICATION_HEIGHT * 0.575);
		}

		int counter = 1;
		for (int i = 0; i < ships.length; i++) {
			add(ships[i], counter * BattleField.SIDE + i * 5,
					APPLICATION_HEIGHT * 0.575 + (5 - ships[i].getLen_v()) / 2.0 * BattleField.SIDE);
			counter += ships[i].getLen_v();

		}

	}

	public static void main(String[] args) {
		new Game().start();
	}

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public static final int APPLICATION_WIDTH = (int) screenSize.getWidth();
	public static final int APPLICATION_HEIGHT = (int) screenSize.getHeight();
	public static final String ACTOR1_NAME = "JAMILA";
	public static final String ACTOR2_NAME = "PARSHA";
	public static final String ROBOT_NAME = "ROBOT";

	RandomGenerator rgen = RandomGenerator.getInstance();
	
	/* Control variables */
	boolean gameStarted, gameFinished, testtttttt, isMapRandom;
	
	/* UI (Swing) variables */
	JButton btn_setup, btn_next, btn_start, btn_random, btn_2players, btn_ai;

	GPoint orig, last;
	GObject gobj;
	GRoundRect shipArea;
	Cell currCell = new Cell(0);
	GLabel lbl_gameStatus;

//	BattleField actor1.getOwnBF(), bf2;
	Actor actor1, actor2;
	Actor curr_actor, enemy_actor;
	Bomb bomb;

}
