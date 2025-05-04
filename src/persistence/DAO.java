/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author jhiy2
 */
public interface DAO<T> {
    void salvar(T entity) throws IOException;
    Map<String, T> carregar() throws IOException;    
}
