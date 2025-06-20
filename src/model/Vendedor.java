/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.AnuncioController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author jhiy2
 */
public class Vendedor extends Usuario {
    private final List<Compra> vendas;
    
    public Vendedor(String id, String nome, String email, String senha, String cpf){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        
        this.vendas = new ArrayList<>();
    }
    
    public void criarAnuncio(String nomeAnuncio, String descricaoAnuncio, LocalDateTime dataInicioAnuncio, LocalDateTime dataFimAnuncio, double valorInicial){
        //AnuncioController.criarAnuncio(nomeAnuncio, descricaoAnuncio, this, dataInicioAnuncio, dataFimAnuncio, valorInicial);
    }
    
    public List<Compra> getVendas(){
        return this.vendas;
    }
    
    public void adicionarVenda(Compra venda){
        if (!vendas.contains(venda)) {
            this.vendas.add(venda);
        }
    }
}
