/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetofinaltarde.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projetofinaltarde.metodos.Autores;

/**
 *
 * @author professor
 */
public class AutoresBD {
    private Connection conexao;
    
    public AutoresBD() throws SQLException {
        this.conexao = ConexaoBD.getConexao();
    } 
    public void adiciona(Autores x) throws SQLException {
        String sql = "insert into autores (nome)"                
                + "values(?)";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);        
        stmt.setString(1, x.getNome());
        
        
        stmt.execute();
        stmt.close();
    }
    
    
     public void alterar(Autores x) throws SQLException {
        String sql = "update clientes set nome=? where id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
       stmt.setString(1, x.getNome());         
        stmt.setInt(2, x.getId());

        stmt.execute();
        stmt.close();
    }
     
     public void remove(Autores x) throws SQLException {
        String sql = "delete from autores where id=?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, x.getId());
        
        stmt.execute();
        stmt.close();
    }
      
    public List<Autores> getLista(String login) throws SQLException{
        String sql = "select * from autores where nome like ?";
        
        PreparedStatement stmt = (PreparedStatement) 
                this.conexao.prepareStatement(sql);
        stmt.setString(1, login);
        
        ResultSet rs = stmt.executeQuery();
        List<Autores> minhaLista = new ArrayList<>();
        while (rs.next()){
            Autores X = new Autores();            
            X.setId(rs.getInt("id"));            
            X.setNome(rs.getString("nome"));            
            
            minhaLista.add(X);
        }
        rs.close();
        stmt.close();
        return minhaLista;
    }
}
