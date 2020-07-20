package br.com.vfs.api.cdc.category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    boolean existsByName(final String name);
}
