package de.mathisburger.impl;

import de.mathisburger.api.LoggingIF;

public class Logging implements LoggingIF {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
