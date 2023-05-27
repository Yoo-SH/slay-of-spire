package nonlivingThings.relatedCard.cardList.attackCard.normal;

import livingThings.fighter.Enemy;
import livingThings.fighter.Protagonist;
import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardKind;

//격돌
//0코스트, 손에 있는 카드가 전부 공격 카드일 때만 사용할 수 있습니다. 피해를 14 줍니다.
public class Clash  extends Card {
	public Clash() {
		cost = 0;
		damage = 14;
		canUseEnemy = true;
		kind = CardKind.ATTACK;
	}
	
	@Override
	public void attack(Enemy monster, Protagonist player) {
		for(int i = 0; i < player.getHand().getCount(); i++) {
			if(player.getHand().getCard(i).getKind() != CardKind.ATTACK) {
				System.out.println("Can't use this card. Please check the cards on your hand if all of card are attack card");
				return;
			}
		}
		
		if(monster.getHp() - (damage + player.getBuffDamage()) <= 0) {	//몬스터 체력이 0보다 작거나 같은 경우 체력을 0으로 만듬
			monster.setHp(0);
		}
		else {
			monster.setHp(monster.getHp() - (damage + player.getBuffDamage()));
		}
	}
}
