package livingThings.fighter;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardBag;
import nonlivingThings.relatedCard.CardTrashCan;
import nonlivingThings.relatedCard.HandCard;
import nonlivingThings.relatedCard.cardList.*;
//플레이어
public class Protagonist extends Fighter {
	private int buffDamage;
	private int[] buffDamageDuration;
	private int	buffDamageCount;
	private int shield;
	private int[] shieldDuration;
	private int shieldCount;
	private int vampirism;
	private int[] vampirismDuration;
	private int vampirismCount;
	private int poison;
	private int[] poisonDuration;
	private int poisonCount;
	private int energy;
	private int location;		//location 자료형 뭘로 하지
	private CardBag bag;
	private CardTrashCan trashCan;
	private HandCard hand;
	
	public Protagonist() {
		hp = maxHp = 80;
		bag = new CardBag();
		trashCan = new CardTrashCan();
		hand = new HandCard();
		
		buffDamage = 0;
		buffDamageDuration = new int[10];
		buffDamageCount = 0;
		shield = 0;
		shieldDuration = new int[10];
		shieldCount = 0;
		vampirism = 0;
		vampirismDuration = new int[10];
		vampirismCount = 0;
		poison = 0;
		poisonDuration = new int[10];
		poisonCount = 0;
		energy = 0;
		
		for(int i = 0; i < 10; i++) {
			gainCard(new Strike());
		}
	}
	
	//카드 사용
	public void useCard(int pos, Enemy target) {	//pos는 카드 인덱스 target도 인덱스로 해야 될 것 같은데 -> 주소로 넘겨주니까 상관 없나
		if(hand.getCard(pos).isCanUseEnemy()) {		//적에게 사용가능한 경우
			if(hand.getCard(pos).getCost() > energy) {
				System.out.println("Energy shortage");
				return;
			}
			else {
				hand.getCard(pos).attack(target);
				energy -= hand.getCard(pos).getCost();
			}
		}
		
		if(hand.getCard(pos).getAdditionDamage() != 0) {
			buffDamage += hand.getCard(pos).getAdditionDamage();
			buffDamageDuration[buffDamageCount] = hand.getCard(pos).getDuration();
			buffDamageCount++;
		}
		if(hand.getCard(pos).getAdditionShield() != 0) {
			shield += hand.getCard(pos).getAdditionShield();
			shieldDuration[shieldCount] = hand.getCard(pos).getDuration();
			shieldCount++;
		}
		if(hand.getCard(pos).getAdditionVampirism() != 0) {
			vampirism += hand.getCard(pos).getAdditionVampirism();
			vampirismDuration[vampirismCount] = hand.getCard(pos).getDuration();
			vampirismCount++;
		}
		if(hand.getCard(pos).getAdditionPoison() != 0) {
			poison += hand.getCard(pos).getAdditionPoison();
			poisonDuration[poisonCount] = hand.getCard(pos).getDuration();
			poisonCount++;
		}
		
		trashCan.setCard(hand.goTrashCan(pos));
	}
	
	//카드 획득
	public void gainCard(Card card) {
		bag.addCard(card);
	}
	
	//카드 제거
	public void eliminateCard(int i) {
		bag.deleteCard(i);
	}
	
	//getter, setter
	//------------------버프------------------
	public int getBuffDamage() {
		return buffDamage;
	}
	public void setBuffDamage(int buffDamage) {
		this.buffDamage = buffDamage;
	}

	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	
	public int getVampirism() {
		return vampirism;
	}
	public void setVampirism(int vampirism) {
		this.vampirism = vampirism;
	}
	
	public int getPoison() {
		return poison;
	}
	public void setPoison(int poison) {
		this.poison = poison;
	}
	
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy += energy;
	}
	
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	
	//------------------객체------------------
	public CardBag getBag() {
		return bag;
	}
	public void setBag(CardBag bag) {
		this.bag = bag;
	}

	public CardTrashCan getTrashCan() {
		return trashCan;
	}
	public void setTrashCan(CardTrashCan trashCan) {
		this.trashCan = trashCan;
	}

	public HandCard getHand() {
		return hand;
	}
	public void setHand(HandCard hand) {
		this.hand = hand;
	}
	
	public int getBuffDamageCount() {
		return buffDamageCount;
	}
	public void setBuffDamageCount(int buffDamageCount) {
		this.buffDamageCount = buffDamageCount;
	}

	public int getShieldCount() {
		return shieldCount;
	}
	public void setShieldCount(int shieldCount) {
		this.shieldCount = shieldCount;
	}

	public int getVampirismCount() {
		return vampirismCount;
	}
	public void setVampirismCount(int vampirismCount) {
		this.vampirismCount = vampirismCount;
	}

	public int getPoisonCount() {
		return poisonCount;
	}
	public void setPoisonCount(int poisonCount) {
		this.poisonCount = poisonCount;
	}

	public int[] getBuffDamageDuration() {
		return buffDamageDuration;
	}

	public int[] getShieldDuration() {
		return shieldDuration;
	}

	public int[] getVampirismDuration() {
		return vampirismDuration;
	}

	public int[] getPoisonDuration() {
		return poisonDuration;
	}

}
