package br.com.vfs.api.cdc.coupon;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupon")
public class CouponController {

    private final CouponRepository couponRepository;

    @PostMapping
    @ResponseStatus(OK)
    @Transactional
    public String create(@RequestBody @Valid final NewCoupon newCoupon){
        log.info("M=create, newCoupon={}", newCoupon);
        var coupon = newCoupon.toModel();
        couponRepository.save(coupon);
        return "Success create coupon";
    }
}
