//******************************************************************************
//A Topdata Sistemas de Automação Ltda não se responsabiliza por qualquer
//tipo de dano que este software possa causar, este exemplo deve ser utilizado
//apenas para demonstrar a comunicação com os equipamentos da linha Inner.
//
//Exemplo On-Line
//Desenvolvido em Java.
//                                           Topdata Sistemas de Automação Ltda.
//******************************************************************************

package com.topdata.easyInner.ui;


//Importing the lybraries that we are using
import com.topdata.easyInner.controller.OnlineController;
import com.topdata.easyInner.dao.DAOUsuariosBio;
import com.topdata.easyInner.entity.Inner;
import com.topdata.easyInner.enumeradores.Enumeradores;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


//
public class JIFEasyInnerOnLine extends javax.swing.JInternalFrame {
    private static final long serialVersionUID = 1L;

    //Teclado
    public static String ultCartao;
    public static int intTentativas;
    
    /*Esse temporizador ser para que o teclado nao fique bloqueado, 
    caso o usuário nao digite nenhum caractere, caso o tempo for excedido, 
    a possibilidade de digitar senha sera bloqueada e seu acesso será negado*/
    private long TempInitDigHabilitada;

    //tentativas para coleta de bilhetes
    int TentativasColeta;   
    

    private DAOUsuariosBio UsersBio;
    static List<String> Users = null;

    

    private String NumCartao = new String();
    private String Bilhetee;

    private HashMap<String, Object> DadosSmartCard = new HashMap<>();

    private DefaultTableModel dtm;
    private OnlineController OnlineControll;

    @SuppressWarnings("unchecked")
    
