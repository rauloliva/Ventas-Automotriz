package com.automotriz.Presentacion;

import java.util.HashMap;
import javax.swing.JOptionPane;
import com.automotriz.Negocio.Peticiones;
import com.automotriz.VO.AutoVO;
import com.automotriz.logger.Logger;
import org.json.simple.JSONObject;
import com.automotriz.VO.SessionVO;
import com.automotriz.VO.UsuarioVO;
import com.automotriz.VO.ComentarioVO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import com.automotriz.Constantes.Constants;
import com.automotriz.Negocio.Handler;
import com.automotriz.Negocio.Response;
import java.sql.ResultSet;

public class Validacion implements Runnable {

    public static final int ATTEMPTS_ALLOWED = 5;
    public static int loginTries = 0;
    private Object[] data;
    private JSONObject requestJSON;
    private SessionVO session;
    private UsuarioVO usuario;
    private Object objVo;
    private DefaultTableModel model;
    private ArrayList<UsuarioVO> usuariosVO;
    private ArrayList<AutoVO> autosVO;
    private ArrayList<ComentarioVO> comentariosVO;
    private Response response;
    public static Thread hiloVaidacion;

    /**
     * initializes a new Validacion object to start validating some data and
     * parsing them in a JSON object
     *
     * @param data The data to validate,before sending them in queries
     */
    public Validacion(Object[] data) {
        this.data = data;
    }

    /**
     *
     * @param data
     * @param obj
     */
    public Validacion(Object[] data, Object obj) {
        this.data = data;
        this.objVo = obj;
    }

    public void setObjectVO(Object objVO) {
        this.objVo = objVO;
    }

    /**
     * validate the object passed to the constructor positions: 0 -> username
     * input, 1 -> length field, 2 -> input keyEvent
     *
     * @return
     */
    public Validacion validateInputLength() {
        if (isEmpty(data[0])) {
            return this;
        }

        if (data[0].toString().length() >= Integer.parseInt(data[1].toString())) {
            ((java.awt.event.KeyEvent) data[2]).consume();
            writeMessages(new Object[]{
                "signin.msg.warn.fieldLength",
                "signin.msg.warn.fieldLength.title",
                JOptionPane.WARNING_MESSAGE
            });

        }
        return this;
    }
    
    /**
     * Initialize the class Handler in order to
     * prepare a new database operation
     * 
     * @param operation The operation's name to execute
     * @param objVO The data needed to execute the operation
     * @return A new instance of Response class
     */
    private void initializeHandler(String operation, Object objVO){
        Logger.log("Creating new Handler");
        Handler handler = new Handler(operation, objVO);
        this.response = handler.createNewOperation();
    } 


