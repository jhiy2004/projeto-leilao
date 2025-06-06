/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import catalogo.Catalogo;
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
    public static boolean addLanceAnuncio(Anuncio anuncio, Comprador comprador, double valor) {
        if (anuncio == null || comprador == null) return false;
        if (anuncio.isEncerrado()) return false;

        Lance lance = new Lance(null, comprador, valor, null, anuncio);
        Lance lanceAtual = anuncio.getLanceAtual();

        if (lanceAtual != null && valor <= lanceAtual.getValor()) return false;

        anuncio.atualizarLance(lance);
        
        Catalogo catalogo = Catalogo.getInstance();

        return catalogo.inserirLance(lance);
    }

    public static List<Lance> listarLancesAnuncio(Anuncio anuncio) {
        if (anuncio == null) return null;
        return anuncio.getLances();
    }
}
