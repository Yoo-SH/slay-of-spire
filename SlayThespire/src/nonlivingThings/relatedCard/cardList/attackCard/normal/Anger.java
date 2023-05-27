package nonlivingThings.relatedCard.cardList.attackCard.normal;

import livingThings.fighter.Enemy;
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
		kind = CardKind.ATTACK;
	}
	
	@Override
	public void attack(Enemy monster, Protagonist player) {
		if(monster.getHp() - (damage + player.getBuffDamage()) <= 0) {	//몬스터 체력이 0보다 작거나 같은 경우 체력을 0으로 만듬
			monster.setHp(0);
		}
		else {
			monster.setHp(monster.getHp() - (damage + player.getBuffDamage()));
		}
		
		player.useAnger();
	}
}