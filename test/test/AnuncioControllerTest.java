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

import java.time.LocalDateTime;
import java.util.*;

import catalogo.Catalogo;
import controller.AnuncioController;
import model.*;

/**
 *
 * @author vitor
 */
public class AnuncioControllerTest {
    private Catalogo catalogo;
    private Anuncio anuncio;
    private Lance lance;
    private Comprador comprador;
    private Vendedor vendedor;

    @Before
    public void setUp() {
        catalogo = mock(Catalogo.class);
        anuncio = mock(Anuncio.class);
        lance = mock(Lance.class);
        comprador = mock(Comprador.class);
        vendedor = mock(Vendedor.class);
    }

    @Test
    public void deveProcessarEncerramentoComLanceVencedor() {
        Map<String, Anuncio> anunciosMap = new HashMap<>();
        anunciosMap.put("1", anuncio);

        when(catalogo.getAnuncios()).thenReturn(anunciosMap);
        when(anuncio.isEncerrado()).thenReturn(true);
        when(anuncio.getLanceAtual()).thenReturn(lance);
        when(anuncio.getNome()).thenReturn("Produto Teste");
        when(anuncio.getVendedor()).thenReturn(vendedor);
        when(lance.getComprador()).thenReturn(comprador);
        when(lance.getValor()).thenReturn(150.0);
        when(catalogo.anuncioInCompras(anuncio)).thenReturn(false);

        try (MockedStatic<Catalogo> mockedStatic = Mockito.mockStatic(Catalogo.class)) {
            mockedStatic.when(Catalogo::getInstance).thenReturn(catalogo);

            // Executa o método
            AnuncioController.processarEncerramentos();

            // Verifica se métodos esperados foram chamados
            verify(anuncio).verificarEncerramento();
            verify(catalogo).inserirCompra(any(Compra.class));
            verify(comprador).adicionarCompra(any(Compra.class));
            verify(vendedor).adicionarVenda(any(Compra.class));
            verify(catalogo, times(2)).inserirNotificacao(any(Notificacao.class));
            verify(vendedor).adicionarNotificacao(any(Notificacao.class));
            verify(comprador).adicionarNotificacao(any(Notificacao.class));
        }
    }
}
