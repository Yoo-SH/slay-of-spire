package nonlivingThings.relatedCard;

import java.util.LinkedList;

//플레이어가 가지고 있는 모든 카드
public class CardBag {
	private  LinkedList<Card> list;
	private  LinkedList<Card> bag;		//list는 플레이어가 가지고 있는 카드, bag은 전투 중 사용하는 카드
	private  int listCount;
	private  int bagCount;
	
	public CardBag() {
		list = new LinkedList<Card>();
		bag = new LinkedList<Card>();
		listCount = 0;
		bagCount = 0;
	}
	
	//새로운 카드 획득
	public void gainCard(Card card) {
		list.add(card);
		bag.add(card);
		listCount++;
		bagCount++;
	}
	
	//카드 제거 - remove(index)는 index위치의 카드를 지움, remove(object)는 object를 지움
	public void eliminateCard(int i) {
		list.remove(i);
		bag.remove(i);
		listCount--;
		bagCount--;
	}
	
	//임시 카드 삭제
	public void eliminateTmpCard(Card card) {
		bag.remove(card);
		bagCount--;
	}
	
	//bag에 카드 추가 - 전투 중
	public void addCard(Card card) {
		bag.add(card);
		bagCount++;
	}
	
	public void display() {
		for(int i = 0; i < bagCount; i++) {
			System.out.print(bag.get(i).getClass().getSimpleName() + " ");
		}
		System.out.println();
	}
	
	//bag에 카드 삭제 - 전투 중
	public Card deleteCard(int pos) {
		bagCount--;
		return bag.remove(pos);
	}
	
	//가지고 있는 카드 출력
	public void displayCardList() {
		for(int i = 0; i < listCount; i++) {
			System.out.print(list.get(i).getClass().getSimpleName() + " ");
		}
		System.out.println();
	}
	
	//가방에 있는 카드 출력
	public void displayBag() {
		for(int i = 0; i < bagCount; i++) {
			System.out.print(bag.get(i).getClass().getSimpleName() + " ");
		}
		System.out.println();
	}
	
	//getter, setter
	public Card getList(int i) {
		return list.get(i);
	}
	public int getListCount() {
		return listCount;
	}
	public int getBagCount() {
		return bagCount;
	}
}