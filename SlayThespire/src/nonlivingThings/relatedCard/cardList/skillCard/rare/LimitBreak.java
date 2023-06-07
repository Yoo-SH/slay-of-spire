package nonlivingThings.relatedCard.cardList.skillCard.rare;

import nonlivingThings.relatedCard.Card;
import livingThings.fighter.Protagonist;

//한계돌파
//1코스트, 추가 데미지가 두 배로 증가합니다.
public class LimitBreak extends Card {
	public LimitBreak(){
		cost = 1;
		hasSkill = true;
	}
	
	@Override
	public void skill(Protagonist player) {
		player.addBuffDamage(player.getBuffDamage());
	}
}
