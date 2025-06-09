package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Usuario;
import model.Vendedor;
import model.Comprador;
import catalogo.Catalogo;

/**
 *
 * @author jhiy2
 */
public class UsuarioController {
    public static boolean matchUser(String email, String senha) {
        Catalogo catalogo = Catalogo.getInstance();
        
        Map<String, Usuario> usuarios = catalogo.getUsuarios();
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public static boolean emailExiste(String email) {
        Catalogo catalogo = Catalogo.getInstance();
        
        Map<String, Usuario> usuarios = catalogo.getUsuarios();
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean cpfExiste(String cpf) {
        Catalogo catalogo = Catalogo.getInstance();
        
        Map<String, Usuario> usuarios = catalogo.getUsuarios();
        
        for (Usuario u : usuarios.values()) {
            if (u.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public static boolean addUser(Usuario novoUsuario) {
        if (emailExiste(novoUsuario.getEmail()) || cpfExiste(novoUsuario.getCpf())) {
            return false;
        }

        Catalogo catalogo = Catalogo.getInstance();
        
        return catalogo.inserirUsuario(novoUsuario);
    }
    
    public static Usuario getUserByEmail(String email) {
        Catalogo catalogo = Catalogo.getInstance();
        
        Map<String, Usuario> usuarios = catalogo.getUsuarios();
        
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public static List<Usuario> getUsuarios() {
        Catalogo catalogo = Catalogo.getInstance();
        
        Map<String, Usuario> usuarios = catalogo.getUsuarios();
        return new ArrayList<>(usuarios.values());
    }
}