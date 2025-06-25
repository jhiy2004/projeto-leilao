/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catalogo;

import controller.AnuncioController;
import controller.LanceController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Anuncio;
import model.Avaliacao;
import model.Cartao;
import model.Compra;
import model.Comprador;
import model.Lance;
import model.Notificacao;
import model.Pagamento;
import model.Usuario;
import model.Vendedor;
import persistence.AnuncioDAO;
import persistence.AvaliacaoDAO;
import persistence.CartaoDAO;
import persistence.CompraDAO;
import persistence.LanceDAO;
import persistence.NotificacaoDAO;
import persistence.PagamentoDAO;
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
    
    private Map<String, Compra> compras;
    private Map<String, Notificacao> notificacoes;
    private Map<String, Avaliacao> avaliacoes;
    private Map<String, Cartao> cartoes;
    private Map<String, Pagamento> pagamentos;
    
    private AnuncioDAO anuncioDAO;
    private LanceDAO lanceDAO;
    private UsuarioDAO usuarioDAO;
    private CompraDAO compraDAO;
    private NotificacaoDAO notificacaoDAO;
    private AvaliacaoDAO avaliacaoDAO;
    private CartaoDAO cartaoDAO;
    private PagamentoDAO pagamentoDAO;
    
    private static Catalogo instance = null;

    private Catalogo(){
        usuarioDAO = new UsuarioDAO();
        
        usuarios = new HashMap<>();
        vendedores = new HashMap<>();
        compradores = new HashMap<>();
        anuncios = new HashMap<>();
        lances = new HashMap<>();
        compras = new HashMap<>();
        pagamentos = new HashMap<>();
        notificacoes = new HashMap<>();
        avaliacoes = new HashMap<>();
        cartoes = new HashMap<>();
        
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
        
        compraDAO = new CompraDAO(compradores, vendedores, anuncios);
        try{
            compras = compraDAO.carregar();
        } catch (IOException e) {
            System.err.println("Erro ao carregar compras: " + e.getMessage());
        }
        
        notificacaoDAO = new NotificacaoDAO(usuarios);
        try{
            notificacoes = notificacaoDAO.carregar();
        } catch(IOException e){
            System.err.println("Erro ao carregar notificações: " + e.getMessage());
        }
        
        avaliacaoDAO = new AvaliacaoDAO(compras);
        try{
            avaliacoes = avaliacaoDAO.carregar();
        } catch (IOException e) {
            System.err.println("Erro ao carregar avaliações: " + e.getMessage());
        }
        
        cartaoDAO = new CartaoDAO(compradores);
        try{
            cartoes = cartaoDAO.carregar();
        } catch (IOException e) {
            System.err.println("Erro ao carregar cartões: " + e.getMessage());
        }
        
        pagamentoDAO = new PagamentoDAO(vendedores, compras, cartoes);
        try{
            pagamentos = pagamentoDAO.carregar();
        } catch(IOException e){
            System.err.println("Erro ao carregar pagamentos: " + e.getMessage());
        }
        
        System.out.println("QTD COMPRADORES: " + compradores.size());
        System.out.println("QTD VENDEDORES: " + vendedores.size());
        System.out.println("QTD LANCES: "+lances.size());
        System.out.println("QTD ANUNCIOS: "+anuncios.size());
        System.out.println("QTD COMPRAS: "+compras.size());
        System.out.println("QTD NOTIFICACOES: "+notificacoes.size());
        System.out.println("QTD AVALIACOES: "+avaliacoes.size());
        System.out.println("QTD CARTOES: "+cartoes.size());
        System.out.println("QTD PAGAMENTOS: "+pagamentos.size());
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
    
    public Map<String, Compra> getCompras(){
        return this.compras;
    }
    
    public Map<String, Notificacao> getNotificacoes(){
        return this.notificacoes;
    }
    
    public Map<String, Avaliacao> getAvaliacoes(){
        return this.avaliacoes;
    }
    
    public Map<String, Cartao> getCartoes(){
        return this.cartoes;
    }
    
    public Map<String, Pagamento> getPagamentos(){
        return this.pagamentos;
    }
    
    public Usuario getUsuarioId(String usuarioId){
        return this.usuarios.get(usuarioId);
    }
    
    public Vendedor getVendedorId(String vendedorId) {
        return this.vendedores.get(vendedorId);
    }

    public Comprador getCompradorId(String compradorId) {
        return this.compradores.get(compradorId);
    }

    public Anuncio getAnuncioId(String anuncioId) {
        return this.anuncios.get(anuncioId);
    }

    public Lance getLanceId(String lanceId) {
        return this.lances.get(lanceId);
    }

    public Compra getCompraId(String compraId) {
        return this.compras.get(compraId);
    }

    public Notificacao getNotificacaoId(String notificacaoId) {
        return this.notificacoes.get(notificacaoId);
    }

    public Avaliacao getAvaliacaoId(String avaliacaoId) {
        return this.avaliacoes.get(avaliacaoId);
    }

    public Cartao getCartaoId(String cartaoId) {
        return this.cartoes.get(cartaoId);
    }
    
    public Pagamento getPagamentoId(String pagamentoId){
        return this.pagamentos.get(pagamentoId);
    }
    
    public List<Anuncio> getAnunciosNome(String nome){
        List<Anuncio> resultados = new ArrayList<>();
        String nomeLower = nome.toLowerCase();
        for(Anuncio a : anuncios.values()){
            if(a.getNome().toLowerCase().equals(nomeLower)){
                resultados.add(a);
            }
        }
        return resultados;
    }
    
    public boolean inserirCompra(Compra compra) {
        if (compras.containsKey(compra.getId())) {
            return false;
        }

        compras.put(compra.getId(), compra);

        try {
            this.compraDAO.salvar(compra);
        } catch (IOException ex) {
            Logger.getLogger(LanceController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
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
    
    public boolean inserirNotificacao(Notificacao n){
        notificacoes.put(n.getId(), n);
        try {
            notificacaoDAO.salvar(n);
        } catch (IOException ex) {
            Logger.getLogger(AnuncioController.class.getName()).log(Level.SEVERE, "Erro ao salvar notificacao", ex);
            return false;
        }   
        return true;
    }
    
    public boolean inserirAvaliacao(Avaliacao a){
        avaliacoes.put(a.getId(), a);
        try {
            avaliacaoDAO.salvar(a);
        } catch (IOException ex) {
            Logger.getLogger(AnuncioController.class.getName()).log(Level.SEVERE, "Erro ao salvar avaliação", ex);
            return false;
        }   
        return true;
    }
    
    public boolean inserirCartao(Cartao c){
        cartoes.put(c.getId(), c);
        try {
            cartaoDAO.salvar(c);
        } catch (IOException ex) {
            Logger.getLogger(AnuncioController.class.getName()).log(Level.SEVERE, "Erro ao salvar cartão", ex);
            return false;
        }   
        return true;
    }
    
    public boolean inserirPagamento(Pagamento p){
        pagamentos.put(p.getId(), p);
        try {
            pagamentoDAO.salvar(p);
        } catch (IOException ex) {
            Logger.getLogger(AnuncioController.class.getName()).log(Level.SEVERE, "Erro ao salvar pagamento", ex);
            return false;
        }   
        return true;
    }


    public boolean anuncioInCompras(Anuncio a){
       for(Compra c : compras.values()){
           if(c.getAnuncio().getId().equals(a.getId())){
               return true;
           }
       }
       return false;
    }
    
    public void alterarNomeUsuario(String nome, Usuario u) throws IOException{
        u.setNome(nome);
        usuarioDAO.editar(u);
    }
    
    public void alterarEmailUsuario(String email, Usuario u) throws IOException{
        u.setEmail(email);
        usuarioDAO.editar(u);
    }
    
    public void alterarSenhaUsuario(String senha, Usuario u) throws IOException{
        u.setSenha(senha);
        usuarioDAO.editar(u);
    }
    
    public void alterarNomeTitularCartao(String nome, Cartao c) throws IOException{
        c.setNomeTitular(nome);
        cartaoDAO.editar(c);
    }
    
    public void alterarNomeMetodoCartao(String nome, Cartao c) throws IOException{
        c.setNomeMeioPagamento(nome);
        cartaoDAO.editar(c);
    }
    
    public void alterarNumeroCartao(String numero, Cartao c) throws IOException{
        c.setNumero(numero);
        cartaoDAO.editar(c);
    }
    
    public void alterarCVVCartao(String cvv, Cartao c) throws IOException{
        c.setCVV(cvv);
        cartaoDAO.editar(c);
    }
    
    public void deletarCartao(Cartao c) throws IOException{
        cartaoDAO.deletar(c);
    }
    
    public void editarStatusCompra(Compra c) throws IOException{
        c.concluir();
        compraDAO.editar(c);
    }
}