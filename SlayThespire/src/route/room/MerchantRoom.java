package route.room;

import livingThings.fighter.Protagonist;
import nonlivingThings.relatedCard.Card;
import nonlivingThings.relatedCard.cardList.attackCard.normal.Anger;
import nonlivingThings.relatedCard.cardList.attackCard.normal.Bash;
import nonlivingThings.relatedCard.cardList.attackCard.normal.BodySlam;
import nonlivingThings.relatedCard.cardList.attackCard.normal.Clash;
import nonlivingThings.relatedCard.cardList.attackCard.normal.Cleave;
import nonlivingThings.relatedCard.cardList.attackCard.normal.Clothesline;
import nonlivingThings.relatedCard.cardList.attackCard.normal.HeavyBlade;
import nonlivingThings.relatedCard.cardList.attackCard.normal.IronWave;
import nonlivingThings.relatedCard.cardList.attackCard.normal.Strike;
import nonlivingThings.relatedCard.cardList.attackCard.normal.Thunderclap;
import nonlivingThings.relatedCard.cardList.attackCard.rare.Bludgeon;
import nonlivingThings.relatedCard.cardList.attackCard.special.Carnage;
import nonlivingThings.relatedCard.cardList.attackCard.special.Hemokinesis;
import nonlivingThings.relatedCard.cardList.attackCard.special.Uppercut;
import nonlivingThings.relatedCard.cardList.skillCard.normal.Defend;
import nonlivingThings.relatedCard.cardList.skillCard.normal.Flex;
import nonlivingThings.relatedCard.cardList.skillCard.rare.Impervious;
import nonlivingThings.relatedCard.cardList.skillCard.rare.LimitBreak;
import nonlivingThings.relatedCard.cardList.skillCard.special.Bloodletting;
import nonlivingThings.relatedCard.cardList.skillCard.special.Intimidate;

//공격카드 2개, 스킬카드 1개, 카드 제거
public class MerchantRoom {
	//물품 개수
	final int LIST_COUNT = 3;
	
	//물품 목록
	//0, 1번째 인덱스는 공격카드
	Card[] list = new Card[LIST_COUNT];
	int[] price = new int[LIST_COUNT];
	
	static int eliminationPrice = 75;		//카드 제거 비용 초기값 75 -> 횟수를 거듭할수록 25골드씩 증가
	
	public MerchantRoom() {
		//일반카드 50%, 특별카드 35%, 희귀카드 15%
		int attackCardTier = (int) (Math.random() * 100);
		int skillCardTier = (int) (Math.random() * 100);
		
		//공격카드
		for(int i = 0; i < 2; i++) {
			if(attackCardTier < 50) {		//일반카드인 경우
				int randomCard = (int) (Math.random() * 10);
				int deviation = (int) (Math.random() * 11);		//가격 편차치
				
				if(randomCard == 0) {
					list[i] = new Anger(); 
				}
				else if(randomCard == 1) {
					list[i] = new Bash();
				}
				else if(randomCard == 2) {
					list[i] = new BodySlam();
				}
				else if(randomCard == 3) {
					list[i] = new Clash();
				}
				else if(randomCard == 4) {
					list[i] = new Cleave();
				}
				else if(randomCard == 5) {
					list[i] = new Clothesline();
				}
				else if(randomCard == 6) {
					list[i] = new HeavyBlade();
				}
				else if(randomCard == 7) {
					list[i] = new IronWave();
				}
				else if(randomCard == 8) {
					list[i] = new Strike();
				}
				else if(randomCard == 9) {
					list[i] = new Thunderclap();
				}
				
				price[i] = 45 + deviation;
			}
			else if(attackCardTier < 85) {		//특별카드인 경우
				int randomCard = (int) (Math.random() * 3);
				int deviation = (int) (Math.random() * 15);		//가격 편차치

				if(randomCard == 0) {
					list[i] = new Carnage(); 
				}
				else if(randomCard == 1) {
					list[i] = new Hemokinesis();
				}
				else if(randomCard == 2) {
					list[i] = new Uppercut();
				}
				
				price[i] = 68 + deviation;
			}
			else {
				int deviation = (int) (Math.random() * 31);
				
				list[i] = new Bludgeon();
				
				price[i] = 135 + deviation;
			}
		}
		
		if(skillCardTier < 50) {		//일반카드인 경우
			int randomCard = (int) (Math.random() * 2);
			int deviation = (int) (Math.random() * 11);
			
			if(randomCard == 0) {
				list[2] = new Defend();
			}
			else if(randomCard == 1) {
				list[2] = new Flex();
			}
			
			price[2] = 45 + deviation;
		}
		else if(skillCardTier < 85) {		//특별카드인 경우
			int randomCard = (int) (Math.random() * 2);
			int deviation = (int) (Math.random() * 15);
			
			if(randomCard == 0) {
				list[2] = new Bloodletting();
			}
			else if(randomCard == 1) {
				list[2] = new Intimidate();
			}
			
			price[2] = 68 + deviation; 
		}
		else {		//희귀카드인 경우
			int randomCard = (int) (Math.random() * 2);
			int deviation = (int) (Math.random() * 31);
			
			if(randomCard == 0) {
				list[2] = new Impervious();
			}
			else if(randomCard == 1) {
				list[2] = new LimitBreak();
			}
			
			price[2] = 135 + deviation;
		}
		
		printList();
	}
	
	//목록 출력
	private void printList() {
		for(int i = 0; i < LIST_COUNT; i++) {
			if(list[i] == null) {
				System.out.println("Sold out");
				continue;
			}
			System.out.println(list[i].getClass().getSimpleName() + " " + price[i]);
		}
	}
	
	//카드를 누르면 호출
	public void sell(Protagonist player, int pos) {
		if(player.getGold() >= price[pos]) {
			player.setGold(player.getGold() - price[pos]);
			player.gainCard(list[pos]);
			list[pos] = null;
		}
		else {
			System.out.println("You don't have money enough to buy it.");
		}
	}
	
	//카드제거 버튼 누르면 카드를 선택하게 한 후 호출
	public void eliminateCard(Protagonist player, Card card) {
		//보유카드 출력
		for(int i = 0; i < player.getBag().getListCount(); i++) {
			System.out.println(player.getBag().getList(i).getClass().getSimpleName());
		}
		
		if(player.getGold() >= eliminationPrice) {
			player.setGold(player.getGold() - eliminationPrice);
			player.eliminateCard(card);
		}
		else {
			System.out.println("You don't have money enough to buy it.");
		}
	}
}