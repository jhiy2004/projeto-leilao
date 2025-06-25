package persistence;

import java.io.*;
import java.util.*;
import model.Cartao;
import model.Comprador;

public class CartaoDAO implements DAO<Cartao> {
    private static final String FILENAME = "cartoes.csv";
    private final Map<String, Comprador> compradores;

    public CartaoDAO(Map<String, Comprador> compradores) {
        this.compradores = compradores;
    }

    @Override
    public void editar(Cartao cartao) throws IOException {
        Map<String, Cartao> cartoes = carregar();

        if (!cartoes.containsKey(cartao.getId())) {
            throw new IllegalArgumentException("Cartao com id não encontrado: " + cartao.getId());
        }
        
        cartoes.put(cartao.getId(), cartao);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Cartao c : cartoes.values()) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    c.getId(),
                    c.getNumero(),
                    c.getNomeTitular(),
                    c.getCvv(),
                    c.getNomeMeioPagamento(),
                    c.getComprador().getId()
                ));
            }
        }
    }

    @Override
    public void deletar(Cartao cartao) throws IOException {
        Map<String, Cartao> cartoes = carregar();

        if (!cartoes.containsKey(cartao.getId())) {
            throw new IllegalArgumentException("Cartao com id não encontrado: " + cartao.getId());
        }
        
        cartoes.remove(cartao.getId());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Cartao c : cartoes.values()) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    c.getId(),
                    c.getNumero(),
                    c.getNomeTitular(),
                    c.getCvv(),
                    c.getNomeMeioPagamento(),
                    c.getComprador().getId()
                ));
            }
        }
    }
    
    @Override
    public void salvar(Cartao cartao) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            // id,numero,nome_titular,cvv,nome_meio_pagamento,compradorId
            writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                cartao.getId(),
                cartao.getNumero(),
                cartao.getNomeTitular(),
                cartao.getCvv(),
                cartao.getNomeMeioPagamento(),
                cartao.getComprador().getId()
            ));
        }
    }

    @Override
    public Map<String, Cartao> carregar() throws IOException {
        Map<String, Cartao> cartoes = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",", -1);
                if (campos.length < 6) continue;

                String id = campos[0];
                String numero = campos[1];
                String nomeTitular = campos[2];
                String cvv = campos[3];
                String meioPagamento = campos[4];
                String compradorId = campos[5];

                Comprador comprador = compradores.get(compradorId);
                if (comprador != null) {
                    Cartao cartao = new Cartao(id, numero, nomeTitular, cvv, meioPagamento, comprador);
                    cartoes.put(id, cartao);
                    comprador.adicionarCartao(cartao); // se houver esse método
                }
            }
        }

        return cartoes;
    }
}
