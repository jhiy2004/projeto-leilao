package test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import catalogo.Catalogo;
import controller.AnuncioController;
import model.Anuncio;
import model.Comprador;
import model.Lance;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author vitor
 */
public class LanceControllerTest {
    private Anuncio anuncio;
    private Comprador comprador;

    @Before
    public void setUp() {
        anuncio = mock(Anuncio.class);
        comprador = mock(Comprador.class);
    }

    @Test
    public void deveRetornarFalseSeAnuncioForNull() {
        boolean resultado = AnuncioController.addLanceAnuncio(null, comprador, 100.0);
        assertFalse(resultado);
    }

    @Test
    public void deveRetornarFalseSeCompradorForNull() {
        boolean resultado = AnuncioController.addLanceAnuncio(anuncio, null, 100.0);
        assertFalse(resultado);
    }

    @Test
    public void deveRetornarFalseSeAnuncioEstiverEncerrado() {
        when(anuncio.isEncerrado()).thenReturn(true);

        boolean resultado = AnuncioController.addLanceAnuncio(anuncio, comprador, 100.0);
        assertFalse(resultado);
    }

    @Test
    public void deveRetornarFalseSeValorNaoSuperarLanceAtual() {
        Lance lanceAtual = mock(Lance.class);
        when(anuncio.isEncerrado()).thenReturn(false);
        when(anuncio.getLanceAtual()).thenReturn(lanceAtual);
        when(lanceAtual.getValor()).thenReturn(200.0);

        boolean resultado = AnuncioController.addLanceAnuncio(anuncio, comprador, 150.0);
        assertFalse(resultado);
    }

    @Test
    public void deveAtualizarLanceEInserirNoCatalogoSeTudoEstiverCorreto() {
        when(anuncio.isEncerrado()).thenReturn(false);
        when(anuncio.getLanceAtual()).thenReturn(null); // sem lance atual

        try (MockedStatic<Catalogo> catalogoMockedStatic = Mockito.mockStatic(Catalogo.class)) {
            Catalogo catalogoMock = mock(Catalogo.class);
            catalogoMockedStatic.when(Catalogo::getInstance).thenReturn(catalogoMock);
            when(catalogoMock.inserirLance(any(Lance.class))).thenReturn(true);

            boolean resultado = AnuncioController.addLanceAnuncio(anuncio, comprador, 300.0);

            verify(anuncio).atualizarLance(any(Lance.class));
            verify(catalogoMock).inserirLance(any(Lance.class));
            assertTrue(resultado);
        }
    }
}
