/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.web;

import com.ideaspymes.contax.facade.ContribuyenteDAO;
import com.ideaspymes.contax.facade.FacturaFacade;
import com.ideaspymes.contax.modelo.Clasificacion;
import com.ideaspymes.contax.modelo.Contribuyente;
import com.ideaspymes.contax.modelo.Factura;
import com.ideaspymes.contax.modelo.SubTipoGasto;
import com.ideaspymes.contax.modelo.SubTipoIngreso;
import com.ideaspymes.contax.modelo.SubTipoInversion;
import com.ideaspymes.contax.modelo.TipoFactura;
import com.ideaspymes.contax.modelo.TipoGasto;
import com.ideaspymes.contax.modelo.TipoImpuesto;
import com.ideaspymes.contax.modelo.TipoIngreso;
import com.ideaspymes.contax.modelo.TipoInversion;
import com.ideaspymes.contax.utils.JsfUtil;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

import javax.inject.Named;

/**
 *
 * @author cromero
 */
@Named
@javax.faces.view.ViewScoped
public class FacturasBean implements Serializable {

    @EJB
    private ContribuyenteDAO contribuyenteDAO;
    @EJB
    private FacturaFacade ejb;

    private String ruc;
    private int cvCliente;
    private String rucProveedor;
    private int cvProveedor;
    private String nombreArchivoSiguiente;
    private Factura actual;

    private TipoIngreso tipoIngresoSeleccionado;
    private SubTipoIngreso subTipoIngresoSeleccionado;

    private TipoGasto tipoGastoSeleccionado;
    private SubTipoGasto subTipoGastoSeleccionado;

    private TipoInversion tipoInversionSeleccionado;
    private SubTipoInversion subTipoInversionSeleccionado;
    

    
    public boolean isMuestraTipoFactura(){
        boolean R = false;
        if(getActual().getTipoImpuesto() == TipoImpuesto.IVA_GENERAL ||getActual().getTipoImpuesto() == TipoImpuesto.IVA_SIMPLIFICADO){
            R = true;
        }else{
            R = false;
        }
        return R;
    }
    
    
    public boolean isMuestraClasificacionIRP(){
        boolean R = false;
        if(getActual().getTipoImpuesto() == TipoImpuesto.IRP ||getActual().getTipoImpuesto() == TipoImpuesto.IRPC){
            R = true;
        }
        return R;
    }
    
    public boolean isMuestraIngresos(){
        boolean R;
        if((getActual().getTipoFactura() != null && getActual().getTipoFactura() == TipoFactura.VENTA) ||
                (getActual().getClasificacion() != null && getActual().getClasificacion() == Clasificacion.INGRESOS)){
            R = true;
        }else{
            R = false;
        }
        return R;
    }
    
    public List<SubTipoIngreso> getItemsSubTipoIngreso() {
        List<SubTipoIngreso> R = new ArrayList<>();
        if (tipoIngresoSeleccionado != null) {
            R = tipoIngresoSeleccionado.getSubTipoIngresos();
        }
        return R;
    }

    public List<SubTipoGasto> getItemsSubTipoGasto() {
        List<SubTipoGasto> R = new ArrayList<>();
        if (tipoGastoSeleccionado != null) {
            R = tipoGastoSeleccionado.getSubTipoGastos();
        }
        return R;
    }

    public List<SubTipoInversion> getItemsSubTipoInversion() {
        List<SubTipoInversion> R = new ArrayList<>();
        if (tipoInversionSeleccionado != null) {
            R = tipoInversionSeleccionado.getSubTipoInversiones();
        }
        return R;
    }

    public TipoIngreso getTipoIngresoSeleccionado() {
        return tipoIngresoSeleccionado;
    }

    public void setTipoIngresoSeleccionado(TipoIngreso tipoIngresoSeleccionado) {
        this.tipoIngresoSeleccionado = tipoIngresoSeleccionado;
    }

    public SubTipoIngreso getSubTipoIngresoSeleccionado() {
        return subTipoIngresoSeleccionado;
    }

    public void setSubTipoIngresoSeleccionado(SubTipoIngreso subTipoIngresoSeleccionado) {
        this.subTipoIngresoSeleccionado = subTipoIngresoSeleccionado;
    }

    public TipoGasto getTipoGastoSeleccionado() {
        return tipoGastoSeleccionado;
    }

