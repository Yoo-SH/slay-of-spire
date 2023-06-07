package nonlivingThings.relatedCard.cardList.skillCard.special;

import nonlivingThings.relatedCard.Card;

//위압
//0코스트, 적 전체에게 약화를 1 부여합니다.
public class Intimidate extends Card {
	public Intimidate(){
		cost = 0;
		additionWeakening = 1;
		canUseEnemy = true;
		canUseAll = true;
	}
}
