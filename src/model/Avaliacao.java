/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author joseyamaoki
 */
public class Avaliacao {
    private final String id;
    private final int nota;
    private final LocalDateTime data;
    private final Compra compra;
    
    public Avaliacao(String id, int nota, Compra compra){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.nota = nota;
        this.data = LocalDateTime.now();
        this.compra = compra;
    }
    
    public Avaliacao(String id, int nota, LocalDateTime data, Compra compra){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.nota = nota;
        this.data = data;
        this.compra = compra;
    }
    
    public String getId(){
        return this.id;
    }
    
    public int getNota(){
        return this.nota;
    }
    
    public LocalDateTime getData(){
        return this.data;
    }
    
    public Compra getCompra(){
        return this.compra;
    }
}
