/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.View.Util;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Pichau
 */
public class ConfiguraPanel {

    public static void ChamarPanel(JFrame frame, JPanel chamado, JPanel PnMain) {
        chamado.setLocation(5, 5);
        frame.setLocation(200, 0);
        
        int x = chamado.getPreferredSize().width + 35;
        int y = chamado.getPreferredSize().height + 57;
        
        chamado.setSize(chamado.getPreferredSize());
        PnMain.setSize(chamado.getPreferredSize().height, chamado.getPreferredSize().width);
        frame.setSize(x, y);
        
        ConfigurarChamadaPnMain(chamado, PnMain);
    }

    private static void ConfigurarChamadaPnMain(JPanel chamado, JPanel PnMain) {
        PnMain.removeAll();
        PnMain.add(chamado, BorderLayout.CENTER);
        PnMain.revalidate();
        PnMain.repaint();
    }
}
