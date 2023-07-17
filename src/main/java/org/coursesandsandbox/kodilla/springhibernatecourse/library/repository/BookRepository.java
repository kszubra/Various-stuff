package org.coursesandsandbox.kodilla.springhibernatecourse.library.repository;

import org.coursesandsandbox.kodilla.springhibernatecourse.library.domain.Book;

import java.util.List;


public interface BookRepository {

    List<Book> findAll();
    void save(Book book);
    void deleteByIndex(int index);

}
