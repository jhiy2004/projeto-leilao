package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Usuario;
import persistence.UsuarioDAO;

/**
 *
 * @author jhiy2
 */
public class UsuarioController {
    private List<Usuario> usuarios;
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
        usuarios = new ArrayList<>();

        try {
            Map<String, Usuario> mapaUsuarios = usuarioDAO.carregar();
            usuarios.addAll(mapaUsuarios.values());
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    public boolean matchUser(String email, String senha) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public boolean emailExiste(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean cpfExiste(String cpf) {
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public boolean addUser(Usuario novoUsuario) {
        if (emailExiste(novoUsuario.getEmail()) || cpfExiste(novoUsuario.getCpf())) {
            return false;
        }

        usuarios.add(novoUsuario);

        try {
            usuarioDAO.salvar(novoUsuario);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
            return false;
        }

        return true;
    }
    
    public Usuario getUserByEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}