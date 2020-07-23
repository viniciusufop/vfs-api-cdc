package br.com.vfs.api.cdc.country;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CountryState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @ManyToOne
    private Country country;

    public CountryState(@NotBlank String name, @NotNull Country country) {
        this.name = name;
        this.country = country;
    }

    public boolean countryStateIsACountry(final Long idCountry){
        return Objects.nonNull(idCountry) && country.getId().equals(idCountry);
    }
}
