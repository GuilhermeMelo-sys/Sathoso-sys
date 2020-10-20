/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Model;

import Enum.ConverterPizzasEnum;
import java.math.BigDecimal;
import java.util.List;
import readOnly.PizzaReadOnly;
import readOnly.PizzaReadOnlyPedido;

/**
 *
 * @author Guilh
 */
public class PedidoPizza {

    private PizzaReadOnlyPedido pizzasPedido;
    private int idPedido;
    private Cobertura cobertura;
    private int id;

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public int getPedido() {
        return idPedido;
    }

    public void setPedido(int pedido) {
        this.idPedido = pedido;
    }

    public PizzaReadOnlyPedido getPizza() {
        return pizzasPedido;
    }

    public void setPizza(PizzaReadOnlyPedido Pizza) {
        this.pizzasPedido = Pizza;
    }

    public int getId() {
        return id;
    }

    public BigDecimal calcularValores(List<PedidoPizza> pizzas) {
        BigDecimal valorDaLista = BigDecimal.ZERO;
        for(PedidoPizza pizza : pizzas){
            valorDaLista = valorDaLista.add(pizza.calcularValor());
        }
        return valorDaLista;
    }

    public BigDecimal calcularValor() {
        return getCobertura().getValor().add(getPizza().getValor()
                .multiply(BigDecimal.valueOf(getPizza().getQuantidade())));
    }

    public BigDecimal calcularEConverterValor(String tipo) {
        BigDecimal valorConvertido = converterValor(tipo);

        return getCobertura().getValor().add(
                valorConvertido.multiply(BigDecimal.valueOf(getPizza().getQuantidade())));
    }

    public BigDecimal converterValor(String tipo) {
        Pizza pizza = Pizza.converter(this);
        return ConverterPizzasEnum.valueOf(getPizza().getTipoPizza().name()).converter(pizza, tipo);
    }

    public PedidoPizza(PizzaReadOnly pizza, int pedido, Cobertura cobertura, int quantidade, int id) {
        this.pizzasPedido = new PizzaReadOnlyPedido(pizza.getId(), pizza.getTipoPizza(),
                pizza.getSabor(), pizza.getValor(), quantidade);
        this.id = id;
        this.idPedido = pedido;
        this.cobertura = cobertura;
    }

    public PedidoPizza(PizzaReadOnlyPedido pizza, Cobertura cobertura, int pedido, int id) {
        this.pizzasPedido = pizza;
        this.id = id;
        this.idPedido = pedido;
        this.cobertura = cobertura;
    }
}
