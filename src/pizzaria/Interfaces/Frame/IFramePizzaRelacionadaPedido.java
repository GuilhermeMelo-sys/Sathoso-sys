/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces.Frame;

import java.util.ArrayList;
import readOnly.PizzaReadOnlyPedido;

/**
 *
 * @author Pichau
 */
public interface IFramePizzaRelacionadaPedido extends IFramePizzaDoPedido {

    int getIdPedidoPizzas();

    int getIdCoberturaRelacionada();
    
    IFrameCobertura getCobertura();

    ArrayList<PizzaReadOnlyPedido> getPizzasDoModelo();

    int getIdPedido();
}
