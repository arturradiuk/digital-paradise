package web;

import javax.ejb.ApplicationException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@ApplicationScoped
public class IdentityUtils {
    @Inject
    HttpServletRequest request;

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