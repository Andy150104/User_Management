/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.t4s4.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.sp24.t4s4.shopping.Cart;
import sample.sp24.t4s4.shopping.Computer;
import sample.sp24.t4s4.shopping.ProductDAO;
import sample.sp24.t4s4.user.UserDTO;

/**
 *
 * @author admin
 */
public class CheckoutSuccess extends HttpServlet {

    private static final String SUCCESS = "viewCart.jsp";
    private static final String ERROR = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            LocalDate currentDate = LocalDate.now();
            Cart cart = (Cart) session.getAttribute("CART");
            float total = Float.parseFloat(request.getParameter("vnp_Amount")) / 100;
            ProductDAO dao = new ProductDAO();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
            if (vnp_ResponseCode.equals("00")) {
                boolean check = dao.checkInsert(user.getUserID(), currentDate, total);
                if (check) {
                    String orderID = dao.getOrderID();
                    boolean checkUpdateAndInsert = dao.CheckUpdateAndInsert(cart, orderID);
                    if (checkUpdateAndInsert) {
                        url = SUCCESS;
                        String email = user.getUserID();
                        if (email.contains("@")) {
                            dao.checkSendMail(email, "YOUR BILL", cart);
                            request.setAttribute("MESSAGE_CHECKOUT", "Checkout Successfull!!! Please check your email");
                        } else {
                            request.setAttribute("MESSAGE_CHECKOUT", "Checkout Successfull!!!");
                        }
                        session.removeAttribute("CART");
                    } else {
                        System.out.println("ERROR at checkInserOrderDetail");
                        request.setAttribute("MESSAGE_CHECKOUT", "Checkout Error!!!");
                        boolean checkDelete = dao.deleteOrder(orderID);
                    }

                }
            } else {
                request.setAttribute("MESSAGE_CHECKOUT", "Checkout Error!!!");
            }
        } catch (Exception e) {
            log("ERROR at CheckoutSuccessController" + e.toString());
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
