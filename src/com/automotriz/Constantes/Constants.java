package com.automotriz.Constantes;

import com.automotriz.Presentacion.Frame_AddComentario;
import com.automotriz.Presentacion.ReadProperties;
import javax.swing.ImageIcon;

/**
 * Global constants variables
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
    
    //values used to send an email
    String CORREO_PRINCIPAL = ReadProperties.props.getProperty("main.mail");

    String PWD_PRINCIPAL = ReadProperties.props.getProperty("main.mail.pwd");

    //The file that contains the SQL queries
    String JSON_FILE = ReadProperties.props.getProperty("operations.path");

    //Logs file path
    String LOGS_DIR_NAME = ReadProperties.props.getProperty("logs.dir");

    int COMMENTS_PER_PAGE = 4;

    int AUTOS_PER_PAGE = 12;
    
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

    //Vehicle's Status
    public String PROCCESS_PURCHASE = "PROCCESS_PURCHASE";
    public String DELETED = "DELETED";

    //zip file
    public String ZIP_NAME = ReadProperties.props.getProperty("zip.name");
    public String BACKUP_FILES_EXTENSION = ReadProperties.props.getProperty("file.extension");

    public String SENDMAIL = "SENDMAIL";
    
    // DATABASE ACTIONS
    public String VALIDATEUSER = "VALIDATEUSER";
    public String USERNAMEEXISTS = "USERNAMEEXISTS";
    public String UPDATEINTENTOS = "UPDATEINTENTOS";
    public String BLOCKUSER = "BLOCKUSER";
    public String VALIDATEUSERNAME = "VALIDATEUSERNAME";
    public String CREATENEWUSER = "CREATENEWUSER";
    public String VALIDATEADMIN = "VALIDATEADMIN";
    public String UPDATEUSER = "UPDATEUSER";
    public String REMOVEUSER = "REMOVEUSER";
    public String FILTRARUSUARIOS = "FILTRARUSUARIOS";
    public String FILTRARTODOSUSUARIOS = "FILTRARTODOSUSUARIOS";
    public String ACTIVARUSER = "ACTIVARUSER";
    public String INSERTNEWAUTO = "INSERTNEWAUTO";
    public String GETCARS = "GETCARS";
    public String UPDATEAUTO = "UPDATEAUTO";
    public String SUBMITCOMENTARIO = "SUBMITCOMENTARIO";
    public String GETFEEDBACK = "GETFEEDBACK";
    public String GETCATALOGO = "GETCATALOGO";
    public String GETVENDEDOR = "GETVENDEDOR";
    public String GETVENDEDORNAME = "GETVENDEDORNAME";
    public String FILTRARAUTOS = "FILTRARAUTOS";
    public String LISTAUTOSBYID = "LISTAUTOSBYID";
    public String UPDATEAUTOESTATUS = "UPDATEAUTOESTATUS";
    public String DB_CONNECTION = "DB_CONNECTION";
    public String TABLESSCHEMA = "TABLESSCHEMA";    
}
