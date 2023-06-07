package nonlivingThings.relatedCard.cardList.attackCard.normal;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//클로스라인
//2코스트, 피해를 12 줍니다. 약화 2를 부여합니다.
public class Clothesline extends Card {
	public Clothesline() {
		cost = 2;
		damage = 12;
		canUseEnemy = true;
		additionWeakening = 2;
		kind = CardKind.ATTACK;
	}
}
