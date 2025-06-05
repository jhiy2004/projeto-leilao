/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Map;
import model.Vendedor;

/**
 *
 * @author jhiy2
 */
public class VendedorController {
    UsuarioController uc;
    Map<String, Vendedor> vendedores;
    
    public VendedorController(UsuarioController uc){
        this.uc = uc;
        this.vendedores = uc.getVendedores();
    }
    
    public Map<String, Vendedor> getVendedores(){
        return this.vendedores;
    }
}
