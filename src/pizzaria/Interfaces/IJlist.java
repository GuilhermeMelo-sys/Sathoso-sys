/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces;

import javax.swing.DefaultListModel;
import javax.swing.JList;
/**
 *
 * @author Pichau
 */
public interface IJlist {
    DefaultListModel getModelo();
    
    JList getLista();
    
    DefaultListModel getModeloPizzaDoPedido();
    
    JList getListaPizzaDoPedido();
}
