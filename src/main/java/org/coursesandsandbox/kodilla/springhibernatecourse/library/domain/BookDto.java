package org.coursesandsandbox.kodilla.springhibernatecourse.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    String title;
    String author;
    int year;
}
