//import route.*;
import livingThings.fighter.*;
import livingThings.fighter.enemyList.*;
import java.util.Scanner;
public class Main {
	public static void main(String args[]) {
		route.Route a = new route.Route();
		Protagonist player = new Protagonist();
		testMonster monster = new testMonster(); 
		
		player.getBag().displayCardList();
		
		System.out.println("Monster's Hp : " + monster.getHp());
		playerTurn(player, monster);
		System.out.println("Monster's Hp : " + monster.getHp());

		System.out.print("In trashcan: ");
		player.getTrashCan().displayTrashCan();
	}
	
	//플레이어 턴 시작 - 몬스터가 여러마리인 경우 어떻게 하지 오버로딩 해야 되나 귀찮은데 -> 아니 왜 디폴트 매개변수 지원 안 하냐고;;
	public static void playerTurn(Protagonist player, Enemy monster1) {
		//주인공의 버프가 끝났으면 버프 종료
		//if()
		
		//플레이어에게 카드 분배
		for(int i = 0; i < 5; i++) {
			if(player.getBag().getBagCount() < 0) {		//카드를 꺼내던 중 가방에 있는 카드를 다 꺼낸 경우
				break;
			}
			
			player.getHand().setCard(player.getBag().goHand());
		}
		
		//에너지3획득
		player.setEnergy(3);
		System.out.println("Energy : " + player.getEnergy());
		
		//턴 종료 버튼을 누른 경우 반복문 종료
		while(true) {
			System.out.println("Choose the Card");
			System.out.print("On hand : ");
			player.getHand().displayHand();
			Scanner sc = new Scanner(System.in);
			player.useCard(sc.nextInt(), monster1);
			System.out.print("On hand : ");
			player.getHand().displayHand();
			
			for(int i = player.getHand().getCount() - 1; i >= 0 ; i--) {
				player.getTrashCan().setCard(player.getHand().goTrashCan(player.getHand().getCount() - 1));
			}
			break;
		}
		
		System.out.println("Energy : " + player.getEnergy());
	}
	
	public void playerTurn(Protagonist player, Enemy monster1, Enemy monster2) {
		//주인공의 버프가 끝났으면 버프 종료
		//if()
		
		//플레이어에게 카드 분배
		for(int i = 0; i < 5; i++) {
			if(player.getBag().getBagCount() < 0) {		//카드를 꺼내던 중 가방에 있는 카드를 다 꺼낸 경우
				break;
			}
			
			player.getHand().setCard(player.getBag().goHand());
		}
		
		//턴 종료 버튼을 누른 경우 반복문 종료
		while(true) {
			
		}
	}
	
	public void playerTurn(Protagonist player, Enemy monster1, Enemy monster2, Enemy monster3) {
		//주인공의 버프가 끝났으면 버프 종료
		//if()
		
		//플레이어에게 카드 분배
		for(int i = 0; i < 5; i++) {
			if(player.getBag().getBagCount() < 0) {		//카드를 꺼내던 중 가방에 있는 카드를 다 꺼낸 경우
				break;
			}
			
			player.getHand().setCard(player.getBag().goHand());
		}
		
		//턴 종료 버튼을 누른 경우 반복문 종료
		while(true) {
			
		}
	}
}