/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Controller;

import Dao.PedidoDao;
import Enum.ConverterPizzasEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import pizzaria.Interfaces.Controller;
import pizzaria.Interfaces.Dao;
import pizzaria.Interfaces.Frame.IFrameCadastroPedido;
import pizzaria.Interfaces.Frame.IFrameConsultaPedido;
import pizzaria.Interfaces.IPedido;
import pizzaria.Model.Pedido;
import pizzaria.Model.Pizza;
import readOnly.PizzaReadOnlyPedido;
import pizzaria.Interfaces.Frame.IFrameAltera;
import pizzaria.Interfaces.Frame.IFramePizzaRelacionadaPedido;
import pizzaria.Model.PedidoPizza;

/**
 *
 * @author Guilh
 */
public class ControllerPedido implements Controller<IPedido> {

    private Dao<Pedido> pedidoDao;

    public ControllerPedido() {
        pedidoDao = new PedidoDao();
    }

    @Override
    public void atualizar(IPedido pedido) {
        ControllerConverte.converterPedido((IFrameCadastroPedido) pedido);
    }

    public void atualizarPelaPizza(IFramePizzaRelacionadaPedido pizza) {
        atualizarPeloPedido(pegarUnico(pizza.getIdPedido()));
    }

    public void atualizarPeloPedido(Pedido pedido) {
        pedidoDao.atualizarDado(pedido);
    }

    public void atualizarValorDoPedido(List<PedidoPizza> pizzas) {
        configurarAtualizacao(pizzas);
    }

    private void configurarAtualizacao(List<PedidoPizza> pizzas) {
        Pedido pedido = pegarUnico(pizzas.get(0).getPedido());
        
        pedido.calcularValores(pizzas);
        
        atualizarPeloPedido(pedido);
    }

    @Override
    public void atualizar(IFrameAltera pedido) {
        atualizarPeloPedido(ControllerConverte.converterPedido(pedido));
    }

    public void atualizarEstado(IFrameConsultaPedido pedido) {
        Pedido pedidoAtualizado = ControllerConverte.converterPedido(pedido);
        pedidoAtualizado.changeStatus(true);

        atualizarPeloPedido(pedidoAtualizado);
    }

    @Override
    public ArrayList<Pedido> pegar(int id) {
        return pedidoDao.pegar(id);
    }

    public Pedido pegarUnico(int id) {
        return pedidoDao.pegarUnico(id);
    }

    public ArrayList<Pedido> pegar(boolean estado) {
        return new PedidoDao().pegar(estado);
    }

    @Override
    public ArrayList<Pedido> pegarVarios() {
        return pedidoDao.pegarTudo();
    }

    @Override
    public String obterInformacoes(int id) {
        String info = " ";
        for (Pedido pdr : pedidoDao.pegar(id)) {
            info = info.concat(pdr.toString() + "\n");
        }
        return info.trim();
    }

    @Override
    public void adicionar(IPedido pdro) {
        pedidoDao.adicionar(ControllerConverte.converterPedido((IFrameCadastroPedido) pdro));
    }

    @Override
    public boolean verificarChave(int chave) {
        return new PedidoDao().verificarChave(chave);
    }

    public BigDecimal calcularValor(PizzaReadOnlyPedido pro) {
        return pro.getValor().multiply(BigDecimal.valueOf(pro.getQuantidade()));
    }

    public BigDecimal converterValor(PizzaReadOnlyPedido pro, String tipoDesejado) {
        String tipoPizza = pro.getTipoPizza().name().toUpperCase().trim();

        return ConverterPizzasEnum.valueOf(tipoPizza).converter(
                Pizza.converter(pro), tipoDesejado);
    }

    public BigDecimal converterCalcularValor(PizzaReadOnlyPedido pro, String tipoDesejado) {
        pro.setValor(converterValor(pro, tipoDesejado));
        return calcularValor(pro);
    }

    public BigDecimal calcularValorTotal(List<PizzaReadOnlyPedido> lstPro) {
        return new Pedido().getCalculoValorTotal(lstPro);
    }

    public BigDecimal calcularValorTotalPedidoPizza(List<PedidoPizza> lstPp) {
        ArrayList<PizzaReadOnlyPedido> lstPro = new ArrayList<>();
        lstPp.forEach(c -> lstPro.add(c.getPizza()));
        return new Pedido().getCalculoValorTotal(lstPro);
    }
}
