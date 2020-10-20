/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Model;

import java.math.BigDecimal;

/**
 *
 * @author Pichau
 */
public class Cobertura {
    private int id;
    private String nome;
    private BigDecimal valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor.setScale(2);
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString(){
        return String.format("%s, %s, %s", this.id, this.nome, this.valor);
    }

    public Cobertura(int id, String nome, BigDecimal valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }
    
}
