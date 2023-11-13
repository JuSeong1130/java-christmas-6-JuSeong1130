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

    private final int amountInclusion;

    Gift(String menuName, int price, int amountInclusion) {
        this.menuName = menuName;
        this.price = price;
        this.amountInclusion = amountInclusion;
    }

    public static int findGiftAmountBy(int purchaseAmount) {
        return Arrays.stream(Gift.values())
                .sorted(Comparator.comparing(Gift::getAmountInclusion).reversed())
                .filter(gift -> gift.amountInclusion <= purchaseAmount)
                .findAny()
                .orElse(NO_GIFT).price;

    }

    public int getPrice() {
        return price;
    }

    public int getAmountInclusion() {
        return amountInclusion;
    }
}
