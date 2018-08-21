package riverway.domain;

import riverway.dto.ProductDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToMany(mappedBy = "product")
    private List<Attachment> images = new ArrayList<>();

    public Product() {
    }

    public Product(String name, Integer price, String description, Category category, User seller) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.seller = seller;
    }

    public ProductDto toProductDto() {
        return ProductDto.build()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .setCategory(category)
                .setDescription(description)
                .setSeller(seller)
                .setImages(images);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public User getSeller() {
        return seller;
    }

    public List<Attachment> getImages() {
        return Collections.unmodifiableList(images);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", seller=" + seller +
                '}';
    }
}
