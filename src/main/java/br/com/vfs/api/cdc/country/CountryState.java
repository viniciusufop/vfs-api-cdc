package br.com.vfs.api.cdc.country;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
