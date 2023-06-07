package nonlivingThings.relatedCard;

import livingThings.fighter.*;

public class Card {
	protected int cost;
	protected int damage;
	protected int additionDamage;
	protected int additionShield;
	
	//additionWeak과 additionWeakening은 지속되는 시간을 저장 
	protected int additionWeak;			//취약
	protected int additionWeakening;	//약화
	protected boolean canUseEnemy;		//damage가 있는 경우에만 true
	protected boolean canUseAll;		//모든 적을 공격하는 지 확인
	protected boolean hasSkill;			//특수 능력을 가지고 있는 지 확인
	
	//ATTACK or SKILL
	protected CardKind kind;

	public Card(){
		cost = 0;
		damage = 0;
		
		additionDamage = 0;
		additionShield = 0;
		additionWeak = 0;
		additionWeakening = 0;
		
		canUseEnemy = false;
		canUseAll = false;
		hasSkill = false;
	}

	public void attack(Enemy monster, Protagonist player) {
		monster.hit(player, this);
		skill(player);
	}
	
	//오버라이딩용
	public void skill(Protagonist player) {}
	
	public boolean isCanUseEnemy() {
		return canUseEnemy;
	}
	
	public boolean isCanUseAll() {
		return canUseAll;
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

	public boolean isHasSkill() {
		return hasSkill;
	}
}