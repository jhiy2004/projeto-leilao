/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import catalogo.Catalogo;
import controller.UsuarioController;
import java.util.HashMap;
import java.util.Map;
import model.Comprador;
import model.Usuario;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

/**
 *
 * @author jhiy2
 */
public class UsuarioControllerTest {
    @Test
    public void testMatchUser_Success() {
        // Cria um mock de Usuario
        Comprador userMock = mock(Comprador.class);
        when(userMock.getEmail()).thenReturn("vitor@email.com");
        when(userMock.getSenha()).thenReturn("123");

        // Cria o mapa de usuários simulado
        Map<String, Usuario> usuarios = new HashMap<>();
        usuarios.put("vitor", userMock);

        // Cria o mock de Catalogo
        Catalogo catalogoMock = mock(Catalogo.class);
        when(catalogoMock.getUsuarios()).thenReturn(usuarios);

        // Usa try-with-resources para mock estático
        try (MockedStatic<Catalogo> mockedStatic = mockStatic(Catalogo.class)) {
            mockedStatic.when(Catalogo::getInstance).thenReturn(catalogoMock);

            // Chama o método a testar
            boolean result = UsuarioController.matchUser("vitor@email.com", "123");

            assertTrue(result); // Deve retornar true
        }
    }
    
    @Test
    public void testMatchUser_Failure() {
        Usuario userMock = mock(Usuario.class);
        when(userMock.getEmail()).thenReturn("outro@email.com");
        when(userMock.getSenha()).thenReturn("senhaerrada");

        Map<String, Usuario> usuarios = new HashMap<>();
        usuarios.put("outro", userMock);

        Catalogo catalogoMock = mock(Catalogo.class);
        when(catalogoMock.getUsuarios()).thenReturn(usuarios);

        try (MockedStatic<Catalogo> mockedStatic = mockStatic(Catalogo.class)) {
            mockedStatic.when(Catalogo::getInstance).thenReturn(catalogoMock);

            boolean result = UsuarioController.matchUser("vitor@email.com", "123");

            assertFalse(result); // Não deve encontrar o usuário
        }
    }
    
    // Testes nome
    @Test
    public void nomeValido_deveAceitarLetrasSimples() {
        assertTrue(UsuarioController.nomeValido("Maria"));
        assertTrue(UsuarioController.nomeValido("Joao"));
    }

    @Test
    public void nomeValido_deveAceitarEspacos() {
        assertTrue(UsuarioController.nomeValido("Joao da Silva"));
    }

    @Test
    public void nomeValido_naoDeveAceitarNumeros() {
        assertFalse(UsuarioController.nomeValido("Maria1"));
        assertFalse(UsuarioController.nomeValido("Joao 2"));
    }

    @Test
    public void nomeValido_naoDeveAceitarCaracteresEspeciais() {
        assertFalse(UsuarioController.nomeValido("Ana!"));
        assertFalse(UsuarioController.nomeValido("Pedro@Silva"));
        assertFalse(UsuarioController.nomeValido("Carlos#"));
        assertFalse(UsuarioController.nomeValido("Lúcia*"));
    }

    @Test
    public void nomeValido_deveRetornarTrueParaNomeComAcentos() {
        assertTrue(UsuarioController.nomeValido("José"));
        assertTrue(UsuarioController.nomeValido("Érica"));
    }

    @Test
    public void nomeValido_naoDeveAceitarNomeVazio() {
        assertTrue(UsuarioController.nomeValido(""));
    }

    @Test
    public void nomeValido_naoDeveAceitarCaracteresMisturados() {
        assertFalse(UsuarioController.nomeValido("Ana123!"));
        assertFalse(UsuarioController.nomeValido("Roberto_"));
    }
    
    // Testes senha
    @Test
    public void senhaValida_deveRetornarFalseParaMenorQue8() {
        assertFalse(UsuarioController.senhaValida("1234567"));
    }

    @Test
    public void senhaValida_deveRetornarTruePara8OuMais() {
        assertTrue(UsuarioController.senhaValida("12345678"));
        assertTrue(UsuarioController.senhaValida("senhaForte123"));
    }

    // Testes email
    @Test
    public void emailValido_deveRetornarTrueParaFormatoValido() {
        assertTrue(UsuarioController.emailValido("user@mail.com"));
        assertTrue(UsuarioController.emailValido("nome.sobrenome@dominio.org"));
    }

    @Test
    public void emailValido_deveRetornarFalseParaFormatoInvalido() {
        assertFalse(UsuarioController.emailValido("usuario@mail"));
        assertFalse(UsuarioController.emailValido("usu@ario@mail.com"));
        assertFalse(UsuarioController.emailValido("usuario.mail.com"));
        assertFalse(UsuarioController.emailValido("")); 
    }

    // Testes CPF
    @Test
    public void cpfValido_deveRetornarTrueParaCPFValido() {
        assertTrue(UsuarioController.cpfValido("529.982.247-25"));
        assertTrue(UsuarioController.cpfValido("52998224725"));
    }

    @Test
    public void cpfValido_deveRetornarFalseParaCPFInvalido() {
        assertFalse(UsuarioController.cpfValido("111.111.111-11"));
        assertFalse(UsuarioController.cpfValido("123.456.789-00"));
        assertFalse(UsuarioController.cpfValido("123456789"));
        assertFalse(UsuarioController.cpfValido("abc.def.ghi-jk"));
        assertFalse(UsuarioController.cpfValido(""));
    }
}