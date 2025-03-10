/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StartUpProjectDAO;
import dao.UserDAO;
import dto.StartupProjectsDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Validate.InputValidator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javafx.animation.Animation.Status;
import javax.websocket.Session;
import org.apache.coyote.http11.Http11AprProcessor;
import sun.java2d.pipe.ValidatePipe;

/**
 *
 * @author hoang
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String NUMBER_PATTERN = "^\\d+$";

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

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null) {
            searchTerm = "";
        }
        StartUpProjectDAO projectDAO = new StartUpProjectDAO();

        List<StartupProjectsDTO> projects = projectDAO.searchByName(searchTerm);
        request.setAttribute("projects", projects);
        request.setAttribute("searchTerm", searchTerm);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = LOGIN_PAGE;
            StartUpProjectDAO projectDAO = new StartUpProjectDAO();
            try {
                String action = request.getParameter("action");
                if (action == null) {
                    url = LOGIN_PAGE;
                } else {
                    if (action.equals("login")) {
                        String username = request.getParameter("txtUserName");
                        String password = request.getParameter("txtPassword");
                        if (isValidLogin(username, password)) {
                            HttpSession session = request.getSession();
                            url = "search.jsp";
                            UserDTO user = getUser(username);
                            request.getSession().setAttribute("user", user);
                            request.getSession().setAttribute("role", user.getRole());

                            url = "search.jsp";
                            StartUpProjectDAO projectD = new StartUpProjectDAO();
                            String searchTerm = request.getParameter("searchTerm");
                            if (searchTerm == null) {
                                searchTerm = "";
                            }
                            List<StartupProjectsDTO> projects = projectD.searchByName(searchTerm);
                            request.setAttribute("projects", projects);
                            request.setAttribute("searchTerm", searchTerm);

                        } else {
                            request.setAttribute("message", "Incorrect UserID or Password");
                            url = "login.jsp";
                        }
                    } else if (action.equals("logout")) {
                        request.getSession().invalidate();
                        url = "login.jsp";
                    } else if (action.equals("search")) {
                        url = "search.jsp";
                        String searchTerm = request.getParameter("searchTerm");
                        List<StartupProjectsDTO> projects = projectDAO.searchByName(searchTerm);
                        request.setAttribute("projects", projects);
                        request.setAttribute("searchTerm", searchTerm);

                    } else if (action.equals("add")) {
                        url = "projectForm.jsp";
                        HttpSession session = request.getSession();
                        UserDTO user = (UserDTO) session.getAttribute("user");

                        if (user != null && "founder".equalsIgnoreCase(user.getRole())) {
                            boolean checkError = false;

//                            int project_id = Integer.parseInt(request.getParameter("txtProjectID"));
////                            System.out.println("Project ID received: " + project_id);
//                            String project_name = request.getParameter("txtProjectName");
////                            System.out.println(project_name);
//                            String description = request.getParameter("txtDescription");
////                            System.out.println(description);
//                            String Status = request.getParameter("txtStatus");
////                            System.out.println(Status);
//                            Date estimated_launch = Date.valueOf(request.getParameter("txtDate"));
////                            System.out.println(estimated_launch);
                            // Validate Project ID
                            //vì do tk project_id là int nhma getPara thì lại là String nên muốn kiểm tra phải làm lại
                            int project_id = 0;
                            try {
                                project_id = Integer.parseInt(request.getParameter("txtProjectID"));
                                if (project_id <= 0) {
                                    request.setAttribute("project_id_error", "Project ID must be a positive number.");
                                    checkError = true;
                                } else if (projectDAO.isProjectIdExists(project_id)) {
                                    checkError = true;
                                    request.setAttribute("project_id_error", "Project ID must not be duplicated ");
                                }
                            } catch (NumberFormatException e) {
                                request.setAttribute("project_id_error", "Project ID must be a valid integer.");
                                checkError = true;
                            }
                            String project_name = request.getParameter("txtProjectName");
                            if (project_name == null || project_name.trim().isEmpty()) {
                                request.setAttribute("project_name_error", "Project Name cannot be empty.");
                                checkError = true;
                            } else if (project_name.length() > 100) {
                                request.setAttribute("project_name_error", "Project Name cannot exceed 100 characters.");
                                checkError = true;
                            }

                            // Validate Description
                            String description = request.getParameter("txtDescription");
                            if (description == null || description.trim().isEmpty()) {
                                request.setAttribute("description_error", "Description cannot be empty.");
                                checkError = true;
                            }

                            // Validate Status
                            String status = request.getParameter("txtStatus");
                            if (status == null || !(status.equals("Ideation") || status.equals("Development") || status.equals("Launch") || status.equals("Scaling"))) {
                                request.setAttribute("Status_Error", "Invalid Status selection.");
                                checkError = true;
                            }

                            // Validate Estimated Launch Date
                            Date estimated_launch = null;
                            try {
                                estimated_launch = Date.valueOf(request.getParameter("txtDate"));
                                if (estimated_launch.before(new Date(System.currentTimeMillis()))) {
                                    request.setAttribute("estimated_launch_error", "Estimated Launch Date must be in the future.");
                                    checkError = true;
                                }
                            } catch (IllegalArgumentException e) {
                                request.setAttribute("estimated_launch_error", "Invalid date format.");
                                checkError = true;
                            }
                            
                            StartupProjectsDTO project = new StartupProjectsDTO(project_id, project_name, description, status, estimated_launch);
                            if (!checkError) {
                                projectDAO.create(project);
                                url = "search.jsp";
                                search(request, response);
                            } else {
                                request.setAttribute("projects", project);
                                url = "projectForm.jsp";
                            }
                        } else {
                            // User không có quyền founder
                            request.setAttribute("message", "No Permission");
                        }
                    } else if (action.equals("updateStatus")) {
                        url = "updateStatus.jsp";
                        HttpSession sessionObj = request.getSession();
                        UserDTO user = (UserDTO) sessionObj.getAttribute("user");

                        if (user != null && "founder".equalsIgnoreCase(user.getRole())) {
                            int project_id = (Integer.parseInt(request.getParameter("project_id")));
                            String newStatus = request.getParameter("newStatus");
                            System.out.println(newStatus);
                            
                            Set<String> validStatuses = new HashSet<>(Arrays.asList("Development", "Launch", "Scaling", "Ideation"));

                            if (!validStatuses.contains(newStatus)) {
                                request.setAttribute("message", "Status must be Development or Launch or Scaling or Ideation");
                            } else {
                                boolean updated = projectDAO.UpdateByStatus(project_id, newStatus);

                                if (updated) {
                                    request.setAttribute("message", "Project status updated successfully!");
                                } else {
                                    request.setAttribute("message", "Prpject status updated failed!");
                                }
                            }
                        } else {
                            request.setAttribute("message", "You do not have permission to update project status.");
                        }
                        url = "updateStatus.jsp";
                    } else if (action.equals("return")) {
                        url = "search.jsp";
                        //return lại trang search , và hiển thị các projects 
                        String searchTerm = request.getParameter("searchTerm");
                        if (searchTerm == null) {
                            searchTerm = "";
                        }
                        List<StartupProjectsDTO> projects = projectDAO.searchByName(searchTerm);
                        request.setAttribute("projects", projects);
                        request.setAttribute("searchTerm", searchTerm);
//                    }else if (action.equals("searchByName")) {
//                        url = "searchByName.jsp";
//                        String searchTerm = request.getParameter("searchTerm");
//                        List<StartupProjectsDTO> projects = projectDAO.searchByName(searchTerm);
//                        request.setAttribute("searchResults", projects);
//                    }
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
