package com.ideaspymes.contax.web;

import com.ideaspymes.contax.modelo.SubTipoIngreso;
import com.ideaspymes.contax.web.util.JsfUtil;
import com.ideaspymes.contax.web.util.JsfUtil.PersistAction;
import com.ideaspymes.contax.facade.SubTipoIngresoFacade;

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

@Named("subTipoIngresoController")
@SessionScoped
public class SubTipoIngresoController implements Serializable {

    @EJB
    private com.ideaspymes.contax.facade.SubTipoIngresoFacade ejbFacade;
    private List<SubTipoIngreso> items = null;
    private SubTipoIngreso selected;

    public SubTipoIngresoController() {
    }

    public SubTipoIngreso getSelected() {
        return selected;
    }

    public void setSelected(SubTipoIngreso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SubTipoIngresoFacade getFacade() {
        return ejbFacade;
    }

    public SubTipoIngreso prepareCreate() {
        selected = new SubTipoIngreso();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SubTipoIngresoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SubTipoIngresoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SubTipoIngresoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SubTipoIngreso> getItems() {
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

    public SubTipoIngreso getSubTipoIngreso(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<SubTipoIngreso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SubTipoIngreso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SubTipoIngreso.class)
    public static class SubTipoIngresoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubTipoIngresoController controller = (SubTipoIngresoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subTipoIngresoController");
            return controller.getSubTipoIngreso(getKey(value));
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
            if (object instanceof SubTipoIngreso) {
                SubTipoIngreso o = (SubTipoIngreso) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SubTipoIngreso.class.getName()});
                return null;
            }
        }

    }

}
