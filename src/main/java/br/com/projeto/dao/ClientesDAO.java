/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author joaom
 */
public class ClientesDAO {
    
    private Connection con;
    
    public ClientesDAO(){
        this.con=new ConnectionFactory().getConnection();
        
    }
    
    
    //Metodo cadastrarCliente
    
    public void cadastrarCliente(Clientes obj){
        try {
            //1 - criar o comando sql
            String sql = "insert into tb_clientes(nome,rg,cpf,email,telefone,celular,cep,"
                    + " endereco, numero, complemento, bairro, cidade, estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            //2-conectar o banco de dados e organizar o comando sql
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getTelefone());
            stmt.setString(6,obj.getCelular());
            stmt.setString(7,obj.getCep());
            stmt.setString(8,obj.getEndereco());
            stmt.setInt(9,obj.getNumero());
            stmt.setString(10,obj.getComplemento());
            stmt.setString(11,obj.getBairro());
            stmt.setString(12,obj.getCidade());
            stmt.setString(13,obj.getEstado());
            
            // executar o comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ops ocorreu um erro " +e);
        }
        
    }
    
    //Metodo AlterarClientes
    public void alterarCliente( Clientes obj){
          try {
            //1 - criar o comando sql
            String sql = "update tb_clientes set nome = ?,rg = ?,cpf = ?,email = ?,"
                    + "telefone = ?,celular = ?,cep = ?, endereco = ?, numero = ?, "
                    + "complemento = ?, bairro = ?, cidade = ?, estado = ? where id = ?";
                    
            
            //2-conectar o banco de dados e organizar o comando sql
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getTelefone());
            stmt.setString(6,obj.getCelular());
            stmt.setString(7,obj.getCep());
            stmt.setString(8,obj.getEndereco());
            stmt.setInt(9,obj.getNumero());
            stmt.setString(10,obj.getComplemento());
            stmt.setString(11,obj.getBairro());
            stmt.setString(12,obj.getCidade());
            stmt.setString(13,obj.getEstado());
            stmt.setInt(14,obj.getId());
            
            // executar o comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Alterado com Sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ops ocorreu um erro em alterar cliente " +e);
        }
        
        
    }
    
    //Metodo ExcluirCliente
    public void excluirCliente(Clientes obj){
          try {
            //1 - criar o comando sql
            String sql = "delete from tb_clientes where id =?";
                    
            
            //2-conectar o banco de dados e organizar o comando sql
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,obj.getId());
            
            // executar o comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Excluido com Sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ops ocorreu um erro na exclusão!: -> " +e);
        }
        
    }
    
     //Metodo ListarCliente
    
    public List<Clientes> listarClientes(){
        try {
            // 1 criar lista
            List<Clientes> lista = new ArrayList<>();
            
            // criar o sql
            String sql = "select * from tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes obj = new Clientes();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("Numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Ops... Deu erro "
                    + "na listagem de Clientes" + e);
        }
        return null;
   
    }
    
    
    //metodo consultaCliente por CPF
    
    public Clientes consultaPorCpf(String cpf){
        try {
            String sql = "select * from tb_clientes where cpf = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,cpf);
            
            ResultSet rs = stmt.executeQuery();
             Clientes obj = new Clientes();
             if(rs.next()){
               
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("Numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                
                
            }
            
             return obj;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!" );
        }
        return null;
    }
    
    
    
    //Buscar cliente por nome
    public List<Clientes> buscarClientePorNome(String nome){
        try {
            // 1 criar lista
            List<Clientes> lista = new ArrayList<>();
            
            // criar o sql
            String sql = "select * from tb_clientes where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes obj = new Clientes();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("Numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Ops... Deu erro "
                    + "na listagem de Clientes" + e);
        }
        return null;
    }
  

    
    
}
