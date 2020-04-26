package com.automotriz.Presentacion;

import java.util.HashMap;
import javax.swing.JOptionPane;
import com.automotriz.VO.AutoVO;
import com.automotriz.logger.Logger;
import com.automotriz.VO.SessionVO;
import com.automotriz.VO.UsuarioVO;
import com.automotriz.VO.ComentarioVO;
import com.automotriz.VO.MailVO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import com.automotriz.Constantes.Constants;
import com.automotriz.Negocio.Request;
import com.automotriz.Negocio.Response;
import java.sql.ResultSet;
import java.util.List;

public class Validacion implements Runnable {

    public static final int ATTEMPTS_ALLOWED = 5;
    private Object[] data;
    private SessionVO session;
    private UsuarioVO usuario;
    private AutoVO auto;
    private ComentarioVO comentario;
    private MailVO mail;
    private ArrayList<UsuarioVO> usuariosVO;
    private ArrayList<AutoVO> autosVO;
    private ArrayList<ComentarioVO> comentariosVO;
    private Response response;
    public static Thread hiloVaidacion;

    /**
     * askAdminRights() has a method that needs to be handle by a thread
     */
    @Override
    public void run() {
    }

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
     * @param reportTitle
     */
    public void requestReport(String reportTitle) {
        initializeHandler(Constants.DB_CONNECTION, null);
        Connection cnn = response.getConnection();
        if (cnn != null) {
            new Report(reportTitle, cnn).generateReport();
        } else {
            JOptionPane.showMessageDialog(null,
                    ReadProperties.props.getProperty("usuario.msg.error.reporte"),
                    ReadProperties.props.getProperty("usuario.msg.error.reporte.title"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * validate the object passed to the constructor positions: 0 -> username
     * input, 1 -> length field, 2 -> input keyEvent
     *
     * @return
     */
    public void validateInputLength() {
        if (isEmpty(data[0])) {
            return;
        }

        if (data[0].toString().length() >= Integer.parseInt(data[1].toString())) {
            ((java.awt.event.KeyEvent) data[2]).consume();
            writeMessages(new Object[]{
                "signin.msg.warn.fieldLength",
                "signin.msg.warn.fieldLength.title",
                JOptionPane.WARNING_MESSAGE
            });
        }
    }

    /**
     * Initialize the class Request in order to prepare a new database operation
     *
     * @param operation The operation's name to execute
     * @param objVO The data needed to execute the operation
     * @return A new instance of Response class
     */
    private void initializeHandler(String operation, Object objVO) {
        Logger.log("Creating new Handler");
        Request handler;
        if (objVO != null) {
            handler = new Request(operation, objVO);
        } else {
            handler = new Request(operation);
        }
        this.response = handler.createNewOperation();
    }

    /**
     *
     * @return True if the login access is successful, otherwise returns false
     * @throws Exception
     */
    public SessionVO validarLogIn() throws Exception {
        Logger.log("validating logging");
        if (isEmpty(data[0]) || isEmpty(data[1])) {
            writeMessages(new Object[]{
                "login.msg.error.empty",
                "login.msg.error.empty.title",
                JOptionPane.ERROR_MESSAGE
            });
            Logger.error("Username and/or password empty");
            return null;
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
                if (rs.getString("estatus").equals("DISABLED")) {
                    writeMessages(new Object[]{
                        "login.msg.user.disabled",
                        "login.msg.user.disabled.title",
                        JOptionPane.ERROR_MESSAGE
                    });
                    return null;
                }
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
            if (this.usuario.getEstatus().equals("BLOCKED")) {
                Logger.log("The user is blocked");
                writeMessages(new Object[]{
                    "login.msg.bloquear",
                    "login.msg.bloquear.title",
                    JOptionPane.ERROR_MESSAGE
                });
                return null;
            }
            if (!this.usuario.getPerfil().equals(data[2].toString())) {
                Logger.log("The user's profile does not match the current profile");
                writeMessages(new Object[]{
                    "login.msg.error.profile",
                    "login.msg.error.profile.title",
                    JOptionPane.WARNING_MESSAGE
                });
                return null;
            } else {
                if (this.usuario.getIntentos() > 0) {
                    Logger.log("Reseting the number of attempts");
                    this.usuario.setIntentos(0);
                    initializeHandler(Constants.UPDATEINTENTOS, usuario);
                }
                //If the login is successful a new SessionVO is created
                Logger.log("Creating the user's session");
                return this.usuario.createSession();
            }
        } /* Incorrect Credentials */ else if (response.getStatus() == Response.STATUS_RESULTSET_EMPTY) {
            // Verify if the username exists in order to register the attempts
            Logger.log("ResultSet empty, verifying if the username exits");
            usuario = new UsuarioVO();
            usuario.setUsuario(data[0].toString());
            initializeHandler(Constants.USERNAMEEXISTS, usuario);
            if (response.getStatus() == Response.STATUS_SUCCESS) {
                //Update the number of attempts 
                Logger.log("The username exits, counting the attempts");
                ResultSet rs = response.getResultSet();
                rs.next();
                usuario.setIntentos(rs.getInt("intentos") + 1);
                usuario.setId(rs.getInt("id"));
                if (usuario.getIntentos() > 5) {
                    Logger.log("The user is blocked");
                    writeMessages(new Object[]{
                        "login.msg.bloquear",
                        "login.msg.bloquear.title",
                        JOptionPane.ERROR_MESSAGE
                    });
                    return null;
                }
                initializeHandler(Constants.UPDATEINTENTOS, usuario);
                if (response.getStatus() == Response.STATUS_UPDATED) {
                    Logger.log("The credentials are incorrect");
                    writeMessages(new Object[]{
                        "login.msg.error.auth",
                        "login.msg.error.auth.title",
                        JOptionPane.ERROR_MESSAGE
                    });

                    if (usuario.getIntentos() == this.ATTEMPTS_ALLOWED) {
                        initializeHandler(Constants.BLOCKUSER, usuario);
                        if (response.getStatus() == Response.STATUS_UPDATED) {
                            Logger.log("The user will be blocked");
                            writeMessages(new Object[]{
                                "login.msg.bloquear",
                                "login.msg.bloquear.title",
                                JOptionPane.ERROR_MESSAGE
                            });
                        }
                    }
                }
                return null;
            } else {
                Logger.log("The username does not exits");
                writeMessages(new Object[]{
                    "login.msg.error.username",
                    "login.msg.error.username.title",
                    JOptionPane.ERROR_MESSAGE
                });
                return null;
            }
        } else if (response.getStatus() == Response.STATUS_FAILURE) {
            writeMessages(new Object[]{
                "login.msg.error.conndb",
                "login.msg.error.conndb.title",
                JOptionPane.ERROR_MESSAGE
            });
            return null;
        }
        return null;
    }

    /**
     * Validates if the username already exists
     *
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
            } else if (response.getStatus() == Response.STATUS_RESULTSET_EMPTY) {
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

    /**
     *
     * @param form
     * @param operation
     * @return
     */
    public boolean validateForm(String form, String operation) {
        boolean allow = false;
        Logger.log("validating " + form + " form ");
        if (isEmpty(data[0]) || isEmpty(data[1]) || isEmpty(data[2]) || isEmpty(data[3])
                || isEmpty(data[4]) || isEmpty(data[5])) {
            writeMessages(new Object[]{
                "signin.msg.error.emptyFields",
                "signin.msg.error.emptyFields.title",
                JOptionPane.WARNING_MESSAGE
            });
            return false;
        }
        if (data[3].toString().equals("Administrador") && operation.equals(Constants.CREATENEWUSER)) {
            allow = askAdminRights();
        } else {
            allow = true;
        }

        //admin rigths
        if (allow) {
            usuario = new UsuarioVO();
            usuario.setUsuario(data[0].toString());
            usuario.setContraseña(data[1].toString());
            usuario.setCorreo(data[2].toString());
            usuario.setPerfil(data[3].toString());
            usuario.setTelefono(data[4].toString());
            usuario.setNombre(data[5].toString());
            initializeHandler(operation, usuario);

            if (response.getStatus() == Response.STATUS_INSERTED || response.getStatus() == Response.STATUS_UPDATED) {
                writeMessages(new Object[]{
                    operation.equals(Constants.CREATENEWUSER)
                    ? "signin.msg.userCreated" : "perfil.msg.userUpdated",
                    operation.equals(Constants.CREATENEWUSER)
                    ? "signin.msg.userCreated.title" : "perfil.msg.userUpdated.title",
                    JOptionPane.INFORMATION_MESSAGE
                });
            }
        }
        return true;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean validateAdminRights(String username, String password) {
        /*for this action is a requirement fot another admin
        to allow this operation*/
        if (isEmpty(username) || isEmpty(password)) {
            writeMessages(new Object[]{
                "signin.msg.error.emptyFields",
                "signin.msg.error.emptyFields.title",
                JOptionPane.WARNING_MESSAGE
            });
            return false;
        }
        usuario = new UsuarioVO();
        usuario.setUsuario(username);
        usuario.setContraseña(password);
        initializeHandler(Constants.VALIDATEADMIN, usuario);
        if (response.getStatus() == Response.STATUS_RESULTSET_EMPTY) {
            writeMessages(new Object[]{
                "signin.msg.error.permissionDenied",
                "signin.msg.error.permissionDenied.title",
                JOptionPane.ERROR_MESSAGE
            });
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public boolean removeUser() {
        session = (SessionVO) data[0];
        initializeHandler(Constants.REMOVEUSER, session);

        if (response.getStatus() == Response.STATUS_UPDATED) {
            writeMessages(new Object[]{
                "perfil.msg.userDeleted",
                "perfil.msg.userDeleted.title",
                JOptionPane.INFORMATION_MESSAGE
            });
        }
        return true;
    }

    /**
     *
     * @param model
     * @return
     * @throws Exception
     */
    public DefaultTableModel filtrarUsuarios(DefaultTableModel model) throws Exception {
        if (this.data == null) {
            initializeHandler(Constants.FILTRARTODOSUSUARIOS, null);
        } else {
            usuario = new UsuarioVO();
            usuario.setUsuario(data[0].toString());
            usuario.setTelefono(data[1].toString());
            usuario.setEstatus(data[2].toString());
            usuario.setPerfil(data[3].toString());
            initializeHandler(Constants.FILTRARUSUARIOS, usuario);
        }

        if (response.getStatus() == Response.STATUS_SUCCESS) {
            usuariosVO = new ArrayList<>();
            ResultSet rs = response.getResultSet();
            Logger.log("Creating rows for the table");
            while (rs.next()) {
                UsuarioVO user = new UsuarioVO();
                user.setUsuario(rs.getString("usuario"));
                user.setCorreo(rs.getString("correo"));
                user.setTelefono(rs.getString("telefono"));
                user.setEstatus(rs.getString("estatus"));
                user.setPerfil(rs.getString("perfil"));
                if (model != null) {
                    model.addRow(new Object[]{
                        user.getUsuario(),
                        user.getCorreo(),
                        user.getPerfil(),
                        user.getEstatus(),
                        user.getTelefono()
                    });
                }
                usuariosVO.add(user);
            }
            return model;
        } else if (response.getStatus() == Response.STATUS_RESULTSET_EMPTY) {
            writeMessages(new Object[]{
                "usuarios.msg.userNotFound",
                "usuarios.msg.userNotFound.title",
                JOptionPane.INFORMATION_MESSAGE
            });
        }
        return null;
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
    public void updateUserAsAdmin() {
        if (/*CASE 1*/(data[0].toString().equals("") && data[2].toString().equals("ACTIVO") && data[3].toString().equals("DISABLED"))
                || /*CASE 2*/ (!data[0].toString().equals("") && data[2].toString().equals("ACTIVO") && data[3].toString().equals("BLOCKED"))
                || /*CASE 3*/ (data[0].toString().equals("") && data[2].toString().equals("DISABLED") && data[3].toString().equals("BLOCKED"))
                || /*CASE 4*/ (data[0].toString().equals("") && data[2].toString().equals("DISABLED") && data[3].toString().equals("ACTIVO"))) {

            //set the username to position 1, in order to form the query
            Object codigo = data[0];
            data[0] = data[1];
            data[1] = codigo;

            usuario = new UsuarioVO();
            usuario.setUsuario(data[0].toString());
            usuario.setEstatus(data[2].toString());
            switch (data[2].toString()) {
                case "DISABLED":
                    initializeHandler(Constants.REMOVEUSER, usuario);
                    if (response.getStatus() == Response.STATUS_UPDATED) {
                        writeMessages(new Object[]{
                            "editUser.msg.userdisabled",
                            "editUser.msg.userdisabled.title",
                            JOptionPane.INFORMATION_MESSAGE
                        });
                    }
                    break;
                case "ACTIVO":
                    initializeHandler(Constants.ACTIVARUSER, usuario);

                    String bodyMessage = ReadProperties.props.getProperty("email.msg.userActived")
                            .replace("*", new Hashing(codigo.toString()).decrypt());
                    //send the notification to the user
                    mail = new MailVO();
                    mail.setDestinatario(data[4].toString());
                    mail.setAsunto("VEHICLE SELL| USUARIO REACTIVADO");
                    mail.setMensaje(bodyMessage);
                    initializeHandler(Constants.SENDMAIL, mail);

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
        }
    }

    /**
     *
     * @return the object of the class Validacion
     * @param isAnUpdate will execute operations related to an UPDATE to a car,
     * otherwise will execute an INSERT
     */
    public boolean saveAutomobile(boolean isAnUpdate) {
        if (data[2].toString().equals("--Seleccionar--")) {
            if (data[3].toString().equals("")) {
                writeMessages(new Object[]{
                    "vender.msg.invalid.marca",
                    "vender.msg.invalid.marca.title",
                    JOptionPane.WARNING_MESSAGE
                });
                return false;
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
            return false;
        }

        if (data[6].toString().equals("--Seleccionar--")) {
            writeMessages(new Object[]{
                "vender.msg.invalid.color",
                "vender.msg.invalid.color.title",
                JOptionPane.WARNING_MESSAGE
            });
            return false;
        }

        if (data[7].toString().equals("")) {
            writeMessages(new Object[]{
                "vender.msg.invalid.descripcion",
                "vender.msg.invalid.descripcion.title",
                JOptionPane.WARNING_MESSAGE
            });
            return false;
        }

        auto = new AutoVO();
        auto.setModelo((int) data[0]);
        auto.setKilometros((int) data[1]);
        auto.setMarca(data[2].toString());
        auto.setCambio(data[4].toString());
        auto.setPrecio((double) data[5]);
        auto.setColor(data[6].toString());
        auto.setDescripcion(data[7].toString());
        auto.setImagenes(data[8].toString());
        auto.setId_usuario((int) data[9]);
        if (isAnUpdate) {

            initializeHandler(Constants.UPDATEAUTO, auto);

            if (response.getStatus() == Response.STATUS_UPDATED) {
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
                return false;
            }
        } else {
            initializeHandler(Constants.INSERTNEWAUTO, auto);

            if (response.getStatus() == Response.STATUS_SUCCESS) {
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
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param model
     * @return
     * @throws Exception
     */
    public DefaultTableModel listUserAutos(DefaultTableModel model) throws Exception {
        session = new SessionVO();
        session.setId(Integer.parseInt(data[0].toString()));
        initializeHandler(Constants.GETCARS, session);
        if (response.getStatus() == Response.STATUS_SUCCESS) {
            autosVO = new ArrayList<>();
            ResultSet rs = response.getResultSet();
            while (rs.next()) {
                auto = new AutoVO();
                auto.setId(rs.getInt("id"));
                auto.setMarca(rs.getString("marca"));
                auto.setModelo(rs.getInt("modelo"));
                auto.setPrecio(rs.getDouble("precio"));
                auto.setColor(rs.getString("color"));
                auto.setEstatus(rs.getString("estatus"));
                if (model != null) {
                    model.addRow(new Object[]{
                        auto.getId(),
                        auto.getMarca(),
                        auto.getModelo(),
                        auto.getPrecio(),
                        auto.getColor(),
                        auto.getEstatus()
                    });
                }
                autosVO.add(auto);
            }
        } else {
            writeMessages(new Object[]{
                "usuarios.msg.userNotFound",
                "usuarios.msg.userNotFound.title",
                JOptionPane.INFORMATION_MESSAGE
            });
        }
        return model;
    }

    /**
     * txt_nombre.getText(), chb_anonimo.isSelected(),
     * txa_comentarios.getText(), valoracion
     *
     * @return
     */
    public boolean submitComentario() {
        if (data[0].toString().equals("")) {
            if (!Boolean.parseBoolean(data[1].toString())) {
                writeMessages(new Object[]{
                    "clientView.msg.error.emptyName",
                    "clientView.msg.error.empty.title",
                    JOptionPane.WARNING_MESSAGE
                });
                return false;
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
                return false;
            }
        }
        comentario = new ComentarioVO();
        comentario.setNombre(data[0].toString());
        comentario.setComentario(data[2].toString());
        comentario.setValoracion(Integer.parseInt(data[3].toString()));
        comentario.setId_usuario(Integer.parseInt(data[4].toString()));
        comentario.setFecha(data[5].toString());
        initializeHandler(Constants.SUBMITCOMENTARIO, comentario);

        if (response.getStatus() == Response.STATUS_INSERTED) {
            writeMessages(new Object[]{
                "clientView.msg.submit.successfully",
                "clientView.msg.submit.successfully.title",
                JOptionPane.INFORMATION_MESSAGE
            });
        }
        return true;
    }

    /**
     *
     * @return @throws Exception
     */
    public List<ComentarioVO> getFeedBack() throws Exception {
        initializeHandler(Constants.GETFEEDBACK, auto);
        if (response.getStatus() == Response.STATUS_SUCCESS) {
            ResultSet rs = response.getResultSet();
            while (rs.next()) {
                comentario = new ComentarioVO();
                comentario.setNombre(rs.getString("nombre"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setValoracion(rs.getInt("valoracion"));
                comentario.setId_usuario(rs.getInt("id_usuario"));
                comentario.setFecha(rs.getString("fecha"));
                comentariosVO.add(comentario);
            }
        }
        return comentariosVO;
    }

    /**
     * Gets the whole catalogo not including the cars updated by the user logged
     *
     * @return
     */
    public ArrayList<AutoVO> getCatalogo() throws Exception {
        autosVO = new ArrayList<>();
        auto = new AutoVO();
        auto.setId_usuario(Integer.parseInt(data[0].toString()));
        initializeHandler(Constants.GETCATALOGO, auto);
        if (response.getStatus() == Response.STATUS_SUCCESS) {
            ResultSet rs = response.getResultSet();
            while (rs.next()) {
                auto = new AutoVO();
                auto.setCambio(rs.getString("cambio"));
                auto.setColor(rs.getString("color"));
                auto.setDescripcion(rs.getString("descripcion"));
                auto.setEstatus(rs.getString("estatus"));
                auto.setId(rs.getInt("id"));
                auto.setId_usuario(rs.getInt("id_usuario"));
                auto.setImagenes(rs.getString("imagenes"));
                auto.setKilometros(rs.getInt("kilometros"));
                auto.setMarca(rs.getString("marca"));
                auto.setModelo(rs.getInt("modelo"));
                auto.setPrecio(rs.getDouble("precio"));
                autosVO.add(auto);
            }
        }
        return autosVO;
    }

    /**
     *
     * @return @throws Exception
     */
    public UsuarioVO getVendedor() throws Exception {
        usuario = new UsuarioVO();
        usuario.setId(Integer.parseInt(data[0].toString()));
        initializeHandler(Constants.GETVENDEDOR, usuario);
        if (response.getStatus() == Response.STATUS_SUCCESS) {
            ResultSet rs = response.getResultSet();
            if (rs.next()) {
                usuario.setCorreo(rs.getString("correo"));
                usuario.setEstatus(rs.getString("estatus"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setNombre(rs.getString("nombre"));
            }
        }
        return usuario;
    }

    /**
     *
     * @return @throws Exception
     */
    public String getVendedorName() throws Exception {
        String vendedorName = "";
        if (!isEmpty(data[0])) {
            usuario = new UsuarioVO();
            usuario.setCorreo(data[0].toString());
            initializeHandler(Constants.GETVENDEDORNAME, usuario);
            if (response.getStatus() == Response.STATUS_SUCCESS) {
                ResultSet rs = response.getResultSet();
                if (rs.next()) {
                    vendedorName = rs.getString("nombre");
                }
            }
        }
        return vendedorName;
    }

    public boolean sendMailToVendedor() {
        if (!isEmpty(data[0]) && !isEmpty(data[1]) && !isEmpty(data[2])) {
            mail = new MailVO();
            mail.setDestinatario(data[0].toString());
            mail.setAsunto(data[1].toString());
            mail.setMensaje(data[2].toString());
            mail.setAttachFile(data[3]);
            initializeHandler(Constants.SENDMAIL, mail);
            /*update the vehicle's status, this vehicle will not be available in
            catalogo*/
            data = new Object[]{
                Constants.PROCCESS_PURCHASE,
                data[4]
            };
            auto = new AutoVO();
            auto.setId(Integer.parseInt(data[1].toString()));
            auto.setEstatus(data[0].toString());
            initializeHandler(Constants.UPDATEAUTOESTATUS, auto);
            return (response.getStatus() == Response.STATUS_UPDATED);
        } else {
            writeMessages(new Object[]{
                "mail.empty.fields",
                "mail.empty.fields.title",
                JOptionPane.WARNING_MESSAGE
            });
        }
        return false;
    }

    public ArrayList<AutoVO> filtrarAutos() throws Exception {
        autosVO = new ArrayList<>();
        if (!isEmpty(data[0]) || !isEmpty(data[1]) || !isEmpty(data[2]) || !isEmpty(data[3])) {
            initializeHandler(Constants.FILTRARAUTOS, auto);
            if (response.getStatus() == Response.STATUS_SUCCESS) {
                ResultSet rs = response.getResultSet();
                while (rs.next()) {
                    auto = new AutoVO();
                    auto.setCambio(rs.getString("cambio"));
                    auto.setColor(rs.getString("color"));
                    auto.setDescripcion(rs.getString("descripcion"));
                    auto.setEstatus(rs.getString("estatus"));
                    auto.setId(rs.getInt("id"));
                    auto.setId_usuario(rs.getInt("id_usuario"));
                    auto.setImagenes(rs.getString("imagenes"));
                    auto.setKilometros(rs.getInt("kilometros"));
                    auto.setMarca(rs.getString("marca"));
                    auto.setModelo(rs.getInt("modelo"));
                    auto.setPrecio(rs.getDouble("precio"));
                    autosVO.add(auto);
                }
            }
        }
        return autosVO;
    }

    public boolean updateStatusAuto() {
        if (!isEmpty(data[0]) || !isEmpty(data[1])) {
            auto = new AutoVO();
            auto.setEstatus(data[0].toString());
            auto.setId(Integer.parseInt(data[1].toString()));
            initializeHandler(Constants.UPDATEAUTOESTATUS, auto);
            
            if (response.getStatus() == Response.STATUS_UPDATED) {
                if(data[0].toString().equals(Constants.DELETED)){
                    writeMessages(new Object[]{
                        "msg.auto.deleted.success",
                        "msg.auto.deleted.success.title",
                        JOptionPane.INFORMATION_MESSAGE
                    });
                }else{
                    writeMessages(new Object[]{
                    "msg.update.status",
                        "msg.update.status.title",
                        JOptionPane.INFORMATION_MESSAGE
                    });
                }
                return true;
            }
        }
        return false;
    }

    public HashMap requestDatabaseTables() {
        initializeHandler(Constants.TABLESSCHEMA, null);
        if (response.getStatus() == Response.STATUS_SUCCESS) {
            return (HashMap) response.getObject();
        }
        return new HashMap();
    }

    public ArrayList<UsuarioVO> getUsuarios() {
        return usuariosVO;
    }

    public ArrayList<AutoVO> getAutos() {
        return autosVO;
    }

    private static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        return obj.toString().equals("");
    }

    private void writeMessages(Object[] Props) {
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
