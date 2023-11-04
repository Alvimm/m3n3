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
    private ConectorBD connection;
    
    public SequenceManager(ConectorBD connection){
        this.connection = connection;
    }
    
    public int getValue(String sequenceName) throws SQLException{
        int value = 0;
        String sql = "SELECT nextval('" + sequenceName + "')";
        
        try{
            ResultSet resultSet = connection.getSelect(sql);
            if(resultSet.next()){
                value = resultSet.getInt(1);
            }
            connection.close(resultSet);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return value;
    }
}
