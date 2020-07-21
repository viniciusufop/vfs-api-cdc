package br.com.vfs.api.cdc.book;

import br.com.vfs.api.cdc.author.AuthorDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
public class BookDetail {
    private final String title;
    private final String resume;
    private final String summary;
    private final BigDecimal price;
    private final int pages;
    private final String isbn;
    private final AuthorDetail author;

    public BookDetail(final Book book) {
        this.title = book.getTitle();
        this.resume = book.getResume();
        this.summary = book.getSummary();
        this.price = book.getPrice();
        this.pages = book.getPages();
        this.isbn = book.getIsbn();
        this.author = new AuthorDetail(book.getAuthor());
    }
}
