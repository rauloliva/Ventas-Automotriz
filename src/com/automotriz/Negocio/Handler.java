package com.automotriz.Negocio;

import com.automotriz.Datos.GestorDB;
import com.automotriz.Datos.Mail;
import com.automotriz.VO.UsuarioVO;
import java.io.File;

public class Handler {

    private String action;
    private String query;
    private Object data;

    public Handler(String action, Object data) {
        this.action = action;
        this.query = "";
        this.data = data;
    }

    public Handler(String action) {
        this.action = action;
        this.query = "";
    }

    public Response createNewOperation() {
        this.createNewQuery();
        return this.sendToGestorDB();
    }

    private Response sendToGestorDB() {
        GestorDB db = new GestorDB(this.query);
        return db.executeQuery();
    }

    /**
     * 
     */
    private void createNewQuery() {
        Queries queries = new Queries() {

            @Override
            public String VALIDATEUSER(UsuarioVO usuario) {
                return "SELECT * FROM usuarios "
                        + "WHERE usuario = '" + usuario.getUsuario() + "' "
                        + "AND contrasena = '" + usuario.getContraseña() + "'";
            }

            @Override
            public String USERNAMEEXISTS(UsuarioVO usuario) {
                return "SELECT id,usuario,intentos FROM usuarios "
                        + "WHERE usuario = '" + usuario.getUsuario() + "'";
            }

            @Override
            public String UPDATEINTENTOS(UsuarioVO usuario) {
                return "UPDATE usuarios SET "
                        + "intentos = " + usuario.getIntentos() + " WHERE id = " + usuario.getId();
            }

            @Override
            public String BLOCKUSER(UsuarioVO usuario) {
                return "UPDATE usuarios SET "
                        + "estatus = 'BLOCKED' WHERE id = " + usuario.getId();
            }

            @Override
            public String VALIDATEUSERNAME(UsuarioVO usuario) {
                return "SELECT usuario FROM usuarios "
                        + "WHERE usuario = '" + usuario.getUsuario() + "'";
            }

            @Override
            public String CREATENEWUSER() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String VALIDATEADMIN() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String UPDATEUSER() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String REMOVEUSER() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String FILTRARUSUARIOS() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String FILTRARTODOSUSUARIOS() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String ACTIVARUSER() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String INSERTNEWAUTO() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String GETCARS() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String UPDATEAUTO() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String SUBMITCOMENTARIO() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String GETFEEDBACK() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String GETCATALOGO() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String GETVENDEDOR() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String GETVENDEDORNAME() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String FILTRARAUTOS() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String LISTAUTOSBYID() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String UPDATEAUTOESTATUS() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        this.query = this.getQuery(queries);
    }

    /**
     * 
     * @param queries
     * @return 
     */
    private String getQuery(Queries queries) {
        switch (this.action) {
            case "VALIDATEUSER":
                return queries.VALIDATEUSER((UsuarioVO) this.data);
            case "USERNAMEEXISTS":
                return queries.USERNAMEEXISTS((UsuarioVO) this.data);
            case "UPDATEINTENTOS":
                return queries.UPDATEINTENTOS((UsuarioVO) this.data);
            case "BLOCKUSER":
                return queries.BLOCKUSER((UsuarioVO) this.data);
            case "VALIDATEUSERNAME":
                return queries.VALIDATEUSERNAME((UsuarioVO) this.data);
            default:
                return "";
        }
    }
    
    /**
     * Sends an email to destinatarios with a attached file
     *
     * @param destinatarios
     * @param asunto
     * @param mensaje
     * @param objFile
     */
    public static void sendMail(String destinatarios, String asunto, String mensaje, Object objFile) {
        Mail mail = new Mail(destinatarios, asunto, mensaje);
        if (objFile != null) {
            File file = (File) objFile;
            mail.attachFiles(file.getName(), file.getAbsolutePath());
        }
        mail.send();
    }
}

interface Queries {

    String VALIDATEUSER(UsuarioVO usuario);

    String USERNAMEEXISTS(UsuarioVO usuario);

    String UPDATEINTENTOS(UsuarioVO usuario);

    String BLOCKUSER(UsuarioVO usuario);

    String VALIDATEUSERNAME(UsuarioVO usuario);

    String CREATENEWUSER();

    String VALIDATEADMIN();

    String UPDATEUSER();

    String REMOVEUSER();

    String FILTRARUSUARIOS();

    String FILTRARTODOSUSUARIOS();

    String ACTIVARUSER();

    String INSERTNEWAUTO();

    String GETCARS();

    String UPDATEAUTO();

    String SUBMITCOMENTARIO();

    String GETFEEDBACK();

    String GETCATALOGO();

    String GETVENDEDOR();

    String GETVENDEDORNAME();

    String FILTRARAUTOS();

    String LISTAUTOSBYID();

    String UPDATEAUTOESTATUS();

}

interface ExecuteQuery {

    Response execute();
}
