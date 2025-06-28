/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import catalogo.Catalogo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Anuncio;
import model.Avaliacao;
import model.Cartao;
import model.Compra;
import model.Comprador;
import model.Notificacao;
import model.Pagamento;
import model.Vendedor;

/**
 *
 * @author jhiy2
 */
public class CompradorController {    
    public static Map<String, Comprador> getCompradores() {
        Catalogo catalogo = Catalogo.getInstance();
        
        return catalogo.getCompradores();
    }
    
        
    public static List<Compra> getTodasComprasComprador(String compradorId) {
        Catalogo catalogo = Catalogo.getInstance();
        Comprador comprador = catalogo.getCompradorId(compradorId);

        if (comprador == null) {
            return new ArrayList<>();
        }

        return comprador.getCompras();
    }
    
    public static List<Cartao> getTodosCartoesComprador(String compradorId){
        Catalogo catalogo = Catalogo.getInstance();
        
        Comprador comprador = catalogo.getCompradorId(compradorId);
        
        if(comprador == null){
            return new ArrayList<>();
        }
        
        return comprador.getCartoes();
    }
    
    public static boolean adicionarCartao(String compradorId, String numero, String titular, String cvv, String nome_meio){
        Catalogo catalogo = Catalogo.getInstance();
        
        Comprador comprador = catalogo.getCompradorId(compradorId);
        
        if(comprador == null) return false;
        
        Cartao cartao = new Cartao(null, numero, titular, cvv, nome_meio, comprador);
        
        comprador.adicionarCartao(cartao);
        catalogo.inserirCartao(cartao);
        return true;
    }
    
    public static boolean fazerPagamento(String cartaoId, String compraId){
        Catalogo catalogo = Catalogo.getInstance();
        
        Cartao cartao = catalogo.getCartaoId(cartaoId);
        Compra compra = catalogo.getCompraId(compraId);
        
        if (cartao == null || compra == null) return false;
        
        Comprador comprador = compra.getComprador();
        
        // Confirma que o dono da compra é o mesmo do cartão
        if (!cartao.getComprador().getId().equals(comprador.getId())) return false;

         if (compra.isConcluida()) return false;
        
        Vendedor vendedor = compra.getVendedor();
        
        Pagamento pagamento = new Pagamento(null, vendedor, compra, cartao);
        
        catalogo.inserirPagamento(pagamento);
        comprador.adicionarPagamento(pagamento);
        vendedor.adicionarPagamento(pagamento);
        
        compra.concluir();
        
        Anuncio anuncio = compra.getAnuncio();
        
        Notificacao notificacaoVendedor = new Notificacao(
                            null,
                            String.format("A compra do seu anúncio '%s' foi concluída", anuncio.getNome()),
                            vendedor
                    );
            
        catalogo.inserirNotificacao(notificacaoVendedor);
        vendedor.adicionarNotificacao(notificacaoVendedor);
        
        return true;
    }
        
    public static boolean adicionarAvaliacaoCompra(String compraId, int nota) {
        if (nota < 1 || nota > 10) {
            System.out.println("Nota inválida. Deve estar entre 1 e 10.");
            return false;
        }

        Catalogo catalogo = Catalogo.getInstance();
        Compra compra = catalogo.getCompras().get(compraId);

        if (compra == null) {
            System.out.println("Compra não encontrada.");
            return false;
        }

        if (!compra.isConcluida()) {
            System.out.println("Só é possível avaliar compras concluídas.");
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
}
