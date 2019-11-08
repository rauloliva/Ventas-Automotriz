package com.automotriz.Constantes;

import com.automotriz.Presentacion.Frame_AddComentario;
import com.automotriz.Presentacion.ReadProperties;
import javax.swing.ImageIcon;

/**
 * Global variables
 *
 * @author oliva
 * @param <Class>
 */
public interface Constants<Class> {

    //status of the DB Connection
    int CONEXION_SUCCESS = 0;

    int CONEXION_FAILURE = 1;
    //status of executing a query
    int QUERY_SUCCESS = 0;

    int QUERY_FAILURE = 1;
    //status of fetching data after executing a query
    int QUERY_GOT_NOTHING = 10;

    int QUERY_GOT_SOMETHING = 11;
    //query acccions
    char SELECT = 'S';

    char INSERT = 'I';

    char UPDATE = 'U';
    //
    String CORREO_PRINCIPAL = ReadProperties.props.getProperty("main.mail");

    String PWD_PRINCIPAL = ReadProperties.props.getProperty("main.mail.pwd");

    //The file that contains the SQL queries
    String JSON_FILE = ReadProperties.props.getProperty("operations.path");
    
    //Logs file path
    String LOGS_DIR_NAME = ReadProperties.props.getProperty("logs.dir");
    
    //
    int COMMENTS_PER_PAGE = 4;

    int AUTOS_PER_PAGE = 12;
    //
    Object STAR_FILLED = new ImageIcon(Frame_AddComentario.class.getResource(
            ReadProperties.props.getProperty("icon.filledStart")));

    Object STAR_EMPTY = new ImageIcon(Frame_AddComentario.class.getResource(
            ReadProperties.props.getProperty("icon.emptyStart")));
    //properties for hashing the strings
    String UNICODE_FORMAT = ReadProperties.props.getProperty("hash.format");

    String SCHEME = ReadProperties.props.getProperty("hash.scheme");

    String KEY = ReadProperties.props.getProperty("hash.key");
    //The path where the reports are placed
    String REPORT_DIR = ReadProperties.props.getProperty("reports.path");
    //number of fields in UsuarioVO
    int NUM_FIELDS = 6;

    public void initFrame(Class c);
    
    public GlobalMethods metohds = new GlobalMethods();
}


