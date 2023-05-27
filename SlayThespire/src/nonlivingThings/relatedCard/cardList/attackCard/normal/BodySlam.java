package nonlivingThings.relatedCard.cardList.attackCard.normal;

import livingThings.fighter.*;
import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//몸통박치기
//1코스트, 현재 방어도만큼 피해를 줍니다.
public class BodySlam extends Card {
	public BodySlam() {
		cost = 1;
		canUseEnemy = true;
		kind = CardKind.ATTACK;
	}
	
	@Override
	public void attack(Enemy monster, Protagonist player) {
		if(monster.getHp() - (damage + player.getShield()) <= 0) {	//몬스터 체력이 0보다 작거나 같은 경우 체력을 0으로 만듬
			monster.setHp(0);
		}
		else {
			monster.setHp(monster.getHp() - (damage + player.getShield()));
		}
	}
}
