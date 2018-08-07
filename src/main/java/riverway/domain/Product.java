package riverway.domain;

import riverway.dto.ProductDto;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Lob
    private String description;

    public Product() {
    }

    public Product(String name, Integer price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public ProductDto toProductDto() {
        return ProductDto.build()
                .setName(name)
                .setPrice(price)
                .setDescription(description);
    }

    public Long getId() {
        return id;
    }
}
