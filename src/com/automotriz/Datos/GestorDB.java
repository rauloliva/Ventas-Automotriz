package com.automotriz.Datos;

import com.automotriz.VO.AutoVO;
import com.automotriz.VO.ComentarioVO;
import com.automotriz.VO.UsuarioVO;
import java.sql.*;
import com.automotriz.logger.Logger;
import com.automotriz.logger.LoggerQuery;
import org.json.simple.JSONObject;
import com.automotriz.Constantes.Constants;
import com.automotriz.Negocio.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * Sends the query to GestorDB so it can be executed
     *
     * @return An instance of Response that contains what the query
     * returns 
     */
    public Response executeQuery() {
        Conexion conexion = new Conexion();

        if (conexion.getConnectionStatus() == Constants.CONEXION_SUCCESS) {
            cnn = conexion.getConnection();
            Logger.log("Connection Success");
            LoggerQuery.logQuery(this.query);
            switch (this.query.charAt(0)) {
                case Constants.SELECT:
                    return SELECT();
                case Constants.INSERT:
                    return INSERT();
                case Constants.UPDATE:
                    return UPDATE();
//                default:
//                    return DBTables(conexion);
            }
        }
        Logger.error("Connection Failure");
        return null;
    }
    
    /**
     * Executes a SELECT SQL query
     * 
     * @return the data fetched by the query
     * in an instance of Response
     */
    private Response SELECT() {
        Response response = new Response();
        try {
            Logger.log("Applying SELECT query");
            ps = cnn.prepareStatement(query);
            rs = ps.executeQuery();
            ResultSet rsTemp = rs;
            int rowsFetched = resultSetCount(rsTemp);
            
            response.setResultSet(rs);
            response.setRowsFetched(rowsFetched);
            response.setStatus( rowsFetched > 0 ? Response.STATUS_SUCCESS : Response.STATUS_RESULTSET_EMPTY);
            return response;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            response.setStatus(Response.STATUS_FAILURE);
            return response;
        }
    }
    
    /**
     * Executes a INSERT SQL query
     * 
     * @return Response.STATUS_INSERTED if the data was inserted
     * or Response.STATUS_DID_NOT_INSERTED if the data could not 
     * be inserted
     */
    private Response INSERT() {
        Response response = new Response();
        try {
            Logger.log("Applying INSERT query");
            ps = cnn.prepareStatement(query);
            int res = ps.executeUpdate();
            response.setStatus((res > 0) ? Response.STATUS_INSERTED : Response.STATUS_DID_NOT_INSERTED);
            return response;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            response.setStatus(Response.STATUS_FAILURE);
            return response;
        }
    }
    
    /**
     * Executes an UPDATE SQL query
     * 
     * @return Response.STATUS_UPDATED if the data was updated
     * or Response.STATUS_DID_NOT_UPDATE if the data could not 
     * be updated
     */
    private Response UPDATE() {
        Response response = new Response();
        try {
            Logger.log("Applying UPDATE query");
            ps = cnn.prepareStatement(query);
            int res = ps.executeUpdate();
            response.setStatus((res > 0) ? Response.STATUS_UPDATED : Response.STATUS_DID_NOT_UPDATE);
            return response;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            response.setStatus(Response.STATUS_FAILURE);
            return response;
        }
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
     *
     * @param conexion
     * @return
     */
    private JSONObject DBTables(Conexion conexion) {
        cnn = conexion.getConnection();
        try {
            Logger.log("Quering DataBase TablesS");
            rs = cnn.getMetaData().getTables("ventas_automotriz", null, "%", new String[]{"TABLE"});
            List<String> tables = new ArrayList();
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
            HashMap data = getDataByTable(tables, cnn);
            return createResponseJSON(new Object[]{data});
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
     *
     * @param tables
     * @param cnn
     * @return
     * @throws Exception
     */
    private HashMap getDataByTable(List<String> tables, Connection cnn) throws Exception {
        HashMap allData = new HashMap();
        for (String table : tables) {
            ps = cnn.prepareStatement("select * from " + table);
            rs = ps.executeQuery();
            ResultSet rsTemp = rs;
            int rowsFetched = resultSetCount(rsTemp);

            Object[] obj = new Object[rowsFetched];
            int i = 0;
            while (rs.next()) {
                if (table.equals("usuarios")) {
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
                } else if (table.equals("autos")) {
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
                } else if (table.equals("comentarios")) {
                    obj[i] = new ComentarioVO(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("comentario"),
                            rs.getInt("valoracion"),
                            rs.getInt("id_usuario"),
                            rs.getString("fecha")
                    );
                }
                i++;
            }
            allData.put(table, obj);
        }
        return allData;
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
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            //rs.close();
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
