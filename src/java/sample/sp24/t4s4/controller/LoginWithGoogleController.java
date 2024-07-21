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
import sample.sp24.t4s4.user.UserGoogleDAO;
import sample.sp24.t4s4.user.UserGoogleDTO;

/**
 *
 * @author admin
 */
public class LoginWithGoogleController extends HttpServlet {

    private static final String SUCCESS = "user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            String code = request.getParameter("code");
            UserGoogleDAO dao = new UserGoogleDAO();
            String accessToken = dao.getToken(code);
            UserGoogleDTO userGoogle = dao.getUserInfo(accessToken);
            UserDTO user = new UserDTO(userGoogle.getEmail(), userGoogle.getName(), "US", userGoogle.getId());
            HttpSession session = request.getSession();
            UserDAO UsDAO = new UserDAO();
            boolean check = UsDAO.insert(user);
            if (check) {
                session.setAttribute("LOGIN_USER", new UserDTO(userGoogle.getEmail(), userGoogle.getName(), "US", "***********"));
                session.setAttribute("GOOGLE_ACCOUNT", userGoogle.getEmail());
                System.out.println(userGoogle);
            }else{
                session.setAttribute("LOGIN_USER", new UserDTO(userGoogle.getEmail(), userGoogle.getName(), "US", "***********"));
            }
        } catch (Exception e) {
            log("Error in LoginWithGoogleController" + e.toString());
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
