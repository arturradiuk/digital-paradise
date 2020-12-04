package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@FacesConverter("converters.MultipleUUIDConverter")

public class MultipleUUIDConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        String[] uuids = s.split(", ");
        List<UUID> uuidsList = new ArrayList<>();
        for (int i = 0; i < uuids.length; i++) {

            uuidsList.add(UUID.fromString(uuids[i].trim()));
        }
        return uuidsList;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        List<UUID> uuidsList = (List<UUID>) o;
        return Arrays.toString(uuidsList.toArray());
    }
}
