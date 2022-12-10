/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit 
this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;


import br.com.projeto.model.Funcionarios;
import br.com.projeto.view.FrmLogin;
import br.com.projeto.view.FrmMenu;
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
public class FuncionariosDAO {
    
    // Conexao
      private Connection con;
    
    public FuncionariosDAO(){
        this.con=new ConnectionFactory().getConnection();
        
    }
    
    
    
    //Metodo Cadastrar Funcionarios
    
     public void cadastrarFuncionarios(Funcionarios obj){
        try {
            //1 - criar o comando sql
            String sql = "insert into tb_funcionarios(nome,rg,cpf,email,senha,"
                    + "cargo, nivel_acesso, telefone,celular,cep,endereco,"
                    + "  numero, complemento, bairro, cidade, estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            //2-conectar o banco de dados e organizar o comando sql
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getSenha());
            stmt.setString(6,obj.getCargo());
            stmt.setString(7,obj.getNivel_acesso());
            stmt.setString(8,obj.getTelefone());
            stmt.setString(9,obj.getCelular());
            stmt.setString(10,obj.getCep());
            stmt.setString(11,obj.getEndereco());
            stmt.setInt(12,obj.getNumero());
            stmt.setString(13,obj.getComplemento());
            stmt.setString(14,obj.getBairro());
            stmt.setString(15,obj.getCidade());
            stmt.setString(16,obj.getEstado());
            
            // executar o comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Cadastrado "
                    + "com Sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ops ocorreu um erro"
                    +e);
        }
        
    }
     //Metodo AlterarFuncionarios
    public void alterarFuncionarios( Funcionarios obj){
          try {
            //1 - criar o comando sql
            String sql = "update tb_funcionarios set nome = ?,rg = ?,cpf = ?, email = ?,"
                    + " senha = ?, cargo = ?, nivel_acesso = ?, telefone = ?,celular = ?,"
                    + "cep = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?,"
                    + " cidade = ?, estado = ? where id = ?";
                    
            
            //2-conectar o banco de dados e organizar o comando sql
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getSenha());
            stmt.setString(6,obj.getCargo());
            stmt.setString(7,obj.getNivel_acesso());
            stmt.setString(8,obj.getTelefone());
            stmt.setString(9,obj.getCelular());
            stmt.setString(10,obj.getCep());
            stmt.setString(11,obj.getEndereco());
            stmt.setInt(12,obj.getNumero());
            stmt.setString(13,obj.getComplemento());
            stmt.setString(14,obj.getBairro());
            stmt.setString(15,obj.getCidade());
            stmt.setString(16,obj.getEstado());
            stmt.setInt(17,obj.getId());
            
            // executar o comando
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null,"Alterado com Sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ops ocorreu um erro em alterar funcionario " +e);
        }
        
        
    }
    
    //Metodo ExcluirFuncionarios
    public void excluirFuncionarios(Funcionarios obj){
          try {
            //1 - criar o comando sql
            String sql = "delete from tb_funcionarios where id =?";
                    
            
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
     
     //Metodo ListarFuncionarios
    
    public List<Funcionarios> listarFuncionarios(){
        try {
            // 1 criar lista
            List<Funcionarios> lista = new ArrayList<>();
            
            // criar o sql
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionarios obj = new Funcionarios();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                
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
                    + "na listagem de Funcionarios" + e);
        }
        return null;
   
    }
    
    
    //Metodos Consulta e lista 
    //metodo consultaFuncionarios por CPF
    
    public Funcionarios consultaPorCpf(String cpf){
        try {
            String sql = "select * from tb_funcionarios where cpf = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,cpf);
            
            ResultSet rs = stmt.executeQuery();
             Funcionarios obj = new Funcionarios();
             if(rs.next()){
               
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    
    
    
    //Buscar Funcionarios por nome
    public List<Funcionarios> buscarFuncionariosPorNome(String nome){
        try {
            // 1 criar lista
            List<Funcionarios> lista = new ArrayList<>();
            
            // criar o sql
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionarios obj = new Funcionarios();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
                    + "na listagem de Funcionarios" + e);
        }
        return null;
    }
    
    //Metodo de Efetuar Login
    
    public void efetuarLogin(String email, String senha){
        
        try {
            // 1 passo -SQL
            String sql = "select * from tb_funcionarios where email = ? and senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                //Usuario logou
                JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                FrmMenu tela = new FrmMenu();
                tela.usuarioLogado = rs.getString("nome");
                tela.setVisible(true);
            }else{
                //Dados incorretos
                JOptionPane.showMessageDialog(null,"Dados incorretos!");
                 new FrmLogin().setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ops... Aconteceu um erro no login!! ->" +e);
           
        }
    }
  
    
}
