package nonlivingThings.relatedCard.cardList.attackCard.normal;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//강타
//2코스트, 피해를 8 줍니다. 취약을 2 부여합니다.
public class Bash extends Card {
	public Bash() {
		cost = 2;
		damage = 8;
		additionWeak = 2;
		canUseEnemy = true;
		kind = CardKind.ATTACK;
	}
}
