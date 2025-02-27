/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.BookDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Utils.DBUtils;

/**
 *
 * @author tungi
 */
public class BookDAO implements IDAO<BookDTO, String> {

    
    public boolean create(BookDTO entity) {
        return false;
    }

    
    public List<BookDTO> readAll() {
        return null;
    }

    
    public BookDTO readById(String id) {
        return null;
    }

 
    public boolean update(BookDTO entity) {
        return false;
    }


    public boolean delete(String id) {
        return false;
    }


    public List<BookDTO> search(String searchTerm) {
        return null;
    }

    public List<BookDTO> searchByTitle(String searchTerm) {
        String sql ="SELECT * FROM tblBooks WHERE title LIKE ?";
        List<BookDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+searchTerm+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BookDTO book = new BookDTO(
                        rs.getString("BookID"), 
                        rs.getString("Title"), 
                        rs.getString("Author"), 
                        rs.getInt("PublishYear"), 
                        rs.getDouble("Price"), 
                        rs.getInt("Quantity"));
                list.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }
    
    public List<BookDTO> searchByTitle2(String searchTerm) {
        String sql ="SELECT * FROM tblBooks WHERE title LIKE ? "
                + "AND Quantity > 10";
        List<BookDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+searchTerm+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BookDTO book = new BookDTO(
                        rs.getString("BookID"), 
                        rs.getString("Title"), 
                        rs.getString("Author"), 
                        rs.getInt("PublishYear"), 
                        rs.getDouble("Price"), 
                        rs.getInt("Quantity"));
                list.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }
    public boolean updateQuantityToZero(String id){
        String sql = "Update tblBooks SET Quantity = 0 Where BookID = ?";
        
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(0, id);
            int i = ps.executeUpdate(); 
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

}