/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.modelo;

/**
 *
 * @author Christian
 */
public enum TipoImpuesto {

    IVA_GENERAL("IVA GENERAL"),
    IRP("IRP"),
    IRPC("IRPC"),
    IVA_SIMPLIFICADO("IVA SIMPLIFICADO"),
    IRAGRO("IRAGR0");

    private final String value;

    private TipoImpuesto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
