/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import catalogo.Catalogo;
import model.Avaliacao;
import model.Compra;

/**
 *
 * @author vitor
 */
public class AvaliacaoController {
    public static void inserirAvaliacao(Avaliacao a, Compra c){
        Catalogo catalogo = Catalogo.getInstance();
        catalogo.inserirAvaliacao(a);
        c.setAvaliacao(a);
    }
}
