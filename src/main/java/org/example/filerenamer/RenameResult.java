package org.example.filerenamer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RenameResult {
    @Getter
    private boolean failed;
    @Getter
    private String fileName;
    @Getter
    private Exception exception;


}
