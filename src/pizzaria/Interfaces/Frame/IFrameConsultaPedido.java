/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces.Frame;

import java.math.BigDecimal;

/**
 *
 * @author Pichau
 */
public interface IFrameConsultaPedido extends IFrame{
    int getIdPedido();
    
    int getIdEndereco();
    
    BigDecimal getValorTotal();
    
    boolean isPronto();
    
}

