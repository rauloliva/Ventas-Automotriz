package com.automotriz.Presentacion;

import com.automotriz.logger.Logger;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    /**
     * The properties object that contains relevant properties of the software
     */
    public static Properties props;

    private ReadProperties() {
        try {
            InputStream in = new FileInputStream("application.properties");
            props = new Properties();
            props.load(in);
            System.out.println("Application properties Initialized");
        } catch (Exception e) {
            Logger.error(e.toString());
            Logger.error(e.getStackTrace());
        }
    }

    /**
     * Initialize the ReadProperties object, and start reading the properties
     * file
     */
    public static void loadApplicationProps() {
        if (props == null) {
            new ReadProperties();
        }
    }
}
