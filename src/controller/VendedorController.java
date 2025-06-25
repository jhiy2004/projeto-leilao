/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import catalogo.Catalogo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Compra;
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
    
        
    public static List<Compra> getTodasVendasVendedor(String vendedorId) {
        Catalogo catalogo = Catalogo.getInstance();
        Vendedor vendedor = catalogo.getVendedorId(vendedorId);

        if (vendedor == null) {
            return new ArrayList<>();
        }

        return vendedor.getVendas();
    }
}
