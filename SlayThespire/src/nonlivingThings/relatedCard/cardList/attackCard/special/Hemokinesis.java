package nonlivingThings.relatedCard.cardList.attackCard.special;

import livingThings.fighter.Enemy;
import livingThings.fighter.Protagonist;
import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//혈류
//1코스트, 체력을 2 잃습니다. 피해를 15 줍니다.
public class Hemokinesis extends Card {
	public Hemokinesis() {
		cost = 1;
		damage = 15;
		canUseEnemy = true;
		kind = CardKind.ATTACK;
	}
	
	@Override
	public void attack(Enemy monster, Protagonist player) {
		//혹시라도 체력이 2보다 작거나 같은 경우
		if(player.getHp() - 2 <= 0) {
			player.setHp(0);
		}
		else {
			player.setHp(player.getHp() - 2);
		}
		
		super.attack(monster, player);
	}
}
