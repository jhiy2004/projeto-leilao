package persistence;

import java.io.*;
import java.util.*;
import model.Notificacao;
import model.Usuario;

public class NotificacaoDAO implements DAO<Notificacao> {
    private static final String FILENAME = "notificacoes.csv";
    private final Map<String, Usuario> usuarios;

    public NotificacaoDAO(Map<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public void editar(Notificacao n) throws IOException {
        Map<String, Notificacao> notificacoes = carregar();

        if (!notificacoes.containsKey(n.getId())) {
            throw new IllegalArgumentException("Notificação com id não encontrada: " + n.getId());
        }

        notificacoes.put(n.getId(), n);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Notificacao notif : notificacoes.values()) {
                writer.write(String.format("%s,%s,%b,%s\n",
                    notif.getId(),
                    notif.getMensagem().replace(",", ";"),
                    notif.isVisualizada(),
                    notif.getUsuario().getId()
                ));
            }
        }
    }

    @Override
    public void deletar(Notificacao n) throws IOException {
        Map<String, Notificacao> notificacoes = carregar();

        if (!notificacoes.containsKey(n.getId())) {
            throw new IllegalArgumentException("Notificação com id não encontrada: " + n.getId());
        }

        notificacoes.remove(n.getId());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Notificacao notif : notificacoes.values()) {
                writer.write(String.format("%s,%s,%b,%s\n",
                    notif.getId(),
                    notif.getMensagem().replace(",", ";"),
                    notif.isVisualizada(),
                    notif.getUsuario().getId()
                ));
            }
        }
    }
    
    @Override
    public void salvar(Notificacao n) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            // id,mensagem,visualizada,usuarioId
            writer.write(String.format("%s,%s,%b,%s\n",
                n.getId(),
                n.getMensagem().replace(",", ";"),
                n.isVisualizada(),
                n.getUsuario().getId()
            ));
        }
    }

    @Override
    public Map<String, Notificacao> carregar() throws IOException {
        Map<String, Notificacao> notificacoes = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",", -1);
                String id = campos[0];
                String mensagem = campos[1].replace(";", ",");
                boolean visualizada = Boolean.parseBoolean(campos[2]);
                String usuarioId = campos[3];

                Usuario usuario = usuarios.get(usuarioId);
                if (usuario != null) {
                    Notificacao notificacao = new Notificacao(id, mensagem, usuario);
                    if (visualizada) notificacao.visualizar();
                    notificacoes.put(id, notificacao);
                    usuario.adicionarNotificacao(notificacao);
                }
            }
        }

        return notificacoes;
    }
}