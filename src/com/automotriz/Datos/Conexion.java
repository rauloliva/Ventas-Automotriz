package com.automotriz.Datos;

import java.sql.*;
import com.automotriz.Presentacion.ReadProperties;
import com.automotriz.logger.Logger;
import com.automotriz.Constantes.Constants;

public class Conexion {

    private Connection cnn;
    private final int connectionStatus;

    public Conexion() {
        Logger.log("Connecting to Database");
        connectionStatus = createNewConnection();
    }

    /**
     * By default creates a database connection with Mysql8.0
     * if the connection fails, attempts connecting through Mysql5.0
     * @return The status of the connection 
     */
    private int createNewConnection() {
        try {
            String[] credentials = readCredentials();
            Class.forName(credentials[5]);

            cnn = DriverManager.getConnection(
                    "jdbc:mysql://" + credentials[0] + ":" + credentials[1] + "/" + credentials[2]
                    + "?useTimezone=true&serverTimezone=UTC",
                    credentials[3],
                    credentials[4]);
            return Constants.CONEXION_SUCCESS;
        } catch (Exception e) {
            Logger.error(e.toString());
            Logger.error(e.getStackTrace());

            //trying to connect with version 5
            try {
                return createConnectionV5();
            } catch (Exception ex) {
                Logger.error(ex.toString());
                Logger.error(ex.getStackTrace());
                return Constants.CONEXION_FAILURE;
            }
        }
    }

    /**
     * Create a database connection through Mysql5.0
     * @return The status of the connection
     * @throws Exception if the connection fails
     */
    private int createConnectionV5() throws Exception {
        String[] credentials = readCredentialsV5();
        Class.forName(credentials[5]);

        cnn = DriverManager.getConnection(
                "jdbc:mysql://" + credentials[0] + ":" + credentials[1] + "/" + credentials[2]
                + "?useTimezone=true&serverTimezone=UTC",
                credentials[3],
                credentials[4]);
        return Constants.CONEXION_SUCCESS;
    }

    /**
     * Read the properties for the connection through Mysql8.0
     * @return the properties gathered in a String array
     */
    private static String[] readCredentials() {
        Logger.log("Reading DB's credentials for version 8");
        return new String[]{
            ReadProperties.props.getProperty("db.host"),
            ReadProperties.props.getProperty("db.port"),
            ReadProperties.props.getProperty("db.database"),
            ReadProperties.props.getProperty("db.username"),
            ReadProperties.props.getProperty("db.pwd"),
            ReadProperties.props.getProperty("db.driver")
        };
    }

    /**
     * Read the properties for the connection through Mysql5.0
     * @return the properties gathered in a String array
     */
    private static String[] readCredentialsV5() {
        Logger.log("Reading DB's credentials for version 5");
        return new String[]{
            ReadProperties.props.getProperty("db.host"),
            ReadProperties.props.getProperty("db.port"),
            ReadProperties.props.getProperty("db.database"),
            ReadProperties.props.getProperty("db.username"),
            ReadProperties.props.getProperty("db.pwd.v5"),
            ReadProperties.props.getProperty("db.driver.v5")
        };
    }

    public int getConnectionStatus() {
        return connectionStatus;
    }

    public Connection getConnection() {
        return this.cnn;
    }
}
