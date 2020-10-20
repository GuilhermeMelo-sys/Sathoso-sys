/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.View.Util.GerenciaLista;

import pizzaria.Interfaces.Frame.IFrame;
import pizzaria.Interfaces.Frame.IFrameCadastroPizza;
import pizzaria.Interfaces.IJlist;

/**
 *
 * @author Pichau
 */
public class UtilListaCadastroPizza extends UtilLista {

    public UtilListaCadastroPizza(IJlist lista) {
        super(lista);
    }

    @Override
    public void AdicionarList(IFrame frame) {
        IFrameCadastroPizza form = (IFrameCadastroPizza) frame;

        form.getTiposPizza().stream().map((c) -> {
            form.percorrerTipo(c.name());
            return c;
        }).forEachOrdered((_item) -> {
            super.AdicionarList(form);
        });
    }

}