    public void setTipoGastoSeleccionado(TipoGasto tipoGastoSeleccionado) {
        this.tipoGastoSeleccionado = tipoGastoSeleccionado;
    }

    public SubTipoGasto getSubTipoGastoSeleccionado() {
        return subTipoGastoSeleccionado;
    }

    public void setSubTipoGastoSeleccionado(SubTipoGasto subTipoGastoSeleccionado) {
        this.subTipoGastoSeleccionado = subTipoGastoSeleccionado;
    }

    public TipoInversion getTipoInversionSeleccionado() {
        return tipoInversionSeleccionado;
    }

    public void setTipoInversionSeleccionado(TipoInversion tipoInversionSeleccionado) {
        this.tipoInversionSeleccionado = tipoInversionSeleccionado;
    }

    public SubTipoInversion getSubTipoInversionSeleccionado() {
        return subTipoInversionSeleccionado;
    }

    public void setSubTipoInversionSeleccionado(SubTipoInversion subTipoInversionSeleccionado) {
        this.subTipoInversionSeleccionado = subTipoInversionSeleccionado;
    }

    public Factura getActual() {
        if (actual == null) {
            actual = new Factura();
        }
        return actual;
    }

    public void setActual(Factura actual) {
        this.actual = actual;
    }

    public void buscarContribuyenteCliente() {
        Contribuyente c = contribuyenteDAO.getContribuyente(ruc);
        if (c != null) {
            cvCliente = c.getCodigoVerificador();
            getActual().setRazonSocialCliente(c.getRazonSocial());
            getActual().setRucCliente(c.getRuc() + "-" + c.getCodigoVerificador());
        }
    }

    public void buscarContribuyenteProveedor() {
        Contribuyente c = contribuyenteDAO.getContribuyente(rucProveedor);
        if (c != null) {
            cvProveedor = c.getCodigoVerificador();
            getActual().setRazonSocialProveedor(c.getRazonSocial());
            getActual().setRucProveedor(c.getRuc() + "-" + c.getCodigoVerificador());
        }
    }

    public String getNombreArchivoSiguiente() {
        if (nombreArchivoSiguiente == null) {

            String path = "C:\\facturas";
            final File folder = new File(path);

            nombreArchivoSiguiente = "";
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

    public ContribuyenteDAO getContribuyenteDAO() {
        return contribuyenteDAO;
    }

    public void setContribuyenteDAO(ContribuyenteDAO contribuyenteDAO) {
        this.contribuyenteDAO = contribuyenteDAO;
    }

    public int getCvCliente() {
        return cvCliente;
    }

    public void setCvCliente(int cvCliente) {
        this.cvCliente = cvCliente;
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

   

    public int getCvProveedor() {
        return cvProveedor;
    }

    public void setCvProveedor(int cvProveedor) {
        this.cvProveedor = cvProveedor;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void guardar() {
        try {
            if (actual != null) {
                actual.setTipoIngreso(tipoIngresoSeleccionado == null?null:tipoIngresoSeleccionado.getNombre());
                actual.setTipoGasto(tipoGastoSeleccionado == null?null:tipoGastoSeleccionado.getNombre());
                actual.setTipoInversion(tipoInversionSeleccionado == null?null:tipoInversionSeleccionado.getNombre());

                actual.setSubTipoIngreso(subTipoIngresoSeleccionado == null?null:subTipoIngresoSeleccionado.getNombre());
                actual.setSubTipoGasto(subTipoGastoSeleccionado == null?null:subTipoGastoSeleccionado.getNombre());
                actual.setSubTipoInversion(subTipoInversionSeleccionado == null?null:subTipoInversionSeleccionado.getNombre());

                ejb.edit(actual);

                mover();
                JsfUtil.addSuccessMessage("Se guardó existosamente!!");
                actual = null;
                limpiar();
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Hubo un error al guarda! : " + e.getMessage());
        }
    }

    public void limpiar() {
        actual = null;
        tipoGastoSeleccionado = null;
        tipoIngresoSeleccionado = null;
        tipoInversionSeleccionado = null;
        ruc = null;
        cvCliente = 0;
        cvProveedor = 0;
        rucProveedor = null;
        subTipoGastoSeleccionado = null;
        subTipoIngresoSeleccionado = null;
        subTipoInversionSeleccionado = null;
        nombreArchivoSiguiente = null;
    }

    public void mover() {
        String path = "C:\\facturas";
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
