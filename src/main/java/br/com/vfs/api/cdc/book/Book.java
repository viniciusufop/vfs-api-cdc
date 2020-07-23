package br.com.vfs.api.cdc.book;

import br.com.vfs.api.cdc.author.Author;
import br.com.vfs.api.cdc.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @DecimalMin("20.00")
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
}
