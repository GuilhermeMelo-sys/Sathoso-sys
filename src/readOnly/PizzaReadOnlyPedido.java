/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readOnly;

import Enum.PizzasEnum;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import pizzaria.Model.Cobertura;
import pizzaria.Model.PedidoPizza;

/**
 *
 * @author Guilh
 */
public class PizzaReadOnlyPedido extends PizzaReadOnly{
    private int quantidade;
    private int cobertura;

    public int getCobertura() {
        return cobertura;
    }

    public void setCobertura(int cobertura) {
        this.cobertura = cobertura;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
    
    public void setValor(BigDecimal valor){
        this.valor = valor;
    }
    
    public void aumentarValor(BigDecimal valor){
        this.valor = this.valor.add(valor).setScale(2, RoundingMode.HALF_UP);
    }
    
    public PizzaReadOnlyPedido(int id, PizzasEnum tipoPizza, String sabor, BigDecimal valor) {
        super(id, tipoPizza, sabor, valor);
    }
    
    public PizzaReadOnlyPedido(int id, PizzasEnum tipoPizza, String sabor, BigDecimal valor, int quantidade, int cobertura) {
        super(id, tipoPizza, sabor, valor);
        this.quantidade = quantidade;
        this.cobertura = cobertura;
    }
    
    public PizzaReadOnlyPedido(PizzaReadOnly pizza, int quantidade, int cobertura) {
        super(pizza.getId(), pizza.getTipoPizza(), pizza.getSabor(), pizza.getValor());
        this.quantidade = quantidade;
        this.cobertura = cobertura;
    }
    
    public static ArrayList<PizzaReadOnlyPedido> converterLista(List<PizzaReadOnly> lista){
        ArrayList<PizzaReadOnlyPedido> pizzaConversao = new ArrayList<>();
        lista.forEach(c -> pizzaConversao.add(new PizzaReadOnlyPedido(c.getId(), c.getTipoPizza(), c.getSabor(), c.getValor())));
        return pizzaConversao;
    }
    
    public boolean valorValido(){
        return (valor.doubleValue() % quantidade) == 0;
    }
    
    public BigDecimal pegarValorComQuantidade(){
        return this.valor.multiply(BigDecimal.valueOf(quantidade));
    }
  
    @Override
    public String toString(){
        return String.format("%s, %s, %s, %s, %s", this.getId(), this.getTipoPizza(),
                this.getSabor(), this.getValor(), this.getQuantidade());
    }
}
