/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.View.Util;

import java.awt.Component;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import pizzaria.Controller.ControllerCobertura;
import pizzaria.Interfaces.Controller;

/**
 *
 * @author Guilh
 */
public class Utilidade {

    public static JRadioButton PegarRadioButtonHabilitado(JPanel panel) {
        JRadioButton jrb = null;
        for (Component comp : panel.getComponents()) {
            jrb = (JRadioButton) comp;
            if (jrb.isSelected()) {
                return jrb;
            }
        }
        return jrb;
    }

    public static boolean VerificarCamposVazios(JPanel PnMain) {
        for (Component component : PnMain.getComponents()) {
            if (component instanceof JTextField) {
                JTextField txt = (JTextField) component;
                if (txt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Existe Campos vazios!");
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<String> ProcurarCheckBoxTipoPizza(JPanel pnTipoPizza) {
        ArrayList<String> tipoPizza = new ArrayList<>();

        for (Component comp : pnTipoPizza.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox chkTipoPizza = (JCheckBox) comp;
                if (chkTipoPizza.isSelected()) {
                    tipoPizza.add(chkTipoPizza.getText());
                }
            }
        }
        return tipoPizza;
    }

    public static void AumentarValor(JLabel quantidade, int maximo) {
        int quantidadePedido = Integer.valueOf(quantidade.getText());

        if (quantidadePedido < maximo) {
            quantidade.setText(String.valueOf(
                    Integer.valueOf(quantidade.getText()) + 1));
        }
    }

    public static void DiminuirValor(JLabel quantidade) {
        if (Integer.valueOf(quantidade.getText()) > 1) {
            quantidade.setText(String.valueOf(
                    Integer.valueOf(quantidade.getText()) - 1));
        }
    }

    public static String TextoChave(Controller controller) {
        int chave = Math.abs(new Random().nextInt(10000));
        while (controller.verificarChave(chave)) {
            chave += 1;
        }
        return String.valueOf(chave);
    }

    public static int CriarOpcao(String enunciado) throws HeadlessException {
        String[] options = {"Sim", "Não"};

        int valor = JOptionPane.showOptionDialog(null, enunciado, "Informação: ?",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                options, options[0]);

        return valor;
    }

    public static String[] atualizarLabelsCobertura(JComboBox cbo) {
            return configurarLabelsCobertura(cbo);
    }

    private static String[] configurarLabelsCobertura(JComboBox cbo) {
        int id = Integer.parseInt(cbo.getSelectedItem().toString());
        ControllerCobertura controllerCobertura = new ControllerCobertura();

        return montarTextos(controllerCobertura, id);
    }

    private static String[] montarTextos(ControllerCobertura controllerCobertura, int id) {
        String textos[] = new String[3];
        
        textos[0] = controllerCobertura.pegarUnico(id).getNome();
        textos[1] = controllerCobertura.pegarUnico(id).getValor().toString();
        textos[2] = String.valueOf(id);
        return textos;
    }
}
