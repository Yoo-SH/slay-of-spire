package nonlivingThings.relatedCard;

import livingThings.fighter.Enemy;

public class Card {
	protected int cost;
	protected int damage;
	//addition 시리즈는 두 개이상이 적용되면 duration을 같게 해야됨
	protected int additionDamage;
	protected int additionShield;
	protected int additionVampirism;
	protected int additionPoison;
	protected int duration;
	protected boolean canUseEnemy;				//damage가 있는 경우에만 true
	
	public Card(){
		cost = 0;
		damage = 0;
		additionDamage = 0;
		additionShield = 0;
		additionVampirism = 0;
		additionPoison = 0;
		duration = 0;
		canUseEnemy = false;
	}
	
	public void attack(Enemy monster) {
		monster.setHp(monster.getHp() - damage);
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
//	public void setDamage(int damage) {
//		this.damage = damage;
//	}

	public int getAdditionDamage() {
		return additionDamage;
	}
//	public void setAdditionDamage(int additionDamage) {
//		this.additionDamage = additionDamage;
//	}

	public int getAdditionShield() {
		return additionShield;
	}
//	public void setAdditionShield(int additionShield) {
//		this.additionShield = additionShield;
//	}

	public int getAdditionVampirism() {
		return additionVampirism;
	}
//	public void setAdditionVampirism(int additionVampirism) {
//		this.additionVampirism = additionVampirism;
//	}

	public int getAdditionPoison() {
		return additionPoison;
	}
//	public void setAddtionPoison(int addtionPoison) {
//		this.addtionPoison = addtionPoison;
//	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isCanUseEnemy() {
		return canUseEnemy;
	}
//	public void setCanUseEnemy(boolean canUseEnemy) {
//		this.canUseEnemy = canUseEnemy;
//	}
}