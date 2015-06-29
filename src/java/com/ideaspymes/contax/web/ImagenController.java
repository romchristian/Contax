/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.web;

import com.ideaspymes.contax.utils.ArchivoUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author cromero
 */
@Named
@ApplicationScoped
public class ImagenController implements Serializable {

    private boolean mostrarPorDefecto;

    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isMostrarPorDefecto() {
        return mostrarPorDefecto;
    }

    public void setMostrarPorDefecto(boolean mostrarPorDefecto) {
        this.mostrarPorDefecto = mostrarPorDefecto;
    }

    public InputStream obtNextImage(String nombreArchivoSiguiente) {
        InputStream R = null;
        String path = ArchivoUtil.PATH;
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

    public InputStream obtNextImage() {
        InputStream R = null;
        String path = ArchivoUtil.PATH;
        try {

            System.out.println("Archivo Imagen: " + imagen);
            if (imagen == null) {
                throw new Exception();
            }
            R = new FileInputStream(new File(path, imagen));
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

    public StreamedContent obtSiguienteImagen() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        String path = ArchivoUtil.PATH;

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {

            return new DefaultStreamedContent();
        } else {

            String nombreImagen = context.getExternalContext().getRequestParameterMap().get("nombreAchivo");
            System.out.println("Nombre Archivo : " + nombreImagen);
            System.out.println("Imagen : " + imagen);

            if (imagen != null && imagen.length() > 0) {
                FileInputStream fis = new FileInputStream(new File(path, imagen));
                return new DefaultStreamedContent(fis);
            } else {
                String defaultpath = FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRealPath("resources/img");
                FileInputStream fis = new FileInputStream(new File(defaultpath, "nodisponible.png"));
                return new DefaultStreamedContent(fis);
            }

        }
    }

}
