package persistence;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import model.Avaliacao;
import model.Compra;

public class AvaliacaoDAO implements DAO<Avaliacao> {
    private static final String FILENAME = "avaliacoes.csv";
    private final Map<String, Compra> compras;

    public AvaliacaoDAO(Map<String, Compra> compras) {
        this.compras = compras;
    }

    @Override
    public void salvar(Avaliacao avaliacao) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            // Formato: id,nota,data,compraId
            writer.write(String.format("%s,%d,%s,%s\n",
                avaliacao.getId(),
                avaliacao.getNota(),
                avaliacao.getData().toString(),
                avaliacao.getCompra().getId()
            ));
        }
    }

    @Override
    public Map<String, Avaliacao> carregar() throws IOException {
        Map<String, Avaliacao> avaliacoes = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",", -1);
                String id = campos[0];
                int nota = Integer.parseInt(campos[1]);
                LocalDateTime data = LocalDateTime.parse(campos[2]);
                String compraId = campos[3];

                Compra compra = compras.get(compraId);
                if (compra != null) {
                    Avaliacao avaliacao = new Avaliacao(id, nota, data, compra);
                    compra.setAvaliacao(avaliacao);
                    avaliacoes.put(id, avaliacao);
                }
            }
        }

        return avaliacoes;
    }
}