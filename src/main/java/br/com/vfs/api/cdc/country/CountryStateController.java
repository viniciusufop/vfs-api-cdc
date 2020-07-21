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
@RequestMapping("/api/country-state")
public class CountryStateController {

    private final CountryRepository countryRepository;
    private final CountryStateRepository countryStateRepository;

    @PostMapping
    @ResponseStatus(OK)
    @Transactional
    public String create(@RequestBody @Valid final NewCountryState newCountryState){
        log.info("M=create, newCountryState={}", newCountryState);
        final var countryState = newCountryState.toModel(countryRepository);
        countryStateRepository.save(countryState);
        return "Success create country state";
    }
}
