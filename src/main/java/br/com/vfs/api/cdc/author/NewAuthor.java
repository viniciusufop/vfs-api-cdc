package br.com.vfs.api.cdc.author;

import br.com.vfs.api.cdc.shared.annotations.UniqueValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@ToString
@NoArgsConstructor
public class NewAuthor implements Serializable {

    @Getter
    @NotBlank
    @Email
    @UniqueValue(domainClass = Author.class, fieldName = "email")
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 400)
    private String description;

    public Author toModel(){
        return new Author(email, name, description);
    }

}
