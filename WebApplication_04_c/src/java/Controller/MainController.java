/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author hoang
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    
    public boolean isValidLogin(String username , String password){
        return username.equals("admin")&& password.equals("12345678");
    }
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try( PrintWriter out = response.getWriter()) {
        String txtUsername = request.getParameter("username");
        String txtPassword = request.getParameter("password");
        
        //kiêm tra xem người dùng có nhập không ?
        if(txtUsername == null || txtPassword.trim().length() ==0){
            out.println("Please enter your username and password ! ");
            return;
        }
        
       //kiểm tra xem có đủ mật khẩu không ?
       if(txtPassword.trim().length() < 8){
           out.println("Password must be 8 letters length !");
           return;
       }
       //kiểm tra xem username = admin và passwword 12345678
        if(isValidLogin(txtUsername,txtPassword)){
            //cần phân biệt giữa forward và redirect là như thế nào
             // If login credentials are valid, forward the request to "search.html"
                // RequestDispatcher is used here to forward the request and response
                // Forwarding happens on the server side, and the URL in the browser does not change
                RequestDispatcher rd = request.getRequestDispatcher("search.html");
                rd.forward(request, response);
        }else{
            // If login credentials are invalid, redirect to "invalid.html"

                //forward , redirect
                //forward : 
//                RequestDispatcher rd = request.getRequestDispatcher("invalid.html");
//                rd.forward(request, response);

                //redirect
                response.sendRedirect("invalid.html");

               // compare rd.forward vs response.sendRedirect
        }
       } catch (Exception e) {
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
