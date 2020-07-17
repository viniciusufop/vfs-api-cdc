package br.com.vfs.api.cdc.author;

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

    @NotBlank
    @Email
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
