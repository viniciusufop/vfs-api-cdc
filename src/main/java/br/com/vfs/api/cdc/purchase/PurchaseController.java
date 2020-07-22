package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.book.BookRepository;
import br.com.vfs.api.cdc.country.CountryRepository;
import br.com.vfs.api.cdc.country.CountryStateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final CountryRepository countryRepository;
    private final CountryStateRepository countryStateRepository;
    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;

    @InitBinder("newPurchase")
    public void init(final WebDataBinder dataBinder){
        dataBinder.addValidators(new IsNullCountryStateValidator(countryRepository, countryStateRepository));
        dataBinder.addValidators(new TotalNewCartValidator(bookRepository));
    }

    @PostMapping
    @ResponseStatus(OK)
    @Transactional
    public String create(@RequestBody @Valid final NewPurchase newPurchase){
        log.info("M=create, newPurchase={}", newPurchase);
        final var purchase = newPurchase.toModel(countryRepository, countryStateRepository, bookRepository);
        purchaseRepository.save(purchase);
        return "Success create purchase";
    }
}
