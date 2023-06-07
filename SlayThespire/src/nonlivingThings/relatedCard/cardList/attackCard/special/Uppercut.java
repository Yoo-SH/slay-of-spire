package nonlivingThings.relatedCard.cardList.attackCard.special;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//어퍼컷
//2코스트, 피해를 13 줍니다. 취약을 1 부여합니다. 약화를 1 부여합니다.
public class Uppercut extends Card {
	public Uppercut() {
		cost = 2;
		damage = 13;
		additionWeak = 1;
		additionWeakening = 1;
		canUseEnemy = true;
		kind = CardKind.ATTACK;
	}
}
