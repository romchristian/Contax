/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Christian
 */
@Entity
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rucCliente;
    private String razonSocialCliente;
    private String numero;
    
    private String rucProveedor;
    private String razonSocialProveedor;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String periodo;
    @Enumerated(EnumType.STRING)
    private TipoFactura tipoFactura;
    @Enumerated(EnumType.STRING)
    private TipoImpuesto tipoImpuesto;
    @Enumerated(EnumType.STRING)
    private Clasificacion clasificacion;
    
    private String tipoIngreso;
    private String tipoGasto;
    private String tipoInversion;
    private String subTipoIngreso;
    private String subTipoGasto;
    private String subTipoInversion;
    
    private BigDecimal gravada05;
    private BigDecimal gravada10;
    private BigDecimal exento;
    private BigDecimal iva05;
    private BigDecimal iva10;
    private BigDecimal gravada05Neto;
    private BigDecimal gravada10Neto;
    
    private BigDecimal totalIva;
    private BigDecimal totalNeto;
    private BigDecimal totalBruto;
    
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRucCliente() {
        return rucCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }

    public String getRazonSocialCliente() {
        return razonSocialCliente;
    }

    public void setRazonSocialCliente(String razonSocialCliente) {
        this.razonSocialCliente = razonSocialCliente;
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
    }

    
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public TipoFactura getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(TipoFactura tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public TipoImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getTipoInversion() {
        return tipoInversion;
    }

    public void setTipoInversion(String tipoInversion) {
        this.tipoInversion = tipoInversion;
    }

    public String getSubTipoIngreso() {
        return subTipoIngreso;
    }

    public void setSubTipoIngreso(String subTipoIngreso) {
        this.subTipoIngreso = subTipoIngreso;
    }

    public String getSubTipoGasto() {
        return subTipoGasto;
    }

    public void setSubTipoGasto(String subTipoGasto) {
        this.subTipoGasto = subTipoGasto;
    }

    public String getSubTipoInversion() {
        return subTipoInversion;
    }

    public void setSubTipoInversion(String subTipoInversion) {
        this.subTipoInversion = subTipoInversion;
    }

    public BigDecimal getGravada05Neto() {
        return gravada05Neto;
    }

    public void setGravada05Neto(BigDecimal gravada05Neto) {
        this.gravada05Neto = gravada05Neto;
    }

    public BigDecimal getGravada10Neto() {
        return gravada10Neto;
    }

    public void setGravada10Neto(BigDecimal gravada10Neto) {
        this.gravada10Neto = gravada10Neto;
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

   
    public BigDecimal getGravada05() {
        return gravada05;
    }

    public void setGravada05(BigDecimal gravada05) {
        this.gravada05 = gravada05;
    }

    public BigDecimal getGravada10() {
        return gravada10;
    }

    public void setGravada10(BigDecimal gravada10) {
        this.gravada10 = gravada10;
    }

    public BigDecimal getExento() {
        return exento;
    }

    public void setExento(BigDecimal exento) {
        this.exento = exento;
    }

    public BigDecimal getIva05() {
        return iva05;
    }

    public void setIva05(BigDecimal iva05) {
        this.iva05 = iva05;
    }

    public BigDecimal getIva10() {
        return iva10;
    }

    public void setIva10(BigDecimal iva10) {
        this.iva10 = iva10;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ideaspymes.contax.modelo.Factura[ id=" + id + " ]";
    }
    
}