/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author jhiy2
 */
public class Anuncio {
    private final List<Lance> lances;
    private final Vendedor vendedor;
    private final LocalDateTime dataCriacao;
    private final LocalDateTime dataEncerramento;
    private final double valorInicial;
    private String nome;
    private String descricao;
    private Estado estado;
    
    
    public Anuncio(String nome, String descricao, Vendedor vendedor, LocalDateTime dataEncerramento, double valorInicial){
        this.nome = nome;
        this.descricao = descricao;
        this.vendedor = vendedor;
        this.dataCriacao = LocalDateTime.now();
        this.dataEncerramento = dataEncerramento;
        this.estado = Estado.ATIVO;
        this.valorInicial = valorInicial;
        
        this.lances = new ArrayList<>();
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public Vendedor getVendedor(){
        return this.vendedor;
    }
    
    public LocalDateTime getDataCriacao(){
        return this.dataCriacao;
    }
    
    public LocalDateTime getDataEncerramento(){
        return this.dataEncerramento;
    }
    
    public List<Lance> getLances(){
        return this.lances;
    
    }
    
    public double getValorInicial(){
        return this.valorInicial;
    }
    
    public Lance getLanceAtual(){
        try{
            return this.lances.getLast();
        }catch(NoSuchElementException e){
            return null;
        }
    }
    
    public void atualizarLance(Lance lance){
        this.lances.add(lance);
    }
    
    public void encerrar(){
        if (!isEncerrado()) {
            this.estado = Estado.ENCERRADO;
        }
    }
    
    public boolean isEncerrado() {
        return estado == Estado.ENCERRADO;
    }

    public void verificarEncerramento() {
        if (estado != Estado.ENCERRADO && LocalDateTime.now().isAfter(dataEncerramento)) {
            this.estado = Estado.ENCERRADO;
        }
    }
}