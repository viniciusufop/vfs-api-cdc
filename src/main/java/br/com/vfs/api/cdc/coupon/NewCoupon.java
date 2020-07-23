package br.com.vfs.api.cdc.coupon;

import br.com.vfs.api.cdc.shared.annotations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Setter
@ToString
@NoArgsConstructor
public class NewCoupon {

    @NotBlank
    @UniqueValue(domainClass = Coupon.class, fieldName = "code")
    private String code;
    @NotNull
    @Positive
    @DecimalMax("1.0")
    private BigDecimal discount;
    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime validate;

    public Coupon toModel(){
        return new Coupon(code, discount, validate);
    }
}
