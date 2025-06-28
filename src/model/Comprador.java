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
public class Comprador extends Usuario {
    private final List<Compra> compras;
    private final List<Cartao> cartoes;
    private final List<Pagamento> pagamentos;
    
    public Comprador(String id, String nome, String email, String senha, String cpf){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        
        this.compras = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
        this.cartoes = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
    }
    
    public List<Compra> getCompras(){
        return this.compras;
    }
    
     public List<Cartao> getCartoes() {
        return this.cartoes;
    }
     
    public List<Pagamento> getPagamentos(){
        return this.pagamentos;
    }
    
    public void adicionarCompra(Compra compra){
        if (!compras.contains(compra)) {
            this.compras.add(compra);
        }
    }
    
    public void adicionarCartao(Cartao cartao) {
        cartoes.add(cartao);
    }
    
    public void adicionarPagamento(Pagamento pagamento){
        pagamentos.add(pagamento);
    }
}
