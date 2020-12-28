package web;

import javax.ejb.ApplicationException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
    
    public String login() {
        try {
            request.login(username, password);
            return "Index";
        } catch (ServletException e) {
            return "LoginErrorPage";
        }
    }

    public String getMyLogin() {
        return ((null == request.getUserPrincipal()) ? "" : request.getUserPrincipal().getName());
        //        return request.isUserInRole("ADMINS")+"";
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