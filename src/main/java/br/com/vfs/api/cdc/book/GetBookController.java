package br.com.vfs.api.cdc.book;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class GetBookController {

    private final BookRepository bookRepository;

    @GetMapping
    @ResponseStatus(OK)
    @Transactional
    public Collection<BookBasic> findAll(){
        return bookRepository.findAll()
                .stream()
                .map(Book::toBookBasic)
                .collect(Collectors.toList());
    }
}
