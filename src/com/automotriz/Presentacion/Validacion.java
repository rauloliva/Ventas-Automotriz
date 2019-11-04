package com.automotriz.Presentacion;

import java.util.HashMap;
import javax.swing.JOptionPane;
import com.automotriz.Negocio.Peticiones;
import com.automotriz.VO.AutoVO;
import com.automotriz.logger.Logger;
import org.json.simple.JSONObject;
import com.automotriz.VO.Session;
import com.automotriz.VO.UsuarioVO;
import com.automotriz.VO.ComentarioVO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import com.automotriz.Constantes.Constants;

public class Validacion implements Runnable {

    public static final int ATTEMPTS_ALLOWED = 3;
    public static int loginTries = 0;
    private HashMap messageProps;
    private Object[] data;
    private JSONObject requestJSON;
    private Session session;
    private Object objVo;
    private DefaultTableModel model;
    private ArrayList<UsuarioVO> usuariosVO;
    private ArrayList<AutoVO> autosVO;
    private ArrayList<ComentarioVO> comentariosVO;
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
     * @param datadic the datadic that contains the query to be executed
     * @param columns columns to get from a table in db. set to null when an
     * INSERT or an UPDATE query is going to be executed
     */
    private void createRequestJSON(String datadic, String[] columns) {
        requestJSON = new JSONObject();
        requestJSON.put("0", datadic);
        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                requestJSON.put("" + (i + 1), data[i]);
            }
        }

        requestJSON.put("response", columns);
    }

    /**
     * @param datadic the datadic that contains the query to be executed
     * @param columns columns to get from a table in db. set to null when an
     * @param data the data that will be sent to GestorDB INSERT or an UPDATE
     * query is going to be executed
     */
    private void createRequestJSON(String datadic, String[] columns, Object[] data) {
        requestJSON = new JSONObject();
        requestJSON.put("0", datadic);
        for (int i = 0; i < data.length; i++) {
            requestJSON.put("" + (i + 1), data[i]);
        }
        requestJSON.put("response", columns);
    }

    public Validacion validarLogIn() {
        messageProps = new HashMap();
        Logger.log("validating log in form");
        if (isEmpty(data[0]) || isEmpty(data[1])) {
            writeMessages(new Object[]{
                "login.msg.error.empty",
                "login.msg.error.empty.title",
                JOptionPane.ERROR_MESSAGE
            });
            return this;
        }
        /*
         send a new request to DB
         in exchange we get a session
         */
        createRequestJSON("VALIDATEUSER",
                new String[]{"id", "usuario", "contrasena", "correo", "perfil", "estatus", "telefono"});

        Logger.log("Creating new Request");
        Peticiones peticion = new Peticiones(requestJSON);
        peticion.setObjectVO(objVo);
        JSONObject response = peticion.getResult();

        if (!response.isEmpty()) {

            if (((int) response.get("estatus")) == Constants.QUERY_GOT_NOTHING) {
                writeMessages(new Object[]{
                    "login.msg.error.auth",
                    "login.msg.error.auth.title",
                    JOptionPane.ERROR_MESSAGE
                });

                //counting the number of tries attempting to log in
                loginTries++;
                Logger.log("Attempt " + loginTries + " to get access");
                if (loginTries == ATTEMPTS_ALLOWED) {
                    Logger.log("Blocking user");
                    writeMessages(new Object[]{
                        "login.msg.bloquear",
                        "login.msg.bloquear.title",
                        JOptionPane.WARNING_MESSAGE
                    });

                    this.data = new String[]{data[0].toString()};
                    createRequestJSON("BLOCKUSER", null);
                    //block the user
                    peticion = new Peticiones(requestJSON);
                    JSONObject result = peticion.getResult();

                    if (!result.isEmpty()) {
                        if (((int) result.get("response")) == Constants.QUERY_SUCCESS) {
                            writeMessages(new Object[]{
                                "login.msg.bloquear",
                                "login.msg.bloquear.title",
                                JOptionPane.ERROR_MESSAGE
                            });
                        } else {
                            writeMessages(new Object[]{
                                "login.msg.error.bloquear",
                                "login.msg.error.bloquear.title",
                                JOptionPane.ERROR_MESSAGE
                            });
                        }
                    } else {
                        //error on server
                        writeMessages(new Object[]{
                            "login.msg.error.conndb",
                            "login.msg.error.conndb.title",
                            JOptionPane.ERROR_MESSAGE
                        });
                    }
                }
            } else {

                //validate if the profile is the correct one
                Logger.log("validating profile");
                if (!((Session) ((Object[]) response.get("obj"))[0]).getPerfil().equals(Frame_LogIn.perfil)) {
                    writeMessages(new Object[]{
                        "login.msg.error.profile",
                        "login.msg.error.profile.title",
                        JOptionPane.WARNING_MESSAGE
                    });
                } else {
                    messageProps = null;
                    /*
                        if the login is successful
                        Create a new Session
                     */
                    Logger.log("Creating the user's session");
                    this.session = (Session) ((Object[]) response.get("obj"))[0];
                }
            }

        } else {
            //error on server
            writeMessages(new Object[]{
                "login.msg.error.conndb",
                "login.msg.error.conndb.title",
                JOptionPane.WARNING_MESSAGE
            });
        }
        return this;
    }

    public Validacion usernameAlreadyExists() {
        Logger.log("checking if username already exists");
        if (!isEmpty(data[0])) {
            messageProps = new HashMap();
            createRequestJSON("VALIDATEUSERNAME", new String[]{"usuario"});

            Logger.log("Creating new Request");
            Peticiones peticion = new Peticiones(requestJSON);
            JSONObject response = peticion.getResult();
            if (!response.isEmpty()) {
                if (((int) response.get("estatus")) == Constants.QUERY_GOT_SOMETHING) {
                    Logger.log("Username already exists");
                    writeMessages(new Object[]{
                        "signin.msg.usernameExists",
                        "signin.msg.usernameExists.title",
                        JOptionPane.ERROR_MESSAGE
                    });
                } else {
                    Logger.log("Username available");
                    messageProps = null;
                }
            }
        }
        //if the field is empty nothing happens
        return this;
    }

    public Validacion validateForm(String form, String datadic) {
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
        if (data[3].toString().equals("Administrador") && datadic.equals("CREATENEWUSER")) {
            Frame_Credentials credentials = new Frame_Credentials(null, true);
            hiloVaidacion = new Thread(this);
            /*stop this thread until the admin give their adminRights*/
            hiloVaidacion.stop();
            allow = credentials.isAllowed();
        } else {
            allow = true;
        }

        //admin rigths
        if (allow) {
            Logger.log("Creating new Request");
            messageProps = new HashMap();
            createRequestJSON(datadic, null);

            Peticiones peticion = new Peticiones(requestJSON);
            JSONObject response = peticion.getResult();
            if (!response.isEmpty()) {
                if (((int) response.get("response")) == Constants.QUERY_SUCCESS) {
                    writeMessages(new Object[]{
                        datadic.equals("CREATENEWUSER")
                        ? "signin.msg.userCreated" : "perfil.msg.userUpdated",
                        datadic.equals("CREATENEWUSER")
                        ? "signin.msg.userCreated.title" : "perfil.msg.userUpdated.title",
                        JOptionPane.INFORMATION_MESSAGE
                    });
                } else {
                    messageProps = null;
                }
            }
        }
        return this;
    }

    @Override
    public void run() {
        System.out.println("isResume");
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
        JSONObject response = peticion.getResult();
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
        messageProps = new HashMap();
        createRequestJSON("REMOVEUSER", null);

        Peticiones peticion = new Peticiones(requestJSON);
        JSONObject response = peticion.getResult();
        if (!response.isEmpty()) {
            if (((int) response.get("response")) == Constants.QUERY_SUCCESS) {
                writeMessages(new Object[]{
                    "perfil.msg.userDeleted",
                    "perfil.msg.userDeleted.title",
                    JOptionPane.INFORMATION_MESSAGE
                });
            } else {
                messageProps = null;
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
        JSONObject response = peticion.getResult();
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
                    response = peticion.getResult();

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
                    response = peticion.getResult();

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
            JSONObject response = peticion.getResult();

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
            JSONObject response = peticion.getResult();

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
        JSONObject response = peticion.getResult();

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
        JSONObject response = peticion.getResult();
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
        JSONObject response = peticion.getResult();
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
        JSONObject response = peticion.getResult();
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
        JSONObject response = peticion.getResult();
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
            JSONObject response = peticion.getResult();
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
        } else {
            writeMessages(new Object[]{
                "mail.empty.fields",
                "mail.empty.fields.title",
                JOptionPane.WARNING_MESSAGE
            });
        }
        return this;
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

    public HashMap getMessage() {
        return this.messageProps;
    }

    public Session getSession() {
        return this.session;
    }

    private static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        return obj.toString().equals("");
    }

    private void writeMessages(Object[] Props) {
        messageProps = new HashMap();
        messageProps.put("message",
                ReadProperties.props.getProperty(Props[0].toString()));
        messageProps.put("title",
                ReadProperties.props.getProperty(Props[0].toString()));
        messageProps.put("type", (int) Props[2]);
    }

    public static String generateDate() {
        Calendar c = new GregorianCalendar();
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month <= 9 ? "0" + month : month) + "-" + (day <= 9 ? "0" + day : day);
    }
}
