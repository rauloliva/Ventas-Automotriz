package com.automotriz.logger;

import com.automotriz.Constantes.Constants;
import com.automotriz.Presentacion.ReadProperties;
import java.io.File;
import java.io.FileWriter;

public class LoggerQuery {

    private static String logFile;
    private static boolean debugMode;

    /**
     * starts reading the application.properties file
     */
    public static void start() {
        debugMode = Boolean.parseBoolean(ReadProperties.props.getProperty("mode.query.debug"));
        new File(Constants.LOGS_DIR_NAME).mkdir();
        logFile = Constants.LOGS_DIR_NAME + "/" + "LogQuery.txt";

        File file = new File(logFile);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Writes a SQL query into this directory: 'logs/' @dirName
     *
     * @param query query to write
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
