package web;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class IdentityUtils implements Serializable {
    @Inject
    HttpServletRequest request;
    
    @NotNull
    @Getter
    @Setter
    private String username;
    
    @NotNull
    @Getter
    @Setter
    private String password;
    
    private final Logger logger  = Logger.getLogger(IdentityUtils.class.getName());
    
    public String login() {
        try {
            request.login(username, password);
            logger.log(Level.INFO, "Login succeeded.");
            return "Index";
        } catch (ServletException e) {
            logger.log(Level.WARNING, "Login failed");
            return "LoginErrorPage";
        }
    }

    public String getMyLogin() {
        return ((null == request.getUserPrincipal()) ? "" : request.getUserPrincipal().getName());
    }

    public void logOut() {
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        request.getSession().invalidate();

    }

    public boolean isUserLogged() {
        return null != request.getUserPrincipal();
    }
}