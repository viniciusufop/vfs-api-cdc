package br.com.vfs.api.cdc.country;

import br.com.vfs.api.cdc.shared.annotations.UniqueValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class NewCountry {

    @NotBlank
    @UniqueValue(domainClass = Country.class, fieldName = "name")
    private String name;

    public Country toModel() {
        return new Country(name);
    }
}
