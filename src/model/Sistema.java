/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jhiy2
 */
public class Sistema {
    private static Sistema instance = null;
    private static boolean logado = false;
    private static Usuario userLogged = null;
    
    private Sistema(){
    }
    
    public static Sistema getInstance(){
        if(instance == null){
            instance = new Sistema();
        }
        
        return instance;
    }
    
    public void logar(Usuario user){
        logado = true;
        userLogged = user;
    }
    
    public Usuario getUsuarioLogado(){
        return userLogged;
    }
    
    public boolean isLogged(){
        return logado;
    }
}
