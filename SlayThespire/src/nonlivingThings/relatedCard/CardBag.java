package nonlivingThings.relatedCard;

import java.util.LinkedList;

//플레이어가 가지고 있는 모든 카드
public class CardBag {
	private  LinkedList<Card> list = new LinkedList<Card>();		//플레이어는 카드를 30장까지 가질 수 있음
	private  LinkedList<Card> bag = new LinkedList<Card>();		//list는 플레이어가 가지고 있는 카드, bag은 꺼냈다 넣었다 할 수 있는 카드
	private  int listCount;
	private  int bagCount;
	
	public CardBag() {
		listCount = 0;
		bagCount = 0;
	}
	
	//카드 추가
	public void addCard(Card card) {
		list.add(card);
		bag.add(card);
		listCount++;
		bagCount++;
	}
	
	//카드 삭제 - remove(index)는 index위치의 카드를 지움, remove(object)는 object를 지우는데 똑같은 카드가 있는 경우 제일 처음에 있는 카드를 지움
	public void deleteCard(int i) {
		list.remove(i);
		bag.remove(i);
		listCount--;
		bagCount--;
	}
	
	//손으로 보냄 - 턴 시작 시 5번 반복
	public Card goHand() {
		int i = (int) ((Math.random() * bagCount));
		bagCount--;
		return bag.remove(i);		//remove(index)는 제거한 객체를 리턴, remove(object)는 제거 성공하면 true를 리턴
	}
	
	//쓰레기통으로부터 받기
	public void receiveFromTrashCan() {
		for(int i = 0; i < bagCount; i++) {
			list.add(CardTrashCan.goBag());
		}
	}
	
	//가지고 있는 카드 출력
	public void displayCard() {
		for(int i = 0; i < listCount; i++) {
			System.out.print(list.get(i).getClass().getSimpleName() + " ");
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
