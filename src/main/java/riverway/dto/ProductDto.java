package riverway.dto;

import riverway.domain.Attachment;
import riverway.domain.Category;
import riverway.domain.Product;
import riverway.domain.User;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDto {

    private Long id;
    @Size(min = 2, max = 20)
    private String name;
    @Size(min = 4)
    private Integer price;

    private Category category;

    private String description;

    private User seller;

    private List<Attachment> images = new ArrayList<>();

    public ProductDto() {
    }

    private ProductDto(String name, Integer price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public static ProductDto build() {
        return new ProductDto();
    }

    public Product toProduct() {
        return new Product(name, price, description, category, seller);
    }

    public Long getId() {
        return id;
    }

    public ProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductDto setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ProductDto setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getSeller() {
        return seller;
    }

    public ProductDto setSeller(User seller) {
        this.seller = seller;
        return this;
    }

    public List<Attachment> getImages() {
        return images;
    }

    public ProductDto setImages(List<Attachment> images) {
        this.images = images;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description);
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
