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
import projetofinaltarde.metodos.Estudantes;

/**
 *
 * @author professor
 */
public class EstudantesBD {
    private Connection conexao;
    
    public EstudantesBD() throws SQLException {
        this.conexao = ConexaoBD.getConexao();
    } 
    public void adiciona(Estudantes x) throws SQLException {
        String sql = "insert into estudantes (turma,nome,email,cpf,endereço,telefone)"                
                + "values(?,?,?,?,?,?)";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);        
        stmt.setString(1, x.getTurma());
        stmt.setString(2, x.getNome());
        stmt.setString(3, x.getEmail());
        stmt.setString(4, x.getCpf());
        stmt.setString(5, x.getEndereco());
        stmt.setString(6, x.getTelefone());
        
        
        stmt.execute();
        stmt.close();
    }
    
    
     public void alterar(Estudantes x) throws SQLException {
        String sql = "update clientes set turma=?,nome=?,email=?,cpf=?,endereco=?,telefone=? where id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
       stmt.setString(1, x.getTurma());         
       stmt.setString(2, x.getNome());         
       stmt.setString(3, x.getEmail());         
       stmt.setString(4, x.getCpf());         
       stmt.setString(5, x.getEndereco());         
       stmt.setString(6, x.getTelefone());         
        stmt.setInt(7, x.getId());

        stmt.execute();
        stmt.close();
    }
     
     public void remove(Estudantes x) throws SQLException {
        String sql = "delete from estudantes where id=?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, x.getId());
        
        stmt.execute();
        stmt.close();
    }
      
    public List<Estudantes> getLista(String nome) throws SQLException{
        String sql = "select * from estudantes where nome like ?";
        
        PreparedStatement stmt = (PreparedStatement) 
                this.conexao.prepareStatement(sql);
        stmt.setString(1, nome);
        
        ResultSet rs = stmt.executeQuery();
        List<Estudantes> minhaLista = new ArrayList<>();
        while (rs.next()){
            Estudantes X = new Estudantes();            
            X.setId(rs.getInt("id"));            
            X.setTurma(rs.getString("turma")); 
            X.setNome(rs.getString("nome")); 
            X.setEmail(rs.getString("email")); 
            X.setCpf(rs.getString("cpf")); 
            X.setEndereco(rs.getString("endereco")); 
            X.setTelefone(rs.getString("telefone")); 
            
            
            minhaLista.add(X);
        }
        rs.close();
        stmt.close();
        return minhaLista;
    }
}
