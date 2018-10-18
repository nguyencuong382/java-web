/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import entity.User_;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author Admin
 */
public class CookieAuthentication {

    public boolean authenUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();
        System.out.println("---");
        
        if(cookies == null || cookies.length < 2){
            return false;
        }
        
        System.out.println(cookies[1].getValue());
        System.out.println(cookies[0].getValue());
        
        
        if (cookies[1].getValue().equals("") || !cookies[0].getValue().equals("1")) {
//            session.setAttribute("loginError", "Authenication required");
//            res.sendRedirect("/LoginAuthenication-Filter-JavaWeb/login.jsp");
            return false;
        } else {
            if (session.getAttribute("user") == null) {
                User_ user = new User_(cookies[1].getValue(), "1");
                session.setAttribute("user", user);
            }
        }

        return true;
    }
}
