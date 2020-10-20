package pizzaria.Model;

import java.math.BigDecimal;
import java.util.List;
import readOnly.PizzaReadOnlyPedido;

public class Pedido {

    private int id;
    private Endereco endereco;
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private boolean isFinish;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return this.id;
    }

    public boolean getStatus() {
        return this.isFinish;
    }

    public void changeStatus(boolean status) {
        this.isFinish = status;
    }

    public Pedido(int id, Endereco endereco, boolean finish) {
        this.id = id;
        this.endereco = endereco;
        this.isFinish = finish;
    }

    public Pedido(int id, Endereco endereco, BigDecimal valorFinal, boolean finish) {
        this.id = id;
        this.endereco = endereco;
        this.valorTotal = valorFinal;
        this.isFinish = finish;
    }

    public Pedido() {
    }

    public BigDecimal getCalculoValorTotal(List<PizzaReadOnlyPedido> pizzas) {
        pizzas.forEach(c -> {
            valorTotal = valorTotal.add(c.getValor());
        });
        return valorTotal;
    }

    public void calcularValores(List<PedidoPizza> pizzas) {
        BigDecimal valorDaLista = BigDecimal.ZERO;
        for (PedidoPizza pizza : pizzas) {
            valorDaLista = valorDaLista.add(pizza.calcularValor());
        }
        this.setValorTotal(valorDaLista);
    }

    public void setValorTotal(BigDecimal valor) {
        this.valorTotal = valor;
    }

    public BigDecimal getValorTotal() {
        return this.valorTotal;
    }
}
