package christmas.model.event;

import christmas.model.menu.DrinkMenu;
import java.util.Arrays;
import java.util.Comparator;

public enum Gift {

    CHAMPAGNE(DrinkMenu.CHAMPAGNE.getMenuName(), DrinkMenu.CHAMPAGNE.getPrice(), 120000),
    NO_GIFT("없음", 0,0)
    ;

    private final String menuName;
    private final int price;

    private final int minimumPurchaseAmount;

    Gift(String menuName, int price, int minimumPurchaseAmount) {
        this.menuName = menuName;
        this.price = price;
        this.minimumPurchaseAmount = minimumPurchaseAmount;
    }

    public static int findGiftAmountBy(int purchaseAmount) {
        return Arrays.stream(Gift.values())
                .sorted(Comparator.comparing(Gift::getMinimumPurchaseAmount).reversed())
                .filter(gift -> gift.minimumPurchaseAmount <= purchaseAmount)
                .findAny()
                .map(gift -> gift.price)
                .orElse(NO_GIFT.price);
    }

    public static String findGiftNameBy(int purchaseAmount) {
        return Arrays.stream(Gift.values())
                .sorted(Comparator.comparing(Gift::getMinimumPurchaseAmount).reversed())
                .filter(gift -> gift.minimumPurchaseAmount <= purchaseAmount)
                .findAny()
                .map(gift -> gift.menuName)
                .orElse(NO_GIFT.menuName);
    }

    public int getPrice() {
        return price;
    }

    public int getMinimumPurchaseAmount() {
        return minimumPurchaseAmount;
    }
}
