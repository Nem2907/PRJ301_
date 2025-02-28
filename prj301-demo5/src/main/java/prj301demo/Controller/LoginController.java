package prj301demo.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import prj301demo.Users.UserDAO;
import prj301demo.Users.UserDTO;

/**
 *
 * @author DUNGHUYNH
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");
            String username = request.getParameter("user");
            String password = request.getParameter("password");

            if (action == null || action.equals("login")) {
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

                UserDAO dao = new UserDAO();
                UserDTO user = dao.login(username, password);

                if (user != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("usersession", user);
                    response.sendRedirect("StudentController");
                } else {
                    request.setAttribute("error", "Username or password is incorrect");
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }
            } else if (action != null && action.equals("logout")) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                    request.setAttribute("error", "Logout successfully");
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
