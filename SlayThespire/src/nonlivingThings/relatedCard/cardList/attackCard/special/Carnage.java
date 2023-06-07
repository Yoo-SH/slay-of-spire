package nonlivingThings.relatedCard.cardList.attackCard.special;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//대학살
//2코스트, 피해를 20 줍니다.
public class Carnage extends Card {
	public Carnage() {
		cost = 2;
		damage = 20;
		canUseEnemy = true;
		kind = CardKind.ATTACK;
	}
}
