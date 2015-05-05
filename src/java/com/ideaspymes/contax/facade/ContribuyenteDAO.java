/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.facade;

import com.ideaspymes.contax.modelo.Contribuyente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Christian
 */
@Stateless
public class ContribuyenteDAO {

    @PersistenceContext(unitName = "RucsPU")
    private EntityManager em;

    public Contribuyente getContribuyente(String cedula) {
        return em.find(Contribuyente.class, cedula);
    }

}
