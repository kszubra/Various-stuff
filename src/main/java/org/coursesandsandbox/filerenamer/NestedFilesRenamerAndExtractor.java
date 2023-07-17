package org.coursesandsandbox.filerenamer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import static org.apache.commons.io.FilenameUtils.getExtension;

public class NestedFilesRenamerAndExtractor {
    public static void main(String[] args) {
        String sd = "C:\\Users\\Kacper\\Desktop\\TLK_ultimate — kopia";
        HashSet<RenameResult> results = new HashSet();
        recursiveCheck(results, 0, "", sd, sd);

        List<RenameResult> failures = results.stream().filter(RenameResult::isFailed).toList();
        if(!failures.isEmpty()) {
            for(RenameResult result : failures) {
                System.out.println(String.format("Failed to process file: %s with exception: %s", result.getFileName(), result.getException()));
            }
        }
        System.out.println("Failed files: " + failures.size());

    }
    public static HashSet<RenameResult> recursiveCheck(HashSet<RenameResult> results, int dirNumber, String previousPath, String startDirectory, String dirToCheck) {
        File curDirectory = new File(dirToCheck);
        System.out.println("--------------------------------------------------------------------------NEW DIRECTORY CHECK: " + curDirectory.getName() + " --------------------------------------------------------------------------");
        List<File> singleFiles = Arrays.stream(Objects.requireNonNull(curDirectory.listFiles()))
                .filter(File::isFile)
                .sorted(Comparator.naturalOrder())
                .toList();
        List<File> dirFiles = Arrays.stream(Objects.requireNonNull(curDirectory.listFiles()))
                .filter(File::isDirectory)
                .toList();
        System.out.println("Single files: " + singleFiles.size());
        System.out.println("Directory files: " + dirFiles.size());

        System.out.println("------------------------- HANDLING SINGLE FILES -----------------------");
        for(int fileNumber = 0; fileNumber < singleFiles.size(); fileNumber++) {
            File file = singleFiles.get(fileNumber);
            String fileExtension = getExtension(file.getName());
            String newFileName = String.format("%s%s_%s.%s",
                    previousPath.isBlank() ? previousPath : previousPath.concat("_"),
                    dirNumber,
                    fileNumber,
                    fileExtension);
            results.add(renameAndMove(file, newFileName, startDirectory));
        }

        for(int directoryNumber = 0; directoryNumber < dirFiles.size(); directoryNumber++) {
            recursiveCheck(results, directoryNumber, previousPath.concat("_" + directoryNumber), startDirectory, dirFiles.get(directoryNumber).getPath());
        }

        return results;
    }

    public static RenameResult renameAndMove(File file, String newName, String moveDir) {
        System.out.println(String.format("Renaming file %s to %s", file.getName(), newName));
        try {
            Path oldFile = Paths.get(file.getPath());
            Path newPath = Files.move(oldFile, oldFile.resolveSibling(newName));
            System.out.println("File renamed successfully");
            Files.move(newPath, Paths.get(String.format("%s\\%s", moveDir, newName)), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved to start directory successfully");
        }
        catch (Exception e) {
            System.out.println("operation failed: " + e);
            return new RenameResult(true, file.getPath(), e);
        }
        System.out.println("Moving file to start directory: ");
        return new RenameResult(false, file.getName(), null);
    }
}
