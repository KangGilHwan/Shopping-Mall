package riverway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Coupon {

    //TODO 쿠폰 나누기 - %와 일정 금액 할인

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer discount;

    public Coupon() {
    }

    public Coupon(String name, Integer discount) {
        this.name = name;
        this.discount = discount;
    }

    public int discount(int beforePrice) {
        int price = beforePrice - discount;
//        if (price < 3 || beforePrice < 10) {
//            throw new RuntimeException("쿠폰을 적용할 수 없습니다.");
//        }
        return price;
    }

    public Long getId() {
        return id;
    }

    public Coupon setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Coupon setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Coupon setDiscount(Integer discount) {
        this.discount = discount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return Objects.equals(name, coupon.name) &&
                Objects.equals(discount, coupon.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, discount);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                '}';
    }
}
