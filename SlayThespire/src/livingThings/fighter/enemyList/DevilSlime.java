package livingThings.fighter.enemyList;

import livingThings.fighter.*;
import nonlivingThings.relatedCard.cardList.Trash;

//중간 보스
public class DevilSlime extends Enemy {
	public DevilSlime() {
		hp = maxHp = 60;
		attackPower = 20;
	}
	
	@Override
	public void attack(Protagonist player) {
		//휘두르기 모션
		
		playSound("/sounds/DevilSlimeAttack.wav", 300);
		
		int randomDeBuff = (int) (Math.random()*5);
		
		if(randomDeBuff == 0) {		//쓰레기 카드 추가
			player.getTrashCan().addCard(new Trash());
			player.getTmpCardBag().add(new Trash());
			player.setTmpCardCount(player.getTmpCardCount() + 1);
		}
		else if(randomDeBuff == 1) {		//공격력 디버프 -> 2턴 간 3만큼
			player.setDeBuffDamage(3);
			player.setDeBuffDamageCount(player.getDeBuffDamageCount() + 1);
			player.setDeBuffDamageDuration(2);
		}
		
		player.hit(this);
	}
}