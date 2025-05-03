/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Anuncio;
import model.Comprador;
import model.Lance;

/**
 *
 * @author jhiy2
 */
public class LanceController {
    public Lance criarLance(Comprador comprador, double valor){
        return new Lance(comprador, valor);
    }
    
    public boolean addLanceAnuncio(Anuncio anuncio, Comprador comprador, double valor){
        if(anuncio == null || comprador == null) return false;
        if(anuncio.isEncerrado()) return false;
        
        Lance lance = new Lance(comprador, valor);
        Lance lanceAtual = anuncio.getLanceAtual();   
        
        if(valor <= lanceAtual.getValor()) return false;
        
        anuncio.atualizarLance(lance);
        return true;
    }
    
    public List<Lance> listarLancesAnuncio(Anuncio anuncio){
        if(anuncio == null) return null;
        
        return anuncio.getLances();
    }
}
