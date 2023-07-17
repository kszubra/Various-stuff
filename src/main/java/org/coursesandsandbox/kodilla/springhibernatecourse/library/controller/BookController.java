package org.coursesandsandbox.kodilla.springhibernatecourse.library.controller;

import java.util.List;

import org.coursesandsandbox.kodilla.springhibernatecourse.library.domain.BookDto;
import org.coursesandsandbox.kodilla.springhibernatecourse.library.domain.BookMapper;
import org.coursesandsandbox.kodilla.springhibernatecourse.library.service.BookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
class BookController {

    private final BookService service;

    @GetMapping
    public List<BookDto> getBooks(@AuthenticationPrincipal UserDetails user) {
        return BookMapper.toBookDtoList(service.getBooks());
    }

    @PostMapping
    public void createBook(@AuthenticationPrincipal UserDetails user, @RequestBody BookDto bookDto) {
        service.createBook(BookMapper.toBook(bookDto));
    }

    @DeleteMapping(path = "/{index}")
    public void deleteBook(@AuthenticationPrincipal UserDetails user, @PathVariable("index") int index) {
        service.deleteBook(index);
    }

}
