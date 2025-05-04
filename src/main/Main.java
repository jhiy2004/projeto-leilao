/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import ui.MainUI;

/**
 *
 * @author jhiy2
 */
public class Main {
    public static void main(String args[]){
        MainUI mainUI = new MainUI();
        mainUI.setVisible(true);
        mainUI.setTitle("Sistema Gerenciador de Leilões");
        mainUI.toFront();
    }
}
