/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;
import dto.StartupProjectsDTO;
import dto.UserDTO;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class StartUpProjectDAO implements IDAO<StartupProjectsDTO, String> {

    @Override
    public boolean create(StartupProjectsDTO entity) {
        String sql = "INSERT INTO [dbo].[tblStartupProjects]([project_id],[project_name],[Description],[Status],[estimated_launch])"
                + "VALUES (? , ? , ? , ? , ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, entity.getProject_id());
            ps.setString(2, entity.getProject_name());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getStatus());
            ps.setDate(5, entity.getEstimated_launch());
            int n = ps.executeUpdate();
            System.out.println("Rows affected: " + n);
            return n > 0;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(); // In lỗi ra console để debug
        } catch (SQLException ex) {
            Logger.getLogger(StartUpProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<StartupProjectsDTO> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StartupProjectsDTO readById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(StartupProjectsDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StartupProjectsDTO> search(String searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<StartupProjectsDTO> searchByName(String searchTerm) {
        List<StartupProjectsDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[tblStartupProjects] "
                + " WHERE [project_name] LIKE ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StartupProjectsDTO project = new StartupProjectsDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch"));
                list.add(project);
            }
        } catch (Exception e) {
        }
        return list;

    }

    public List<StartupProjectsDTO> searchByStatus(String searchStatus) {
        List<StartupProjectsDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[tblStartupProjects] "
                + " WHERE [Status] LIKE ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchStatus + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StartupProjectsDTO project = new StartupProjectsDTO(rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch"));
                list.add(project);
            }
        } catch (Exception e) {
        }
        return list;

    }

    public boolean UpdateByStatus(int project_id, String newStatus) {
        String sql = "UPDATE [dbo].[tblStartupProjects] "
                + "SET [Status] = ? "
                + "Where [project_id] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newStatus);
            ps.setInt(2, project_id);
            int n = ps.executeUpdate();
            return n > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public List<StartupProjectsDTO> searchByName2(String searchTerm) {
        String sql = "SELECT * FROM [dbo].[tblStartupProjects] WHERE project_name LIKE ? ";
        List<StartupProjectsDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StartupProjectsDTO project = new StartupProjectsDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch"));
                list.add(project);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }

    public boolean isProjectIdExists(int projectId) {
        String sql = "SELECT COUNT(*) FROM StartupProjects WHERE project_id = ?";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public List<StartupProjectsDTO> UpdateByStatus(String oldstatus, String newStatus) {
//        String updateSql = "UPDATE [dbo].[tblStartupProjects] "
//                          + "SET [Status] = ? "
//                          + "WHERE [Status] = ?";
//        
//        String selectSql = "SELECT * FROM [dbo].[tblStartupProjects] "
//                          + "WHERE [Status] = ?";
//        List<StartupProjectsDTO> updatedProjects = new ArrayList<>();
//
//        try {
//            Connection conn = DBUtils.getConnection();
//            PreparedStatement psUpdate = conn.prepareStatement(updateSql);
//
//            psUpdate.setString(1, newStatus);
//            psUpdate.setString(2, oldstatus);
//            int n = psUpdate.executeUpdate();
//
//            if (n > 0) {
//                PreparedStatement psSelect = conn.prepareStatement(selectSql);
//                psSelect.setString(1, newStatus);
//                ResultSet rs = psSelect.executeQuery();
//                while (rs.next()) {
//                    updatedProjects.add(new StartupProjectsDTO(
//                            rs.getInt("project_id"),
//                            rs.getString("project_name"),
//                            rs.getString("Description"),
//                            rs.getString("Status"),
//                            rs.getDate("estimated_launch")));
//                }
//            }
//        } catch (Exception e) {
//        }
//        return updatedProjects;
//    }
}
