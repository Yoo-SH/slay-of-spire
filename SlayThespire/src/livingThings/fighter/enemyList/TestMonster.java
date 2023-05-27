package livingThings.fighter.enemyList;

import livingThings.fighter.Enemy;
import livingThings.fighter.Protagonist;

public class TestMonster extends Enemy {
	public TestMonster() {
		hp = maxHp = 15;
		attackPower = 4;
	}
	
	@Override
	public void attack(Protagonist player) {
		System.out.println("TestMonster");
	}
}
