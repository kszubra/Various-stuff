package org.coursesandsandbox.differencefinder.clustering;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClusterRecord {
    private final String description;
    private final Map<String, Double> features;
}
