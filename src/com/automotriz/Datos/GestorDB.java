package com.automotriz.Datos;

import com.automotriz.VO.AutoVO;
import com.automotriz.VO.ComentarioVO;
import com.automotriz.VO.UsuarioVO;
import java.sql.*;
import com.automotriz.logger.Logger;
import com.automotriz.logger.LoggerQuery;
import org.json.simple.JSONObject;
import com.automotriz.Constantes.Constants;

public class GestorDB {
    
    private String query;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection cnn;
    private Object objVO;
    
    public GestorDB() {
    }

    /**
     * Set the object where the data will be saved after retrieving from
     * database
     *
     * @param objVO The Object VO
     */
    public void setObjectVO(Object objVO) {
        this.objVO = objVO;
    }

    /**
     * Initializing this constructor with a query statement that will be
     * executed
     *
     * @param query The query statement that could be either insert, select or
     * update
     */
    public GestorDB(String query) {
        this.query = query;
    }

    /**
     * Place the query String into a private attribute so the query only belongs
     * to the GestorDB object
     *
     * @param query The SQL query to execute
     */
    public void putQuery(String query) {
        this.query = query;
    }
    
    public static Connection sendSQLConnection() {
        Logger.log("Requesting the database connection");
        Connection con = null;
        Conexion conexion = new Conexion();
        if (conexion.getConnectionStatus() == Constants.CONEXION_SUCCESS) {
            con = conexion.getConnection();
            Logger.log("Retunning the database connection");
        }
        return con;
    }

    /**
     * Sends the query to GestorDB so it can be executed
     *
     * @param request The JSON object that acts as a request to the server
     * @return A JSON object that acts as a response to the client
     */
    public JSONObject sendQuery(JSONObject request) {
        Conexion conexion = new Conexion();
        
        if (conexion.getConnectionStatus() == Constants.CONEXION_SUCCESS) {
            Logger.log("Connection Success");
            LoggerQuery.logQuery(this.query);
            switch (this.query.charAt(0)) {
                case Constants.SELECT:
                    return SELECT(conexion);
                case Constants.INSERT:
                    return INSERT(conexion, request);
                case Constants.UPDATE:
                    return UPDATE(conexion, request);
                default:
                    return new JSONObject();
            }
        }
        Logger.error("Connection Failure");
        return null;
    }

    /**
     * Creates a JSON object that will be sent as a response to the client
     *
     * @param values Values captured from a SQL SELECT query
     * @return A new JSON object that contains the response that will be sent to
     * the client
     */
    private JSONObject createResponseJSON(Object[] values) {
        JSONObject response = new JSONObject();
        if (values == null || values.length == 0) {
            response.put("estatus", Constants.QUERY_GOT_NOTHING);
            return response;
        }
        response.put("obj", values);
        response.put("estatus", Constants.QUERY_GOT_SOMETHING);
        return response;
    }

    /**
     * Executes a SELECT SQL query
     *
     * @param conexion The database connection
     * @return A JSON object that acts as a response created by the
     * 'createResponseJSON' method
     */
    private JSONObject SELECT(Conexion conexion) {
        cnn = conexion.getConnection();
        try {
            Logger.log("Applying SELECT query");
            ps = cnn.prepareStatement(query);
            rs = ps.executeQuery();
            ResultSet rsTemp = rs;
            int rowsFetched = resultSetCount(rsTemp);
            
            Object[] obj = new Object[rowsFetched];
            int i = 0;
            while (rs.next()) {
                if (objVO instanceof UsuarioVO) {
                    obj[i] = new UsuarioVO(
                            rs.getInt("id"),
                            rs.getString("usuario"),
                            rs.getString("contrasena"),
                            rs.getString("correo"),
                            rs.getString("perfil"),
                            rs.getString("estatus"),
                            rs.getString("telefono"),
                            rs.getString("nombre"),
                            rs.getString("permisos")
                    );
                } else if (objVO instanceof AutoVO) {
                    obj[i] = new AutoVO(
                            rs.getInt("id"),
                            rs.getInt("modelo"),
                            rs.getString("imagenes"),
                            rs.getInt("kilometros"),
                            rs.getString("descripcion"),
                            rs.getString("marca"),
                            rs.getString("cambio"),
                            rs.getDouble("precio"),
                            rs.getString("color"),
                            rs.getString("estatus"),
                            rs.getInt("id_usuario")
                    );
                } else if (objVO instanceof ComentarioVO) {
                    obj[i] = new ComentarioVO(
                            rs.getString("nombre"),
                            rs.getString("comentario"),
                            rs.getInt("valoracion"),
                            rs.getString("fecha")
                    );
                }
                i++;
            }
            return createResponseJSON(obj);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            return new JSONObject();
        } finally {
            try {
                closeConnection();
            } catch (Exception e) {
                Logger.error(e.toString());
                Logger.error(e.getStackTrace());
            }
        }
    }

    /**
     * Executes a INSERT SQL query
     *
     * @param conexion The database connection
     * @param request The JSON request that acts as a request
     * @return A JSON object that acts as a response created by the
     * 'createResponseJSON' method
     */
    private JSONObject INSERT(Conexion conexion, JSONObject request) {
        cnn = conexion.getConnection();
        try {
            Logger.log("Applying INSERT query");
            ps = cnn.prepareStatement(query);
            int res = ps.executeUpdate();
            request.put("response", (res > 0) ? Constants.QUERY_SUCCESS : Constants.QUERY_FAILURE);
            return request;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            return new JSONObject();
        } finally {
            try {
                closeConnection();
            } catch (Exception e) {
                Logger.error(e.toString());
                Logger.error(e.getStackTrace());
            }
        }
    }

    /**
     * Executes a UPDATE SQL query
     *
     * @param conexion The database connection
     * @param request The JSON request that acts as a request
     * @return A JSON object that acts as a response created by the
     * 'createResponseJSON' method
     */
    private JSONObject UPDATE(Conexion conexion, JSONObject request) {
        cnn = conexion.getConnection();
        try {
            Logger.log("Applying UPDATE query");
            ps = cnn.prepareStatement(query);
            int res = ps.executeUpdate();
            request.put("response", (res > 0) ? Constants.QUERY_SUCCESS : Constants.QUERY_FAILURE);
            return request;
            
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            return new JSONObject();
        } finally {
            try {
                closeConnection();
            } catch (Exception e) {
                Logger.error(e.toString());
                Logger.error(e.getStackTrace());
            }
        }
    }

    /**
     * Close the database connection, as well close the preparedStatement object
     * and the ResultSet object
     *
     * @throws Exception if an error occurs when closing Connection,
     * PreparedStatement and ResultSet
     */
    private void closeConnection() throws Exception {
        Logger.log("Closing Connection");
        cnn.close();
        ps.close();
        if (rs != null) {
            rs.close();
        }
    }

    /**
     * Returns the total of queries got from a SELECT query
     *
     * @param res the resultSet object
     * @return the total of queries
     * @throws Exception
     */
    private int resultSetCount(ResultSet res) throws Exception {
        int size = 0;
        try {
            res.last();
            size = res.getRow();
            res.beforeFirst();
        } catch (Exception ex) {
            Logger.error(ex.getMessage());
            Logger.error(ex.getStackTrace());
            return 0;
        }
        return size;
    }
}
