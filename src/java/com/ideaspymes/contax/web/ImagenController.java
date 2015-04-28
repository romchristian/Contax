/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author cromero
 */
@Named
@ApplicationScoped
public class ImagenController implements Serializable {

    private String nombreArchivoSiguiente;

    public String getNombreArchivoSiguiente() {
        return nombreArchivoSiguiente;
    }

    public void setNombreArchivoSiguiente(String nombreArchivoSiguiente) {
        this.nombreArchivoSiguiente = nombreArchivoSiguiente;
    }

    public InputStream obtNextImage() {
        InputStream R = null;
        String path = "C:\\Users\\cromero\\Desktop\\facturas";
        try {
            final File folder = new File(path);
            

            for (final File fileEntry : folder.listFiles()) {
                System.out.println("File Entry: " + fileEntry.getName());
                nombreArchivoSiguiente = fileEntry.getName();
            }
            
            System.out.println("Archivo: " + nombreArchivoSiguiente);
            R = new FileInputStream(new File(path, nombreArchivoSiguiente));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacturasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return R;
    }

   
}