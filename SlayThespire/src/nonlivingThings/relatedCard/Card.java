package nonlivingThings.relatedCard;

import livingThings.fighter.*;

public class Card {
	protected int cost;
	protected int damage;
	protected int additionDamage;
	protected int additionShield;
	//additionWeak과 additionWeakening에 지속되는 시간을 저장 
	protected int additionWeak;
	protected int additionWeakening;
//	protected int duration;		-> 필요 없음 additionDamage는 전투 중 계속 유지 됨
	protected boolean canUseEnemy;		//damage가 있는 경우에만 true
	
	//ATTACK or SKILL
	protected CardKind kind;

	public Card(){
		cost = 0;
		damage = 0;
		
		additionDamage = 0;
		additionShield = 0;
		additionWeak = 0;
		additionWeakening = 0;
		
//		duration = 0;
		canUseEnemy = false;
	}
	
	public void attack(Enemy monster, Protagonist player) {
		if(monster.getHp() - (damage + player.getBuffDamage()) <= 0) {	//몬스터 체력이 0보다 작거나 같은 경우 체력을 0으로 만듬
			monster.setHp(0);
		}
		else {
			monster.setHp(monster.getHp() - (damage + player.getBuffDamage()));
		}
	}

	public boolean isCanUseEnemy() {
		return canUseEnemy;
	}
	
	//getter, setter
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDamage() {
		return damage;
	}

	public int getAdditionDamage() {
		return additionDamage;
	}

	public int getAdditionShield() {
		return additionShield;
	}
	
	public int getAdditionWeak() {
		return additionWeak;
	}
	
	public int getAdditionWeakening() {
		return additionWeakening;
	}
	
	public CardKind getKind() {
		return kind;
	}
	
//	public int getDuration() {
//		return duration;
//	}
//	public void setDuration(int duration) {
//		this.duration = duration;
//	}
}