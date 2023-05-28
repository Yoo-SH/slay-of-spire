import route.*;

import livingThings.fighter.*;
import livingThings.fighter.enemyList.*;

import nonlivingThings.relatedCard.*;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Route a = new Route();
		Protagonist player = new Protagonist();
		TestMonster monster = new TestMonster(); 
		
		player.getBag().displayCardList();
		
		System.out.println("Monster's Hp : " + monster.getHp());
		
		Enemy[] monsterList = { monster };
		fight(player, monsterList);
	}
	
	public static void playerTurn(Protagonist player, Enemy[] monsterList) {
		//버프 지속시간 1씩 줄임
		//주인공의 버프가 끝났으면 버프 종료
		player.decreaseBuffDamage();
		player.decreaseDeBuffDamage();
		
		//플레이어에게 카드 분배
		for(int i = 0; i < 5; i++) {
			if(player.getBag().getBagCount() == 0) {		//카드를 꺼내던 중 가방에 있는 카드를 다 꺼낸 경우
				player.goBag();
			}
			
			player.goHand();
		}
		
		//에너지3획득
		player.setEnergy(3);
		System.out.println("Energy : " + player.getEnergy());
		
		//턴 종료 버튼을 누른 경우 반복문 종료
		while(true) {
			int cardIndex = player.selectCard();
			
			if(player.getHand().getCard(cardIndex).isCanUseEnemy()) {		//적에게 사용할 수 있는 경우 적을 선택 후 카드 사용
				if(player.getHand().getCard(cardIndex).isCanUseAll()) {		//전체 공격
					//적을 클릭하면 카드 사용
					for(int i = 0; i < monsterList.length; i++) {
						player.useCard(cardIndex, monsterList[i]);
					}
				}
				else {		//단일 공격
					int targetIndex = player.selectEnemy(monsterList);
					player.useCard(cardIndex, monsterList[targetIndex]);
				}
			}
			else {		//적에게 사용할 수 없는 경우
				player.useCard(cardIndex);
			}
			
			//공격 후 몬스터가 전부 죽으면
			if(isMonsterAllDie(monsterList)) {
				break;
			}
			
			//if(턴 종료 버튼을 누르면)
			System.out.println("Do you want to finish your turn? (Y/N)");
			
			Scanner sc = new Scanner(System.in);
			char command = sc.next().charAt(0);;
			
			if(command == 'Y') {
				break;
			}
		}
		
		//손에 남은 모든 카드를 쓰레기통으로 보냄
		for(; player.getHand().getCount() > 0;) {
			player.goTrashCan(0);
		}
	}
	
	//몬스터 차례
	public static void monsterTurn(Protagonist player, Enemy monster) {
		monster.decreaseWeak();
		monster.decreaseWeakening();
		monster.attack(player);
	}
	
	//전투
	public static void fight(Protagonist player, Enemy[] monsterList) {
		while(!player.isDie() && !isMonsterAllDie(monsterList)) {
			//플레이어 턴 호출
			playerTurn(player, monsterList);
			//몬스터 턴 호출
			for(int i = 0; i < monsterList.length; i++) {
				if(!monsterList[i].isDie()) {
					monsterTurn(player, monsterList[i]);
				}
			}
		}
		
		//전투 종료
		//쓰레기통에 있는 카드를 가방으로
		//임시 카드들 삭제
		//플레이어에게 적용된 버프 삭제
		player.goBag();
		player.eliminateTmpCard();
		player.resetBuff();
		//보상 선택 창
	}
	
	//몬스터가 전부 죽었는 지 확인
	public static boolean isMonsterAllDie(Enemy[] monsterList) {
		if(monsterList.length == 1) {
			if(monsterList[0].isDie()) {
				return true;
			}
		}
		else if(monsterList.length == 2){
			if(monsterList[0].isDie() && monsterList[1].isDie()) {
				return true;
			}
		}
		else if(monsterList.length == 3) {
			if(monsterList[0].isDie() && monsterList[1].isDie() && monsterList[2].isDie()) {
				return true;
			}
		}
		
		return false;
	}
}