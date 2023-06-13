package nonlivingThings.relatedCard.cardList.skillCard.normal;

import livingThings.fighter.Protagonist;
import nonlivingThings.relatedCard.Card;

//수비
//1코스트, 방어도를 5 얻습니다.
public class Defend extends Card {
	public Defend(){
		cost = 1;
		additionShield = 0;
	}
	
	@Override
	public void skill(Protagonist player) {
		playSound("/sounds/Defend.wav", 10);
	}
}
