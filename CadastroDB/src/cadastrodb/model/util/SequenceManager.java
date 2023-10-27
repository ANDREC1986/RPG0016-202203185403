/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrodb.model.util;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author andre
 */
public class SequenceManager {
    public static int getValue() throws SQLException{
        Connection con = ConectorDB.getConnection();
        PreparedStatement smt = ConectorDB.getPrepared(con,"SELECT NEXT VALUE FOR idPessoa_SEQ AS VALOR");
        ResultSet result = ConectorDB.getSelect(smt);
        result.next();
        return result.getInt("VALOR");
    }
}