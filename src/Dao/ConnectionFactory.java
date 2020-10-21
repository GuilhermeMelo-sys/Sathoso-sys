/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pizzaria.View.FrameAcesso;

/**
 *
 * @author Guilh
 */
public class ConnectionFactory {
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String USUARIO =  FrameAcesso.usuario;
    private final static String SENHA = FrameAcesso.senha;
    private final static String URL = "jdbc:mysql://localhost:3306/"+FrameAcesso.bancoDeDados+"?useTimezone=true&serverTimezone=UTC";
    
    public static java.sql.Connection connection(){
        try {
            Class.forName(DRIVER);
     
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao banco de dados");
        }
    }

    public static void close(Connection con){
        try {
            if(con != null)
                con.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão: "+ ex);
        }
    }
    
    public static void close(Connection con, PreparedStatement pstm){
        close(con);
        
        try {
            if(pstm != null) 
                pstm.close();
         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar o PreparedStatement"+ ex);
        }
    }
    
    public static void close(Connection con, PreparedStatement pstm, ResultSet rs){
        close(con, pstm);
        try {
            if(rs != null) 
                rs.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar o ResultSet"+ ex);
        }
    }
}
