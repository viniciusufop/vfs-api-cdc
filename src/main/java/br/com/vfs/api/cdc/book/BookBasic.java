package br.com.vfs.api.cdc.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookBasic {
    private final Long id;
    private final String title;

    public BookBasic(final Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
    }
}