    /**
     * 
     * @return True if the login access is successful, otherwise returns false
     * @throws Exception 
     */
    public boolean validarLogIn() throws Exception {
        Logger.log("validating logging");
        if (isEmpty(data[0]) || isEmpty(data[1])) {
            writeMessages(new Object[]{
                "login.msg.error.empty",
                "login.msg.error.empty.title",
                JOptionPane.ERROR_MESSAGE
            });
            Logger.error("Username and/or password empty");
            return false;
        }
        
        usuario = new UsuarioVO();
        usuario.setUsuario(data[0].toString());
        usuario.setContraseña(data[1].toString());

        initializeHandler(Constants.VALIDATEUSER, usuario);

        if (this.response.getStatus() == Response.STATUS_SUCCESS) {
            //validate if the profile is the correct one
            Logger.log("validating profile");
            ResultSet rs = response.getResultSet();
            if (rs.next()) {
                this.usuario.setId(rs.getInt("id"));
                this.usuario.setNombre(rs.getString("nombre"));
                this.usuario.setUsuario(rs.getString("usuario"));
                this.usuario.setContraseña(rs.getString("contrasena"));
                this.usuario.setCorreo(rs.getString("correo"));
                this.usuario.setTelefono(rs.getString("telefono"));
                this.usuario.setPerfil(rs.getString("perfil"));
                this.usuario.setEstatus(rs.getString("estatus"));
                this.usuario.setIntentos(rs.getInt("intentos"));
            }
            if(this.usuario.getEstatus().equals("BLOCKED")){
                Logger.log("The user is blocked");
                writeMessages(new Object[]{
                    "login.msg.bloquear",
                    "login.msg.bloquear.title",
                    JOptionPane.ERROR_MESSAGE
                });
                return false;
            }
            if (!this.usuario.getPerfil().equals(data[2].toString())) {
                Logger.log("The user's profile does not match the current profile");
                writeMessages(new Object[]{
                    "login.msg.error.profile",
                    "login.msg.error.profile.title",
                    JOptionPane.WARNING_MESSAGE
                });
                return false;
            } else {
                if (this.usuario.getIntentos() > 0) {
                    Logger.log("Reseting the number of attempts");
                    this.usuario.setIntentos(0);
                    initializeHandler(Constants.UPDATEINTENTOS, usuario);
                } 
                //If the login is successful a new SessionVO is created
                Logger.log("Creating the user's session");
                this.session = this.usuario.createSession();
            }
        } 
        /* Incorrect Credentials */ 
        else if (response.getStatus() == Response.STATUS_RESULTSET_EMPTY) {
            // Verify if the username exists in order to register the attempts
            Logger.log("ResultSet empty, verifying if the username exits");
            usuario = new UsuarioVO();
            usuario.setUsuario(data[0].toString());
            initializeHandler(Constants.USERNAMEEXISTS, usuario);
            if(response.getStatus() == Response.STATUS_SUCCESS){
                //Update the number of attempts 
                Logger.log("The username exits, counting the attempts");
                ResultSet rs = response.getResultSet();
                rs.next();
                usuario.setIntentos(rs.getInt("intentos") + 1);
                usuario.setId(rs.getInt("id"));
                if(usuario.getIntentos() > 5){
                    Logger.log("The user is blocked");
                    writeMessages(new Object[]{
                        "login.msg.bloquear",
                        "login.msg.bloquear.title",
                        JOptionPane.ERROR_MESSAGE
                    });
                    return false;
                }
                initializeHandler(Constants.UPDATEINTENTOS, usuario);
                if(response.getStatus() == Response.STATUS_UPDATED){
                    Logger.log("The credentials are incorrect");
                    writeMessages(new Object[]{
                        "login.msg.error.auth",
                        "login.msg.error.auth.title",
                        JOptionPane.ERROR_MESSAGE
                    });

                    if(usuario.getIntentos() == this.ATTEMPTS_ALLOWED){
                        initializeHandler(Constants.BLOCKUSER, usuario);
                        if(response.getStatus() == Response.STATUS_UPDATED){
                            Logger.log("The user will be blocked");
                            writeMessages(new Object[]{
                                "login.msg.bloquear",
                                "login.msg.bloquear.title",
                                JOptionPane.ERROR_MESSAGE
                            });
                        }
                    }
                }
                return false;
            }else{
                Logger.log("The username does not exits");
                writeMessages(new Object[]{
                    "login.msg.error.username",
                    "login.msg.error.username.title",
                    JOptionPane.ERROR_MESSAGE
                });
                return false;
            }
        } else if (response.getStatus() == Response.STATUS_FAILURE) {
            writeMessages(new Object[]{
                "login.msg.error.conndb",
                "login.msg.error.conndb.title",
                JOptionPane.ERROR_MESSAGE
            });
            return false;
        }
        return true;
    }

    /**
     * Validates if the username already exists
     * @return True if the username exists, otherwise returns false
     */
    public boolean usernameAlreadyExists() {
        Logger.log("checking if username already exists");
        if (!isEmpty(data[0])) {
            usuario = new UsuarioVO();
            usuario.setUsuario(data[0].toString());
            initializeHandler(Constants.VALIDATEUSERNAME, usuario);

            if (response.getStatus() == Response.STATUS_SUCCESS) {
                Logger.log("Username already exists");
                writeMessages(new Object[]{
                    "signin.msg.usernameExists",
                    "signin.msg.usernameExists.title",
                    JOptionPane.ERROR_MESSAGE
                });
                return true;
            }else if (response.getStatus() == Response.STATUS_RESULTSET_EMPTY) {
                Logger.log("Username available");
            }
            return false;
        }
        return true;
    }

