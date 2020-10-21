/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Controller;

import Dao.CoberturaDao;
import Dao.EnderecoDao;
import java.util.ArrayList;
import pizzaria.Interfaces.Controller;
import pizzaria.Interfaces.Dao;
import pizzaria.Interfaces.Frame.IFrameAltera;
import pizzaria.Interfaces.Frame.IFrameConsultaEndereco;
import pizzaria.Model.Endereco;
import readOnly.EnderecoReadOnly;

/**
 *
 * @author Guilh
 */
public class ControllerEndereco implements Controller<IFrameConsultaEndereco>{
    private Dao<Endereco> enderecoDao;
    EnderecoReadOnly ero = null;
    
    public ControllerEndereco(){
        enderecoDao = new EnderecoDao();
    }
    
    @Override
    public void atualizar(IFrameConsultaEndereco valor) {
        enderecoDao.atualizarDado(converter(valor));
    }
    
    @Override
    public void atualizar(IFrameAltera dado) {
        enderecoDao.atualizarDado((Endereco) dado.getObjeto());
    }
    
    public Endereco converter(IFrameConsultaEndereco valor){
       return new Endereco(valor.getId(), valor.getLogradouro(),
               valor.getBairro(), valor.getCidade());
    }
    
    @Override
    public ArrayList<Endereco> pegar(int id) {
        return enderecoDao.pegar(id);
    }

    @Override
    public ArrayList<Endereco> pegarVarios() {
        return enderecoDao.pegarTudo();
    }

    @Override
    public String obterInformacoes(int id) {
        String info = "";
        
        for(Endereco erol : pegar(id)){
            info = info.concat(ero.toString()+ "\n");
        } 
        return info;
    }

    @Override
    public void adicionar(IFrameConsultaEndereco eroParam) {
        enderecoDao.adicionar(converter(eroParam));
    }

    @Override
    public boolean verificarChave(int id) {
        return enderecoDao.vereficarChave(id);
    }

    public Endereco pegarUnico(int idEndereco) {
        return enderecoDao.pegarUnico(idEndereco);
    }
}
