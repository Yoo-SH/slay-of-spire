package livingThings.fighter.enemyList;

import livingThings.fighter.*;

public class Mushroom extends Enemy {
	public Mushroom() {
		hp = maxHp = 30;
		attackPower = 6;
	}
	
	@Override
	public void attack(Protagonist player) {
		int attackKind = (int) (Math.random()*2);
		
		if(attackKind < 1) {		//50%확률로 attack1	
			attack1(player);
		}
		else {
			attack2(player);
		}
		
	}
	
	//때리기 or 깨물기 공격 -> 특수능력X
	private void attack1(Protagonist player) {
		int motionKind = (int) (Math.random()*2);
		
		if(motionKind == 0) {		//때리기 모션
			
			playSound("/sounds/MushroomAttack1-1.wav", 300);
		}
		else {		//깨물기 모션
			
			playSound("/sounds/MushroomAttack1-2.wav", 300);
		}
		
		player.hit(this);
	}
	
	//포자 공격 -> 공격력 2턴간 3감소 -> 확률 높음
	private void attack2(Protagonist player) {
		//폭탄 공격 모션
		
		playSound("/sounds/MushroomAttack2.wav", 300);
		
		//디버프
		player.setDeBuffDamage(3);
		player.setDeBuffDamageCount(player.getDeBuffDamageCount() + 1);
		player.setDeBuffDamageDuration(2);
	}
}
