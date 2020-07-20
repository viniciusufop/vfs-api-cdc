package br.com.vfs.api.cdc.book;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface BookRepository extends CrudRepository<Book, Long> {

    Collection<Book> findAll();
}
