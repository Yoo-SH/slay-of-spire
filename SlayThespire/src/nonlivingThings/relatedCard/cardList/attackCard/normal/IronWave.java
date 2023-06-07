package nonlivingThings.relatedCard.cardList.attackCard.normal;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//철의 파동
//1코스트, 방어도를 5 얻습니다. 피해를 5 줍니다.
public class IronWave extends Card {
	public IronWave() {
		cost = 1;
		damage = 5;
		additionShield = 5;
		canUseEnemy = true;
		kind = CardKind.ATTACK;
	}
}
