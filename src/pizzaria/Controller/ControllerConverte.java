/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Controller;

import javax.swing.JOptionPane;
import pizzaria.Interfaces.Frame.IFrameCadastroPedido;
import pizzaria.Interfaces.Frame.IFrameConsultaPedido;
import pizzaria.Model.Endereco;
import pizzaria.Model.Pedido;
import pizzaria.Interfaces.Frame.IFrameAltera;
import pizzaria.Interfaces.Frame.IFrameConsultaEndereco;
import pizzaria.Interfaces.Frame.IFramePizzaRelacionadaPedido;

/**
 *
 * @author Pichau
 */
public class ControllerConverte {

    public static Pedido converterPedido(IFrameCadastroPedido pedido) {
        return new Pedido(pedido.getIdPedido(),
                pedido.getEndereco(), pedido.getValorTotal(), pedido.getStatus());
    }

    public static Pedido converterPedido(IFrameConsultaPedido pedido) {
        return new Pedido(pedido.getIdPedido(),
                new ControllerEndereco().pegarUnico(pedido.getIdEndereco()),
                pedido.getValorTotal(), pedido.isPronto());
    }

    public static Pedido converterPedido(IFrameAltera<IFrameConsultaEndereco> form) {
        Pedido pedido = (Pedido) form.getObjeto();
        IFrameConsultaEndereco iEndereco = (IFrameConsultaEndereco) form;

        Endereco endereco = null;
        try {
            endereco = criarEndereco(iEndereco);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Valor de Endereço incorreto!\nErro:" + ex.getMessage());
        }

        return new Pedido(pedido.getId(), endereco,
                pedido.getValorTotal(), pedido.getStatus());
    }

    private static Endereco criarEndereco(IFrameConsultaEndereco iEndereco) throws NullPointerException {
        Endereco endereco = new Endereco(iEndereco.getId(), iEndereco.getLogradouro(),
                iEndereco.getBairro(), iEndereco.getCidade());
        return endereco;
    }
}
