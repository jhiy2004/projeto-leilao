/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import model.Anuncio;
import model.Comprador;
import model.Lance;
import persistence.LanceDAO;

/**
 *
 * @author jhiy2
 */
public class LanceController {
    private Map<String, Lance> lances;
    private LanceDAO lanceDAO;
    
    public LanceController(LanceDAO lanceDAO, Map<String, Comprador> compradores, Map<String, Anuncio> anuncios) {
        this.lanceDAO = lanceDAO;
        this.lances = new HashMap<>();

        try {
            this.lances = this.lanceDAO.carregar();
        } catch (IOException ex) {
            Logger.getLogger(LanceController.class.getName()).log(Level.SEVERE, "Erro ao carregar lances", ex);
            this.lances = new HashMap<>();
        }
    }
    
    public boolean addLanceAnuncio(Anuncio anuncio, Comprador comprador, double valor) {
        if (anuncio == null || comprador == null) return false;
        if (anuncio.isEncerrado()) return false;

        Lance lance = new Lance(null, comprador, valor, null, anuncio);
        Lance lanceAtual = anuncio.getLanceAtual();

        if (lanceAtual != null && valor <= lanceAtual.getValor()) return false;

        anuncio.atualizarLance(lance);
        lances.put(lance.getId(), lance);

        try {
            this.lanceDAO.salvar(lance);
        } catch (IOException ex) {
            Logger.getLogger(LanceController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public List<Lance> listarLancesAnuncio(Anuncio anuncio) {
        if (anuncio == null) return null;
        return anuncio.getLances();
    }
}
