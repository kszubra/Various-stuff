package org.coursesandsandbox.kodilla.springhibernatecourse.library.repository;

import org.coursesandsandbox.kodilla.springhibernatecourse.library.domain.Book;

import java.util.ArrayList;
import java.util.List;


class InMemoryBookRepository implements BookRepository {

    private List<Book> books = new ArrayList<>();

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public void deleteByIndex(int index) {
        books.remove(index);
    }

}
