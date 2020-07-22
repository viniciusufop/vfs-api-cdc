package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.country.CountryRepository;
import br.com.vfs.api.cdc.country.CountryStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@RequiredArgsConstructor
public class IsNullCountryStateValidator implements Validator {

    private final CountryRepository countryRepository;
    private final CountryStateRepository countryStateRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchase.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final var newPurchase = (NewPurchase) target;
        if(Objects.isNull(newPurchase.getIdCountryState())){
            final var country = countryRepository.findById(newPurchase.getIdCountry())
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Country (%d) not found", newPurchase.getIdCountry())));
            if(country.existCountryStates())
                errors.rejectValue("idCountryState", null,
                        String.format("Country states mandatory by country %s", country.getName()));
        } else {
            final var countryStateOptional = countryStateRepository.findById(newPurchase.getIdCountryState());
            if(countryStateOptional.isEmpty() || !countryStateOptional.get().countryStateIsACountry(newPurchase.getIdCountry()))
                errors.rejectValue("idCountryState", null,
                        String.format("Invalid country states (%d) mandatory", newPurchase.getIdCountryState()));

        }
    }
}
