package de.mathisburger.ui;

import de.mathisburger.ConfigurationManager;
import de.mathisburger.api.FileCopyIF;

import java.io.File;

public class ConsoleUI {

    private final FileCopyIF fileCopy;

    public ConsoleUI() {
        this.fileCopy = ConfigurationManager.getFileCopy();
    }

    public void run() {
        fileCopy.copyFile(new File("src.txt"), new File("target.txt"));
    }
}
