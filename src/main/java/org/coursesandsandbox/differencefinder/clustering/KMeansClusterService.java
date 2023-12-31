package org.coursesandsandbox.differencefinder.clustering;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toSet;

public class KMeansClusterService {

    public static Map<Centroid, List<ClusterRecord>> generateClusters(List<ClusterRecord> records, int numberOfClusters, int maxIterations) {
        Map<Centroid, List<ClusterRecord>> clusters = KMeans.fit(records, numberOfClusters, new EuclideanDistance(), maxIterations);
        clusters.forEach((key, value) -> {
            System.out.println("-------------------------- CLUSTER ----------------------------");

            System.out.println(sortedCentroid(key));
            String members = String.join(", ", value.stream()
                    .map(item -> String.format("%s[%s, %s]", item.getDescription(), item.getFeatures().get("x"), item.getFeatures().get("y")))
                    .collect(toSet()));
            System.out.print(members);

            System.out.println();
            System.out.println();
        });

        return clusters;
    }

    private static Centroid sortedCentroid(Centroid key) {
        List<Map.Entry<String, Double>> entries = new ArrayList<>(key
                .getCoordinates()
                .entrySet());
        entries.sort((e1, e2) -> e2
                .getValue()
                .compareTo(e1.getValue()));

        Map<String, Double> sorted = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : entries) {
            sorted.put(entry.getKey(), entry.getValue());
        }

        return new Centroid(sorted);
    }
}
