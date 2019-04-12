package others;

public class Actor {

	private String name;
	
	private BattleField own_bf, enemy_bf;
	
	public Actor(String name, BattleField own, BattleField enemy) {
		this.name = name;
		
		this.own_bf = own;
		this.enemy_bf = enemy;
	}
	
	public BattleField getOwnBF() {
		return own_bf;
	}
	
	public BattleField getEnemyBF() {
		return enemy_bf;
	}
}
