/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Model;

import Enum.ConverterPizzasEnum;
import Enum.PizzasEnum;
import java.math.*;
import readOnly.PizzaReadOnly;

/**
 *
 * @author Guilh
 */
public abstract class Pizza {

    private int id;
    private String sabor;
    protected BigDecimal valor;

    public double pegarValorTotal(int quantidade) {
        return valor.multiply(BigDecimal.valueOf(quantidade)).doubleValue();
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public abstract void setValor(BigDecimal valor);

    public void setValorAjustado(double valor) {
        this.valor = BigDecimal.valueOf(valor);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Pizza converter(PizzaReadOnly pro) {
        Pizza pizza = PizzasEnum.valueOf(pro.getTipoPizza().name().
                trim().toUpperCase()).pegarClasse();

        pizza.setId(pro.getId());
        pizza.setSabor(pro.getSabor());
        pizza.setValor(pro.getValor());

        return pizza;
    }

    public static Pizza converter(PedidoPizza pedidoPizza) {
        Pizza pizza = PizzasEnum.valueOf(pedidoPizza.getPizza().getTipoPizza().name()).pegarClasse();

        pizza.setId(pedidoPizza.getPizza().getId());
        pizza.setSabor(pedidoPizza.getPizza().getSabor());
        pizza.setValor(pedidoPizza.getPizza().getValor());

        return pizza;
    }
    
    public void aumentarValor(BigDecimal valor){
        this.valor = this.valor.add(valor);
    }

    public BigDecimal converterValor(String tipoVelho, String tipo) {
        return ConverterPizzasEnum.valueOf(tipoVelho).converter(this, tipo);
    }

    public abstract BigDecimal converterPizzaRegular(Pizza pizza);

    public abstract BigDecimal converterPizzaFamilia(Pizza pizza);

    public abstract BigDecimal converterPizzaIndividual(Pizza pizza);

    public Pizza(int id, String sabor, double valor) {
        this.id = id;
        this.valor = BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP);
        this.sabor = sabor;
    }

    public Pizza() {
    }

}
