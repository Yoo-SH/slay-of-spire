import route.*;

import livingThings.fighter.*;
import livingThings.fighter.enemyList.*;

import nonlivingThings.relatedCard.*;

import java.util.LinkedList;
import java.util.Scanner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;

import route.room.*;

public class Main {
	public static void main(String args[]) {
		Route a = new Route();
		Protagonist player = new Protagonist();
		Skeleton1 monster = new Skeleton1(); 
		
		player.getBag().displayCardList();
		
		System.out.println("Monster's Hp : " + monster.getHp());
		
		Enemy[] monsterList = { monster };
		fight(player, monsterList);
	}
	
	public static void playerTurn(Protagonist player, Enemy[] monsterList) {
		//버프 지속시간 1씩 줄임
		//주인공의 디버프가 끝났으면 버프 종료
		player.decreaseDeBuffDamage();
		
		//플레이어에게 카드 분배
		for(int i = 0; i < 5; i++) {
			if(player.getBag().getBagCount() == 0) {		//카드를 꺼내던 중 가방에 있는 카드를 다 꺼낸 경우
				player.goBag();
			}
			
			player.goHand();
		}
		
		//에너지3획득
		player.addEnergy(3);
		
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
			//체력을 소모하는 카드로 인해서 자살 가능
			if(player.isDie()) {
				
			}
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
		//10~20골드 획득 and 카드 선택 -> 카드는 넘기기 버튼이 존재함
		int deviation = (int) (Math.random() * 11);		//골드 편차치
		player.setGold(player.getGold() + 10 + deviation);
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
	
	//save는 chat gpt 이용하여 구현
	private void save(Protagonist player, Route route) {
		String filePath = "./";
		File saveFile = new File(filePath);
		
		if (!saveFile.exists()) {		//saveFile이 존재하는 경우
		    try {
		    	//새로운 파일 생성
		        saveFile.createNewFile();
		        System.out.println("Save file created successfully!");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		else {		//saveFile이 존재하지 않는 경우
			try {
		        FileOutputStream fileOut = new FileOutputStream(saveFile, false);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
		        // Perform operations on the file, such as writing data
		        // Close the FileOutputStream and other resources when done
				
				//player와 route에 관한 정보를 저장
				out.writeObject(player);
				out.writeObject(route);
				
				//Stream을 닫음 -> 이게 무슨 말인지는 잘 모르겠음
				out.close();
				fileOut.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
			
		    System.out.println("Save file already exists!");
		}
	}
}