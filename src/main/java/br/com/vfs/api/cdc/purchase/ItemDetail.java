package br.com.vfs.api.cdc.purchase;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@ToString
public class ItemDetail {
    @NotNull
    private String title;
    @Positive
    @NotNull
    private Integer quantity;
    @Positive
    @NotNull
    private BigDecimal price;

    public ItemDetail(final Item item) {
        this.title = item.getTitle();
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
    }

    public BigDecimal totalPrice(){
        return price.multiply(new BigDecimal(quantity));
    }
}
