package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.country.Country;
import br.com.vfs.api.cdc.country.CountryState;
import br.com.vfs.api.cdc.coupon.Coupon;
import br.com.vfs.api.cdc.shared.annotations.CpfCnjp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @ManyToOne
    private Country country;
    @ManyToOne
    private CountryState countryState;
    @NotNull
    private Long phone;
    @NotNull
    private Long cep;
    @NotEmpty
    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "purchase_items")
    private Set<Item> items;
    @ManyToOne
    private Coupon coupon;

    public void setCountryState(CountryState countryState) {
        Assert.isTrue(countryState.countryStateIsACountry(country.getId()), "Country state not associate a Country");
        this.countryState = countryState;
    }
}
