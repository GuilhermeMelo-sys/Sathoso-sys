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
public interface IFrameCobertura extends IFrame {
    int getIdCobertura();
    String getNomeCobertura();
    BigDecimal getValorCobertura();
}
