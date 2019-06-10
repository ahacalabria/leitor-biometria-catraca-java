//******************************************************************************
//A Topdata Sistemas de Automação Ltda não se responsabiliza por qualquer
//tipo de dano que este software possa causar, este exemplo deve ser utilizado
//apenas para demonstrar a comunicação com os equipamentos da linha Inner.
//
//Exemplo Off-Line
//Desenvolvido em Java.
//                                           Topdata Sistemas de Automação Ltda.
//******************************************************************************
package com.topdata.easyInner.ui;

import com.topdata.EasyInner;
import com.topdata.easyInner.controller.EasyInnerOffLineController;
import static com.topdata.easyInner.enumeradores.Enumeradores.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class JIFEasyInnerOffLine extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;

    //Declaração variáveis
    private final EasyInnerOffLineController offLineController = new EasyInnerOffLineController(this);

    /**
     * Inicialização formulário
     */
    @SuppressWarnings("unchecked")
    public JIFEasyInnerOffLine() throws InterruptedException {
        initComponents();

        //Carregamento das combos
        //Combo Tipo Conexão
        jCboTipoConexao.removeAllItems();
        jCboTipoConexao.addItem("Serial");
        jCboTipoConexao.addItem("TCP/IP");
        jCboTipoConexao.addItem("TCP/IP porta fixa");
        jCboTipoConexao.setSelectedIndex(2); //Default

        //Combo Tipo de Leitor
        jCboTipoLeitor.removeAllItems();
        jCboTipoLeitor.addItem("Código Barras");
        jCboTipoLeitor.addItem("Magnético");
        jCboTipoLeitor.addItem("Prox. Abatrack/Smart Card");
        jCboTipoLeitor.addItem("Prox. Wiegand/Smart Card");
        jCboTipoLeitor.addItem("Prox. Smart Card Serial");
        jCboTipoLeitor.addItem("Codigo barras serial");
        jCboTipoLeitor.addItem("Wiegand FC sem zero");
        jCboTipoLeitor.setSelectedIndex(0); //Default

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
        jCboEquipamento.setSelectedIndex(0); //Default

        //Inicialização dos campos
        jTxtNumInner.setText("1");
        jTxtDigitos.setText("14");
        jTxtPorta.setText("3570");

        jTxaVersao.setBackground(this.getBackground());
        jTxaVersao.setLineWrap(true);
        jTxaVersao.setWrapStyleWord(true);
     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        chkDoisLeitores = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTxaVersao = new javax.swing.JTextArea();
        lblTipoConexao = new javax.swing.JLabel();
        lblTipoLeitor = new javax.swing.JLabel();
        jCboTipoLeitor = new javax.swing.JComboBox();
        jCboTipoConexao = new javax.swing.JComboBox();
        lblTipoEquipamento = new javax.swing.JLabel();
        jCboEquipamento = new javax.swing.JComboBox();
        jRdbPadraoLivre = new javax.swing.JRadioButton();
        jRdbPadraoTopdata = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblCatraca = new javax.swing.JLabel();
        optEsquerda = new javax.swing.JRadioButton();
        optDireita = new javax.swing.JRadioButton();
        canvas1 = new java.awt.Canvas();
        imgCatraca = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblInner = new javax.swing.JLabel();
        jTxtNumInner = new javax.swing.JTextField();
        lblQdtDigitos = new javax.swing.JLabel();
        jTxtDigitos = new javax.swing.JTextField();
        lblPorta = new javax.swing.JLabel();
        jTxtPorta = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jChkHorarios = new javax.swing.JCheckBox();
        jChkLista = new javax.swing.JCheckBox();
        jChkHorariosSirene = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jChkBio = new javax.swing.JCheckBox();
        jChkListaBio = new javax.swing.JCheckBox();
        jChkVerificacao = new javax.swing.JCheckBox();
        jChkIdentificacao = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jChkRelogio = new javax.swing.JCheckBox();
        jChkTeclado = new javax.swing.JCheckBox();
        jChkMensagem = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jChkCartaoMaster = new javax.swing.JCheckBox();
        jTxtCartaoMaster = new javax.swing.JTextField();
        jChkModuloLC = new javax.swing.JCheckBox();
        jLblEnvia = new javax.swing.JLabel();
        jBtnEnviar = new javax.swing.JButton();
        jLblstatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxaBilhetes = new javax.swing.JTextArea();
        jBtnReceber = new javax.swing.JButton();
        jLblBilhetes = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jTextPane1);

        setClosable(true);
        setTitle("Exemplo OffLine EasyInner.dll");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Configurações"));
        jPanel4.setMinimumSize(new java.awt.Dimension(473, 153));

        chkDoisLeitores.setText("2 Leitores?");
        chkDoisLeitores.setEnabled(false);
        chkDoisLeitores.setName("chkLista"); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTxaVersao.setEditable(false);
        jTxaVersao.setColumns(1);
        jTxaVersao.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTxaVersao.setLineWrap(true);
        jTxaVersao.setRows(1);
        jTxaVersao.setWrapStyleWord(true);
        jTxaVersao.setAutoscrolls(false);
        jTxaVersao.setBorder(null);
        jTxaVersao.setSelectionColor(new java.awt.Color(236, 233, 216));
        jScrollPane2.setViewportView(jTxaVersao);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblTipoConexao.setText("Tipo Conexão:");

        lblTipoLeitor.setText("Tipo Leitor:");

        jCboTipoLeitor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboTipoLeitorItemStateChanged(evt);
            }
        });
        jCboTipoLeitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboTipoLeitorActionPerformed(evt);
            }
        });

        lblTipoEquipamento.setText("Tipo Equipamento:");

        jCboEquipamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboequipamento_itemstatechanged(evt);
            }
        });

        jRdbPadraoLivre.setSelected(true);
        jRdbPadraoLivre.setText("Livre");
        jRdbPadraoLivre.setName("optPadrao"); // NOI18N
        jRdbPadraoLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRdbPadraoLivreActionPerformed(evt);
            }
        });

        jRdbPadraoTopdata.setText("Topdata");
        jRdbPadraoTopdata.setName("optPadrao"); // NOI18N
        jRdbPadraoTopdata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRdbPadraoTopdataMouseClicked(evt);
            }
        });
        jRdbPadraoTopdata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRdbPadraoTopdataActionPerformed(evt);
            }
        });

        jLabel1.setText("Parâmetros:");

        jLabel3.setText("Padrão Cartão:");

        lblCatraca.setLabelFor(this);
        lblCatraca.setText("Ao entrar, a catraca está instalada à sua:");
        lblCatraca.setEnabled(false);
        lblCatraca.setName(""); // NOI18N

        buttonGroup2.add(optEsquerda);
        optEsquerda.setText("Esquerda");
        optEsquerda.setEnabled(false);
        optEsquerda.setName("optCatraca"); // NOI18N
        optEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optEsquerdaActionPerformed(evt);
            }
        });

        buttonGroup2.add(optDireita);
        optDireita.setText("Direita");
        optDireita.setEnabled(false);
        optDireita.setName("optCatraca"); // NOI18N
        optDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optDireitaActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridLayout(3, 0));

        lblInner.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblInner.setText("Número Inner:");
        jPanel2.add(lblInner);
        jPanel2.add(jTxtNumInner);
        jTxtNumInner.getAccessibleContext().setAccessibleParent(this);

        lblQdtDigitos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblQdtDigitos.setText("Número de Dígitos:");
        jPanel2.add(lblQdtDigitos);
        jPanel2.add(jTxtDigitos);

        lblPorta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPorta.setText("Porta:");
        jPanel2.add(lblPorta);
        jPanel2.add(jTxtPorta);

        jPanel8.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        jPanel7.setLayout(new java.awt.GridLayout(3, 0, 0, 3));

        jChkHorarios.setText("Horários");
        jChkHorarios.setName("jChkHorarios"); // NOI18N
        jPanel7.add(jChkHorarios);

        jChkLista.setText("Lista");
        jChkLista.setName("jChkLista"); // NOI18N
        jPanel7.add(jChkLista);

        jChkHorariosSirene.setText("Sirene");
        jChkHorariosSirene.setName("jChkHorariosSirene"); // NOI18N
        jPanel7.add(jChkHorariosSirene);

        jPanel8.add(jPanel7);

        jPanel6.setLayout(new java.awt.GridLayout(4, 0, 0, 3));

        jChkBio.setText("Biometria");
        jChkBio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkBioActionPerformed(evt);
            }
        });
        jPanel6.add(jChkBio);

        jChkListaBio.setText("Lista sem Bio");
        jChkListaBio.setEnabled(false);
        jChkListaBio.setName("chkDoisLeitores"); // NOI18N
        jChkListaBio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkListaBioActionPerformed(evt);
            }
        });
        jPanel6.add(jChkListaBio);

        jChkVerificacao.setText("Verificação");
        jChkVerificacao.setEnabled(false);
        jChkVerificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkVerificacaoActionPerformed(evt);
            }
        });
        jPanel6.add(jChkVerificacao);

        jChkIdentificacao.setText("Identificação");
        jChkIdentificacao.setEnabled(false);
        jChkIdentificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkIdentificacaoActionPerformed(evt);
            }
        });
        jPanel6.add(jChkIdentificacao);

        jPanel8.add(jPanel6);

        jPanel3.setLayout(new java.awt.GridLayout(3, 0, 0, 3));

        jChkRelogio.setSelected(true);
        jChkRelogio.setText("Relógio");
        jChkRelogio.setName("jChkRelogio"); // NOI18N
        jPanel3.add(jChkRelogio);

        jChkTeclado.setSelected(true);
        jChkTeclado.setText("Teclado");
        jChkTeclado.setName("chkDoisLeitores"); // NOI18N
        jPanel3.add(jChkTeclado);

        jChkMensagem.setSelected(true);
        jChkMensagem.setText("Mensagens");
        jChkMensagem.setName("jChkMensagem"); // NOI18N
        jPanel3.add(jChkMensagem);

        jPanel8.add(jPanel3);

        jChkCartaoMaster.setText("Cartão Master");
        jChkCartaoMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkCartaoMasterActionPerformed(evt);
            }
        });

        jTxtCartaoMaster.setText("0");
        jTxtCartaoMaster.setToolTipText("");
        jTxtCartaoMaster.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jTxtCartaoMaster))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jChkCartaoMaster)
                        .addGap(0, 20, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jChkCartaoMaster)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTxtCartaoMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jChkModuloLC.setText("Módulo LC");
        jChkModuloLC.setEnabled(false);
        jChkModuloLC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkModuloLCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTipoConexao)
                                        .addComponent(lblTipoLeitor))
                                    .addGap(31, 31, 31))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(lblTipoEquipamento)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jCboTipoConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jRdbPadraoLivre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jRdbPadraoTopdata))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jCboEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jCboTipoLeitor, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chkDoisLeitores)
                                    .addGap(12, 12, 12)
                                    .addComponent(imgCatraca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblCatraca)
                                .addGap(18, 18, 18)
                                .addComponent(optEsquerda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(optDireita)
                                .addGap(72, 72, 72))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jChkModuloLC, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTipoConexao)
                                .addComponent(jCboTipoConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRdbPadraoTopdata, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRdbPadraoLivre))))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoEquipamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCboTipoLeitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkDoisLeitores)
                            .addComponent(lblTipoLeitor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCatraca)
                            .addComponent(optEsquerda)
                            .addComponent(optDireita))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(imgCatraca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jChkModuloLC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("");
        jChkModuloLC.getAccessibleContext().setAccessibleName("ModuloLC");

        jBtnEnviar.setLabel("Enviar Configurações");
        jBtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEnviarActionPerformed(evt);
            }
        });

        jLblstatus.setText("Bilhetes coletados");

        jTxaBilhetes.setColumns(20);
        jTxaBilhetes.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTxaBilhetes.setLineWrap(true);
        jScrollPane1.setViewportView(jTxaBilhetes);

        jBtnReceber.setText("Receber Bilhetes");
        jBtnReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReceberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnReceber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblBilhetes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(79, 79, 79))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtnEnviar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLblstatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLblEnvia, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnEnviar)
                            .addComponent(jLblstatus)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblEnvia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jBtnReceber))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblBilhetes, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnviarActionPerformed

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    offLineController.enviarConfiguracoes();
                } catch (Exception ex) {
                    Logger.getLogger(JIFEasyInnerOffLine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

    }//GEN-LAST:event_jBtnEnviarActionPerformed

    private void jCboTipoLeitorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboTipoLeitorItemStateChanged
        //Se tipo leitor for de proximidade habilita opção 2 leitores
        chkDoisLeitores.setEnabled(!(jCboTipoLeitor.getSelectedIndex() == CODIGO_DE_BARRAS)
                && !(jCboTipoLeitor.getSelectedIndex() == MAGNETICO));
        chkDoisLeitores.setSelected(false);
    }//GEN-LAST:event_jCboTipoLeitorItemStateChanged

    private void jBtnReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReceberActionPerformed
        new Thread(new Runnable() {

            @Override
            public void run() {
                offLineController.coletarBilhetes();
            }
        }).start();

    }//GEN-LAST:event_jBtnReceberActionPerformed

    private void jRdbPadraoTopdataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdbPadraoTopdataActionPerformed
        //Habilita/Desabilita campos
        jRdbPadraoTopdata.setSelected(jRdbPadraoTopdata.isSelected());

    }//GEN-LAST:event_jRdbPadraoTopdataActionPerformed

    private void jRdbPadraoLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdbPadraoLivreActionPerformed
        //Habilita/Desabilita campos
        if (jRdbPadraoLivre.isSelected()) {
            jRdbPadraoTopdata.setSelected(false);
        }
    }//GEN-LAST:event_jRdbPadraoLivreActionPerformed

    private void jChkBioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkBioActionPerformed
        //Habilita/Desabilita campos Bio
        if (jChkBio.isSelected()) {
            jChkVerificacao.setEnabled(true);
            jChkIdentificacao.setEnabled(true);
            jChkListaBio.setEnabled(true);
            jChkIdentificacao.setSelected(true);
            jChkModuloLC.setEnabled(true);
        } else {
            jChkVerificacao.setEnabled(false);
            jChkIdentificacao.setEnabled(false);
            jChkListaBio.setEnabled(false);
            jChkIdentificacao.setSelected(false);
            jChkVerificacao.setSelected(false);
            jChkListaBio.setSelected(false);
            jChkModuloLC.setEnabled(false);
            jChkModuloLC.setSelected(false);
        }
    }//GEN-LAST:event_jChkBioActionPerformed

    private void jChkVerificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkVerificacaoActionPerformed
        //Habilita/Desabilita campos
        if (!jChkVerificacao.isSelected()) {
            jChkListaBio.setSelected(false);
            jChkBio.setSelected(false);
        } else {
            jChkBio.setSelected(true);
        }

}//GEN-LAST:event_jChkVerificacaoActionPerformed

    private void jChkListaBioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkListaBioActionPerformed
        //Habilita/Desabilita campos
        if (jChkListaBio.isSelected()) {
            jChkVerificacao.setSelected(true);
            jChkBio.setSelected(true);
        }
}//GEN-LAST:event_jChkListaBioActionPerformed

    private void cboequipamento_itemstatechanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboequipamento_itemstatechanged
        //Seleção combo Equipamento
        //Habilita/Desabilita campos conforme seleção
        if ((jCboEquipamento.getSelectedIndex() != Acionamento_Coletor)) {
            optEsquerda.setEnabled(true);
            optDireita.setEnabled(true);
            chkDoisLeitores.setEnabled(true);

            if ((jCboEquipamento.getSelectedIndex() == Acionamento_Catraca_Urna)) {
                optDireita.setSelected(true);
                optEsquerda.setEnabled(false);
                optDireita.setEnabled(false);
                imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/direita-java.JPG"))); // NOI18N
                lblCatraca.setEnabled(true);
                jCboTipoLeitor.setSelectedIndex(4);//proximidade
                chkDoisLeitores.setSelected(true);
            } else {
                if (optDireita.isSelected()) {
                    imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/direita-java.JPG"))); // NOI18N
                } else {
                    if (optEsquerda.isSelected()) {
                        imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/esquerda-java.JPG"))); // NOI18N
                    }
                }
                lblCatraca.setEnabled(true);
            }
        } else {
            optEsquerda.setEnabled(false);
            optDireita.setEnabled(false);
            lblCatraca.setEnabled(false);
            imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/nenhum.JPG"))); // NOI18N
        }

    }//GEN-LAST:event_cboequipamento_itemstatechanged

    private void optEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optEsquerdaActionPerformed
        imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/esquerda-java.JPG"))); // NOI18N

    }//GEN-LAST:event_optEsquerdaActionPerformed

    private void optDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optDireitaActionPerformed
        imgCatraca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/topdata/easyInner/resources/direita-java.JPG"))); // NOI18N
    }//GEN-LAST:event_optDireitaActionPerformed

    private void jCboTipoLeitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboTipoLeitorActionPerformed
        if (jCboTipoLeitor.getSelectedIndex() == 2) {
            jTxtDigitos.setText("14");
        } else if (jCboTipoLeitor.getSelectedIndex() == 3) {
            jTxtDigitos.setText("6");
        }
    }//GEN-LAST:event_jCboTipoLeitorActionPerformed

    private void jChkIdentificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkIdentificacaoActionPerformed
        if (!jChkIdentificacao.isSelected() && !jChkVerificacao.isSelected()) {
            jChkBio.setSelected(false);
        } else {
            jChkBio.setSelected(true);
        }
    }//GEN-LAST:event_jChkIdentificacaoActionPerformed

    private void jChkCartaoMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkCartaoMasterActionPerformed
        jTxtCartaoMaster.setEnabled(jChkCartaoMaster.isSelected());
    }//GEN-LAST:event_jChkCartaoMasterActionPerformed

    private void jRdbPadraoTopdataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRdbPadraoTopdataMouseClicked
        JOptionPane.showMessageDialog(rootPane, "Este tipo de cartão é para uso exclusivo de cartões fabricado pela Topdata !");
    }//GEN-LAST:event_jRdbPadraoTopdataMouseClicked

    private void jChkModuloLCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkModuloLCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jChkModuloLCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private java.awt.Canvas canvas1;
    public javax.swing.JCheckBox chkDoisLeitores;
    private javax.swing.JLabel imgCatraca;
    public javax.swing.JButton jBtnEnviar;
    public javax.swing.JButton jBtnReceber;
    public javax.swing.JComboBox jCboEquipamento;
    public javax.swing.JComboBox jCboTipoConexao;
    public javax.swing.JComboBox jCboTipoLeitor;
    public javax.swing.JCheckBox jChkBio;
    public javax.swing.JCheckBox jChkCartaoMaster;
    public javax.swing.JCheckBox jChkHorarios;
    public javax.swing.JCheckBox jChkHorariosSirene;
    public javax.swing.JCheckBox jChkIdentificacao;
    public javax.swing.JCheckBox jChkLista;
    public javax.swing.JCheckBox jChkListaBio;
    public javax.swing.JCheckBox jChkMensagem;
    public javax.swing.JCheckBox jChkModuloLC;
    public javax.swing.JCheckBox jChkRelogio;
    public javax.swing.JCheckBox jChkTeclado;
    public javax.swing.JCheckBox jChkVerificacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLblBilhetes;
    public javax.swing.JLabel jLblEnvia;
    public javax.swing.JLabel jLblstatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public javax.swing.JRadioButton jRdbPadraoLivre;
    public javax.swing.JRadioButton jRdbPadraoTopdata;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    public javax.swing.JTextArea jTxaBilhetes;
    public javax.swing.JTextArea jTxaVersao;
    public javax.swing.JTextField jTxtCartaoMaster;
    public javax.swing.JTextField jTxtDigitos;
    public javax.swing.JTextField jTxtNumInner;
    public javax.swing.JTextField jTxtPorta;
    private javax.swing.JLabel lblCatraca;
    private javax.swing.JLabel lblInner;
    private javax.swing.JLabel lblPorta;
    private javax.swing.JLabel lblQdtDigitos;
    private javax.swing.JLabel lblTipoConexao;
    private javax.swing.JLabel lblTipoEquipamento;
    private javax.swing.JLabel lblTipoLeitor;
    public javax.swing.JRadioButton optDireita;
    public javax.swing.JRadioButton optEsquerda;
    // End of variables declaration//GEN-END:variables

}
