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
import catalogo.Catalogo;
import java.util.Map;

/**
 *
 * @author jhiy2
 */
public class AnuncioController {
    public static boolean addLanceAnuncio(Anuncio anuncio, Comprador comprador, double valor) {
        return LanceController.addLanceAnuncio(anuncio, comprador, valor);
    }
    
    public static List<Lance> listarLancesAnuncio(Anuncio anuncio) {
        return LanceController.listarLancesAnuncio(anuncio);
    }
    
    public static Anuncio criarAnuncio(String nome, String descricao, Vendedor vendedor, LocalDateTime dataEncerramento, double valorInicial) {
        Anuncio anuncio = new Anuncio(null, nome, descricao, vendedor, null, dataEncerramento, valorInicial);
        
        Catalogo catalogo = Catalogo.getInstance();
        if(catalogo.inserirAnuncio(anuncio) == false)
            return null;
        
        return anuncio;
    }
    
    public static List<Anuncio> getTodosAnuncios() {
        Catalogo catalogo = Catalogo.getInstance();
        Map<String, Anuncio> anuncios = catalogo.getAnuncios();
        
        for (Anuncio a : anuncios.values()) {
            a.verificarEncerramento();
        }
        return new ArrayList<>(anuncios.values());
    }

    public Anuncio getAnuncioPorId(String id) {
        Catalogo catalogo = Catalogo.getInstance();
        Map<String, Anuncio> anuncios = catalogo.getAnuncios();
        
        return anuncios.get(id);
    }
}
