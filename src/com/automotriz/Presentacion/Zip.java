package com.automotriz.Presentacion;

import com.automotriz.VO.UsuarioVO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import com.automotriz.Constantes.Constants;

public class Zip {

    private String zipPath;
    private ZipOutputStream out;
    private ZipInputStream in;

    public void newZip() throws Exception {
        chooseZipPath();
        out = new ZipOutputStream(new FileOutputStream(zipPath));
        in = new ZipInputStream(new FileInputStream(zipPath));
    }

    private void chooseZipPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showDialog(chooser, "Elegir Ruta") == JFileChooser.APPROVE_OPTION) {
            zipPath = chooser.getSelectedFile().getPath() + "\\" + Constants.ZIP_NAME;
        }
    }

    public void newFile(String fileName, Object content) throws Exception {
        ZipEntry e = new ZipEntry(fileName);
        out.putNextEntry(e);
        ObjectOutputStream o = new ObjectOutputStream(out);
        o.writeObject(content);
        o.close();
    }

    public void createZip() throws Exception {
        out.close();
    }

    public List<Object> readZipContent() throws Exception {
        ZipEntry e = null;
        List<Object> objs = new ArrayList<>();
        while ((e = in.getNextEntry()) != null) {
            ObjectInputStream i = new ObjectInputStream(in);
            objs.add(i.readObject());
        }
        return objs;
    }

    public static void main(String[] args) {
        try {
            ReadProperties.loadApplicationProps();
            Zip zip = new Zip();
            zip.newZip();
            zip.newFile("Usuarios." + Constants.BACKUP_FILES_EXTENSION, new UsuarioVO(0, "a", "e", "d", "v", "b", "f", "vc", "554"));
            //zip.newFile("Comentarios.mex", "Aqui van los comentarios");
            zip.createZip();

            //read the zip file
            List<Object> objs = zip.readZipContent();
            for (Object obj : objs) {
                if (obj instanceof UsuarioVO) {
                    System.out.println(((UsuarioVO) obj).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
