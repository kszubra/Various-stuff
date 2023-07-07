package org.example.differencefinder.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.differencefinder.clustering.Centroid;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ClusterRectangle {
    private double x;
    private double y;
    private double width;
    private double height;
    private Centroid centroid;
}
