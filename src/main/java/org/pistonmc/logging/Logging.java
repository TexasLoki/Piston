package org.pistonmc.logging;

import static org.pistonmc.logging.LogLevel.INFO;

public class Logging {

    private static LogWriter writer;
    private static Logger global = getLogger((String) null);

    public static Logger getLogger(String name, Logger parent, LogLevel level) {
        Logger logger = new Logger(name, level);
        logger.setParent(parent);
        return logger;
    }

    public static Logger getLogger(Class<?> clazz, Logger parent, LogLevel level) {
        return getLogger(clazz.getSimpleName(), parent, level);
    }

    public static Logger getLogger(String name, Logger parent) {
        return getLogger(name, parent, INFO);
    }

    public static Logger getLogger(Class<?> clazz, Logger parent) {
        return getLogger(clazz.getSimpleName(), parent);
    }

    public static Logger getLogger(String name, LogLevel level) {
        return getLogger(name, global, level);
    }

    public static Logger getLogger(Class<?> clazz, LogLevel level) {
        return getLogger(clazz.getSimpleName(), level);
    }

    public static Logger getLogger(String name) {
        return getLogger(name, global);
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getSimpleName());
    }

    public static Logger getLogger() {
        return global;
    }

    public static LogWriter getWriter() {
        return writer;
    }

}
