## 시뮬레이션
1. `중개자`가 `사용자`에게 날짜를 물어본다.
   2. 날짜가 유효하지 않다면 다시 물어본다.
2. `중개자`가 `사용자`에게 메뉴를 입력해달라고한다.
   3. 메뉴가 유효하지 않다면 다시 물어본다.
3. `중개자`는 `OrderService`에게 `날짜`와 `주문메뉴`를 보내 `주문 요약 내역`을 알려달라고한다.
4. `OrderService`는 `날짜`와 `주문메뉴`를 받아 `Order`를 생성하고 `EventPlanner`에게 할인결과를 알려달라고한다.
5. `OrderService`는 할인결과를 받아 `중개자`에게 전달해준다.

## 협력 객체
### LocalDateTime(java.utill class)
- [x] LocalDateTime을 생성한다. -> return LocalDateTime 
    - [x] 날짜가 잘못 입력 됬다면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.
### Order(class)
- [x] 주문을 생성한다(OrderItems, LocalDateTime localDateTime) -> return Order
- [x] 주문의 할인 전 총 결제 금액을 알려준다.() -> return int
- [x] 주말인지 확인해준다 -> return boolean
- [x] 평일인지 확인해준다 -> return boolean
- [x] 주문 금액이 일정 금액 이상인지 확인해준다 -> return boolean
- [x] menuType이 몇개인지 확인해준다 -> return int
- [x] 주문날짜가 시작날짜와 종료날짜 사이에 있는지 확인해준다 -> return boolean
- [x] 주문날짜가 특정날짜로부터 몇일이 지났닌지 확인한다. -> return int
- [x] 주문날짜가 특정날짜들에 포함되는지 확인한다. -> return boolean

### OrderItems(class) 
- [x] OrderItems를 생성한다(List<OrderItem>) -> return OrderItems
  - [x] 주문의 총 개수가 20개 초과라면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.
  - [x] 음료만 주문 했다면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.
  - [x] 중복 메뉴가 있다면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.
- [x] 총 주문 금액을 리턴해준다 -> int
- [x] menuType이 몇개인지 확인해준다 -> int

### OrderItem(class)
- [x] 주문 항목을 생성한다(Menu menu, OrderQuantity orderQuantity) -> return OrderItem
- [x] 주문항목이 음료수타입인지 확인한다. -> return boolean
- [x] 주문항목의 금액을 확인한다. -> return int

### OrderQuantity(class)
- [x] 주문수량을 생성한다 -> OrderQuantity
    - [x] orderQuantity는 1이상이 아니라면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.

### Menus(class)
- [x] Menus를 생성한다() -> return Menus
    - [x] enum으로 있는 값들을 가져와 Menu 객체들을 생성해서 List<`Menu`> 를 초기화 해준다. 
- [x] 메뉴를 찾아 주는 기능(String name) -> return Menu
    - [x] 이름에 맞는 메뉴가 없다면 예외를 발생 시켜 에러 메시지를 출력하고 다시 입력 받는다.
### Menu(class)
- [x] Menu를 생성한다.(MenuType 음식타입, String 음식이름 int 가격) -> return Menu
- [x] Menu의 이름이 일치하는지 확인한다. -> return boolean
- [x] Menu의 타입이 일치하는지 확인한다. -> return boolean

### EventPlanner(class)
- [x] EventPlanner를 생성한다.(List<`Event` events) -> return EventPlanner
- [x] 할인 결과들에 대해 알려준다(Order order) -> return DiscountResults

### Event(Interface)     
구현체 : ChrisMasEvent, WeekdayEvent, WeekendEvent, GiftEvent, SpecialDayEvent
- [x] Event를 생성한다.(이벤트 이름, 할인정책들) -> return Event
- [x] 이벤트를 적용하여 할인한다(Order) -> return DiscountResult

### DiscountResults(class)
- [x] 할인결과들을 생성한다.(List<DiscountResult>) -> return DiscountResults
- [x] 총 할인 금액을 확인한다. -> return int

### DiscountResult
- [x] 할인 결과를 생성한다.(String 이벤트이름, int 할인금액) -> return DiscountResult

## 데이터

### MenuType
- 에피타이저
- 음료
- 메인
- 디저트

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

### SpecialDay(Enum)
날짜
- DAY_3(3)

### Badge(enum)
뱃지이름, 할인금액
- Star(별,5000)
- TREE("트리", 10_000)
- SANTA("산타",20_000)

### Gift(enum)
증정품 이름, 가격, 최소 주문 금액
- NO_GIFT("없음", 0, 0);


## 입력
1. 날짜 입력 기능 -> return LocalDate
2. 메뉴들 입력 기능 -> return List<RequestOrderItemDto>

## 출력
2. 주문 요약 내역 출력 기능(ResponseOrderSummaryDto)
   1. 안내 메시지 출력 기능 
   2. 주문 메뉴 출력 기능
   2. 총 주문 금액 출력 기능
   3. 증정 메뉴 출력 기능
   4. 혜택 내역 출력 기능
   5. 총 혜택 금액 출력 기능
   6. 할인 후 예상 결제 금액 출력 기능
   7. 12월 이벤트 배지 출력 기능
8. 메뉴 목록 출력 기능
9. 주의 사항 출력 기능



