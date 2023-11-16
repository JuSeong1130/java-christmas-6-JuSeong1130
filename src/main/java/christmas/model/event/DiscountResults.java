package christmas.model.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscountResults {

    private List<DiscountResult> discountResults;

    public DiscountResults(List<DiscountResult> discountResults) {
        this.discountResults = new ArrayList<>(discountResults);
    }

    public List<DiscountResult> getDiscountResults() {
        return Collections.unmodifiableList(List.copyOf(discountResults));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiscountResults that = (DiscountResults) o;
        return Objects.equals(discountResults, that.discountResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountResults);
    }

    public int calculateTotalDiscountAmount() {
        return discountResults.stream()
                .mapToInt(DiscountResult::getDiscountAmount)
                .sum();
    }
}
