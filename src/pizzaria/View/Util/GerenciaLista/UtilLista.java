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
import readOnly.PizzaReadOnly;
import pizzaria.Interfaces.ControllerLista;
import pizzaria.Interfaces.Frame.IFrame;
import pizzaria.Interfaces.Frame.IFramePizza;
import pizzaria.Interfaces.IJlist;
import readOnly.PizzaReadOnlyPedido;

/**
 *
 * @author Guilh
 */
public class UtilLista implements ControllerLista<PizzaReadOnly> {

    protected DefaultListModel model;
    protected JList lista;

    public UtilLista(IJlist lista) {
        this.lista = lista.getLista();
        this.model = lista.getModelo();
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
    public PizzaReadOnly PegarElementoSelecionado() {
        if (this.lista.getSelectedIndex() == -1) {
            throw new ArrayIndexOutOfBoundsException("Não há nada selecionado!");
        }

        PizzaReadOnly pro = ConverterParaModelo(model.getElementAt(lista.getSelectedIndex()).toString());
        return pro;
    }

    @Override
    public ArrayList<PizzaReadOnly> PegarTodosElementos() {
        ArrayList<PizzaReadOnly> listPro = new ArrayList<>();

        for (int i = 0; i < lista.getModel().getSize(); i++) {
            listPro.add(ConverterParaModelo(model.getElementAt(i).toString()));
        }
        model.clear();
        return listPro;
    }

    protected void AdicionarNovoNaLista(IFramePizza form) {
        model.addElement(new PizzaReadOnly(form.getIdPizza(), form.getTipoPizza(),
                form.getSabor(), form.getValor()));
    }

    protected void AtualizarOExistente(int indiceElemento, IFramePizza form) {
        ConverterParaModelo(indiceElemento);
        model.clear();
        model.addElement(ConverterParaModelo(form));
    }

    @Override
    public void AdicionarList(IFrame frame) {
        IFramePizza form = (IFramePizza) frame;
        
        if(model.size() <= 0 || PegarIndice(form) == -1) {
            AdicionarNovoNaLista(form);}
        else
            AtualizarOExistente(PegarIndice(form), form);
    }

    @Override
    public PizzaReadOnly ConverterParaModelo(IFrame frame) {
        IFramePizza form = (IFramePizza) frame;

        if (String.valueOf(form.getIdPizza()).isEmpty()) {
            throw new NumberFormatException("Não existe pizza selecionada!");
        }

        return new PizzaReadOnly(form.getIdPizza(), form.getTipoPizza(), form.getSabor(),
                form.getValor());
    }

    @Override
    public PizzaReadOnly ConverterParaModelo(String campo) {
        String[] campos = campo.split(",");

        return new PizzaReadOnlyPedido(Integer.parseInt(campos[0]),
                PizzasEnum.valueOf(campos[1].trim()), campos[2], 
                BigDecimal.valueOf(Double.parseDouble(campos[3]))
        );
    }

    @Override
    public PizzaReadOnly ConverterParaModelo(int id) {
        return ConverterParaModelo(model.getElementAt(id).toString());
    }

    @Override
    public ArrayList<PizzaReadOnly> PegarValorTotalPedido() {
        Enumeration elementos = model.elements();
        ArrayList<PizzaReadOnly> pizzas = new ArrayList<>();

        while (elementos.hasMoreElements()) {
            pizzas.add(this.ConverterParaModelo(elementos.nextElement().toString()));
        }
        return pizzas;
    }

    protected int PegarIndice(IFramePizza frame) {
        
        for (int i = 0; i < model.size(); i++) {
            PizzaReadOnly pizza = ConverterParaModelo(model.getElementAt(i).toString());
            
            if (frame.getIdPizza()== pizza.getId() &&
                    frame.getTipoPizza().name().equals(pizza.getTipoPizza().name())){ 
                
                return i;
            }
        }
        return -1;
    }
}
