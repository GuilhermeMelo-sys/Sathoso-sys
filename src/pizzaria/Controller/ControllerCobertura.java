/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Controller;

import Dao.CoberturaDao;
import java.util.ArrayList;
import pizzaria.Interfaces.Controller;
import pizzaria.Interfaces.Dao;
import pizzaria.Interfaces.Frame.IFrameAltera;
import pizzaria.Interfaces.Frame.IFrameCobertura;
import pizzaria.Model.Cobertura;

/**
 *
 * @author Pichau
 */
public class ControllerCobertura implements Controller<IFrameCobertura>{
    private Dao<Cobertura> coberturaDao;
    
    public ControllerCobertura(){
        coberturaDao = new CoberturaDao();
    }
    
    @Override
    public void atualizar(IFrameCobertura valor) {
        coberturaDao.atualizarDado(converter(valor));
    }

    @Override
    public void atualizar(IFrameAltera dado) {
        coberturaDao.atualizarDado(converter((IFrameCobertura) dado));
    }

    @Override
    public ArrayList<Cobertura> pegar(int id) {
        return coberturaDao.pegar(id);
    }
    
    public Cobertura pegarUnico(int id){
        return coberturaDao.pegarUnico(id);
    }
    
    @Override
    public ArrayList<Cobertura> pegarVarios() {
        return coberturaDao.pegarTudo();
    }

    @Override
    public String obterInformacoes(int id) {
        return coberturaDao.pegarUnico(id).toString();
    }

    @Override
    public void adicionar(IFrameCobertura valor) {
        coberturaDao.adicionar(converter(valor));
    }

    @Override
    public boolean verificarChave(int id) {
        return coberturaDao.vereficarChave(id);
    }
    
    public static Cobertura converter(IFrameCobertura form){
        return new Cobertura(form.getIdCobertura(), form.getNomeCobertura(), form.getValorCobertura());
    }
}
