package livingThings.fighter.enemyList;

import livingThings.fighter.*;

public class Skeleton2 extends  Enemy {
	public Skeleton2() {
		hp = maxHp = 30;
		attackPower = 20;
	}
	
	@Override
	public void attack(Protagonist player) {
		//공격 모션
		
		playSound("/sounds/Skeleton2Attack.wav", 10);
		
		player.hit(this);
	}
}
