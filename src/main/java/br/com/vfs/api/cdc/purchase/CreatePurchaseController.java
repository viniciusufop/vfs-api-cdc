package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.book.BookRepository;
import br.com.vfs.api.cdc.country.CountryRepository;
import br.com.vfs.api.cdc.country.CountryStateRepository;
import br.com.vfs.api.cdc.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class CreatePurchaseController {

    private final CountryRepository countryRepository;
    private final CountryStateRepository countryStateRepository;
    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;
    private final CouponRepository couponRepository;

    @InitBinder("newPurchase")
    public void init(final WebDataBinder dataBinder){
        dataBinder.addValidators(new IsNullCountryStateValidator(countryRepository, countryStateRepository));
        dataBinder.addValidators(new TotalNewCartValidator(bookRepository));
        dataBinder.addValidators(new IsCouponExpirationValidator(couponRepository));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Transactional
    public String create(@RequestBody @Valid final NewPurchase newPurchase){
        log.info("M=create, newPurchase={}", newPurchase);
        final var purchase = newPurchase.toModel(countryRepository, countryStateRepository, bookRepository, couponRepository);
        return String.format("/api/purchase/%d",purchaseRepository.save(purchase).getId());
    }
}
