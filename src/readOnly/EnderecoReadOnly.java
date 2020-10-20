/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readOnly;

import pizzaria.Interfaces.ReadOnly;
import pizzaria.Model.Endereco;

/**
 *
 * @author Guilh
 */
public class EnderecoReadOnly extends Endereco implements ReadOnly{
    
    public EnderecoReadOnly(int id, String logradouro, String bairro, String cidade) {
        super(id, logradouro, bairro, cidade);
    }
    
}
