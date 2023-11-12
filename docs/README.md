## 협력 객체
### LocalDateTime(java.utill class)
- [ ] LocalDateTime을 생성한다. -> return LocalDateTime 
    - [ ]날짜가 잘못 입력 됬다면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.
### Order(class)
- [ ] 주문을 생성한다(List<`OrderItem`> orerItems, LocalDateTime localDateTime) -> return Order
    - [ ] 주문의 총 개수가 20개 초과라면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.
    - [ ] 음료만 주문 했다면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.
    - [ ] 중복 메뉴가 있다면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.
- [ ] 주문의 할인 전 총 결제 금액을 알려준다.() -> return int

### OrderItem(class)
- [ ] 주문 항목을 생성한다(Menu menu, count) -> return OrderItem
    - [ ] count는 1이상이 아니라면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.

### Menus(class)
- [ ] Menus를 생성한다() -> return Menus
    - [ ] enum으로 있는 값들을 가져와 Menu 객체들을 생성해서 List<`Menu`> 를 초기화 해준다. > 쉽게 찾기 위해 Map으로 해줘도 될듯
- [ ] 메뉴를 찾아 주는 기능(String name) -> return Menu
    - [ ] 이름에 맞는 메뉴가 없다면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.
### Menu(class)
- [ ] Menu를 생성한다.(String 음식타입, String 음식이름 int 가격) -> return Menu

### EventPlanner(class)
- [ ] EventPlanner를 생성한다.(List<`Event` events) -> return EventPlanner
- [ ] 할인 결과들에 대해 알려준다(Order order) -> return List<`DiscountResult`>
    - [ ] events를 loop를 돌며 Order에 대한 할인 내역을 알려달라고한다 
    - [ ] DiscountResult를 모아 List로 돌려준다,
### Event(Interface)     무비(이벤트플래너)는 여러 정책(이벤트)를 가지고 있음
ChrisMasEvent, 평일 이벤트,주말 이벤트,증정 이벤트, SpecialDay이벤트     >이벤트 정책에 따라 해줌  > 이벤트는 여러 정책들을 가지고 있고 여러정책은 여러조건을 가지고 있음

- [ ] Event를 생성한다.(이벤트 이름, 할인정책들) -> return Event
- [ ] 이벤트를 적용한다.(Order) -> return DiscountResult
    1. 주문 금액이 10000원이상이어야 한다 <<변동성이 심하다생각함 여기있으면 안됨
    2. 할인 정책 활용
    3. 기간에 맞는지 확인해야함

평일 할인 이벤트, 주말 할인 이벤트, 특별할인 이벤트 , 증정 이벤트

### EventCondition(Interface)
implements : AmountCondition, PeriodCondition

기간,

1. 할인 조건이 맞는지 확인해주기

### DisCountPolicy(Interface)
메뉴(메인,디저트) 할인정책, 기간 할인 정책,   << 이렇게 구성한 이유 한이벤트에도 여러 할인 정책이 들어갈 수 있음
- [ ] Discountpolicy를 생성한다.(String policyName, List<`DiscountCondition`>)
- [ ] 할인조건에게 맞는지 물어본다
    - [ ] 맞다면 정책에 맞게 할인금액을 리턴해준다. (정책 자율성 높음 알아서 조건이 여러개 맞을때 이벤트가 적용될 수도 있고 ㅇㅇ)

### DiscountResult
- [ ] 할인 결과를 생성한다.(String 이벤트이름, int 할인금액)

### Star(Enum)
1,2,3,4,5,6,7



### Badge(enum)
Star(별,5000)
TREE("트리", 10_000)
SANTA("산타",20_000)
### AppetizerMenu(enum)
이름, 가격
양송이 수프,6000
타파스,5500
시저샐러드,8000
### MainMenu(enum)
이름, 가격
티본스테이크(55,000)
바비큐립(54,000)
해산물파스타(35,000)
크리스마스파스타(25,000)
### DessertMenu(enum)
이름, 가격
초코케이크(15,000)
아이스크림(5,000)
### DrinkMenu(enum)
이름, 가격
제로콜라(3,000)
레드와인(60,000)
샴페인(25,000)

### 증정 메뉴(enum)
증정품, 가격, 조건
조건에 맞는 증정품 찾는 기능

FoodCategory


## 입력
1. 날짜 입력 기능 -> return int
2. 메뉴들 입력 기능 -> return List<RequestMenuDto>

## 출력
1. 결과 메시지 출력 기능 (12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!)
2. 주문 내역 출력
   1. 주문 메뉴 출력 기능
   2. 총 주문 금액 출력 기능
3. 주문 할인 내역 출력
   3. 증정 메뉴 출력 기능
   4. 혜택 내역 출력 기능
   5. 총 혜택 금액 출력 기능
   6. 할인 후 예상 결제 금액 출력 기능()
   7. 12월 이벤트 배지 출력 기능(String badgeName)


## 추가로 하면 좋을것들
1. 메뉴판 사용자에게 보여주기
2. 이벤트 목록 보여주기
   3. 주의사항 안내하기
      4. 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
      4.음료만 주문 시, 주문할 수 없습니다. 
      5. 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.

