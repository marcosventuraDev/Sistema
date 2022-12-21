/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.model;

/**
 *
 * @author joaom
 */
public class Vendas {
    //Atributos
    
    private int id;
    private Clientes cliente_id;
    private String data_venda;
    private double total_venda;
    private String observacoes;
    
    //gatters e setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clientes getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Clientes cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public double getTotal_venda() {
        return total_venda;
    }

    public void setTotal_venda(double total_venda) {
        this.total_venda = total_venda;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    
   

    
}
