package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.book.BookRepository;
import br.com.vfs.api.cdc.country.CountryRepository;
import br.com.vfs.api.cdc.country.CountryState;
import br.com.vfs.api.cdc.country.CountryStateRepository;
import br.com.vfs.api.cdc.coupon.Coupon;
import br.com.vfs.api.cdc.coupon.CouponRepository;
import br.com.vfs.api.cdc.shared.annotations.CpfCnjp;
import br.com.vfs.api.cdc.shared.annotations.ExistElement;
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
    @ExistElement(domainClass = Coupon.class)
    private Long idCoupon;

    public Purchase toModel(final CountryRepository countryRepository,
                            final CountryStateRepository countryStateRepository,
                            final BookRepository bookRepository,
                            final CouponRepository couponRepository){
        final var country = countryRepository.findById(idCountry)
                .orElseThrow(()-> new IllegalArgumentException("country not found"));

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

        final var countryStateOptional = findCountryState(countryStateRepository);
        countryStateOptional.ifPresent(purchase::setCountryState);

        if(Objects.nonNull(idCoupon)){
            final var couponOptional = couponRepository.findById(idCoupon);
            couponOptional.ifPresent(purchase::setCoupon);
        }

        return purchase;
    }

    private Optional<CountryState> findCountryState(CountryStateRepository countryStateRepository) {
        if(Objects.isNull(idCountryState)) return Optional.empty();
        return countryStateRepository.findById(idCountryState);
    }
}