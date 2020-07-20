package br.com.vfs.api.cdc.author;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {


    private final AuthorRepository authorRepository;

    @PostMapping
    @ResponseStatus(OK)
    @Transactional
    public String create(@RequestBody @Valid final NewAuthor newAuthor){
        log.info("M=create, newAuthor={}", newAuthor);
        var author = newAuthor.toModel();
        authorRepository.save(author);
        return "Success create author";
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @Transactional
    public Author findById(@PathVariable("id") Long id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not exist"));
    }
}
