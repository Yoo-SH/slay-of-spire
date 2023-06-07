package livingThings.fighter.enemyList;

import livingThings.fighter.*;

public class FlyingEye extends Enemy {
	public FlyingEye() {
		hp = maxHp = 17;
		attackPower = 10;
	}
	
	@Override
	public void attack(Protagonist player) {
		int attackKind = (int) (Math.random()*4);
		
		if(attackKind < 3) {		//75%확률로 attack1	
			attack1(player);
		}
		else {
			attack2(player);
		}
	}
	
	//깨물기 or 몸통박치기 공격 -> 특수능력X
	private void attack1(Protagonist player) {
		int motionKind = (int) (Math.random()*2);
		
		if(motionKind == 0) {		//깨물기 모션
			
		}
		else {		//몸통박치기 모션
			
		}
		
		player.hit(this);
	}
	
	//원거리 공격 -> 공격력 3턴간 5감소
	private void attack2(Protagonist player) {
		//원거리 공격 모션
		
		//디버프
		player.setDeBuffDamage(5);
		player.setDeBuffDamageCount(player.getDeBuffDamageCount() + 1);
		player.setDeBuffDamageDuration(3);
	}
}
