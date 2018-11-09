/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.OrderDetails;
import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ItemDAO;
import model.OrderDetailsDAO;
import model.OrdersDAO;

/**
 *
 * @author Admin
 */
public class AddController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String itemCode = request.getParameter("itemCode");
        String usd = request.getParameter("usd");
        String yen = request.getParameter("other");
        String euro = request.getParameter("euro");
        String other = request.getParameter("other");
        String cash = request.getParameter("cash");
        String card = request.getParameter("card");
        String page = request.getParameter("page");
        String quantity = request.getParameter("quantity");
        String seatNo = request.getParameter("seatNo");

        if (itemCode == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/add.jsp");
            try {
                request.setAttribute("items", new ItemDAO().list());
            } catch (Exception ex) {
                System.out.println(ex);
            }
            rd.forward(request, response);
        } else {
            String currency = "euro";

            if (usd != null) {
                currency = "usd";
            } else if (yen != null) {
                currency = "yen";
            } else if (other != null) {
                currency = "other";
            }

            boolean isCash = true;

            if (cash == null) {
                isCash = false;
            }
            int qua = 0;
            try {
                qua = Integer.parseInt(quantity);
                float price = new ItemDAO().get(Integer.parseInt(itemCode)).getPrice();
                Orders order = new Orders(0, isCash, currency, seatNo, qua * price);
                order = new OrdersDAO().save(order);
                
                OrderDetails ord = new OrderDetails(order.getOrderId(), Integer.parseInt(itemCode), page, qua, price);
                new OrderDetailsDAO().save(ord);
                
                response.sendRedirect("/PT2-Anh/list");
            } catch (Exception ex) {
                System.out.println(ex);
            }

            System.out.println("page" + page);
            System.out.println("quantity" + quantity);

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
