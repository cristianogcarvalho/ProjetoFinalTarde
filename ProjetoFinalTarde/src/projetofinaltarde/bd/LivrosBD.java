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
import projetofinaltarde.metodos.Livros;
import projetofinaltarde.metodos.Livros;

/**
 *
 * @author professor
 */
public class LivrosBD {
    
    private Connection conexao;
    
    public LivrosBD() throws SQLException {
        this.conexao = ConexaoBD.getConexao();
    } 
    public void adiciona(Livros x) throws SQLException {
        String sql = "insert into livros (nome,cidade)"                
                + "values(?,?)";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);        
        stmt.setString(1, x.getNome());
        stmt.setString(2, x.getCidade());
        
        
        stmt.execute();
        stmt.close();
    }
    
    
     public void alterar(Livros x) throws SQLException {
        String sql = "update clientes set nome=?,cidade=? where id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
       stmt.setString(1, x.getNome());         
       stmt.setString(2, x.getCidade());         
        stmt.setInt(3, x.getId());

        stmt.execute();
        stmt.close();
    }
     
     public void remove(Livros x) throws SQLException {
        String sql = "delete from livros where id=?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, x.getId());
        
        stmt.execute();
        stmt.close();
    }
      
    public List<Livros> getLista(String login) throws SQLException{
        String sql = "select * from livros where nome like ?";
        
        PreparedStatement stmt = (PreparedStatement) 
                this.conexao.prepareStatement(sql);
        stmt.setString(1, login);
        
        ResultSet rs = stmt.executeQuery();
        List<Livros> minhaLista = new ArrayList<>();
        while (rs.next()){
            Livros X = new Livros();            
            X.setId(rs.getInt("id"));            
            X.setNome(rs.getString("nome")); 
            X.setCidade(rs.getString("cidade")); 
            
            
            minhaLista.add(X);
        }
        rs.close();
        stmt.close();
        return minhaLista;
    }
}
