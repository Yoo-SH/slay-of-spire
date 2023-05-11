package livingThings.fighter;
//전투를 하는 클래스의 부모
public class Fighter {
	protected int maxHp;
	protected int hp;
	protected int attackPower;
	
	//getter, setter
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {		//최대 체력은 바뀔 일이 없음 -> 유물 같은거 있으면 될지도?
		this.maxHp = maxHp;
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getAttackPower() {
		return attackPower;
	}
	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
}
