package nonlivingThings.relatedCard.cardList.attackCard.normal;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//타격
//1코스트, 피해를 6 줍니다.
public class Strike extends Card {
	public Strike() {
		cost = 1;
		damage = 6;
		canUseEnemy = true;
		kind = CardKind.ATTACK;
	}
}
