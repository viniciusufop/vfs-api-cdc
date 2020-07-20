package br.com.vfs.api.cdc.category;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class NewCategory {

    @NotBlank
    private String name;

    public Category toModel(){
        return new Category(name);
    }

}
