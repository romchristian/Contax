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
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author cromero
 */
@Named
@ApplicationScoped
public class ImagenController implements Serializable {

    private boolean mostrarPorDefecto;

    public boolean isMostrarPorDefecto() {
        return mostrarPorDefecto;
    }

    public void setMostrarPorDefecto(boolean mostrarPorDefecto) {
        this.mostrarPorDefecto = mostrarPorDefecto;
    }

    public InputStream obtNextImage(String nombreArchivoSiguiente) {
        InputStream R = null;
        String path = "C:\\facturas";
        try {

            System.out.println("Archivo: " + nombreArchivoSiguiente);
            if (nombreArchivoSiguiente == null) {
                throw new Exception();
            }
            R = new FileInputStream(new File(path, nombreArchivoSiguiente));
        } catch (Exception ex) {
            String defaultpath = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("resources/img");
            try {
                R = new FileInputStream(new File(defaultpath, "nodisponible.png"));
            } catch (FileNotFoundException ex1) {
               // Logger.getLogger(ImagenController.class.getName()).log(Level.SEVERE, null, ex1);
            }

            //Logger.getLogger(FacturasBean.class.getName()).log(Level.SEVERE, null, ex);

        }
        return R;
    }

}
