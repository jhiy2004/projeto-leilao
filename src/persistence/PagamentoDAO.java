package persistence;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import model.*;

public class PagamentoDAO implements DAO<Pagamento> {
    private static final String FILENAME = "pagamentos.csv";

    private final Map<String, Vendedor> vendedores;
    private final Map<String, Compra> compras;
    private final Map<String, Cartao> cartoes;

    public PagamentoDAO(Map<String, Vendedor> vendedores,
                        Map<String, Compra> compras,
                        Map<String, Cartao> cartoes) {
        this.vendedores = vendedores;
        this.compras = compras;
        this.cartoes = cartoes;
    }

    @Override
    public void salvar(Pagamento pagamento) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            // id,vendedorId,compraId,cartaoId,data
            writer.write(String.format("%s,%s,%s,%s,%s\n",
                pagamento.getId(),
                pagamento.getVendedor().getId(),
                pagamento.getCompra().getId(),
                pagamento.getCartao().getId(),
                pagamento.getData().toString()
            ));
        }
    }

    @Override
    public Map<String, Pagamento> carregar() throws IOException {
        Map<String, Pagamento> pagamentos = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",", -1);
                if (campos.length < 5) continue;

                String id = campos[0];
                String vendedorId = campos[1];
                String compraId = campos[2];
                String cartaoId = campos[3];
                LocalDateTime data = LocalDateTime.parse(campos[4]);

                Vendedor vendedor = vendedores.get(vendedorId);
                Compra compra = compras.get(compraId);
                Cartao cartao = cartoes.get(cartaoId);

                if (vendedor != null && compra != null && cartao != null) {
                    Pagamento pagamento = new Pagamento(id, vendedor, compra, cartao, data);
                    pagamentos.put(id, pagamento);

                    cartao.getComprador().adicionarPagamento(pagamento);
                    vendedor.adicionarPagamento(pagamento);
                }
            }
        }
        return pagamentos;
    }
}
