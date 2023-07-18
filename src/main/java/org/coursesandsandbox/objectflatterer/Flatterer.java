package org.coursesandsandbox.objectflatterer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO: improve: make more generic
public class Flatterer {

    public static List<ResultCategory> flattenCategory(String id, String idPath, String namePath, List<Category> children, String lang) {
        List<ResultCategory> bigResult = new ArrayList<>();
        bigResult.add(new ResultCategory(id, idPath, lang, namePath));
        if(Objects.nonNull(children) && !children.isEmpty()) {
            for(Category category : children) {
                List<ResultCategory> smallResult = flattenCategory(
                        category.getCategoryId(),
                        idPath.concat("/").concat(category.getCategoryId()),
                        namePath.concat(" ").concat(category.getNames().get(lang)),
                        category.getSubcategories(),
                        lang
                );
                bigResult.addAll(smallResult);
            }
        }
        return bigResult;
    }
}
