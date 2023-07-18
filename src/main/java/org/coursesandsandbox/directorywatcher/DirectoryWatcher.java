package org.coursesandsandbox.directorywatcher;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardWatchEventKinds.*;

public class DirectoryWatcher {
    public static final String LANDING_DIRECTORY = "src/main/resources/directorywatcher";
    public static final String IMAGES_DIRECTORY = LANDING_DIRECTORY + "/IMAGES";
    public static final String ANIMATIONS_DIRECTORY = LANDING_DIRECTORY + "/ANIMATIONS";
    public static final String VIDEOS_DIRECTORY = LANDING_DIRECTORY + "/VIDEOS";
    public static final String[] DIRECTORIES = {LANDING_DIRECTORY, IMAGES_DIRECTORY, ANIMATIONS_DIRECTORY, VIDEOS_DIRECTORY};
    public static final List<String> IMAGE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "bmp");
    public static final List<String> ANIMATION_EXTENSIONS = Arrays.asList("gif", "swf");
    public static final List<String> VIDEO_EXTENSIONS = Arrays.asList("vlc", "mov", "mp4", "avi", "mpg", "webm");

    public static void main(String[] args)  {
        createDirectories();
        runWatcher();
    }

    private static void runWatcher() {
        Path homeFolder = Paths.get(LANDING_DIRECTORY);
        WatchService homeWatchService;
        try {
            homeWatchService = FileSystems.getDefault().newWatchService();
            homeFolder.register(homeWatchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        } catch (IOException e) {
            System.out.println(String.format("Error when creating watch service: %s", e.getMessage()));;
            return;
        }

        boolean valid = true;
        do {
            WatchKey homeWatchKey;

            try {
                homeWatchKey = homeWatchService.take();
            } catch (InterruptedException e) {
                System.out.println(String.format("Exception when getting watch key: %s", e.getMessage()));;
                return;
            }

            for (WatchEvent<?> event : homeWatchKey.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (ENTRY_CREATE.equals(kind)) {
                    String filePath = LANDING_DIRECTORY + "/" + event.context().toString();
                    System.out.println("New file detected: " + filePath);
                    sleepThreadFor(2000);
                    moveFile(filePath, getProperDirectory(event.context().toString()));
                } else if (ENTRY_DELETE.equals(kind)) {
                    String filePath = LANDING_DIRECTORY + "/" + event.context().toString();
                    System.out.println("File deleted: " + filePath);
                } else if (ENTRY_MODIFY.equals(kind)) {
                    String filePath = LANDING_DIRECTORY + "/" + event.context().toString();
                    System.out.println("File modified: " + filePath);
                }
            }
            valid = homeWatchKey.reset();

        } while (valid);
    }

    private static void createDirectories() {
        for(String dir : DIRECTORIES) {
            File file = new File(dir);
            if(!file.exists()) {
                if(!file.mkdir()) {
                    System.out.println("Failed to create directory: " + dir);
                }
            }
        }
    }

    private static void moveFile(String source, String destination) {
        Path result = null;
        CopyOption[] options = new CopyOption[] {REPLACE_EXISTING};
        try {
            result =  Files.move(Paths.get(source), Paths.get(destination), options);
        } catch (IOException e) {
            System.out.println("Exception while moving file: " + e.getMessage());
        }
        if(result != null) {
            System.out.println("File moved successfully.");
        }else{
            System.out.println("File movement failed.");
        }
    }

    private static void sleepThreadFor(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Failed to sleep thread: " + e.getMessage());
        }
    }

    private static Boolean fileIsUsed(String path) {
        String system = System.getProperty("os.name").toLowerCase();
        try(FileOutputStream fos = new FileOutputStream(new File(path))) {
            return false;
        } catch(IOException e) {
            return true;
        }
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    private static String getProperDirectory(String filename) {
        String extension = getFileExtension(filename);
        if (IMAGE_EXTENSIONS.contains(extension)) {
            return IMAGES_DIRECTORY + "/" + filename;
        } else if (ANIMATION_EXTENSIONS.contains(extension)) {
            return ANIMATIONS_DIRECTORY + "/" + filename;
        } else if (VIDEO_EXTENSIONS.contains(extension)) {
            return VIDEOS_DIRECTORY + "/" + filename;
        } else {
            System.out.println("Unknown file extension: " + extension);
            throw new RuntimeException();
        }
    }

}
