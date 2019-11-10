package com.automotriz.Presentacion;

import static com.automotriz.Constantes.Global.global;
import com.automotriz.VO.AutoVO;
import com.automotriz.logger.Logger;
import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.automotriz.Constantes.Constants;

public class Frame_Vender extends javax.swing.JInternalFrame implements Constants<Frame_Vender> {
    
    private DefaultListModel imagesName;
    /*As key will be the Name's image
      and the value will be the abosotute path's image
     */
    private HashMap imagesPath;
    private AutoVO autoVO;
    
    public Frame_Vender() {
        initComponents();
        this.imagesPath = new HashMap();
        this.imagesName = new DefaultListModel();
        setVisible(true);
        initFrame(this);
    }
    
    public Frame_Vender(AutoVO autoVO) {
        initComponents();
        this.autoVO = autoVO;
        this.imagesPath = new HashMap();
        this.imagesName = new DefaultListModel();
        setVisible(true);
        initFrame(this);
        setAutoSelected();
    }
    
    @Override
    public void initFrame(Frame_Vender c) {
        String name = ReadProperties.props.getProperty("name.Vender");
        c.setName(name);
        c.setTitle(name);
        Logger.log("Starting " + c.getName() + " frame...");
        
        panelContent.setBackground(Color.decode(ReadProperties.props.getProperty("color.white")));
        panelForm.setBackground(Color.decode(ReadProperties.props.getProperty("color.grey")));
        txt_dueño.setText(global.getSession().getUsername().toUpperCase());
        setModeloValue();
        setMarcas();
        setCambios();
        setColores();
    }
    
    private void setModeloValue() {
        Calendar c = new GregorianCalendar();
        int year = c.get(Calendar.YEAR);
        spn_modelo.setValue(year);
        spn_km.setValue(0);
        //set a range to the modelo
        SpinnerNumberModel model1 = new SpinnerNumberModel(year, 1950, year, 1.0);
        spn_modelo.setModel(model1);
        
        SpinnerNumberModel model2 = new SpinnerNumberModel();
        model2.setMinimum(0);
        spn_km.setModel(model2);
        
        SpinnerNumberModel model3 = new SpinnerNumberModel();
        model3.setMinimum(1);
        model3.setValue(1);
        spn_precio.setModel(model3);
    }
    
    private void setMarcas() {
        cmb_marca.addItem("--Seleccionar--");
        String marcas[] = ReadProperties.props.getProperty("vender.marcas").split(";");
        for (String marca : marcas) {
            cmb_marca.addItem(marca);
        }
    }
    
    private void setCambios() {
        cmb_cambio.addItem("--Seleccionar--");
        String cambios[] = ReadProperties.props.getProperty("vender.cambios").split(";");
        for (String cambio : cambios) {
            cmb_cambio.addItem(cambio);
        }
    }
    
    private void setColores() {
        cmb_color.addItem("--Seleccionar--");
        String colores[] = ReadProperties.props.getProperty("vender.colores").split(";");
        for (String color : colores) {
            cmb_color.addItem(color);
        }
    }

    /**
     * Placing all the information about the selected car from Frame_MyCars
     * modal
     */
    private void setAutoSelected() {
        spn_modelo.setValue(autoVO.getModelo());
        spn_km.setValue(autoVO.getKilometros());
        if (validateMarca()) {
            cmb_marca.setSelectedItem(autoVO.getMarca());
        } else {
            cmb_marca.setEnabled(false);
            chb_otraMarca.setSelected(true);
            txt_otraMarca.setEnabled(true);
            txt_otraMarca.setText(autoVO.getMarca());
        }
        cmb_cambio.setSelectedItem(autoVO.getCambio());
        spn_precio.setValue(autoVO.getPrecio());
        txa_descripcion.setText(autoVO.getDescripcion());
        cmb_color.setSelectedItem(autoVO.getColor());
        setImages();
        //change the purpose of the button 'Publicar Auto'
        btn_publicarAuto.setText("Guardar Cambios");
    }
    
    private boolean validateMarca() {
        boolean isInCombo = false;
        for (String marca : ReadProperties.props.getProperty("vender.marcas").split(";")) {
            if (marca.equals(autoVO.getMarca())) {
                isInCombo = true;
            }
        }
        return isInCombo;
    }
    
    private void setImages() {
        if (!autoVO.getImagenes().equals("")) {
            imagesPath = new HashMap();
            for (String imgURL : autoVO.getImagenes().split(";")) {
                String imgName = imgURL.replace("Catalogo/", "").trim();
                addSelectedImage(imgName, imgURL);
            }
        }
    }

