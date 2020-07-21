package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.book.Book;
import br.com.vfs.api.cdc.book.BookRepository;
import br.com.vfs.api.cdc.shared.annotations.ExistElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
public class NewItem {
    @NotNull
    @ExistElement(domainClass = Book.class)
    public Long idBook;
    @NotNull
    @Positive
    public Integer quantity;

    public BigDecimal calcBookValue(final BookRepository bookRepository){
        if(Objects.isNull(idBook)) return BigDecimal.ZERO;
        final var bookOptional = bookRepository.findById(idBook);
        if(bookOptional.isEmpty()) return BigDecimal.ZERO;
        return bookOptional.get().getPrice().multiply(new BigDecimal(quantity));
    }
}
