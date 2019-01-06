package riverway.domain.order;

public enum OrderState {
    PAYMET_WATTING(true),
    PREPARING(true),
    SHIPPED(false),
    DELIVERING(false),
    DELIVERY_COMPLETED(false),
    CANCEL(false);

    private boolean changeable;

    OrderState(boolean changeable){
        this.changeable = changeable;
    }

    public boolean isChangeable() {
        return changeable;
    }
}
