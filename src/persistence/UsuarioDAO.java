package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.Usuario;
import model.Vendedor;
import model.Comprador;

/**
 *
 * @author jhiy2
 */
public class UsuarioDAO implements DAO<Usuario> {
    private static final String FILENAME = "usuarios.csv";

    @Override
    public void editar(Usuario usuario) throws IOException {
        Map<String, Usuario> usuarios = carregar();

        if (!usuarios.containsKey(usuario.getId())) {
            throw new IllegalArgumentException("Usuário com id não encontrado para edição: " + usuario.getId());
        }

        usuarios.put(usuario.getId(), usuario);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Usuario u : usuarios.values()) {
                String tipo;
                if (u instanceof Vendedor) {
                    tipo = "vendedor";
                } else if (u instanceof Comprador) {
                    tipo = "comprador";
                } else {
                    continue;
                }

                writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    u.getId(),
                    u.getNome().replace(",", ";"),
                    u.getEmail().replace(",", ";"),
                    u.getSenha().replace(",", ";"),
                    u.getCpf().replace(",", ";"),
                    tipo
                ));
            }
        }
    }

    @Override
    public void deletar(Usuario usuario) throws IOException {
        Map<String, Usuario> usuarios = carregar();

        if (!usuarios.containsKey(usuario.getId())) {
            throw new IllegalArgumentException("Usuário com id não encontrado para exclusão: " + usuario.getId());
        }

        usuarios.remove(usuario.getId());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Usuario u : usuarios.values()) {
                String tipo;
                if (u instanceof Vendedor) {
                    tipo = "vendedor";
                } else if (u instanceof Comprador) {
                    tipo = "comprador";
                } else {
                    continue;
                }

                writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    u.getId(),
                    u.getNome().replace(",", ";"),
                    u.getEmail().replace(",", ";"),
                    u.getSenha().replace(",", ";"),
                    u.getCpf().replace(",", ";"),
                    tipo
                ));
            }
        }
    }
    
    @Override
    public void salvar(Usuario usuario) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            String tipo;
            if (usuario instanceof Vendedor) {
                tipo = "vendedor";
            } else if (usuario instanceof Comprador) {
                tipo = "comprador";
            } else {
                throw new IllegalArgumentException("Tipo de usuário desconhecido.");
            }

            // id,nome,email,senha,tipo
            writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                usuario.getId(),
                usuario.getNome().replace(",", ";"),
                usuario.getEmail().replace(",", ";"),
                usuario.getSenha().replace(",", ";"),
                usuario.getCpf().replace(",", ";"),
                tipo
            ));
        }
    }

    @Override
    public Map<String, Usuario> carregar() throws IOException {
        Map<String, Usuario> usuarios = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",", -1);
                String id = campos[0];
                String nome = campos[1].replace(";", ",");
                String email = campos[2].replace(";", ",");
                String senha = campos[3].replace(";", ",");
                String cpf = campos[4].replace(";", ",");
                String tipo = campos[5];

                Usuario usuario = null;
                if (tipo.equalsIgnoreCase("vendedor")) {
                    usuario = new Vendedor(id, nome, email, senha, cpf);
                } else if (tipo.equalsIgnoreCase("comprador")) {
                    usuario = new Comprador(id, nome, email, senha, cpf);
                }

                if (usuario != null) {
                    usuarios.put(id, usuario);
                }
            }
        }

        return usuarios;
    }
}