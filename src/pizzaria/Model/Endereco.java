/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Model;

/**
 *
 * @author Guilh
 */
public class Endereco {
    private int id;
    private String logradouro;
    private String bairro;
    private String cidade;
    
    public int getId(){
        return this.id;
    }
    
    public String getLogradouro(){
        return this.logradouro;
    }
    
    public void setLogradouro(String logradouro){
        this.logradouro = logradouro;
    }
    
    public String getBairro(){
        return this.bairro;
    }
    
    public void setBairro(String bairro){
        this.bairro = bairro;
    }
    
    public String getCidade(){
        return this.cidade;
    }
    
    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public Endereco(int id, String logradouro, String bairro, String cidade) {
        this.id = id;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
    }
    
    @Override
    public String toString(){
        return String.format("%d, %s, %s, %s", this.id, 
                    this.logradouro, this.bairro, this.cidade);
    }
}
