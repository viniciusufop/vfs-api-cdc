package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.shared.annotations.CpfCnjp;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}