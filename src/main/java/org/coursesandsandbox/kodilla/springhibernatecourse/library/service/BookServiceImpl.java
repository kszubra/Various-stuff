package org.coursesandsandbox.kodilla.springhibernatecourse.library.service;

import java.util.List;


import lombok.RequiredArgsConstructor;
import org.coursesandsandbox.kodilla.springhibernatecourse.library.domain.Book;
import org.coursesandsandbox.kodilla.springhibernatecourse.library.repository.BookRepository;

@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public List<Book> getBooks() {
        return repository.findAll();
    }

    @Override
    public void createBook(Book book) {
        repository.save(book);
    }

    @Override
    public void deleteBook(int index) {
        repository.deleteByIndex(index);
    }

}
