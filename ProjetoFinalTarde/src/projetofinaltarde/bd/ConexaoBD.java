/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetofinaltarde.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author professor
 */
public class ConexaoBD {
         public static Connection getConexao()
            throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conectando ao Banco!");
            return DriverManager.getConnection("jdbc:mysql:"
                    + "//192.168.1.230/projetofinaltarde", "cristiano", "");
        } catch (ClassNotFoundException e) {
            System.out.println("Problemas na conexão!");
            throw new SQLException(e.getMessage());
        }
    }
    
}
