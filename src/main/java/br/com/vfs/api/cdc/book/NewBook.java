package br.com.vfs.api.cdc.book;

import br.com.vfs.api.cdc.author.AuthorRepository;
import br.com.vfs.api.cdc.category.CategoryRepository;
import br.com.vfs.api.cdc.shared.annotations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Setter
@NoArgsConstructor
public class NewBook {
    @NotBlank
    @UniqueValue(domainClass = Book.class, fieldName = "title")
    private String title;
    @NotBlank
    @Size(max = 500)
    private String resume;
    private String summary;
    @NotNull
    @Min(20)
    private BigDecimal price;
    @NotNull
    @Min(100)
    private int pages;
    @NotBlank
    @UniqueValue(domainClass = Book.class, fieldName = "isbn")
    private String isbn;
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime publicationDate;
    @NotNull
    private Long idCategory;
    @NotNull
    private Long idAuthor;

    public Book toModel(final AuthorRepository authorRepository, final CategoryRepository categoryRepository) {
        var category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid category: %d", this.idCategory)));
        var author = authorRepository.findById(idAuthor)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid author: %d", this.idAuthor)));
        return Book.builder()
                .title(title)
                .resume(resume)
                .summary(summary)
                .price(price)
                .pages(pages)
                .isbn(isbn)
                .publicationDate(publicationDate)
                .category(category)
                .author(author)
                .build();
    }
}
