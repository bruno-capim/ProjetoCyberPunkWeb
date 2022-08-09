/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno Antunes
 */
public class ConectaBD {       
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost/bdcyberp";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String DRIVERBD = "com.mysql.jdbc.Driver";
    
    public static Connection getConexao(){
        if (conexao == null){
            try {
                Class.forName(DRIVERBD);           
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conexao;
    }
    
    public static void fechaConxao(){
        if (conexao != null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
