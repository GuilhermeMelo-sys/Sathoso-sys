/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Controller;

import Dao.PizzaDao;
import java.util.*;
import pizzaria.Interfaces.Controller;
import pizzaria.Model.Pizza;
import javax.swing.JOptionPane;
import pizzaria.Interfaces.Dao;
import pizzaria.Interfaces.Frame.IFrameAltera;
import pizzaria.Interfaces.Frame.IFramePizza;
import readOnly.PizzaReadOnly;

/**
 *
 * @author Guilh
 */
public class ControllerPizza implements Controller<IFramePizza> {

    private Dao<PizzaReadOnly> pizzaDao;

    public ControllerPizza() {
        pizzaDao = new PizzaDao();
    }

    @Override
    public ArrayList<PizzaReadOnly> pegar(int id) {
        return pizzaDao.pegar(id);
    }

    @Override
    public ArrayList<PizzaReadOnly> pegarVarios() {
        return pizzaDao.pegarTudo();
    }

    public PizzaReadOnly pegarPorTipo(PizzaReadOnly pro) {
        return new PizzaDao().pegarPorTipo(pro);
    }

    public PizzaReadOnly pegarPorTipo(int id, int tipo) {
        return new PizzaDao().pegarPorTipo(id, tipo);
    }

    public PizzaReadOnly pegarUnico(int id) {
        return new PizzaDao().pegarUnico(id);
    }

    public ArrayList<PizzaReadOnly> pegarAgrupado() {
        return new PizzaDao().pegarDadosAgrupados();
    }

    @Override
    public String obterInformacoes(int id) {
        String info = "";
        for (PizzaReadOnly pro : pizzaDao.pegar(id)) {
            info = info.concat(pro.toString());
        }
        return info;
    }

    @Override
    public void adicionar(IFramePizza pizza) {
        configurarAdicionar(converterParaPizza(pizza));
        if (!ExisteLogs()) {
            JOptionPane.showMessageDialog(null, "Adicionados com sucesso!");
        }
    }

    public void configurarAdicionar(PizzaReadOnly pizza) {
        Pizza pi = Pizza.converter(pizza);
        pizzaDao.adicionar(PizzaReadOnly.converterPizza(pi));
    }

    private boolean ExisteLogs() {
        if (new PizzaDao().pegarLogs().size() <= 0) {
            return false;
        }
        exibirLogs();
        return true;
    }

    @Override
    public void atualizar(IFramePizza form) {
        pizzaDao.atualizarDado(converterParaPizza(form));
        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
    }

    @Override
    public void atualizar(IFrameAltera dado) {
        pizzaDao.atualizarDado(converterParaPizza((IFramePizza) dado));
        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
    }

    public void adicionarTodosPro(List<PizzaReadOnly> pro) {
        pro.forEach(c -> configurarAdicionar(c));
        if (!ExisteLogs()) {
            JOptionPane.showMessageDialog(null, "Adicionados com sucesso!");
        }
    }

    private void exibirLogs() {
        String logs = "Alguns dados não foram cadastrados: \n";
        logs = new PizzaDao().pegarLogs().stream().map((pizza) -> pizza.toString() + "\n").reduce(logs, String::concat);
        JOptionPane.showMessageDialog(null, logs.trim(), " tente cadastrar com um Id diferente.",
                JOptionPane.WARNING_MESSAGE);

        new PizzaDao().resetarLogs();
    }

    @Override
    public boolean verificarChave(int id) {
        return pizzaDao.vereficarChave(id);
    }

    private PizzaReadOnly converterParaPizza(IFramePizza form) {
        return new PizzaReadOnly(form.getIdPizza(), form.getTipoPizza(), form.getSabor(),
                form.getValor());
    }
}
