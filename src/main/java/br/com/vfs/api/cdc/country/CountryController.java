package br.com.vfs.api.cdc.country;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/country")
public class CountryController {

    private final CountryRepository countryRepository;

    @PostMapping
    @ResponseStatus(OK)
    @Transactional
    public String create(@RequestBody @Valid final NewCountry newCountry){
        log.info("M=create, newCountry={}", newCountry);
        final var country = newCountry.toModel();
        countryRepository.save(country);
        return "Success create country";
    }
}
