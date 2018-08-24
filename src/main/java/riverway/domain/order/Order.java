package riverway.domain.order;

import riverway.domain.User;
import riverway.domain.cart.Cart;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
//public class Order {
//
//    @ManyToOne
//    @JoinColumn(name = "consumer_id")
//    private User consumer;
//
//    @Embedded
//    private Shipping shipping;
//
//    private Cart cart;
//
//    public Order() {
//    }
//
//    public Order(User consumer, Shipping shipping, Cart cart) {
//        this.consumer = consumer;
//        this.shipping = shipping;
//        this.cart = cart;
//    }
//
//    public User getConsumer() {
//        return consumer;
//    }
//
//    public Order setConsumer(User consumer) {
//        this.consumer = consumer;
//        return this;
//    }
//
//    public Shipping getShipping() {
//        return shipping;
//    }
//
//    public Order setShipping(Shipping shipping) {
//        this.shipping = shipping;
//        return this;
//    }
//
//    public Cart getCart() {
//        return cart;
//    }
//
//    public Order setCart(Cart cart) {
//        this.cart = cart;
//        return this;
//    }
//}
