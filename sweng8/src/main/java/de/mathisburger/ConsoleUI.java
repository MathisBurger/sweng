package de.mathisburger.ui;

import de.mathisburger.ConfigurationManager;
import de.mathisburger.api.FileCopyIF;

import java.io.File;

public class ConsoleUI {


    public static void main(String[] args) {
        ConfigurationManager.getFileCopy().copyFile(new File("src.txt"), new File("target.txt"));
    }
}
