/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import catalogo.Catalogo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Notificacao;

/**
 *
 * @author vitor
 */
public class NotificacaoController {
    public static List<Notificacao> getTodasNotificacoes() {
        Catalogo catalogo = Catalogo.getInstance();
        Map<String, Notificacao> notificacoes = catalogo.getNotificacoes();

        return new ArrayList<>(notificacoes.values());
    }
}
