/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.View.SubtelasPedido;

import Enum.PizzasEnum;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import pizzaria.Controller.ControllerCobertura;
import pizzaria.Controller.ControllerPedidoPizza;
import pizzaria.Controller.ControllerPizza;
import pizzaria.Interfaces.Frame.IFrameAltera;
import pizzaria.Interfaces.Frame.IFrameCobertura;
import pizzaria.Interfaces.Frame.IFramePizzaRelacionadaPedido;
import pizzaria.Model.Cobertura;
import pizzaria.Model.PedidoPizza;
import pizzaria.View.Util.Utilidade;
import readOnly.PizzaReadOnlyPedido;

/**
 *
 * @author Pichau
 */
public class FrameAlterarPedidoPizza extends javax.swing.JPanel implements IFramePizzaRelacionadaPedido, IFrameCobertura, IFrameAltera<PedidoPizza> {

    /**
     * Creates new form FrameAlterarPedidoPizza
     */
    private boolean alterado;
    private PedidoPizza pizza;
    private ControllerPedidoPizza controller;
    private ControllerPizza controllerPizza;
    private IFramePizzaRelacionadaPedido frame;
    private IFrameCobertura cobertura;
    private ControllerCobertura controllerCobertura;

    public FrameAlterarPedidoPizza() {
        initComponents();
    }

    public FrameAlterarPedidoPizza(IFramePizzaRelacionadaPedido frame) {
        controllerPizza = new ControllerPizza();
        controllerCobertura = new ControllerCobertura();
        controller = new ControllerPedidoPizza();

        initComponents();

        cboTipoPizza.setBackground(Color.white);
        cboPizzas.setBackground(Color.white);
        this.frame = frame;

        CarregarCobertura();
        CarregarDados();

        AtualizarCampos();
        DescrevePizza(frame);
    }

    private void CarregarDados() {
        controllerPizza.pegarAgrupado().forEach(c -> cboPizzas.addItem(String.valueOf(c.getId())));
    }

    private void CarregarCobertura() {
        this.controllerCobertura.pegarVarios().forEach(c
                -> cboCobertura.addItem(String.valueOf(c.getId()))
        );
    }

    private void CarregarTipos(int id) {
        cboTipoPizza.removeAllItems();

        controllerPizza.pegar(id).forEach((c) -> {
            cboTipoPizza.addItem(c.getTipoPizza().name());
        });
    }

    private void AtualizarPreco() {
        try {
            ConfigurarValor(Integer.parseInt(cboPizzas.getSelectedItem().toString()));

            BigDecimal valor = controller.calcularValorEConverter(this,
                    cboTipoPizza.getSelectedItem().toString());
            txtValor.setText(valor.toString());
            pizza.getPizza().setValor(valor);

        } catch (NullPointerException ex) {
        }
    }

    private void ConfigurarQuantidade() {
        if (txtQuantidade.getText().isEmpty()
                || txtQuantidade.getText().chars().anyMatch(c -> Character.isLetter(c))) {
            this.txtQuantidade.setText("1");
        } else {
            this.txtQuantidade.setText(txtQuantidade.getText());
        }
    }

    private void ConfigurarValor(int id) {
        BigDecimal valor = controllerPizza.pegarUnico(id).getValor();
        txtValor.setText(String.valueOf(valor));
        pizza.getPizza().setValor(valor);
    }

    private void AtualizarCampos() {
        int id = Integer.parseInt(cboPizzas.getSelectedItem().toString());
        txtSabor.setText(controllerPizza.pegarUnico(id).getSabor());
        txtIdPizzaDoPedido.setText(String.valueOf(frame.getIdPedidoPizzas()));

        CarregarTipos(id);
        ConfigurarQuantidade();
        txtValor.setText(String.valueOf(controllerPizza.pegarUnico(id).getValor()));
        txtIdPedido.setText(String.valueOf(frame.getIdPedido()));

        descreverCobertura();

        pizza = controller.converter(this);
    }

    private void descreverCobertura() {
        txtIdCobertura.setText(Utilidade.atualizarLabelsCobertura(cboCobertura)[0]);
        lblValorCobertura.setText(Utilidade.atualizarLabelsCobertura(cboCobertura)[1]);
        txtIdCobertura.setText(Utilidade.atualizarLabelsCobertura(cboCobertura)[2]);
    }

    @Override
    public int getIdPedidoPizzas() {
        return Integer.parseInt(txtIdPizzaDoPedido.getText());
    }

    @Override
    public int getQuantidade() {
        return Integer.parseInt(txtQuantidade.getText());
    }

    @Override
    public int getIdPizza() {
        return Integer.parseInt(cboPizzas.getSelectedItem().toString());
    }

    @Override
    public PizzasEnum getTipoPizza() {
        return PizzasEnum.valueOf(cboTipoPizza.getSelectedItem().toString().trim().toUpperCase());
    }

    @Override
    public String getSabor() {
        return this.txtSabor.getText();
    }

    @Override
    public BigDecimal getValor() {
        return BigDecimal.valueOf(Double.parseDouble(txtValor.getText()));
    }

    @Override
    public PedidoPizza getObjeto() {
        return this.pizza;
    }

