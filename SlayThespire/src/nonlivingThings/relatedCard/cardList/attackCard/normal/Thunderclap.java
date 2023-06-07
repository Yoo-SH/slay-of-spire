package nonlivingThings.relatedCard.cardList.attackCard.normal;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//천둥
//1코스트, 적 전체에게 피해를 4 주고 취약 1을 부여합니다.
public class Thunderclap extends Card {
	public Thunderclap() {
		cost = 1;
		damage = 4;
		additionWeak = 1;
		canUseEnemy = true;
		canUseAll = true;
		kind = CardKind.ATTACK;
	}
}
