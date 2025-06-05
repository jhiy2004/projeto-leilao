package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Usuario;
import model.Vendedor;
import model.Comprador;
import persistence.UsuarioDAO;

/**
 *
 * @author jhiy2
 */
public class UsuarioController {
    private Map<String, Usuario> usuarios;
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();

        try {
            usuarios = usuarioDAO.carregar();
        } catch (IOException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    public boolean matchUser(String email, String senha) {
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public boolean emailExiste(String email) {
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean cpfExiste(String cpf) {
        for (Usuario u : usuarios.values()) {
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

        usuarios.put(novoUsuario.getId().toString(), novoUsuario);

        try {
            usuarioDAO.salvar(novoUsuario);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
            return false;
        }

        return true;
    }
    
    public Usuario getUserByEmail(String email) {
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(this.usuarios.values());
    }
    
    public Map<String, Comprador> getCompradores() {
        Map<String, Comprador> novo = new HashMap<>();
        
        for(Usuario u : usuarios.values()){
            if(u instanceof Comprador){
                novo.put(u.getId().toString(), (Comprador)u);
            }
        }
        
        return novo;
    }
    
    public Map<String, Vendedor> getVendedores() {
        Map<String, Vendedor> novo = new HashMap<>();
        
        for(Usuario u : usuarios.values()){
            if(u instanceof Vendedor){
                novo.put(u.getId().toString(), (Vendedor)u);
            }
        }
        
        return novo;    
    }
}