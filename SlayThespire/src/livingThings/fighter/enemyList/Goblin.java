package livingThings.fighter.enemyList;

import livingThings.fighter.*;

public class Goblin extends Enemy {
	public Goblin() {
		hp = maxHp = 30;
		attackPower = 7;
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
	
	//휘두르기 공격 -> 특수능력X
	private void attack1(Protagonist player) {
		int motionKind = (int) (Math.random()*2);
		
		if(motionKind == 0) {		//휘두르기 모션1
			
		}
		else {		//휘두르기 모션2
			
		}

		player.hit(this);
	}
	
	//폭탄 공격 -> 공격력 2턴간 8감소
	private void attack2(Protagonist player) {
		//폭탄 공격 모션
		
		//디버프
		player.setDeBuffDamage(8);
		player.setDeBuffDamageCount(player.getDeBuffDamageCount() + 1);
		player.setDeBuffDamageDuration(2);
	}
}
