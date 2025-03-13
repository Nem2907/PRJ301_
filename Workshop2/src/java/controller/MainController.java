/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ExamCategoriesDAO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDAO;
import dao.ExamDAO;
import dto.ExamCategoriesDTO;
import dto.ExamsDTO;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.animation.Animation.Status;
import javax.websocket.Session;
import org.apache.coyote.http11.Http11AprProcessor;

/**
 *
 * @author hoang
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";

    public UserDTO getUser(String strUserID) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readByName(strUserID);
        return user;
    }

    public boolean isValidLogin(String strUserID, String strPassword) {
        UserDTO user = getUser(strUserID);
        System.out.println(user);
        return user != null && user.getPassword().equals(strPassword);
    }

    private String validateExamInput(String examTitle, String subject, String categoryIdStr, String totalMarksStr, String durationStr) {
        if (examTitle == null || examTitle.trim().isEmpty()) {
            return "Exam Title cannot be empty.";
        }
        if (subject == null || subject.trim().isEmpty()) {
            return "Subject cannot be empty.";
        }
        if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
            return "Please select a category.";
        }
        if (totalMarksStr == null || totalMarksStr.trim().isEmpty()) {
            return "Total Marks cannot be empty.";
        }
        if (durationStr == null || durationStr.trim().isEmpty()) {
            return "Duration cannot be empty.";
        }

        try {
            int categoryId = Integer.parseInt(categoryIdStr);
            if (categoryId <= 0) {
                return "Invalid category selected.";
            }
        } catch (NumberFormatException e) {
            return "Invalid category format.";
        }

        try {
            int totalMarks = Integer.parseInt(totalMarksStr);
            if (totalMarks <= 0) {
                return "Total Marks must be greater than 0.";
            }
        } catch (NumberFormatException e) {
            return "Total Marks must be a valid number.";
        }

        try {
            int duration = Integer.parseInt(durationStr);
            if (duration <= 0) {
                return "Duration must be greater than 0.";
            }
        } catch (NumberFormatException e) {
            return "Duration must be a valid number.";
        }

        return null;
    }

//    public void search(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String searchTerm = request.getParameter("searchTerm");
//        if (searchTerm == null) {
//            searchTerm = "";
//        }
//        StartUpProjectDAO projectDAO = new StartUpProjectDAO();
//
//        List<StartupProjectsDTO> projects = projectDAO.searchByName(searchTerm);
//        request.setAttribute("projects", projects);
//        request.setAttribute("searchTerm", searchTerm);
//    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = LOGIN_PAGE;
            ExamCategoriesDAO examCategoriesDAO = new ExamCategoriesDAO();
            ExamDAO examDAO = new ExamDAO();
            try {
                String action = request.getParameter("action");
                System.out.println(action);
                if (action == null) {
                    url = LOGIN_PAGE;
                } else {
                    if (action.equals("login")) {
                        String username = request.getParameter("txtUserName");
                        System.out.println(username);
                        String password = request.getParameter("txtPassword");
                        System.out.println(password);
                        if (isValidLogin(username, password)) {
                            System.out.println(isValidLogin(username, password));
                            url = "viewCategories.jsp";
                            UserDTO user = getUser(username);

                            request.getSession().setAttribute("user", user);
                            request.getSession().setAttribute("role", user.getRole());
                        } else {
                            request.setAttribute("message", "Incorrect UserID or Password");
                            url = "login.jsp";
                        }
                    } else if (action.equals("logout")) {
                        request.getSession().invalidate();
                        url = "login.jsp";
                    } else if (action.equals("viewCategories")) {

                        List<ExamCategoriesDTO> categories = examCategoriesDAO.readAll();
                        request.setAttribute("categories", categories);
                        url = "viewCategories.jsp";

                    } else if (action.equals("viewExams")) {
                        String categoryIdStr = request.getParameter("category_id");
                        if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
                            request.setAttribute("errorMessage", "Invalid Category ID.");
                            url = "viewCategories.jsp";
                        } else {
                            try {
                                int categoryId = Integer.parseInt(categoryIdStr);

                                List<ExamsDTO> examList = examDAO.getExamsByCategory(categoryId);

                                request.setAttribute("examList", examList);
                                url = "examlist.jsp";
                            } catch (NumberFormatException e) {
                                request.setAttribute("errorMessage", "Invalid Category ID format.");
                                url = "viewCategories.jsp";
                            }
                        }
                    } else if (action.equals("addExam")) {
                        String examTitle = request.getParameter("exam_title");
                        String subject = request.getParameter("subject");
                        String categoryIdStr = request.getParameter("category_id");
                        String totalMarksStr = request.getParameter("total_marks");
                        String durationStr = request.getParameter("duration");

                        if (examTitle == null || subject == null || categoryIdStr == null || totalMarksStr == null || durationStr == null) {
                            // Nếu chưa có dữ liệu (nghĩa là chỉ mới bấm "Create New Exam"), thì chuyển đến trang ExamForm.jsp
                            url = "ExamForm.jsp";
                        } else {

                            String message = validateExamInput(examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                            if (message != null) {
                                request.setAttribute("Error_Message", message);
                            } else {
                                int categoryId = Integer.parseInt(categoryIdStr);
                                int totalMarks = Integer.parseInt(totalMarksStr);
                                int duration = Integer.parseInt(durationStr);
                                ExamsDTO newExam = new ExamsDTO(0, examTitle, subject, categoryId, totalMarks, duration);
                                boolean success = examDAO.create(newExam);

                                if (success) {
                                    request.setAttribute("message", "Exam created successfully!"); 
                                    url = "ExamForm.jsp";
                                } else {
                                    request.setAttribute("errorMessage", "Failed to create exam.");
                                    url = "ExamForm.jsp";
                                }
                            }
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
