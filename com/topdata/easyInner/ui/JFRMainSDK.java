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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class JFRMainSDK extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    public JFRMainSDK() {
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        menuOffline = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuOnline = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuBio = new javax.swing.JMenu();
        jmiInnerBio = new javax.swing.JMenuItem();
        jmiInnerBio6xx = new javax.swing.JMenuItem();
        Sobre = new javax.swing.JMenu();
        jMitHelp = new javax.swing.JMenuItem();
        jMitSobre = new javax.swing.JMenuItem();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SDK EasyInner ");

        menuOffline.setText("OffLine");
        menuOffline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOfflineActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Inner OffLine");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuOffline.add(jMenuItem1);

        menuBar.add(menuOffline);

        menuOnline.setText("OnLine");

        jMenuItem2.setText("Inner OnLine");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuOnline.add(jMenuItem2);

        menuBar.add(menuOnline);

        menuBio.setText("Biometria");

        jmiInnerBio.setText("Inner Bio");
        jmiInnerBio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInnerBioActionPerformed(evt);
            }
        });
        menuBio.add(jmiInnerBio);

        jmiInnerBio6xx.setText("Inner Bio 6xx");
        jmiInnerBio6xx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInnerBio6xxActionPerformed(evt);
            }
        });
        menuBio.add(jmiInnerBio6xx);

        menuBar.add(menuBio);

        Sobre.setText("Ajuda");

        jMitHelp.setText("Manual de Desenvolvimento");
        jMitHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMitHelpActionPerformed(evt);
            }
        });
        Sobre.add(jMitHelp);

        jMitSobre.setText("Sobre");
        jMitSobre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMitSobreMousePressed(evt);
            }
        });
        Sobre.add(jMitSobre);

        menuBar.add(Sobre);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuOfflineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOfflineActionPerformed

    }//GEN-LAST:event_menuOfflineActionPerformed

    private void offline() throws InterruptedException {

        //Instancia a classe tela Offline
        JIFEasyInnerOffLine f = new JIFEasyInnerOffLine();
        f.setVisible(true);
        f.setClosable(true);
        desktopPane.add(f, javax.swing.JLayeredPane.DEFAULT_LAYER);

    } 

    private void online() {

        //Instancia a classe tela Online
        JIFEasyInnerOnLine o = new JIFEasyInnerOnLine();
        o.setVisible(true);
        o.setClosable(true);
        o.setBounds(0, 0, 750, 750);
        desktopPane.add(o, javax.swing.JLayeredPane.DEFAULT_LAYER);
    }

    private void biometria() throws Exception {

        //Instancia a classe tela Bio
        JIFEasyInnerBio b = new JIFEasyInnerBio();

        b.setVisible(true);
        b.setClosable(true);
        b.setBounds(0, 0, 850, 550);
        desktopPane.add(b, javax.swing.JLayeredPane.DEFAULT_LAYER);
    }
    
    private void Biometria6xx(){
        JIFEasyInnerBio6xx Bio6xx = new JIFEasyInnerBio6xx();
        
        Bio6xx.setVisible(true);
        Bio6xx.setClosable(true);
        Bio6xx.setBounds(0, 0, 850, 550);
        desktopPane.add(Bio6xx, javax.swing.JLayeredPane.DEFAULT_LAYER);
    }

    private void sobre() {

        //Instancia a classe tela JIFSobre
        JIFSobre s = new JIFSobre();
        s.setVisible(true);
        s.setClosable(true);
        s.setBounds(0, 0, 500, 670);
        desktopPane.add(s, javax.swing.JLayeredPane.DEFAULT_LAYER);

    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            //Tela OffLine
            offline();
        } catch (InterruptedException ex) {
            Logger.getLogger(JFRMainSDK.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //Tela OnLine
        online();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jmiInnerBioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInnerBioActionPerformed
        try {
            //Tela Bio
            biometria();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jmiInnerBioActionPerformed

    private void Help() throws IOException {
        String dir = System.getProperty("user.dir");
        String ArquivoHelp = "SDK_EasyInner_-_Manual_de_Desenvolvimento.chm";
        Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + dir + "\\" + ArquivoHelp);

    }

    private void jMitHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMitHelpActionPerformed
        try {
            Help();
        } catch (IOException ex) {
            Logger.getLogger(JFRMainSDK.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMitHelpActionPerformed

    private void jMitSobreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMitSobreMousePressed
        sobre();
    }//GEN-LAST:event_jMitSobreMousePressed

    private void jmiInnerBio6xxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInnerBio6xxActionPerformed
        try {
            //Tela Bio
            Biometria6xx();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jmiInnerBio6xxActionPerformed

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JFRMainSDK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Sobre;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMitHelp;
    private javax.swing.JMenuItem jMitSobre;
    private javax.swing.JMenuItem jmiInnerBio;
    private javax.swing.JMenuItem jmiInnerBio6xx;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuBio;
    private javax.swing.JMenu menuOffline;
    private javax.swing.JMenu menuOnline;
    // End of variables declaration//GEN-END:variables

}
