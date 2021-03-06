/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.View;

import Enum.PizzasEnum;
import java.awt.BorderLayout;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import pizzaria.Controller.*;
import pizzaria.View.Util.GerenciaLista.UtilLista;
import pizzaria.View.Util.Utilidade;
import readOnly.PizzaReadOnly;
import pizzaria.Controller.ControllerPedidoPizza;
import pizzaria.Interfaces.ControllerLista;
import pizzaria.View.Util.ConfiguraJScroll;
import pizzaria.Interfaces.Frame.IFrameCadastroPedido;
import pizzaria.Interfaces.Frame.IFrameCobertura;
import pizzaria.Interfaces.Frame.IFramePizzaRelacionadaPedido;
import pizzaria.Interfaces.Frame.Observador;
import pizzaria.Interfaces.IJlist;
import pizzaria.Model.*;
import pizzaria.View.SubtelasPedido.FrameCadastroCobertura;
import pizzaria.View.Util.GerenciaLista.UtilListaPizzaPedido;
import readOnly.PizzaReadOnlyPedido;

/**
 *
 * @author Guilh
 */
public class FrameCadastroPedido extends javax.swing.JPanel implements Observador, IFrameCobertura, IFrameCadastroPedido, IFramePizzaRelacionadaPedido, IJlist {

    /**
     * Creates new form FrameCadastroPedido
     */
    private DefaultListModel model;
    private DefaultListModel modelEscolhidas;

    private ControllerCobertura controllerCobertura;
    private ControllerPedido controllerPedido;
    private ControllerLista<PizzaReadOnly> controllerLista;
    private UtilListaPizzaPedido controllerPedidoPizza;
    private ArrayList<IFramePizzaRelacionadaPedido> pizzas;

    private FrameConsultaEndereco cEro;

    public FrameCadastroPedido() {
        initComponents();

        model = new DefaultListModel();
        modelEscolhidas = new DefaultListModel();
        controllerPedido = new ControllerPedido();
        cEro = new FrameConsultaEndereco();
        pizzas = new ArrayList<>();

        controllerLista = new UtilLista(this);
        controllerPedidoPizza = new UtilListaPizzaPedido(this);
        controllerCobertura = new ControllerCobertura();

        configurarFrame();
    }

    private void configurarFrame() {
        txtCodigoPedido.setText(Utilidade.TextoChave(controllerPedido));
        configurarCbo();
        ConfigurarScrollPane();
        colocarRdbInvisivel();
        carregarPizzas();
    }

    private void configurarCbo() {
        cboCobertura.setBackground(Color.white);
        carregarCbo();
    }

    private void carregarCbo() {
        cboCobertura.removeAllItems();

        controllerCobertura.pegarVarios().forEach(c
                -> this.cboCobertura.addItem(String.valueOf(c.getId()))
        );
    }

    private void colocarRdbVisivel() {
        habilitarRdb(bgTipo.getElements(), new ControllerPizza().pegar(controllerLista
                .PegarElementoSelecionado().getId()));
    }

    private void habilitarRdb(Enumeration elementos, ArrayList<PizzaReadOnly> pizzasComEnum) {
        while (elementos.hasMoreElements()) {
            JRadioButton rdb = (JRadioButton) elementos.nextElement();

            pizzasComEnum.forEach(c -> {
                if (c.getTipoPizza().name().equals(rdb.getText().toUpperCase().trim())) {
                    rdb.setVisible(true);
                }
            });
        }
    }

    private void selecionarRdbVisivel() {
        Enumeration elementos = bgTipo.getElements();

        while (elementos.hasMoreElements()) {
            JRadioButton rdb = (JRadioButton) elementos.nextElement();
            if(rdb.isVisible()){
                rdb.setSelected(true);
            }
        }
    }

    private void colocarRdbInvisivel() {
        Enumeration elementos = bgTipo.getElements();

        while (elementos.hasMoreElements()) {
            JRadioButton rdb = (JRadioButton) elementos.nextElement();
            rdb.setVisible(false);
        }
    }

    private void ConfigurarScrollPane() {
        ConfiguraJScroll.configJscrollPane(jScrollPane1);
        ConfiguraJScroll.configJscrollPane(jScrollPane2);
    }

