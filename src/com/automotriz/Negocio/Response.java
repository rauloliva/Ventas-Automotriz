package com.automotriz.Negocio;

import java.sql.*;

public class Response {

    private int status;
    private ResultSet rs = null;
    private int rowsFetched = 0;
    private Connection cnn;
    private Object obj;

    public static int STATUS_SUCCESS = 1;
    public static int STATUS_FAILURE = 2;
    public static int STATUS_RESULTSET_EMPTY = 3;
    public static int STATUS_UPDATED = 4;
    public static int STATUS_DID_NOT_UPDATE = 5;
    public static int STATUS_INSERTED = 6;
    public static int STATUS_DID_NOT_INSERTED = 7;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setResultSet(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSet getResultSet() {
        return rs;
    }
    
    public void setRowsFetched(int rowsFetched){
        this.rowsFetched = rowsFetched;
    }
    
    public int getRowsFetched(){
        return rowsFetched;
    }
    
    public void setConnection(Connection cnn){
        this.cnn = cnn;
    }
    
    public Connection getConnection(){
        return this.cnn;
    }
    
    public void setObject(Object obj){
        this.obj = obj;
    }
    
    public Object getObject(){
        return this.obj;
    }
}
