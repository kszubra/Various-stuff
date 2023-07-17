package org.coursesandsandbox.kodilla.springhibernatecourse.csvconverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    private int id;
    private int quantity;
    private int price;

}
