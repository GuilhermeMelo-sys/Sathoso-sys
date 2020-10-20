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
public class PizzaRegular extends Pizza {
    
    public PizzaRegular(int id, String sabor, double valor) {
        super(id, sabor, valor);
    }
    
    public PizzaRegular(){}
    
    //transforma o valor no cadastro, pois é cadastrado como uma pizza individual ^^
    @Override
    public void setValor(BigDecimal valor){
        if(valor.doubleValue() <= 0) 
            throw new IllegalArgumentException("Valor abaixo do valor necessário.");
        
        super.valor = AjustarValor(valor);
    }

    private BigDecimal AjustarValor(BigDecimal valor) {
        return valor.divide(BigDecimal.valueOf(0.5555555555555556), RoundingMode.HALF_UP);   
    }
    
    @Override
    public BigDecimal converterPizzaRegular(Pizza pizza) {
        return pizza.getValor().setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal converterPizzaFamilia(Pizza pizza) {
        return pizza.getValor().multiply(BigDecimal.valueOf(1.222222222222222));
    }

    @Override
    public BigDecimal converterPizzaIndividual(Pizza pizza) {
        
        return pizza.getValor().divide(BigDecimal.valueOf(0.5555555555555556), RoundingMode.HALF_UP);
    }
}
