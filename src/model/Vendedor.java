/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author jhiy2
 */
public class Vendedor extends Usuario {
    private final List<Compra> vendas;
    private final List<Pagamento> pagamentos;

    
    public Vendedor(String id, String nome, String email, String senha, String cpf){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        
        this.vendas = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
    }
    
    public List<Compra> getVendas(){
        return this.vendas;
    }
    
    public List<Pagamento> getPagamentos(){
         return this.pagamentos;
     }
    
    public void adicionarVenda(Compra venda){
        if (!vendas.contains(venda)) {
            this.vendas.add(venda);
        }
    }
 
    public void adicionarPagamento(Pagamento pagamento){
        pagamentos.add(pagamento);
    }
}
