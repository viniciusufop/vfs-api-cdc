package br.com.vfs.api.cdc.category;

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
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @InitBinder("newCategory")
    public void init(final WebDataBinder dataBinder){
        dataBinder.addValidators(new ValidatedDuplicateNameCategory(categoryRepository));
    }

    @PostMapping
    @ResponseStatus(OK)
    @Transactional
    public String create(@RequestBody @Valid final NewCategory newCategory){
        var category = newCategory.toModel();
        categoryRepository.save(category);
        return "Success create category";
    }
}
