package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "title")
public class Item {

    @NotNull
    @ManyToOne
    public Book book;
    @NotNull
    private String title;
    @Positive
    @NotNull
    private Integer quantity;
    @Positive
    @NotNull
    private BigDecimal price;
}
