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
import model.Avaliacao;
import model.Compra;
import model.Notificacao;

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

        return new ArrayList<>(anuncios.values());
    }
    
    public static List<Compra> getTodasComprasComprador(String compradorId) {
        Catalogo catalogo = Catalogo.getInstance();
        Map<String, Compra> todasCompras = catalogo.getCompras();

        List<Compra> comprasDoComprador = new ArrayList<>();
        for (Compra c : todasCompras.values()) {
            if (c.getComprador().getId().equals(compradorId)) {
                comprasDoComprador.add(c);
            }
        }

        return comprasDoComprador;
    }
    
    public static List<Compra> getTodasVendasVendedor(String vendedorId) {
        Catalogo catalogo = Catalogo.getInstance();
        Map<String, Compra> todasCompras = catalogo.getCompras();

        List<Compra> comprasDoVendedor = new ArrayList<>();
        for (Compra c : todasCompras.values()) {
            if (c.getVendedor().getId().equals(vendedorId)) {
                comprasDoVendedor.add(c);
            }
        }

        return comprasDoVendedor;
    }
    
    public static boolean adicionarAvaliacaoCompra(String compraId, int nota) {
        if (nota < 1 || nota > 5) {
            System.out.println("Nota inválida. Deve estar entre 1 e 10.");
            return false;
        }

        Catalogo catalogo = Catalogo.getInstance();
        Compra compra = catalogo.getCompras().get(compraId);

        if (compra == null) {
            System.out.println("Compra não encontrada.");
            return false;
        }

        if (compra.getAvaliacao() != null) {
            System.out.println("Compra já foi avaliada.");
            return false;
        }

        Avaliacao avaliacao = new Avaliacao(null, nota, compra);
        compra.setAvaliacao(avaliacao);

        catalogo.inserirAvaliacao(avaliacao);

        return true;
    }
 
    public static void processarEncerramentos() {
        Catalogo catalogo = Catalogo.getInstance();
        Map<String, Anuncio> anuncios = catalogo.getAnuncios();

        for (Anuncio anuncio : anuncios.values()) {
            anuncio.verificarEncerramento();

            if (anuncio.isEncerrado() && !catalogo.anuncioInCompras(anuncio)) {
                Lance lanceVencedor = anuncio.getLanceAtual();
                if (lanceVencedor != null) {
                    Comprador comprador = lanceVencedor.getComprador();
                    Vendedor vendedor = anuncio.getVendedor();

                    Compra compra = new Compra(
                        null,
                        LocalDateTime.now(),
                        lanceVencedor.getValor(),
                        vendedor,
                        comprador,
                        anuncio
                    );

                    catalogo.inserirCompra(compra);

                    comprador.adicionarCompra(compra);
                    vendedor.adicionarVenda(compra);
                    
                    Notificacao notificacaoVendedor = new Notificacao(
                            null,
                            String.format("Seu anúncio '%s' teve um lance vencedor", anuncio.getNome()),
                            vendedor
                    );
                    
                    Notificacao notificacaoComprador = new Notificacao(
                            null,
                            String.format("Seu lance foi vencedor no anúncio '%s'", anuncio.getNome()),
                            comprador
                    );
                    
                    catalogo.inserirNotificacao(notificacaoVendedor);
                    vendedor.adicionarNotificacao(notificacaoVendedor);
                    
                    catalogo.inserirNotificacao(notificacaoComprador);
                    comprador.adicionarNotificacao(notificacaoComprador);
                }
            }
        }
    }

    public Anuncio getAnuncioPorId(String id) {
        Catalogo catalogo = Catalogo.getInstance();
        Map<String, Anuncio> anuncios = catalogo.getAnuncios();
        
        return anuncios.get(id);
    }
    
    public List<Anuncio> getAnunciosPorNome(String nome) {
        Catalogo catalogo = Catalogo.getInstance();
        Map<String, Anuncio> allAnuncios = catalogo.getAnuncios();
        
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        for(Anuncio a : allAnuncios.values()){
            if(a.getNome().toLowerCase().equals(nome.toLowerCase())){
                anuncios.add(a);
            }
        }
        
        return anuncios;
    }
}
