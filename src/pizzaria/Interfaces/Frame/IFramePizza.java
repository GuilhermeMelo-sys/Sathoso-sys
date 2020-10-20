/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces.Frame;

import Enum.PizzasEnum;
import java.math.BigDecimal;
import pizzaria.Interfaces.IPedido;

/**
 *
 * @author Pichau
 */
public interface IFramePizza extends IPedido{
    int getIdPizza();
    
    PizzasEnum getTipoPizza();
    
    String getSabor();
    
    BigDecimal getValor();
}
