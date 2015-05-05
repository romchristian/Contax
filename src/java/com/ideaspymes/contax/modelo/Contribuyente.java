/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Christian
 */
@Entity
public class Contribuyente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String ruc;
    private Integer codigoVerificador;
    private String razonSocial;
    private String rucViejo;

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

   

    public Integer getCodigoVerificador() {
        return codigoVerificador;
    }

    public void setCodigoVerificador(Integer codigoVerificador) {
        this.codigoVerificador = codigoVerificador;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRucViejo() {
        return rucViejo;
    }

    public void setRucViejo(String rucViejo) {
        this.rucViejo = rucViejo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.ruc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contribuyente other = (Contribuyente) obj;
        if (!Objects.equals(this.ruc, other.ruc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ruc + "-" + codigoVerificador + "," + razonSocial;
    }

}
