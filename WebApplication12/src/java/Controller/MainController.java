/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BookDAO;
import DAO.UserDAO;
import dto.BookDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tungi
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public BookDAO bookDAO = new BookDAO();

    private static final String LOGIN_PAGE = "login.jsp";

    public UserDTO getUser(String strUserID) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readById(strUserID);
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
        List<BookDTO> books = bookDAO.searchByTitle(searchTerm);
        request.setAttribute("books", books);
        request.setAttribute("searchTerm", searchTerm);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    String strUserID = request.getParameter("txtUserID");
                    String strPassword = request.getParameter("txtPassword");
                    if (isValidLogin(strUserID, strPassword)) {
                        url = "search.jsp";
                        UserDTO user = getUser(strUserID);
                        request.getSession().setAttribute("user", user);
                    } else {
                        request.setAttribute("message", "Incorrect UserID or Password");
                        url = "login.jsp";
                    }
                } else if (action.equals("logout")) {
                    request.getSession().invalidate(); // Hủy session
//                   request.setAttribute("user", null);
                    url = "login.jsp";
                } else if (action.equals("search")) {
                    url = "search.jsp";
                    BookDAO bdao = new BookDAO();
                    String searchTerm = request.getParameter("searchTerm");
                    List<BookDTO> books = bdao.searchByTitle(searchTerm);
                    request.setAttribute("books", books);
                    request.setAttribute("searchTerm", searchTerm);
                } else if (action.equals("delete")) {
                    BookDAO bdao = new BookDAO();
                    String id = request.getParameter("id");
                    bdao.updateQuantityToZero(id);
                } else if (action.equals("add")) {
                    // tạo biến để check error
                    boolean checkError = false ;
                    String bookID = request.getParameter("txtBookID");
                    String title = request.getParameter("txtTitle");
                    String author = request.getParameter("txtAuthor");
                    int publishYear = Integer.parseInt(request.getParameter("txtPublishYear"));
                    double price = Double.parseDouble(request.getParameter("txtPrice"));
                    int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                    
                    // những quyển sách này bị null và rỗng
                    if(bookID == null || bookID.trim().isEmpty()){
                        checkError = true ;
                        request.setAttribute("BookID_Error","BookID can not be empty");
                    }
                    if(title == null || title.trim().isEmpty()){
                        checkError = true ;
                        request.setAttribute("title_Error"," can not be empty");
                    }
                    if(author == null || author.trim().isEmpty()){
                        checkError = true ;
                        request.setAttribute("author_Error","BookID can not be empty");
                    }
                    if(publishYear > 2000){
                        checkError = true ;
                        request.setAttribute("publishYear_Error","BookID can not be empty");
                    }
                    if(price < 0){
                        checkError = true ;
                        request.setAttribute("price_Error","Price must be positive");
                    }
                    if(quantity < 0){
                        checkError = true ;
                        request.setAttribute("quantity_Error","quantity must be positive");
                    }
                    

                    
                    BookDTO book = new BookDTO(bookID, title, author, publishYear, price, quantity);
                    if(!checkError){
                    bookDAO.create(book);
                    //search
                    url =  "search.jsp";
                    search(request, response);
                    }else{
                        request.setAttribute("book", book);
                        url = "bookForm.jsp";
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
 