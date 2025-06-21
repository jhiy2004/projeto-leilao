/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author jhiy2
 */
public abstract class Usuario {
    protected String nome;
    protected String email;
    protected String senha;
    protected String cpf;
    protected String id;
    protected List<Notificacao> notificacoes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getId() {
        return id;
    }
    
    
    public List<Notificacao> getNotificacoes(){
        return this.notificacoes;
    }
    
    public void  adicionarNotificacao(Notificacao notificacao){
        if(!notificacoes.contains(notificacao)){
            this.notificacoes.add(notificacao);
        }
    }
}
