import route.*;
import livingThings.fighter.*;
import livingThings.fighter.enemyList.*;

import java.util.LinkedList;
import java.util.Scanner;
public class Main {
	public static void main(String args[]) {
		Route a = new Route();
		Protagonist player = new Protagonist();
		TestMonster monster = new TestMonster(); 
		
		player.getBag().displayCardList();
		
		System.out.println("Monster's Hp : " + monster.getHp());
		fight(player, monster);
	}
	
	//플레이어 턴 시작 - 몬스터가 여러마리인 경우 어떻게 하지 오버로딩 해야 되나 귀찮은데 -> 아니 왜 디폴트 매개변수 지원 안 하냐고;;
	public static void playerTurn(Protagonist player, Enemy monster1) {
		//버프 지속시간 1씩 줄임
		//주인공의 버프가 끝났으면 버프 종료
		player.decreaseBuffDamage();
		player.decreaseDeBuffDamage();
		
		//플레이어에게 카드 분배
		for(int i = 0; i < 5; i++) {
			if(player.getBag().getBagCount() < 0) {		//카드를 꺼내던 중 가방에 있는 카드를 다 꺼낸 경우
				player.goBag();
			}
			
			player.goHand();
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
			
			//손에 남은 모든 카드를 쓰레기통으로 보냄
			for(; player.getHand().getCount() > 0;) {
				player.goTrashCan(0);
			}
			
			System.out.println("Energy : " + player.getEnergy());
			System.out.println("Monster's Hp : " + monster1.getHp());
			System.out.print("In trashcan : ");
			player.getTrashCan().displayTrashCan();
			System.out.print("On hand : ");
			player.getHand().displayHand();
			
			break;
		}
		
		player.goBag();
	}
	
	public static void playerTurn(Protagonist player, Enemy monster1, Enemy monster2) {
		//주인공의 버프가 끝났으면 버프 종료
		player.decreaseBuffDamage();
		player.decreaseDeBuffDamage();
		
		//플레이어에게 카드 분배
		for(int i = 0; i < 5; i++) {
			if(player.getBag().getBagCount() < 0) {		//카드를 꺼내던 중 가방에 있는 카드를 다 꺼낸 경우
				player.goBag();
			}
			
			player.goHand();
		}
		
		//턴 종료 버튼을 누른 경우 반복문 종료
		while(true) {
			
		}
	}
	
	public static void playerTurn(Protagonist player, Enemy monster1, Enemy monster2, Enemy monster3) {
		//주인공의 버프가 끝났으면 버프 종료
		player.decreaseBuffDamage();
		player.decreaseDeBuffDamage();
		
		//플레이어에게 카드 분배
		for(int i = 0; i < 5; i++) {
			if(player.getBag().getBagCount() < 0) {		//카드를 꺼내던 중 가방에 있는 카드를 다 꺼낸 경우
				player.goBag();
			}
			
			player.goHand();
		}
		
		//턴 종료 버튼을 누른 경우 반복문 종료
		while(true) {
			
		}
	}
	
	public static void monsterTurn(Protagonist player, Enemy monster) {
		monster.decreaseWeak();
		monster.decreaseWeakening();
		monster.attack(player);
	}
	
	public static void fight(Protagonist player, Enemy monster1) {
		//멀티 스레드로 플레이어, 몬스터 움직임 구현
		//스레드 선언
		
		while(!player.isDie() && !monster1.isDie()) {
			//플레이어 턴 호출
			playerTurn(player, monster1);
			//몬스터 턴 호출
			monsterTurn(player, monster1);
		}
		
		//전투 종료
		//쓰레기통에 있는 카드를 가방으로
		//임시 카드들 삭제
		//플레이어에게 적용된 버프 삭제
		player.goBag();
		player.eliminateTmpCard();
		player.resetBuff();
		//보상 선택 창 -> 스레드 필요
	}
	
	public static void fight(Protagonist player, Enemy monster1, Enemy monster2) {
		//멀티 스레드로 플레이어, 몬스터 움직임 구현
		
		//플레이어 턴 호출
		playerTurn(player, monster1, monster2);
		
		//몬스터 턴 호출
		monsterTurn(player, monster1);
		monsterTurn(player, monster2);
		
		//전투 종료
		//쓰레기통에 있는 카드를 가방으로
		//임시 카드들 삭제
		//플레이어에게 적용된 버프 삭제
		player.goBag();
		player.eliminateTmpCard();
		player.resetBuff();
		//보상 선택 창 -> 스레드 필요
	}
	
	public static void fight(Protagonist player, Enemy monster1, Enemy monster2, Enemy monster3) {
		//멀티 스레드로 플레이어, 몬스터 움직임 구현
		
		//플레이어 턴 호출
		playerTurn(player, monster1, monster2, monster3);
		
		//몬스터 턴 호출
		monsterTurn(player, monster1);
		monsterTurn(player, monster2);
		monsterTurn(player, monster3);
		
		//전투 종료
		//쓰레기통에 있는 카드를 가방으로
		//임시 카드들 삭제
		//플레이어에게 적용된 버프 삭제
		player.goBag();
		player.eliminateTmpCard();
		player.resetBuff();
		//보상 선택 창 -> 스레드 필요
	}
}