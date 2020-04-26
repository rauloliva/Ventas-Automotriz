package com.automotriz.Negocio;

import com.automotriz.Datos.GestorDB;
import com.automotriz.Datos.Mail;
import com.automotriz.VO.UsuarioVO;
import com.automotriz.VO.MailVO;
import com.automotriz.Constantes.Constants;
import com.automotriz.VO.AutoVO;
import com.automotriz.VO.ComentarioVO;
import com.automotriz.VO.SessionVO;
import java.io.File;

public class Request {

    private String action;
    private String query = "";
    private Object data;

    /**
     * Creates an instance of Request
     * 
     * @param action database action from the Constants interface
     * @param data the data used to build the query
     */
    public Request(String action, Object data) {
        this.action = action;
        this.data = data;
    }

    /**
     * Creates an instance of Request
     * 
     * @param action database action from the Constants interface
     */
    public Request(String action) {
        this.action = action;
    }

    /**
     * Sends the query to GestorDB class to create a database operation
     * 
     * @return The response from GestorDB
     */
    public Response createNewOperation() {
        Response response = new Response();
        this.createNewQuery();
        switch(this.action){
            case Constants.SENDMAIL:
                this.sendMail((MailVO) this.data);
                return response;
            case Constants.DB_CONNECTION:
                response.setConnection(GestorDB.sendSQLConnection());
                return response;
            default:
                GestorDB db = new GestorDB(this.query);
                return db.executeQuery();
        }
    }

