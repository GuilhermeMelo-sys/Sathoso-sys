/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilh
 * @param <T>
 */
public interface Relatorio<T> {
    List<ReadOnly> RELATORIO = new ArrayList<>();
    ArrayList<T> pegarLogs();
    
    void resetarLogs();
}
