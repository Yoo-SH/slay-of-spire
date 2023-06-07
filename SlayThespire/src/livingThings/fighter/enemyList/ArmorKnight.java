package livingThings.fighter.enemyList;

import livingThings.fighter.*;

public class ArmorKnight extends Enemy {
	public ArmorKnight() {
		hp = maxHp = 45;
		attackPower = 12;
	}
	
	@Override
	public void attack(Protagonist player) {
		//공격 모션
		
		player.hit(this);
	}
}
