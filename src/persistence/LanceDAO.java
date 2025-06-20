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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Anuncio;
import model.Comprador;
import model.Lance;

/**
 *
 * @author jhiy2
 */
public class LanceDAO implements DAO<Lance>{
    private static final String FILENAME = "lances.csv";
    private final Map<String, Comprador> compradores;
    private final Map<String, Anuncio> anuncios;

    
    public LanceDAO(Map<String, Comprador> compradores, Map<String, Anuncio> anuncios){
        this.compradores = compradores;
        this.anuncios = anuncios;
    }
    
    @Override
    public void salvar(Lance lance) throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))){
            //id,compradorId,valor,data,anuncioId
            writer.write(String.format("%s,%s,%f,%s,%s\n",
                lance.getId(),
                lance.getComprador().getId(),
                lance.getValor(),
                lance.getData().toString(),
                lance.getAnuncio().getId()
            ));
        }

    }
    
    @Override
    public Map<String, Lance> carregar() throws IOException {
        Map<String, Lance> lances = new HashMap<>();
        List<Lance> lancesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",", -1);
                String id = campos[0];
                String compradorId = campos[1];
                double valor = Double.parseDouble(campos[2]);
                LocalDateTime data = LocalDateTime.parse(campos[3]);
                String anuncioId = campos[4];

                Comprador comprador = this.compradores.get(compradorId);
                Anuncio anuncio = this.anuncios.get(anuncioId);        
                if (comprador != null && anuncio != null) {
                    Lance lance = new Lance(id, comprador, valor, data, anuncio);
                    lances.put(id, lance);
                    lancesList.add(lance);
                }
            }
        }

        lancesList.sort(Comparator.comparing(Lance::getData));

        for (Lance lance : lancesList) {
            lance.getAnuncio().atualizarLance(lance);
        }

        return lances;
    }

}
