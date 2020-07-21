package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.book.BookRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class NewCart {
    @NotNull
    @Positive
    private BigDecimal total;
    @NotEmpty
    @Valid
    private List<NewItem> newItems;

    public BigDecimal totalCalculate(final BookRepository bookRepository){
        if (CollectionUtils.isEmpty(newItems)) return BigDecimal.ZERO;
        return newItems
                .stream()
                .map(newItem -> newItem.calcBookValue(bookRepository))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
