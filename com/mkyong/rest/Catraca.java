/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

import com.topdata.EasyInner;
import com.topdata.easyInner.controller.EasyInnerBioController;
import com.topdata.easyInner.controller.OnlineController;
import com.topdata.easyInner.entity.Inner;
import com.topdata.easyInner.enumeradores.Enumeradores;
import com.topdata.easyInner.service.EasyInnerBioService;
import com.topdata.easyInner.ui.JIFEasyInnerBio;
import com.topdata.easyInner.ui.JIFEasyInnerOnLine;
import com.topdata.easyInner.utils.EasyInnerUtils;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GOVERNO-01
 */
public class Catraca {
    private OnlineController controller;
    private static Catraca instancia;
    private EasyInnerBioController bioController;
    public static final int RET_COMANDO_OK = 0;
    public static final int RET_COMANDO_ERRO = 1;
    public static final int RET_PORTA_NAOABERTA = 2;
    public static final int RET_PORTA_JAABERTA = 3;
    public static final int RET_DLL_INNER2K_NAO_ENCONTRADA = 4;
    public static final int RET_DLL_INNERTCP_NAO_ENCONTRADA = 5;
    public static final int RET_DLL_INNERTCP2_NAO_ENCONTRADA = 6;
    public static final int RET_ERRO_GPF = 8;
    public static final int RET_TIPO_CONEXAO_INVALIDA = 9;
    private EasyInner easyInner;
    private EasyInnerBioService bioService;
    private Biometria bio;
    
    private Catraca(){
        this.controller = new OnlineController(new JIFEasyInnerOnLine());
        this.bioController = new EasyInnerBioController(null);
        this.easyInner = new EasyInner();
        this.bioService = new EasyInnerBioService(easyInner);
//        this.bio = new Biometria();
        //adicionarInner(1);
        //iniciar();
    }
    public static synchronized Catraca getInstance(){
        if(instancia==null) instancia = new Catraca();
        return instancia;
    }
    public static void main(String [] args){
        Catraca c = new Catraca();
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        c.adicionarInner(i);
        i = sc.nextInt();
        c.iniciar();
        while(true){
             i = sc.nextInt();
            c.liberarCatraca(i);    
        }
//        sc.close();
    }
    
    public void adicionarInner(final int numeroInner){
        new Thread(new Runnable() {

            @Override
            public void run() {
                Inner AddInner = new Inner();
                AddInner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
                AddInner.EstadoTeclado = Enumeradores.EstadosTeclado.TECLADO_EM_BRANCO;
                AddInner.Numero = numeroInner;
                AddInner.QtdDigitos = 14;
                AddInner.Teclado = false;
                AddInner.TipoLeitor = 0;
                AddInner.Identificacao = 0;
                AddInner.Verificacao = 0;
                AddInner.DoisLeitores = false;
                AddInner.VariacaoInner = 0;
                AddInner.Acionamento = 3;
                AddInner.Catraca = true;
                AddInner.CatInvertida = false;
                AddInner.PadraoCartao = 1;
                AddInner.TipoConexao = 2;
                AddInner.Porta = 3569+numeroInner;
                AddInner.Biometrico = false;
                AddInner.Lista = false;
                AddInner.ListaBio = false;
                AddInner.TipoComBio = 0;
                controller.AdicionarInner(AddInner);
            }
        }).start();
    }
    
    public void removerInner(int numInner){
        this.controller.RemoverInner(numInner);
    }
    
    public void iniciar(){
        try {
            //Ação que inicia a configuração e conexão         
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        controller.IniciarMaquinaEstados();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JIFEasyInnerOnLine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void parar(){
        this.controller.PararMaquinaOnline();
    }
    
    public void liberarCatraca(int numeroInner){
        Inner inner = (Inner) this.controller.ListaInners.get(numeroInner);
        
//        if (inner.Catraca) {
            //Ação que efetua a Entrada da Catraca
            this.controller.HabilitarLadoCatraca("Saida", false);
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
//        }
    }
    
    public String colherBiometria(int numeroInner) throws InterruptedException{
        Inner inner = (Inner) this.controller.ListaInners.get(numeroInner);
        int retorno = -1;
        byte Template[] = new byte[808];
        int tentativas = 0;
        String msg = "";

//        if (isConectado() == true) {
//            JOptionPane.showMessageDialog(uiBio, "Posicione o dedo no leitor");    
        Logger.getLogger(JIFEasyInnerOnLine.class.getName()).log(Level.SEVERE, null, "Posicione o dedo no leitor");
            retorno = easyInner.SolicitarTemplateLeitor(numeroInner);
            Thread.sleep(500);
            if (retorno == RET_COMANDO_OK) {
                do {
                    retorno = easyInner.ReceberTemplateLeitor(numeroInner, 0, Template);
                    Thread.sleep(100);
                } while (retorno != RET_COMANDO_OK && tentativas++ < 50);
                if (retorno == 0) {
//                    this.bio.convertArrayByteToHex(Template);
                    msg = EasyInnerUtils.ArrayByteToHex(Template, 0, Template.length);
//                    UsuarioBio usuarioBio = new UsuarioBio();
//                    usuarioBio.setCartao(uiBio.jTxtUsuarioCadInner.getText());
//                    usuarioBio.setTemplate1(msg.toString());
//                    usuarioBio.setTemplate2(msg.toString());
//                    DAOUsuariosBio AcessoBio = new DAOUsuariosBio();
//                    AcessoBio.InserirUsuarioBio(usuarioBio, 0);
//                    uiBio.jTxaCadastroInner.setText("Cadastrado com sucesso");
                }
            }
//        } else {
//            JOptionPane.showMessageDialog(uiBio, "Erro a solicitar digital !");
            Logger.getLogger(JIFEasyInnerOnLine.class.getName()).log(Level.SEVERE, null, "Erro a solicitar digital !");
//        }
            return msg;
    }
}
