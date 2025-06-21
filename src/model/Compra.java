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
public class Compra {
    private final String id;
    private final LocalDateTime data;
    private final double valor;
    private boolean concluida;
    private final Vendedor vendedor;
    private final Comprador comprador;
    private final Anuncio anuncio;
    private Avaliacao avaliacao;
    
    public Compra(String id, LocalDateTime data, double valor, boolean concluida, Vendedor vendedor, Comprador comprador, Anuncio anuncio){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.data = data;
        this.valor = valor;
        this.concluida = concluida;
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.anuncio = anuncio;
        this.avaliacao = null;
    }
    
    public Compra(String id, LocalDateTime data, double valor, Vendedor vendedor, Comprador comprador, Anuncio anuncio){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.data = data;
        this.valor = valor;
        this.concluida = false;
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.anuncio = anuncio;
        this.avaliacao = null;
    }
    
    public String getId(){
        return this.id;
    }
    
    public LocalDateTime getData(){
        return this.data;
    }
    
    public double getValor(){
        return this.valor;
    }
    
    public boolean isConcluida(){
        return this.concluida;
    }
    
    public Vendedor getVendedor(){
        return this.vendedor;
    }
    
    public Comprador getComprador(){
        return this.comprador;
    }
    
    public Anuncio getAnuncio(){
        return this.anuncio;
    }
    
    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    public void concluir(){
        this.concluida = true;
    }
}
