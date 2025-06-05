/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Map;
import model.Comprador;

/**
 *
 * @author jhiy2
 */
public class CompradorController {
    UsuarioController uc;
    Map<String, Comprador> compradores;
    
    public CompradorController(UsuarioController uc){
        this.uc = uc;
        this.compradores = uc.getCompradores();
    }
    
    public Map<String, Comprador> getCompradores(){
        return this.compradores;
    }
}
