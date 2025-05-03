/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Anuncio;
import model.Comprador;
import model.Lance;
import model.Vendedor;

/**
 *
 * @author jhiy2
 */
public class AnuncioController {
    private final List<Anuncio> anuncios;
    private final LanceController lanceController;
    
    public AnuncioController(LanceController lanceController){
        this.anuncios = new ArrayList<>();
        this.lanceController = lanceController;
    }
    
    public boolean addLanceAnuncio(Anuncio anuncio, Comprador comprador, double valor){
        return lanceController.addLanceAnuncio(anuncio, comprador, valor);
    }
    
    public List<Lance> listarLancesAnuncio(Anuncio anuncio){
        return lanceController.listarLancesAnuncio(anuncio);
    }
    
    public Anuncio criarAnuncio(String nome, String descricao, Vendedor vendedor, LocalDateTime dataEncerramento, double valorInicial){        
        Anuncio anuncio = new Anuncio(nome, descricao, vendedor, dataEncerramento, valorInicial);
        anuncios.add(anuncio);
        return anuncio;
    }
    
    public List<Anuncio> getTodosAnuncios(){
        for(Anuncio a : this.anuncios){
            a.verificarEncerramento();
        }
        
        return this.anuncios;
    }
}
