/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.View.Util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 *
 * @author Guilh
 */
public class ColorJMenu {
    public static void customizeMenuBar(JMenuBar menuBar, Color color, Color fore) {

        menuBar.setUI(new BasicMenuBarUI() {

            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(color);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }

        });

        MenuElement[] menus = menuBar.getSubElements();

        for (MenuElement menuElement : menus) {

            JMenu menu = (JMenu) menuElement.getComponent();
            changeComponentColors(menu, color, fore);
            menu.setOpaque(true);

            MenuElement[] menuElements = menu.getSubElements();

            for (MenuElement popupMenuElement : menuElements) {

                JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
                popupMenu.setBorder(null);

                MenuElement[] menuItens = popupMenuElement.getSubElements();

                for (MenuElement menuItemElement : menuItens) {

                    JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
                    changeComponentColors(menuItem, color, fore);
                    menuItem.setOpaque(true);

                }
            }
        }
    }

    private static void changeComponentColors(Component comp, Color back, Color fore) {
        comp.setBackground(back);
        comp.setForeground(fore);
    }
}
