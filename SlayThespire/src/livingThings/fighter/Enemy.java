package livingThings.fighter;

import java.util.LinkedList;

//몬스터
public class Enemy extends Fighter {
//	private LinkedList<Integer> weakDuration;		//취약 받는 데미지 50% 증가
//	private LinkedList<Integer> weakeningDuration;	//약화 공격력 25% 감소
	private int weakDuration;
	private int weakeningDuration;
//	private int weakCount;
//	private int weakeningCount;
	
	public Enemy() {
//		weakDuration = new LinkedList<Integer>();
//		weakeningDuration = new LinkedList<Integer>();
		weakDuration = 0;
		weakeningDuration = 0;
//		weakCount = 0;
//		weakeningCount = 0;
	}
	
	//오버라이딩용
	public void attack(Protagonist player) {}
	
	//죽었는 지 확인
	public boolean isDie() {
		return hp <= 0 ? true : false;
	}
	
	//전투 중 weak 추가
	public void addWeak(int weakDuration) {
		this.weakDuration += weakDuration;
//		weakCount++;
	}

	//전투 중 weakening 추가
	public void addWeakening(int weakeningDuration) {
		this.weakeningDuration += weakeningDuration;
//		weakeningCount++;
	}
	
	//전투 중 weak 시간 줄이기, 끝나면 해제
	public void decreaseWeak() {
//		for(int i = weakCount - 1; i != -1; i--) {
//			//버프 지속시간을 1씩 줄임
//			weakDuration.set(i, weakDuration.get(i) - 1);
//			
//			//버프 지속시간이 0이 되면 버프 제거
//			if(weakDuration.get(i) == 0) {
//				weakDuration.remove(i);
//				weakCount--;
//			}
//		}
		weakDuration--;
	}
	
	//전투 중 weakening 시간 줄이기, 끝나면 해제
	public void decreaseWeakening() {
//		for(int i = weakeningCount - 1; i != -1; i--) {
//			//버프 지속시간을 1씩 줄임
//			weakeningDuration.set(i, weakeningDuration.get(i) - 1);
//			
//			//버프 지속시간이 0이 되면 버프 제거
//			if(weakeningDuration.get(i) == 0) {
//				weakeningDuration.remove(i);
//				weakeningCount--;
//			}
//		}
		weakeningDuration--;
	}
	
	//getter, setter
	public int getWeakDuration() {
//		int sum = 0;
//		
//		for(int i = 0; i < weakCount; i++) {
//			sum += weakDuration.get(i);
//		}
//		
//		return sum;
		return weakDuration;
	}
	
	public int getWeakeningDuration() {
//		int sum = 0;
//		
//		for(int i = 0; i < weakeningCount; i++) {
//			sum += weakeningDuration.get(i);
//		}
//		
//		return sum;
		return weakDuration;
	}
}