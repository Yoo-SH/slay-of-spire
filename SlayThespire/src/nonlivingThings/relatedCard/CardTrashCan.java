package nonlivingThings.relatedCard;

import java.util.LinkedList;

public class CardTrashCan {
	private static LinkedList<Card> list = new LinkedList<Card>();		//크기는 카드 가방의 bagCount
	private static int count;
	
	public CardTrashCan() {
		count = 0;
	}
	
	//가방으로 보냄 - 카드를 꺼낼 때 랜덤으로 꺼내기 때문에 여기서 섞어서 보낼 필요X
	public static Card goBag() {
		count--;
		return list.remove();
	}
	
	//쓰레기 통에 있는 카드 출력
	public void displayTrashCan() {
		for(int i = 0; i < count; i++) {
			System.out.print(list.get(i).getClass().getSimpleName() + " ");
		}
		
		System.out.println();
	}
	
	//getter, setter
	public void setCard(Card card) {
		list.add(card);
		count++;
	}
}
