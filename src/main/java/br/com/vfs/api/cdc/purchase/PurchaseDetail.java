package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.country.CountryState;
import br.com.vfs.api.cdc.coupon.Coupon;
import br.com.vfs.api.cdc.shared.annotations.CpfCnjp;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@ToString
public class PurchaseDetail {

    private final Long id;
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    @CpfCnjp
    private final String document;
    @NotBlank
    private final String address;
    @NotBlank
    private final String complement;
    @NotBlank
    private final String city;
    @NotNull
    private final String country;
    private final String countryState;
    @NotNull
    private final Long phone;
    @NotNull
    private final Long cep;
    @NotEmpty
    @Size(min = 1)
    private final Set<ItemDetail> items;
    private final boolean couponExisting;
    private final BigDecimal couponValue;
    @NotNull
    @Positive
    private final BigDecimal originalValue;
    @NotNull
    @Positive
    private final BigDecimal finalValue;

    public PurchaseDetail(final Purchase purchase) {
        this.id = purchase.getId();
        this.email = purchase.getEmail();
        this.firstName = purchase.getFirstName();
        this.lastName = purchase.getLastName();
        this.document = purchase.getDocument();
        this.address = purchase.getAddress();
        this.complement = purchase.getComplement();
        this.city = purchase.getCity();
        this.country = purchase.getCountry().getName();
        this.countryState  = Optional.ofNullable(purchase.getCountryState())
                .map(CountryState::getName)
                .orElse(null);
        this.phone = purchase.getPhone();
        this.cep = purchase.getCep();
        this.items = purchase.getItems().stream()
                .map(ItemDetail::new)
                .collect(Collectors.toSet());
        this.originalValue = items.stream()
                .map(ItemDetail::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.couponExisting = Objects.nonNull(purchase.getCoupon());
        this.couponValue = Optional.ofNullable(purchase.getCoupon())
                .map(Coupon::getDiscount)
                .map(originalValue::multiply)
                .orElse(null);
         this.finalValue = Optional.ofNullable(couponValue)
                 .map(originalValue::subtract)
                 .orElse(originalValue);
    }
}
