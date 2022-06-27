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
import projetofinaltarde.metodos.Funcionarios;

/**
 *
 * @author professor
 */
public class FuncionariosBD {
    private Connection conexao;
    
    public FuncionariosBD() throws SQLException {
        this.conexao = ConexaoBD.getConexao();
    } 
    public void adiciona(Funcionarios x) throws SQLException {
        String sql = "insert into editoras (nome,login,senha,email,telefone)"                
                + "values(?,?,?,?,?)";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);        
        stmt.setString(1, x.getNome());
        stmt.setString(2, x.getLogin());
        stmt.setString(3, x.getSenha());
        stmt.setString(4, x.getEmail());
        stmt.setString(5, x.getTelefone());
        
        
        stmt.execute();
        stmt.close();
    }
    
    
     public void alterar(Funcionarios x) throws SQLException {
        String sql = "update clientes set nome=?,login=?,senha=?,email=?,telefone=? where id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
       stmt.setString(1, x.getNome());         
       stmt.setString(2, x.getLogin());         
       stmt.setString(3, x.getSenha());         
       stmt.setString(4, x.getEmail());         
       stmt.setString(5, x.getTelefone());         
        stmt.setInt(6, x.getId());

        stmt.execute();
        stmt.close();
    }
     
     public void remove(Funcionarios x) throws SQLException {
        String sql = "delete from editoras where id=?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, x.getId());

        
        stmt.execute();
        stmt.close();
    }
      
    public List<Funcionarios> getLista(String nome) throws SQLException{
        String sql = "select * from editoras where nome like ?";
        
        PreparedStatement stmt = (PreparedStatement) 
                this.conexao.prepareStatement(sql);
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        List<Funcionarios> minhaLista = new ArrayList<>();
        while (rs.next()){
            Funcionarios X = new Funcionarios();            
            X.setId(rs.getInt("id"));            
            X.setNome(rs.getString("nome"));            
            X.setLogin(rs.getString("login"));            
            X.setSenha(rs.getString("senha"));            
            X.setEmail(rs.getString("email"));                       
            X.setTelefone(rs.getString("telefone"));            
            
            minhaLista.add(X);
        }
        rs.close();
        stmt.close();
        return minhaLista;
    }
}