    public boolean askAdminRights() {
        Frame_Credentials credentials = new Frame_Credentials(null, true);
        hiloVaidacion = new Thread(this);
        /*stop this thread until the admin give their adminRights*/
        hiloVaidacion.stop();
        return credentials.isAllowed();
    }

    public Validacion validateForm(String form, String operation) {
        boolean allow = false;
        Logger.log("validating " + form + " form ");
        if (isEmpty(data[0]) || isEmpty(data[1]) || isEmpty(data[2]) || isEmpty(data[3]) || isEmpty(data[4]) || isEmpty(data[5])) {
            writeMessages(new Object[]{
                "signin.msg.error.emptyFields",
                "signin.msg.error.emptyFields.title",
                JOptionPane.WARNING_MESSAGE
            });
            return this;
        }
        if (data[3].toString().equals("Administrador") && operation.equals(Constants.CREATENEWUSER)) {
            allow = askAdminRights();
        } else {
            allow = true;
        }

        //admin rigths
        if (allow) {
            Logger.log("Creating new Request");
            initializeHandler(operation, usuario);

            if (response.getStatus() == Response.STATUS_INSERTED) {
                writeMessages(new Object[]{
                    operation.equals(Constants.CREATENEWUSER)
                    ? "signin.msg.userCreated" : "perfil.msg.userUpdated",
                    operation.equals(Constants.CREATENEWUSER)
                    ? "signin.msg.userCreated.title" : "perfil.msg.userUpdated.title",
                    JOptionPane.INFORMATION_MESSAGE
                });
            }
        }
        return this;
    }

    @Override
    public void run() {
    }

    public Validacion validateAdminRights(String username, String password) {
        //for this action is a requirement fot another admin
        //to allow this operation
        if (isEmpty(username) || isEmpty(password)) {
            writeMessages(new Object[]{
                "signin.msg.error.emptyFields",
                "signin.msg.error.emptyFields.title",
                JOptionPane.WARNING_MESSAGE
            });
            return this;
        }

        createRequestJSON("VALIDATEADMIN",
                new String[]{"id"},
                new Object[]{username, password});
        Logger.log("Creating new Request");
        Peticiones peticion = new Peticiones(requestJSON);
        JSONObject response = peticion.execute();
        if (((int) response.get("estatus")) == Constants.QUERY_GOT_NOTHING) {
            writeMessages(new Object[]{
                "signin.msg.error.permissionDenied",
                "signin.msg.error.permissionDenied.title",
                JOptionPane.ERROR_MESSAGE
            });
        }
        return this;
    }

    public Validacion removeUser() {
        Logger.log("Creating new Request");
        createRequestJSON("REMOVEUSER", null);

        Peticiones peticion = new Peticiones(requestJSON);
        JSONObject response = peticion.execute();
        if (!response.isEmpty()) {
            if (((int) response.get("response")) == Constants.QUERY_SUCCESS) {
                writeMessages(new Object[]{
                    "perfil.msg.userDeleted",
                    "perfil.msg.userDeleted.title",
                    JOptionPane.INFORMATION_MESSAGE
                });
            }
        }
        return this;
    }

