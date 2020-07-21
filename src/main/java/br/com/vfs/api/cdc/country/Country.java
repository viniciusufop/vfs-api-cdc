package br.com.vfs.api.cdc.country;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<CountryState> countryStates;

    public Country(@NotBlank String name) {
        this.name = name;
    }

    public boolean existCountryStates(){
        return !CollectionUtils.isEmpty(countryStates);
    }

    public boolean countryStateAssociate(final Long idCountryState){
        return existCountryStates() && countryStates
                .stream()
                .map(CountryState::getId)
                .noneMatch(idCountryState::equals);
    }
}
