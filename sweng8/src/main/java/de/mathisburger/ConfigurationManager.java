package de.mathisburger;

import de.mathisburger.api.FileCopyIF;
import de.mathisburger.api.LockingIF;
import de.mathisburger.api.LoggingIF;
import de.mathisburger.impl.FileCopyLogic;
import de.mathisburger.impl.Locking;
import de.mathisburger.impl.Logging;

public class ConfigurationManager {


    public static LoggingIF getLogger() {
        return new Logging();
    }

    public static LockingIF getLocking() {
        return new Locking(ConfigurationManager.getLogger());
    }

    public static FileCopyIF getFileCopy() {
        return new FileCopyLogic(ConfigurationManager.getLogger(), ConfigurationManager.getLocking());
    }
}
