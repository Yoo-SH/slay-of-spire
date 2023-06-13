package nonlivingThings.relatedCard;

import java.util.LinkedList;

//손에 있는 카드
//전투 종료 시 모든 카드들을 가방으로 이동
//전투 시 턴마다 5장씩 줌 / 단, 쓰지 않은 카드들은 다 쓰레기통으로 이동
public class HandCard {
	private  LinkedList<Card> list;
	private  int count;
	public TestPanel p = new TestPanel();
	
	public HandCard() {
		list = new LinkedList<Card>();
		count = 0;
	}
	
	//손에 있는 카드 출력
	public void displayHand() {
		for(int i = 0; i < count; i++) {
			System.out.print(list.get(i).getClass().getSimpleName() + " ");
		}
		
		System.out.println();
	}
	
	//카드 추가
	public void addCard(Card card) {
		if(count > 10) {
			System.out.println("On hand, Card count is over 10.");
			return;
		}
		list.add(card);
		count++;
	}
	
	//카드 삭제
	public Card deleteCard(int pos) {
		count--;
		return list.remove(pos);
	}
	
	//getter, setter
	public Card getCard(int i) {
		return list.get(i);
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}