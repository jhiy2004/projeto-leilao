/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 *
 * @author jhiy2
 */
public class Anuncio {
    private final String id;
    private final List<Lance> lances;
    private final Vendedor vendedor;
    private final LocalDateTime dataCriacao;
    private final LocalDateTime dataEncerramento;
    private final double valorInicial;
    private String nome;
    private String descricao;
    private Estado estado;
    
    
    public Anuncio(String id, String nome, String descricao, Vendedor vendedor, LocalDateTime dataCriacao, LocalDateTime dataEncerramento, double valorInicial){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.nome = nome;
        this.descricao = descricao;
        this.vendedor = vendedor;
        this.dataCriacao = (dataCriacao == null) ? LocalDateTime.now() : dataCriacao;
        this.dataEncerramento = dataEncerramento;
        this.estado = Estado.ATIVO;
        this.valorInicial = valorInicial;
        
        this.lances = new ArrayList<>();
    }
    
    public String getId(){
        return this.id;
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
    
    public double getValorAtual(){
        try{
            return this.lances.getLast().getValor();
        }catch(NoSuchElementException e){
            return this.valorInicial;
        }
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
    
    @Override
    public String toString() {
        return "Anuncio{" +
            "id='" + id + '\'' +
            ", nome='" + nome + '\'' +
            ", descricao='" + descricao + '\'' +
            ", vendedor=" + vendedor.getNome() + // ou outro identificador
            ", dataEncerramento=" + dataEncerramento +
            ", valorInicial=" + valorInicial +
            ", lanceAtual=" + (getLanceAtual() != null ? getLanceAtual().getValor() : "nenhum") +
            '}';
    }

}