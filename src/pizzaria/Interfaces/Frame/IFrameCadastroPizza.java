/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces.Frame;

import Enum.PizzasEnum;
import java.util.ArrayList;

/**
 *
 * @author Pichau
 */
public interface IFrameCadastroPizza extends IFramePizza{
    
    ArrayList<PizzasEnum> getTiposPizza();
    
    void percorrerTipo(String tipo);
}
