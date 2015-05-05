package com.ideaspymes.contax.web;

import com.ideaspymes.contax.modelo.SubTipoGasto;
import com.ideaspymes.contax.web.util.JsfUtil;
import com.ideaspymes.contax.web.util.JsfUtil.PersistAction;
import com.ideaspymes.contax.facade.SubTipoGastoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("subTipoGastoController")
@SessionScoped
public class SubTipoGastoController implements Serializable {

    @EJB
    private com.ideaspymes.contax.facade.SubTipoGastoFacade ejbFacade;
    private List<SubTipoGasto> items = null;
    private SubTipoGasto selected;

    public SubTipoGastoController() {
    }

    public SubTipoGasto getSelected() {
        return selected;
    }

    public void setSelected(SubTipoGasto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SubTipoGastoFacade getFacade() {
        return ejbFacade;
    }

    public SubTipoGasto prepareCreate() {
        selected = new SubTipoGasto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SubTipoGastoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SubTipoGastoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SubTipoGastoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SubTipoGasto> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public SubTipoGasto getSubTipoGasto(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<SubTipoGasto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SubTipoGasto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SubTipoGasto.class)
    public static class SubTipoGastoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubTipoGastoController controller = (SubTipoGastoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subTipoGastoController");
            return controller.getSubTipoGasto(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SubTipoGasto) {
                SubTipoGasto o = (SubTipoGasto) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SubTipoGasto.class.getName()});
                return null;
            }
        }

    }

}
