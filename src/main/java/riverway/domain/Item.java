package riverway.domain;

import riverway.dto.ItemDto;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemname;

    @Column(nullable = false)
    private Integer price;

    public Item() {
    }

    public Item(String itemname, Integer price) {
        this.itemname = itemname;
        this.price = price;
    }

    public ItemDto toItemDto() {
        return ItemDto.build()
                .setItemname(itemname)
                .setPrice(price);
    }

    public Long getId() {
        return id;
    }
}
