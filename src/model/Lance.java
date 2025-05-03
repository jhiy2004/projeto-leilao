/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author jhiy2
 */
public class Lance {
    private final Comprador comprador;
    private final double valor;
    private final LocalDateTime data;
    
    public Lance(Comprador comprador, double valor){
        if (comprador == null) throw new IllegalArgumentException("Comprador não pode ser nulo.");

        this.valor = valor;
        this.comprador = comprador;
        this.data = LocalDateTime.now();
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
}
