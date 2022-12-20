/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Vendas;
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
public class VendasDAO {
    
    private Connection con;
    
    public VendasDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    
    //Cadastrar Vendas
    
    public void cadastrarVenda(Vendas obj){
        
        try {
            String sql = "insert into tb_vendas(cliente_id, data_venda, "
                    + "total_venda, observacoes) values (?, ?, ?, ?)";
             PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,obj.getCliente_id().getId());
            stmt.setString(2,obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4,obj.getObservacoes());
            
            stmt.execute();
            stmt.close();
          
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ops...! Ocorreu um erro no registro da Venda"+e);
        }
        
    }
    
    
    
    //Retorna a Ultima venda
    public int retornaUltimaVenda(){
        try {
            int idvenda = 0;
            
            String sql = "select max(id) id from tb_vendas";
               PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                Vendas p = new Vendas();
                p.setId(rs.getInt("id"));
                idvenda = p.getId();
                
            }
        
          return idvenda;
        } catch (SQLException e) {
            
            throw new RuntimeException("Opss... Aconteceu um erro  no retorno de vendas"+e);
        }
        
}
    
    //Metodo que filtra vendas por data
    
    public List<Vendas> listarVendasPorPeriodo(String data_inicio, String data_fim){
        try {
            
            List<Vendas> lista = new ArrayList<>();
            
            String sql = "SELECT v.id, v.data_venda, c.nome, v.total_venda, "
                    + " v.observacoes FROM tb_vendas as v INNER JOIN tb_clientes as c"
                    + " on (v.cliente_id = c.id) WHERE  v.datavenda BETWEEN ?  "
                    + "ADN ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio);
            stmt.setString(2, data_fim);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Vendas obj = new Vendas();
                Clientes c = new Clientes();
                
                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("v.data_venda"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObservacoes(rs.getString("v.observacoes"));
                
                
                obj.setCliente(c);
                
                lista.add(obj);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro na busca por data"+ e);
        }
        
    }
    
    
}
