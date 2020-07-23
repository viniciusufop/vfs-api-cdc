package br.com.vfs.api.cdc.book;

import br.com.vfs.api.cdc.shared.errors.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
                .map(BookBasic::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @Transactional
    public BookDetail findById(@PathVariable @Valid @NotNull final Long id){
        final var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book id (%d) not found", id)));
        return new BookDetail(book);
    }
}
