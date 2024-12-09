package de.mathisburger.impl;

import de.mathisburger.api.LockingIF;
import de.mathisburger.api.LoggingIF;

import java.io.File;
import java.io.IOException;

public class Locking implements LockingIF {

    private LoggingIF logging;

    public Locking(LoggingIF logging) {
        this.logging = logging;
    }

    @Override
    public void lockFile(File file) throws IOException {
        // Hier wird die Datei gelockt
        logging.log( "Obtaining lock for file " + file );
    }

    @Override
    public void unlock(File file) {
        // Hier wird die Datei unlocked
        logging.log( "Removing lock for file " + file );
    }
}