    public JIFEasyInnerOnLine() {

        //Inicialização dos componentes
        initComponents();

        //Seta campos
        jSpnNumInner.setValue(1);
        jSpnQtdDigitos.setValue(14);
        jSpnPorta.setValue(3570);

        jChkBiometrico.setSelected(false);
        jChkDoisLeitores.setSelected(false);

        //**************************************************
        //Carregamento das combos e campos
        //**************************************************
        //Padrão cartão
        jCboPadraoCartao.addItem("TopData");
        jCboPadraoCartao.addItem("Livre");
        jCboPadraoCartao.setSelectedIndex(1);

        jCboPadraoCartao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (jCboPadraoCartao.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Este tipo de cartão é para uso exclusivo de cartões fabricado pela Topdata !");
                }
            }
        });

        //Combo Tipo Conexão
        jCboTipoConexao.removeAllItems();
        jCboTipoConexao.addItem("Serial");
        jCboTipoConexao.addItem("TCP/IP");
        jCboTipoConexao.addItem("TCP/IP porta fixa");
        jCboTipoConexao.setSelectedIndex(2);   //Default

        //Combo Tipo Leitor
        jCboTipoLeitor.removeAllItems();
        jCboTipoLeitor.addItem("Código Barras");
        jCboTipoLeitor.addItem("Magnético");
        jCboTipoLeitor.addItem("Prox. Abatrack/Smart Card");
        jCboTipoLeitor.addItem("Prox. Wiegand/Smart Card");
        jCboTipoLeitor.addItem("Prox. Smart Card Serial");
        jCboTipoLeitor.addItem("Codigo barras serial");
        jCboTipoLeitor.addItem("Wiegand FC sem zero");
        jCboTipoLeitor.setSelectedIndex(2);  //Default

        //Combo Equipamento
        jCboEquipamento.removeAllItems();
        jCboEquipamento.addItem("Não utilizado(Coletor)");
        jCboEquipamento.addItem("Catraca Entrada/Saída");
        jCboEquipamento.addItem("Catraca Entrada");
        jCboEquipamento.addItem("Catraca Saída");
        jCboEquipamento.addItem("Catraca Saída Liberada");
        jCboEquipamento.addItem("Catraca Entrada Liberada");
        jCboEquipamento.addItem("Catraca Liberada 2 Sentidos");
        jCboEquipamento.addItem("Catraca Liberada 2 Sentidos(Sentido Giro)");
        jCboEquipamento.addItem("Catraca com Urna");
        jCboEquipamento.setSelectedIndex(1);  //Default

        //Desabilita botões
        jBtnEntrada.setEnabled(false);
        jBtnParar.setEnabled(false);
        jBtnSaida.setEnabled(false);
        jBtnIniciar.setEnabled(false);
        
        OnlineControll = new OnlineController(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jCboTipoConexao = new javax.swing.JComboBox();
        lblInners = new javax.swing.JLabel();
        lblNumDig = new javax.swing.JLabel();
        jCboTipoLeitor = new javax.swing.JComboBox();
        jChkDoisLeitores = new javax.swing.JCheckBox();
        lblPorta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTipoEquipamento = new javax.swing.JLabel();
        jCboEquipamento = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jChkLista = new javax.swing.JCheckBox();
        jChkCartaoMaster = new javax.swing.JCheckBox();
        jTxtCartaoMaster = new javax.swing.JTextField();
        jChkTeclado = new javax.swing.JCheckBox();
        jChkIdentificacao = new javax.swing.JCheckBox();
        jChkVerificacao = new javax.swing.JCheckBox();
        jChkListaBio = new javax.swing.JCheckBox();
        jChkBiometrico = new javax.swing.JCheckBox();
        jchkModuloLC = new javax.swing.JCheckBox();
        jSpnQtdDigitos = new javax.swing.JSpinner();
        jSpnNumInner = new javax.swing.JSpinner();
        jSpnPorta = new javax.swing.JSpinner();
        jCboPadraoCartao = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jrdbEsquerda = new javax.swing.JRadioButton();
        jrdbDireita = new javax.swing.JRadioButton();
        imgCatraca = new javax.swing.JLabel();
        jBtnParar = new javax.swing.JButton();
        jBtnIniciar = new javax.swing.JButton();
        jBtnEntrada = new javax.swing.JButton();
        jBtnSaida = new javax.swing.JButton();
        jBtnLimpar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jBtnRemoverInner = new javax.swing.JButton();
        jBtnAdcionarInner = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLblStatus = new javax.swing.JLabel();
        lblDadosRec = new javax.swing.JLabel();
        jLblDados = new javax.swing.JLabel();
        lblStatuss = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTxaBilhetes = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTxaVersao = new javax.swing.JTextArea();
        jlblBilhetes = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblInners = new javax.swing.JTable();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Exemplo OnLine EasyInner.dll");
        setMaximumSize(new java.awt.Dimension(800, 920));
        setMinimumSize(new java.awt.Dimension(800, 700));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(715, 915));
        setVisible(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurações :"));
        jPanel1.setMinimumSize(new java.awt.Dimension(473, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCboTipoConexao.setName("jCboTipoConexao"); // NOI18N
        jPanel1.add(jCboTipoConexao, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 34, 180, -1));

        lblInners.setText("Número Inner:");
        lblInners.setName("lblInners"); // NOI18N
        jPanel1.add(lblInners, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, -1, -1));

        lblNumDig.setText("Qtd de Dígitos:");
        lblNumDig.setName("lblNumDig"); // NOI18N
        jPanel1.add(lblNumDig, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 16, -1, -1));

        jCboTipoLeitor.setName("jCboTipoLeitor"); // NOI18N
        jCboTipoLeitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCboTipoLeitorMouseClicked(evt);
            }
        });
        jPanel1.add(jCboTipoLeitor, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 34, 194, -1));

        jChkDoisLeitores.setSelected(true);
        jChkDoisLeitores.setText("2 Leitores?");
        jChkDoisLeitores.setName("jChkDoisLeitores"); // NOI18N
        jPanel1.add(jChkDoisLeitores, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 56, -1, -1));

        lblPorta.setText("Porta:");
        lblPorta.setName("lblPorta"); // NOI18N
        jPanel1.add(lblPorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 16, -1, -1));

        jLabel1.setText("Tipo Conexão:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 16, -1, -1));

        jLabel2.setText("Tipo Leitor:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 16, -1, -1));

        lblTipoEquipamento.setText("Tipo Equipamento:");
        jPanel1.add(lblTipoEquipamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 56, -1, -1));

        jCboEquipamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboEquipamentocboequipamento_itemstatechanged(evt);
            }
        });
        jCboEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboEquipamentoActionPerformed(evt);
            }
        });
        jPanel1.add(jCboEquipamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 81, 260, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametros"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jChkLista.setText("Lista OffLine");
        jChkLista.setName("chkIdentificacao"); // NOI18N
        jPanel5.add(jChkLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, -1, -1));

        jChkCartaoMaster.setText("Cartão Master");
        jChkCartaoMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkCartaoMasterActionPerformed(evt);
            }
        });
        jPanel5.add(jChkCartaoMaster, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 65, -1, -1));

        jTxtCartaoMaster.setText("0");
        jTxtCartaoMaster.setEnabled(false);
        jPanel5.add(jTxtCartaoMaster, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 90, 107, -1));

        jChkTeclado.setSelected(true);
        jChkTeclado.setText("Teclado");
        jChkTeclado.setName("jChkTeclado"); // NOI18N
        jPanel5.add(jChkTeclado, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 39, 95, -1));

        jChkIdentificacao.setText("Identificação");
        jChkIdentificacao.setEnabled(false);
        jChkIdentificacao.setName("jChkIdentificacao"); // NOI18N
        jChkIdentificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkIdentificacaoActionPerformed(evt);
            }
        });
        jPanel5.add(jChkIdentificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 77, 110, 19));

        jChkVerificacao.setText("Verificação");
        jChkVerificacao.setEnabled(false);
        jChkVerificacao.setName("jChkVerificacao"); // NOI18N
        jChkVerificacao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jChkVerificacaoStateChanged(evt);
            }
        });
        jChkVerificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkVerificacaoActionPerformed(evt);
            }
        });
        jPanel5.add(jChkVerificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 58, 110, 19));

        jChkListaBio.setText("Lista sem Bio OffLine");
        jChkListaBio.setEnabled(false);
        jPanel5.add(jChkListaBio, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 39, -1, 19));

        jChkBiometrico.setText("Biometria");
        jChkBiometrico.setName("jChkBiometrico"); // NOI18N
        jChkBiometrico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jChkBiometricoStateChanged(evt);
            }
        });
        jChkBiometrico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkBiometricoActionPerformed(evt);
            }
        });
        jPanel5.add(jChkBiometrico, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 18, 110, 19));

        jchkModuloLC.setText("Modulo LC");
        jchkModuloLC.setEnabled(false);
        jchkModuloLC.setName("jChkIdentificacao"); // NOI18N
        jchkModuloLC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchkModuloLCActionPerformed(evt);
            }
        });
        jPanel5.add(jchkModuloLC, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 96, 110, 19));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 112, 310, 130));
        jPanel1.add(jSpnQtdDigitos, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 34, 62, -1));
        jPanel1.add(jSpnNumInner, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 34, 80, -1));
        jPanel1.add(jSpnPorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 34, 62, -1));

        jPanel1.add(jCboPadraoCartao, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 81, 160, -1));

        jLabel4.setText("Padrão Cartão:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 56, -1, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Ao entrar a catraca está instalada à sua"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup2.add(jrdbEsquerda);
        jrdbEsquerda.setText("Esquerda");
        jrdbEsquerda.setEnabled(false);
        jrdbEsquerda.setName("optCatraca"); // NOI18N
        jrdbEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrdbEsquerdaActionPerformed(evt);
            }
        });
        jPanel7.add(jrdbEsquerda, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 27, -1, -1));

        buttonGroup2.add(jrdbDireita);
        jrdbDireita.setSelected(true);
        jrdbDireita.setText("Direita");
        jrdbDireita.setEnabled(false);
        jrdbDireita.setName("optCatraca"); // NOI18N
        jrdbDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrdbDireitaActionPerformed(evt);
            }
        });
        jPanel7.add(jrdbDireita, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 27, -1, -1));
        jPanel7.add(imgCatraca, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 27, 54, 59));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 112, -1, -1));

        jPanel10.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 11, -1, 260));
        jPanel1.getAccessibleContext().setAccessibleName("");

        jBtnParar.setText("Parar");
        jBtnParar.setName("jBtnParar"); // NOI18N
        jBtnParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPararActionPerformed(evt);
            }
        });
        jPanel10.add(jBtnParar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 667, -1, -1));

        jBtnIniciar.setText("Iniciar");
        jBtnIniciar.setName("jBtnIniciar"); // NOI18N
        jBtnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIniciarActionPerformed(evt);
            }
        });
        jPanel10.add(jBtnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 667, -1, -1));

        jBtnEntrada.setLabel("Entrada");
        jBtnEntrada.setSelected(true);
        jBtnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEntradaActionPerformed(evt);
            }
        });
        jPanel10.add(jBtnEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 667, -1, -1));
        jBtnEntrada.getAccessibleContext().setAccessibleName("cmdEntrada");

        jBtnSaida.setLabel("Saida");
        jBtnSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaidaActionPerformed(evt);
            }
        });
        jPanel10.add(jBtnSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 667, -1, -1));

        jBtnLimpar.setText("Limpar");
        jBtnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimparActionPerformed(evt);
            }
        });
        jPanel10.add(jBtnLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 667, -1, -1));

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));
        jPanel10.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 296, -1, -1));

        jBtnRemoverInner.setText("Remover da Lista");
        jBtnRemoverInner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRemoverInnerActionPerformed(evt);
            }
        });
        jPanel10.add(jBtnRemoverInner, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, -1, -1));

        jBtnAdcionarInner.setText("Incluir na Lista");
        jBtnAdcionarInner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAdcionarInnerActionPerformed(evt);
            }
        });
        jPanel10.add(jBtnAdcionarInner, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jLblStatus.setMinimumSize(new java.awt.Dimension(200, 0));
        jLblStatus.setName("jLblStatus"); // NOI18N

        lblDadosRec.setText("Dados recebidos:");
        lblDadosRec.setName("lblDadosRec"); // NOI18N

        jLblDados.setName("jLblDados"); // NOI18N

        lblStatuss.setText("Status:");
        lblStatuss.setName("lblStatuss"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDadosRec)
                    .addComponent(lblStatuss))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblDados, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblDadosRec, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(lblStatuss)
                        .addGap(9, 9, 9))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLblDados, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))))
        );

        jPanel10.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 670, -1));

        jTxaBilhetes.setColumns(20);
        jTxaBilhetes.setRows(5);
        jScrollPane6.setViewportView(jTxaBilhetes);

        jPanel10.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 592, 660, 69));

        jTxaVersao.setColumns(20);
        jTxaVersao.setRows(5);
        jScrollPane7.setViewportView(jTxaVersao);

        jPanel10.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 670, 53));

        jlblBilhetes.setText("Bilhetes recebidos:");
        jlblBilhetes.setName("lblDadosRec"); // NOI18N
        jPanel10.add(jlblBilhetes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 563, -1, 18));

        jTblInners.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Inner", "Qtd Dígitos", "Teclado", "Lista Off", "ListaBio", "TipoLeitor", "Modulo Bio", "Identificacao", "Verificacao", "DoisLeitores", "Catraca"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTblInners.setMaximumSize(new java.awt.Dimension(1000, 900));
        jTblInners.setMinimumSize(new java.awt.Dimension(1000, 900));
        jTblInners.setPreferredSize(new java.awt.Dimension(1000, 900));
        jScrollPane2.setViewportView(jTblInners);

        jPanel10.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 670, 97));

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 0, 690, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void jBtnRemoverInnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRemoverInnerActionPerformed
        new Thread(new Runnable() {

            @Override
            public void run() {
                if (jTblInners.getSelectedRowCount() > 0) {
                    DefaultTableModel dtm = (DefaultTableModel) jTblInners.getModel();
                    dtm.removeRow(jTblInners.getSelectedRow());
                    int linha = jTblInners.getSelectedRow();
                    OnlineControll.RemoverInner(Integer.parseInt(jTblInners.getValueAt(linha, 0).toString()));
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Selecione um Inner na lista !");
                }
            }

        }).start();
    }//GEN-LAST:event_jBtnRemoverInnerActionPerformed

    private void jBtnAdcionarInnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAdcionarInnerActionPerformed
        new Thread(new Runnable() {

            @Override
            public void run() {
                
                //Catraca
                if ((jCboEquipamento.getSelectedIndex() != Enumeradores.Acionamento_Coletor) && (!(jrdbDireita.isSelected()) && (!(jrdbEsquerda.isSelected())))) {
                    JOptionPane.showMessageDialog(null, "Favor informar o lado de instalação da catraca !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                DefaultTableModel tblListaInners = (DefaultTableModel) jTblInners.getModel();

                for (int i = 0; i < tblListaInners.getRowCount(); i++) {
                    if (jSpnNumInner.getValue().toString().equals(tblListaInners.getValueAt(i, 0).toString())) {
                        JOptionPane.showMessageDialog(rootPane, "Numero do Inner já existente !");
                        return;
                    }
                }

                tblListaInners.addRow(new Object[]{
                    jSpnNumInner.getValue(), jSpnQtdDigitos.getValue(), jChkTeclado.isSelected(), 
                    jChkLista.isSelected(), jChkListaBio.isSelected(), jCboTipoLeitor.getSelectedIndex(), 
                    jchkModuloLC.isSelected() == true ? "Módulo LC" : "Módulo LN", 
                    jChkIdentificacao.isSelected(), jChkVerificacao.isSelected(), jChkDoisLeitores.isSelected(), 
                    jCboEquipamento.getSelectedIndex() != Enumeradores.Acionamento_Coletor});

                jBtnIniciar.setEnabled(jTblInners.getRowCount() > 0);
                Inner AddInner = new Inner();
                AddInner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
                AddInner.EstadoTeclado = Enumeradores.EstadosTeclado.TECLADO_EM_BRANCO;
                AddInner.Numero = Integer.parseInt(jSpnNumInner.getValue().toString());
                AddInner.QtdDigitos = Integer.parseInt(jSpnQtdDigitos.getValue().toString());
                AddInner.Teclado = jChkTeclado.isSelected();
                AddInner.TipoLeitor = jCboTipoLeitor.getSelectedIndex();
                AddInner.Identificacao = jChkIdentificacao.isSelected() ? 1 : 0;
                AddInner.Verificacao = jChkVerificacao.isSelected() ? 1 : 0;
                AddInner.DoisLeitores = jChkDoisLeitores.isSelected();
                AddInner.VariacaoInner = 0;
                AddInner.Acionamento = jCboEquipamento.getSelectedIndex();
                AddInner.Catraca = AddInner.Acionamento != 0;
                AddInner.CatInvertida = jrdbEsquerda.isSelected();
                AddInner.PadraoCartao = jCboPadraoCartao.getSelectedIndex();
                AddInner.TipoConexao = jCboTipoConexao.getSelectedIndex();
                AddInner.Porta = Integer.parseInt(jSpnPorta.getValue().toString());
                AddInner.Biometrico = jChkBiometrico.isSelected();
                AddInner.Lista = jChkLista.isSelected();
                AddInner.ListaBio = jChkListaBio.isSelected();
                AddInner.TipoComBio = jchkModuloLC.isSelected() ? 1 : 0;
                OnlineControll.AdicionarInner(AddInner);
            }
        }).start();
    }//GEN-LAST:event_jBtnAdcionarInnerActionPerformed

    private void jBtnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimparActionPerformed
        //Exclui bilhetes coletados
        jTxaBilhetes.setText("");
    }//GEN-LAST:event_jBtnLimparActionPerformed

    private void jBtnSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaidaActionPerformed
        
        Inner inner = new Inner();
        if (inner.Catraca) {
            //Botão que efetua a Saída da Catraca
            OnlineControll.HabilitarLadoCatraca("Saida", false);
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;

            //Desabilita botões
            jBtnEntrada.setEnabled(false);
            jBtnSaida.setEnabled(false);
        } else {
            //Desabilita botões
            jBtnEntrada.setEnabled(false);
            jBtnSaida.setEnabled(false);
            jBtnEntrada.setEnabled(true);
            jBtnSaida.setEnabled(true);
        }
    }//GEN-LAST:event_jBtnSaidaActionPerformed

    private void jBtnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEntradaActionPerformed

        Inner inner = (Inner) OnlineControll.ListaInners.get(1);
        if (inner.Catraca) {
            //Botão que efetua a Entrada da Catraca
            OnlineControll.HabilitarLadoCatraca("Entrada", false);
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;

            //Desabilita botões
            jBtnEntrada.setEnabled(false);
            jBtnSaida.setEnabled(false);
        } else {
            //Desabilita botões
            jBtnEntrada.setEnabled(false);
            jBtnSaida.setEnabled(false);
            jBtnEntrada.setEnabled(true);
            jBtnSaida.setEnabled(true);
        }
    }//GEN-LAST:event_jBtnEntradaActionPerformed

    private void jBtnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIniciarActionPerformed
        //Botão que inicia a configuração e conexão
        try {
            UsersBio = new DAOUsuariosBio();
            //Users = 
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        OnlineControll.IniciarMaquinaEstados();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JIFEasyInnerOnLine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jBtnIniciarActionPerformed

    private void jBtnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPararActionPerformed
        OnlineControll.PararMaquinaOnline();
    }//GEN-LAST:event_jBtnPararActionPerformed

    private void jrdbDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrdbDireitaActionPerformed
        //imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/direita-java.JPG"))); // NOI18N
    }//GEN-LAST:event_jrdbDireitaActionPerformed

    private void jrdbEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrdbEsquerdaActionPerformed
        //imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/esquerda-java.JPG"))); // NOI18N
    }//GEN-LAST:event_jrdbEsquerdaActionPerformed

    private void jChkCartaoMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkCartaoMasterActionPerformed
        jTxtCartaoMaster.setEnabled(jChkCartaoMaster.isSelected());
    }//GEN-LAST:event_jChkCartaoMasterActionPerformed

    private void jChkIdentificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkIdentificacaoActionPerformed
        if (!jChkIdentificacao.isSelected() && !jChkVerificacao.isSelected()) {
            jChkBiometrico.setSelected(false);
        } else {
            jChkBiometrico.setSelected(true);
        }
    }//GEN-LAST:event_jChkIdentificacaoActionPerformed

    private void jChkVerificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkVerificacaoActionPerformed

    }//GEN-LAST:event_jChkVerificacaoActionPerformed

    private void jChkVerificacaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jChkVerificacaoStateChanged
        //Habilita/Desabilita campos
        if (!jChkVerificacao.isSelected()) {
            jChkListaBio.setSelected(false);
        }
    }//GEN-LAST:event_jChkVerificacaoStateChanged

    private void jChkBiometricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkBiometricoActionPerformed
        if (jChkBiometrico.isSelected()) {
            jChkVerificacao.setEnabled(true);
            jchkModuloLC.setEnabled(true);
            jChkIdentificacao.setEnabled(true);
            jChkListaBio.setEnabled(true);
            jChkIdentificacao.setSelected(true);
        } else {
            jChkVerificacao.setEnabled(false);
            jChkIdentificacao.setEnabled(false);
            jChkListaBio.setEnabled(false);
            jchkModuloLC.setEnabled(false);
            jChkIdentificacao.setSelected(false);
            jChkVerificacao.setSelected(false);
            jChkListaBio.setSelected(false);
            jchkModuloLC.setSelected(false);
        }
    }//GEN-LAST:event_jChkBiometricoActionPerformed

    private void jChkBiometricoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jChkBiometricoStateChanged
        //Habilita/Desabilita campos
        if (jChkBiometrico.isSelected()) {
            jChkIdentificacao.setEnabled(true);
            jChkVerificacao.setEnabled(true);
            jChkListaBio.setEnabled(true);
        } else {
            jChkVerificacao.setEnabled(false);
            jChkIdentificacao.setEnabled(false);
            jChkListaBio.setEnabled(false);
            jChkVerificacao.setSelected(false);
            jChkIdentificacao.setSelected(false);
            jChkListaBio.setSelected(false);
        }
    }//GEN-LAST:event_jChkBiometricoStateChanged

    private void jCboEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboEquipamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCboEquipamentoActionPerformed

    private void jCboEquipamentocboequipamento_itemstatechanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboEquipamentocboequipamento_itemstatechanged
        //Combo Equipamento
        //Carrega as opções de acordo com a seleção

        //Se catraca
        if ((jCboEquipamento.getSelectedIndex() != Enumeradores.Acionamento_Coletor)) {
            jrdbEsquerda.setEnabled(true);
            jrdbDireita.setEnabled(true);
            jChkDoisLeitores.setEnabled(true);

            //Se Urna
            if ((jCboEquipamento.getSelectedIndex() == Enumeradores.Acionamento_Catraca_Urna)) {
                jrdbDireita.setSelected(true);
                jrdbEsquerda.setEnabled(false);
                jrdbDireita.setEnabled(false);
                //imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/direita-java.JPG"))); // NOI18N
                jCboTipoLeitor.setSelectedIndex(4);//proximidade
                jChkDoisLeitores.setSelected(true);
            } else { //Não é Urna
                if (jrdbDireita.isSelected()) {
                    //imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/direita-java.JPG"))); // NOI18N
                } else {
                    if (jrdbEsquerda.isSelected()) {
                        //.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/esquerda-java.JPG"))); // NOI18N
                    }
                }
            }
        } else { //Coletor
            jrdbEsquerda.setEnabled(false);
            jrdbDireita.setEnabled(false);
            //imgCatraca.setIcon(new javax.swing.ImageIcon("C:\Users\GOVERNO\Downloads\MercuryFormsServer-master\src\main\java\com\topdata\easyInner\resources"))); // NOI18N
        }
    }//GEN-LAST:event_jCboEquipamentocboequipamento_itemstatechanged

    private void jCboTipoLeitorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCboTipoLeitorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jCboTipoLeitorMouseClicked

    private void jchkModuloLCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkModuloLCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchkModuloLCActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel imgCatraca;
    private javax.swing.JButton jBtnAdcionarInner;
    private javax.swing.JButton jBtnEntrada;
    private javax.swing.JButton jBtnIniciar;
    private javax.swing.JButton jBtnLimpar;
    public javax.swing.JButton jBtnParar;
    private javax.swing.JButton jBtnRemoverInner;
    private javax.swing.JButton jBtnSaida;
    private javax.swing.JComboBox jCboEquipamento;
    private javax.swing.JComboBox jCboPadraoCartao;
    private javax.swing.JComboBox jCboTipoConexao;
    private javax.swing.JComboBox jCboTipoLeitor;
    private javax.swing.JCheckBox jChkBiometrico;
    private javax.swing.JCheckBox jChkCartaoMaster;
    private javax.swing.JCheckBox jChkDoisLeitores;
    private javax.swing.JCheckBox jChkIdentificacao;
    private javax.swing.JCheckBox jChkLista;
    private javax.swing.JCheckBox jChkListaBio;
    private javax.swing.JCheckBox jChkTeclado;
    private javax.swing.JCheckBox jChkVerificacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLblDados;
    private javax.swing.JLabel jLblStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSpinner jSpnNumInner;
    private javax.swing.JSpinner jSpnPorta;
    private javax.swing.JSpinner jSpnQtdDigitos;
    private javax.swing.JTable jTblInners;
    private javax.swing.JTextArea jTxaBilhetes;
    private javax.swing.JTextArea jTxaVersao;
    private javax.swing.JTextField jTxtCartaoMaster;
    private javax.swing.JCheckBox jchkModuloLC;
    private javax.swing.JLabel jlblBilhetes;
    private javax.swing.JRadioButton jrdbDireita;
    private javax.swing.JRadioButton jrdbEsquerda;
    private javax.swing.JLabel lblDadosRec;
    private javax.swing.JLabel lblInners;
    private javax.swing.JLabel lblNumDig;
    private javax.swing.JLabel lblPorta;
    private javax.swing.JLabel lblStatuss;
    private javax.swing.JLabel lblTipoEquipamento;
    // End of variables declaration//GEN-END:variables

    private void InserirHorarioAcesso(byte bTime, byte bDay, byte bRange, byte bHour, byte bMin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void AtualizarBotoes(boolean b)
    {
        jBtnParar.setEnabled(!b);
        jBtnIniciar.setEnabled(b);
        jBtnAdcionarInner.setEnabled(b);
        jBtnRemoverInner.setEnabled(b);
    }

    public void AtualizarStatus(String status)
    {
        jLblStatus.setText(status);
    }

    public void AtualizarVersao(String versao)
    {
        jTxaVersao.append(versao);
    }
    
    public void LimparVersao()
    {
        jTxaVersao.setText("");
    }

    public void AtuaLizarBilhetes(String sBilheteDisplay)
    {
        jTxaBilhetes.append(sBilheteDisplay);
    }

    public void AtualizarDados(String msg)
    {
        jLblDados.setText(msg);
    }
}


