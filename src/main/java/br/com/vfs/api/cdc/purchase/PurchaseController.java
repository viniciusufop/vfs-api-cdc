package br.com.vfs.api.cdc.purchase;

import br.com.vfs.api.cdc.book.BookRepository;
import br.com.vfs.api.cdc.country.CountryRepository;
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
    private final BookRepository bookRepository;
    @InitBinder("newPurchase")
    public void init(final WebDataBinder dataBinder){
        dataBinder.addValidators(new IsNullCountryStateValidator(countryRepository));
        dataBinder.addValidators(new TotalNewCartValidator(bookRepository));
    }

    @PostMapping
    @ResponseStatus(OK)
    @Transactional
    public String create(@RequestBody @Valid final NewPurchase newPurchase){
        log.info("M=create, newPurchase={}", newPurchase);
        return "Success create purchase";
    }
}
