/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.UUID;

/**
 *
 * @author jhiy2
 */
public class Cartao {
    private final String id;
    private String numero;
    private String nome_titular;
    private String cvv;
    private String nome_meio_pagamento;
    private final Comprador comprador;
    
    public Cartao(String id, String numero, String nome_titular, String cvv, String nome_meio_pagamento, Comprador comprador){
        this.id = (id == null) ? UUID.randomUUID().toString() : id;
        this.numero = numero;
        this.nome_titular = nome_titular;
        this.cvv = cvv;
        this.nome_meio_pagamento = nome_meio_pagamento;
        this.comprador = comprador;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getNumero(){
        return this.numero;
    }
    
    public String getNomeTitular(){
        return this.nome_titular;
    }
    
    public String getCvv(){
        return this.cvv;
    }
    
    public String getNomeMeioPagamento(){
        return this.nome_meio_pagamento;
    }
    
    public Comprador getComprador(){
        return this.comprador;
    }
    
    public void setNumero(String numero){
        this.numero = numero;
    }
    
    public void setNomeTitular(String nome_titular){
        this.nome_titular = nome_titular;
    }
    
    public void setNomeMeioPagamento(String nome_meio_pagamento){
        this.nome_meio_pagamento = nome_meio_pagamento;
    }
    
    public void setCVV(String cvv){
        this.cvv = cvv;
    }
}
