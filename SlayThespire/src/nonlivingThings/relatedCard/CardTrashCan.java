package nonlivingThings.relatedCard;

import java.util.LinkedList;

public class CardTrashCan {
	private LinkedList<Card> list;		//크기는 카드 가방의 bagCount
	private int count;
	
	public CardTrashCan() {
		list = new LinkedList<Card>();
		count = 0;
	}
	
	//쓰레기 통에 있는 카드 출력
	public void displayTrashCan() {
		for(int i = 0; i < count; i++) {
			System.out.print(list.get(i).getClass().getSimpleName() + " ");
		}
		
		System.out.println();
	}
	
	//카드 추가
	public void addCard(Card card) {
		list.add(card);
		count++;
	}
	//카드 삭제
	public Card deleteCard() {
		count--;
		return list.remove();
	}
	
	//getter, setter
	public LinkedList<Card> getList() {
		return list;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