    /**
     * Shows an image chooser with the following suffixes: bmp, jpg, jpeg, wbmp,
     * png and gif
     */
    private void addImage() {
        if (this.imagesName.size() < 5) {
            JFileChooser chooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("Imagenes", ImageIO.getReaderFileSuffixes());
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Selecciona Imagen");
            chooser.setCurrentDirectory(new File("C:\\Users\\oliva\\OneDrive\\Pictures"));
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                addSelectedImage(chooser.getSelectedFile().getName(), chooser.getSelectedFile().getAbsolutePath());
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    ReadProperties.props.getProperty("vender.msg.imagesEnough"),
                    ReadProperties.props.getProperty("vender.msg.imagesEnough.title"),
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void addSelectedImage(String imageName, String path) {
        this.imagesPath.put(imageName, path);
        imagesName.addElement(imageName);
        this.lst_images.setModel(imagesName);
    }
    
    private void removeSelectedImage() {
        imagesPath.remove(lst_images.getSelectedValue());
        ((DefaultListModel) lst_images.getModel()).remove(lst_images.getSelectedIndex());
        btn_eliminarImage.setEnabled(false);
        btn_preview.setEnabled(false);
    }
    
    private void publicarAuto() {
        Validacion validacion = new Validacion(new Object[]{
            Double.parseDouble(spn_modelo.getValue().toString()),
            spn_km.getValue().toString(),
            cmb_marca.getSelectedItem().toString(),
            txt_otraMarca.getText(),
            cmb_cambio.getSelectedItem().toString(),
            spn_precio.getValue().toString(),
            cmb_color.getSelectedItem().toString(),
            txa_descripcion.getText(),
            imagesPath,
            global.getSession().getId()
        }).saveAutomobile(false);
        
        HashMap message = validacion.getMessage();
        if (message != null) {
            JOptionPane.showMessageDialog(this,
                    message.get("message").toString(),
                    message.get("title").toString(),
                    Integer.parseInt(message.get("type").toString())
            );
            if (Integer.parseInt(message.get("type").toString()) == JOptionPane.INFORMATION_MESSAGE) {
                clearForm();
            }
        }
    }
    
    private void clearForm() {
        spn_modelo.setValue(0);
        spn_km.setValue(0);
        cmb_cambio.setSelectedItem("--Seleccionar--");
        cmb_cambio.setEnabled(true);
        txt_otraMarca.setText(null);
        txt_otraMarca.setEnabled(false);
        chb_otraMarca.setSelected(false);
        cmb_marca.setSelectedItem("--Seleccionar--");
        cmb_color.setSelectedItem("--Seleccionar--");
        spn_precio.setValue(1);
        txa_descripcion.setText(null);
        imagesPath = new HashMap();
        imagesName = new DefaultListModel();
        lst_images.setModel(imagesName);
        btn_publicarAuto.setText("Publicar Auto");
    }
    
    private void modificarAuto() {
        new Frame_MyCars(global.getParent(), true);
    }
    
    private void seePreview() {
        String imagePath = imagesPath.get(lst_images.getSelectedValue()).toString();
        new Frame_PreviewImage(global.getParent(), true, imagePath);
    }
    
    private void saveChanges() {
        Validacion validacion = new Validacion(new Object[]{
            Double.parseDouble(spn_modelo.getValue().toString()),
            spn_km.getValue().toString(),
            cmb_marca.getSelectedItem().toString(),
            txt_otraMarca.getText(),
            cmb_cambio.getSelectedItem().toString(),
            spn_precio.getValue().toString(),
            cmb_color.getSelectedItem().toString(),
            txa_descripcion.getText(),
            imagesPath,
            autoVO.getId()
        }).saveAutomobile(true);
        
        HashMap message = validacion.getMessage();
        if (message != null) {
            JOptionPane.showMessageDialog(this,
                    message.get("message").toString(),
                    message.get("title").toString(),
                    Integer.parseInt(message.get("type").toString())
            );
            if (Integer.parseInt(message.get("type").toString()) == JOptionPane.INFORMATION_MESSAGE) {
                clearForm();
            }
        }
    }
    
    private void generateReport() {
        Connection cnn = Validacion.requestSQLConnection();
        if (cnn != null) {
            new Report("Autos", cnn).generateReport();
        } else {
            JOptionPane.showMessageDialog(this,
                    ReadProperties.props.getProperty("usuario.msg.error.reporte"),
                    ReadProperties.props.getProperty("usuario.msg.error.reporte.title"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void exportCSVTemplate() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(
                ReadProperties.props.getProperty("auto.csv.name")));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            Excel csv = new Excel(chooser.getSelectedFile().getAbsolutePath());
            csv.setHeader(new String[]{"Modelo", "Kilometros", "Marca", "Cambio", "Precio", "Color", "Descripcion"});
            if (csv.writeCSV()) {
                JOptionPane.showMessageDialog(this,
                        ReadProperties.props.getProperty("msg.export.csv.template"),
                        ReadProperties.props.getProperty("msg.export.csv.template.title"),
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
    
    private void importCSVTemplate() {
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Excel CSV file", "csv");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Selecciona CSV");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String fileURL = chooser.getSelectedFile().getAbsolutePath();
            Excel csv = new Excel(fileURL);
            String[] data = csv.readCSV();
            if (data != null) {
                /*
                start validating data types as ints
                and if the value at index exits
                 */
                spn_modelo.setValue(isNumber(indexExists(data, 0) ? data[0] : "") ? Integer.parseInt(data[0]) : 0);
                //spn_modelo.setValue(isNumber(data[0]) ? Integer.parseInt(data[0]) : 0);
                spn_km.setValue(isNumber(indexExists(data, 1) ? data[1] : "") ? Integer.parseInt(data[1]) : 0);
                //spn_km.setValue(isNumber(data[1]) ? Integer.parseInt(data[1]) : 0);

                if (indexExists(data, 2)) {
                    if (isInCombo(data[2])) {
                        String marca = ("" + data[2].charAt(0)).toUpperCase() + data[2].substring(1);
                        System.out.println(marca);
                        cmb_marca.setSelectedItem(marca);
                    } else {
                        txt_otraMarca.setText(data[2]);
                        chb_otraMarca.setSelected(true);
                        cmb_marca.setEnabled(false);
                        cmb_marca.setSelectedItem("--Seleccionar--");
                    }
                }
                
                if (indexExists(data, 3)) {
                    String cambio = ("" + data[3].charAt(0)).toUpperCase() + data[3].substring(1);
                    cmb_cambio.setSelectedItem(cambio);
                }

                //spn_precio.setValue(isNumber(data[4]) ? Integer.parseInt(data[4]) : 0);
                spn_precio.setValue(isNumber(indexExists(data, 4) ? data[4] : "") ? Integer.parseInt(data[4]) : 0);
                
                if (indexExists(data, 5)) {
                    String color = ("" + data[5].charAt(0)).toUpperCase() + data[5].substring(1);
                    cmb_color.setSelectedItem(color);
                }
                
                if (indexExists(data, 6)) {
                    txa_descripcion.setText(data[6]);
                }
            }
        }
    }
    
    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            return false;
        }
    }

    /**
     *
     * @return
     */
    private boolean indexExists(Object[] obj, int index) {
        try {
            Object m = obj[index];
            return true;
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            return false;
        }
    }
    
    private boolean isInCombo(String marcaCSV) {
        String[] marcas = ReadProperties.props.getProperty("vender.marcas").split(";");
        for (String marca : marcas) {
            if (marca.equalsIgnoreCase(marcaCSV)) {
                return true;
            }
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelForm = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        spn_modelo = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        spn_km = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        cmb_marca = new javax.swing.JComboBox<>();
        chb_otraMarca = new javax.swing.JCheckBox();
        txt_otraMarca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmb_cambio = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        spn_precio = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_descripcion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_dueño = new javax.swing.JTextField();
        cmb_color = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btn_selectImage = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lst_images = new javax.swing.JList<>();
        btn_eliminarImage = new javax.swing.JButton();
        btn_publicarAuto = new javax.swing.JButton();
        btn_preview = new javax.swing.JButton();
        btn_modificarAuto = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_options = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel1.setText("Completa el siguiente formulario para proceder con la publicacion de la venta de tu auto");

        panelForm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Modelo");

        spn_modelo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Kilometros");

        spn_km.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("Marca");

        cmb_marca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        chb_otraMarca.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chb_otraMarca.setText("Mi marca no aparece en la lista");
        chb_otraMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb_otraMarcaActionPerformed(evt);
            }
        });

        txt_otraMarca.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_otraMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_otraMarca.setEnabled(false);
        txt_otraMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_otraMarcaKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("Cambio");

        cmb_cambio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Precio");

        spn_precio.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setText("$");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setText("Descripcion");

        txa_descripcion.setColumns(20);
        txa_descripcion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txa_descripcion.setRows(5);
        txa_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txa_descripcionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txa_descripcion);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("Color");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setText("Imagenes");

        txt_dueño.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_dueño.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_dueño.setEnabled(false);

        cmb_color.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setText("Dueño del Auto");

        btn_selectImage.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_selectImage.setText("Imagen");
        btn_selectImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_selectImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selectImageActionPerformed(evt);
            }
        });

        lst_images.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lst_images.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lst_imagesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lst_images);

        btn_eliminarImage.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_eliminarImage.setText("Eliminar");
        btn_eliminarImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_eliminarImage.setEnabled(false);
        btn_eliminarImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarImageActionPerformed(evt);
            }
        });

        btn_publicarAuto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_publicarAuto.setText("Publicar Auto");
        btn_publicarAuto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_publicarAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_publicarAutoActionPerformed(evt);
            }
        });

        btn_preview.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_preview.setText("Preview");
        btn_preview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_preview.setEnabled(false);
        btn_preview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_previewActionPerformed(evt);
            }
        });

        btn_modificarAuto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_modificarAuto.setText("Modificar Auto");
        btn_modificarAuto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_modificarAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarAutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(46, 46, 46)
                                .addComponent(cmb_cambio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spn_modelo)
                                    .addComponent(spn_km)
                                    .addComponent(cmb_marca, 0, 170, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFormLayout.createSequentialGroup()
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addGap(59, 59, 59)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_color, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelFormLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spn_precio)))))
                        .addGap(27, 27, 27)
                        .addComponent(txt_otraMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chb_otraMarca)
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addComponent(btn_publicarAuto)
                                .addGap(18, 18, 18)
                                .addComponent(btn_modificarAuto)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_eliminarImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_preview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_selectImage, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dueño, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(spn_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(spn_km, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_otraMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chb_otraMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmb_cambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(spn_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cmb_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_publicarAuto)
                            .addComponent(btn_modificarAuto))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_dueño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_selectImage)
                                    .addComponent(jLabel10))
                                .addGap(32, 32, 32)
                                .addComponent(btn_eliminarImage)
                                .addGap(36, 36, 36)
                                .addComponent(btn_preview)
                                .addContainerGap(85, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        menu_options.setText("Opciones");
        menu_options.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_options.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/reporte.png"))); // NOI18N
        jMenuItem2.setText("Generar Reporte");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu_options.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/download.png"))); // NOI18N
        jMenuItem3.setText("Exportar Template");
        jMenuItem3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menu_options.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/automotriz/Presentacion/img/importar.png"))); // NOI18N
        jMenuItem4.setText("Importar Datos");
        jMenuItem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menu_options.add(jMenuItem4);

        jMenuBar1.add(menu_options);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chb_otraMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb_otraMarcaActionPerformed
        if (chb_otraMarca.isSelected()) {
            txt_otraMarca.setEnabled(true);
            cmb_marca.setEnabled(false);
            cmb_marca.setSelectedItem("--Seleccionar--");
        } else {
            txt_otraMarca.setEnabled(false);
            cmb_marca.setEnabled(true);
        }
    }//GEN-LAST:event_chb_otraMarcaActionPerformed

    private void btn_selectImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selectImageActionPerformed
        addImage();
    }//GEN-LAST:event_btn_selectImageActionPerformed

    private void btn_eliminarImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarImageActionPerformed
        removeSelectedImage();
    }//GEN-LAST:event_btn_eliminarImageActionPerformed

    private void lst_imagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lst_imagesMouseClicked
        if (!this.imagesPath.isEmpty()) {
            btn_eliminarImage.setEnabled(true);
            btn_preview.setEnabled(true);
        }
    }//GEN-LAST:event_lst_imagesMouseClicked

    private void btn_publicarAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_publicarAutoActionPerformed
        if (btn_publicarAuto.getText().equals("Publicar Auto")) {
            publicarAuto();
        } else {
            saveChanges();
        }
    }//GEN-LAST:event_btn_publicarAutoActionPerformed

    private void btn_previewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_previewActionPerformed
        seePreview();
    }//GEN-LAST:event_btn_previewActionPerformed

    private void btn_modificarAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarAutoActionPerformed
        modificarAuto();
    }//GEN-LAST:event_btn_modificarAutoActionPerformed

    private void txa_descripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txa_descripcionKeyTyped
        if (txa_descripcion.getText().length() == 245 || evt.getKeyChar() == '\'') {
            evt.consume();
        }
    }//GEN-LAST:event_txa_descripcionKeyTyped

    private void txt_otraMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_otraMarcaKeyTyped
        if (txt_otraMarca.getText().length() == 28) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_otraMarcaKeyTyped

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        generateReport();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        exportCSVTemplate();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        importCSVTemplate();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminarImage;
    private javax.swing.JButton btn_modificarAuto;
    private javax.swing.JButton btn_preview;
    private javax.swing.JButton btn_publicarAuto;
    private javax.swing.JButton btn_selectImage;
    private javax.swing.JCheckBox chb_otraMarca;
    private javax.swing.JComboBox<String> cmb_cambio;
    private javax.swing.JComboBox<String> cmb_color;
    private javax.swing.JComboBox<String> cmb_marca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lst_images;
    private javax.swing.JMenu menu_options;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelForm;
    private javax.swing.JSpinner spn_km;
    private javax.swing.JSpinner spn_modelo;
    private javax.swing.JSpinner spn_precio;
    private javax.swing.JTextArea txa_descripcion;
    private javax.swing.JTextField txt_dueño;
    private javax.swing.JTextField txt_otraMarca;
    // End of variables declaration//GEN-END:variables
}
