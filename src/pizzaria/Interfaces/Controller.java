/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces;

import java.util.ArrayList;
import java.util.Random;
import pizzaria.Interfaces.Frame.IFrame;
import pizzaria.Interfaces.Frame.IFrameAltera;


/**
 *
 * @author Guilh
 * @param <T>
 */
public interface Controller<T extends IFrame> {
    
    int ALEATORIO = new Random().nextInt(1000);
    
    void atualizar(T valor);
    
    void atualizar(IFrameAltera dado);
    
    ArrayList<?> pegar(int id);

    ArrayList<?> pegarVarios();

    String obterInformacoes(int id);

    void adicionar(T valor);
    
    boolean verificarChave(int id);
}
