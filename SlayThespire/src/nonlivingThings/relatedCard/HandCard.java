package nonlivingThings.relatedCard;

import java.util.LinkedList;

//손에 있는 카드
//전투 종료 시 모든 카드들을 가방으로 이동
//전투 시 턴마다 5장씩 줌 / 단, 쓰지 않은 카드들은 다 쓰레기통으로 이동
public class HandCard {
	private  LinkedList<Card> list = new LinkedList<Card>();		//손에 들 수 있는 카드의 최대 개수는 10장
	private  int count;
	
	//쓰레기통으로 보냄 - 턴 종료 시 남은 카드 개수만큼 호출
	public Card goTrashCan(int i) {
		count--;
		return list.remove(i);
	}
	
	//손에 있는 카드 출력
	public void displayHand() {
		for(int i = 0; i < count; i++) {
			System.out.print(list.get(i).getClass().getSimpleName() + " ");
		}
		
		System.out.println();
	}
	
	//getter, setter
	public Card getCard(int i) {
		return list.get(i);
	}
	public void setCard(Card card) {
		if(count > 10) {
			System.out.println("On hand, Card count is over 10.");
			return;
		}
		list.add(card);
		count++;
	}

	public int getCount() {
		return count;
	}
}