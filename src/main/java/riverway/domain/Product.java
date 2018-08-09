package riverway.domain;

import riverway.dto.ProductDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "product")
    List<Attachment> images = new ArrayList<>();

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
                .setDescription(description)
                .setImages(images);
    }

    public Long getId() {
        return id;
    }
}
