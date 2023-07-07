package org.example.differencefinder.clustering;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ImageComparisonParams {

    @Getter
    double tolerance;
    @Getter
    int numberOfClusters;
    @Getter
    int maxIterations;

}
