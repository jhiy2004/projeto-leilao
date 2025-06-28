/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import catalogo.Catalogo;
import java.io.IOException;
import model.Cartao;

/**
 *
 * @author vitor
 */
public class CartaoController {
    public static boolean validarNomeTitular(String nome){
        String caracteresInvalidos = "0123456789*-.;/!@#$%^&*()_+=[]{}|:;\"'<>,.?/~`\\";
    
        for (char c : caracteresInvalidos.toCharArray()) {
            if (nome.indexOf(c) != -1) {
                return false;
            }
        }

        return true;
    }
    
    public static boolean validarNomeMetodo(String nome){
        String caracteresInvalidos = "*-.;/!@#$%^&*()_+=[]{}|:;\"'<>,.?/~`\\";
    
        for (char c : caracteresInvalidos.toCharArray()) {
            if (nome.indexOf(c) != -1) {
                return false;
            }
        }

        return true;
    }
    
    public static boolean validarNumero(String numero){
        if (numero.length() != 16) {
            return false;
        }

        for (int i = 0; i < numero.length(); i++) {
            if (!Character.isDigit(numero.charAt(i))) {
                return false;
            }
        }

        return true;
    }
    
    public static boolean validarCVV(String cvv){
        if (cvv.length() != 3) {
            return false;
        }

        for (int i = 0; i < cvv.length(); i++) {
            if (!Character.isDigit(cvv.charAt(i))) {
                return false;
            }
        }

        return true;
    }
    
    public static void setNomeTitularCartao(String nome, Cartao c) throws IOException{
        Catalogo catalogo = Catalogo.getInstance();
        catalogo.alterarNomeTitularCartao(nome, c);
    }
    
    public static void setNomeMetodoCartao(String nome, Cartao c) throws IOException{
        Catalogo catalogo = Catalogo.getInstance();
        catalogo.alterarNomeMetodoCartao(nome, c);
    }
    
    public static void setNumeroCartao(String numero, Cartao c) throws IOException{
        Catalogo catalogo = Catalogo.getInstance();
        catalogo.alterarNumeroCartao(numero, c);
    }
    
    public static void setCVVCartao(String cvv, Cartao c) throws IOException{
        Catalogo catalogo = Catalogo.getInstance();
        catalogo.alterarCVVCartao(cvv, c);
    }
    
    public static void deletarCartao(Cartao c) throws IOException{
        Catalogo catalogo = Catalogo.getInstance();
        catalogo.deletarCartao(c);
    }
    
    public static boolean adicionarCartao(Cartao c){
        Catalogo catalogo = Catalogo.getInstance();
        return catalogo.inserirCartao(c);
    }
}
