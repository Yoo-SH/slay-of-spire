package livingThings.fighter.enemyList;

import livingThings.fighter.*;
import nonlivingThings.relatedCard.cardList.Trash;


//스켈레톤 병사
public class Skeleton1 extends Enemy {
	public Skeleton1() {
		hp = maxHp = 20;
		attackPower = 7;
	}
	
	@Override
	public void attack(Protagonist player) {
		int attackKind = (int) (Math.random()*4);
		
		if(attackKind < 3) {		//75%확률로 attack1	
			attack1(player);
		}
		else {
			attack2(player);
		}
	}
	
	//휘두르기 공격 -> 특수능력X
	private void attack1(Protagonist player) {
		int motionKind = (int) (Math.random()*2);
		
		if(motionKind == 0) {		//휘두르기 모션1
			
		}
		else {		//휘두르기 모션2
			
		}
		
		player.hit(this);
	}
	
	//칼 던지기 공격 -> 공격 시 쓰레기 카드 추가
	private void attack2(Protagonist player) {
		//칼 던지기 모션
		
		//player tmpCardBag에 쓰레기 카드 추가
		player.getTrashCan().addCard(new Trash());
		player.getTmpCardBag().add(new Trash());
		player.setTmpCardCount(player.getTmpCardCount() + 1);
	}
}
