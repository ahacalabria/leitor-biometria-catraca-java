//******************************************************************************
//A Topdata Sistemas de Automação Ltda não se responsabiliza por qualquer
//tipo de dano que este software possa causar, este exemplo deve ser utilizado
//apenas para demonstrar a comunicação com os equipamentos da linha Inner.
//
//Exemplo Biometria
//Desenvolvido em Java.
//                                           Topdata Sistemas de Automação Ltda.
//******************************************************************************

package com.topdata.easyInner.ui;

import com.topdata.easyInner.controller.EasyInnerBioController;
import com.topdata.easyInner.dao.DAOUsuariosBio;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.*;
import javax.swing.JOptionPane;

public class JIFEasyInnerBio extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;

    //Declaração de variáveis
    private final EasyInnerBioController bioController = new EasyInnerBioController(this);

    /**

     *
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "unchecked"})
    public JIFEasyInnerBio() throws Exception {

        initComponents();

        //Carregamento das Combos
        //Combo Tipo Conexão
        jCboTipoConexao.removeAllItems();
        jCboTipoConexao.addItem("Serial");
        jCboTipoConexao.addItem("TCP/IP");
        jCboTipoConexao.addItem("TCP/IP porta fixa");
        jCboTipoConexao.setSelectedIndex(2);    //Default

        //Combo Tipo Leitor
        jCboTipoLeitor.removeAllItems();
        jCboTipoLeitor.addItem("Código Barras");
        jCboTipoLeitor.addItem("Magnético");
        jCboTipoLeitor.addItem("Prox. Abatrack/Smart Card");
        jCboTipoLeitor.addItem("Prox. Wiegand/Smart Card");
        jCboTipoLeitor.addItem("Prox. Smart Card Serial");
        jCboTipoLeitor.addItem("Codigo barras serial");
        jCboTipoLeitor.addItem("Wiegand FC sem zero");
        jCboTipoLeitor.setSelectedIndex(0);     //Default

        //Combo Padrão Cartão
        jCboPadraoCartao.removeAllItems();
        jCboPadraoCartao.addItem("Topdata");
        jCboPadraoCartao.addItem("Livre");
        jCboPadraoCartao.setSelectedIndex(1);   //Default

        //Inicialização campos
        jTxtNumInner.setText("1");
        jTxtQtdeDigitos.setText("16");
        jTxtPorta.setText("3570");

        bioController.carregarGridManutencao();
        bioController.CarregarGridSD();
        bioController.carregarHamster();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jTbpBiometria = new javax.swing.JTabbedPane();
        tbConfiguracao = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jBtnReceberModelo = new javax.swing.JButton();
        jBtnReceberVersao = new javax.swing.JButton();
        jTxtNumInner = new javax.swing.JTextField();
        jTxtPorta = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCboTipoConexao = new javax.swing.JComboBox();
        jCboPadraoCartao = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jCboTipoLeitor = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jTxtQtdeDigitos = new javax.swing.JTextField();
        chkHabVerificacao = new javax.swing.JCheckBox();
        chkHabIdentificacao = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        cmdConfigurarInner = new javax.swing.JButton();
        jchkBio16Digitos = new javax.swing.JCheckBox();
        jBtnEnviarAjustesBio = new javax.swing.JButton();
        jTxtTimeoutIdent = new javax.swing.JTextField();
        jTxtNivelLFD = new javax.swing.JTextField();
        jlblTimeouIndet = new javax.swing.JLabel();
        jlblNivelLFD = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        LblStatusConf = new javax.swing.JLabel();
        tbManutencaoUsuarioBio = new javax.swing.JDesktopPane();
        jPnlUsuariosInner = new javax.swing.JPanel();
        jbtnExcluirSelecionadosBase = new javax.swing.JButton();
        jbtnEnviarTemplates = new javax.swing.JButton();
        jScrUsuariosBase = new javax.swing.JScrollPane();
        jTblUsuariosBase = new javax.swing.JTable();
        jPnlUsuariosBase = new javax.swing.JPanel();
        jbtnReceberTemplates = new javax.swing.JButton();
        jbtnExcluirSelecionadosInner = new javax.swing.JButton();
        jScrUsuariosInner = new javax.swing.JScrollPane();
        jTUsuariosInner = new javax.swing.JTable();
        jScrManutencao = new javax.swing.JScrollPane();
        jTxaManutencao = new javax.swing.JTextArea();
        jbtnReceberQtdeUsuarios = new javax.swing.JButton();
        jPnlQtdDigital = new javax.swing.JPanel();
        jRdbDigital1 = new javax.swing.JRadioButton();
        jRdbDigital2 = new javax.swing.JRadioButton();
        jbtnAtualizarUsuarios = new javax.swing.JButton();
        pnlHamster = new javax.swing.JPanel();
        pnlConfQualidade = new javax.swing.JPanel();
        pnlCapHamster = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jCboDispositivos = new javax.swing.JComboBox();
        jBtnIniciar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTxtCartao = new javax.swing.JTextField();
        jBtnCapturar = new javax.swing.JButton();
        pnlConfiguracoesQual = new javax.swing.JPanel();
        chkPopup = new javax.swing.JCheckBox();
        sldValorVerify = new javax.swing.JSlider();
        lblMinVerify = new javax.swing.JLabel();
        lblValorVerify = new javax.swing.JLabel();
        sldValorDigital = new javax.swing.JSlider();
        lblMinDigital = new javax.swing.JLabel();
        lblValorDigital = new javax.swing.JLabel();
        jchkEnviarInner = new javax.swing.JCheckBox();
        jlblInfoCapHamster = new javax.swing.JLabel();
        jpnImagem = new javax.swing.JPanel();
        cnvImgDigital = new java.awt.Canvas();
        lblQualidadeImagem = new javax.swing.JLabel();
        lblValorQualImagem = new javax.swing.JLabel();
        tbUsuarioSemDigital = new javax.swing.JPanel();
        jPnlUsuariosSemBio = new javax.swing.JPanel();
        jBtnEnviarLista = new javax.swing.JButton();
        jScrUsuariosSemBio = new javax.swing.JScrollPane();
        jTblUsuarioSemBio = new javax.swing.JTable();
        jbtnAdicionarUsuarioSD = new javax.swing.JButton();
        jScrManutencao2 = new javax.swing.JScrollPane();
        jTxaUsuarioSD = new javax.swing.JTextArea();
        jtxtUsuarioSD = new javax.swing.JTextField();
        tbCadastroInner = new javax.swing.JPanel();
        jPnlCMDDiretoInnerBio = new javax.swing.JPanel();
        jLblUsuario = new javax.swing.JLabel();
        jTxtUsuarioCadInner = new javax.swing.JTextField();
        jBtnCadastrar = new javax.swing.JButton();
        jBtnSolicitarDigtal = new javax.swing.JButton();
        jScrManutencao1 = new javax.swing.JScrollPane();
        jTxaCadastroInner = new javax.swing.JTextArea();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setTitle("Exemplo Teste Biometria");

        jTbpBiometria.setVerifyInputWhenFocusTarget(false);

        tbConfiguracao.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Número do inner:");

        jLabel5.setText("Porta:");

        jBtnReceberModelo.setText("Receber modelo bio");
        jBtnReceberModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReceberModeloActionPerformed(evt);
            }
        });

        jBtnReceberVersao.setText("Receber versão bio");
        jBtnReceberVersao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReceberVersaoActionPerformed(evt);
            }
        });

        jTxtNumInner.setText("1");

        jTxtPorta.setText("3570");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(104, 104, 104)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtNumInner)
                    .addComponent(jTxtPorta, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addGap(81, 81, 81)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnReceberVersao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnReceberModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBtnReceberModelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnReceberVersao))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTxtNumInner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Tipo Conexão:");

        jLabel7.setText("Padrão Cartão:");

        jLabel8.setText("Tipo Leitor:");

        jLabel9.setText("Número de Dígitos:");

        chkHabVerificacao.setSelected(true);
        chkHabVerificacao.setText("Habilitar verificação biométrica");

        chkHabIdentificacao.setSelected(true);
        chkHabIdentificacao.setText("Habilitar identificação biométrica");

        jLabel1.setText("Biometria:");

        cmdConfigurarInner.setText("Configurar inner");
        cmdConfigurarInner.setPreferredSize(new java.awt.Dimension(127, 23));
        cmdConfigurarInner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdConfigurarInnerActionPerformed(evt);
            }
        });

        jchkBio16Digitos.setText("Biometria mais de 10 digitos");

        jBtnEnviarAjustesBio.setText("Enviar Ajustes Bio");
        jBtnEnviarAjustesBio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEnviarAjustesBioActionPerformed(evt);
            }
        });

        jTxtTimeoutIdent.setText("70");

        jTxtNivelLFD.setText("0");

        jlblTimeouIndet.setText("Timeout Indet:");

        jlblNivelLFD.setText("Nivel LFD:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkHabIdentificacao, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(jCboPadraoCartao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCboTipoLeitor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCboTipoConexao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTxtQtdeDigitos, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmdConfigurarInner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkHabVerificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jchkBio16Digitos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 40, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblTimeouIndet)
                            .addComponent(jlblNivelLFD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtNivelLFD, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTxtTimeoutIdent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)))
                    .addComponent(jBtnEnviarAjustesBio, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addGap(65, 65, 65))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtQtdeDigitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboTipoConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboTipoLeitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTxtTimeoutIdent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblTimeouIndet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboPadraoCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTxtNivelLFD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblNivelLFD))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(chkHabVerificacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkHabIdentificacao))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkBio16Digitos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEnviarAjustesBio)
                    .addComponent(cmdConfigurarInner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        jTxtTimeoutIdent.getAccessibleContext().setAccessibleDescription("");

        jPanel9.setBackground(new java.awt.Color(153, 255, 255));

        LblStatusConf.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LblStatusConf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblStatusConf.setText("Selecione um comando.");
        LblStatusConf.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblStatusConf, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblStatusConf)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tbConfiguracao.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbConfiguracao.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbConfiguracao.setLayer(jPanel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout tbConfiguracaoLayout = new javax.swing.GroupLayout(tbConfiguracao);
        tbConfiguracao.setLayout(tbConfiguracaoLayout);
        tbConfiguracaoLayout.setHorizontalGroup(
            tbConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbConfiguracaoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(tbConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        tbConfiguracaoLayout.setVerticalGroup(
            tbConfiguracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbConfiguracaoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jTbpBiometria.addTab("Configurações", tbConfiguracao);

        tbManutencaoUsuarioBio.setBackground(new java.awt.Color(255, 255, 255));

        jPnlUsuariosInner.setBorder(javax.swing.BorderFactory.createTitledBorder("Digitais na base de dados"));

        jbtnExcluirSelecionadosBase.setText("Excluir selecionados");
        jbtnExcluirSelecionadosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExcluirSelecionadosBaseActionPerformed(evt);
            }
        });

        jbtnEnviarTemplates.setText("Enviar templates >>");
        jbtnEnviarTemplates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEnviarTemplatesActionPerformed(evt);
            }
        });

        jTblUsuariosBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cartão", "Template 1", "Template 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblUsuariosBase.getTableHeader().setReorderingAllowed(false);
        jScrUsuariosBase.setViewportView(jTblUsuariosBase);

        javax.swing.GroupLayout jPnlUsuariosInnerLayout = new javax.swing.GroupLayout(jPnlUsuariosInner);
        jPnlUsuariosInner.setLayout(jPnlUsuariosInnerLayout);
        jPnlUsuariosInnerLayout.setHorizontalGroup(
            jPnlUsuariosInnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlUsuariosInnerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPnlUsuariosInnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnExcluirSelecionadosBase, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEnviarTemplates, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrUsuariosBase, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPnlUsuariosInnerLayout.setVerticalGroup(
            jPnlUsuariosInnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlUsuariosInnerLayout.createSequentialGroup()
                .addComponent(jScrUsuariosBase, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnEnviarTemplates, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnExcluirSelecionadosBase, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPnlUsuariosBase.setBorder(javax.swing.BorderFactory.createTitledBorder("Digitais no Inner"));

        jbtnReceberTemplates.setText("<< Receber templates");
        jbtnReceberTemplates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReceberTemplatesActionPerformed(evt);
            }
        });

        jbtnExcluirSelecionadosInner.setText("Excluir selecionados");
        jbtnExcluirSelecionadosInner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExcluirSelecionadosInnerActionPerformed(evt);
            }
        });

        jTUsuariosInner.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuarios"
            }
        ));
        jScrUsuariosInner.setViewportView(jTUsuariosInner);

        javax.swing.GroupLayout jPnlUsuariosBaseLayout = new javax.swing.GroupLayout(jPnlUsuariosBase);
        jPnlUsuariosBase.setLayout(jPnlUsuariosBaseLayout);
        jPnlUsuariosBaseLayout.setHorizontalGroup(
            jPnlUsuariosBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlUsuariosBaseLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPnlUsuariosBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnReceberTemplates, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(jbtnExcluirSelecionadosInner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(90, 90, 90))
            .addGroup(jPnlUsuariosBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrUsuariosInner, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlUsuariosBaseLayout.setVerticalGroup(
            jPnlUsuariosBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlUsuariosBaseLayout.createSequentialGroup()
                .addComponent(jScrUsuariosInner, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jbtnReceberTemplates, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jbtnExcluirSelecionadosInner, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTxaManutencao.setBackground(new java.awt.Color(153, 255, 255));
        jTxaManutencao.setColumns(20);
        jTxaManutencao.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jTxaManutencao.setLineWrap(true);
        jTxaManutencao.setText("Selecione um comando");
        jTxaManutencao.setAutoscrolls(false);
        jScrManutencao.setViewportView(jTxaManutencao);

        jbtnReceberQtdeUsuarios.setText("Receber Quantidade Usuários Bio");
        jbtnReceberQtdeUsuarios.setActionCommand("Receber Quantidade de Usuários Bio");
        jbtnReceberQtdeUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReceberQtdeUsuariosActionPerformed(evt);
            }
        });

        jPnlQtdDigital.setBorder(javax.swing.BorderFactory.createTitledBorder("Inner Versão > 5.xx"));

        jRdbDigital1.setText("1 Digital");
        jRdbDigital1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRdbDigital1ActionPerformed(evt);
            }
        });

        jRdbDigital2.setSelected(true);
        jRdbDigital2.setText("2 Digital");
        jRdbDigital2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRdbDigital2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlQtdDigitalLayout = new javax.swing.GroupLayout(jPnlQtdDigital);
        jPnlQtdDigital.setLayout(jPnlQtdDigitalLayout);
        jPnlQtdDigitalLayout.setHorizontalGroup(
            jPnlQtdDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlQtdDigitalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jRdbDigital1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRdbDigital2)
                .addGap(26, 26, 26))
        );
        jPnlQtdDigitalLayout.setVerticalGroup(
            jPnlQtdDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlQtdDigitalLayout.createSequentialGroup()
                .addGroup(jPnlQtdDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRdbDigital2)
                    .addComponent(jRdbDigital1))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jbtnAtualizarUsuarios.setText("Atualizar usuários");
        jbtnAtualizarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAtualizarUsuariosActionPerformed(evt);
            }
        });

        tbManutencaoUsuarioBio.setLayer(jPnlUsuariosInner, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbManutencaoUsuarioBio.setLayer(jPnlUsuariosBase, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbManutencaoUsuarioBio.setLayer(jScrManutencao, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbManutencaoUsuarioBio.setLayer(jbtnReceberQtdeUsuarios, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbManutencaoUsuarioBio.setLayer(jPnlQtdDigital, javax.swing.JLayeredPane.DEFAULT_LAYER);
        tbManutencaoUsuarioBio.setLayer(jbtnAtualizarUsuarios, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout tbManutencaoUsuarioBioLayout = new javax.swing.GroupLayout(tbManutencaoUsuarioBio);
        tbManutencaoUsuarioBio.setLayout(tbManutencaoUsuarioBioLayout);
        tbManutencaoUsuarioBioLayout.setHorizontalGroup(
            tbManutencaoUsuarioBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbManutencaoUsuarioBioLayout.createSequentialGroup()
                .addGroup(tbManutencaoUsuarioBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbManutencaoUsuarioBioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tbManutencaoUsuarioBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPnlQtdDigital, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnReceberQtdeUsuarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbManutencaoUsuarioBioLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrManutencao, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(tbManutencaoUsuarioBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tbManutencaoUsuarioBioLayout.createSequentialGroup()
                        .addComponent(jPnlUsuariosInner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jPnlUsuariosBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtnAtualizarUsuarios))
                .addContainerGap())
        );
        tbManutencaoUsuarioBioLayout.setVerticalGroup(
            tbManutencaoUsuarioBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbManutencaoUsuarioBioLayout.createSequentialGroup()
                .addComponent(jbtnAtualizarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tbManutencaoUsuarioBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbManutencaoUsuarioBioLayout.createSequentialGroup()
                        .addComponent(jScrManutencao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(220, 220, 220)
                        .addComponent(jbtnReceberQtdeUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPnlQtdDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbManutencaoUsuarioBioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPnlUsuariosBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbManutencaoUsuarioBioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPnlUsuariosInner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jTbpBiometria.addTab("Manutenção Usuário Bio", tbManutencaoUsuarioBio);

        pnlConfQualidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlCapHamster.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setText("Dispositivo:");

        jCboDispositivos.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jCboDispositivos_click(evt);
            }
        });
        jCboDispositivos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboDispositivosItemStateChanged(evt);
            }
        });

        jBtnIniciar.setText("Iniciar");
        jBtnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIniciarActionPerformed(evt);
            }
        });

        jLabel13.setText("Cartão:");

        jTxtCartao.setEnabled(false);

        jBtnCapturar.setText("Capturar");
        jBtnCapturar.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jBtnCapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCapturarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCapHamsterLayout = new javax.swing.GroupLayout(pnlCapHamster);
        pnlCapHamster.setLayout(pnlCapHamsterLayout);
        pnlCapHamsterLayout.setHorizontalGroup(
            pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapHamsterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addGroup(pnlCapHamsterLayout.createSequentialGroup()
                        .addGroup(pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCboDispositivos, 0, 136, Short.MAX_VALUE)
                            .addComponent(jTxtCartao))
                        .addGap(18, 21, Short.MAX_VALUE)
                        .addGroup(pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBtnCapturar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlCapHamsterLayout.setVerticalGroup(
            pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapHamsterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(1, 1, 1)
                .addGroup(pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboDispositivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnIniciar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCapHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxtCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCapturar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlConfiguracoesQual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        chkPopup.setLabel("Popup");

        sldValorVerify.setValue(85);
        sldValorVerify.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldValorVerifyStateChanged(evt);
            }
        });

        lblMinVerify.setText("Valor minimo verify");

        lblValorVerify.setText("85");

        sldValorDigital.setMaximum(5);
        sldValorDigital.setValue(3);
        sldValorDigital.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldValorDigitalStateChanged(evt);
            }
        });

        lblMinDigital.setText("Valor minimo digital");

        lblValorDigital.setText("3");

        javax.swing.GroupLayout pnlConfiguracoesQualLayout = new javax.swing.GroupLayout(pnlConfiguracoesQual);
        pnlConfiguracoesQual.setLayout(pnlConfiguracoesQualLayout);
        pnlConfiguracoesQualLayout.setHorizontalGroup(
            pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addComponent(lblMinVerify)
                        .addGap(43, 43, 43)
                        .addComponent(lblValorVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkPopup))
                    .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                        .addGroup(pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sldValorVerify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMinDigital)
                                .addGap(43, 43, 43)
                                .addComponent(lblValorDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sldValorDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlConfiguracoesQualLayout.setVerticalGroup(
            pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkPopup)
                    .addGroup(pnlConfiguracoesQualLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMinVerify)
                            .addComponent(lblValorVerify))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sldValorVerify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlConfiguracoesQualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMinDigital)
                    .addComponent(lblValorDigital))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sldValorDigital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jchkEnviarInner.setLabel("Enviar para o Inner");

        jlblInfoCapHamster.setText("jLabel2");

        javax.swing.GroupLayout pnlConfQualidadeLayout = new javax.swing.GroupLayout(pnlConfQualidade);
        pnlConfQualidade.setLayout(pnlConfQualidadeLayout);
        pnlConfQualidadeLayout.setHorizontalGroup(
            pnlConfQualidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfQualidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConfQualidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCapHamster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jchkEnviarInner, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlConfiguracoesQual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblInfoCapHamster))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlConfQualidadeLayout.setVerticalGroup(
            pnlConfQualidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfQualidadeLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jlblInfoCapHamster)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(pnlCapHamster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jchkEnviarInner)
                .addGap(18, 18, 18)
                .addComponent(pnlConfiguracoesQual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jpnImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblQualidadeImagem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblQualidadeImagem.setText("Qualidade na imagem:");

        lblValorQualImagem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblValorQualImagem.setText("0");

        javax.swing.GroupLayout jpnImagemLayout = new javax.swing.GroupLayout(jpnImagem);
        jpnImagem.setLayout(jpnImagemLayout);
        jpnImagemLayout.setHorizontalGroup(
            jpnImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnImagemLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(cnvImgDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(jpnImagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblQualidadeImagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblValorQualImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jpnImagemLayout.setVerticalGroup(
            jpnImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnImagemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cnvImgDigital, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jpnImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQualidadeImagem)
                    .addComponent(lblValorQualImagem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHamsterLayout = new javax.swing.GroupLayout(pnlHamster);
        pnlHamster.setLayout(pnlHamsterLayout);
        pnlHamsterLayout.setHorizontalGroup(
            pnlHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHamsterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlConfQualidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );
        pnlHamsterLayout.setVerticalGroup(
            pnlHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHamsterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHamsterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlConfQualidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTbpBiometria.addTab("Hamster", pnlHamster);

        jPnlUsuariosSemBio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnEnviarLista.setText("Enviar Lista Sem Digital");
        jBtnEnviarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEnviarListaActionPerformed(evt);
            }
        });

        jTblUsuarioSemBio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuário"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblUsuarioSemBio.setEditingRow(0);
        jScrUsuariosSemBio.setViewportView(jTblUsuarioSemBio);

        jbtnAdicionarUsuarioSD.setText("Cadastrar usuário sem digital");
        jbtnAdicionarUsuarioSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAdicionarUsuarioSDActionPerformed(evt);
            }
        });

        jTxaUsuarioSD.setBackground(new java.awt.Color(153, 255, 255));
        jTxaUsuarioSD.setColumns(20);
        jTxaUsuarioSD.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jTxaUsuarioSD.setLineWrap(true);
        jTxaUsuarioSD.setText("Selecione um comando");
        jTxaUsuarioSD.setAutoscrolls(false);
        jScrManutencao2.setViewportView(jTxaUsuarioSD);

        javax.swing.GroupLayout jPnlUsuariosSemBioLayout = new javax.swing.GroupLayout(jPnlUsuariosSemBio);
        jPnlUsuariosSemBio.setLayout(jPnlUsuariosSemBioLayout);
        jPnlUsuariosSemBioLayout.setHorizontalGroup(
            jPnlUsuariosSemBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlUsuariosSemBioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlUsuariosSemBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrUsuariosSemBio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jBtnEnviarLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnAdicionarUsuarioSD, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(jScrManutencao2)
                    .addGroup(jPnlUsuariosSemBioLayout.createSequentialGroup()
                        .addComponent(jtxtUsuarioSD, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPnlUsuariosSemBioLayout.setVerticalGroup(
            jPnlUsuariosSemBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlUsuariosSemBioLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrManutencao2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrUsuariosSemBio, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtUsuarioSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnAdicionarUsuarioSD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnEnviarLista)
                .addContainerGap())
        );

        jbtnAdicionarUsuarioSD.getAccessibleContext().setAccessibleName("Cadastrar usuario sem digital");

        javax.swing.GroupLayout tbUsuarioSemDigitalLayout = new javax.swing.GroupLayout(tbUsuarioSemDigital);
        tbUsuarioSemDigital.setLayout(tbUsuarioSemDigitalLayout);
        tbUsuarioSemDigitalLayout.setHorizontalGroup(
            tbUsuarioSemDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbUsuarioSemDigitalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnlUsuariosSemBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(517, Short.MAX_VALUE))
        );
        tbUsuarioSemDigitalLayout.setVerticalGroup(
            tbUsuarioSemDigitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbUsuarioSemDigitalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnlUsuariosSemBio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTbpBiometria.addTab("Usuario sem digital", tbUsuarioSemDigital);

        jPnlCMDDiretoInnerBio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLblUsuario.setText("Usuário:");

        jBtnCadastrar.setLabel("Cadastrar Usuario Bio no Inner");
        jBtnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCadastrarActionPerformed(evt);
            }
        });

        jBtnSolicitarDigtal.setLabel("Cadastrar na base de dados via Inner");
        jBtnSolicitarDigtal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSolicitarDigtalActionPerformed(evt);
            }
        });

        jTxaCadastroInner.setBackground(new java.awt.Color(153, 255, 255));
        jTxaCadastroInner.setColumns(20);
        jTxaCadastroInner.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jTxaCadastroInner.setLineWrap(true);
        jTxaCadastroInner.setText("Selecione um comando");
        jTxaCadastroInner.setAutoscrolls(false);
        jScrManutencao1.setViewportView(jTxaCadastroInner);

        javax.swing.GroupLayout jPnlCMDDiretoInnerBioLayout = new javax.swing.GroupLayout(jPnlCMDDiretoInnerBio);
        jPnlCMDDiretoInnerBio.setLayout(jPnlCMDDiretoInnerBioLayout);
        jPnlCMDDiretoInnerBioLayout.setHorizontalGroup(
            jPnlCMDDiretoInnerBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCMDDiretoInnerBioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlCMDDiretoInnerBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnSolicitarDigtal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnlCMDDiretoInnerBioLayout.createSequentialGroup()
                        .addComponent(jLblUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtUsuarioCadInner))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlCMDDiretoInnerBioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrManutencao1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPnlCMDDiretoInnerBioLayout.setVerticalGroup(
            jPnlCMDDiretoInnerBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlCMDDiretoInnerBioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrManutencao1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPnlCMDDiretoInnerBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblUsuario)
                    .addComponent(jTxtUsuarioCadInner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSolicitarDigtal)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tbCadastroInnerLayout = new javax.swing.GroupLayout(tbCadastroInner);
        tbCadastroInner.setLayout(tbCadastroInnerLayout);
        tbCadastroInnerLayout.setHorizontalGroup(
            tbCadastroInnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbCadastroInnerLayout.createSequentialGroup()
                .addComponent(jPnlCMDDiretoInnerBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 534, Short.MAX_VALUE))
        );
        tbCadastroInnerLayout.setVerticalGroup(
            tbCadastroInnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbCadastroInnerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnlCMDDiretoInnerBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jTbpBiometria.addTab("Cadastro Leitor Inner", tbCadastroInner);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTbpBiometria, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTbpBiometria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Receber Modelo Bio O modelo do Inner Bio é retornado.
     *
     * @param evt
     */
    private void jBtnReceberModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReceberModeloActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    LblStatusConf.setText("Receber modelo bio");    
                    LblStatusConf.setText("Modelo placa FIM : " + bioController.solicitarModeloBio());
                } catch (InterruptedException ex) {
                    Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();
    }//GEN-LAST:event_jBtnReceberModeloActionPerformed

    /**
     * Receber Versão Bio A versão do Inner Bio é retornado.
     *
     * @param evt
     */
    private void jBtnReceberVersaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReceberVersaoActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jTxaManutencao.append("Solicitando Versão bio...");
                    JOptionPane.showMessageDialog(rootPane, "versão placa FIM :" + bioController.solicitarVersaoBio());

                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Erro ao solicitar versão !" + ex);
                }
            }
        }).start();
    }//GEN-LAST:event_jBtnReceberVersaoActionPerformed

    /**
     * MONTAR CONFIGURAÇÃO INNER Método responsável em enviar as configurações
     * Inner e Inner Bio
     *
     * @param evt
     */
        private void cmdConfigurarInnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConfigurarInnerActionPerformed
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bioController.configurarInner();
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao enviar configurações " + ex.getMessage());
                    }
                }

            }).start();
        }//GEN-LAST:event_cmdConfigurarInnerActionPerformed

    /**
     * Adicionar Usuário na Memória Cadastra a 1º e 2º digital do usuário na
     * memória do Inner Bio.
     *
     * @param evt
     */
        private void jBtnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCadastrarActionPerformed
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //Mensagem de Status
                    jTxaManutencao.setText("Cadastrando Usuário " + jTxtUsuarioCadInner.getText());

                    //Obrigatório
                    if (jTxtUsuarioCadInner.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Preencha o Nº do usuário para cadastro");
                        return;
                    }

                    bioController.cadastrarPeloLeitorInner();
                }
            }).start();
        }//GEN-LAST:event_jBtnCadastrarActionPerformed

    /**
     * Remover Usuário da Memória Verifica se o usuário existe, se sim, exclui e
     * retorna.
     *
     * @param evt
     */
        private void jBtnSolicitarDigtalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSolicitarDigtalActionPerformed
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Mensagem de Status
                        jTxaManutencao.setText("Solicitando digital leitor biométrico Inner");
                        bioController.solicitarDigitalLeitorInner();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    }
                }
            }).start();
        }//GEN-LAST:event_jBtnSolicitarDigtalActionPerformed

    /**
     * Exclui o usuario selecionado da base
     *
     * @param evt
     */
        private void jbtnReceberTemplatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReceberTemplatesActionPerformed
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        AtualizarStatusBotoes(false);
                        bioController.ReceberUsuarios();
                    }
                    catch (Exception ex)
                    {
                        Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
        }//GEN-LAST:event_jbtnReceberTemplatesActionPerformed

    /**
     * Envio Lista de Usuários sem digital
     *
     * @param evt
     */
        private void jBtnEnviarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnviarListaActionPerformed
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bioController.enviarListaSemBio();
                }
            }).start();
        }//GEN-LAST:event_jBtnEnviarListaActionPerformed

    /**
     * Receber Usuários cadastrados no Inner Bio Retorna todos os usuários
     * cadastrados
     *
     * @param evt
     */
    private void jbtnAtualizarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAtualizarUsuariosActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                jTxaManutencao.setText("Atualizar os Usuários...");
                AtualizarStatusBotoes(false);
                try {
                    bioController.solicitarUsuariosPlacaFIM();
                } catch (Exception ex) {
                    Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }).start();
    }//GEN-LAST:event_jbtnAtualizarUsuariosActionPerformed
    
    public void AtualizarStatusBotoes(boolean b)
    {
        jbtnAtualizarUsuarios.setEnabled(b);
        jbtnEnviarTemplates.setEnabled(b);
        jbtnReceberTemplates.setEnabled(b);
        jbtnExcluirSelecionadosBase.setEnabled(b);
        jbtnExcluirSelecionadosInner.setEnabled(b);
        jbtnReceberQtdeUsuarios.setEnabled(b);
    }
    
    /**
     * Receber Usuários cadastrados no Inner Bio Retorna a quantidade de
     * usuários cadastrados.
     *
     * @param evt
     */
    private void jbtnReceberQtdeUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReceberQtdeUsuariosActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Mensagem Status
                    AtualizarStatusBotoes(false);
                    jTxaManutencao.setText("Recebendo quantidade de usuários cadastrados...");
                    Integer total = bioController.receberQuantidadeUsuariosBio();
                    jTxaManutencao.setText("Total de Usuários : " + total.toString());
                    AtualizarStatusBotoes(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }//GEN-LAST:event_jbtnReceberQtdeUsuariosActionPerformed

    /**
     * Envia somente o usuário selecionado no JTable
     * @param evt 
     */
    private void jbtnExcluirSelecionadosInnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExcluirSelecionadosInnerActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jTxaManutencao.setText("Excluir usuário do Inner");
                    AtualizarStatusBotoes(false);
                    bioController.excluirUsuariosBio();
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            }
        }).start();
    }//GEN-LAST:event_jbtnExcluirSelecionadosInnerActionPerformed

    /**
     * Exclui os usuarios selecionados no JTable
     * @param evt 
     */
    private void jbtnExcluirSelecionadosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExcluirSelecionadosBaseActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("ExcluirUsuarioBase");
                AtualizarStatusBotoes(false);
                bioController.excluirUsuarioBase();
            }
        }).start();
    }//GEN-LAST:event_jbtnExcluirSelecionadosBaseActionPerformed

    private void jRdbDigital2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdbDigital2ActionPerformed
        jRdbDigital1.setSelected(!jRdbDigital2.isSelected());
    }//GEN-LAST:event_jRdbDigital2ActionPerformed

    private void jRdbDigital1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRdbDigital1ActionPerformed
        jRdbDigital2.setSelected(!jRdbDigital1.isSelected());
    }//GEN-LAST:event_jRdbDigital1ActionPerformed

    private void jBtnEnviarAjustesBioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnviarAjustesBioActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                jTxaManutencao.append("Enviando Ajustes Bio...");
                bioController.EnviarAjustesBiometricos();
            }
        }).start();
    }//GEN-LAST:event_jBtnEnviarAjustesBioActionPerformed

    private void jCboDispositivos_click(javax.swing.event.PopupMenuEvent evt)//GEN-FIRST:event_jCboDispositivos_click
    {//GEN-HEADEREND:event_jCboDispositivos_click
        if (jCboDispositivos.getItemCount() == 0)
        {
            JOptionPane.showMessageDialog(null, "Hamster não foi conectado ou o driver não foi instalado, para maiores detalhes acesse o arquivo \n leiame contido na instalação do SDK (Menu Iniciar/Programas/SDK EasyInner/Manuais)");
        }
    }//GEN-LAST:event_jCboDispositivos_click

    private void jCboDispositivosItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_jCboDispositivosItemStateChanged
    {//GEN-HEADEREND:event_jCboDispositivosItemStateChanged
        if (jCboDispositivos.getSelectedIndex() >= 0)
        {
            jBtnIniciar.setEnabled(true);
        } else
        {
            jBtnIniciar.setEnabled(false);
        }
    }//GEN-LAST:event_jCboDispositivosItemStateChanged

    private void jBtnIniciarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnIniciarActionPerformed
    {//GEN-HEADEREND:event_jBtnIniciarActionPerformed
        //Obrigatório
        if (jCboDispositivos.getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(null, "Não foi selecionado nenhum dispositivo");
        }
        jlblInfoCapHamster.setText("Iniciar hasmter");
        bioController.carregarHamster();
        jTxtCartao.setEnabled(true);
    }//GEN-LAST:event_jBtnIniciarActionPerformed

    private void jBtnCapturarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnCapturarActionPerformed
    {//GEN-HEADEREND:event_jBtnCapturarActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Obrigatório
                if (jTxtCartao.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Favor informar o número do cartão!");
                    return;
                }

                try
                {
                    bioController.cadastrarDigitalHamster();
                } catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }).start();
    }//GEN-LAST:event_jBtnCapturarActionPerformed

    private void sldValorDigitalStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_sldValorDigitalStateChanged
    {//GEN-HEADEREND:event_sldValorDigitalStateChanged
        lblValorDigital.setText(Integer.toString(sldValorDigital.getValue()));
    }//GEN-LAST:event_sldValorDigitalStateChanged

    private void sldValorVerifyStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_sldValorVerifyStateChanged
    {//GEN-HEADEREND:event_sldValorVerifyStateChanged
        lblValorVerify.setText(Integer.toString(sldValorVerify.getValue()));
    }//GEN-LAST:event_sldValorVerifyStateChanged

    private void jbtnAdicionarUsuarioSDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbtnAdicionarUsuarioSDActionPerformed
    {//GEN-HEADEREND:event_jbtnAdicionarUsuarioSDActionPerformed
        try
        {           
            DAOUsuariosBio userBio = new DAOUsuariosBio();
            userBio.InserirUsuarioSD(jtxtUsuarioSD.getText());
            
        }
        catch (IOException ex)
        {
            Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnAdicionarUsuarioSDActionPerformed

    private void jbtnEnviarTemplatesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbtnEnviarTemplatesActionPerformed
    {//GEN-HEADEREND:event_jbtnEnviarTemplatesActionPerformed
        new Thread(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        AtualizarStatusBotoes(false);
                        bioController.EnviarUsuarioSelecionado();
                    }
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(JIFEasyInnerBio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
    }//GEN-LAST:event_jbtnEnviarTemplatesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel LblStatusConf;
    public javax.swing.JCheckBox chkHabIdentificacao;
    public javax.swing.JCheckBox chkHabVerificacao;
    public javax.swing.JCheckBox chkPopup;
    public javax.swing.JButton cmdConfigurarInner;
    public java.awt.Canvas cnvImgDigital;
    private javax.swing.JButton jBtnCadastrar;
    public javax.swing.JButton jBtnCapturar;
    public javax.swing.JButton jBtnEnviarAjustesBio;
    private javax.swing.JButton jBtnEnviarLista;
    public javax.swing.JButton jBtnIniciar;
    public javax.swing.JButton jBtnReceberModelo;
    public javax.swing.JButton jBtnReceberVersao;
    private javax.swing.JButton jBtnSolicitarDigtal;
    public javax.swing.JComboBox jCboDispositivos;
    public javax.swing.JComboBox jCboPadraoCartao;
    public javax.swing.JComboBox jCboTipoConexao;
    public javax.swing.JComboBox jCboTipoLeitor;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblUsuario;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPnlCMDDiretoInnerBio;
    private javax.swing.JPanel jPnlQtdDigital;
    private javax.swing.JPanel jPnlUsuariosBase;
    private javax.swing.JPanel jPnlUsuariosInner;
    private javax.swing.JPanel jPnlUsuariosSemBio;
    public javax.swing.JRadioButton jRdbDigital1;
    public javax.swing.JRadioButton jRdbDigital2;
    private javax.swing.JScrollPane jScrManutencao;
    private javax.swing.JScrollPane jScrManutencao1;
    private javax.swing.JScrollPane jScrManutencao2;
    private javax.swing.JScrollPane jScrUsuariosBase;
    private javax.swing.JScrollPane jScrUsuariosInner;
    private javax.swing.JScrollPane jScrUsuariosSemBio;
    public javax.swing.JTable jTUsuariosInner;
    public javax.swing.JTable jTblUsuarioSemBio;
    public javax.swing.JTable jTblUsuariosBase;
    public javax.swing.JTabbedPane jTbpBiometria;
    public javax.swing.JTextArea jTxaCadastroInner;
    public javax.swing.JTextArea jTxaManutencao;
    public javax.swing.JTextArea jTxaUsuarioSD;
    public javax.swing.JTextField jTxtCartao;
    public javax.swing.JTextField jTxtNivelLFD;
    public javax.swing.JTextField jTxtNumInner;
    public javax.swing.JTextField jTxtPorta;
    public javax.swing.JTextField jTxtQtdeDigitos;
    public javax.swing.JTextField jTxtTimeoutIdent;
    public javax.swing.JTextField jTxtUsuarioCadInner;
    private javax.swing.JButton jbtnAdicionarUsuarioSD;
    private javax.swing.JButton jbtnAtualizarUsuarios;
    private javax.swing.JButton jbtnEnviarTemplates;
    private javax.swing.JButton jbtnExcluirSelecionadosBase;
    private javax.swing.JButton jbtnExcluirSelecionadosInner;
    private javax.swing.JButton jbtnReceberQtdeUsuarios;
    private javax.swing.JButton jbtnReceberTemplates;
    public javax.swing.JCheckBox jchkBio16Digitos;
    public javax.swing.JCheckBox jchkEnviarInner;
    public javax.swing.JLabel jlblInfoCapHamster;
    private javax.swing.JLabel jlblNivelLFD;
    private javax.swing.JLabel jlblTimeouIndet;
    public javax.swing.JPanel jpnImagem;
    public javax.swing.JTextField jtxtUsuarioSD;
    private javax.swing.JLabel lblMinDigital;
    private javax.swing.JLabel lblMinVerify;
    private javax.swing.JLabel lblQualidadeImagem;
    private javax.swing.JLabel lblValorDigital;
    public javax.swing.JLabel lblValorQualImagem;
    private javax.swing.JLabel lblValorVerify;
    private javax.swing.JPanel pnlCapHamster;
    public javax.swing.JPanel pnlConfQualidade;
    public javax.swing.JPanel pnlConfiguracoesQual;
    public javax.swing.JPanel pnlHamster;
    public javax.swing.JSlider sldValorDigital;
    public javax.swing.JSlider sldValorVerify;
    private javax.swing.JPanel tbCadastroInner;
    private javax.swing.JDesktopPane tbConfiguracao;
    private javax.swing.JDesktopPane tbManutencaoUsuarioBio;
    private javax.swing.JPanel tbUsuarioSemDigital;
    // End of variables declaration//GEN-END:variables

}
