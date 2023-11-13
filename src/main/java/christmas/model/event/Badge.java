package christmas.model.event;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {

    NONE("없음",0),
    STAR("별",5000),
    TREE("트리", 10_000),
    SANTA("산타",20_000)
    ;

    private String name;
    private int minmumDicountAmount;

    Badge(String name, int minmumDicountAmount) {
        this.name = name;
        this.minmumDicountAmount = minmumDicountAmount;
    }

    public static String findBadgeBy(int dicountAmount) {
        return Arrays.stream(Badge.values())
                .sorted(Comparator.comparing(Badge::getMinmumDicountAmount).reversed())
                .filter(badge -> badge.minmumDicountAmount <= dicountAmount)
                .findAny()
                .orElse(NONE).name;
    }

    public String getName() {
        return name;
    }

    public int getMinmumDicountAmount() {
        return minmumDicountAmount;
    }
}
