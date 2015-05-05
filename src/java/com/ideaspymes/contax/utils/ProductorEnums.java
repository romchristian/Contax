/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.utils;

import com.ideaspymes.contax.modelo.Clasificacion;
import com.ideaspymes.contax.modelo.TipoFactura;
import com.ideaspymes.contax.modelo.TipoImpuesto;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author christian
 */
@Named
@ApplicationScoped
public class ProductorEnums implements Serializable {

    public SelectItem[] obtTiposImpuestos() {
        SelectItem[] R = new SelectItem[TipoImpuesto.values().length];
        TipoImpuesto[] lista = TipoImpuesto.values();
        for (int i = 0; i < lista.length; i++) {
            TipoImpuesto e = lista[i];
            R[i] = new SelectItem(e, e.toString());
        }
        return R;
    }
    
    public SelectItem[] obtTiposFactura() {
        SelectItem[] R = new SelectItem[TipoFactura.values().length];
        TipoFactura[] lista = TipoFactura.values();
        for (int i = 0; i < lista.length; i++) {
            TipoFactura e = lista[i];
            R[i] = new SelectItem(e, e.toString());
        }
        return R;
    }

     public SelectItem[] obtClasificacionesIRP() {
        SelectItem[] R = new SelectItem[Clasificacion.values().length];
        Clasificacion[] lista = Clasificacion.values();
        for (int i = 0; i < lista.length; i++) {
            Clasificacion e = lista[i];
            R[i] = new SelectItem(e, e.toString());
        }
        return R;
    }

}
