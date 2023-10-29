/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.util;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Filipe
 */
public class SequenceManager {
    private ConectorBD conn;
    
    public SequenceManager(ConectorBD conn){
        this.conn = conn;
    }
    
    public int getValue(String sequenceName) throws SQLException{
        int value = 0;
        String sql = "SELECT nextval('" + sequenceName + "')";
        
        try{
            ResultSet resultSet = conn.getSelect(sql);
            if(resultSet.next()){
                value = resultSet.getInt(1);
            }
            conn.close(resultSet);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return value;
    }
}
