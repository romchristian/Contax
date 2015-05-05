package com.ideaspymes.contax.web;

import com.ideaspymes.contax.modelo.SubTipoInversion;
import com.ideaspymes.contax.web.util.JsfUtil;
import com.ideaspymes.contax.web.util.JsfUtil.PersistAction;
import com.ideaspymes.contax.facade.SubTipoInversionFacade;

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

@Named("subTipoInversionController")
@SessionScoped
public class SubTipoInversionController implements Serializable {

    @EJB
    private com.ideaspymes.contax.facade.SubTipoInversionFacade ejbFacade;
    private List<SubTipoInversion> items = null;
    private SubTipoInversion selected;

    public SubTipoInversionController() {
    }

    public SubTipoInversion getSelected() {
        return selected;
    }

    public void setSelected(SubTipoInversion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SubTipoInversionFacade getFacade() {
        return ejbFacade;
    }

    public SubTipoInversion prepareCreate() {
        selected = new SubTipoInversion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SubTipoInversionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SubTipoInversionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SubTipoInversionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SubTipoInversion> getItems() {
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

    public SubTipoInversion getSubTipoInversion(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<SubTipoInversion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SubTipoInversion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SubTipoInversion.class)
    public static class SubTipoInversionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubTipoInversionController controller = (SubTipoInversionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subTipoInversionController");
            return controller.getSubTipoInversion(getKey(value));
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
            if (object instanceof SubTipoInversion) {
                SubTipoInversion o = (SubTipoInversion) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SubTipoInversion.class.getName()});
                return null;
            }
        }

    }

}
