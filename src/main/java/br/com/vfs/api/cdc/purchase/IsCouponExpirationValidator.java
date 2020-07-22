package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@RequiredArgsConstructor
public class IsCouponExpirationValidator implements Validator {

    private final CouponRepository couponRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchase.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final var newPurchase = (NewPurchase) target;
        final var idCoupon = newPurchase.getIdCoupon();
        if(Objects.isNull(idCoupon)) return;
        final var couponOptional = couponRepository.findById(idCoupon);
        if (couponOptional.isPresent() && couponOptional.get().expired())
            errors.rejectValue("idCoupon", null, String.format("Expired coupon (%d)", idCoupon));
    }
}
