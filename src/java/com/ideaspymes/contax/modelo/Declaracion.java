/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "declaracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Declaracion.findAll", query = "SELECT d FROM Declaracion d")})
public class Declaracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "impuesto")
    private String impuesto;
    @Size(max = 255)
    @Column(name = "libro")
    private String libro;
    @Size(max = 255)
    @Column(name = "clasificacion")
    private String clasificacion;
    @Size(max = 255)
    @Column(name = "subclasificacion")
    private String subclasificacion;
    @Size(max = 255)
    @Column(name = "ruccliente")
    private String ruccliente;
    @Size(max = 255)
    @Column(name = "razonsocialcliente")
    private String razonsocialcliente;
    @Size(max = 255)
    @Column(name = "tipodocumento")
    private String tipodocumento;
    @Column(name = "fechadocumento")
    @Temporal(TemporalType.DATE)
    private Date fechadocumento;
    @Size(max = 255)
    @Column(name = "periodoadeclarar")
    private String periodoadeclarar;
    @Size(max = 255)
    @Column(name = "nrodocumento")
    private String nrodocumento;
    @Size(max = 255)
    @Column(name = "doccomprador")
    private String doccomprador;
    @Size(max = 255)
    @Column(name = "ructercero")
    private String ructercero;
    @Size(max = 255)
    @Column(name = "razonsocilatercero")
    private String razonsocilatercero;
    @Size(max = 255)
    @Column(name = "inscriptoeniva")
    private String inscriptoeniva;
    @Column(name = "nrotimbrado")
    private Integer nrotimbrado;
    @Column(name = "provienebienesgananciales")
    private Boolean provienebienesgananciales;
    @Column(name = "exentas")
    private BigInteger exentas;
    @Column(name = "gravada05")
    private BigInteger gravada05;
    @Column(name = "gravada05neto")
    private BigInteger gravada05neto;
    @Column(name = "gravada10")
    private BigInteger gravada10;
    @Column(name = "gravada10neto")
    private BigInteger gravada10neto;
    @Column(name = "iva05")
    private BigInteger iva05;
    @Column(name = "iva10")
    private BigInteger iva10;
    @Column(name = "totaliva")
    private BigInteger totaliva;
    @Column(name = "totalneto")
    private BigInteger totalneto;
    @Column(name = "totalbruto")
    private BigInteger totalbruto;
    @Column(name = "exportaciones")
    private BigInteger exportaciones;
    @Column(name = "nogravadoyexonerado")
    private BigInteger nogravadoyexonerado;
    @Column(name = "gravados")
    private BigInteger gravados;

    public Declaracion() {
    }

    public Declaracion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getSubclasificacion() {
        return subclasificacion;
    }

    public void setSubclasificacion(String subclasificacion) {
        this.subclasificacion = subclasificacion;
    }

    public String getRuccliente() {
        return ruccliente;
    }

    public void setRuccliente(String ruccliente) {
        this.ruccliente = ruccliente;
    }

    public String getRazonsocialcliente() {
        return razonsocialcliente;
    }

    public void setRazonsocialcliente(String razonsocialcliente) {
        this.razonsocialcliente = razonsocialcliente;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Date getFechadocumento() {
        return fechadocumento;
    }

    public void setFechadocumento(Date fechadocumento) {
        this.fechadocumento = fechadocumento;
    }

    public String getPeriodoadeclarar() {
        return periodoadeclarar;
    }

    public void setPeriodoadeclarar(String periodoadeclarar) {
        this.periodoadeclarar = periodoadeclarar;
    }

    public String getNrodocumento() {
        return nrodocumento;
    }

    public void setNrodocumento(String nrodocumento) {
        this.nrodocumento = nrodocumento;
    }

    public String getDoccomprador() {
        return doccomprador;
    }

    public void setDoccomprador(String doccomprador) {
        this.doccomprador = doccomprador;
    }

    public String getRuctercero() {
        return ructercero;
    }

    public void setRuctercero(String ructercero) {
        this.ructercero = ructercero;
    }

    public String getRazonsocilatercero() {
        return razonsocilatercero;
    }

    public void setRazonsocilatercero(String razonsocilatercero) {
        this.razonsocilatercero = razonsocilatercero;
    }

    public String getInscriptoeniva() {
        return inscriptoeniva;
    }

    public void setInscriptoeniva(String inscriptoeniva) {
        this.inscriptoeniva = inscriptoeniva;
    }

    public Integer getNrotimbrado() {
        return nrotimbrado;
    }

    public void setNrotimbrado(Integer nrotimbrado) {
        this.nrotimbrado = nrotimbrado;
    }

    public Boolean getProvienebienesgananciales() {
        return provienebienesgananciales;
    }

    public void setProvienebienesgananciales(Boolean provienebienesgananciales) {
        this.provienebienesgananciales = provienebienesgananciales;
    }

    public BigInteger getExentas() {
        return exentas;
    }

    public void setExentas(BigInteger exentas) {
        this.exentas = exentas;
    }

    public BigInteger getGravada05() {
        return gravada05;
    }

    public void setGravada05(BigInteger gravada05) {
        this.gravada05 = gravada05;
    }

    public BigInteger getGravada05neto() {
        return gravada05neto;
    }

    public void setGravada05neto(BigInteger gravada05neto) {
        this.gravada05neto = gravada05neto;
    }

    public BigInteger getGravada10() {
        return gravada10;
    }

    public void setGravada10(BigInteger gravada10) {
        this.gravada10 = gravada10;
    }

    public BigInteger getGravada10neto() {
        return gravada10neto;
    }

    public void setGravada10neto(BigInteger gravada10neto) {
        this.gravada10neto = gravada10neto;
    }

    public BigInteger getIva05() {
        return iva05;
    }

    public void setIva05(BigInteger iva05) {
        this.iva05 = iva05;
    }

    public BigInteger getIva10() {
        return iva10;
    }

    public void setIva10(BigInteger iva10) {
        this.iva10 = iva10;
    }

    public BigInteger getTotaliva() {
        return totaliva;
    }

    public void setTotaliva(BigInteger totaliva) {
        this.totaliva = totaliva;
    }

    public BigInteger getTotalneto() {
        return totalneto;
    }

    public void setTotalneto(BigInteger totalneto) {
        this.totalneto = totalneto;
    }

    public BigInteger getTotalbruto() {
        return totalbruto;
    }

    public void setTotalbruto(BigInteger totalbruto) {
        this.totalbruto = totalbruto;
    }

    public BigInteger getExportaciones() {
        return exportaciones;
    }

    public void setExportaciones(BigInteger exportaciones) {
        this.exportaciones = exportaciones;
    }

    public BigInteger getNogravadoyexonerado() {
        return nogravadoyexonerado;
    }

    public void setNogravadoyexonerado(BigInteger nogravadoyexonerado) {
        this.nogravadoyexonerado = nogravadoyexonerado;
    }

    public BigInteger getGravados() {
        return gravados;
    }

    public void setGravados(BigInteger gravados) {
        this.gravados = gravados;
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
        if (!(object instanceof Declaracion)) {
            return false;
        }
        Declaracion other = (Declaracion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ideaspymes.contax.modelo.Declaracion[ id=" + id + " ]";
    }
    
}
