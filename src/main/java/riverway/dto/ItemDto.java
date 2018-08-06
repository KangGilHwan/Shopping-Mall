package riverway.dto;

import riverway.domain.Item;

import javax.validation.constraints.Size;
import java.util.Objects;

public class ItemDto {

    private Long id;
    @Size(min = 2, max = 20)
    private String itemname;
    @Size()
    private Integer price;

    public ItemDto() {
    }

    private ItemDto(String itemname, Integer price){
        this.itemname = itemname;
        this.price = price;
    }

    public static ItemDto build(){
        return new ItemDto();
    }

    public Item toItem(){
        return new Item(itemname, price);
    }

    public Long getId() {
        return id;
    }

    public ItemDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getItemname() {
        return itemname;
    }

    public ItemDto setItemname(String itemname) {
        this.itemname = itemname;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ItemDto setPrice(Integer price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Objects.equals(id, itemDto.id) &&
                Objects.equals(itemname, itemDto.itemname) &&
                Objects.equals(price, itemDto.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemname, price);
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "itemname='" + itemname + '\'' +
                ", price=" + price +
                '}';
    }
}
