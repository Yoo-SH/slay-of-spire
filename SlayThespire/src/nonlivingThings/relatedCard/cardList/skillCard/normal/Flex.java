package nonlivingThings.relatedCard.cardList.skillCard.normal;

import livingThings.fighter.Protagonist;
import nonlivingThings.relatedCard.Card;

//몸풀기
//1코스트, 힘을 2얻습니다.
public class Flex extends Card {
	public Flex(){
		cost = 1;
		additionDamage = 2;
	}
	
	@Override
	public void skill(Protagonist player) {
		playSound("/sounds/Flex.wav", 10);
	}
}
