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
import sample.sp24.t4s4.user.UserDAO;
import sample.sp24.t4s4.user.UserDTO;

/**
 *
 * @author admin
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR = "MainController";
    private static final String SUCCESS = "SearchController";
    private static String roleIDCheck = "";
    private static String userID = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();

        try {
            String userID = request.getParameter("userID");
            String fullname = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            UserDAO dao = new UserDAO();
            UserDTO user = new UserDTO(userID, fullname, roleID, userID);
            boolean check = dao.update(user);
            if (check) {
                roleIDCheck = roleID;
                userID = userID;
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at UpdateController" + e.toString());
        } finally {
            if (roleIDCheck.equals("US") && userID.equals(session.getAttribute("LOGIN_USER"))) {
                response.sendRedirect("login.html");
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
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
