package br.com.vfs.api.cdc.author;

import lombok.Getter;

@Getter
public class AuthorDetail {
    private final String name;
    private final String description;

    public AuthorDetail(final Author author) {
        this.name = author.getName();
        this.description = author.getDescription();
    }
}
