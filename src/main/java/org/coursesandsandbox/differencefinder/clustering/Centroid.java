package org.coursesandsandbox.differencefinder.clustering;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Centroid {

    private final Map<String, Double> coordinates;
}
