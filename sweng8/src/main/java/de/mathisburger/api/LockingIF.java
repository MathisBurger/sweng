package de.mathisburger.api;

import java.io.File;
import java.io.IOException;

public interface LockingIF {
    void lockFile(File file) throws IOException;
    void unlock(File file);
}
