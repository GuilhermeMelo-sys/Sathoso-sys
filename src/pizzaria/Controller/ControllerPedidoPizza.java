/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Controller;

import Dao.PedidoPizzaDao;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import pizzaria.Interfaces.Controller;
import pizzaria.Interfaces.Frame.IFrameAltera;
import pizzaria.Interfaces.Frame.IFramePizzaRelacionadaPedido;
import pizzaria.Model.PedidoPizza;
import readOnly.PizzaReadOnlyPedido;

/**
 *
 * @author Guilh
 */
public class ControllerPedidoPizza implements Controller<IFramePizzaRelacionadaPedido> {

    private PedidoPizzaDao pedidoPizzaDao;

    public ControllerPedidoPizza() {
        pedidoPizzaDao = new PedidoPizzaDao();
    }

    public void atualizar(PedidoPizza valor) {
        pedidoPizzaDao.atualizarDado(valor);
        atualizarValorPedido(valor);
    }

    public void atualizarValorPedido(PedidoPizza valor) {
        ControllerPedido controllerPedido = new ControllerPedido();
        controllerPedido.atualizarValorDoPedido(pedidoPizzaDao.pegar(valor.getPedido()));
    }

    @Override
    public void atualizar(IFramePizzaRelacionadaPedido valor) {
        atualizar(converter(valor));
    }

    @Override
    public void atualizar(IFrameAltera dado) {
        atualizar(converter((IFramePizzaRelacionadaPedido) dado));
    }

    public PedidoPizza converter(IFramePizzaRelacionadaPedido valor) {
        return new PedidoPizza(criarPizzaDoPedido(valor),
                new ControllerCobertura().pegarUnico(valor.getIdCoberturaRelacionada()),
                valor.getIdPedido(), valor.getIdPedidoPizzas());
    }

    public PedidoPizza converter(IFramePizzaRelacionadaPedido form, PizzaReadOnlyPedido c) {
        return new PedidoPizza(c, form.getIdPedido(),
                ControllerCobertura.converter(form.getCobertura()),
                form.getQuantidade(), form.getIdPedidoPizzas());
    }

    private PizzaReadOnlyPedido criarPizzaDoPedido(IFramePizzaRelacionadaPedido valor) {
        return new PizzaReadOnlyPedido(valor.getIdPizza(),
                valor.getTipoPizza(), valor.getSabor(),
                valor.getValor(), valor.getQuantidade());
    }

    @Override
    public ArrayList<PedidoPizza> pegar(int id) {
        return pedidoPizzaDao.pegar(id);
    }

    public PedidoPizza pegarUnico(int id) {
        return pedidoPizzaDao.pegarUnico(id);
    }

    @Override
    public ArrayList<PedidoPizza> pegarVarios() {
        return pedidoPizzaDao.pegarTudo();
    }

    @Override
    public String obterInformacoes(int id) {
        return pedidoPizzaDao.pegar(id).toString();
    }

    @Override
    public void adicionar(IFramePizzaRelacionadaPedido valor) {
        adicionar(converter(valor));
    }

    public void adicionar(PedidoPizza valor) {
        pedidoPizzaDao.adicionar(valor);
    }

    public void adicionarTodos(List<PedidoPizza> pro) {
        pro.forEach((pp) -> {
            adicionar(pp);
        });
    }

    @Override
    public boolean verificarChave(int id) {
        return pedidoPizzaDao.vereficarChave(id);
    }

    public void deletarItem(IFramePizzaRelacionadaPedido pizzaPedido) {
        PedidoPizza pizza = pegarUnico(pizzaPedido.getIdPedidoPizzas());
        pedidoPizzaDao.deletarDado(pizza);
        atualizarValorPedido(pizza);
    }
 
    public BigDecimal calcularValorEConverter(IFramePizzaRelacionadaPedido pp, String tipo) {
        return converter(pp).calcularEConverterValor(tipo).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal ConverterValor(IFramePizzaRelacionadaPedido pp, String tipo) {
        return converter(pp).converterValor(tipo).setScale(2, RoundingMode.HALF_UP);
    }

    public ArrayList<PedidoPizza> criarListaDePizzas(IFramePizzaRelacionadaPedido form) {
        ArrayList<PedidoPizza> pizzas = new ArrayList<>();

        form.getPizzasDoModelo().forEach(c -> pizzas.add(converter(form, c)));
        return pizzas;
    }
}
