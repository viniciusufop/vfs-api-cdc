package br.com.vfs.api.cdc.book;

import br.com.vfs.api.cdc.author.Author;
import br.com.vfs.api.cdc.category.Category;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
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
    private String isbn;
    @Future
    @NotNull
    private LocalDateTime publicationDate;
    @NotNull
    @ManyToOne
    private Category category;
    @NotNull
    @ManyToOne
    private Author author;

    public BookBasic toBookBasic() {
        return new BookBasic(id, title);
    }
}
