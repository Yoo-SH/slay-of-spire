package livingThings.fighter.enemyList;

import livingThings.fighter.*;
import nonlivingThings.relatedCard.cardList.Trash;

public class EarthWorm extends  Enemy {
	public EarthWorm() {
		hp = maxHp = 25;
		attackPower = 7;
	}
	
	@Override
	public void attack(Protagonist player) {
		//공격 모션
		
		//player tmpCardBag에 쓰레기 카드 추가
		player.getTrashCan().addCard(new Trash());
		player.getTmpCardBag().add(new Trash());
		player.setTmpCardCount(player.getTmpCardCount() + 1);
		
		player.hit(this);
	}
}