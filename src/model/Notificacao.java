/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.UUID;

/**
 *
 * @author joseyamaoki
 */
public class Notificacao {
    private final String id;
    private final String mensagem;
    private boolean visualizada;
    private final Usuario usuario;
    
    public Notificacao(String id, String mensagem, Usuario usuario){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.mensagem = mensagem;
        this.visualizada = false;
        this.usuario = usuario;
    }
    
    public void visualizar(){
        this.visualizada = true;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getMensagem(){
        return this.mensagem;
    }
    
    public boolean isVisualizada(){
        return this.visualizada;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
}
