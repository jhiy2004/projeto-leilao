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
public class Lance {
    private final String id;
    private final Comprador comprador;
    private final double valor;
    private final LocalDateTime data;
    private final Anuncio anuncio;
    
    public Lance(String id, Comprador comprador, double valor, LocalDateTime data, Anuncio anuncio){
        if (comprador == null) throw new IllegalArgumentException("Comprador não pode ser nulo.");

        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.valor = valor;
        this.comprador = comprador;
        this.data = (data == null) ? LocalDateTime.now() : data;
        this.anuncio = anuncio;
    }
    
    public String getId(){
        return this.id;
    }
    
    public Comprador getComprador(){
        return this.comprador;
    }
    
    public double getValor(){
        return this.valor;
    }
    
    public LocalDateTime getData(){
        return this.data;
    }
    
    public Anuncio getAnuncio(){
        return this.anuncio;
    }
}
