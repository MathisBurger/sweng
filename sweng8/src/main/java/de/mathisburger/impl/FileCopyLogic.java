package de.mathisburger.impl;

import de.mathisburger.api.FileCopyIF;
import de.mathisburger.api.LockingIF;
import de.mathisburger.api.LoggingIF;

import java.io.File;
import java.io.IOException;

public class FileCopyLogic implements FileCopyIF {

    private final LoggingIF logging;
    private final LockingIF locking;

    public FileCopyLogic(LoggingIF logging, LockingIF locking) {
        this.logging = logging;
        this.locking = locking;
    }

    @Override
    public void copyFile(File source, File target) {
        try {
            logging.log( "Obtaining file locks" );
            locking.lockFile( source );
            locking.lockFile( target );

            logging.log( "Starting file copy" );
            // Do the file copy here
            logging.log( "Files successfull copied" );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            locking.unlock( source );
            locking.unlock( target );
            logging.log( "Copy job finished" );
        }
    }
}
