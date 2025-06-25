/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import catalogo.Catalogo;
import java.io.IOException;
import model.Compra;

/**
 *
 * @author vitor
 */
public class CompraController {
    public static void editarStatusCompra(Compra c) throws IOException{
        Catalogo catalogo = Catalogo.getInstance();
        catalogo.editarStatusCompra(c);
    }
}
