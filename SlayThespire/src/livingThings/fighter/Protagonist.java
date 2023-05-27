package livingThings.fighter;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardBag;
import nonlivingThings.relatedCard.CardTrashCan;
import nonlivingThings.relatedCard.HandCard;
import nonlivingThings.relatedCard.cardList.attackCard.normal.*;
import nonlivingThings.relatedRelics.*;

import java.util.LinkedList;

//플레이어
public class Protagonist extends Fighter {
	private LinkedList<Integer> buffDamage;		//데미지 증가
	private LinkedList<Integer> buffDamageDuration;
	private int	buffDamageCount;
	private LinkedList<Integer> deBuffDamage;	//데미지 감소
	private LinkedList<Integer> deBuffDamageDuration;
	private int	deBuffDamageCount;
	
	private int shield;		//shield는 지속시간 및 적용 개수 필요없음
	private int energy;
	
	private CardBag cardBag;
	private CardTrashCan trashCan;
	private HandCard hand;
	private RelicsBag relicsBag;
	
	//임시 카드들을 보관하는 가방 -> 전투 종료 후 제거
	//ex) Anger(분노) 카드는 복사해서 쓰레기통에 넣어 놓음
	private LinkedList<Card> tmpCardBag;
	private int tmpCardCount;
	
	public Protagonist() {
		hp = maxHp = 80;
		
		buffDamage = new LinkedList<Integer>();
		buffDamageDuration = new LinkedList<Integer>();
		buffDamageCount = 0;
		
		deBuffDamage = new LinkedList<Integer>();
		deBuffDamageDuration = new LinkedList<Integer>();
		deBuffDamageCount = 0;
		
		shield = 0;
		energy = 0;
		
		cardBag = new CardBag();
		trashCan = new CardTrashCan();
		hand = new HandCard();
		relicsBag = new RelicsBag();
		
		tmpCardBag = new LinkedList<Card>();
		tmpCardCount = 0;
		
		for(int i = 0; i < 10; i++) {
			gainCard(new Strike());
		}
	}
	
	//죽었는 지 확인
	public boolean isDie() {
		return hp <= 0 ? true : false;
	}
	
	//카드 사용
	public void useCard(int pos, Enemy target) {	//pos는 카드 인덱스 target도 인덱스로 해야 될 것 같은데 -> 주소로 넘겨주니까 상관 없음
		if(hand.getCard(pos).isCanUseEnemy()) {		//적에게 사용가능한 경우
			if(hand.getCard(pos).getCost() > energy) {
				System.out.println("Energy shortage");
				return;
			}
			else {
				hand.getCard(pos).attack(target, this);
				energy -= hand.getCard(pos).getCost();
			}
		}
		
		//카드에 버프 및 디버프들이 존재하는 경우
		if(hand.getCard(pos).getAdditionDamage() != 0) {
			buffDamage.add(hand.getCard(pos).getAdditionDamage());
			buffDamageCount++;
		}
		if(hand.getCard(pos).getAdditionShield() != 0) {
			shield += hand.getCard(pos).getAdditionShield();
		}
		if(hand.getCard(pos).getAdditionWeak() != 0) {
			target.addWeak(hand.getCard(pos).getAdditionWeak());
		}
		if(hand.getCard(pos).getAdditionWeakening() != 0) {
			target.addWeakening(hand.getCard(pos).getAdditionWeakening());
		}
		
		trashCan.addCard(hand.deleteCard(pos));
	}
	
	
	
	//카드 획득
	public void gainCard(Card card) {
		cardBag.gainCard(card);
	}
	
	//카드 제거
	public void eliminateCard(int i) {
		cardBag.eliminateCard(i);
	}
	
	//임시 카드 제거
	public void eliminateTmpCard() {
		for(; tmpCardCount != 0; tmpCardCount--) {
			cardBag.eliminateTmpCard(tmpCardBag.get(tmpCardCount - 1));
			tmpCardBag.remove(tmpCardCount - 1);
		}
	}
	
