/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.t4s4.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.sp24.t4s4.shopping.Cart;
import sample.sp24.t4s4.shopping.Computer;
import sample.sp24.t4s4.shopping.ProductDAO;
import sample.sp24.t4s4.shopping.ProductError;
import sample.sp24.t4s4.user.UserDTO;

/**
 *
 * @author admin
 */
public class CheckoutController extends HttpServlet {

    private static final String SUCCESS = "PayVNPayController";
    private static final String ERROR = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            ProductDAO dao = new ProductDAO();
            List<ProductError> list = new ArrayList<>();
            int countError = 0;
            for (Computer computer : cart.getCart().values()) {
                int quantityInCurrent = dao.getQuatityOfProduct(computer.getId());
                if (quantityInCurrent == 0) {
                    String id = computer.getId();
                    String name = computer.getName();
                    String error = "Your product " + computer.getName() + " was sold out";
                    list.add(new ProductError(id, error, name));
                    request.setAttribute("MESSAGE_ERROR_CHECK_OUT", list);
                    countError++;
                } else if (computer.getQuantity() > quantityInCurrent) {
                    String id = computer.getId();
                    String name = computer.getName();
                    String error = "The quantity of " + computer.getName() + " had exceeded the available quantity";
                    list.add(new ProductError(id, error, name));
                    request.setAttribute("MESSAGE_ERROR_CHECK_OUT", list);
                    countError++;
                }
            }
            if(countError==0){
                url=SUCCESS;
            }
        } catch (Exception e) {
            request.setAttribute("MESSAGE_CHECKOUT", "Checkout Error!!! Please add product to checkout!!");
            log("Error in checkoutController" + e.toString());
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
