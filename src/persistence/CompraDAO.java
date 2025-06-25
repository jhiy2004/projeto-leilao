package persistence;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import model.Anuncio;
import model.Compra;
import model.Comprador;
import model.Vendedor;

public class CompraDAO implements DAO<Compra> {
    private static final String FILENAME = "compras.csv";
    private final Map<String, Comprador> compradores;
    private final Map<String, Vendedor> vendedores;
    private final Map<String, Anuncio> anuncios;

    public CompraDAO(Map<String, Comprador> compradores, Map<String, Vendedor> vendedores, Map<String, Anuncio> anuncios) {
        this.compradores = compradores;
        this.vendedores = vendedores;
        this.anuncios = anuncios;
    }
    
    @Override
    public void editar(Compra compra) throws IOException {
        Map<String, Compra> compras = carregar();
        
        if (!compras.containsKey(compra.getId())) {
            throw new IllegalArgumentException("Compra com id não encontrado: " + compra.getId());
        }
        
        compras.put(compra.getId(), compra);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Compra c : compras.values()) {
                writer.write(String.format("%s,%s,%f,%b,%s,%s,%s\n",
                    c.getId(),
                    c.getData().toString(),
                    c.getValor(),
                    c.isConcluida(),
                    c.getVendedor().getId(),
                    c.getComprador().getId(),
                    c.getAnuncio().getId()
                ));
            }
        }
    }

    @Override
    public void deletar(Compra compra) throws IOException {
        Map<String, Compra> compras = carregar();
        
        if (!compras.containsKey(compra.getId())) {
            throw new IllegalArgumentException("Compra com id não encontrado: " + compra.getId());
        }
        
        compras.remove(compra.getId());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Compra c : compras.values()) {
                writer.write(String.format("%s,%s,%f,%b,%s,%s,%s\n",
                    c.getId(),
                    c.getData().toString(),
                    c.getValor(),
                    c.isConcluida(),
                    c.getVendedor().getId(),
                    c.getComprador().getId(),
                    c.getAnuncio().getId()
                ));
            }
        }
    }


    @Override
    public void salvar(Compra compra) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            // id,data,valor,concluida,vendedorId,compradorId,anuncioId
            writer.write(String.format("%s,%s,%f,%b,%s,%s,%s\n",
                compra.getId(),
                compra.getData().toString(),
                compra.getValor(),
                compra.isConcluida(),
                compra.getVendedor().getId(),
                compra.getComprador().getId(),
                compra.getAnuncio().getId()
            ));
        }
    }

    @Override
    public Map<String, Compra> carregar() throws IOException {
        Map<String, Compra> compras = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",", -1);
                String id = campos[0];
                LocalDateTime data = LocalDateTime.parse(campos[1]);
                double valor = Double.parseDouble(campos[2]);
                boolean concluida = Boolean.parseBoolean(campos[3]);
                String vendedorId = campos[4];
                String compradorId = campos[5];
                String anuncioId = campos[6];

                Comprador comprador = compradores.get(compradorId);
                Vendedor vendedor = vendedores.get(vendedorId);
                Anuncio anuncio = anuncios.get(anuncioId);

                if (comprador != null && vendedor != null && anuncio != null) {
                    Compra compra = new Compra(id, data, valor, concluida, vendedor, comprador, anuncio);
                    compras.put(id, compra);
                    comprador.adicionarCompra(compra);
                    vendedor.adicionarVenda(compra);
                }
            }
        }

        return compras;
    }
}