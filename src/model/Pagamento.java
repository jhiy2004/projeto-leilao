/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author jhiy2
 */
public class Pagamento {
    private final String id;
    private final Vendedor vendedor;
    private final Compra compra;
    private final Cartao cartao;
    private final LocalDateTime data;
    
    public Pagamento(String id, Vendedor vendedor, Compra compra, Cartao cartao, LocalDateTime data){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.vendedor = vendedor;
        this.compra = compra;
        this.cartao = cartao;
        this.data = data;
    }
    
    public Pagamento(String id, Vendedor vendedor, Compra compra, Cartao cartao){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.vendedor = vendedor;
        this.compra = compra;
        this.cartao = cartao;
        this.data = LocalDateTime.now();
    }
    
    public String getId(){
        return this.id;
    }
    
    public double getValorCompra(){
        return this.compra.getValor();
    }
    
    public Comprador getComprador(){
        return this.cartao.getComprador();
    }
    
    public Vendedor getVendedor(){
        return this.vendedor;
    }
    
    public Compra getCompra(){
        return this.compra;
    }
    
    public Cartao getCartao(){
        return this.cartao;
    }
    
    public LocalDateTime getData(){
        return this.data;
    }
}
