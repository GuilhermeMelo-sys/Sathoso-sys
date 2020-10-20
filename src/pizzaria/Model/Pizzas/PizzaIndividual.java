/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Model.Pizzas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import pizzaria.Model.Pizza;

/**
 *
 * @author Guilh
 */
public class PizzaIndividual extends Pizza{
    
    public PizzaIndividual(int id, String sabor, double valor) {
        super(id, sabor, valor);
    }
    
    public PizzaIndividual(){}    
    
    @Override
    public void setValor(BigDecimal valor){
        if(valor.doubleValue() <= 0 ) 
            throw new IllegalArgumentException("Valor abaixo de zero,"
                    + " portanto invalido.");
        
        this.valor = valor;
    }
    
    @Override
    public BigDecimal converterPizzaRegular(Pizza pizza) {    
        return pizza.getValor().multiply(BigDecimal.valueOf(1.8));
    }

    @Override
    public BigDecimal converterPizzaFamilia(Pizza pizza) {
        return pizza.getValor().multiply(BigDecimal.valueOf(2.2));
    }

    @Override
    public BigDecimal converterPizzaIndividual(Pizza pizza) {
        return pizza.getValor().setScale(2, RoundingMode.HALF_UP);
    }
}
