import java.util.ArrayList;

import acm.graphics.GCanvas;
import acm.gui.TableLayout;
import acmx.export.javax.swing.JButton;
import ships.BattleShip;
import ships.Ship;

public class SelectMenu extends GCanvas{
	private static final long serialVersionUID = 1L;
	
	public JButton button;
	ArrayList<Ship> items = new ArrayList<Ship>();
		
	
	public SelectMenu() {
		setLayout(new TableLayout(4, 3));
		button = new JButton("Play");
		add(button);
		add(new JButton());
		
//		items.add(new BattleShip("battleship_h.png"));
//		items.add(new Cruiser());
	}
}