    private void carregarPizzas() {
        ArrayList<PizzaReadOnly> lstPro = new ControllerPizza().pegarAgrupado();
        lstPro.forEach(c -> model.addElement(c));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipo = new javax.swing.ButtonGroup();
        PnPedido = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnTipoPizza = new javax.swing.JPanel();
        rbtnFamiliar = new javax.swing.JRadioButton();
        rbtnIndividual = new javax.swing.JRadioButton();
        rbtnRegular = new javax.swing.JRadioButton();
        lblTipoDePizza1 = new javax.swing.JLabel();
        lblPizzas = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPizzas = new javax.swing.JList<>();
        btnRetirar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstPizzasPedido = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoPedido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblQuantidadeEnunciado = new javax.swing.JLabel();
        lblNormal3 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblPrecoTotal = new javax.swing.JLabel();
        pnDados = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        lblSabor = new javax.swing.JLabel();
        lbltPreco = new javax.swing.JLabel();
        lblPreco = new javax.swing.JLabel();
        lblQuantidade = new javax.swing.JLabel();
        lblQuantidade1 = new javax.swing.JLabel();
        lblPizzas1 = new javax.swing.JLabel();
        cboCobertura = new javax.swing.JComboBox<>();
        lblTipoDePizza3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTipoDePizza4 = new javax.swing.JLabel();
        lblNomeCobertura = new javax.swing.JLabel();
        lblValorCobertura = new javax.swing.JLabel();
        lblTipoDePizza5 = new javax.swing.JLabel();
        btnAdicionaCobertura = new javax.swing.JButton();
        PnEndereco = new javax.swing.JPanel();
        btnChamarEndereco = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnConsultarEndereco = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnPedido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnTipoPizza.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bgTipo.add(rbtnFamiliar);
        rbtnFamiliar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbtnFamiliar.setText("Familiar");
        rbtnFamiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFamiliarActionPerformed(evt);
            }
        });
        pnTipoPizza.add(rbtnFamiliar);

        bgTipo.add(rbtnIndividual);
        rbtnIndividual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbtnIndividual.setSelected(true);
        rbtnIndividual.setText("Individual");
        rbtnIndividual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnIndividualActionPerformed(evt);
            }
        });
        pnTipoPizza.add(rbtnIndividual);

        bgTipo.add(rbtnRegular);
        rbtnRegular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbtnRegular.setText("Regular");
        rbtnRegular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRegularActionPerformed(evt);
            }
        });
        pnTipoPizza.add(rbtnRegular);

        jPanel2.add(pnTipoPizza, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 220, 70));

        lblTipoDePizza1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTipoDePizza1.setText("Coberturas:");
        jPanel2.add(lblTipoDePizza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        lblPizzas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPizzas.setText("Valor Total:");
        jPanel2.add(lblPizzas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, -1, -1));
        lblPizzas.getAccessibleContext().setAccessibleName("");

        txtQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtQuantidade.setBorder(null);
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
        jPanel2.add(txtQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 220, 30));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        lstPizzas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lstPizzas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lstPizzas.setSelectionBackground(new java.awt.Color(204, 204, 255));
        lstPizzas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lstPizzasFocusLost(evt);
            }
        });
        lstPizzas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstPizzasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstPizzas);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 230, 180));

        btnRetirar.setBackground(new java.awt.Color(255, 255, 255));
        btnRetirar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRetirar.setText("Retirar da Lista");
        btnRetirar.setEnabled(false);
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRetirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 230, 40));

        btnAdicionar.setBackground(new java.awt.Color(255, 255, 255));
        btnAdicionar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdicionar.setText("Adicionar na Lista");
        btnAdicionar.setEnabled(false);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 230, 40));

        jScrollPane2.setBorder(null);

        lstPizzasPedido.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lstPizzasPedido.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jScrollPane2.setViewportView(lstPizzasPedido);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 220, 140));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Código do Pedido:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtCodigoPedido.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCodigoPedido.setBorder(null);
        txtCodigoPedido.setEnabled(false);
        jPanel2.add(txtCodigoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, 29));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Pizzas:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        lblQuantidadeEnunciado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQuantidadeEnunciado.setText("Quantidade da Pizza:");
        jPanel2.add(lblQuantidadeEnunciado, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, -1, -1));

        lblNormal3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNormal3.setText("Pizza Selecionada:");
        lblNormal3.setToolTipText("");
        jPanel2.add(lblNormal3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        lblId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblId.setToolTipText("");
        jPanel2.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 50, 20));

        lblPrecoTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPrecoTotal.setText("0");
        jPanel2.add(lblPrecoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 570, -1, -1));

        pnDados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnDados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTipo.setToolTipText("");
        pnDados.add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 120, 20));

        lblSabor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSabor.setToolTipText("");
        pnDados.add(lblSabor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 20));

        lbltPreco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbltPreco.setText("Preco: ");
        lbltPreco.setToolTipText("");
        pnDados.add(lbltPreco, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        lblPreco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPreco.setText("0");
        lblPreco.setToolTipText("");
        pnDados.add(lblPreco, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        lblQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQuantidade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuantidade.setToolTipText("");
        pnDados.add(lblQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        lblQuantidade1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQuantidade1.setText("Quantidade:");
        lblQuantidade1.setToolTipText("");
        pnDados.add(lblQuantidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jPanel2.add(pnDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 230, 70));

        lblPizzas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPizzas1.setText("Pizzas a serem entregues:");
        jPanel2.add(lblPizzas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, -1));

        cboCobertura.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cboCobertura.setMaximumRowCount(20);
        cboCobertura.setBorder(null);
        cboCobertura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboCoberturaItemStateChanged(evt);
            }
        });
        jPanel2.add(cboCobertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 240, 30));

        lblTipoDePizza3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTipoDePizza3.setText("Tipo de Pizza:");
        jPanel2.add(lblTipoDePizza3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTipoDePizza4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTipoDePizza4.setText("Nome da Cobertura:");

        lblNomeCobertura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNomeCobertura.setText("Nenhuma");

        lblValorCobertura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblValorCobertura.setText("0");

        lblTipoDePizza5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTipoDePizza5.setText("Valor da Cobertura:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTipoDePizza5)
                        .addGap(20, 20, 20)
                        .addComponent(lblValorCobertura))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTipoDePizza4)
                        .addGap(15, 15, 15)
                        .addComponent(lblNomeCobertura)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoDePizza5)
                    .addComponent(lblValorCobertura))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoDePizza4)
                    .addComponent(lblNomeCobertura))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        btnAdicionaCobertura.setBackground(new java.awt.Color(255, 255, 255));
        btnAdicionaCobertura.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnAdicionaCobertura.setText("Adicionar + Coberturas");
        btnAdicionaCobertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionaCoberturaActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdicionaCobertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 160, 30));

        PnPedido.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 530, 600));

        PnEndereco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PnEndereco.setPreferredSize(new java.awt.Dimension(343, 408));

        javax.swing.GroupLayout PnEnderecoLayout = new javax.swing.GroupLayout(PnEndereco);
        PnEndereco.setLayout(PnEnderecoLayout);
        PnEnderecoLayout.setHorizontalGroup(
            PnEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        PnEnderecoLayout.setVerticalGroup(
            PnEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );

        PnPedido.add(PnEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 380, 510));

        btnChamarEndereco.setBackground(new java.awt.Color(255, 255, 255));
        btnChamarEndereco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChamarEndereco.setText("Endereço");
        btnChamarEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamarEnderecoActionPerformed(evt);
            }
        });
        PnPedido.add(btnChamarEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 170, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Chamar tela Endereço:");
        jLabel2.setToolTipText("");
        PnPedido.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 5, -1, -1));

        btnConsultarEndereco.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarEndereco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnConsultarEndereco.setText("Consultar Endereço");
        btnConsultarEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarEnderecoActionPerformed(evt);
            }
        });
        PnPedido.add(btnConsultarEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 170, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Chamar Consulta Endereco:");
        jLabel5.setToolTipText("");
        PnPedido.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 45, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Criar Pedido:");
        PnPedido.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        btnCadastrar.setBackground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        PnPedido.add(btnCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 640, 380, 40));

        add(PnPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1000, 690));
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnIndividualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnIndividualActionPerformed
        MudarTextoLblSabor();
        calcularValor();
    }//GEN-LAST:event_rbtnIndividualActionPerformed

    private void rbtnFamiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFamiliarActionPerformed
        MudarTextoLblSabor();
        calcularValor();
    }//GEN-LAST:event_rbtnFamiliarActionPerformed

    private void rbtnRegularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRegularActionPerformed
        MudarTextoLblSabor();
        calcularValor();
    }//GEN-LAST:event_rbtnRegularActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        controllerPedidoPizza.AdicionarList(this);

        CalcularValorTotal();
        IFramePizzaRelacionadaPedido pizza = this;
        pizzas.add(pizza);

        btnRetirar.setEnabled(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void CalcularValorTotal() {
        lblPrecoTotal.setText(String.valueOf(controllerPedido
                .calcularValorTotal(controllerPedidoPizza.PegarTodosElementos())
                .setScale(2, RoundingMode.HALF_UP)));
    }

    private void lstPizzasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstPizzasValueChanged
        configurarDescricao();
        selecionarRdbVisivel();
        MudarTextoLblSabor();
        calcularValor();
    }//GEN-LAST:event_lstPizzasValueChanged

    public void colocarDescricao() {
        lblId.setText(String.valueOf(controllerLista.PegarElementoSelecionado().getId()));
        lblPreco.setText(String.valueOf(controllerLista.PegarElementoSelecionado().getValor()));
        lblSabor.setText(String.valueOf(controllerLista.PegarElementoSelecionado().getSabor()));
    }

    public void atualizarRdb() {
        colocarRdbInvisivel();
        colocarRdbVisivel();
    }

    private void configurarDescricao() {
        try {
            colocarDescricao();
            atualizarRdb();
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void lstPizzasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lstPizzasFocusLost

    }//GEN-LAST:event_lstPizzasFocusLost

    private void txtQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantidadeFocusLost
        lblQuantidade.setText(this.txtQuantidade.getText());
        calcularValor();
    }//GEN-LAST:event_txtQuantidadeFocusLost

    private void txtQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyPressed
        if (evt.getKeyCode() == 10) {
            txtQuantidade.setFocusable(false);

            configurarDescricao();
            calcularValor();

            txtQuantidade.setFocusable(true);
        }
    }//GEN-LAST:event_txtQuantidadeKeyPressed

    private void btnChamarEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChamarEnderecoActionPerformed
        ChamarPanel(new FrameCadastroEndereco());
    }//GEN-LAST:event_btnChamarEnderecoActionPerformed

    private void btnAdicionaCoberturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionaCoberturaActionPerformed
        new FrameCadastroCobertura(this).setVisible(true);
    }//GEN-LAST:event_btnAdicionaCoberturaActionPerformed

    private void btnConsultarEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarEnderecoActionPerformed
        cEro.AtualizarConsulta();
        ChamarPanel(cEro);
    }//GEN-LAST:event_btnConsultarEnderecoActionPerformed

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
        if (lstPizzasPedido.getSelectedIndex() == -1) {
            int op = Utilidade.CriarOpcao("Deseja retirar todos os dados da lista ? ");

            if (op == 1) {
                configurarAposRetirarTudo();
            }
        } else {
            ConfigurarAposRetirarSelecionados();
        }
    }//GEN-LAST:event_btnRetirarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        if ((getPizzas().size() > 0) && (cEro.ero != null)) {
            CadastrarPedidoPizza(new ControllerPedidoPizza().criarListaDePizzas(this));

            controllerPedido.adicionar(this);

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
            txtCodigoPedido.setText(Utilidade.TextoChave(controllerPedido));
            return;
        }
        JOptionPane.showMessageDialog(null, "Não foi possível cadastrar!");
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void cboCoberturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboCoberturaItemStateChanged
        try {
            lblNomeCobertura.setText(Utilidade.atualizarLabelsCobertura(cboCobertura)[0]);
            lblValorCobertura.setText(Utilidade.atualizarLabelsCobertura(cboCobertura)[1]);
            calcularValor();
        } catch (NullPointerException ex) {
        }
    }//GEN-LAST:event_cboCoberturaItemStateChanged

    private void ConfigurarAposRetirarSelecionados() {
        controllerPedidoPizza.retirarSelecionados();
        CalcularValorTotal();
    }

    private void configurarAposRetirarTudo() {
        modelEscolhidas.clear();
        btnRetirar.setEnabled(false);
        lblPrecoTotal.setText("0");
    }

    private void calcularValor() {
        if (!(lblId.getText().isEmpty())) {
            configurarDescricao();

            BigDecimal valor = new ControllerPedidoPizza().calcularValorEConverter(this,
                    getTipoRelacionadoNaList().name())
                    .setScale(2, RoundingMode.HALF_UP);

            lblPreco.setText(String.valueOf(valor));
            btnAdicionar.setEnabled(true);
        }
    }

    private void MudarTextoLblSabor() {
        try {
            lblSabor.setText(controllerLista.ConverterParaModelo(this).getSabor().trim());

            lblTipo.setText(Utilidade.PegarRadioButtonHabilitado(pnTipoPizza).getText().trim());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Não há item selecionado: " + ex.getMessage());
        }
    }

    private void CadastrarPedidoPizza(ArrayList<PedidoPizza> lstPp) {
        new ControllerPedidoPizza().adicionarTodos(lstPp);
    }

    private void ChamarPanel(JPanel panel) {
        panel.setLocation(1, 1);
        panel.setSize(this.PnEndereco.getWidth() - 10, this.PnEndereco.getHeight() - 10);

        ConfigurarChamadaPnMain(panel);
    }

    private void ConfigurarChamadaPnMain(JPanel panel) {
        PnEndereco.removeAll();
        PnEndereco.add(panel, BorderLayout.CENTER);
        PnEndereco.revalidate();
        PnEndereco.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnEndereco;
    private javax.swing.JPanel PnPedido;
    private javax.swing.ButtonGroup bgTipo;
    private javax.swing.JButton btnAdicionaCobertura;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnChamarEndereco;
    private javax.swing.JButton btnConsultarEndereco;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JComboBox<String> cboCobertura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNomeCobertura;
    private javax.swing.JLabel lblNormal3;
    private javax.swing.JLabel lblPizzas;
    private javax.swing.JLabel lblPizzas1;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblPrecoTotal;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblQuantidade1;
    private javax.swing.JLabel lblQuantidadeEnunciado;
    private javax.swing.JLabel lblSabor;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTipoDePizza1;
    private javax.swing.JLabel lblTipoDePizza3;
    private javax.swing.JLabel lblTipoDePizza4;
    private javax.swing.JLabel lblTipoDePizza5;
    private javax.swing.JLabel lblValorCobertura;
    private javax.swing.JLabel lbltPreco;
    private javax.swing.JList<String> lstPizzas;
    private javax.swing.JList<String> lstPizzasPedido;
    private javax.swing.JPanel pnDados;
    private javax.swing.JPanel pnTipoPizza;
    private javax.swing.JRadioButton rbtnFamiliar;
    private javax.swing.JRadioButton rbtnIndividual;
    private javax.swing.JRadioButton rbtnRegular;
    private javax.swing.JTextField txtCodigoPedido;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables

    @Override
    public int getIdPizza() {
        return Integer.parseInt(lblId.getText());
    }

    public ArrayList<PizzaReadOnlyPedido> getPizzas() {
        return controllerPedidoPizza.PegarTodosElementos();
    }

    //PROPIEDADES PEDIDO
    @Override
    public Endereco getEndereco() {
        return cEro.ero;
    }

    @Override
    public boolean getStatus() {
        return false;
    }

    @Override
    public DefaultListModel getModelo() {
        return model;
    }

    @Override
    public JList getLista() {
        return lstPizzas;
    }

    //PROPIEDADES LISTA
    @Override
    public DefaultListModel getModeloPizzaDoPedido() {
        return modelEscolhidas;
    }

    @Override
    public JList getListaPizzaDoPedido() {
        return lstPizzasPedido;
    }

    //PROPIEDADES PIZZA
    @Override
    public int getQuantidade() {
        if (!(txtQuantidade.getText().isEmpty())) {
            return Integer.parseInt(txtQuantidade.getText());
        }

        return 1;
    }

    @Override
    public PizzasEnum getTipoPizza() {
        if (!lblTipo.getText().isEmpty()) {
            PizzasEnum.valueOf(lblTipo.getText().trim().toUpperCase());
        }

        return PizzasEnum.valueOf(
                this.controllerLista.PegarElementoSelecionado().getTipoPizza().name()
        );
    }

    @Override
    public String getSabor() {
        return lblSabor.getText().trim();
    }

    @Override
    public BigDecimal getValor() {
        System.out.println(lblPreco.getText());
        return BigDecimal.valueOf(Double.parseDouble(lblPreco.getText()));
    }

    @Override
    public int getIdPedido() {
        return Integer.parseInt(txtCodigoPedido.getText());
    }

    @Override
    public int getIdPedidoPizzas() {
        return Integer.parseInt(Utilidade.TextoChave(new ControllerPedidoPizza()));
    }

    @Override
    public BigDecimal getValorTotal() {
        return BigDecimal.valueOf(Double.parseDouble(lblPrecoTotal.getText()))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public IFrameCobertura getCobertura() {
        return this;
    }

    @Override
    public int getIdCobertura() {
        return Integer.parseInt(cboCobertura.getSelectedItem().toString());
    }

    @Override
    public String getNomeCobertura() {
        return this.lblNomeCobertura.getText();
    }

    @Override
    public BigDecimal getValorCobertura() {
        return BigDecimal.valueOf(Double.parseDouble(lblValorCobertura.getText()));
    }

    @Override
    public ArrayList<PizzaReadOnlyPedido> getPizzasDoModelo() {
        return controllerPedidoPizza.PegarTodosElementos();
    }

    @Override
    public PizzasEnum getTipoRelacionadoNaList() {
        return PizzasEnum.valueOf(
                Utilidade.PegarRadioButtonHabilitado(pnTipoPizza).getText().toUpperCase().trim()
        );
    }

    @Override
    public void setObservador() {
        carregarCbo();
    }

    @Override
    public int getIdCoberturaRelacionada() {
        return Integer.parseInt(this.cboCobertura.getSelectedItem().toString());
    }
}
