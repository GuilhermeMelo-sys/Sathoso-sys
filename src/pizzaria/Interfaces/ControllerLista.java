/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces;

import java.util.ArrayList;
import pizzaria.Interfaces.Frame.IFrame;

/**
 *
 * @author Pichau
 * @param <T>
 */
public interface ControllerLista<T> {

    void retirarSelecionados();
    
    void LimparTodoModelo();
            
    ArrayList<T> PegarTodosElementos();
    
    T PegarElementoSelecionado();
    
    void AdicionarList(IFrame rd);
            
    T ConverterParaModelo(IFrame frame);
    
    T ConverterParaModelo(int id);
    
    T ConverterParaModelo(String campo);
    
    ArrayList<T> PegarValorTotalPedido();
}
