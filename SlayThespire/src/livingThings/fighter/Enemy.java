package livingThings.fighter;

import nonlivingThings.relatedCard.*;

//몬스터
public class Enemy extends Fighter {
	protected int weakDuration;		//취약 받는 데미지 50% 증가
	protected int weakeningDuration;	//약화 공격력 25% 감소
	
	public Enemy() {
		weakDuration = 0;
		weakeningDuration = 0;
	}
	
	//오버라이딩용
	public void attack(Protagonist player) {}
	
	//공격 당함
	public void hit(Protagonist player, Card card) {
		int damage = card.getDamage() + player.getBuffDamage() - player.getDebuffDamage();
		//취약이 없는 경우
		if(weakDuration <= 0) {
			if(hp - damage <= 0) {	//몬스터 체력이 0보다 작거나 같은 경우 체력을 0으로 만듬
				hp = 0;
			}
			else {
				hp -= damage;
			}
		}
		//취약이 있는 경우
		else {
			if(hp - damage * 3 / 2 <= 0) {
				hp = 0;
			}
			else {
				hp -= damage * 3 / 2;
			}
		}
		
		//카드에 디버프가 존재하는 경우
		if(card.getAdditionWeak() != 0) {
			weakDuration += card.getAdditionWeak();
		}
		if(card.getAdditionWeakening() != 0) {
			weakeningDuration += card.getAdditionWeakening();
		}
	}
	
	//죽었는 지 확인
	public boolean isDie() {
		return hp <= 0 ? true : false;
	}
	
	//전투 중 weak 추가
	public void addWeak(int weakDuration) {
		this.weakDuration += weakDuration;
	}

	//전투 중 weakening 추가
	public void addWeakening(int weakeningDuration) {
		this.weakeningDuration += weakeningDuration;
	}
	
	//전투 중 weak 시간 줄이기, 끝나면 해제
	public void decreaseWeak() {
		weakDuration--;
	}
	
	//전투 중 weakening 시간 줄이기, 끝나면 해제
	public void decreaseWeakening() {
		weakeningDuration--;
	}
	
	//getter, setter
	public int getWeakDuration() {
		return weakDuration;
	}
	
	public int getWeakeningDuration() {
		return weakDuration;
	}
}