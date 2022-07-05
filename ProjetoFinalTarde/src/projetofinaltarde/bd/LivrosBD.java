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
        String sql = "insert into livros (nome,autor,ISBN,editora,descricao,)"                
                + "values(?,?,?,?,?)";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);        
        stmt.setString(1, x.getNome());
        stmt.setString(2, x.getAutor());
        stmt.setString(3, x.getIsbn());
        stmt.setString(4, x.getEditora());
        stmt.setString(6, x.getDescricao());
        
        
        stmt.execute();
        stmt.close();
    }
 
    
     public void alterar(Livros x) throws SQLException {
        String sql = "update clientes set nome=?,nome=?,autor=?,ISBN=?,editora=?,descricao=?, where id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
       stmt.setString(1, x.getNome());
        stmt.setString(2, x.getAutor());
        stmt.setString(3, x.getIsbn());
        stmt.setString(4, x.getEditora());
        stmt.setString(5, x.getDescricao());
        stmt.setString(6, x.getDescricao());        
        stmt.setInt(7, x.getId());

        stmt.execute();
        stmt.close();
    }
     
     public void remove(Livros x) throws SQLException {
        String sql = "delete from estudantes where id=?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, x.getId());
        
        stmt.execute();
        stmt.close();
    }
      
    public List<Livros> getLista(String login) throws SQLException{
        String sql = "select * from estudantes where nome like ?";
        
        PreparedStatement stmt = (PreparedStatement) 
                this.conexao.prepareStatement(sql);
        stmt.setString(1, login);
        
        ResultSet rs = stmt.executeQuery();
        List<Livros> minhaLista = new ArrayList<>();
        while (rs.next()){
            Livros X = new Livros();            
            X.setId(rs.getInt("id"));            
            X.setNome(rs.getString("nome")); 
            X.setAutor(rs.getString("autor"));
            X.setIsbn(rs.getString("isbn")); 
            X.setEditora(rs.getString("editora"));  
            X.setDescricao(rs.getString("descricao")); 
            
            
            minhaLista.add(X);
        }
        rs.close();
        stmt.close();
        return minhaLista;
    }
}
