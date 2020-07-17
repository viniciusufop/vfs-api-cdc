package br.com.vfs.api.cdc.author;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 400)
    private String description;
    @CreatedDate
    @NotNull
    @Column(updatable = false)
    private LocalDateTime createAt;

    public Author(@NotBlank @Email final String email, @NotBlank final String name,
                  @NotBlank @Size(max = 400) final String description) {
        this.email = email;
        this.name = name;
        this.description = description;
    }
}
