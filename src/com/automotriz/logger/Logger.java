package com.automotriz.logger;

import com.automotriz.Presentacion.ReadProperties;
import java.io.*;
import java.util.*;

public class Logger {

    public static String sThis;
    private static String logFile;
    private final static String dirName = "logs";
    private static boolean debugMode;

    /**
     * starts reading the application.properties file
     */
    public static void start() {
        ReadProperties.loadApplicationProps();
        debugMode = Boolean.parseBoolean(ReadProperties.props.getProperty("mode.debug"));
        new File(dirName).mkdir();
        logFile = dirName + "/" + getDateFileName() + "Log.txt";

    }

    /**
     * Writes a log message into this directory: 'logs/'
     *
     * @param context context to set
     */
    public static void log(String err) {
        if (debugMode) {
            try {
                String date = getDate();
                FileWriter fw = new FileWriter(logFile, new File(logFile).exists());
                fw.write(date + " - INFO - " + err + "\n");
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes a error message into this directory: 'logs/' the main reason why
     * the error occurred, will be display by this method One line will be
     * written
     *
     * @param context err that will be in the log
     */
    public static void error(String err) {
        if (debugMode) {
            try {
                String date = getDate();
                FileWriter fw = new FileWriter(logFile, new File(logFile).exists());
                fw.write(date + " - ERROR - " + err + "\n");
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes the complete error message into this directory: 'logs/' many lines
     * will be written
     *
     * @param context err that will be in the log
     */
    public static void error(StackTraceElement[] err) {
        if (debugMode) {
            try {
                String completeErr = "";
                for (StackTraceElement st : err) {
                    completeErr += st.toString() + "\n";
                }
                FileWriter fw = new FileWriter(logFile, new File(logFile).exists());
                fw.write("\t\t" + completeErr);
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String getDate() {
        Calendar c = new GregorianCalendar();
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return "[" + day + "/" + month + "/" + year + "  " + hour + ":"
                + (minute >= 0 && minute <= 9 ? "0" + minute : minute) + ":" + second + "]";
    }

    private static String getDateFileName() {
        return getDate().replace("/", "").replace(" ", "").replace(":", "");
    }
}
