/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.StudentDAO;

/**
 *
 * @author Admin
 */
public class AddStudentController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean valid(String text) {
        if (text == null || text.equals("")) {
            return false;
        }

        return true;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String studentName = request.getParameter("studentName");
        String classId = request.getParameter("classId");
        String gender = request.getParameter("gender");
        String DOB = request.getParameter("DOB");
        try {
            if (valid(studentName) || valid(classId) || valid(DOB)) {
                String studentName_ = studentName;
                int classId_ = Integer.parseInt(classId);
                boolean gender_ = gender != null;
                Date DOB_ = null;
                try {
                    DOB_ = new SimpleDateFormat("dd/MM/yyyy").parse(DOB);
                } catch (ParseException ex) {
                    System.out.println(ex); 
                }

                Student newStudent = new Student(-1, studentName_, classId_, gender_, DOB_);
                new StudentDAO().save(newStudent);
                response.sendRedirect("/FinalQ1_SE05630/group");
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/ws1/AddNewStudent.jsp");
                rd.forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println(ex);
            RequestDispatcher rd = request.getRequestDispatcher("/ws1/AddNewStudent.jsp");
            rd.forward(request, response);
        }
        
        
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
