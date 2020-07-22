package br.com.vfs.api.cdc.coupon;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String code;
    @NotNull
    @Positive
    private BigDecimal discount;
    @NotNull
    @Future
    private LocalDateTime validate;

    public Coupon(@NotBlank String code, @NotNull @Positive BigDecimal discount, @NotNull @Future LocalDateTime validate) {
        this.code = code;
        this.discount = discount;
        this.validate = validate;
    }

    public boolean expired(){
        return validate.compareTo(LocalDateTime.now()) < 0;
    }
}