    public Validacion filtrarUsuarios() {

        if (this.data == null) {
            createRequestJSON("FILTRARTODOSUSUARIOS",
                    new String[]{"id", "usuario", "contrasena", "correo", "perfil", "estatus", "telefono"});
        } else {
            createRequestJSON("FILTRARUSUARIOS",
                    new String[]{"id", "usuario", "contrasena", "correo", "perfil", "estatus", "telefono"});
        }

        Logger.log("Creating new Request");
        Peticiones peticion = new Peticiones(requestJSON);
        peticion.setObjectVO(objVo);
        JSONObject response = peticion.execute();
        if (((int) response.get("estatus")) == Constants.QUERY_GOT_SOMETHING) {
            Logger.log("Creating rows for the table");
            usuariosVO = new ArrayList<>();

            Object[] objArray = (Object[]) response.get("obj");
            for (Object obj : objArray) {
                UsuarioVO vo = (UsuarioVO) obj;
                usuariosVO.add(vo);
                if (model != null) {
                    model.addRow(new Object[]{
                        vo.getUsuario(),
                        vo.getCorreo(),
                        vo.getPerfil(),
                        vo.getEstatus(),
                        vo.getTelefono()
                    });
                }
            }
        } else {
            writeMessages(new Object[]{
                "usuarios.msg.userNotFound",
                "usuarios.msg.userNotFound.title",
                JOptionPane.INFORMATION_MESSAGE
            });
        }

        return this;
    }

    /**
     * Object's indexes = 0 -> codigo, 1 -> Username, 2 -> Selected status, 3 ->
     * current user status
     *
     * this method will only execute if one of the following conditions is true:
     * codigo == '' and selected status == 'ACTIVO' and current user status ==
     * 'DISABLED' codigo != '' and selected status == 'ACTIVO' and current user
     * status == 'BLOCKED' codigo == '' and selected status == 'DISABLED' and
     * current user status == 'BLOCKED'
     *
     * @return the Object class 'Validacion'
     */
    public Validacion updateUserAsAdmin() {

        if (/*CASE 1*/(data[0].toString().equals("") && data[2].toString().equals("ACTIVO") && data[3].toString().equals("DISABLED"))
                || /*CASE 2*/ (!data[0].toString().equals("") && data[2].toString().equals("ACTIVO") && data[3].toString().equals("BLOCKED"))
                || /*CASE 3*/ (data[0].toString().equals("") && data[2].toString().equals("DISABLED") && data[3].toString().equals("BLOCKED"))
                || /*CASE 4*/ (data[0].toString().equals("") && data[2].toString().equals("DISABLED") && data[3].toString().equals("ACTIVO"))) {

            Logger.log("Creating new Request");
            Peticiones peticion;
            JSONObject response;
            //set the username to position 1, in order to form the query
            Object codigo = data[0];
            data[0] = data[1];
            data[1] = codigo;

            switch (data[2].toString()) {
                case "DISABLED":
                    createRequestJSON("REMOVEUSER", null);
                    peticion = new Peticiones(requestJSON);
                    peticion.setObjectVO(objVo);
                    response = peticion.execute();

                    writeMessages(new Object[]{
                        "editUser.msg.userdisabled",
                        "editUser.msg.userdisabled.title",
                        JOptionPane.INFORMATION_MESSAGE
                    });
                    break;
                case "ACTIVO":
                    createRequestJSON("ACTIVARUSER", null);
                    peticion = new Peticiones(requestJSON);
                    peticion.setObjectVO(objVo);
                    response = peticion.execute();

                    //send the notification to the user
                    String bodyMessage = ReadProperties.props.getProperty("email.msg.userActived")
                            .replace("*", new Hashing(codigo.toString()).decrypt());
                    Peticiones.sendMail(
                            data[4].toString(),
                            "VEHICLE SELL| USUARIO REACTIVADO",
                            bodyMessage,
                            null);

                    writeMessages(new Object[]{
                        "editUser.msg.userActivado",
                        "editUser.msg.userActivado.title",
                        JOptionPane.INFORMATION_MESSAGE
                    });
            }

        } else {
            writeMessages(new Object[]{
                "editUser.msg.avd.noOperation",
                "editUser.msg.avd.noOperation.title",
                JOptionPane.WARNING_MESSAGE
            });
            return this;
        }
        return this;
    }

