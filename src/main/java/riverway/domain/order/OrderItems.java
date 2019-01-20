package riverway.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class OrderItems {

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderItem> orderItems = new ArrayList<>();

    public OrderItems() {
    }

    public OrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int calculateTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getPrice)
                .sum();
    }

    public String joinItemNames() {
        return orderItems.stream()
                .map(OrderItem::getItemName)
                .collect(Collectors.joining(", "));
    }

    public int getNumberOfItems() {
        return orderItems.size();
    }
}
