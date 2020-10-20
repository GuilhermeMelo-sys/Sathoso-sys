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
public class TablePedido extends TableModelo{   
    
    public TablePedido(){
        super(
                new String[]{"ID", "ID Endereco", "Valor Final", "Está Pronto ?"},
                new ArrayList<Object[]>()
        );
    }
    
    public TablePedido(String[] colunas, List<Object[]> valores) {
        super(colunas, valores);
    }
}
