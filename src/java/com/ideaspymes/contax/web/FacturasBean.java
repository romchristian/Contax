/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.web;

import com.ideaspymes.contax.facade.ContribuyenteDAO;
import com.ideaspymes.contax.facade.FacturaFacade;
import com.ideaspymes.contax.facade.SubTipoGastoFacade;
import com.ideaspymes.contax.facade.SubTipoIngresoFacade;
import com.ideaspymes.contax.facade.SubTipoInversionFacade;
import com.ideaspymes.contax.facade.TipoGastoFacade;
import com.ideaspymes.contax.facade.TipoIngresoFacade;
import com.ideaspymes.contax.facade.TipoInversionFacade;
import com.ideaspymes.contax.modelo.Clasificacion;
import com.ideaspymes.contax.modelo.Contribuyente;
import com.ideaspymes.contax.modelo.Factura;
import com.ideaspymes.contax.modelo.SubTipoGasto;
import com.ideaspymes.contax.modelo.SubTipoIngreso;
import com.ideaspymes.contax.modelo.SubTipoInversion;
import com.ideaspymes.contax.modelo.TipoGasto;
import com.ideaspymes.contax.modelo.TipoImpuesto;
import com.ideaspymes.contax.modelo.TipoIngreso;
import com.ideaspymes.contax.modelo.TipoInversion;
import com.ideaspymes.contax.utils.ArchivoUtil;
import com.ideaspymes.contax.utils.JsfUtil;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import javax.inject.Named;

/**
 *
 * @author cromero
 */
@Named
@ViewScoped
public class FacturasBean implements Serializable {
    
    @EJB
    private ContribuyenteDAO contribuyenteDAO;
    @EJB
    private FacturaFacade ejb;
    @EJB
    private TipoGastoFacade ejbTipoGastoFacade;
    @EJB
    private TipoIngresoFacade ejbTipoIngresoFacade;
    @EJB
    private TipoInversionFacade ejbTipoInversionFacade;
    @EJB
    private SubTipoGastoFacade ejbSubTipoGastoFacade;
    @EJB
    private SubTipoIngresoFacade ejbSubTipoIngresoFacade;
    @EJB
    private SubTipoInversionFacade ejbSubTipoInversionFacade;
    
    @Inject
    private ImagenController imagenController;
    
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
    private boolean conIRP;
    private long id;
    private boolean contextoModificacion;
    private boolean generarAporteIPS;
    
    private String codEst;
    private String codSuc;
    private String numeroFact;
    private List<Factura> filteredValues;
    private BigDecimal totalExento = BigDecimal.ZERO;
    private BigDecimal totalGravada05 = BigDecimal.ZERO;
    private BigDecimal totalGravada10 = BigDecimal.ZERO;
    private BigDecimal totalIva = BigDecimal.ZERO;
    private BigDecimal totalNeto = BigDecimal.ZERO;
    private BigDecimal totalBruto = BigDecimal.ZERO;
    
    public BigDecimal getTotalExento() {
        return totalExento;
    }
    
    public void setTotalExento(BigDecimal totalExento) {
        this.totalExento = totalExento;
    }
    
    public BigDecimal getTotalGravada05() {
        return totalGravada05;
    }
    
    public void setTotalGravada05(BigDecimal totalGravada05) {
        this.totalGravada05 = totalGravada05;
    }
    
    public BigDecimal getTotalGravada10() {
        return totalGravada10;
    }
    
    public void setTotalGravada10(BigDecimal totalGravada10) {
        this.totalGravada10 = totalGravada10;
    }
    
    public BigDecimal getTotalIva() {
        return totalIva;
    }
    
    public void setTotalIva(BigDecimal totalIva) {
        this.totalIva = totalIva;
    }
    
