package org.coursesandsandbox.objectflatterer;

import java.util.ArrayList;
import java.util.List;

import static org.coursesandsandbox.objectflatterer.Flatterer.flattenCategory;

public class TestClass {


    public static void main(String[] args) {
        Category startCategory = ExampleData.getExampleCategory();
        List<ResultCategory> resultCategories = new ArrayList<>();

        for (String lang : startCategory.getNames().keySet()) {
            resultCategories.addAll(
                    flattenCategory(
                            startCategory.getCategoryId(),
                            startCategory.getCategoryId(),
                            startCategory.getNames().get(lang),
                            startCategory.getSubcategories(),
                            lang
                            )
            );
        }

        for(ResultCategory result : resultCategories) {
            System.out.println(result);
        }
    }
}
