package br.com.vfs.api.cdc.book;

import br.com.vfs.api.cdc.author.AuthorRepository;
import br.com.vfs.api.cdc.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @PostMapping
    @ResponseStatus(OK)
    @Transactional
    public String create(@RequestBody @Valid final NewBook newBook){
        log.info("M=create, newBook={}", newBook);
        var book = newBook.toModel(authorRepository, categoryRepository);
        bookRepository.save(book);
        return "Success create book";
    }
}
