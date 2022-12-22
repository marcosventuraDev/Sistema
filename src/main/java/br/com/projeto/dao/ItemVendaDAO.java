/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.ItemVendas;
import br.com.projeto.model.Produtos;
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
public class ItemVendaDAO {
     //Conectar com o banco de dados
    private Connection con;
    
    public ItemVendaDAO(){
        
        this.con = new ConnectionFactory().getConnection();
    }
    
    
    //Metodo que cadastraItens
    public void cadastraItem(ItemVendas obj){
        try {
           
            String sql = "insert into tb_itensvendas(venda_id, produto_id,"
                    + " qtd, subtotal)values(?, ?, ?, ?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1,obj.getVendas().getId());
            stmt.setInt(2,obj.getProdutos().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4,obj.getSubtotal());
            
            stmt.execute();
            stmt.close();
               
           
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"Erro no "
                    + "cadastrar ItensVendas"+ e);
        }
    }
    
    //Metodo de listar itens de venda por ID
    
    public List<ItemVendas> listaItensVenda(int venda_id){
        
        List<ItemVendas>lista = new ArrayList<>();
        
        try {
            String query = "SELECT i.id, p.descricao, i.qtd, p.preco, "
                    + "i.subtotal from tb_itensvendas AS i INNER "
                    + "JOIN tb_produtos AS p ON (i.produto_id = p.id) WHERE "
                    + "i.venda_id = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,venda_id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ItemVendas item = new ItemVendas();
                Produtos p = new Produtos();
                
                item.setId(rs.getInt("i.id"));
                p.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtd"));
                p.setPreco(rs.getInt("p.preco"));
                item.setSubtotal(rs.getInt("i.subtotal"));
                
                item.setProdutos(p);
                
                
                 lista.add(item);
            }
            
           return lista;
            
        } catch (Exception e) {
         
            JOptionPane.showMessageDialog(null, "Erro na listagem dos Itens da venda" + e);
        }
        return null;
        
        
    }
    
}
