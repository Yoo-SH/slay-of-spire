package nonlivingThings.relatedCard.cardList.attackCard.normal;

import livingThings.fighter.Protagonist;
import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//분노
//0코스트, 피해를 6 줍니다. 이 카드를 1장 복사해 버린 카드 더미에 섞어 넣습니다.
//복사된 카드는 전투 종료 후 남아있지 않음
public class Anger extends Card {
	public Anger() {
		cost = 0;
		damage = 6;
		canUseEnemy = true;
		hasSkill = true;
		kind = CardKind.ATTACK;
	}
	
	@Override
	public void skill(Protagonist player) {
		player.getTrashCan().addCard(new Anger());
		player.getTmpCardBag().add(new Anger());
		player.setTmpCardCount(player.getTmpCardCount() + 1);
	}
}