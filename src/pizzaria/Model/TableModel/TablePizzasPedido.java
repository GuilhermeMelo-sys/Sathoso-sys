/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Model.TableModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pichau
 */
public class TablePizzasPedido extends TableModelo{
    
    public TablePizzasPedido(){
        super(
                new String[]{"ID Pizza", "ID Pedido", "ID Cobertura", "Sabor Pizza", "ID", "Quantidade", "Tipo Pizza:"},
                new ArrayList<Object[]>()
        );
    }
    
    public TablePizzasPedido(String[] colunas, List<Object[]> valores) {
        super(colunas, valores);
    }
}
