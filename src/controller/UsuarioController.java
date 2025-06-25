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
    
    public static boolean nomeValido(String nome) {
    // Lista de caracteres inválidos
    String caracteresInvalidos = "0123456789*-.;/!@#$%^&*()_+=[]{}|:;\"'<>,.?/~`\\";
    
    for (char c : caracteresInvalidos.toCharArray()) {
        if (nome.indexOf(c) != -1) {
            return false;
        }
    }

    return true;
}
    
    public static boolean senhaValida(String senha){
        if (senha.length() < 8) {
            return false;
        }
        return true;
    }
    
    public static boolean emailValido(String email){
        if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")){
            return false;
        }
        return true;
    }
    
    public static boolean cpfValido(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^\\d]", "");

        // CPF deve ter 11 dígitos e não pode ter todos os dígitos iguais
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }

            int digito1 = 11 - (soma % 11);
            if (digito1 >= 10) digito1 = 0;
            if (digito1 != (cpf.charAt(9) - '0')) return false;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }

            int digito2 = 11 - (soma % 11);
            if (digito2 >= 10) digito2 = 0;
            return digito2 == (cpf.charAt(10) - '0');

        } catch (Exception e) {
            return false;
        }
    }
    
    public void alterarNome(String nome, Usuario user) throws IOException{
        Catalogo catalogo = Catalogo.getInstance();
        
        catalogo.alterarNome(nome, user);
    }
    
    public void alterarEmail(String email, Usuario user) throws IOException{
        Catalogo catalogo = Catalogo.getInstance();
        
        catalogo.alterarEmail(email, user);
    }
    
    public void alterarSenha(String senha, Usuario user) throws IOException{
        Catalogo catalogo = Catalogo.getInstance();
        
        catalogo.alterarSenha(senha, user);
    }
}