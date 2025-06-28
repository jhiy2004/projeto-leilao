/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import controller.CartaoController;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author jhiy2
 */
public class CartaoControllerTest {
    // Testes nome titular
    @Test
    public void validarNomeTitular_deveRetornarTrueParaNomeValido() {
        assertTrue(CartaoController.validarNomeTitular("Maria da Silva"));
        assertTrue(CartaoController.validarNomeTitular("Joao Pedro"));
    }

    @Test
    public void validarNomeTitular_deveRetornarFalseParaNomeComNumerosOuSimbolos() {
        assertFalse(CartaoController.validarNomeTitular("Maria123"));
        assertFalse(CartaoController.validarNomeTitular("Joao@Silva"));
        assertFalse(CartaoController.validarNomeTitular("Lucas!"));
        assertFalse(CartaoController.validarNomeTitular("Ana#"));
    }

    // Testes nome método
    @Test
    public void validarNomeMetodo_deveAceitarTextoSemSimbolos() {
        assertTrue(CartaoController.validarNomeMetodo("Cartao Credito"));
        assertTrue(CartaoController.validarNomeMetodo("Debito Online"));
    }

    @Test
    public void validarNomeMetodo_deveRetornarFalseParaSimbolosInvalidos() {
        assertFalse(CartaoController.validarNomeMetodo("Débito@Online"));
        assertFalse(CartaoController.validarNomeMetodo("PIX#"));
        assertFalse(CartaoController.validarNomeMetodo("Cartão!"));
    }

    // Testes número cartão
    @Test
    public void validarNumero_deveAceitarNumeroCom16Digitos() {
        assertTrue(CartaoController.validarNumero("1234567812345678"));
    }

    @Test
    public void validarNumero_deveRecusarNumerosComLetrasOuSimbolos() {
        assertFalse(CartaoController.validarNumero("1234abcd5678efgh"));
        assertFalse(CartaoController.validarNumero("1234-5678-9012-3456"));
    }

    @Test
    public void validarNumero_deveRecusarNumerosComTamanhoInvalido() {
        assertFalse(CartaoController.validarNumero("12345678"));
        assertFalse(CartaoController.validarNumero("123456781234567890"));
    }

    // Testes cvv
    @Test
    public void validarCVV_deveAceitarTresDigitos() {
        assertTrue(CartaoController.validarCVV("123"));
        assertTrue(CartaoController.validarCVV("999"));
    }

    @Test
    public void validarCVV_deveRecusarNaoNumericoOuTamanhoIncorreto() {
        assertFalse(CartaoController.validarCVV("12"));
        assertFalse(CartaoController.validarCVV("1234"));
        assertFalse(CartaoController.validarCVV("12A"));
        assertFalse(CartaoController.validarCVV("1@3"));
    }
}