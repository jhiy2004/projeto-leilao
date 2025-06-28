/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.*;

import catalogo.Catalogo;
import controller.CompradorController;
import model.*;

/**
 *
 * @author vitor
 */
public class CompradorControllerTest {
    private Catalogo catalogo;
    private Cartao cartao;
    private Compra compra;
    private Comprador comprador;
    private Vendedor vendedor;
    private Anuncio anuncio;

    @Before
    public void setUp() {
        catalogo = mock(Catalogo.class);
        cartao = mock(Cartao.class);
        compra = mock(Compra.class);
        comprador = mock(Comprador.class);
        vendedor = mock(Vendedor.class);
        anuncio = mock(Anuncio.class);
    }

    @Test
    public void deveRealizarPagamentoComSucesso() {
        // Mocks básicos
        String cartaoId = "cartao123";
        String compraId = "compra123";

        // Simulações
        when(catalogo.getCartaoId(cartaoId)).thenReturn(cartao);
        when(catalogo.getCompraId(compraId)).thenReturn(compra);
        when(cartao.getComprador()).thenReturn(comprador);
        when(comprador.getId()).thenReturn("comprador123");
        when(compra.getComprador()).thenReturn(comprador);
        when(compra.getVendedor()).thenReturn(vendedor);
        when(compra.getAnuncio()).thenReturn(anuncio);
        when(compra.isConcluida()).thenReturn(false);
        when(anuncio.getNome()).thenReturn("Produto de Teste");

        try (MockedStatic<Catalogo> catalogoMockedStatic = Mockito.mockStatic(Catalogo.class)) {
            catalogoMockedStatic.when(Catalogo::getInstance).thenReturn(catalogo);

            boolean resultado = CompradorController.fazerPagamento(cartaoId, compraId);

            assertTrue(resultado);

            // Verificações
            verify(catalogo).inserirPagamento(any(Pagamento.class));
            verify(comprador).adicionarPagamento(any(Pagamento.class));
            verify(vendedor).adicionarPagamento(any(Pagamento.class));
            verify(compra).concluir();
            verify(catalogo).inserirNotificacao(any(Notificacao.class));
            verify(vendedor).adicionarNotificacao(any(Notificacao.class));
        }
    }
}
