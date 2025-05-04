/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import model.Anuncio;
import model.Comprador;
import model.Lance;
import model.Vendedor;
import persistence.AnuncioDAO;
import persistence.LanceDAO;

/**
 *
 * @author jhiy2
 */
public class AnuncioController {
    private Map<String, Anuncio> anuncios;
    private final LanceController lanceController;
    private final AnuncioDAO anuncioDAO;
    
    public AnuncioController(AnuncioDAO anuncioDAO, LanceDAO lanceDAO, Map<String, Vendedor> vendedores, Map<String, Comprador> compradores) {
        this.anuncioDAO = anuncioDAO;
        try {
            this.anuncios = anuncioDAO.carregar();
        } catch (IOException ex) {
            Logger.getLogger(AnuncioController.class.getName()).log(Level.SEVERE, "Erro ao carregar anúncios", ex);
            this.anuncios = new HashMap<>();
        }
        
        this.lanceController = new LanceController(lanceDAO, compradores, anuncios);
    }
    
    public boolean addLanceAnuncio(Anuncio anuncio, Comprador comprador, double valor) {
        return lanceController.addLanceAnuncio(anuncio, comprador, valor);
    }
    
    public List<Lance> listarLancesAnuncio(Anuncio anuncio) {
        return lanceController.listarLancesAnuncio(anuncio);
    }
    
    public Anuncio criarAnuncio(String nome, String descricao, Vendedor vendedor, LocalDateTime dataEncerramento, double valorInicial) {
        Anuncio anuncio = new Anuncio(null, nome, descricao, vendedor, null, dataEncerramento, valorInicial);
        anuncios.put(anuncio.getId(), anuncio);
        try {
            anuncioDAO.salvar(anuncio);
        } catch (IOException ex) {
            Logger.getLogger(AnuncioController.class.getName()).log(Level.SEVERE, "Erro ao salvar anúncio", ex);
        }
        return anuncio;
    }
    
    public List<Anuncio> getTodosAnuncios() {
        for (Anuncio a : this.anuncios.values()) {
            a.verificarEncerramento();
        }
        return new ArrayList<>(this.anuncios.values());
    }

    public Anuncio getAnuncioPorId(String id) {
        return anuncios.get(id);
    }
}
