/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import dto.ExamCategoriesDTO;
import dto.ExamsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;
/**
 *
 * @author hoang
 */
 public class ExamCategoriesDAO implements IDAO<ExamCategoriesDTO,String>{

    @Override
    public boolean create(ExamCategoriesDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExamCategoriesDTO> readAll() {
        List<ExamCategoriesDTO> categories = new ArrayList<>();
        String sql = "SELECT [category_id] ,[category_name] ,[description] FROM [dbo].[tblExamCategories]";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ExamCategoriesDTO exam = new ExamCategoriesDTO(rs.getInt("category_id"),
                                      rs.getString("category_name"),
                                      rs.getString("description"));
                categories.add(exam);
            }
            return categories;
        } catch (Exception e) {
        }
        return null;
        
    }    

    @Override
    public ExamCategoriesDTO readById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ExamCategoriesDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExamCategoriesDTO> search(String searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<ExamCategoriesDTO> getExamsByCategory(int categories_id) {
        List<ExamCategoriesDTO> categories = new ArrayList<>();
            String sql = "SELECT [category_id], [category_name], [description] FROM [tblExamCategories]";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ExamCategoriesDTO newCategories = new ExamCategoriesDTO(rs.getInt("category_id"),
                                                               rs.getString("category_name"),
                                                                rs.getString("description"));
                categories.add(newCategories);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
    
}
