package org.coursesandsandbox.kodilla.springhibernatecourse.bytecode.instrumentation.javaagent;


import org.coursesandsandbox.kodilla.springhibernatecourse.bytecode.instrumentation.bytebuddy.ComicBook;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ComicBooksFilter {

    private final List<ComicBook> books;

    public ComicBooksFilter(List<ComicBook> books) {
        this.books = books;

    }

    public List<ComicBook> onlyBooksOlderThan(int years) {
        return books.stream()
                .filter(b -> b.getYear() < LocalDate.now().getYear() - years)
                .collect(Collectors.toList());
    }

}
