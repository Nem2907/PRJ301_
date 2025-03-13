/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ExamsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author hoang
 */
public class ExamDAO implements IDAO<ExamsDTO, String> {

    @Override
    public boolean create(ExamsDTO entity) {
         String sql = "INSERT INTO [dbo].[tblExams]([exam_title],[Subject],[category_id],[total_marks],[Duration])"
                + "VALUES (? , ? , ? , ? , ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, entity.getExam_tilte());
            ps.setString(2, entity.getSubject());
            ps.setInt(3, entity.getCategory_id());
            ps.setInt(4, entity.getToltal_marks());
            ps.setInt(5, entity.getDuration());
            int n = ps.executeUpdate();
            System.out.println("Rows affected: " + n);
            return n > 0;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(); // In lỗi ra console để debug
        } catch (SQLException ex) {
            Logger.getLogger(ExamsDTO  .class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<ExamsDTO> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExamsDTO readById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ExamsDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExamsDTO> search(String searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public List<ExamsDTO> getExamsByCategory(int categories_id) {
        List<ExamsDTO> exams = new ArrayList<>();
            String sql = "SELECT [exam_id],[exam_title],[Subject],[total_marks],[Duration] FROM [tblExams]"
                    + "WHERE [category_id] = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categories_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamsDTO newExam = new ExamsDTO(rs.getInt("exam_id"), 
                                                rs.getString("exam_title"), 
                                                rs.getString("Subject"),
                                                categories_id, 
                                                rs.getInt("total_marks"), 
                                                rs.getInt("Duration"));
                exams.add(newExam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exams;
    }
    

}


