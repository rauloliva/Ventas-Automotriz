package com.automotriz.logger;

import com.automotriz.Presentacion.ReadProperties;
import java.io.File;
import java.io.FileWriter;

public class LoggerQuery {

    public static String sThis;
    private static String logFile;
    private final static String dirName = "logs";
    private static boolean debugMode;

    /**
     * starts reading the application.properties file
     */
    public static void start() {
        ReadProperties.loadApplicationProps();
        debugMode = Boolean.parseBoolean(ReadProperties.props.getProperty("mode.query.debug"));
        new File(dirName).mkdir();
        logFile = dirName + "/" + "LogQuery.txt";

        File file = new File(logFile);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Writes a SQL query into this directory: 'logs/'
     *
     * @param context context to set
     */
    public static void logQuery(String query) {
        if (debugMode) {
            try {
                FileWriter fw = new FileWriter(logFile, new File(logFile).exists());
                fw.write(" - QUERY - " + query + "\n");
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