    private void DescrevePizza(IFramePizzaRelacionadaPedido frame) {
        this.lblPizzaDoPedido.setText(String.format("Id Pizza: %s, Id Pedido: %s, Sabor: %s",
                frame.getIdPizza(), frame.getIdPedidoPizzas(), frame.getSabor()));

        this.lblPizzaDoPedido1.setText(String.format("\nTipo Pizza: %s, Valor: %s R$, Quantidade: %s, Cobertura: %s",
                frame.getTipoPizza().name(), frame.getValor(), frame.getQuantidade(),
                frame.getCobertura().getNomeCobertura()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSabor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdPizzaDoPedido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblPizzaDoPedido = new javax.swing.JLabel();
        lblPizzaDoPedido1 = new javax.swing.JLabel();
        cboPizzas = new javax.swing.JComboBox<>();
        cboTipoPizza = new javax.swing.JComboBox<>();
        txtIdPedido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboCobertura = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtIdCobertura = new javax.swing.JLabel();
        lblValorCobertura = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblNomeCobertura1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(530, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Sabor:");
        pnMain.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, -1, -1));

        txtSabor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSabor.setEnabled(false);
        pnMain.add(txtSabor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 205, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("ID Pizza:");
        pnMain.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tipo de Pizza:");
        pnMain.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 102, -1, -1));

        txtIdPizzaDoPedido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIdPizzaDoPedido.setEnabled(false);
        pnMain.add(txtIdPizzaDoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 161, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("ID da pizza do Pedido: ");
        pnMain.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        txtQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQuantidadeFocusLost(evt);
            }
        });
        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyPressed(evt);
            }
        });
        pnMain.add(txtQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 131, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Quantidade:");
        pnMain.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        txtValor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtValor.setEnabled(false);
        pnMain.add(txtValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, 205, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Valor:");
        pnMain.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, -1, -1));

        lblPizzaDoPedido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPizzaDoPedido.setText("ID:");
        pnMain.add(lblPizzaDoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        lblPizzaDoPedido1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPizzaDoPedido1.setText("ID:");
        pnMain.add(lblPizzaDoPedido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 37, -1, -1));

        cboPizzas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboPizzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPizzasActionPerformed(evt);
            }
        });
        pnMain.add(cboPizzas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 205, 30));

        cboTipoPizza.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboTipoPizza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Individual", "Regular", "Familiar" }));
        cboTipoPizza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoPizzaActionPerformed(evt);
            }
        });
        pnMain.add(cboTipoPizza, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 133, 205, 30));

        txtIdPedido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIdPedido.setEnabled(false);
        pnMain.add(txtIdPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 161, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("ID do Pedido: ");
        pnMain.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        cboCobertura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboCobertura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboCoberturaItemStateChanged(evt);
            }
        });
        cboCobertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCoberturaActionPerformed(evt);
            }
        });
        pnMain.add(cboCobertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 205, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Coberturas:");
        pnMain.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setText("ID:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setText("Valor da cobertura:");

        txtIdCobertura.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtIdCobertura.setText("0");

        lblValorCobertura.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblValorCobertura.setText("0");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setText("Nome da cobertura:");

        lblNomeCobertura1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblNomeCobertura1.setText("Nenhuma");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(24, 24, 24)
                        .addComponent(lblValorCobertura))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdCobertura))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(lblNomeCobertura1)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdCobertura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeCobertura1)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblValorCobertura))
                .addContainerGap())
        );

        pnMain.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 220, 110));

        add(pnMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 520, 490));
    }// </editor-fold>//GEN-END:initComponents

    private void cboPizzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPizzasActionPerformed
        AtualizarCampos();
    }//GEN-LAST:event_cboPizzasActionPerformed

    private void cboTipoPizzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoPizzaActionPerformed
        AtualizarPreco();
    }//GEN-LAST:event_cboTipoPizzaActionPerformed

    private void txtQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantidadeFocusLost
        AtualizarPrecoPelaQuantidade();
    }//GEN-LAST:event_txtQuantidadeFocusLost

    private void AtualizarPrecoPelaQuantidade() {
        try {
            pizza.getPizza().setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            AtualizarPreco();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Valor não númerico inserido!\nErro:" + ex.getMessage());
        }
    }

    private void txtQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyPressed
        if (evt.getKeyCode() == 10) {
            AtualizarPrecoPelaQuantidade();
        }
    }//GEN-LAST:event_txtQuantidadeKeyPressed

    private void cboCoberturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCoberturaActionPerformed
        try {
            this.AtualizarCampos();
            this.AtualizarPreco();
        } catch (NullPointerException ex) {

        }
    }//GEN-LAST:event_cboCoberturaActionPerformed

    private void cboCoberturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboCoberturaItemStateChanged

    }//GEN-LAST:event_cboCoberturaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboCobertura;
    private javax.swing.JComboBox<String> cboPizzas;
    private javax.swing.JComboBox<String> cboTipoPizza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNomeCobertura1;
    private javax.swing.JLabel lblPizzaDoPedido;
    private javax.swing.JLabel lblPizzaDoPedido1;
    private javax.swing.JLabel lblValorCobertura;
    private javax.swing.JPanel pnMain;
    private javax.swing.JLabel txtIdCobertura;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextField txtIdPizzaDoPedido;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtSabor;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

    @Override
    public int getIdPedido() {
        return frame.getIdPedido();
    }

    @Override
    public IFrameCobertura getCobertura() {
        return this;
    }

    public Cobertura getCoberturaModel() {
        return ControllerCobertura.converter(this);
    }

    @Override
    public ArrayList<PizzaReadOnlyPedido> getPizzasDoModelo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdCobertura() {
        return Integer.parseInt(cboCobertura.getSelectedItem().toString());
    }

    @Override
    public String getNomeCobertura() {
        return txtIdCobertura.getText();
    }

    @Override
    public BigDecimal getValorCobertura() {
        return BigDecimal.valueOf(Double.parseDouble(lblValorCobertura.getText()));
    }

    @Override
    public int getIdCoberturaRelacionada() {
        return Integer.parseInt(txtIdCobertura.getText());
    }
}
