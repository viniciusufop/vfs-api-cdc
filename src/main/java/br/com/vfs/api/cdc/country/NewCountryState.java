package br.com.vfs.api.cdc.country;

import br.com.vfs.api.cdc.shared.annotations.UniqueValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class NewCountryState {

    @NotBlank
    @UniqueValue(domainClass = CountryState.class, fieldName = "name")
    private String name;

    @NotNull
    private Long idCountry;

    public CountryState toModel(final CountryRepository countryRepository) {
        final var country = countryRepository.findById(idCountry)
        .orElseThrow(() -> new IllegalArgumentException(String.format("country (%d) not found", idCountry)));
        return new CountryState(name, country);
    }
}
