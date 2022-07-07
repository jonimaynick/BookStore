/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phu.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String SIGNIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String HOME = "home.jsp";
    private static final String VIEWPRODUCT = "ProductController";
    private static final String ADDTOCART = "AddToCartController";
    private static final String UPDATE = "UpdateController";
    private static final String DELETE = "DeleteController";
    private static final String ADDBOOK = "AddController";
    private static final String SEARCHBOOK = "SearchController";
    private static final String UPDATECART = "UpdateCartController";
    private static final String DELETECART = "DeleteCartController";
    private static final String PAYMENT = "PaymentController";
    private static final String CHECKCOUPONS = "CheckCouponsController";
    private static final String VIEWORDER = "ViewOrderController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Home".equals(action)) {
                url = HOME;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("Cart".equals(action)) {
                url = ERROR;
            } else if ("Sign In".equals(action)) {
                url = SIGNIN;
            } else if ("View Product".equals(action)) {
                url = VIEWPRODUCT;
            } else if ("Add To Cart".equals(action)) {
                url = ADDTOCART;
            } else if ("Update".equals(action)) {
                url = UPDATE;
            } else if ("Delete".equals(action)) {
                url = DELETE;
            } else if ("Add".equals(action)) {
                url = ADDBOOK;
            } else if ("Search".equals(action)) {
                url = SEARCHBOOK;
            } else if ("Delete Cart".equals(action)) {
                url = DELETECART;
            } else if ("Update Cart".equals(action)) {
                url = UPDATECART;
            } else if ("Payment".equals(action)) {
                url = PAYMENT;
            } else if ("Check Coupons".equals(action)) {
                url = CHECKCOUPONS;
            } else if ("View Order".equals(action)) {
                url = VIEWORDER;
            } else {
                //History
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_MESSAGE", "error");
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
