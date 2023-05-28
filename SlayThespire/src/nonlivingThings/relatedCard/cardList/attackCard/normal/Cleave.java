package nonlivingThings.relatedCard.cardList.attackCard.normal;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//절단
//1코스트, 적 전체에게 피해를 8 줍니다.
//적의 수만큼 호출
public class Cleave extends Card {
	public Cleave() {
		cost = 0;
		damage = 8;
		canUseEnemy = true;
		canUseAll = true;
		kind = CardKind.ATTACK;
	}
}