    /**
     * Creates a new query based in the action requested
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
                return "SELECT usuarios FROM usuarios "
                        + "WHERE usuario = '" + usuario.getUsuario() + "'";
            }

            @Override
            public String CREATENEWUSER(UsuarioVO usuario) {
                return "INSERT INTO usuarios "
                        + "(usuario,contrasena,correo,perfil,estatus,telefono,nombre,intentos) "
                        + "VALUES ('" + usuario.getUsuario() + "','" + usuario.getContraseña() + "',"
                        + "'" + usuario.getCorreo() + "','" + usuario.getPerfil() + "','ACTIVO',"
                        + "'" + usuario.getTelefono() + "','" + usuario.getNombre() + "',0)";
            }

            @Override
            public String VALIDATEADMIN(UsuarioVO usuario) {
                return "SELECT * FROM usuarios "
                        + "WHERE estatus='ACTIVO' AND perfil='Administrador' AND "
                        + "usuario='" + usuario.getUsuario() + "' AND contrasena='" + usuario.getContraseña() + "' "
                        + "AND permisos='ACCESO_TOTAL'";
            }

            @Override
            public String UPDATEUSER(UsuarioVO usuario) {
                return "UPDATE usuarios SET usuario='" + usuario.getUsuario() + "',"
                        + "contrasena='" + usuario.getContraseña() + "',correo='" + usuario.getCorreo() + "',"
                        + "perfil='" + usuario.getPerfil() + "',telefono='" + usuario.getTelefono() + "',"
                        + "nombre='" + usuario.getNombre() + "' "
                        + "WHERE usuario='" + usuario.getUsuario() + "'";
            }

            @Override
            public String REMOVEUSER(SessionVO session) {
                return "UPDATE usuarios SET estatus='DISABLED' "
                        + "WHERE usuario='" + session.getUsername() + "'";
            }

            @Override
            public String FILTRARUSUARIOS(UsuarioVO usuario) {
                String SQL = "";

                SQL += (!usuario.getUsuario().equals(""))
                        ? "usuario LIKE '" + usuario.getUsuario() + "%' " : "";

                if (!usuario.getTelefono().equals("")) {
                    SQL += (SQL.equals("")) ? "telefono LIKE '" + usuario.getTelefono() + "%' "
                            : "AND telefono LIKE '" + usuario.getTelefono() + "%' ";
                }

                if (!usuario.getEstatus().equals("--Seleccionar--")) {
                    SQL += (SQL.equals("")) ? "estatus = '" + usuario.getEstatus() + "' "
                            : "AND estatus = '" + usuario.getEstatus() + "' ";
                }

                if (!usuario.getPerfil().equals("--Seleccionar--")) {
                    SQL += (SQL.equals("")) ? "perfil = '" + usuario.getPerfil() + "' "
                            : "AND perfil = '" + usuario.getPerfil() + "' ";
                }
                return "SELECT * FROM usuarios WHERE " + SQL;
            }

            @Override
            public String FILTRARTODOSUSUARIOS() {
                return "SELECT * FROM usuarios";
            }

            @Override
            public String ACTIVARUSER(UsuarioVO usuario) {
                return "UPDATE usuarios SET estatus = 'ACTIVO' "
                        + "WHERE usuario = '" + usuario.getUsuario() + "'";
            }

            @Override
            public String INSERTNEWAUTO(AutoVO auto) {
                return "INSERT INTO autos (modelo,imagenes,kilometros,descripcion,marca,"
                        + "cambio,precio,color,estatus,id_usuario) VALUES ("
                        + "" + auto.getModelo() + ",'" + auto.getImagenes() + "'," + auto.getKilometros() + ","
                        + "'" + auto.getDescripcion() + "','" + auto.getMarca() + "','" + auto.getCambio() + "',"
                        + "" + auto.getPrecio() + ",'" + auto.getColor() + "','ACTIVO'," + auto.getId_usuario()
                        + ")";
            }

            @Override
            public String GETCARS(SessionVO session) {
                return "SELECT * FROM autos WHERE id_usuario=" + session.getId();
            }

            @Override
            public String UPDATEAUTO(AutoVO auto) {
                return "UPDATE autos SET modelo=" + auto.getModelo() + ",imagenes='" + auto.getImagenes() + "',"
                        + "kilometros=" + auto.getKilometros() + ",descripcion='" + auto.getDescripcion() + "',"
                        + "marca='" + auto.getMarca() + "',cambio='" + auto.getCambio() + "',"
                        + "precio=" + auto.getPrecio() + ",color='" + auto.getColor() + "' "
                        + "WHERE id=" + auto.getId();
            }

            @Override
            public String SUBMITCOMENTARIO(ComentarioVO comentario) {
                return "INSERT INTO comentarios (nombre,comentario,valoracion,id_usuario,fecha) VALUES ("
                        + "'" + comentario.getNombre() + "','" + comentario.getComentario() + "',"
                        + "" + comentario.getValoracion() + "," + comentario.getId_usuario() + ","
                        + "'" + comentario.getFecha() + "'"
                        + ")";
            }

            @Override
            public String GETFEEDBACK() {
                return "SELECT * FROM comentarios";
            }

            @Override
            public String GETCATALOGO(AutoVO auto) {
                return "SELECT * FROM autos WHERE "
                        + "id_usuario != " + auto.getId_usuario() + " AND "
                        + "estatus = 'ENVENTA'";
            }

            @Override
            public String GETVENDEDOR(UsuarioVO usuario) {
                return "SELECT correo,estatus,telefono,nombre FROM usuarios "
                        + "WHERE id = " + usuario.getId();
            }

            @Override
            public String GETVENDEDORNAME(UsuarioVO usuario) {
                return "SELECT nombre FROM usuarios WHERE correo='"+usuario.getCorreo()+"'";
            }

            @Override
            public String FILTRARAUTOS(AutoVO auto) {
                String SQL = "";

                SQL += !auto.getMarca().equals("--Seleccionar--") ? "marca='" + auto.getMarca() + "' " : "";

                if ((double) auto.getModelo() > 0) {
                    SQL += SQL.equals("") ? "modelo = " + auto.getMarca() + " "
                            : "AND modelo = " + auto.getMarca() + " ";
                }

                if (!auto.getColor().equals("--Seleccionar--")) {
                    SQL += SQL.equals("") ? "color = '" + auto.getColor() + "' "
                            : "AND color = '" + auto.getColor() + "' ";
                }

                if (!auto.getCambio().equals("--Seleccionar--")) {
                    SQL += SQL.equals("") ? "cambio = '" + auto.getCambio() + "' "
                            : "AND cambio = '" + auto.getCambio() + "' ";
                }

                SQL += SQL.equals("") ? "id_usuario = '" + auto.getId_usuario() + "' "
                        : "AND id_usuario = '" + auto.getId_usuario() + "' ";

                return "SELECT * FROM autos WHERE " + SQL;
            }

            @Override
            public String UPDATEAUTOESTATUS(AutoVO auto) {
                return "UPDATE autos SET estatus = '" + auto.getEstatus() + "' "
                        + "WHERE id = " + auto.getId();
            }
            
            @Override
            public String TABLESSCHEMA(){
                return "TABLES SCHEMAS";
            }
        };
        this.query = this.getQuery(queries);
    }

    /**
     * Builds the query based in the action requested
     * 
     * @param queries Queries interface
     * @return The query
     */
    private String getQuery(Queries queries) {
        switch (this.action) {
            case Constants.VALIDATEUSER:
                return queries.VALIDATEUSER((UsuarioVO) this.data);
            case Constants.USERNAMEEXISTS:
                return queries.USERNAMEEXISTS((UsuarioVO) this.data);
            case Constants.UPDATEINTENTOS:
                return queries.UPDATEINTENTOS((UsuarioVO) this.data);
            case Constants.BLOCKUSER:
                return queries.BLOCKUSER((UsuarioVO) this.data);
            case Constants.VALIDATEUSERNAME:
                return queries.VALIDATEUSERNAME((UsuarioVO) this.data);
            case Constants.CREATENEWUSER:
                return queries.CREATENEWUSER((UsuarioVO) this.data);
            case Constants.VALIDATEADMIN:
                return queries.VALIDATEADMIN((UsuarioVO) this.data);
            case Constants.REMOVEUSER:
                return queries.REMOVEUSER((SessionVO) this.data);
            case Constants.FILTRARUSUARIOS:
                return queries.FILTRARUSUARIOS((UsuarioVO) this.data);
            case Constants.FILTRARTODOSUSUARIOS:
                return queries.FILTRARTODOSUSUARIOS();
            case Constants.ACTIVARUSER:
                return queries.ACTIVARUSER((UsuarioVO) this.data);
            case Constants.UPDATEUSER:
                return queries.UPDATEUSER((UsuarioVO) this.data);
            case Constants.INSERTNEWAUTO:
                return queries.INSERTNEWAUTO((AutoVO) this.data);
            case Constants.UPDATEAUTO:
                return queries.UPDATEAUTO((AutoVO) this.data);
            case Constants.GETCARS:
                return queries.GETCARS((SessionVO) this.data);
            case Constants.SUBMITCOMENTARIO:
                return queries.SUBMITCOMENTARIO((ComentarioVO) this.data);
            case Constants.GETCATALOGO:
                return queries.GETCATALOGO((AutoVO) this.data);
            case Constants.GETVENDEDOR:
                return queries.GETVENDEDOR((UsuarioVO) this.data);
            case Constants.GETVENDEDORNAME:
                return queries.GETVENDEDORNAME((UsuarioVO) this.data);
            case Constants.UPDATEAUTOESTATUS:
                return queries.UPDATEAUTOESTATUS((AutoVO) this.data);
            case Constants.FILTRARAUTOS:
                return queries.FILTRARAUTOS((AutoVO) this.data);
            case Constants.TABLESSCHEMA:
                return queries.TABLESSCHEMA();
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
    private void sendMail(MailVO mailVO) {
        Mail mail = new Mail(mailVO);
        if (mailVO.getAttachFile() != null) {
            File file = (File) mailVO.getAttachFile();
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

    String CREATENEWUSER(UsuarioVO usuario);

    String VALIDATEADMIN(UsuarioVO usuario);

    String UPDATEUSER(UsuarioVO usuario);

    String REMOVEUSER(SessionVO session);

    String FILTRARUSUARIOS(UsuarioVO usuario);

    String FILTRARTODOSUSUARIOS();

    String ACTIVARUSER(UsuarioVO usuario);

    String INSERTNEWAUTO(AutoVO auto);

    String GETCARS(SessionVO session);

    String UPDATEAUTO(AutoVO auto);

    String SUBMITCOMENTARIO(ComentarioVO comentario);

    String GETFEEDBACK();

    String GETCATALOGO(AutoVO auto);

    String GETVENDEDOR(UsuarioVO usuario);

    String GETVENDEDORNAME(UsuarioVO usuario);

    String FILTRARAUTOS(AutoVO auto);

    String UPDATEAUTOESTATUS(AutoVO auto);
    
    String TABLESSCHEMA();
}
