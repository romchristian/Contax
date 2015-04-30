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
    private String nombreArchivoSiguiente;

    public String getNombreArchivoSiguiente() {
        if (nombreArchivoSiguiente == null) {

            String path = "C:\\Users\\cromero\\Desktop\\facturas";
            final File folder = new File(path);

            for (final File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {
                    System.out.println("File Entry: " + fileEntry.getName());
                    nombreArchivoSiguiente = fileEntry.getName();
                }
            }

        }
        return nombreArchivoSiguiente;
    }

    public void setNombreArchivoSiguiente(String nombreArchivoSiguiente) {
        this.nombreArchivoSiguiente = nombreArchivoSiguiente;
    }

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
            String archivoActual = nombreArchivoSiguiente;

            File afile = new File(path + "\\" + archivoActual);

            if (afile.renameTo(new File(theDir + "\\" + afile.getName()))) {
                System.out.println("File is moved successful!");
                nombreArchivoSiguiente = null;
            } else {
                System.out.println("File is failed to move!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
