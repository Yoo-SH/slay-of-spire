package nonlivingThings.relatedCard.cardList.skillCard.special;

import nonlivingThings.relatedCard.Card;
import livingThings.fighter.Protagonist;

//사혈
//0코스트, 에너지 2개를 얻습니다. 체력을 3 잃습니다.
public class Bloodletting extends Card {
	public Bloodletting(){
		cost = 0;
		hasSkill = true;
	}
	
	@Override
	public void skill(Protagonist player) {
		player.addEnergy(2);
		if(player.getHp() - 3 < 0) {
			player.setHp(0);
		}
		else {
			player.setHp(player.getHp() - 3);
		}
		
		playSound("/sounds/Bloodletting.wav", 10);
	}
}
