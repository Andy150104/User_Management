/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.t4s4.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.sp24.t4s4.shopping.Cart;
import sample.sp24.t4s4.shopping.Computer;

/**
 *
 * @author admin
 */
public class AddController extends HttpServlet {

    private static final String ERROR="shopping2.jsp";
    private static final String SUCCESS="shopping2.jsp";
//    private static final String ERROR="shopping.jsp";
//    private static final String SUCCESS="shopping.jspr";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        try {
//            String strComputer=request.getParameter("computer");
//            String[] str=strComputer.split("-");
//            String id=str[0];
//            String name=str[1];
//            double price=Double.parseDouble(str[2]);
            String id=request.getParameter("id");
            String name=request.getParameter("name");
            double price=Double.parseDouble(request.getParameter("price").replaceAll(",", ""));
            int quantity=Integer.parseInt(request.getParameter("quantity"));
            HttpSession session=request.getSession();
            if(session!=null){
                Cart cart=(Cart) session.getAttribute("CART");
                if(cart==null){
                    cart=new Cart();
                }
                Computer computer=new Computer(id, name, price, quantity);
                boolean check=cart.add(computer);
                if(check){
                    session.setAttribute("CART", cart);
                    request.setAttribute("MESSAGE", name+"with "+quantity+" items added successfully! ");
                    url=SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at AddController:"+e.toString());
        }finally{
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
