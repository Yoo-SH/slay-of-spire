package livingThings.fighter;

import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.CardBag;
import nonlivingThings.relatedCard.CardTrashCan;
import nonlivingThings.relatedCard.HandCard;
import nonlivingThings.relatedCard.cardList.attackCard.normal.*;
import nonlivingThings.relatedCard.cardList.skillCard.normal.*;
import nonlivingThings.relatedRelics.*;

import java.util.LinkedList;
import java.util.Scanner;

//플레이어
public class Protagonist extends Fighter {
	private int buffDamage;		//데미지 증가
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
		
		buffDamage = 0;
		
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
		
		//타격 5장, 수비 4장, 강타 1장
		for(int i = 0; i < 5; i++) {
			gainCard(new Strike());
		}
		for(int i = 0; i < 4; i++) {
			gainCard(new Defend());
		}
		gainCard(new Bash());
	}

	//죽었는 지 확인
	public boolean isDie() {
		return hp <= 0 ? true : false;
	}
	
	//카드 사용 - 적에게 사용할 수 있는 경우
	public void useCard(int pos, Enemy target) {
		if(hand.getCard(pos).getCost() > energy) {
			System.out.println("Energy shortage");
			return;
		}
		else {
			hand.getCard(pos).attack(target, this);
			energy -= hand.getCard(pos).getCost();
		}
		
		//카드에 버프가 존재하는 경우
		if(hand.getCard(pos).getAdditionDamage() != 0) {
			buffDamage += hand.getCard(pos).getAdditionDamage();
		}
		if(hand.getCard(pos).getAdditionShield() != 0) {
			shield += hand.getCard(pos).getAdditionShield();
		}
		
		//카드가 스킬을 가지고 있는 경우
		if(hand.getCard(pos).isHasSkill()) {
			hand.getCard(pos).skill(this);
		}
		
		goTrashCan(pos);
	}
	
	//카드 사용 - 적에게 사용할 수 없는 경우
	public void useCard(int pos) {
		if(hand.getCard(pos).getCost() > energy) {
			System.out.println("Energy shortage");
			return;
		}
		else {
			energy -= hand.getCard(pos).getCost();
		}
		
		//카드에 버프 및 디버프들이 존재하는 경우
		if(hand.getCard(pos).getAdditionDamage() != 0) {
			buffDamage += hand.getCard(pos).getAdditionDamage();
		}
		if(hand.getCard(pos).getAdditionShield() != 0) {
			shield += hand.getCard(pos).getAdditionShield();
		}

		//카드가 스킬을 가지고 있는 경우
		if(hand.getCard(pos).isHasSkill()) {
			hand.getCard(pos).skill(this);
		}
		
		trashCan.addCard(hand.deleteCard(pos));
	}
	
	//카드 선택
	public int selectCard() {
		System.out.println("Choose the Card");
		System.out.print("On hand : ");
		hand.displayHand();
		
		int index;
		
		Scanner sc = new Scanner(System.in);
		index = sc.nextInt();
		
		//손에 들고 있는 카드 수보다 큰 인덱스를 고르는 경우
		while(index >= hand.getCount()) {
			System.out.println("Incorrect Index. Choose the one more time.");
			
			index = sc.nextInt();
		}
		
		return index;
	}
	
	//공격당함
	public void hit(Enemy monster) {
		if(monster.getWeakeningDuration() == 0) {		//몬스터에게 약화가 없는 경우
			if(shield == 0) {
				hp -= monster.getAttackPower();
			}
			else if(shield > monster.getAttackPower()) {		//shield가 몬스터의 공격력보다 큰 경우
				shield -= monster.getAttackPower();
			}
			else {		//shield가 몬스터의 공격력보다 작은 경우
				hp -=  monster.getAttackPower() - shield;
				shield = 0;
			}
		}
		else {		//몬스터에게 약화가 있는 경우
			if(shield == 0) {
				hp -= monster.getAttackPower() * 3 / 4;
			}
			else if(shield > monster.getAttackPower() * 3 / 4) {		//shield가 몬스터의 공격력보다 큰 경우
				shield -= monster.getAttackPower() * 3 / 4;
			}
			else {		//shield가 몬스터의 공격력보다 작은 경우
				hp -=  monster.getAttackPower() * 3 / 4 - shield;
				shield = 0;
			}
		}
		
	}
	
	//공격할 몬스터 선택
	public int selectEnemy(Enemy[] monsterList) {
		System.out.println("Choose the monster");
		
		int index;
		
		Scanner sc = new Scanner(System.in);
		index = sc.nextInt();
		
		// 몬스터 수보다 큰 숫자를 고르거나 (ex. 몬스터 수는 2마리인데 3번 인덱스를 고른 경우) 선택한 인덱스의 몬스터가 죽어있는 경우
		while(index > monsterList.length - 1
			  || monsterList[index].isDie()) {
			System.out.println("Incorrect Index. Choose the one more time.");
			
			index = sc.nextInt();
		}
		
		return index;
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
		for(; tmpCardCount > 0; tmpCardCount--) {
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
		buffDamage = 0;
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
	
	public void addEnergy(int energy) {
		this.energy += energy;
	}
	
	public void addBuffDamage(int buffDamage) {
		this.buffDamage += buffDamage;
	}
	
	//getter, setter
	public int getShield() {
		return shield;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	//------------------버프------------------
	public int getBuffDamage() {
		return buffDamage;
	}
	
	//------------------디버프------------------
	public int getDebuffDamage() {
		int sum = 0;
		
		for(int i = 0; i < deBuffDamageCount; i++) {
			sum += deBuffDamage.get(i);
		}
		
		return sum;
	}
	public void setDeBuffDamage(int deBuffDamage) {
		this.deBuffDamage.add(deBuffDamage);
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
	
	public int getTmpCardCount() {
		return tmpCardCount;
	}

	public void setTmpCardCount(int tmpCardCount) {
		this.tmpCardCount = tmpCardCount;
	}

	public LinkedList<Card> getTmpCardBag() {
		return tmpCardBag;
	}
}
