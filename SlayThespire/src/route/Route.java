package route;

import java.io.Serializable;

public class Route implements Serializable {
	final int ROW = 15;
	final int COL = 7;
	private RoomKind[][] room = new RoomKind[ROW][COL];
	private int[] roomCount = new int[ROW];
	private String[][][] link = new String[ROW - 1][COL][3];		//방과 방 사이의 길 / 각 방마다 가지고 있으며, 최대 3개
	final int ELITE_ROW = 7;		//중간 보스 8층에서 나옴
	final int BOSS_ROW = 14;		//보스는 15층에서 나옴
	
//----------method----------
	//생성자
	public Route() {
		roomCount[ELITE_ROW] = 1;
		roomCount[BOSS_ROW] = 1;
		roomCount[ELITE_ROW + 1] = 3;
		
		//1층
		for(int i = 0; i < COL; i++) {
			if(countRowRoom(0) == 6) {		//방이 6개라면 for문 탈출
				break;
			}
			
			int existence = (int) (Math.random()*2);
			
			if(existence == 1 && room[0][i] == null) {		//1일 때 방 생성, 첫 번째 방은 ENEMY
				room[0][i] = RoomKind.ENEMY;
			}
			
			if(i == 6 && countRowRoom(0) < 3) {		//for문을 다 돌았는데 방의 개수가 3보다 작으면 i를 0으로 바꿈
				i = 0;
			}
		}
		
		roomCount[0] = countRowRoom(0);
		
		//9층 -> 방이 3개만 존재(방 하나 당 링크를 3으로 해놔서 버그 예방용으로 이렇게 설정)
		//아래 층에 방이 하나 밖에 없어서 makeRoom 못 씀
		for(int i = 0; i < COL; i++) {
			if(countRowRoom(ELITE_ROW + 1) == 3) {		//방이 3개라면 for문 탈출
				break;
			}
			
			int existence = (int) (Math.random()*4);
			
			if(existence == 3 && room[ELITE_ROW + 1][i] == null)	{		//3일 때 방 생성
				link[ELITE_ROW][3][countRowRoom(ELITE_ROW + 1)] = Integer.toString(i);
				room[ELITE_ROW + 1][i] = RoomKind.ENEMY;
			}
			
			if(i == 6 && countRowRoom(ELITE_ROW + 1) < 3) {		//for문을 다 돌았는데 방의 개수가 3보다 작으면 i를 0으로 바꿈
				i = 0;
			}
		}
		
		//2층 ~ 14층 / 8층, 9층은 제외 8층은 중간 보스방, 9층은 1층과 같은 방법으로 생성
		for(int i = 1; i < BOSS_ROW; i++) {
			if(i == ELITE_ROW || i == ELITE_ROW + 1) {
				continue;
			}
			
			for(int j = 0; j < COL; j++) {
				makeRoom(i, j);
			}
			
			for(int j = 0; j < COL; j++) {
				if(room[i - 1][j] != null) {
					if(countRowRoom(i) == 6) {		//방이 6개라면 for문 탈출
						break;
					}
					
					int existence = (int) (Math.random()*countRowRoom(i - 1));
					
					if(existence == 0) {		//0일 때 방 생성, 16% ~ 33%, 이전 층에 방이 적으면 확률 높음
						makeRoom(i, j);
					}
				}
			}
			
			roomCount[i] = countRowRoom(i);
			
			if(roomCount[i] < 3) {
				i--;
			}
		}
		
		linkSpecialRoom(ELITE_ROW);
		linkSpecialRoom(BOSS_ROW);
		
		room[ELITE_ROW][3] = RoomKind.ELITE;
		room[BOSS_ROW][3] = RoomKind.BOSS;
		
		for(int i = 14; i >= 0; i--) {
			for(int j = 0; j < 7; j++) {
				if(room[i][j] == null) {
					System.out.print("   ");
				}
				else if(room[i][j] == RoomKind.ENEMY) {
					System.out.print(" M ");
				}
				else if(room[i][j] == RoomKind.MERCHANT) {
					System.out.print(" $ ");
				}
				else if(room[i][j] == RoomKind.TREASURE) {
					System.out.print(" T ");
				}
				else if(room[i][j] == RoomKind.REST) {
					System.out.print(" R ");
				}
				else if(room[i][j] == RoomKind.ELITE) {
					System.out.print(" E ");
				}
				else if(room[i][j] == RoomKind.BOSS) {
					System.out.print(" B ");
				}
			}
			
			System.out.println("");
			
			if(i == ELITE_ROW || i == ELITE_ROW + 1 || i == BOSS_ROW) {
				System.out.println("");
				continue;
			}
			for(int j = 0; j < 7; j++) {
				if(i == 0) {
					break;
				}
				for(int k = 0; k < 3; k++) {
					if(link[i - 1][j][k] == null) {
						System.out.print(" ");
					}
					else if(Integer.parseInt(link[i - 1][j][k]) - j == -1) {
						System.out.print("\\");
					}
					else if(Integer.parseInt(link[i - 1][j][k]) - j == 0) {
						System.out.print("|");
					}
					else if(Integer.parseInt(link[i - 1][j][k]) - j == 1) {
						System.out.print("/");
					}
				}
			}
			
			System.out.println("");
		}
	}
	
	//아래 층을 바탕으로 방을 만듬
	private void makeRoom(int row, int col) {
		if(room[row - 1][col] != null) {
			int direction = decideDirection(row - 1, col);		//direction은 -1, 0, 1 중 하나

			link[row - 1][col][direction + 1] = Integer.toString(col + direction);		//0에는 왼쪽, 1에는 가운데, 2에는 오른쪽 저장
			
			int probability = (int) (Math.random()*50);		//확률 / 0~29 몬스터, 30~38 상인, 39~44 보물, 45~49 휴식 
			
			if(probability < 30) {
				room[row][col + direction] = RoomKind.ENEMY;
			}
			else if(probability < 39) {
				room[row][col + direction] = RoomKind.MERCHANT;
			}
			else if(probability < 45 ){
				room[row][col + direction] = RoomKind.TREASURE;
			}
			else {
				room[row][col + direction] = RoomKind.REST;
			}
		}
	}
	
	//방향 정하기
	private int decideDirection(int row, int col) {
		int direction = (int) (Math.random()*3) - 1;
		while (!isValidLocation(col + direction)		//가리키는 곳이 -1이거나 7인 경우
		   || (col > 0 && Integer.toString(col).equals(link[row][col - 1][2]) && direction == -1)	//왼쪽 링크가 자신을 가리키고, direction이 왼쪽을 가리키는 경우
		   || (col < 6 && Integer.toString(col).equals(link[row][col + 1][0]) && direction == 1)		//오른쪽 링크가 자신을 가리키고, direction이 오른쪽을 가리키는 경우
			  ) {
			
			direction = (int) (Math.random()*3) - 1;
		}
		
		return direction;
	}
	
	//방을 만드는 위치가 유효한 위치에 있는 지 확인
	private boolean isValidLocation(int col) {
		if(col < 0 || col > 6) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//중간보스 방과 보스 방 이전의 방들의 링크는 3으로 고정
	private void linkSpecialRoom(int row) {
		for(int i = 0; i < COL; i++) {
			if(room[row - 1][i] != null) {
				link[row - 1][i][1] = "3";
			}
		}
	}
	
	//층에 몇 개의 방이 있는지 셈
	private int countRowRoom(int floor) {
		int count = 0;
		
		for(int i = 0; i < COL; i++) {
			if(room[floor][i] != null) {
				count++;
			}
		}
		
		return count;
	}
}