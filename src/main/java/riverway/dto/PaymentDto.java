package riverway.dto;

public class PaymentDto {

    private Long orderId;
    private String username;
    private String itemName;
    private int quantity;
    private int totalPrice;

    public PaymentDto() {
    }

    public PaymentDto(Long orderId, String username, String itemName, int quantity, int totalPrice){
        this.orderId = orderId;
        this.username = username;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public PaymentDto setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PaymentDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public PaymentDto setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public PaymentDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public PaymentDto setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public static PaymentDto of(Long orderId, String username, String itemName, int quantity, int totalPrice) {
        return new PaymentDto(orderId, username, itemName, quantity, totalPrice);
    }
}
