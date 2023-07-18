package org.coursesandsandbox.objectflatterer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExampleData {

    public static Category getExampleCategory() {
        Category mammals = Category.builder()
                .categoryId("1")
                .subcategories(new ArrayList<>())
                .names(new HashMap<>())
                .build();
        mammals.getNames().put("EN", "Mammals");
        mammals.getNames().put("PL", "Ssaki");

        Category dogs = Category.builder()
                .categoryId("2")
                .subcategories(new ArrayList<>())
                .names(new HashMap<>())
                .build();
        dogs.getNames().put("EN", "Dogs");
        dogs.getNames().put("PL", "Psy");

        Category cats = Category.builder()
                .categoryId("3")
                .subcategories(new ArrayList<>())
                .names(new HashMap<>())
                .build();
        cats.getNames().put("EN", "Cats");
        cats.getNames().put("PL", "koty");

        Category goldenRet = Category.builder()
                .categoryId("3")
                .subcategories(new ArrayList<>())
                .names(new HashMap<>())
                .build();
        goldenRet.getNames().put("EN", "GoldenRet");
        goldenRet.getNames().put("PL", "ZłotyRet");

        Category gshep = Category.builder()
                .categoryId("4")
                .subcategories(new ArrayList<>())
                .names(new HashMap<>())
                .build();
        gshep.getNames().put("EN", "Gshep");
        gshep.getNames().put("PL", "Owczrek");

        Category maineCoon = Category.builder()
                .categoryId("5")
                .subcategories(new ArrayList<>())
                .names(new HashMap<>())
                .build();
        maineCoon.getNames().put("EN", "Main_en");
        maineCoon.getNames().put("PL", "Maine_pl");

        Category siberian = Category.builder()
                .categoryId("6")
                .subcategories(new ArrayList<>())
                .names(new HashMap<>())
                .build();
        siberian.getNames().put("EN", "siberian");
        siberian.getNames().put("PL", "Syberyczyk");

        dogs.getSubcategories().addAll(List.of(goldenRet, gshep));
        cats.getSubcategories().addAll(List.of(maineCoon, siberian));
        mammals.getSubcategories().addAll(List.of(cats, dogs));

        return mammals;
    }
}
