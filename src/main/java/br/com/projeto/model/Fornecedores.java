/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.model;

/**
 *
 * @author joaom
 */
public class Fornecedores extends Clientes {
    
    
    //Atributos
    private String cnpj;
    
    
    //getter e setter

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

      @Override
    public String toString(){
        return this.getNome();
    } 
}
