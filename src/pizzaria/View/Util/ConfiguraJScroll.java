/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.View.Util;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author Guilh
 */
public class ConfiguraJScroll {

    public static void configJscrollPane(JScrollPane jsb) {
        jsb.getVerticalScrollBar().setBackground(Color.white);
        jsb.getHorizontalScrollBar().setBackground(Color.white);

        jsb.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbDarkShadowColor = Color.gray.brighter();
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setBackground(Color.white);
                jbutton.setPreferredSize(new Dimension(20, 20));
                jbutton.setBorder(BorderFactory.createLineBorder(Color.gray.brighter(), 1));
                return jbutton;
            }

        });

        jsb.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbDarkShadowColor = Color.gray.brighter();
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setBackground(Color.white);
                jbutton.setPreferredSize(new Dimension(20, 20));
                jbutton.setBorder(BorderFactory.createLineBorder(Color.gray.brighter(), 1));
                return jbutton;
            }

        });
    }
}
