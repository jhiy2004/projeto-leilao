/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import model.Anuncio;
import model.Vendedor;

/**
 *
 * @author jhiy2
 */
public class AnuncioDAO implements DAO<Anuncio> {
    private static final String FILENAME = "anuncios.csv";
    private final Map<String, Vendedor> vendedores;
    
    public AnuncioDAO(Map<String, Vendedor> vendedores){
        this.vendedores = vendedores;
    }
    
    @Override
    public void editar(Anuncio anuncio) throws IOException {
        Map<String, Anuncio> anuncios = carregar();

        if (!anuncios.containsKey(anuncio.getId())) {
            throw new IllegalArgumentException("Anuncio com id não encontrado: " + anuncio.getId());
        }
        
        anuncios.put(anuncio.getId(), anuncio);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Anuncio a : anuncios.values()) {
                writer.write(String.format(Locale.US, "%s,%s,%s,%s,%s,%s,%.2f\n",
                    a.getId(),
                    a.getNome().replace(",", ";"),
                    a.getDescricao().replace(",", ";"),
                    a.getVendedor().getId(),
                    a.getDataCriacao().toString(),
                    a.getDataEncerramento().toString(),
                    a.getValorInicial()
                ));
            }
        }
    }

    @Override
    public void deletar(Anuncio anuncio) throws IOException {
        Map<String, Anuncio> anuncios = carregar();

        if (!anuncios.containsKey(anuncio.getId())) {
            throw new IllegalArgumentException("Anuncio com id não encontrado: " + anuncio.getId());
        }
        
        anuncios.remove(anuncio.getId());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Anuncio a : anuncios.values()) {
                writer.write(String.format(Locale.US, "%s,%s,%s,%s,%s,%s,%f\n",
                    a.getId(),
                    a.getNome().replace(",", ";"),
                    a.getDescricao().replace(",", ";"),
                    a.getVendedor().getId(),
                    a.getDataCriacao().toString(),
                    a.getDataEncerramento().toString(),
                    a.getValorInicial()
                ));
            }
        }
    }
    
    @Override
    public void salvar(Anuncio anuncio) throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))){
            //id,nome,descricao,vendedorId,dataCriacao,dataEncerramento,valorInicial
            writer.write(String.format(Locale.US, "%s,%s,%s,%s,%s,%s,%f\n",
                anuncio.getId(),
                anuncio.getNome().replace(",", ";"),
                anuncio.getDescricao().replace(",", ";"),
                anuncio.getVendedor().getId(),
                anuncio.getDataCriacao().toString(),
                anuncio.getDataEncerramento().toString(),
                anuncio.getValorInicial()
            ));
        }

    }
    
    @Override
    public Map<String, Anuncio> carregar() throws IOException{
        Map<String, Anuncio> anuncios = new HashMap<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(FILENAME))){
            String linha;
            while((linha = reader.readLine()) != null){
                String[] campos = linha.split(",", -1);
                String id = campos[0];
                String nome = campos[1].replace(";", ",");
                String descricao = campos[2].replace(";", ",");
                String vendedorId = campos[3];
                LocalDateTime dataCriacao = LocalDateTime.parse(campos[4]);
                LocalDateTime dataEncerramento = LocalDateTime.parse(campos[5]);
                double valorInicial = Double.parseDouble(campos[6]);

                Vendedor vendedor = this.vendedores.get(vendedorId);
                if(vendedor != null){
                    Anuncio anuncio = new Anuncio(id, nome, descricao, vendedor, dataCriacao, dataEncerramento, valorInicial);
                    anuncios.put(id, anuncio);
                }
            }
        }
        
        return anuncios;
    }
}
