/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
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
public class ProdutosDAO {
    //Conexao com o banco de Dados
    
        private Connection con;
    
    public ProdutosDAO(){
        this.con=new ConnectionFactory().getConnection();
        
    }
    
    //Metodo Cadastrar produto
    
    public void cadastrar(Produtos obj){
        try {
            String sql = "insert into tb_produtos(descricao, custo, preco, qtd_estoque, for_id)values(?, ?, ?, ?, ?)";
            
    //2 - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getCusto());
            stmt.setDouble(3,obj.getPreco());
            stmt.setInt(4, obj.getQtd_estoque());
            stmt.setInt(5, obj.getFornecedor().getId());
            
            stmt.execute();
            stmt.close();
               
            JOptionPane.showMessageDialog(null, "Procuto cadastrado com sucesso");
            
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Erro no cadastro do Produto:" +e);
        }
    }
    //Metodo Alterar Produtos
    public void alterar(Produtos obj){
        try {
            String sql = "update tb_produtos set descricao = ?, custo = ?, preco = ?"
                    + ", qtd_estoque = ?, for_id = ? where id = ?";
            //conectar o banco de dados e organizar o comando
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,obj.getDescricao());
            stmt.setDouble(2, obj.getCusto());
            stmt.setDouble(3, obj.getPreco());
            stmt.setInt(4, obj.getQtd_estoque());
            
            stmt.setInt(5, obj.getFornecedor().getId());
            
            stmt.setInt(6, obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Produto alterado com Sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Eita... Ocorreu um erro em Alterar o Produto"+ e);
                    
        }
         
    }
    
    public void excluir(Produtos obj){
        
        try {
            
            String sql = "delete from tb_produtos where id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1,obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Erro ao excluir cliente"+e);
        }
    }
    
   //Metodo lista Pdrodutos
    
    public List<Produtos> listarProdutos(){
        
        try {
            
            //Criar a lista
            
            List<Produtos> lista = new ArrayList();
            
            //Criar o sql, organnizar e executar
            
            String sql = "select p.id, p.descricao, p.custo, p.preco, p.qtd_estoque,"
                    + " f.nome from tb_produtos as p inner join "
                    + "tb_fornecedores as f "
                    + "on (p.for_id = f.id); ";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setCusto(rs.getDouble("p.custo"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                
                lista.add(obj);
                
            }
            
            return lista;
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Ops... Erro em "
                    + "listar Produtos" + e);
        }
            
         return null;
    }
    
    
    //buscar produto por nome
    
      public List<Produtos> listarProdutosPorNome(String nome){
        
        try {
            
            //Criar a lista
            
            List<Produtos> lista = new ArrayList();
            
            //Criar o sql, organnizar e executar
            
            String sql = "select p.id, p.descricao, p.custo, p.preco, p.qtd_estoque,"
                    + " f.nome from tb_produtos as p inner join "
                    + "tb_fornecedores as f "
                    + "on (p.for_id = f.id) where p.descricao like ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setCusto(rs.getDouble("p.custo"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                
                lista.add(obj);
                
            }
            
            return lista;
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Ops... Erro em "
                    + "listar Produtos" + e);
        }
            
         return null;
    }
      
      
      
      
       //buscar produto por nome
    
      public Produtos consultaPorNome(String nome){
        
        try {
            
             
            //Criar o sql, organnizar e executar
            
            String sql = "select p.id, p.descricao, p.custo, p.preco, p.qtd_estoque,"
                    + " f.nome from tb_produtos as p inner join "
                    + "tb_fornecedores as f "
                    + "on (p.for_id = f.id) where p.descricao = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();
            
            while(rs.next()){
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setCusto(rs.getDouble("p.custo"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                
                               
            }
            
            return obj;
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Ops... Erro em "
                    + "buscar Produtos" + e);
        }
            
         return null;
    }



//buscar produto por Codigo de barras
      
    
      public Produtos buscaPorCodigo(int id){
        
        try {
            
             
            //Criar o sql, organnizar e executar
            
            String sql = "select * from tb_produtos where id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
          
            
            while(rs.next()){
                
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setCusto(rs.getDouble("custo"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));
                
                          
                
                               
            }
            
            return obj;
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Ops... Erro em "
                    + "buscar Produtos" + e);
        }
            
         return null;
    }
      
      //Metodo para dar Baixa no estoque
      
      public void baixaEstoque(int id, int qtd_nova){
          try {
              String sql = "UPDATE tb_produtos SET qtd_estoque = ? WHERE id = ?";
              //-conectando com o banco de dados
              PreparedStatement stmt = con.prepareStatement(sql);
              
              stmt.setInt(1, qtd_nova);
              stmt.setInt(2, id);
              stmt.execute();
              stmt.close();
              
          } catch (Exception e) {
              
              JOptionPane.showMessageDialog(null, "Erro na alteração"
                      + "na quantidade de Estoque "+e);
          }
      }
      
      
}

