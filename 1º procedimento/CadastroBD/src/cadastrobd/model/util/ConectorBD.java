/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 *
 * @author Filipe
 */
public class ConectorBD {
    private Connection connection;
    private Properties properties = new Properties();
    
    public ConectorBD(){
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        try{
            if(connection == null || connection.isClosed()){
                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.user");
                String password = properties.getProperty("db.password");
                connection = DriverManager.getConnection(url, user, password);
                //System.out.println("Conexão estabelecida com sucesso!");
            }
        } catch(SQLException e){
            System.err.println("Erro ao estabelecer a conexão com o banco de dados: " + e.getMessage());
        }
        return connection;
    }
    public PreparedStatement getPrepared(String sql){
        try{
            return getConnection().prepareStatement(sql);
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getSelect(String sql){
        try{
            PreparedStatement preparedStatement = getPrepared(sql);
            if(preparedStatement != null){
                return preparedStatement.executeQuery();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void close(Statement statement){
        if(statement != null){
            try{
                statement.close();
            } catch(SQLException e){
            e.printStackTrace();
         }
        }
    }
    
    public void close(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            } catch(SQLException e){
            e.printStackTrace();
         }
        }
    }
    
        public void close(){
        if(connection != null){
            try{
                connection.close();
            } catch(SQLException e){
            e.printStackTrace();
         }
        }
    }
}
