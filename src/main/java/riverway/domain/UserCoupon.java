package riverway.domain;

import javax.persistence.*;

@Entity
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    public UserCoupon() {
    }

    public UserCoupon(User user, Coupon coupon) {
        this.user = user;
        this.coupon = coupon;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    @Override
    public String toString() {
        return "UserCoupon{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", username=" + user.getUsername() +
                ", coupon=" + coupon +
                '}';
    }
}
