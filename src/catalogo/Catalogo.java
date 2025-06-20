/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catalogo;

import controller.AnuncioController;
import controller.LanceController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Anuncio;
import model.Comprador;
import model.Lance;
import model.Usuario;
import model.Vendedor;
import persistence.AnuncioDAO;
import persistence.LanceDAO;
import persistence.UsuarioDAO;

/**
 *
 * @author joseyamaoki
 */
public class Catalogo {
    private Map<String, Usuario> usuarios;
    private Map<String, Vendedor> vendedores;
    private Map<String, Comprador> compradores;

    private Map<String, Anuncio> anuncios;
    private Map<String, Lance> lances;
    
    private AnuncioDAO anuncioDAO;
    private LanceDAO lanceDAO;
    private UsuarioDAO usuarioDAO;
    
    private static Catalogo instance = null;

    private Catalogo(){
        usuarioDAO = new UsuarioDAO();
        
        usuarios = new HashMap<>();
        vendedores = new HashMap<>();
        compradores = new HashMap<>();
        anuncios = new HashMap<>();
        lances = new HashMap<>();
        
        try {
            usuarios = usuarioDAO.carregar();
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
        }
        
        for(Usuario u : usuarios.values()){
            if(u instanceof Vendedor){
                vendedores.put(u.getId(), (Vendedor)u);
            }else if(u instanceof Comprador){
                compradores.put(u.getId(), (Comprador)u);
            }
        }
        
        anuncioDAO = new AnuncioDAO(vendedores);
        try {
            anuncios = anuncioDAO.carregar();
        } catch (IOException e) {
            System.err.println("Erro ao carregar anúncios: " + e.getMessage());
        }
        
        lanceDAO = new LanceDAO(compradores, anuncios);
        try {
            lances = lanceDAO.carregar();
        } catch (IOException e) {
            System.err.println("Erro ao carregar lances: " + e.getMessage());
        }
        
        System.out.println("QTD COMPRADORES: " + compradores.size());
        System.out.println("QTD VENDEDORES: " + vendedores.size());
        System.out.println("QTD LANCES: "+lances.size());
        System.out.println("QTD ANUNCIOS: "+anuncios.size());   
    }
    
    public static Catalogo getInstance(){
        if(instance == null){
            instance = new Catalogo();
        }
        return instance;
    }
    
    public Map<String, Usuario> getUsuarios(){
        return this.usuarios;
    }
    
    public Map<String, Vendedor> getVendedores(){
        return this.vendedores;
    }
    
    public Map<String, Comprador> getCompradores(){
        return this.compradores;
    }
    
    public Map<String, Anuncio> getAnuncios(){
        return this.anuncios;
    }
    
    public boolean inserirUsuario(Usuario u){
        usuarios.put(u.getId(), u);
        if(u instanceof Vendedor){
            vendedores.put(u.getId(), (Vendedor)u);
        }else if(u instanceof Comprador){
            compradores.put(u.getId(), (Comprador)u);
        }
        
        try {
            usuarioDAO.salvar(u);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean inserirLance(Lance l){
        lances.put(l.getId(), l);

        try {
            this.lanceDAO.salvar(l);
        } catch (IOException ex) {
            Logger.getLogger(LanceController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean inserirAnuncio(Anuncio a){
        anuncios.put(a.getId(), a);
        try {
            anuncioDAO.salvar(a);
        } catch (IOException ex) {
            Logger.getLogger(AnuncioController.class.getName()).log(Level.SEVERE, "Erro ao salvar anúncio", ex);
            return false;
        }   
        return true;
    }

}
