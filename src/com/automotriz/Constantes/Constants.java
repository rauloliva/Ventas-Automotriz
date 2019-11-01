package com.automotriz.Constantes;

import com.automotriz.Presentacion.Frame_AddComentario;
import com.automotriz.Presentacion.ReadProperties;
import javax.swing.ImageIcon;

/**
 * Global variables
 * @author oliva
 */
public interface Constants {
    //
    int CONEXION_SUCCESS = 0;
    int CONEXION_FAILURE = 1;
    
    ///
    int QUERY_SUCCESS = 0;
    int QUERY_FAILURE = 1;

    //
    int QUERY_GOT_NOTHING = 10;
    int QUERY_GOT_SOMETHING = 11;

    //
    char SELECT = 'S';
    char INSERT = 'I';
    char UPDATE = 'U';
    
    //
    String CORREO_PRINCIPAL = ReadProperties.props.getProperty("main.mail");
    String PWD_PRINCIPAL = ReadProperties.props.getProperty("main.mail.pwd");
    
    /**
     * The file that contains the SQL queries
     */
    String JSON_FILE = ReadProperties.props.getProperty("operations.path");
    
    int COMMENTS_PER_PAGE = 4;
    int AUTOS_PER_PAGE = 12;
    
    Object STAR_FILLED = new ImageIcon(Frame_AddComentario.class.getResource(
            ReadProperties.props.getProperty("icon.filledStart")));
    Object STAR_EMPTY = new ImageIcon(Frame_AddComentario.class.getResource(
            ReadProperties.props.getProperty("icon.emptyStart")));
    
    String UNICODE_FORMAT = ReadProperties.props.getProperty("hash.format");
    String SCHEME = ReadProperties.props.getProperty("hash.scheme");
    String KEY = ReadProperties.props.getProperty("hash.key");
    
    String REPORT_DIR = "Reports/";
    
    //number of fields in UsuarioVO
    int NUM_FIELDS = 6;
}
