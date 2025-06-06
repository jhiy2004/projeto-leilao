/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import catalogo.Catalogo;
import java.util.Map;
import model.Vendedor;

/**
 *
 * @author jhiy2
 */
public class VendedorController {
    public static Map<String, Vendedor> getVendedores() {
        Catalogo catalogo = Catalogo.getInstance();
        
        return catalogo.getVendedores();
    }
}