	//카드를 가방에서 손으로 보냄 - 턴 시작 시 5번 호출 -> 유물 있으면 더 많이 호출
	public void goHand() {
		int i = (int) ((Math.random() * cardBag.getBagCount()));
		hand.addCard(cardBag.deleteCard(i));
	}
	
	//카드를 손에서 쓰레기통으로 보냄 - 턴 종료 시 남은 카드 개수만큼 호출
	public void goTrashCan(int pos) {
		trashCan.addCard(hand.deleteCard(pos));
	}

	//카드를 쓰레기통에서 가방으로 보냄
	public void goBag() {
		for(int i = trashCan.getCount(); i > 0; i--) {
			cardBag.addCard(trashCan.deleteCard());
		}
	}
	
	//버프 초기화 -> 전투 종료 시 호출
	public void resetBuff() {
		for(; buffDamageCount-- != 0; ) {
			buffDamage.remove();
			buffDamageDuration.remove();
		}
		for(; deBuffDamageCount-- != 0; ) {
			deBuffDamage.remove();
			deBuffDamageDuration.remove();
		}
		shield = 0;
	}
	
	//전투 중 버프 획득
	public void addDeBuffDamage(int deBuffDamage) {
		this.deBuffDamage.add(deBuffDamage);
		deBuffDamageCount++;
	}
	//전투 중 버프 제거
	public void removeDeBuffDamage(int i) {
		this.deBuffDamage.remove(i);
	}
	
	//전투 중 버프 시간 줄이기, 끝나면 해제
	public void decreaseBuffDamage() {
		for(int i = buffDamageCount - 1; i != -1; i--) {
			//버프 지속시간을 1씩 줄임
			buffDamageDuration.set(i, buffDamageDuration.get(i) - 1);
			
			//버프 지속시간이 0이 되면 버프 제거
			if(buffDamageDuration.get(i) == 0) {
				buffDamageDuration.remove(i);
				buffDamage.remove(i);
				buffDamageCount--;
			}
		}
	}
	
	//전투 중 디버프 시간 줄이기, 끝나면 해제
	public void decreaseDeBuffDamage() {
		for(int i = deBuffDamageCount - 1; i != -1; i--) {
			//디버프 지속시간을 1씩 줄임
			deBuffDamageDuration.set(i,  deBuffDamageDuration.get(i) - 1);
			
			//디버프 지속시간이 0이 되면 버프 제거
			if(deBuffDamageDuration.get(i) == 0) {
				deBuffDamageDuration.remove(i);
				deBuffDamage.remove(i);
				deBuffDamageCount--;
			}
		}
	}
	
	//분노(Anger)카드를 사용하면 Anger클래스의 attack메소드에서 호출
	public void useAnger() {
		tmpCardBag.add(new Anger());
		tmpCardCount++;
		trashCan.addCard(tmpCardBag.get(tmpCardCount - 1));
	}
	
	//getter, setter
	public int getShield() {
		return shield;
	}
	
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy += energy;
	}
	
	//------------------버프------------------
	public int getBuffDamage() {
		int sum = 0;
		
		for(int i = 0; i < buffDamageCount; i++) {
			sum += buffDamage.get(i);
		}
		
		return sum;
	}
	
	public int getBuffDamageCount() {
		return buffDamageCount;
	}
	
	//------------------디버프------------------
	public int getDeBuffDamage() {
		int sum = 0;
		
		for(int i = 0; i < deBuffDamageCount; i++) {
			sum += deBuffDamage.get(i);
		}
		
		return sum;
	}
	
	public void setDeBuffDamageDuration(int deBuffDamageDuration) {
		this.deBuffDamageDuration.add(deBuffDamageDuration);
	}

	public int getDeBuffDamageCount() {
		return deBuffDamageCount;
	}
	public void setDeBuffDamageCount(int deBuffDamageCount) {
		this.deBuffDamageCount = deBuffDamageCount;
	}
	
	//------------------객체------------------
	public CardBag getBag() {
		return cardBag;
	}

	public CardTrashCan getTrashCan() {
		return trashCan;
	}

	public HandCard getHand() {
		return hand;
	}
}
