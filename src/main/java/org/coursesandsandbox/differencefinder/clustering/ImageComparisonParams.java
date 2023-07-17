package org.coursesandsandbox.differencefinder.clustering;

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
