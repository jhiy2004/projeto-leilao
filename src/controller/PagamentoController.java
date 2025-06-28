/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import catalogo.Catalogo;
import model.Pagamento;

/**
 *
 * @author vitor
 */
public class PagamentoController {
    public static void inserirPagamento(Pagamento p){
        Catalogo catalogo = Catalogo.getInstance();
        catalogo.inserirPagamento(p);
    }
}
