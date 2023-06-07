package livingThings.fighter.enemyList;

import livingThings.fighter.*;

public class SteelGolem extends Enemy {
	public SteelGolem() {
		hp = maxHp = 80;
		attackPower = 6;
	}
	
	@Override
	public void attack(Protagonist player) {
		int motionKind = (int) (Math.random()*2);
		
		if(motionKind == 0) {		//휘두르기 모션1
			
		}
		else {		//휘두르기 모션2
			
		}
		
		player.hit(this);
	}
}
