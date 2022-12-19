/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.ItemVendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
               JOptionPane.showMessageDialog(null,"sucesso No registro de produtos na tb_itensVendas");
           
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"Erro no "
                    + "cadastrar ItensVendas"+ e);
        }
    }
    
}
