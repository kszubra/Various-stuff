package org.example.differencefinder;

import org.example.differencefinder.clustering.ImageComparisonParams;
import org.example.differencefinder.utils.DifferenceColoringUtils;
import org.example.differencefinder.utils.FileManagementUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

public class ImageDifferenceFinder {
    public static String BASE_RESOURCE_PATH = "src/main/resources/differencefinder/%s";
    public static String BASE_RESUlT_PATH = "src/main/resources/differencefinder/results/";
    public static String PIXES = "pixels";
    public static String RECTANGLES = "rectangles";

    public static void main(String[] args) {
        String filename1 = "a.jpg";
        String filename2 = "b.jpg";

        perform(filename1, filename2, new ImageComparisonParams(0.1, 15, 1000));

    }

    private static void perform(String art1Name, String art2Name, ImageComparisonParams params) {
        BufferedImage bi1 = FileManagementUtils.readImageFromResources(String.format(BASE_RESOURCE_PATH, art1Name));
        BufferedImage bi2 = FileManagementUtils.readImageFromResources(String.format(BASE_RESOURCE_PATH, art2Name));

        File resultPixels = new File(getResultsFilename(PIXES, params));
        File resultRectangles = new File(getResultsFilename(RECTANGLES, params));

        Instant start = Instant.now();
        new DifferenceColoringUtils().markPixels(bi1, bi2, resultPixels, params.getTolerance());
        Instant stop = Instant.now();
        Duration durationPixels = Duration.between(start, stop);

        start = Instant.now();
        new DifferenceColoringUtils().markRectangles(bi1, bi2, resultRectangles, params.getTolerance(), params.getNumberOfClusters(), params.getMaxIterations());
        stop = Instant.now();

        Duration durationRectangles = Duration.between(start, stop);

        System.out.println("Pixels finished in: " + durationPixels.getSeconds() + " seconds");
        System.out.println("Rectangles finished in: " + durationRectangles.getSeconds() + " seconds");
    }

    private static String getResultsFilename(String type, ImageComparisonParams params) {
        String timestamp =new Timestamp(System.currentTimeMillis()).toString()
                .replace("-", "")
                .replace(":", "")
                .replace(" ", "")
                .substring(0, 14);

        return new StringBuilder()
                .append(BASE_RESUlT_PATH)
                .append("/")
                .append(type)
                .append("_")
                .append(timestamp)
                .append("_")
                .append("tolerance")
                .append("_")
                .append(params.getTolerance())
                .append("_")
                .append("clusters")
                .append("_")
                .append(params.getNumberOfClusters())
                .append("_")
                .append("iterations")
                .append("_")
                .append(params.getMaxIterations())
                .append(".jpg")
                .toString();
    }
}
