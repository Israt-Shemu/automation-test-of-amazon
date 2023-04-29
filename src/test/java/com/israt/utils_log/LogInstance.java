package com.israt.utils_log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogInstance {
    // Initialize Log4j instance
  private static final Logger LOGGER =LogManager.getLogger(LogInstance.class);

    //info level logs
    public static void info(String message) {

        LOGGER.info(message);
    }

    //warn level logs
    public static void warn(String message) {

        LOGGER.warn(message);
    }

    //error level log
    public static void error(String message) {

        LOGGER.error(message);
    }

    //fatal_error level logs
    public static void fatal(String message) {

        LOGGER.fatal(message);
    }

    //debug level logs
    public static void debug(String message) {
        LOGGER.debug(message);
    }
}
