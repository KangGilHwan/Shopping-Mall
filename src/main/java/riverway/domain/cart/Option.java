package riverway.domain.cart;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class Option {

    @Enumerated(EnumType.STRING)
    private Size size;
    private int amount;

    public Option() {
    }

    public Option(Size size, int amount) {
        this.size = size;
        this.amount = amount;
    }

    public void addAmount(Option newItem) {
        amount += newItem.amount;
    }

    public int calculatePrice(int price) {
        return price * amount;
    }

    public Option setSize(Size size) {
        this.size = size;
        return this;
    }

    public Option setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public Size getSize() {
        return size;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return size == option.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public String toString() {
        return "Option{" +
                "size=" + size +
                ", amount=" + amount +
                '}';
    }
}
