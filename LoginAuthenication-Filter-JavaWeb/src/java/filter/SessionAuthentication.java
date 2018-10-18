/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import entity.User_;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserDAO;

/**
 *
 * @author Admin
 */
public class SessionAuthentication {

    public boolean authenUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("loginError", "Authenication required");
            res.sendRedirect("/LoginAuthenication-Filter-JavaWeb/login.jsp");
            return false;
        }
        
        return true;
    }

}