    /**
     * spn_modelo, spn_km, cmb_marca, txt_otraMarca, cmb_cambio, spn_precio,
     * cmb_color, txa_descripcion, imagesPath, txt_dueño
     *
     * @return the object of the class Validacion
     * @param isAnUpdate will execute operations related to an UPDATE to a car,
     * otherwise will execute an INSERT
     */
    public Validacion saveAutomobile(boolean isAnUpdate) {

        if (data[2].toString().equals("--Seleccionar--")) {
            if (data[3].toString().equals("")) {
                writeMessages(new Object[]{
                    "vender.msg.invalid.marca",
                    "vender.msg.invalid.marca.title",
                    JOptionPane.WARNING_MESSAGE
                });
                return this;
            }
            //if the combo does not select a marca take the value from the textfield
            data[2] = data[3];
        }

        if (data[4].toString().equals("--Seleccionar--")) {
            writeMessages(new Object[]{
                "vender.msg.invalid.cambio",
                "vender.msg.invalid.cambio.title",
                JOptionPane.WARNING_MESSAGE
            });
            return this;
        }

        if (data[6].toString().equals("--Seleccionar--")) {
            writeMessages(new Object[]{
                "vender.msg.invalid.color",
                "vender.msg.invalid.color.title",
                JOptionPane.WARNING_MESSAGE
            });
            return this;
        }

        if (data[7].toString().equals("")) {
            writeMessages(new Object[]{
                "vender.msg.invalid.descripcion",
                "vender.msg.invalid.descripcion.title",
                JOptionPane.WARNING_MESSAGE
            });
            return this;
        }

        if (isAnUpdate) {
            createRequestJSON("UPDATEAUTO", null);
            Peticiones peticion = new Peticiones(requestJSON);
            JSONObject response = peticion.execute();

            if (((int) response.get("response")) == Constants.QUERY_SUCCESS) {
                writeMessages(new Object[]{
                    "vender.msg.autoUpdated",
                    "vender.msg.autoUpdated.title",
                    JOptionPane.INFORMATION_MESSAGE
                });
            } else {
                writeMessages(new Object[]{
                    "vender.msg.error.autoNotUpdated",
                    "vender.msg.error.autoNotUpdated.title",
                    JOptionPane.ERROR_MESSAGE
                });
            }
        } else {
            createRequestJSON("INSERTNEWAUTO", null);
            Peticiones peticion = new Peticiones(requestJSON);
            JSONObject response = peticion.execute();

            if (((int) response.get("response")) == Constants.QUERY_SUCCESS) {
                writeMessages(new Object[]{
                    "vender.msg.autoInserted",
                    "vender.msg.autoInserted.title",
                    JOptionPane.INFORMATION_MESSAGE
                });
            } else {
                writeMessages(new Object[]{
                    "vender.msg.error.autoNotInserted",
                    "vender.msg.error.autoNotInserted.title",
                    JOptionPane.ERROR_MESSAGE
                });
            }
        }

        return this;
    }

    public Validacion listUserAutos() {
        createRequestJSON("GETCARS", null);
        Peticiones peticion = new Peticiones(requestJSON);
        peticion.setObjectVO(objVo);
        JSONObject response = peticion.execute();

        if (((int) response.get("estatus")) == Constants.QUERY_GOT_SOMETHING) {

            autosVO = new ArrayList<>();
            Object[] objArray = (Object[]) response.get("obj");
            if (objArray != null) {
                for (Object obj : objArray) {
                    AutoVO vo = (AutoVO) obj;
                    autosVO.add(vo);
                }
            }

            setRowsTable(objArray);
        } else {
            writeMessages(new Object[]{
                "usuarios.msg.userNotFound",
                "usuarios.msg.userNotFound.title",
                JOptionPane.INFORMATION_MESSAGE
            });
        }
        return this;
    }

    private void setRowsTable(Object[] autos) {
        if (model != null) {
            Logger.log("Creating rows for the table");
            for (Object obj : autos) {
                AutoVO vo = (AutoVO) obj;
                autosVO.add(vo);
                model.addRow(new Object[]{
                    vo.getId(),
                    vo.getMarca(),
                    vo.getModelo(),
                    vo.getPrecio(),
                    vo.getColor(),
                    vo.getEstatus()
                });
            }
        }
    }

