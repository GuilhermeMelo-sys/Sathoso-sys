/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces.Frame;

import pizzaria.Interfaces.IPedido;

/**
 *
 * @author Pichau
 */
public interface IFrameConsultaEndereco extends IPedido {

    int getId();

    String getLogradouro();

    String getBairro();

    String getCidade();
}
