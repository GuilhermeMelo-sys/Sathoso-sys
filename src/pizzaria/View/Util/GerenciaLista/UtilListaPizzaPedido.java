/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.View.Util.GerenciaLista;

import Enum.PizzasEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import pizzaria.Controller.ControllerCobertura;
import readOnly.PizzaReadOnlyPedido;
import pizzaria.Interfaces.ControllerLista;
import pizzaria.Interfaces.Frame.IFrame;
import pizzaria.Interfaces.Frame.IFrameCadastroPedido;
import pizzaria.Interfaces.Frame.IFramePizzaDoPedido;
import pizzaria.Interfaces.IJlist;

/**
 *
 * @author Pichau
 */
public class UtilListaPizzaPedido implements ControllerLista<PizzaReadOnlyPedido> {

    private DefaultListModel model;
    private JList lista;

    public UtilListaPizzaPedido(IJlist formulario) {
        this.model = formulario.getModeloPizzaDoPedido();
        this.lista = formulario.getListaPizzaDoPedido();
        this.lista.setModel(model);
    }

    @Override
    public void retirarSelecionados() {
        for (int valor : lista.getSelectedIndices()) {
            if (valor >= model.getSize()) {
                valor = valor - model.getSize();
            }
            model.remove(valor);
        }
    }

    @Override
    public void LimparTodoModelo() {
        if (lista.getSelectedIndices().length == 0) {
            model.clear();
        }
    }

    @Override
    public ArrayList<PizzaReadOnlyPedido> PegarTodosElementos() {
        ArrayList<PizzaReadOnlyPedido> listPp = new ArrayList<>();

        for (int i = 0; i < lista.getModel().getSize(); i++) {
            listPp.add(ConverterParaModelo(model.getElementAt(i).toString()));
        }

        return listPp;
    }

    public void AdicionarNovoNaLista(IFrameCadastroPedido form) {
        model.addElement(TraduzirFormulario(form));
    }

    public void AtualizarOExistente(int indiceElemento, IFramePizzaDoPedido form) {
        PizzaReadOnlyPedido pizza = ConverterParaModelo(indiceElemento);
        pizza.setQuantidade(form.getQuantidade() + pizza.getQuantidade());
        pizza.aumentarValor(form.getValor());

        model.remove(indiceElemento);
        model.addElement(pizza);
    }

    @Override
    public void AdicionarList(IFrame frame) {
        IFrameCadastroPedido form = (IFrameCadastroPedido) frame;
        int indice = PegarIndice(form);

        if (model.size() <= 0 || indice == -1) {
            AdicionarNovoNaLista(form);
        } else {
            AtualizarOExistente(indice, form);
        }
    }

    @Override
    public PizzaReadOnlyPedido ConverterParaModelo(IFrame frame) {
        IFrameCadastroPedido form = (IFrameCadastroPedido) frame;

        return TraduzirFormulario(form);
    }

    @Override
    public PizzaReadOnlyPedido ConverterParaModelo(int id) {
        return ConverterParaModelo(model.getElementAt(id).toString());
    }

    @Override
    public PizzaReadOnlyPedido ConverterParaModelo(String campo) {
        String[] campos = campo.split(",");
        return new PizzaReadOnlyPedido(Integer.parseInt(campos[0]), PizzasEnum.valueOf(campos[1].trim()),
                campos[2], BigDecimal.valueOf(Double.parseDouble(campos[3])),
                Integer.parseInt(campos[4].trim())
        );
    }

    @Override
    public ArrayList<PizzaReadOnlyPedido> PegarValorTotalPedido() {
        Enumeration elements = model.elements();
        ArrayList<PizzaReadOnlyPedido> pizzas = new ArrayList<>();

        while (elements.hasMoreElements()) {
            pizzas.add(ConverterParaModelo(elements.nextElement().toString()));
        }
        return pizzas;
    }

    private PizzaReadOnlyPedido TraduzirFormulario(IFrameCadastroPedido form) {
        PizzaReadOnlyPedido pizzaPedido = new PizzaReadOnlyPedido(form.getIdPizza(),
                form.getTipoRelacionadoNaList(), form.getSabor().trim(), form.getValor(),
                form.getQuantidade(), ControllerCobertura.converter(form.getCobertura())
        );
        return pizzaPedido;
    }

    @Override
    public PizzaReadOnlyPedido PegarElementoSelecionado() {
        String linha = model.getElementAt(lista.getSelectedIndex()).toString();

        return ConverterParaModelo(linha);
    }

    private int PegarIndice(IFramePizzaDoPedido frame) {
        for (int i = 0; i < model.size(); i++) {
            PizzaReadOnlyPedido modelo = ConverterParaModelo(model.getElementAt(i).toString());
            if (ModeloIgual(frame, modelo)) {
                return i;
            }
        }
        return -1;
    }

    private boolean ModeloIgual(IFramePizzaDoPedido frame, PizzaReadOnlyPedido modelo) {
        return frame.getIdPizza()== modelo.getId()
                && frame.getTipoPizza().name().equals(modelo.getTipoPizza().name());
    }

}
