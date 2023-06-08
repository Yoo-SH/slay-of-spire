package nonlivingThings.relatedCard.cardList.attackCard.normal;

import livingThings.fighter.Enemy;
import livingThings.fighter.Protagonist;
import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//대검
//2코스트, 피해를 14 줍니다. 추가 데미지의 효과가 3배로 적용됩니다.
public class HeavyBlade extends Card {
	public HeavyBlade() {
		cost = 2;
		damage = 14;
		canUseEnemy = true;
		kind = CardKind.ATTACK;
	}
	
	@Override
	public void attack(Enemy monster, Protagonist player) {
		if(monster.getHp() - (damage + (player.getBuffDamage()*3) - player.getDeBuffDamage()) < 0) {	//몬스터 체력이 0보다 작거나 같은 경우 체력을 0으로 만듬
			monster.setHp(0);
		}
		else {
			monster.setHp(monster.getHp() - (damage + (player.getBuffDamage()*3) - player.getDeBuffDamage()));
		}
	}
}