    /**
     * txt_nombre.getText(), chb_anonimo.isSelected(),
     * txa_comentarios.getText(), valoracion
     *
     * @return
     */
    public Validacion submitComentario() {
        if (data[0].toString().equals("")) {
            if (!Boolean.parseBoolean(data[1].toString())) {
                writeMessages(new Object[]{
                    "clientView.msg.error.emptyName",
                    "clientView.msg.error.empty.title",
                    JOptionPane.WARNING_MESSAGE
                });
                return this;
            }
            data[0] = "Anonimo";
        }

        if (data[2].toString().equals("")) {
            if (((int) data[3]) == 0) {
                writeMessages(new Object[]{
                    "clientView.msg.error.emptyComentarioVal",
                    "clientView.msg.error.empty.title",
                    JOptionPane.WARNING_MESSAGE
                });
                return this;
            }
        }

        createRequestJSON("SUBMITCOMENTARIO", null);
        Peticiones peticion = new Peticiones(requestJSON);
        JSONObject response = peticion.execute();
        if (((int) response.get("response")) == Constants.QUERY_SUCCESS) {
            writeMessages(new Object[]{
                "clientView.msg.submit.successfully",
                "clientView.msg.submit.successfully.title",
                JOptionPane.INFORMATION_MESSAGE
            });
        }
        return this;
    }

    public Validacion getFeedBack() {
        createRequestJSON("GETFEEDBACK", null);
        Peticiones peticion = new Peticiones(requestJSON);
        peticion.setObjectVO(objVo);
        JSONObject response = peticion.execute();
        if (((int) response.get("estatus")) == Constants.QUERY_GOT_SOMETHING) {
            Object[] obj = (Object[]) response.get("obj");
            comentariosVO = new ArrayList<>();
            for (Object vo : obj) {
                comentariosVO.add((ComentarioVO) vo);
            }
        }
        return this;
    }

    /**
     * Gets the whole catalogo not including the cars updated by the user logged
     *
     * @return
     */
    public Validacion getCatalogo() {
        createRequestJSON("GETCATALOGO", null);
        Peticiones peticion = new Peticiones(requestJSON);
        peticion.setObjectVO(objVo);
        JSONObject response = peticion.execute();
        if (((int) response.get("estatus")) == Constants.QUERY_GOT_SOMETHING) {
            Object[] obj = (Object[]) response.get("obj");
            autosVO = new ArrayList<>();
            for (Object vo : obj) {
                autosVO.add((AutoVO) vo);
            }
        }
        return this;
    }

    public Validacion getVendedor() {
        createRequestJSON("GETVENDEDOR", null);
        Peticiones peticion = new Peticiones(requestJSON);
        peticion.setObjectVO(objVo);
        JSONObject response = peticion.execute();
        if (((int) response.get("estatus")) == Constants.QUERY_GOT_SOMETHING) {
            usuariosVO = new ArrayList<>();
            usuariosVO.add((UsuarioVO) ((Object[]) response.get("obj"))[0]);
        }
        return this;
    }

    public Validacion getVendedorName() {
        if (!isEmpty(data[0])) {
            createRequestJSON("GETVENDEDORNAME", null);
            Peticiones peticion = new Peticiones(requestJSON);
            peticion.setObjectVO(objVo);
            JSONObject response = peticion.execute();
            if (((int) response.get("estatus")) == Constants.QUERY_GOT_SOMETHING) {
                usuariosVO = new ArrayList<>();
                usuariosVO.add((UsuarioVO) ((Object[]) response.get("obj"))[0]);
            }
        }
        return this;
    }

    public Validacion sendMailToVendedor() {
        if (!isEmpty(data[0]) && !isEmpty(data[1]) && !isEmpty(data[2])) {
            Peticiones.sendMail(
                    data[0].toString(),
                    data[1].toString(),
                    data[2].toString(),
                    data[3]);
            /*update the vehicle's status, this vehicle will not be available in
            catalogo*/
            data = new Object[]{
                Constants.PROCCESS_PURCHASE,
                data[4]
            };
            createRequestJSON("UPDATEAUTOESTATUS", null);
            Peticiones peticion = new Peticiones(requestJSON);
            peticion.execute();
        } else {
            writeMessages(new Object[]{
                "mail.empty.fields",
                "mail.empty.fields.title",
                JOptionPane.WARNING_MESSAGE
            });
        }
        return this;
    }

