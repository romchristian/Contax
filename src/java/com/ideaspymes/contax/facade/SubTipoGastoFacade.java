/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.contax.facade;

import com.ideaspymes.contax.modelo.SubTipoGasto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Christian
 */
@Stateless
public class SubTipoGastoFacade extends AbstractFacade<SubTipoGasto> {
    @PersistenceContext(unitName = "ContaxPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubTipoGastoFacade() {
        super(SubTipoGasto.class);
    }
    
}
