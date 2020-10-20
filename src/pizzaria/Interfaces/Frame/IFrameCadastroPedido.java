/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces.Frame;

import Enum.PizzasEnum;
import java.math.BigDecimal;
import pizzaria.Model.Endereco;

/**
 *
 * @author Pichau
 */
public interface IFrameCadastroPedido extends IFramePizzaRelacionadaPedido {

    Endereco getEndereco();

    BigDecimal getValorTotal();
    
    PizzasEnum getTipoRelacionadoNaList();

    boolean getStatus();
}
