/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phu.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phu.DAO.BookDAO;
import phu.DAO.CouponsDAO;
import phu.DAO.DetailDAO;
import phu.DAO.OrderDAO;
import phu.DTO.BookDTO;
import phu.DTO.BookError;
import phu.DTO.CartDTO;
import phu.DTO.DetailDTO;
import phu.DTO.OrderDTO;
import phu.DTO.UserDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "PaymentController", urlPatterns = {"/PaymentController"})
public class PaymentController extends HttpServlet {

    private static final String EXCEP = "error.jsp";
    private static final String ERROR = "cart.jsp";
    private static final String SUCCESS = "ThankPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null) {
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                OrderDAO orderDAO = new OrderDAO();
                Date orderDate = new java.util.Date();
                float total = 0;
                String coupons = request.getParameter("Coupons");
                if (coupons.isEmpty()) {
                    coupons = null;
                }
                OrderDTO order = new OrderDTO("", orderDate, total, user.getUserID(), true, coupons);
                List<BookError> listBookError = CheckQuality(cart);
                if (listBookError.size() == 0) {
                    float count = 0;
                    order = orderDAO.CreateOrder(order);
                    boolean check = true;
                    for (BookDTO book : cart.getCart().values()) {

                        DetailDAO detailDAO = new DetailDAO();
                        float price = book.getPrice() * book.getQuantity();
                        count += price;
                        DetailDTO detail = new DetailDTO("", price, book.getQuantity(), order.getOrderID(), book.getBookID());
                        check = detailDAO.AddDetail(detail);
                        if (!check) {
                            break;
                        }
                    }
                    if (!check) {
                        url = EXCEP;
                    } else {
                        if (coupons != null) {
                            count = count - (count * CouponsDAO.GetPercent(coupons) / 100);
                        }

                        url = SUCCESS;
                        orderDAO.UpdateTotal(count, order.getOrderID());
                        session.setAttribute("CART", null);
                    }
                } else {
                    request.setAttribute("QUANTITY_ERROR", listBookError);
                }
            }
        } catch (Exception e) {
            log("error at update cart controller");
            System.out.println(e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private List<BookError> CheckQuality(CartDTO cart) throws SQLException {
        List<BookError> listError = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();
        for (BookDTO book : cart.getCart().values()) {
            int inventory = bookDAO.getBookFromID(book.getBookID()).getQuantity();
            if (inventory < book.getQuantity()) {
                listError.add(new BookError(book.getBookID(), "This book have not enougt!"));
            }
        }
        return listError;
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
