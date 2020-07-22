package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.book.BookRepository;
import br.com.vfs.api.cdc.country.CountryRepository;
import br.com.vfs.api.cdc.country.CountryState;
import br.com.vfs.api.cdc.country.CountryStateRepository;
import br.com.vfs.api.cdc.shared.annotations.CpfCnjp;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@Data
@NoArgsConstructor
public class NewPurchase {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @CpfCnjp
    private String document;
    @NotBlank
    private String address;
    @NotBlank
    private String complement;
    @NotBlank
    private String city;
    @NotNull
    private Long idCountry;
    private Long idCountryState;
    @NotNull
    private Long phone;
    @NotNull
    private Long cep;
    @Valid
    @NotNull
    private NewCart newCart;

    public Purchase toModel(final CountryRepository countryRepository,
                            final CountryStateRepository countryStateRepository,
                            final BookRepository bookRepository){
        final var country = countryRepository.findById(idCountry)
                .orElseThrow(()-> new IllegalArgumentException("country not found"));
        final var countryStateOptional = findCountryState(countryStateRepository);
        final var purchase = Purchase.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .document(document)
                .address(address)
                .complement(complement)
                .city(city)
                .country(country)
                .phone(phone)
                .cep(cep)
                .items(newCart.getModel(bookRepository))
                .build();
        countryStateOptional.ifPresent(purchase::setCountryState);
        return purchase;
    }

    private Optional<CountryState> findCountryState(CountryStateRepository countryStateRepository) {
        if(Objects.isNull(idCountryState)) return Optional.empty();
        return countryStateRepository.findById(idCountryState);
    }
}