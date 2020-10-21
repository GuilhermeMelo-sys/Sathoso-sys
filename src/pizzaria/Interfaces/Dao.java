/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import pizzaria.Interfaces.Frame.IFrame;

/**
 *
 * @author Guilh
 * @param <T>
 */
public interface Dao<T> {
    
    ArrayList<T> pegar(int id) ;
    
    public T pegarUnico(int id);
    
    ArrayList<T> pegarTudo();
    
    void adicionar(T objeto) ;
    
    void deletarDado(T objeto) ;
    
    void atualizarDado(T objeto);
    
    boolean vereficarChave(int id);
}
