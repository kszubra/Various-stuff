package org.coursesandsandbox.kodilla.springhibernatecourse.library.service;

import org.coursesandsandbox.kodilla.springhibernatecourse.library.domain.Book;

import java.util.List;


public interface BookService {
    List<Book> getBooks();
    void createBook(Book book);
    void deleteBook(int index);
}
