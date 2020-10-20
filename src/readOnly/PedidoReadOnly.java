/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readOnly;
import java.math.BigDecimal;
import pizzaria.Interfaces.ReadOnly;
import pizzaria.Model.Endereco;
import pizzaria.Model.Pedido;

/**
 *
 * @author Guilh
 */
public class PedidoReadOnly extends Pedido implements ReadOnly {
    public PedidoReadOnly(int id, Endereco endereco, boolean finish){
        super(id, endereco, finish);
    }
    public PedidoReadOnly(int id, Endereco endereco, BigDecimal valorFinal, boolean finish){
        super(id, endereco, valorFinal, finish);
    }
}
