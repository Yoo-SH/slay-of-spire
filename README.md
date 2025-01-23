# [slay the spire](https://www.youtube.com/watch?v=LaiU90k8P0o&t=127s)

slay the spire Game을 모티브로 한 프로젝트입니다.(https://store.steampowered.com/app/646570/Slay_the_Spire/?l=koreana)


- 3.1 사용된 언어: JAVA
- 3.2 사용된 라이브러리: swing, awt
- 3.3 사용된 협업도구: GitHub, Discord

## **클래스 다이어그램**
![Image](https://github.com/user-attachments/assets/7dd47910-ef5b-47af-a9ab-556540fed0eb)


## 객체

### Fighter

- **주인공**: 주인공이 가져야할 hp, energy, gold, shield, buffDamage 등을 생성자에서 초기화하고 이후 캡슐화된 카드 클래스, 카드 가방클래스, 핸드카드 클래스 등을 임폴트하여 주인공이 가져야할 기능별로 초기화 값에 영향을 주도록 함수로 구현. 또한, 주인공을 전역변수로 설정하여 단계별로 맵을 클리어해도 값을 유지하도록 설정.

- **몬스터**: Enemy클래스에서 몬스터가 가져야할 값들을 생성자에서 초기화. 몬스터는 다양한 종류의 인스턴스 객체 생성이 필요하여 Enemy클래스를 상속하여 다형성을 이용해 다양한 Enemy 인스턴스 객체를 생성(enemyList)

### Card

- **카드**: 카드가 가져야할 cost, damage, canUseEnemy, hasSkill(버프, 공격카드 구분을 위한 변수)등을 생성자에서 초기화하고 기능별로 초기화 값에 영향을 주도록 함수로 구현. 이후 Card클래스를 상속하여 다형성을 이용해 다양한 공격카드, 버프카드 등의 다양한 객체 생성

### CardBag
- **카드 가방**: LinkedList<Card>를 생성하여 다양한 카드 객체를 저장 및 관리

### CardTarshCan
- **카드 쓰레기더미**: LinkedList<Card>를 생성하여 다양한 카드 객체를 저장 및 관리

### HandCard
- **핸드 카드**: LinkedList<Card> 생성하여 다양한 카드 객체를 저장 및 관리

### Route

- **루트 알고리즘**: 각 단계별로 플레이어는 몬스터맵, 상점, 휴식, 보물을 주어진 경로에 따라서 선택해야함. 각 단계별로 몬스터맵과, 상점, 휴식, 보물 등은 아래층을 바탕으로 랜덤하게 생성. 방향 또한 랜덤하게 생성됨


## 몬스터맵 구상 그림

- **몬스터맵(일반. 중간, 보스)**: 인스턴스 몬스터 객체들의 위치와 값을 미리 세팅해두고, 주인공, 카드, 가방, 쓰레기통을 모두 구현.

![Image](https://github.com/user-attachments/assets/057a9c50-a40e-4dc1-af10-c7d280af8ae4) 


- **상점**: 카드와 골드를 랜덤으로 설정하여 지정된 위치에서 구매할 수 있도록 구현

![Image](https://github.com/user-attachments/assets/f8bb312a-a859-4bdb-8e7e-ff2502cbe6f9)
- **휴식**: 체력을 충전할 수 있도록 구현
- **보물**: 골드를 충전할 수 있도록 구현

## GUI

### 과정 1: 이미지를 캡처하여 저장
![Image](https://github.com/user-attachments/assets/e8499406-b2df-4a11-b68b-2e1ae8bd28c8)

### 과정2: 도구를 이용하여 전처리함
![Image](https://github.com/user-attachments/assets/99efe790-4b3e-4496-b241-8df9b108a056)

### 과정3: 효과를 적용하여 이미지를 생성함
![Image](https://github.com/user-attachments/assets/ccbb4664-9a78-48e6-8373-31e2b774181b)

### 예시: 임펙트 전 이미지
![Image](https://github.com/user-attachments/assets/bf967020-64de-4812-913e-a486713cc038)

### 예시: 임펙트 후 이미지
![Image](https://github.com/user-attachments/assets/274072c9-a1e1-4037-bdb7-cada49d3fd18)


### 예시: GUI 구현 전
![Image](https://github.com/user-attachments/assets/f180f4a9-90f6-4247-b955-97a12640f2f6)

### 예시: GUI 구현 후
![Image](https://github.com/user-attachments/assets/44d01609-99ae-493d-b2eb-0b4afc8f2d16)