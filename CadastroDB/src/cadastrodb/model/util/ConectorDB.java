/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrodb.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andre
 */
public class ConectorDB {
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;","loja","loja");
    }
    
    public static PreparedStatement getPrepared(Connection con, String sql) throws SQLException{
        PreparedStatement smt = con.prepareStatement(sql);
        return smt;
    }
    
    public static ResultSet getSelect(PreparedStatement prep) throws SQLException {
        ResultSet result = prep.executeQuery();
        return result;
    }
    
    public static void close(PreparedStatement smt){
        try {
            smt.close();
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
    
        public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
        
        public static void close(ResultSet result){
        try {
            result.close();
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
        
        public static void close(ResultSet result, PreparedStatement prep, Connection con){
            close(result);
            close(prep);
            close(con);
        }
}
