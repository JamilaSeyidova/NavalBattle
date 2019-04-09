import acm.program.GraphicsProgram;

public class Game extends GraphicsProgram{
	private static final long serialVersionUID = 1L;

	public static final int APPLICATION_WIDTH = 1200;
	public static final int APPLICATION_HEIGHT = 600;
	
	@Override
	public void init() {
		
		add(new SelectMenu());
		
//		BattleField bf1 = new BattleField();
	//	BattleField bf2 = new BattleField();
		
		
//		Actor actor1 = new Actor("Tofig", bf1, bf2);
//		Actor actor2 = new Actor("Anar");
		
		
		
//		add(actor1.getOwnBF(), 0, 0);
		
//		add(actor1.getEnemyBF(), BattleField.LENGTH+50, 0);
	}
	
	@Override
	public void run() {
		
	}
	
	public static void main(String[] args) {
		new Game().start();
	}

}
