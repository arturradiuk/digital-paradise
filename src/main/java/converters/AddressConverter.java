package converters;

import controller.exceptions.user.AddressException;
import model.entities.Address;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converters.AddressConverter")
public class AddressConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        String[] tab = s.split(" ");
        StringBuilder temp = new StringBuilder();
        for (int i = 1; i < tab.length; i++) {
            temp.append(tab[i]);
        }
        Address address = null;
        try {
            address = new Address(tab[0], temp.toString());
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Address temp = (Address) o;
        return temp.toString();
    }
}