    public Validacion filtrarAutos() {
        autosVO = null;
        if (!isEmpty(data[0]) || !isEmpty(data[1]) || !isEmpty(data[2]) || !isEmpty(data[3])) {
            createRequestJSON("FILTRARAUTOS", null);
            Peticiones peticion = new Peticiones(requestJSON);
            peticion.setObjectVO(objVo);
            JSONObject response = peticion.execute();
            if (((int) response.get("estatus")) == Constants.QUERY_GOT_SOMETHING) {
                autosVO = new ArrayList<>();
                for (Object obj : (Object[]) response.get("obj")) {
                    AutoVO auto = (AutoVO) obj;
                    autosVO.add(auto);
                }
            }
        }
        return this;
    }

    public Validacion deleteAuto() {
        if (!isEmpty(data[0]) || !isEmpty(data[1])) {
            createRequestJSON("UPDATEAUTOESTATUS", null);
            Peticiones peticion = new Peticiones(requestJSON);
            JSONObject response = peticion.execute();
            if (((int) response.get("response")) == Constants.QUERY_SUCCESS) {
                writeMessages(new Object[]{
                    "msg.auto.deleted.success",
                    "msg.auto.deleted.success.title",
                    JOptionPane.INFORMATION_MESSAGE
                });
            }
        }
        return this;
    }

    public Validacion updateStatusAuto() {
        if (!isEmpty(data[0]) || !isEmpty(data[1])) {
            createRequestJSON("UPDATEAUTOESTATUS", null);
            Peticiones peticion = new Peticiones(requestJSON);
            JSONObject response = peticion.execute();
            if (((int) response.get("response")) == Constants.QUERY_SUCCESS) {
                writeMessages(new Object[]{
                    "msg.update.status",
                    "msg.update.status.title",
                    JOptionPane.INFORMATION_MESSAGE
                });
            } else {
                writeMessages(new Object[]{
                    "msg.update.status.failed",
                    "msg.update.status.failed.title",
                    JOptionPane.ERROR_MESSAGE
                });
            }
        }
        return this;
    }

    public HashMap requestDatabaseTables() {
        createRequestJSON("", null);
        Peticiones peticion = new Peticiones(requestJSON);
        JSONObject response = peticion.execute();
        if (((int) response.get("estatus")) == Constants.QUERY_GOT_SOMETHING) {
            return (HashMap) ((Object[]) response.get("obj"))[0];
        }
        return new HashMap();
    }

    public static Connection requestSQLConnection() {
        return Peticiones.requestSQLConnection();
    }

    public ArrayList<UsuarioVO> getUsuarios() {
        return usuariosVO;
    }

    public ArrayList<AutoVO> getAutos() {
        return autosVO;
    }

    public ArrayList<ComentarioVO> getComentarios() {
        return comentariosVO;
    }

    public DefaultTableModel getTableModel() {
        return model;
    }

    public void setTableModel(DefaultTableModel model) {
        this.model = model;
    }

    public SessionVO getSession() {
        return this.session;
    }

    private static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        return obj.toString().equals("");
    }

    private void writeMessages(Object[] Props) {
//        messageProps = new HashMap();
//        messageProps.put("message",
//                ReadProperties.props.getProperty(Props[0].toString()));
//        messageProps.put("title",
//                ReadProperties.props.getProperty(Props[1].toString()));
//        messageProps.put("type", (int) Props[2]);
        JOptionPane.showMessageDialog(null, 
                ReadProperties.props.getProperty(Props[0].toString()),
                ReadProperties.props.getProperty(Props[1].toString()),
                (int) Props[2]);
    }

    public static String generateDate() {
        Calendar c = new GregorianCalendar();
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month <= 9 ? "0" + month : month) + "-" + (day <= 9 ? "0" + day : day);
    }
}
