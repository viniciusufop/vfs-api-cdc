package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.country.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
public class IsNullCountryStateValidator implements Validator {

    private final CountryRepository countryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchase.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final var newPurchase = (NewPurchase) target;
        final var country = countryRepository.findById(newPurchase.getIdCountry())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Country (%d) not found", newPurchase.getIdCountry())));
        if(country.existCountryStates()){
            if(newPurchase.getIdCountryState() == null)
                errors.rejectValue("idCountryState", null, String.format("Country states mandatory by country %s", country.getName()));
            if(country.countryStateAssociate(newPurchase.getIdCountryState()))
                errors.rejectValue("idCountryState", null, String.format("Country states mandatory by country %s", country.getName()));
        } else {
            if(newPurchase.getIdCountryState() != null){
                errors.rejectValue("idCountryState", null, String.format("Not country states from country %s", country.getName()));
            }
        }
    }
}
