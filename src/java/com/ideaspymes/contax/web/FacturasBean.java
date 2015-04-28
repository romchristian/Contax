/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.web;

import java.io.File;
import java.io.Serializable;
import javax.inject.Inject;

import javax.inject.Named;

/**
 *
 * @author cromero
 */
@Named
@javax.faces.view.ViewScoped
public class FacturasBean implements Serializable {

    private String ruc;
    @Inject
    private ImagenController imagenController; 
    

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void mover() {
        String path = "C:\\Users\\cromero\\Desktop\\facturas";
        File theDir = new File(path + "\\" + ruc);

        

// if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir);
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("DIR created");
            }
        }

        try {
            String archivoActual = imagenController.getNombreArchivoSiguiente();
            
            File afile = new File(path + "\\" + archivoActual);

            if (afile.renameTo(new File(theDir + "\\" + afile.getName()))) {
                System.out.println("File is moved successful!");
            } else {
                System.out.println("File is failed to move!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
