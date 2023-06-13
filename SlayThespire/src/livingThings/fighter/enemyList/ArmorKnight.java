package livingThings.fighter.enemyList;

import livingThings.fighter.*;

//갑옷 골렘 -> 왜 Knight로 했지? 심지어 armored도 아니네...
public class ArmorKnight extends Enemy {
	public ArmorKnight() {
		hp = maxHp = 45;
		attackPower = 12;
	}
	
	@Override
	public void attack(Protagonist player) {
		//공격 모션
		
		playSound("/sounds/ArmorKnightAttack1.wav", 200);
		playSound("/sounds/ArmorKnightAttack2.wav", 400);
		playSound("/sounds/ArmorKnightAttack3.wav", 300);
		
		player.hit(this);
	}
}
