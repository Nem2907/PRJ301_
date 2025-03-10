/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tungi
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public static final String LOGIN_PAGE = "login.jsp";
    public static final String SEARCH_PAGE = "search.jsp";

    public UserDTO getUser(String strUserName) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readByName(strUserName);
        return user;
    }

    public boolean isValidLogin(String strUserID, String strPassword) {
        UserDTO user = getUser(strUserID);
        System.out.println(user);
        return user != null && user.getPassword().equals(strPassword);
    }
    public boolean create(UserDTO entity) {
        
        UserDAO udao = new UserDAO();
        if (udao.isUserExists(entity.getUserName())) {
            System.out.println("UserName đã tồn tại!");
            return false;
        }
        return true;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = LOGIN_PAGE;
            UserDAO userDAO = new UserDAO();
            try {
                String action = request.getParameter("action");
                if (action == null) {
                    url = LOGIN_PAGE;
                } else {
                    if (action.equals("login")) {
                        String userName = request.getParameter("txtUserName");
                        String passWord = request.getParameter("txtPassword");
                        System.out.println(userName);
                        System.out.println(passWord);
                        if (isValidLogin(userName, passWord)) {
                            HttpSession session = request.getSession();
                            url = "menu.jsp";
                            UserDTO user = getUser(userName);
                            request.getSession().setAttribute("user", user);
                            request.getSession().setAttribute("role", user.getRole());
                        } else {
                            request.setAttribute("errorMessage", "Invalid UserName or Password !");
                            url = "login.jsp";
                        }
                    } else if (action.equals("Register")) {
                        url = "register.jsp";
                    } else if (action.equals("logout")) {
                        request.getSession().invalidate();
                        url = "login.jsp";
                    } else if (action.equals("register-account")) {
                        String username = request.getParameter("txtUserName");
                        String password = request.getParameter("txtPassword");
                        String fullName = request.getParameter("txtFullName");
                        String phoneNumber = request.getParameter("txtPhoneNumber");
                        String email = request.getParameter("txtEmail");
                        String confirmPassword = request.getParameter("txtConfirm-Password");

                        // Biến để lưu thông báo lỗi
                        boolean isCheckError = true ;

                        
                        //kiểm tra confirm Password có giống password cũ ko?
                        if (!password.equals(confirmPassword)) {
                            isCheckError = true;
                            request.setAttribute("confirm_ErrorMessage","Confirm Password must be equal to Password !");
                        }
                        //kiểm tra password
                        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
                            request.setAttribute("confirmPassword_ErrorMessage","ConfirmPassword can't be null !");
                        }
                        //kiểm tra xem userName có bị trùng không ?
                        if(userDAO.isUserExists(username)){
                            isCheckError = true;
                            request.setAttribute("userName_ExisterrorMessage","This username is used !");
                        }
                        // Kiểm tra username có bị null ko
                        if (username == null || username.trim().isEmpty()) {
                            isCheckError = true;
                            request.setAttribute("userName_ErrorMessage","Username can't be Empty !");
                        }
                        
                        // Kiểm tra mật khẩu (độ dài tối thiểu 6 ký tự)
                        if (password == null || password.length() < 6) {
                            isCheckError = true;
                            request.setAttribute("password_errorMessage","Password can't be Empty!");
                        }
                                               
                        // Kiểm tra fullname
                        if (fullName == null || fullName.trim().isEmpty()) {
                            isCheckError = true;
                            request.setAttribute("fullname_errorMessage", "FullName can't be Empty ");
                        }

                        // Kiểm tra số điện thoại (chỉ chứa số, độ dài hợp lệ)
                        if (phoneNumber == null || !phoneNumber.matches("\\d{10,11}")) {
                            isCheckError = true;
                            request.setAttribute("phoneNumber_errorMessage","PhoneNumber is Invalid");
                        }

                        // Kiểm tra email đúng định dạng
                        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                            isCheckError = true;
                            request.setAttribute("email_errorMessage","Email is Invalid !");
                        }

                        // Nếu có lỗi, quay lại trang đăng ký
                        if (isCheckError) {
                            // Nếu hợp lệ, tiếp tục xử lý đăng ký (thêm vào database)
                            UserDTO user = new UserDTO(username, password, fullName, phoneNumber, email, "User");
                            userDAO.create(user);
                            // Chuyển hướng đến trang đăng nhập và dừng chương trình
                            request.setAttribute("Message", "Register Sucessfully");
                            url = "register.jsp";
                        } else {
                            url = "register.jsp";
                        }

                    }

                }
            } catch (Exception e) {
                log("Error in MainController: " + e.toString());
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
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
