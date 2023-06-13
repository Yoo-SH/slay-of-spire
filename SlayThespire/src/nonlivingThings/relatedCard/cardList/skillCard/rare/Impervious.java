package nonlivingThings.relatedCard.cardList.skillCard.rare;

import livingThings.fighter.Protagonist;
import nonlivingThings.relatedCard.Card;

//무적
//2코스트, 방어도를 30 얻습니다.
public class Impervious extends Card {
	public Impervious(){
		cost = 2;
		additionShield = 30;
	}
	
	@Override
	public void skill(Protagonist player) {
		playSound("/sounds/Defend.wav", 10);
	}
}
