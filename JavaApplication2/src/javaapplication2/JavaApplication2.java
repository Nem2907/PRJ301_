/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author hoang
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String server = "NEM\\SQLEXPRESS";
        String username = "sa";
        String password = "12345";
        String Db_Name = "Web_05_a";
        int port = 1433;
        
       SQLServerDataSource ds = new SQLServerDataSource();
       ds.setUser(username);
       ds.setPassword(password);
       ds.setDatabaseName(Db_Name);
       ds.setServerName(server);
       ds.setPortNumber(port);
       ds.setEncrypt(false);
        try(Connection conn = ds.getConnection()) {
            System.out.println("Kết nối thành công!");
            System.out.println(conn.getCatalog());
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
