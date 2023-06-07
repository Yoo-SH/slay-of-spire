package nonlivingThings.relatedCard.cardList.attackCard.rare;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//몽둥이질
//3코스트, 피해를 32 줍니다.
public class Bludgeon extends Card {
	public Bludgeon() {
		cost = 3;
		damage = 32;
		canUseEnemy = true;
		kind = CardKind.ATTACK;
	}
}