    public BigDecimal getTotalNeto() {
        return totalNeto;
    }
    
    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }
    
    public BigDecimal getTotalBruto() {
        return totalBruto;
    }
    
    public void setTotalBruto(BigDecimal totalBruto) {
        this.totalBruto = totalBruto;
    }
    
    public List<Factura> getFilteredValues() {
        if (filteredValues == null) {
            filteredValues = new ArrayList<>();
            filteredValues.addAll(findAll());
        }
        return filteredValues;
    }
    
    public void setFilteredValues(List<Factura> filteredValues) {
        this.filteredValues = filteredValues;
    }
    
    public void updateSum() {
        System.out.println("HOLA");
        if (filteredValues != null) {
            System.out.println("HOLA 1");
            totalExento = BigDecimal.ZERO;
            totalGravada05 = BigDecimal.ZERO;
            totalGravada10 = BigDecimal.ZERO;
            totalIva = BigDecimal.ZERO;
            totalNeto = BigDecimal.ZERO;
            totalBruto = BigDecimal.ZERO;
            
            for (Factura f : filteredValues) {
                System.out.println("HOLA f : " + f.getTotalBruto());
                totalExento = totalExento.add(f.getExento() != null ? f.getExento() : BigDecimal.ZERO);
                totalGravada05 = totalGravada05.add(f.getGravada05() != null ? f.getGravada05() : BigDecimal.ZERO);
                totalGravada10 = totalGravada10.add(f.getGravada10() != null ? f.getGravada10() : BigDecimal.ZERO);
                totalIva = totalIva.add(f.getTotalIva() != null ? f.getTotalIva() : BigDecimal.ZERO);
                totalNeto = totalNeto.add(f.getTotalNeto() != null ? f.getTotalNeto() : BigDecimal.ZERO);
                totalBruto = totalBruto.add(f.getTotalBruto() != null ? f.getTotalBruto() : BigDecimal.ZERO);
            }
            System.out.println("Total Bruto : " + totalBruto);
        }
    }
    
    public String getCodEst() {
        return codEst;
    }
    
    public void setCodEst(String codEst) {
        
        this.codEst = getCodFormat(codEst);
    }
    
    public String getCodSuc() {
        return codSuc;
    }
    
    public void setCodSuc(String codSuc) {
        this.codSuc = getCodFormat(codSuc);
    }
    
    public String getNumeroFact() {
        return numeroFact;
    }
    
    public void setNumeroFact(String numeroFact) {
        this.numeroFact = getNumeroFactFormat(numeroFact);
    }
    
    public void cargaImagen() {
        String path = ArchivoUtil.PATH;
        final File folder = new File(path);
        nombreArchivoSiguiente = "";
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                // System.out.println("File Entry: " + fileEntry.getName());
                nombreArchivoSiguiente = fileEntry.getName();
            }
        }
        //System.out.println("Imagen en bean: " + nombreArchivoSiguiente);
        imagenController.setImagen(nombreArchivoSiguiente);
    }
    
    public boolean isGenerarAporteIPS() {
        return generarAporteIPS;
    }
    
    public void setGenerarAporteIPS(boolean generarAporteIPS) {
        this.generarAporteIPS = generarAporteIPS;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void resfreca() {
        if (id > 0) {
            actual = ejb.find(id);
            contextoModificacion = true;
            try {
                nombreArchivoSiguiente = actual.getUrl();
                // System.out.println("Nombre Imagen: " + nombreArchivoSiguiente);
                imagenController.setImagen(nombreArchivoSiguiente);
                String[] aruc = actual.getRucCliente().split("-");
                ruc = aruc[0];
                cvCliente = Integer.parseInt(aruc[1]);
                tipoIngresoSeleccionado = ejbTipoIngresoFacade.findNombre(actual.getTipoIngreso());
                tipoGastoSeleccionado = ejbTipoGastoFacade.findNombre(actual.getTipoGasto());
                tipoInversionSeleccionado = ejbTipoInversionFacade.findNombre(actual.getTipoInversion());
                subTipoGastoSeleccionado = ejbSubTipoGastoFacade.findNombre(actual.getSubTipoGasto());
                subTipoIngresoSeleccionado = ejbSubTipoIngresoFacade.findNombre(actual.getSubTipoIngreso());
                subTipoInversionSeleccionado = ejbSubTipoInversionFacade.findNombre(actual.getSubTipoInversion());
                
                String[] arucProveedor = actual.getRucProveedor().split("-");
                rucProveedor = arucProveedor[0];
                cvProveedor = Integer.parseInt(arucProveedor[1]);
                
            } catch (Exception e) {
                
            }
            //return "nuevo.xhtml?faces-redirect=true&id=" + id;
        } else {
            String path = ArchivoUtil.PATH;
            final File folder = new File(path);
            
            nombreArchivoSiguiente = "";
            for (final File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {
                    System.out.println("File Entry: " + fileEntry.getName());
                    nombreArchivoSiguiente = fileEntry.getName();
                }
            }
            
            imagenController.setImagen(nombreArchivoSiguiente);
            //return "nuevo.xhtml?faces-redirect=true&id=0";
        }
    }
    
    public void cargaDatos() {
        if (id > 0) {
            actual = ejb.find(id);
            tipoIngresoSeleccionado = ejbTipoIngresoFacade.findNombre(actual.getTipoIngreso());
            tipoGastoSeleccionado = ejbTipoGastoFacade.findNombre(actual.getTipoGasto());
            tipoInversionSeleccionado = ejbTipoInversionFacade.findNombre(actual.getTipoInversion());
            subTipoGastoSeleccionado = ejbSubTipoGastoFacade.findNombre(actual.getSubTipoGasto());
            subTipoIngresoSeleccionado = ejbSubTipoIngresoFacade.findNombre(actual.getSubTipoIngreso());
            subTipoInversionSeleccionado = ejbSubTipoInversionFacade.findNombre(actual.getSubTipoInversion());
            
            try {
                //nombreArchivoSiguiente = actual.getUrl();
                imagenController.setImagen(null);
                
                if (actual.getNumero() != null) {
                    String[] anumero = actual.getNumero().split("-");
                    if (anumero.length == 3) {
                        codEst = anumero[0];
                        codSuc = anumero[1];
                        numeroFact = anumero[2];
                    } else if (anumero.length == 1) {
                        numeroFact = anumero[0];
                    }
                }
                
                System.out.println("Nombre Imagen: " + nombreArchivoSiguiente);
                
                String[] aruc = actual.getRucCliente().split("-");
                ruc = aruc[0];
                cvCliente = Integer.parseInt(aruc[1]);
                
                String[] arucProveedor = actual.getRucProveedor().split("-");
                rucProveedor = arucProveedor[0];
                cvProveedor = Integer.parseInt(arucProveedor[1]);
                
            } catch (Exception e) {
            }
            
        }
        
        imagenController.setImagen(null);
//            else {
//            String path = ArchivoUtil.PATH;
//            final File folder = new File(path);
//            
//            nombreArchivoSiguiente = "";
//            for (final File fileEntry : folder.listFiles()) {
//                if (!fileEntry.isDirectory()) {
//                    System.out.println("File Entry: " + fileEntry.getName());
//                    nombreArchivoSiguiente = fileEntry.getName();
//                }
//            }
//        }
    }
    
    public List<Factura> findAll() {
        return ejb.findAll();
    }
    
    public boolean isConIRP() {
        return conIRP;
    }
    
    public void setConIRP(boolean conIRP) {
        this.conIRP = conIRP;
    }
    
    public boolean isMuestraTipoFactura() {
        boolean R = false;
        if (getActual().getTipoImpuesto() == TipoImpuesto.IVA_GENERAL) {
            R = true;
        } else {
            R = false;
        }
        return R;
    }
    
    public boolean isMuestraGenerarAporteIPS() {
        boolean R = false;
        if (tipoIngresoSeleccionado != null && tipoIngresoSeleccionado.getNombre().compareToIgnoreCase("Dependiente") == 0) {
            R = true;
        } else {
            R = false;
        }
        return R;
    }
    
    
    
    public boolean isMuestraPlazoAnio() {
        boolean R = false;
        if (subTipoGastoSeleccionado != null && subTipoGastoSeleccionado.getNombre().compareToIgnoreCase("Colocaciones en ahorro y bonos") == 0) {
            R = true;
        } else {
            R = false;
        }
        return R;
    }
    
    public boolean isMuestraSoloIvaSimplificado() {
        boolean R = false;
        if (getActual().getTipoImpuesto() == TipoImpuesto.IVA_SIMPLIFICADO) {
            R = true;
        } else {
            R = false;
        }
        return R;
    }
    
    public boolean isMuestraConIRP() {
        boolean R = false;
        if (getActual().getTipoImpuesto() == TipoImpuesto.IVA_GENERAL || getActual().getTipoImpuesto() == TipoImpuesto.IVA_SIMPLIFICADO) {
            R = true;
        } else {
            R = false;
        }
        return R;
    }
    
    public boolean isMuestraConIRPC() {
        boolean R = false;
        if (getActual().getTipoImpuesto() == TipoImpuesto.IVA_GENERAL || getActual().getTipoImpuesto() == TipoImpuesto.IVA_SIMPLIFICADO) {
            R = true;
        } else {
            R = false;
        }
        return R;
    }
    
    public boolean isMuestraClasificacionIRP() {
        boolean R = false;
        if (getActual().getTipoImpuesto() == TipoImpuesto.IRP || getActual().getTipoImpuesto() == TipoImpuesto.IRPC || getActual().isConIRP()) {
            R = true;
        }
        return R;
    }
    
    public boolean isMuestraClasificacionSoloIRPC() {
        boolean R = false;
        if (getActual().getTipoImpuesto() == TipoImpuesto.IRPC || getActual().isConIRPC()) {
            R = true;
        }
        return R;
    }
    
    public boolean isMuestraClasificacionSoloIRP() {
        boolean R = false;
        if (getActual().getTipoImpuesto() == TipoImpuesto.IRP) {
            R = true;
        }
        return R;
    }
    
    public boolean isMuestraIngresos() {
        boolean R;
        if ((getActual().getClasificacion() != null && getActual().getClasificacion() == Clasificacion.INGRESOS && !isMuestraClasificacionSoloIRPC())) {
            R = true;
        } else {
            R = false;
        }
        return R;
    }
    
    public boolean isMuestraGastos() {
        boolean R;
        if ((getActual().getClasificacion() != null && (getActual().getClasificacion() == Clasificacion.GASTOS && !isMuestraClasificacionSoloIRPC()))) {
            R = true;
        } else {
            R = false;
        }
        return R;
    }
    
    public boolean isMuestraInversion() {
        boolean R;
        if ((getActual().getClasificacion() != null && (getActual().getClasificacion() == Clasificacion.INVERSION && !isMuestraClasificacionSoloIRPC()))) {
            R = true;
        } else {
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
        Contribuyente c = contribuyenteDAO.getContribuyente(getRuc());
        if (c != null) {
            cvCliente = c.getCodigoVerificador();
            getActual().setRazonSocialCliente(c.getRazonSocial());
            getActual().setRucCliente(c.getRuc() + "-" + c.getCodigoVerificador());
        }
    }
    
    public void buscarContribuyenteProveedor() {
        Contribuyente c = contribuyenteDAO.getContribuyente(getRucProveedor());
        if (c != null) {
            cvProveedor = c.getCodigoVerificador();
            getActual().setRazonSocialProveedor(c.getRazonSocial());
            getActual().setRucProveedor(c.getRuc() + "-" + c.getCodigoVerificador());
        }
    }
    
    public String getNombreArchivoSiguiente() {
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
    
    public String guardar() {
        try {
            if (validar()) {
                actual.setTipoIngreso(tipoIngresoSeleccionado == null ? null : tipoIngresoSeleccionado.getNombre());
                actual.setTipoGasto(tipoGastoSeleccionado == null ? null : tipoGastoSeleccionado.getNombre());
                actual.setTipoInversion(tipoInversionSeleccionado == null ? null : tipoInversionSeleccionado.getNombre());
                
                actual.setSubTipoIngreso(subTipoIngresoSeleccionado == null ? null : subTipoIngresoSeleccionado.getNombre());
                actual.setSubTipoGasto(subTipoGastoSeleccionado == null ? null : subTipoGastoSeleccionado.getNombre());
                actual.setSubTipoInversion(subTipoInversionSeleccionado == null ? null : subTipoInversionSeleccionado.getNombre());
                String numero = "";
                if (codEst != null) {
                    numero += codEst + "-";
                }
                if (codSuc != null) {
                    numero += codSuc + "-";
                }
                
                if (numeroFact != null) {
                    numero += numeroFact;
                }
                actual.setNumero(numero);
                //ejb.edit(actual);
                mover();
                if (generarAporteIPS) {
                    crearAporteIPS();
                }
                
                JsfUtil.addSuccessMessage("Se guardó existosamente!!");
                actual = null;
                limpiar();
            }
            
            return "nuevo.xhtml?faces-redirect=true&id=0";
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Hubo un error al guarda! : " + e.getMessage());
            return null;
        }
    }
    
    public boolean validar() {
        if (actual == null) {
            JsfUtil.addErrorMessage("No hay nada para guardar!");
            return false;
        }
        
        if (actual.getRucCliente() == null || actual.getRucCliente().length() == 0) {
            JsfUtil.addErrorMessage("No hay ruc de cliente");
            return false;
        }
        
        if (actual.getRazonSocialCliente() == null || actual.getRazonSocialCliente().length() == 0) {
            JsfUtil.addErrorMessage("No hay razon social de cliente");
            return false;
        }
        
        if (actual.getTipoImpuesto() == null || actual.getTipoImpuesto().toString().compareToIgnoreCase("") == 0) {
            JsfUtil.addErrorMessage("No hay tipo impuesto");
            return false;
        }
        
        if (actual.getFecha() == null) {
            JsfUtil.addErrorMessage("No hay fecha");
            return false;
        }
        
        if (actual.getTotalBruto() == null || actual.getTotalBruto().compareTo(new BigDecimal(BigInteger.ZERO)) == 0) {
            JsfUtil.addErrorMessage("No hay total bruto");
            return false;
        }
        
        return true;
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
        codEst = null;
        codSuc = null;
        numeroFact = null;
        imagenController.setImagen(null);
    }
    
    public void mover() {
        String path = ArchivoUtil.PATH;
        File theDir = new File(path + "\\" + getRuc());

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
                String url = getRuc() + "\\" + afile.getName();
                
                actual.setUrl(url);
                ejb.edit(actual);
                
                nombreArchivoSiguiente = null;
            } else {
                ejb.edit(actual);
                System.out.println("File is failed to move!");
            }
            
        } catch (Exception e) {
            ejb.edit(actual);
            e.printStackTrace();
        }
    }
    
    public void siCambiaTipoImpuesto() {
        
        actual.setTipoFactura(null);
        actual.setConIRP(false);
        actual.setClasificacion(null);
        actual.setTipoIngreso(null);
        actual.setTipoGasto(null);
        actual.setTipoInversion(null);
        actual.setSubTipoIngreso(null);
        actual.setSubTipoGasto(null);
        actual.setSubTipoInversion(null);
        actual.setTipoExportacion(null);
        actual.setTipoAjustes(null);
        tipoGastoSeleccionado = null;
        tipoIngresoSeleccionado = null;
        tipoInversionSeleccionado = null;
        subTipoGastoSeleccionado = null;
        subTipoIngresoSeleccionado = null;
        subTipoInversionSeleccionado = null;
        
    }
    
    public void siCambiaLibro() {
        
        actual.setClasificacion(null);
        actual.setTipoIngreso(null);
        actual.setTipoGasto(null);
        actual.setTipoInversion(null);
        actual.setSubTipoIngreso(null);
        actual.setSubTipoGasto(null);
        actual.setSubTipoInversion(null);
        actual.setTipoExportacion(null);
        actual.setTipoAjustes(null);
        tipoGastoSeleccionado = null;
        tipoIngresoSeleccionado = null;
        tipoInversionSeleccionado = null;
        subTipoGastoSeleccionado = null;
        subTipoIngresoSeleccionado = null;
        subTipoInversionSeleccionado = null;
        
    }
    
    public void siCambiaConIRP() {
        
        actual.setClasificacion(null);
        actual.setTipoIngreso(null);
        actual.setTipoGasto(null);
        actual.setTipoInversion(null);
        actual.setSubTipoIngreso(null);
        actual.setSubTipoGasto(null);
        actual.setSubTipoInversion(null);
        tipoGastoSeleccionado = null;
        tipoIngresoSeleccionado = null;
        tipoInversionSeleccionado = null;
        subTipoGastoSeleccionado = null;
        subTipoIngresoSeleccionado = null;
        subTipoInversionSeleccionado = null;
        
    }
    
    public void siCambiaConIRPC() {
        
        actual.setClasificacion(null);
        actual.setTipoIngreso(null);
        actual.setTipoGasto(null);
        actual.setTipoInversion(null);
        actual.setSubTipoIngreso(null);
        actual.setSubTipoGasto(null);
        actual.setSubTipoInversion(null);
        tipoGastoSeleccionado = null;
        tipoIngresoSeleccionado = null;
        tipoInversionSeleccionado = null;
        subTipoGastoSeleccionado = null;
        subTipoIngresoSeleccionado = null;
        subTipoInversionSeleccionado = null;
        
    }
    
    public void siCambiaClasificionIRP() {
        
        actual.setTipoIngreso(null);
        actual.setTipoGasto(null);
        actual.setTipoInversion(null);
        actual.setSubTipoIngreso(null);
        actual.setSubTipoGasto(null);
        actual.setSubTipoInversion(null);
        tipoGastoSeleccionado = null;
        tipoIngresoSeleccionado = null;
        tipoInversionSeleccionado = null;
        
    }
    
    public void siCambiaTipoIngreso() {
        
        generarAporteIPS = false;
        actual.setTipoGasto(null);
        actual.setTipoInversion(null);
        actual.setSubTipoIngreso(null);
        actual.setSubTipoGasto(null);
        actual.setSubTipoInversion(null);
        subTipoGastoSeleccionado = null;
        subTipoIngresoSeleccionado = null;
        subTipoInversionSeleccionado = null;
        
    }
    
    public void siCambiaTipoGasto() {
        
        actual.setTipoIngreso(null);
        actual.setTipoInversion(null);
        actual.setSubTipoIngreso(null);
        actual.setSubTipoGasto(null);
        actual.setSubTipoInversion(null);
        subTipoGastoSeleccionado = null;
        subTipoIngresoSeleccionado = null;
        subTipoInversionSeleccionado = null;
        
    }
    
    public void siCambiaTipoInversion() {
        
        actual.setTipoIngreso(null);
        actual.setTipoGasto(null);
        actual.setSubTipoIngreso(null);
        actual.setSubTipoGasto(null);
        actual.setSubTipoInversion(null);
        subTipoGastoSeleccionado = null;
        subTipoIngresoSeleccionado = null;
        subTipoInversionSeleccionado = null;
        
    }
    
    public void eliminar() {
        if (actual != null) {
            ejb.remove(actual);
            actual = null;
            JsfUtil.addSuccessMessage("Eliminado Exitosamente!");
        }
        
    }
    
    public List<TipoIngreso> getItemsAvailableTipoIngreso() {
        if (actual.getTipoImpuesto() == TipoImpuesto.IVA_GENERAL && actual.isConIRP()) {
            return ejbTipoIngresoFacade.findAllNoDependiente();
        } else {
            return ejbTipoIngresoFacade.findAll();
        }
        
    }
    
    private void crearAporteIPS() {
        Factura f = new Factura();
        
        f.setFecha(actual.getFecha());
        f.setCambiaPeriodo(actual.isCambiaPeriodo());
        f.setConIRP(actual.isConIRP());
        f.setConIRPC(actual.isConIRPC());
        f.setNumero(actual.getNumero());
        f.setPeriodo(actual.getPeriodo());
        f.setRucCliente(actual.getRucCliente());
        f.setRazonSocialCliente(actual.getRazonSocialCliente());
        f.setTipoImpuesto(TipoImpuesto.IRP);
        f.setClasificacion(Clasificacion.GASTOS);
        f.setTipoGasto("Generales");
        f.setSubTipoGasto("Descuentos y aportes legales");
        
        f.setGravada05(BigDecimal.ZERO);
        f.setGravada05Neto(BigDecimal.ZERO);
        f.setGravada10(BigDecimal.ZERO);
        f.setGravada10Neto(BigDecimal.ZERO);
        f.setIva05(BigDecimal.ZERO);
        f.setIva10(BigDecimal.ZERO);
        f.setTotalIva(BigDecimal.ZERO);
        
        BigDecimal ips = actual.getTotalBruto().multiply(new BigDecimal(0.095));
        f.setExento(ips);
        f.setTotalNeto(ips);
        f.setTotalBruto(ips);
        ejb.edit(f);
        
    }
    
    private String getCodFormat(String cod) {
        String R = "";
        if (cod != null) {
            if (cod.length() == 1) {
                R = "00" + cod;
            } else if (cod.length() == 2) {
                R = "0" + cod;
            } else if (cod.length() == 3) {
                R = cod;
            }
        }
        return R;
    }
    
    private String getNumeroFactFormat(String cod) {
        String R = "";
        if (cod != null) {
            if (cod.length() == 1) {
                R = "000000" + cod;
            } else if (cod.length() == 2) {
                R = "00000" + cod;
            } else if (cod.length() == 3) {
                R = "0000" + cod;
            } else if (cod.length() == 4) {
                R = "000" + cod;
            } else if (cod.length() == 5) {
                R = "00" + cod;
            } else if (cod.length() == 6) {
                R = "0" + cod;
            } else if (cod.length() == 7) {
                R = cod;
            }
        }
        return R;
    }
    
}
