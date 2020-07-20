package br.com.vfs.api.cdc.category;

import br.com.vfs.api.cdc.shared.UniqueValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class NewCategory {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;

    public Category toModel(){
        return new Category(name);
    }

}
