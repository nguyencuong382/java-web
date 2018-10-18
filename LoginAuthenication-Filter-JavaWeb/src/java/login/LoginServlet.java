/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import entity.User_;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserDAO;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

    public void sessionLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        HttpSession session = request.getSession();

        if (!userName.equals("") && passWord.equals("1")) {
            User_ user = new User_(userName, passWord);
            session.setAttribute("user", user);
            response.sendRedirect("/LoginAuthenication-Filter-JavaWeb/index.jsp");
            request.removeAttribute("loginError");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/LoginAuthenication-Filter-JavaWeb/login.jsp");
            request.setAttribute("loginError", "User name or password is invalid");
            rd.forward(request, response);
            
//            response.sendRedirect("/LoginAuthenication-Filter-JavaWeb/login.jsp");
        }
    }

    public void cookieLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        HttpSession session = request.getSession();
        User_ user = null;
        try {
            user = new UserDAO().list(userName, passWord);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        if (user != null) {
            Cookie cUserName = new Cookie("userName", userName);
            Cookie cPassWord = new Cookie("passWord", passWord);
            
            cUserName.setMaxAge(3600);
            cPassWord.setMaxAge(3600);
            
            response.addCookie(cUserName);
            response.addCookie(cPassWord);
            
            session.setAttribute("user", user);
            session.removeAttribute("loginError");
            response.sendRedirect("/LoginAuthenication-Filter-JavaWeb/index.jsp");
        } else {
//            RequestDispatcher rd = request.getRequestDispatcher("/LoginAuthenication-Filter-JavaWeb/login.jsp");
//            request.setAttribute("loginError", "User name or password is invalid");
//            rd.forward(request, response);
            
            session.setAttribute("loginError", "User name or password is invalid");
            response.sendRedirect("/LoginAuthenication-Filter-JavaWeb/login.jsp");
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        cookieLogin(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
