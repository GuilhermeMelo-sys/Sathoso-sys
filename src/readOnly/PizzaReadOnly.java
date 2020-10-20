/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readOnly;

import Enum.PizzasEnum;
import java.math.BigDecimal;
import pizzaria.Interfaces.ReadOnly;
import pizzaria.Model.Pizza;

/**
 *
 * @author Guilh
 */
public class PizzaReadOnly implements ReadOnly{
    protected int id;
    protected PizzasEnum tipoPizza;
    protected String sabor;
    protected BigDecimal valor;

    public int getId() {
        return id;
    }

    public String getSabor() {
        return sabor;
    }
    
    public PizzasEnum getTipoPizza(){
        return tipoPizza;
    }

    public BigDecimal getValor() {
        return valor;
    }
    
    public Pizza pegarPizza(Pizza pizza){
       pizza.setId(id);
       pizza.setSabor(sabor);
       pizza.setValor(valor);
       return pizza;
    }
    
    public Pizza pegarPizzaValorNaoAjustado(Pizza pizza){
       pizza.setId(id);
       pizza.setSabor(sabor);
       pizza.setValorAjustado(valor.doubleValue());
       return pizza;
    }

    public PizzaReadOnly(int id, PizzasEnum tipoPizza, String sabor, BigDecimal valor) {
        this.id = id;
        this.tipoPizza = tipoPizza;
        this.sabor = sabor;
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", getId(), getTipoPizza(), getSabor(),
                getValor());
    }

    public static PizzaReadOnly converterPizza(Pizza pizza) {
        PizzaReadOnly pro;
        
        pro = new PizzaReadOnly(pizza.getId(),
                PizzasEnum.valueOf(pizza.getClass().getSimpleName().replace("Pizza", " ").trim().toUpperCase()),
                pizza.getSabor(), pizza.getValor());
        
        return pro;
    }
}
