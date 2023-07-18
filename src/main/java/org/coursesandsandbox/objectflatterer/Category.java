package org.coursesandsandbox.objectflatterer;

import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Category {

    private String categoryId;

    private Map<String, String> names;

    private List<Category> subcategories;
}
