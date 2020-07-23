package br.com.vfs.api.cdc.purchase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class GetPurchaseController {

    private final PurchaseRepository purchaseRepository;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @Transactional
    public PurchaseDetail findById(@PathVariable("id") @Valid final Long id){
        final var purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Purchase (%d) not found", id)));
        final var purchaseDetail = new PurchaseDetail(purchase);
        log.info("M=findById, purchaseDetail={}", purchaseDetail);
        return purchaseDetail;
    }
}
