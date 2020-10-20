/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

import java.math.BigDecimal;
import pizzaria.Model.Pizza;
import pizzaria.Model.Pizzas.PizzaFamiliar;
import pizzaria.Model.Pizzas.PizzaIndividual;
import pizzaria.Model.Pizzas.PizzaRegular;

/**
 *
 * @author Guilh
 */
public enum ConverterPizzasEnum {
    INDIVIDUAL {
        @Override
        public BigDecimal converter(Pizza pizza, String tipo) {
            if(tipo.toUpperCase().equals("REGULAR")){
                return new PizzaIndividual().converterPizzaRegular(pizza);}
            if(tipo.toUpperCase().equals("FAMILIAR")) 
                return new PizzaIndividual().converterPizzaFamilia(pizza);
            else
                return pizza.getValor();
        }
    },
    REGULAR {
        @Override
        public BigDecimal converter(Pizza pizza, String tipo) {
            if(tipo.toUpperCase().equals("FAMILIAR"))
                return new PizzaRegular().converterPizzaFamilia(pizza);
            if(tipo.toUpperCase().equals("INVIDUAL"))
                return new PizzaRegular().converterPizzaIndividual(pizza);
            else    
                return pizza.getValor();
        }
    },
    FAMILIAR {
        @Override
        public BigDecimal converter(Pizza pizza, String tipo) {
            if(tipo.toUpperCase().equals("REGULAR"))
                return new PizzaFamiliar().converterPizzaRegular(pizza);
            if(tipo.toUpperCase().equals("INVIDUAL"))
                return new PizzaFamiliar().converterPizzaIndividual(pizza);
            else
                return pizza.getValor();
        }
    };
        
    public abstract BigDecimal converter(Pizza pizza, String tipo);
}
