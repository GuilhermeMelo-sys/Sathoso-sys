/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

import pizzaria.Model.Pizza;
import pizzaria.Model.Pizzas.*;

/**
 *
 * @author Guilh
 */
public enum PizzasEnum {
    INDIVIDUAL(0) {
        @Override
        public PizzaIndividual pegarClasse() {
           return new PizzaIndividual();
        }

    }, FAMILIAR(1) {
        @Override
        public PizzaFamiliar pegarClasse() {
            return new PizzaFamiliar();
        }
    }, REGULAR(2) {
        @Override
        public PizzaRegular pegarClasse() {
            return new PizzaRegular(); 
        }
    };
    public int indiceTipo;
    
    PizzasEnum(int indiceTipo){
        this.indiceTipo = indiceTipo;
    }
    public abstract Pizza pegarClasse();
}
