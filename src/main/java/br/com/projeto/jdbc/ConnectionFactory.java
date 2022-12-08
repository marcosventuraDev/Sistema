/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

/**
 *
 * @author joaom
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
        
        try{
            
            return DriverManager.getConnection("jdbc:mysql://localhost/bdvendas","root","");
           
        
        } catch (Exception error){
            JOptionPane.showMessageDialog(null,"Ops deu erro na conexao: "+ error);
        }
        return null;
        
   
    }
    
}

           