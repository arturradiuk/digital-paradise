import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.UUID;

//@FacesConverter(forClass = java.util.UUID.class)
@FacesConverter("UUIDConverter")
public class UUIDConverter  implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        return UUID.fromString(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        UUID uuid = (UUID)o;
        return uuid.toString();
    }
}
