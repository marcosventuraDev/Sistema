/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;
import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
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
public class FornecedoresDAO {
    
     private Connection con;
    
    public FornecedoresDAO(){
        this.con=new ConnectionFactory().getConnection();
        
    }
    
    //Metodo cadastrarFornecedores
    
    
    public void cadastrarFornecedores(Fornecedores obj){
        try {
            //1 - criar o comando sql
            String sql = "insert into tb_fornecedores(nome,cnpj,cpf,email,telefone,celular,cep,"
                    + " endereco, numero, complemento, bairro, cidade, estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            //2-conectar o banco de dados e organizar o comando sql
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getCnpj());
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
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Ops ocorreu um erro no cadastro " +e);
        }
        
    }

    
    ///Cuidado para não misturar
    
    //Metodo AlterarFornecedores
    public void alterarFornecedores( Fornecedores obj){
          try {
            //1 - criar o comando sql
            String sql = "update tb_fornecedores set nome = ? , cnpj= ?, cpf = ?, email = ?,"
                    + "telefone = ?,celular = ?,cep = ?, endereco = ?, numero = ?, "
                    + "complemento = ?, bairro = ?, cidade = ?, estado = ? where id = ?";
                    
            
            //2-conectar o banco de dados e organizar o comando sql
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getCnpj());
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
            JOptionPane.showMessageDialog(null,"Ops ocorreu um erro em alterar Fornecedores " +e);
        }
        
        
    }
    
    //Metodo ExcluirFornecedores
    public void excluirFornecedores(Fornecedores obj){
          try {
            //1 - criar o comando sql
            String sql = "delete from tb_fornecedores where id =?";
                    
            
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
    
     //Metodo ListarFornecedores
    
    public List<Fornecedores> listarFornecedores(){
        try {
            // 1 criar lista
            List<Fornecedores> lista = new ArrayList<>();
            
            // criar o sql
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedores obj = new Fornecedores();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
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
                    + "na listagem de Fornecedores" + e);
        }
        return null;
   
    }
    
    
    //metodo consultaFornecedores por CPF
    
    public Fornecedores consultaPorCpf(String cpf){
        try {
            String sql = "select * from tb_fornecedores where cpf = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,cpf);
            
            ResultSet rs = stmt.executeQuery();
             Fornecedores obj = new Fornecedores();
             if(rs.next()){
               
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
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
            JOptionPane.showMessageDialog(null, "Fornecedores não encontrado!" );
        }
        return null;
    }
    
    
    
    //Buscar Fornecedores por nome
    public List<Fornecedores> buscarFornecedoresPorNome(String nome){
        try {
            // 1 criar lista
            List<Fornecedores> lista = new ArrayList<>();
            
            // criar o sql
            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedores obj = new Fornecedores();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
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
                    + "na listagem de Fornecedores" + e);
        }
        return null;
    }
  

    
    
}
