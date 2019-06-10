/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.controller;

import com.topdata.EasyInner;
import com.topdata.easyInner.dao.DAOUsuarios;
import com.topdata.easyInner.dao.DAOUsuariosBio;
import com.topdata.easyInner.entity.Horarios;
import com.topdata.easyInner.entity.Inner;
import com.topdata.easyInner.entity.UsuarioSemDigital;
import com.topdata.easyInner.entity.Usuarios;
import com.topdata.easyInner.enumeradores.Enumeradores;
import com.topdata.easyInner.enumeradores.Enumeradores.EstadosTeclado;
import com.topdata.easyInner.service.BioService;
import com.topdata.easyInner.ui.JIFEasyInnerOnLine;
import static com.topdata.easyInner.ui.JIFEasyInnerOnLine.intTentativas;
import com.topdata.easyInner.utils.EasyInnerUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jonatas.silva
 */
public class OnlineController {
    public HashMap ListaInners;
    private boolean Parar;
    private final EasyInner easyInner;
    private JIFEasyInnerOnLine frmOnline;
    //Catraca
    private boolean LiberaEntrada          = false;
    private boolean LiberaSaida            = false;
    private boolean LiberaEntradaInvertida = false;
    private boolean LiberaSaidaInvertida   = false;
    
    public OnlineController(JIFEasyInnerOnLine frm)
    {
        frmOnline = frm;
        this.easyInner = new EasyInner();
        ListaInners = new HashMap();
        Parar = false;
    }
    
    public void AdicionarInner(Inner inner)
    {
        ListaInners.put(inner.Numero, inner);
    }
    
    /**
     * Realiza a cricao do array de equipamentos para serem controlados.
     *
     * @throws InterruptedException
     */
    public void IniciarMaquinaEstados() throws InterruptedException {

        int Ret = 0;
        //Fecha qualquer conexão que estivesse aberta..
        easyInner.FecharPortaComunicacao();
        //Define o tipo de conexão conforme o selecionado no combo (serial, TCP porta Variavel, TCP Porta Fixa..etc)
        Inner inner = (Inner)ListaInners.values().toArray()[0];
        easyInner.DefinirTipoConexao(inner.TipoConexao);

        //Abre a porta de Comunicação com os Inners..
        Ret = easyInner.AbrirPortaComunicacao(inner.Porta);

        //Caso o retorno seja OK, abre a maquina de Estados..
        if (Ret == Enumeradores.RET_COMANDO_OK) {
            Parar = false;
            frmOnline.AtualizarBotoes(Parar);
            MaquinaEstados();
        } else {
            //JOptionPane.showMessageDialog(null, "Erro ao tentar abrir a porta de comunicação.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            frmOnline.AtualizarBotoes(true);
        }
    }
    
    /**
     * FUNCIONAMENTO DA M�?QUINA DE ESTADOS MÉTODO RESPONS�?VEL EM EXECUTAR OS
     * PROCEDIMENTOS DO MODO ONLINE A Máquina de Estados nada mais é do que uma
     * rotina que fica em loop testando uma variável que chamamos de Estado.
     * Dependendo do estado atual, executamos alguns procedimentos e em seguida
     * alteramos o estado que será verificado pela máquina de estados novamente
     * no próximo passo do loop.
     *
     * @throws InterruptedException
     */
    private void MaquinaEstados() throws InterruptedException {

        //Enquanto Parar = false prosseguir a maquina...
        while (!Parar) {
            //Verifica o Estado do Inner Atual..
            for(Object objInner : ListaInners.values())
            {
                Inner inner = (Inner)objInner;
                switch (inner.EstadoAtual) {
                    case ESTADO_CONECTAR:
                        PASSO_ESTADO_CONECTAR(inner);
                        break;

                    case ESTADO_ENVIAR_CFG_OFFLINE:
                        PASSO_ESTADO_ENVIAR_CFG_OFFLINE(inner);
                        break;

                    case ESTADO_COLETAR_BILHETES:
                        PASSO_ESTADO_COLETAR_BILHETES(inner);
                        break;

                    case ESTADO_ENVIAR_CFG_ONLINE:
                        PASSO_ESTADO_ENVIAR_CFG_ONLINE(inner);
                        break;

                    case ESTADO_ENVIAR_DATA_HORA:
                        PASSO_ESTADO_ENVIAR_DATA_HORA(inner);
                        break;

                    case ESTADO_ENVIAR_MSG_PADRAO:
                        PASSO_ESTADO_ENVIAR_MSG_PADRAO(inner);
                        break;

                    case ESTADO_CONFIGURAR_ENTRADAS_ONLINE:
                        PASSO_ESTADO_CONFIGURAR_ENTRADAS_ONLINE(inner);
                        break;

                    case ESTADO_POLLING:
                        PASSO_ESTADO_POLLING(inner);
                        break;

                    case ESTADO_LIBERAR_CATRACA:
                        PASSO_ESTADO_LIBERA_GIRO_CATRACA(inner);
                        break;

                    case ESTADO_MONITORA_GIRO_CATRACA:
                        PASSO_ESTADO_MONITORA_GIRO_CATRACA(inner);
                        break;

                    case ESTADO_PING_ONLINE:
                        PASSO_ESTADO_ENVIA_PING_ONLINE(inner);
                        break;

                    case ESTADO_RECONECTAR:
                        PASSO_ESTADO_RECONECTAR(inner);
                        break;

                    case ESTADO_AGUARDA_TEMPO_MENSAGEM:
                        PASSO_ESTADO_AGUARDA_TEMPO_MENSAGEM(inner);
                        break;

                    case ESTADO_DEFINICAO_TECLADO:
                        PASSO_ESTADO_DEFINICAO_TECLADO(inner);
                        break;

                    case ESTADO_AGUARDA_DEFINICAO_TECLADO:
                        PASSO_ESTADO_AGUARDA_DEFINICAO_TECLADO(inner);
                        break;

                    case ESTADO_ENVIA_MSG_URNA:
                        PASSO_ESTADO_ENVIA_MSG_URNA(inner);
                        break;

                    case ESTADO_MONITORA_URNA:
                        PASSO_ESTADO_MONITORA_URNA(inner);
                        break;

                    case ESTADO_ACIONAR_RELE1:
                        PASSO_ESTADO_ACIONAR_RELE1(inner);
                        break;
                    
                    case ESTADO_ACIONAR_RELE2:
                        PASSO_ESTADO_ACIONAR_RELE2(inner);
                        break;

                    case ESTADO_ENVIAR_CONFIGMUD_ONLINE_OFFLINE:
                        PASSO_ESTADO_ENVIAR_CONFIGMUD_ONLINE_OFFLINE(inner);
                        break;

                    case ESTADO_ENVIAR_MSG_OFFLINE_CATRACA:
                        PASSO_ESTADO_ENVIAR_MSG_OFFLINE_CATRACA(inner);
                        break;

                    case ESTADO_ENVIAR_MSG_ACESSO_NEGADO:
                        PASSO_ESTADO_ENVIAR_MSG_ACESSO_NEGADO(inner);
                        break;

                    case ESTADO_VALIDA_URNA_CHEIA:
                        PASSO_ESTADO_VALIDA_URNA_CHEIA(inner);
                        
                    case ESTADO_ENVIAR_MSG_URNA_CHEIA:
                        PASSO_ESTADO_ENVIAR_MSG_URNA_CHEIA(inner);
                        break;

                    case ESTADO_VALIDAR_ACESSO:
                        PASSO_ESTADO_VALIDAR_ACESSO(inner);
                        break;
                       
                    case ESTADO_RECEBER_FIRWARE:
                        PASSO_ESTADO_RECEBER_VERSAO_FW(inner);
                        break;
                        
                    case ESTADO_RECEBER_MODELO_BIO:
                        PASSO_ESTADO_RECEBER_MODELO_BIO(inner);
                        break;
                        
                    case ESTADO_ENVIAR_LISTA_OFFLINE:
                        PASSO_ESTADO_ENVIAR_LISTA_OFFLINE(inner);
                        break;
                        
                    case ESTADO_ENVIAR_LISTA_SEMDIGITAL:
                        PASSO_ESTADO_ENVIAR_LISTA_SEMDIGITAL(inner);
                        break;
                    
                    case ESTADO_RECEBER_QTD_BILHETES_OFF:
                        PASSO_ESTADO_RECEBER_QTD_BILHETES_OFF(inner);
                        break;
                        
                    case ESTADO_ENVIAR_MSG_OFFLINE_COLETOR:
                        PASSO_ESTADO_ENVIAR_MSG_OFF_COLETOR(inner);
                        break;
                      
                    case ESTADO_RECEBER_VERSAO_BIO:
                        PASSO_ESTADO_RECEBER_VERSAO_BIO(inner);
                        break;
                    
                    case ESTADO_DESLIGAR_RELE:
                        PASSO_ESTADO_DESLIGAR_RELE(inner);
                        break;
                }
            }
            Thread.sleep(1);
        }

        //Fecha a porta de Comunicação quando sai da maquina de estados..
        easyInner.FecharPortaComunicacao();
    }

    private void PASSO_ESTADO_CONECTAR(Inner inner)
    {
        int Ret = 0;
        long IniConexao = 0;
            long tempo = 0;
        try {

            Ret = Enumeradores.Limpar;
            //Inicia tempo ping online
            inner.TempoInicialPingOnLine = (int) System.currentTimeMillis();

            //Mensagem Status
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Conectando...");

            IniConexao = System.currentTimeMillis();
            //Realiza loop enquanto o tempo fim for menor que o tempo atual, e o comando retornado diferente de OK.
            do {
                tempo = System.currentTimeMillis() - IniConexao;
                //Tenta abrir a conexão 
                Thread.sleep(100);
                Ret = TestarConexaoInner(inner.Numero);

            } while (Ret != Enumeradores.RET_COMANDO_OK && tempo < 10000);

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //caso consiga o Inner vai para o Passo de Configuração OFFLINE, posteriormente para coleta de Bilhetes.
                inner.CountTentativasEnvioComando = 0;
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECEBER_FIRWARE;

            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                inner.CountTentativasEnvioComando++;
            }

        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
        }
    }

    private void PASSO_ESTADO_ENVIAR_CFG_OFFLINE(Inner inner)
    {
        try {
            int Ret = 0;
            //Mensagem Status
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviado configurações OFF-LINE...");

            //Preenche os campos de configuração do Inner
            MontaConfiguracaoInner(inner, Enumeradores.MODO_OFF_LINE);
            
            //Envia o comando de configuração
            Ret = easyInner.EnviarConfiguracoes(inner.Numero);

            //Testa o retorno do envio das configurações Off Line
            if (Ret == Enumeradores.RET_COMANDO_OK) {

                inner.CountTentativasEnvioComando = 0;
                //verifica se o enviar lista esta selecionado
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_LISTA_OFFLINE;
                inner.TempoColeta = (int) System.currentTimeMillis() + 3000;

            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                inner.CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_COLETAR_BILHETES(Inner inner) throws InterruptedException
    {
        if (inner.InnerNetAcesso) {
            ColetarBilhetesInnerAcesso(inner);
        } else {
            ColetarBilhetesInnerNet(inner);
        }
        inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_CFG_ONLINE;
    }

    private void PASSO_ESTADO_ENVIAR_CFG_ONLINE(Inner inner)
    {
        try {
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviar configuração Online...");
            int Ret = 0;        
            //Monta configuração modo Online
            MontaConfiguracaoInner(inner, Enumeradores.MODO_ON_LINE);

            //Envia as configurações ao Inner Atual.
            Ret = easyInner.EnviarConfiguracoes(inner.Numero);

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //caso consiga enviar as configurações, passa para o passo Enviar Data Hora
                inner.CountTentativasEnvioComando = 0;
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_CONFIGMUD_ONLINE_OFFLINE;
            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                inner.CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_ENVIAR_DATA_HORA(Inner inner)
    {
        try {
            int Ret = 0;
            //Exibe estado do Inner no Rodapé da Janela
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviando data e hora...");

            Date Data = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yy");
            int Ano = Integer.parseInt(formatter.format(Data));
            formatter = new SimpleDateFormat("MM");
            int Mes = Integer.parseInt(formatter.format(Data));
            formatter = new SimpleDateFormat("dd");
            int Dia = Integer.parseInt(formatter.format(Data));
            formatter = new SimpleDateFormat("HH");
            int Hora = Integer.parseInt(formatter.format(Data));
            formatter = new SimpleDateFormat("mm");
            int Minuto = Integer.parseInt(formatter.format(Data));
            formatter = new SimpleDateFormat("ss");
            int Segundo = Integer.parseInt(formatter.format(Data));
            //Envia Comando de Relógio ao Inner Atual..
            //RelogioInner relogioInner = new RelogioInner();
            Ret = easyInner.EnviarRelogio(inner.Numero, Dia, Mes, Ano, Hora, Minuto, Segundo);
            //Testa o Retorno do comando de Envio de Relógio..
            if (Ret == Enumeradores.RET_COMANDO_OK) {

                inner.CountTentativasEnvioComando = 0;
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_CFG_ONLINE;
            } 
            else 
            {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (inner.CountTentativasEnvioComando >= 3) 
                {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                inner.CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_ENVIAR_MSG_PADRAO(Inner inner)
    {
        try {
            int Ret = 0;
            //Exibe estado do Inner no Rodapé da Janela
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviando Mensagem Padrão...");

            //Declaração de Variáveis..
            Ret = Enumeradores.Limpar;

            //Envia comando definindo a mensagem Padrão Online para o Inner.
            Ret = easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 1, "   Modo Online");

            //Testa o retorno da mensagem enviada..
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //Muda o passo para configuração de entradas Online.
                inner.CountTentativasEnvioComando = 0;
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_POLLING;
            } 
            else 
            {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (inner.CountTentativasEnvioComando >= 3) 
                {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                //Adiciona 1 ao contador de tentativas
                inner.CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_CONFIGURAR_ENTRADAS_ONLINE(Inner inner)
    {
        try {
            int Ret = 0;
            //Exibe estado do Inner no Rodapé da Janela
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Configurando Entradas Online...");

            //Declaração de variáveis..
            Ret = Enumeradores.Limpar;

            //Converte Binário para Decimal
            int ValorDecimal = ConfiguraEntradasMudancaOnLine(inner); //Ver no manual Anexo III

            Ret = easyInner.EnviarFormasEntradasOnLine(inner.Numero, (byte) inner.QtdDigitos, 1, (byte) ValorDecimal, 15, 17);
            //Testa o retorno do comando..
            if (Ret ==  Enumeradores.RET_COMANDO_OK) {
                //Vai para o Estado De Polling.
                inner.TempoInicialPingOnLine = (int) System.currentTimeMillis();
                inner.CountTentativasEnvioComando = 0;
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_PADRAO;

            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                //Adiciona 1 ao contador de tentativas
                inner.CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_POLLING(Inner inner)
    {
         try 
        {
            int Ret = 0;
            StringBuffer Cartao = new StringBuffer();
            //Exibe estado do Inner no Rodapé da Janela
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Estado de Polling...");
            
            Cartao.delete(0, Cartao.length());
            Ret = -1;
            
            int[] iArrBCartaoRb = new int[8]; /*it receives the card data*/
            //Envia o Comando de Coleta de Bilhetes..
            Ret = easyInner.ReceberDadosOnLine(inner.Numero, iArrBCartaoRb, Cartao);

            //Atribui Temporizador
            inner.Temporizador = (int)System.currentTimeMillis();
            inner.TempoInicialMensagem = (int)System.currentTimeMillis();     
            if (Ret == Enumeradores.RET_COMANDO_OK) 
            {
                if ( iArrBCartaoRb[1] == Enumeradores.TECLA_FUNCAO
                    || iArrBCartaoRb[1] == Enumeradores.TECLA_ANULA
                        || ((Cartao.length() == 0)
                        && !(inner.EstadoTeclado == Enumeradores.EstadosTeclado.AGUARDANDO_TECLADO))) 
                {
                    inner.CountTentativasEnvioComando = 0;
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_AGUARDA_TEMPO_MENSAGEM;
                    return;
                }

                /*******************************************************/
                //Assigning the card data into the current Inner
                inner.BilheteInner.Origem        = iArrBCartaoRb[0];
                inner.BilheteInner.Complemento   = iArrBCartaoRb[1];
                inner.BilheteInner.Dia           = iArrBCartaoRb[2];
                inner.BilheteInner.Mes           = iArrBCartaoRb[3];
                inner.BilheteInner.Ano           = iArrBCartaoRb[4];
                inner.BilheteInner.Hora          = iArrBCartaoRb[5];
                inner.BilheteInner.Minuto        = iArrBCartaoRb[6];
                inner.BilheteInner.Segundo       = iArrBCartaoRb[7];
                
                /*Cleaning the Strinbuilder variable and assigning a new value*/
                inner.BilheteInner.Cartao.setLength(0);
                inner.BilheteInner.Cartao = new StringBuilder(Cartao.toString());
                MontarBilheteRecebido(inner);
                
                PreencherBilheteDisplay(inner);
                
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_VALIDAR_ACESSO;
            
            }    
            else 
            {
                long temp = System.currentTimeMillis() - inner.TempoInicialPingOnLine;
                //Se passar 3 segundos sem receber nada, passa para o estado enviar ping on line, para manter o equipamento em on line.
                if ((int) temp > 3000) {
                    inner.EstadoSolicitacaoPingOnLine = inner.EstadoAtual;
                    inner.CountTentativasEnvioComando = 0;
                    inner.TempoInicialPingOnLine = (int) System.currentTimeMillis();
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_PING_ONLINE;
                }
            }

        } catch (Exception ex) {
            System.err.println(ex);
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PreencherBilheteDisplay(Inner inner)
    {
        String sBilheteDisplay = "";
        sBilheteDisplay += "Marcações Online. Inner: " + inner.Numero + 
                        " Complemento:" + (inner.BilheteInner.Complemento);
        //Se Quantidade de dígitos informado for maior que 14 não deve mostrar data e hora
        if (inner.QtdDigitos <= 14)
        {
            sBilheteDisplay += " Data:"
                    + (String.valueOf(inner.BilheteInner.Dia).length() == 1 ? "0" + String.valueOf(inner.BilheteInner.Dia) : String.valueOf(inner.BilheteInner.Dia)) + "/"
                    + (String.valueOf(inner.BilheteInner.Mes).length() == 1 ? "0" + String.valueOf(inner.BilheteInner.Mes) : String.valueOf(inner.BilheteInner.Mes)) + "/"
                    + String.valueOf(inner.BilheteInner.Ano) + " Hora:"
                    + (String.valueOf(inner.BilheteInner.Hora).length() == 1 ? "0" + String.valueOf(inner.BilheteInner.Hora) : String.valueOf(inner.BilheteInner.Hora)) + ":"
                    + (String.valueOf(inner.BilheteInner.Minuto).length() == 1 ? "0" + String.valueOf(inner.BilheteInner.Minuto) : String.valueOf(inner.BilheteInner.Minuto)) + ":"
                    + (String.valueOf(inner.BilheteInner.Segundo).length() == 1 ? "0" + String.valueOf(inner.BilheteInner.Segundo) : String.valueOf(inner.BilheteInner.Segundo)) + " "
                    + " Cartão: " + inner.BilheteInner.Cartao + "\n";
        }
        else
        {
            sBilheteDisplay += " Cartão: " + inner.BilheteInner.Cartao + "\r\n";
        }
        
        //Adiciona bilhete coletado na Lista
        frmOnline.AtuaLizarBilhetes(sBilheteDisplay);
    }

    private void PASSO_ESTADO_LIBERA_GIRO_CATRACA(Inner inner)
    {
        try {
            int Ret = 0;
            //Exibe estado do Inner no Rodapé da Janela
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Libera Giro da Catraca...");

            //Envia comando de liberar a catraca para Entrada.
            if (LiberaEntrada) {                                    //"                ENTRADA LIBERADA"
                easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "PREFEITURA BARBALHA - BOA FESTA!");
                LiberaEntrada = false;
                Ret = easyInner.LiberarCatracaEntrada(inner.Numero);
            } else if (LiberaEntradaInvertida) {
                easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "PREFEITURA BARBALHA - BOA FESTA!");
                LiberaEntradaInvertida = false;
                Ret = easyInner.LiberarCatracaEntradaInvertida(inner.Numero);
            } else if (LiberaSaida) {
                //Envia comando de liberar a catraca para Saída.
                easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "PREFEITURA BARBALHA - BOA FESTA!");
                LiberaSaida = false;
                Ret = easyInner.LiberarCatracaSaida(inner.Numero);
            } else if (LiberaSaidaInvertida) {
                easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "PREFEITURA BARBALHA - BOA FESTA!");
                LiberaSaidaInvertida = false;
                Ret = easyInner.LiberarCatracaSaidaInvertida(inner.Numero);
            } else {
                easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "PREFEITURA BARBALHA BOA FESTA!");
                Ret = easyInner.LiberarCatracaDoisSentidos(inner.Numero);
            }

            //Testa Retorno do comando..
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                easyInner.AcionarBipCurto(inner.Numero);
                inner.CountPingFail = 0;
                inner.CountTentativasEnvioComando = 0;
                inner.TempoInicialPingOnLine = (int) System.currentTimeMillis();
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_MONITORA_GIRO_CATRACA;
            } else {
                //Se o retorno for diferente de 0 tenta liberar a catraca 3 vezes, caso não consiga enviar o comando volta para o passo reconectar.
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.CountTentativasEnvioComando = 0;
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                inner.CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_MONITORA_GIRO_CATRACA(Inner inner)
    {
        try {
            int Ret = 0;
            int[] Bilhete = new int[8];
            StringBuffer Cartao;

            Cartao = new StringBuffer();

            //Exibe estado do giro
            frmOnline.AtualizarDados("Monitorando Giro de Catraca!");

            //Exibe estado do Inner no Rodapé da Janela
            frmOnline.AtualizarDados("Inner " + inner.Numero + " Monitora Giro da Catraca...");

            //Monitora o giro da catraca..
            Ret = easyInner.ReceberDadosOnLine(inner.Numero, Bilhete, Cartao);

            //Testa o retorno do comando..
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //Testa se girou o não a catraca..
                if (Bilhete[0] == Enumeradores.FIM_TEMPO_ACIONAMENTO) {
                    frmOnline.AtualizarDados("Não girou a catraca!");
                } else if (Bilhete[0] == Enumeradores.GIRO_DA_CATRACA_TOPDATA) {
                    if (inner.CatInvertida) {
                        if (Integer.parseInt(String.valueOf(Bilhete[1])) == 0) {
                            frmOnline.AtualizarDados("Girou a catraca para saída.");
                        } else {
                            frmOnline.AtualizarDados("Girou a catraca para entrada.");
                        }
                    } else {
                        if (Integer.parseInt(String.valueOf(Bilhete[1])) == 0) {
                            frmOnline.AtualizarDados("Girou a catraca para entrada.");
                        } else {
                            frmOnline.AtualizarDados("Girou a catraca para saída.");
                        }
                    }
                }

                //Vai para o estado de Envio de Msg Padrão..
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONFIGURAR_ENTRADAS_ONLINE;
            } else {
                //Caso o tempo que estiver monitorando o giro chegue a 3 segundos,
                //deverá enviar o ping on line para manter o equipamento em modo on line
                long tempo = (System.currentTimeMillis() - inner.TempoInicialPingOnLine);
                //Se passar 3 segundos sem receber nada, passa para o estado enviar ping on line, para manter o equipamento em on line.
                if (tempo >= 3000) {
                    inner.EstadoSolicitacaoPingOnLine = inner.EstadoAtual;
                    inner.CountTentativasEnvioComando = 0;
                    inner.TempoInicialPingOnLine = System.currentTimeMillis();
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_PING_ONLINE;
                }
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_ENVIA_PING_ONLINE(Inner inner)
    {
        try {
            //Exibe estado do Inner no Rodapé da Janela
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " PING ONLINE...");

            //Envia o comando de PING ON LINE, se o retorno for OK volta para o estado onde chamou o método
            int retorno = easyInner.PingOnLine(inner.Numero);
            if (retorno == easyInner.RET_COMANDO_OK) {
                inner.EstadoAtual = inner.EstadoSolicitacaoPingOnLine;
            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                inner.CountTentativasEnvioComando++;
            }
            inner.TempoInicialPingOnLine = System.currentTimeMillis();
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
        }
    }

    private void PASSO_ESTADO_RECONECTAR(Inner inner)
    {
        try {
            int Ret = 0;
            long IniConexao;

            long tempo = System.currentTimeMillis() - inner.TempoInicialPingOnLine;
            if (tempo < 10000) {
                return;
            }
            inner.TempoInicialPingOnLine = (int) System.currentTimeMillis();

            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Reconectando...");
            Ret = Enumeradores.Limpar;

            IniConexao = System.currentTimeMillis();
            //Realiza loop enquanto o tempo fim for menor que o tempo atual, e o comando retornado diferente de OK.
            do {
                tempo = System.currentTimeMillis() - IniConexao;

                Ret = TestarConexaoInner(inner.Numero);

                Thread.sleep(10l);

            } while (Ret != Enumeradores.RET_COMANDO_OK && tempo < 15000);

            //Testa o comando de envio de relógio para o Inner
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //Zera as variáveis de controle da maquina de estados.
                inner.CountTentativasEnvioComando = 0;
                //Recebendo Versao de firmware, passo obrigatorio
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECEBER_FIRWARE;
            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                inner.CountTentativasEnvioComando++;
            }

            inner.CountRepeatPingOnline = 0;
        } catch (Exception ex) {
            System.out.println("Passo Reconectar :  " + ex);
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_AGUARDA_TEMPO_MENSAGEM(Inner inner)
    {
        try {
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Aguardar tempo mensagem...");
            //Após passar os 2 segundos volta para o passo enviar mensagem padrão
            long tempo = (int)System.currentTimeMillis() - inner.TempoInicialMensagem;
            if (tempo > 2000) {
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONFIGURAR_ENTRADAS_ONLINE;
                easyInner.DesligarLedVermelho(inner.Numero);
            }
        } 
        catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_DEFINICAO_TECLADO(Inner inner)
    {
        int Ret = 0;
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Definição teclado...");
        //Envia mensagem Padrão Online..
        easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "ENTRADA OU SAIDA?");
        Ret = easyInner.EnviarFormasEntradasOnLine(inner.Numero,
                0, //Quantidade de Digitos do Teclado.. (Não aceita digitação numérica)
                0, //0 – não ecoa
                Enumeradores.ACEITA_TECLADO,
                10, // Tempo de entrada do Teclado (10s).
                32);//Posição do Cursor (32 fica fora..)

        //Se Retorno OK, vai para proximo estado..
        if (Ret == Enumeradores.RET_COMANDO_OK) {
            intTentativas = 0;
            inner.EstadoTeclado = Enumeradores.EstadosTeclado.AGUARDANDO_TECLADO;
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_AGUARDA_DEFINICAO_TECLADO;
        } else {
            //Caso o retorno não for OK, tenta novamente até 3x..
            if (MaximoNumeroTentativas() == true) {
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
            }
        }
    }

    private void PASSO_ESTADO_AGUARDA_DEFINICAO_TECLADO(Inner inner)
    {
        try {
            int Ret = 0;
            int[] Bilhete = new int[8];
            StringBuffer Cartao;
            String NumCartao;
            String Bilhetee;

            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Estado aguardar definição teclado");

            Cartao = new StringBuffer();
            NumCartao = new String();

            //Envia o Comando de Coleta de Bilhetes..
            Ret = easyInner.ReceberDadosOnLine(inner.Numero, Bilhete, Cartao);

            //Atribui Temporizador
            inner.Temporizador = (int) System.currentTimeMillis();

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                if (inner.EstadoTeclado == Enumeradores.EstadosTeclado.AGUARDANDO_TECLADO) {
                    //****************************************************
                    //Entrada, saída liberada, confirma, anula ou função tratar mensagem
                    //66 - "Entrada" via teclado
                    //67 - "Saída" via teclado
                    //35 - "Confirma" via teclado
                    //42 - "Anula" via teclado
                    //65 - "Função" via teclado
                    switch (Integer.parseInt(String.valueOf(Bilhete[1])))
                    {
                    //entrada
                        case Enumeradores.TECLA_ENTRADA:
                            HabilitarLadoCatraca("Entrada", inner.CatInvertida);
                            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                            break;
                    //saida
                        case Enumeradores.TECLA_SAIDA:
                            HabilitarLadoCatraca("Saida", inner.CatInvertida);
                            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                            break;
                    //confirma
                        case Enumeradores.TECLA_CONFIRMA:
                            easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "LIBERADO DOIS   SENTIDOS.");
                            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                            break;
                    //anula
                        case Enumeradores.TECLA_ANULA:
                            easyInner.LigarBackLite(inner.Numero);
                            inner.TempoInicialMensagem = (int) System.currentTimeMillis();
                            inner.CountTentativasEnvioComando = 0;
                            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONFIGURAR_ENTRADAS_ONLINE;
                            break;
                    //função
                        case Enumeradores.TECLA_FUNCAO:
                            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_DEFINICAO_TECLADO;
                            break;
                        default:
                            break;
                    }
                    inner.EstadoTeclado = Enumeradores.EstadosTeclado.TECLADO_EM_BRANCO;
                }
            } else {
                long temp = System.currentTimeMillis() - inner.TempoInicialPingOnLine;
                //Se passar 3 segundos sem receber nada, passa para o estado enviar ping on line, para manter o equipamento em on line.
                if ((int) temp > 3000) {
                    inner.EstadoSolicitacaoPingOnLine = inner.EstadoAtual;
                    inner.CountTentativasEnvioComando = 0;
                    inner.TempoInicialPingOnLine = (int) System.currentTimeMillis();
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_PING_ONLINE;
                }
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_ENVIA_MSG_URNA(Inner inner)
    {
         try {
            //Testa o Retorno do comando de Envio de Mensagem Padrão On Line
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviar mensagem urna...");
            if (easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, " DEPOSITE O       CARTAO") == Enumeradores.RET_COMANDO_OK) {
                easyInner.AcionarRele2(inner.Numero);
                inner.CountTentativasEnvioComando = 0;
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_MONITORA_URNA;
            } else {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                inner.CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_MONITORA_URNA(Inner inner)
    {
        try {
            //Exibe estado do giro
            frmOnline.AtualizarDados("Monitorando urna!");

            //Exibe estado do Inner no Rodapé da Janela
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Monitora urna...");

            //Declaração de Variáveis..
            int[] Bilhete = new int[8];
            StringBuffer Cartao;
            String NumCartao;
            int Ret = Enumeradores.Limpar;

            Cartao = new StringBuffer();
            NumCartao = new String();

            //Monitora o giro da catraca..
            Ret = easyInner.ReceberDadosOnLine(inner.Numero, Bilhete, Cartao);

            //Testa o retorno do comando..
            if (Ret == Enumeradores.RET_COMANDO_OK) {
                //Testa se girou o não a catraca..
                switch (Bilhete[0])
                {
                    case Enumeradores.URNA:
                        frmOnline.AtualizarDados("URNA RECOLHEU CARTÃO");
                        //Vai para o estado de Envio de Msg Padrão..
                        LiberaSaida = true;
                        inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                        break;
                    case Enumeradores.FIM_TEMPO_ACIONAMENTO:
                        frmOnline.AtualizarDados("NÃO DEPOSITOU CARTÃO");
                        //easyInner.AcionarBipLongo(inner.Numero);
                        easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "     ACESSO          NEGADO");
                        //Vai para o estado de Envio de Msg Padrão..
                        inner.TempoInicialMensagem = (int) System.currentTimeMillis();
                        inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_AGUARDA_TEMPO_MENSAGEM;
                        break;
                    case Enumeradores.URNA_CHEIA:
                        frmOnline.AtualizarDados("URNA CHEIA");
                        easyInner.AcionarBipLongo(inner.Numero);
                        //Vai para o estado de Envio de Msg Padrão..
                        inner.TempoInicialMensagem = (int) System.currentTimeMillis();
                        inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_URNA_CHEIA;
                        break;
                    default:
                        break;
                }
                
            } else {
                //Caso o tempo que estiver monitorando o giro chegue a 3 segundos,
                long tempo = (System.currentTimeMillis() - inner.TempoInicialPingOnLine);
                //deverá enviar o ping on line para manter o equipamento em modo on line
                if (tempo > 3000) {
                    inner.EstadoSolicitacaoPingOnLine = inner.EstadoAtual;
                    inner.CountTentativasEnvioComando = 0;
                    inner.TempoInicialPingOnLine = (int) System.currentTimeMillis();
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_PING_ONLINE;
                }
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_ACIONAR_RELE1(Inner inner)
    {
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Estado acionar rele 1");
        //Aciona Bip Curto..
        easyInner.AcionarBipCurto(inner.Numero);
        //Desliga Led Verde
        easyInner.LigarLedVerde(inner.Numero);
        easyInner.AcionarRele1(inner.Numero);
        inner.TempoInicialMensagem = (int)System.currentTimeMillis();
        inner.Tentativas = 0;
        easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "Acesso Liberado!");
        inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_AGUARDA_TEMPO_MENSAGEM;
    }

    private void PASSO_ESTADO_ENVIAR_CONFIGMUD_ONLINE_OFFLINE(Inner inner)
    {
        try {
            int Ret = 0;
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviar mudanças on off...");
            easyInner.HabilitarMudancaOnLineOffLine(2, 20);

            //Configura o teclado para quando o Inner voltar para OnLine após uma queda
            //para OffLine.
            easyInner.DefinirConfiguracaoTecladoOnLine(inner.QtdDigitos, 1, 5, 17);

            //Define Mudanças OnLine
            //Função que configura BIT a BIT, Ver no manual Anexo III
            easyInner.DefinirEntradasMudancaOnLine(ConfiguraEntradasMudancaOnLine(inner));

            if (inner.Biometrico) {
                // Configura entradas mudança OffLine com Biometria
                easyInner.DefinirEntradasMudancaOffLineComBiometria((inner.Teclado ? Enumeradores.Opcao_SIM : Enumeradores.Opcao_NAO), 3, (byte) (inner.DoisLeitores ? 3 : 0), inner.Verificacao, inner.Identificacao);
            } else {
                // Configura entradas mudança OffLine
                easyInner.DefinirEntradasMudancaOffLine((inner.Teclado ? Enumeradores.Opcao_SIM : 
                                                        Enumeradores.Opcao_NAO), 
                                                        (byte)(inner.DoisLeitores ? 1 : 3), 
                                                        (byte)(inner.DoisLeitores ? 2 : 0), 0);
            }
            
            //Define mensagem de Alteração Online -> Offline.
            easyInner.DefinirMensagemPadraoMudancaOffLine(1, " Modo OffLine");

            //Define mensagem de Alteração OffLine -> OnLine.
            easyInner.DefinirMensagemPadraoMudancaOnLine(1, "Modo Online");

            //Envia Configurações.
            Ret = easyInner.EnviarConfiguracoesMudancaAutomaticaOnLineOffLine(inner.Numero);

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                inner.CountTentativasEnvioComando = 0;
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONFIGURAR_ENTRADAS_ONLINE;
                inner.TempoColeta = (int) System.currentTimeMillis() + 3000;
                inner.TentativasColeta = 0;
            } else {
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                inner.CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_ENVIAR_MSG_OFFLINE_CATRACA(Inner inner)
    {
        try 
        {    
            int Ret = 0;
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviar mensagem off catraca...");
            if(inner.CatInvertida)
            {
                //Mensagem Entrada e Saida Offline Liberado!
                easyInner.DefinirMensagemEntradaOffLine(1, "Entrada liberada.");
                easyInner.DefinirMensagemSaidaOffLine(1, "Saida liberada.");            
            }
            else
            {
                //Mensagem Entrada e Saida Offline Liberado!
                easyInner.DefinirMensagemEntradaOffLine(1, "Saida liberada.");
                easyInner.DefinirMensagemSaidaOffLine(1, "Entrada liberada.");
            }

            easyInner.DefinirMensagemPadraoOffLine(1, "Modo OffLine");

            Ret = easyInner.EnviarMensagensOffLine(inner.Numero);

            if (Ret == Enumeradores.RET_COMANDO_OK) {
                inner.CountTentativasEnvioComando = 0;
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_DATA_HORA;
                inner.TempoColeta = (int) System.currentTimeMillis() + 3000;
            } else {
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                inner.CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_ENVIAR_MSG_ACESSO_NEGADO(Inner inner)
    {
        try {
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviar mensagem acesso negado...");
            //Testa o Retorno do comando de Envio de Mensagem Padrão On Line
            if (easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, " Acesso Negado!  NAO CADASTRADO \r\n") == Enumeradores.RET_COMANDO_OK) {
                inner.TempoInicialMensagem = System.currentTimeMillis();
                easyInner.AcionarBipLongo(inner.Numero);
                //if (inner.InnerNetAcesso) {
                easyInner.LigarLedVermelho(inner.Numero);
                //}
                //Muda o passo para configuração de entradas Online.
                inner.TempoInicialMensagem= (int) System.currentTimeMillis();
                inner.CountTentativasEnvioComando = 0;
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_AGUARDA_TEMPO_MENSAGEM;
            } 
            else 
            {
                //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
                if (inner.CountTentativasEnvioComando >= 3) {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
                }
                //Adiciona 1 ao contador de tentativas
                inner.CountTentativasEnvioComando++;
            }
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_VALIDA_URNA_CHEIA(Inner inner)
    {
        //Declaração de Variáveis..
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Validar urna cheia...");
        easyInner.AcionarRele2(inner.Numero);
        int[] Bilhete = new int[8];
        StringBuffer Cartao;
        String NumCartao;
        int Ret = Enumeradores.Limpar;

        Cartao = new StringBuffer();
        NumCartao = new String();

        //Monitora para ver se a Urna esta cheia
        Ret = easyInner.ReceberDadosOnLine(inner.Numero, Bilhete, Cartao);
        
        //Testa o retorno do comando.. 
        if (Ret == Enumeradores.RET_COMANDO_OK) {
            //Testa se a urna esta cheia
            if (Bilhete[0] == Enumeradores.URNA_CHEIA) {
                frmOnline.AtualizarDados("URNA CHEIA");
                easyInner.AcionarBipLongo(inner.Numero);
                //Vai para o estado de Envio de Msg Urna cheia
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_URNA_CHEIA;
            }
        }
        else{
            System.out.println("ESTADO_ENVIA_MSG_URNA");
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIA_MSG_URNA;
        }
    }

    private void PASSO_ESTADO_ENVIAR_MSG_URNA_CHEIA(Inner inner)
    {
        int Ret = Enumeradores.Limpar;
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviar mensagem urna cheia...");
                       
        Ret = easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "   URNA CHEIA    ESVAZIAR URNA ");
        
        if  (Ret ==  Enumeradores.RET_COMANDO_OK){
            
            if (inner.InnerNetAcesso) {
                easyInner.LigarLedVermelho(inner.Numero);
            }
            
            inner.TempoInicialMensagem = (int) System.currentTimeMillis();
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_AGUARDA_TEMPO_MENSAGEM;
        }
        else{
            //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
            if (inner.CountTentativasEnvioComando >= 3) {
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
            }
            //Adiciona 1 ao contador de tentativas
            inner.CountTentativasEnvioComando++;
        }
    }

    private void PASSO_ESTADO_VALIDAR_ACESSO(Inner inner)
    {
        //inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_DEFINE_PROCESSO;
        //E Urna ou entrada e saída ou liberada 2 sentidos ou sentido giro
        //E cartão = proximidade     
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Validar Acesso...");
//        if (LiberaAcesso(inner.BilheteInner.Cartao.toString()) == false) 
//        {
//            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_ACESSO_NEGADO;
//        } //Se 1 leitor
//        //E Urna ou entrada e saída ou liberada 2 sentidos ou sentido giro
//                //E cartão = proximidade
//        else 
            if (((inner.DoisLeitores == false)
                && ((inner.Acionamento == Enumeradores.Acionamento_Catraca_Urna)
                || (inner.Acionamento == Enumeradores.Acionamento_Catraca_Entrada_E_Saida)
                || (inner.Acionamento == Enumeradores.Acionamento_Catraca_Liberada_2_Sentidos)
                || (inner.Acionamento == Enumeradores.Acionamento_Catraca_Sentido_Giro))
                && ((inner.TipoLeitor == 2) || (inner.TipoLeitor == 3)
                || (inner.TipoLeitor == 4)))) {
            if (inner.EstadoTeclado == Enumeradores.EstadosTeclado.TECLADO_EM_BRANCO) {
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_DEFINICAO_TECLADO;
            }

             //Se estamos trabalhando com Urna e 1 leitor
            if ((inner.Catraca) && (inner.Acionamento == Enumeradores.Acionamento_Catraca_Urna)) {
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIA_MSG_URNA;
            }
        }
        else if (inner.Acionamento == Enumeradores.Acionamento_Coletor){
            if (inner.BilheteInner.Origem == Enumeradores.ORIGEM_VIA_LEITOR2){
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ACIONAR_RELE2;
            }else{
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ACIONAR_RELE1;
            }
        }
        else 
        {
            if (inner.Catraca) {
                if (inner.Acionamento == Enumeradores.Acionamento_Catraca_Entrada) 
                {
                    HabilitarLadoCatraca("Entrada", inner.CatInvertida); 
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                } 
                else if (inner.Acionamento == Enumeradores.Acionamento_Catraca_Saida) 
                {
                    HabilitarLadoCatraca("Saida", inner.CatInvertida);
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                }
                
                //Acionamento saida liberada
                else if(inner.Acionamento == Enumeradores.Acionamento_Catraca_Saida_Liberada)
                {
                    HabilitarLadoCatraca("Entrada", inner.CatInvertida);
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                }    
                //Acionamento Etranda Liberada
                else if(inner.Acionamento == Enumeradores.Acionamento_Catraca_Entrada_Liberada)
                {
                    HabilitarLadoCatraca("Saida", inner.CatInvertida);
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                }
                
                //Se Urna e 2 leitores
                else if (inner.Acionamento == Enumeradores.Acionamento_Catraca_Urna && inner.BilheteInner.Origem == Enumeradores.VIA_LEITOR2) 
                {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_VALIDA_URNA_CHEIA;
                } 
                else 
                {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_LIBERAR_CATRACA;
                }
            } 
        }           
    }
    
    private void PASSO_ESTADO_RECEBER_VERSAO_FW(Inner inner)
    {
        int Ret = 0;
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviar receber versão FW...");
        int Versao[] = new int[16];
        //Solicita a versão do firmware do Inner e dados como o Idioma, se é
        //uma versão especial.
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Estado receber firware...");
        Ret = easyInner.ReceberVersaoFirmware6xx(inner.Numero, Versao);
        
        inner.VersaoFW.setLinhaProduto(Versao[0]);
        inner.VersaoFW.setVariacaoSup(Versao[1]);
        inner.VersaoFW.setVariacaoInf(Versao[2]);
        inner.VersaoFW.setFirwareSup(Versao[3]);
        inner.VersaoFW.setFirwareInf(Versao[4]);
        inner.VersaoFW.setFirwareSuf(Versao[5]);
        inner.VersaoFW.setTipoEquip(Versao[6]);
        inner.VersaoFW.setTipoModuloBio(Versao[7]);
        //Se selecionado Biometria, valida se o equipamento é compatível
        if (inner.Biometrico) {
            if ((((inner.VersaoFW.getLinhaProduto() != 6) && (inner.VersaoFW.getLinhaProduto() != 14)) || 
                    ((inner.VersaoFW.getLinhaProduto() == 14) && (inner.VersaoFW.getTipoEquip() == 0)))) {
                //JOptionPane.showMessageDialog(null, "Equipamento " + String.valueOf(inner.Numero) + 
                        //" não compatível com Biometria.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (Ret == Enumeradores.RET_COMANDO_OK) {
            //Define a linha do Inner
            switch (inner.VersaoFW.getLinhaProduto()) {
                case 1:
                    inner.LinhaInner = "Inner Plus";
                    break;

                case 2:
                    inner.LinhaInner = "Inner Disk";
                    break;

                case 3:
                    inner.LinhaInner = "Inner Verid";
                    break;

                case 6:
                    inner.LinhaInner = "Inner Bio";
                    break;

                case 7:
                    inner.LinhaInner = "Inner NET";
                    break;

                case 14:
                    inner.LinhaInner = "Inner Acesso";
                    inner.InnerNetAcesso = true;
                    break;
            }
        }
        inner.VersaoInner = Integer.toString(inner.VersaoFW.getFirwareSup()) + '.' + 
                            Integer.toString(inner.VersaoFW.getFirwareInf()) + '.' + 
                            Integer.toString(inner.VersaoFW.getFirwareSuf());
        if (inner.VersaoFW.getLinhaProduto() == 6 || inner.VersaoFW.getTipoEquip() == 1)
        {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECEBER_MODELO_BIO;
        } else {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_CFG_OFFLINE;
        }
    }

    private int TestarConexaoInner(int Numero)
    {
        int[] DataHora = new int[6];
        Integer ret = easyInner.ReceberRelogio(Numero, DataHora);
        return ret;
    }

    private void PASSO_ESTADO_RECEBER_MODELO_BIO(Inner inner) throws InterruptedException
    {
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Estado receber modelo bio...");
        //Se for biometria
        if (inner.VersaoFW.getFirwareSup() < 6) {
            inner.ModeloBioInner = BioService.ReceberModeloBioAVer5xx(inner.Numero);
        }else {
            inner.ModeloBioInner = BioService.ReceberModeloBio6xx(inner.Numero, inner.TipoComBio);
        }
        if (inner.ModeloBioInner != null && inner.ModeloBioInner != "Modulo incorreto"){
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECEBER_VERSAO_BIO;
        }else if ("Modulo incorreto".equals(inner.ModeloBioInner)){
            PararMaquinaOnline();
            //JOptionPane.showMessageDialog(null, "Modulo incorreto", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } else {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
        }
    }
     private void PASSO_ESTADO_RECEBER_VERSAO_BIO(Inner inner) throws InterruptedException
    {
        int Ret = 0;
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Estado receber versão bio...");
        //Se for biometria
        if (inner.VersaoFW.getFirwareSup() < 6) {
            inner.VersaoBio = BioService.ReceberVersaoBioAVer5xx(inner.Numero);
        }else {
            inner.VersaoBio = BioService.ReceberVersaoBio6xx(inner.Numero, inner.TipoComBio);
        }
        if (inner.VersaoBio != null && inner.VersaoBio != "Modulo incorreto"){
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_CFG_OFFLINE;
        }
        else if ("Modulo incorreto".equals(inner.VersaoBio)){
            PararMaquinaOnline();
            //JOptionPane.showMessageDialog(null, "Modulo incorreto", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } else {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
        }
    }
    
    private void MontaConfiguracaoInner(Inner inner, int modo)
    {
        try {
            // ANTES de realizar a configuração precisa definir o Padrão do cartão
            if (inner.PadraoCartao == 1) {
                easyInner.DefinirPadraoCartao(Enumeradores.PADRAO_LIVRE);
            } else {
                easyInner.DefinirPadraoCartao(Enumeradores.PADRAO_TOPDATA);
            }

            //Define Modo de comunicação
            if (modo == Enumeradores.MODO_OFF_LINE) {
                //Configurações para Modo Offline.
                //Prepara o Inner para trabalhar no modo Off-Line, porém essa função
                //ainda não envia essa informação para o equipamento.
                easyInner.ConfigurarInnerOffLine();
            } else {
                //Configurações para Modo Online.
                //Prepara o Inner para trabalhar no modo On-Line, porém essa função
                //ainda não envia essa informação para o equipamento.
                easyInner.ConfigurarInnerOnLine();
            }

            //Verificar
            //Acionamentos 1 e 2
            //Configura como irá funcionar o acionamento(rele) 1 e 2 do Inner, e por
            //quanto tempo ele será acionado.
            switch (inner.Acionamento) {
                //Coletor
                case Enumeradores.Acionamento_Coletor:
                    easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA_OU_SAIDA, 7);
                    easyInner.ConfigurarAcionamento2(Enumeradores.ACIONA_REGISTRO_ENTRADA_OU_SAIDA, 7);
                    break;

                //Catraca
                case Enumeradores.Acionamento_Catraca_Entrada_E_Saida:
                    easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA_OU_SAIDA, 7);
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;

                case Enumeradores.Acionamento_Catraca_Entrada:
                    if(inner.CatInvertida)
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_SAIDA, 7);    
                    } 
                    else
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA, 7);
                    }
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;

                case Enumeradores.Acionamento_Catraca_Saida:
                    if(inner.CatInvertida)
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA, 7);    
                    } 
                    else
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_SAIDA, 7);
                    }
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break; 
                      

                case Enumeradores.Acionamento_Catraca_Urna:
                    easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA_OU_SAIDA, 7);
                    easyInner.ConfigurarAcionamento2(Enumeradores.ACIONA_REGISTRO_SAIDA, 5);
                    break;

                case Enumeradores.Acionamento_Catraca_Saida_Liberada:
                    //Se Esquerda Selecionado - Inverte código
                    if(inner.CatInvertida && (!inner.InnerNetAcesso))
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_ENTRADA_LIBERADA, 7);
                    }
                    else
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_SAIDA_LIBERADA, 7);
                    }
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;

                case Enumeradores.Acionamento_Catraca_Entrada_Liberada:
                    //Se Esquerda Selecionado - Inverte código
                    if(inner.CatInvertida && (!inner.InnerNetAcesso))
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_SAIDA_LIBERADA, 7);
                    }
                    else
                    {
                        easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_ENTRADA_LIBERADA, 7);
                    }
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;

                case Enumeradores.Acionamento_Catraca_Liberada_2_Sentidos:
                    easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_LIBERADA_DOIS_SENTIDOS, 7);
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;

                case Enumeradores.Acionamento_Catraca_Sentido_Giro:
                    easyInner.ConfigurarAcionamento1(Enumeradores.CATRACA_LIBERADA_DOIS_SENTIDOS_MARCACAO_REGISTRO, 7);
                    easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                    break;
            }

            SelecionarTipoLeitor(inner);

            easyInner.DefinirQuantidadeDigitosCartao(inner.QtdDigitos);

            //Habilitar teclado
            easyInner.HabilitarTeclado((inner.Teclado ? Enumeradores.Opcao_SIM : Enumeradores.Opcao_NAO), 0);

            //Define os valores para configurar os leitores de acordo com o tipo de inner
            DefineValoresParaConfigurarLeitores(inner);
            easyInner.ConfigurarLeitor1(inner.ValorLeitor1);
            easyInner.ConfigurarLeitor2(inner.ValorLeitor2);

            //Box = Configura equipamentos com dois leitores
            if (inner.DoisLeitores) {
                // exibe mensagens do segundo leitor
                easyInner.ConfigurarWiegandDoisLeitores(1, Enumeradores.Opcao_SIM);
            }

            // Registra acesso negado
            easyInner.RegistrarAcessoNegado(1);

            //Catraca
            //Define qual será o tipo do registro realizado pelo Inner ao aproximar um
            //cartão do tipo proximidade no leitor do Inner, sem que o usuário tenha
            //pressionado a tecla entrada, saída ou função.
            if ((inner.Acionamento == Enumeradores.Acionamento_Catraca_Entrada_E_Saida) || 
                    (inner.Acionamento == Enumeradores.Acionamento_Catraca_Liberada_2_Sentidos) || 
                    (inner.Acionamento == Enumeradores.Acionamento_Catraca_Sentido_Giro)) {
                easyInner.DefinirFuncaoDefaultLeitoresProximidade(12); // 12 – Libera a catraca nos dois sentidos e registra o bilhete conforme o sentido giro.
            } else {
                if ((inner.Acionamento == Enumeradores.Acionamento_Catraca_Entrada) || 
                        (inner.Acionamento == Enumeradores.Acionamento_Catraca_Saida_Liberada)) {
                    if (inner.CatInvertida == false) {
                        easyInner.DefinirFuncaoDefaultLeitoresProximidade(10);  // 10 – Registrar sempre como entrada.
                    } else {
                        easyInner.DefinirFuncaoDefaultLeitoresProximidade(11);  // 11 – Registrar sempre como saída.
                    }
                } else {
                    if (inner.CatInvertida == false) {
                        easyInner.DefinirFuncaoDefaultLeitoresProximidade(11);  // 11 – Registrar sempre como saída.
                    } else {
                        easyInner.DefinirFuncaoDefaultLeitoresProximidade(10);  // 10 – Registrar sempre como entrada.
                    }
                }
            }

            //Configura o tipo de registro que será associado a uma marcação
            if (inner.Biometrico) {
                easyInner.DefinirFuncaoDefaultSensorBiometria(10);
            } else {
                easyInner.DefinirFuncaoDefaultSensorBiometria(0);
            }

            //Configura para receber o horario dos dados qdo Online.
            if (inner.QtdDigitos <= 14) {
                easyInner.ReceberDataHoraDadosOnLine(Enumeradores.Opcao_SIM);
            }

            // Caso desejar configurar o Inner para ler cartoes que possam 
            // variar de 1 dígito até 16 dígitos
            // utilizar a funcao InserirQuantidadeDigitoVariavel
            // easyInner.InserirQuantidadeDigitoVariavel(8);
            // easyInner.InserirQuantidadeDigitoVariavel(10);
            // easyInner.InserirQuantidadeDigitoVariavel(14);
            if (inner.VersaoFW.getFirwareSup() >= 5)
            {
                easyInner.ConfigurarBioVariavel(1);
            }
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(frmOnline, ex);
            //System.out.println(ex.printStackTrace());
            System.out.println(ex);
        }
    }

    private void SelecionarTipoLeitor(Inner inner)
    {
        //Configurar tipo do leitor
        switch (inner.TipoLeitor) {
            case Enumeradores.CODIGO_DE_BARRAS:
                easyInner.ConfigurarTipoLeitor(Enumeradores.CODIGO_DE_BARRAS);
                break;
            case Enumeradores.MAGNETICO:
                easyInner.ConfigurarTipoLeitor(Enumeradores.MAGNETICO);
                break;
            case Enumeradores.PROXIMIDADE_ABATRACK2:
                easyInner.ConfigurarTipoLeitor(Enumeradores.PROXIMIDADE_ABATRACK2);
                break;
            case Enumeradores.WIEGAND:
                easyInner.ConfigurarTipoLeitor(Enumeradores.WIEGAND);
                break;
            case Enumeradores.PROXIMIDADE_SMART_CARD:
                easyInner.ConfigurarTipoLeitor(Enumeradores.PROXIMIDADE_SMART_CARD);
                break;
            case Enumeradores.CODIGO_BARRAS_SERIAL:
                easyInner.ConfigurarTipoLeitor(Enumeradores.CODIGO_BARRAS_SERIAL);
                break;
            case Enumeradores.WIEGAND_FC_SEM_ZERO:
                easyInner.ConfigurarTipoLeitor(Enumeradores.WIEGAND_FC_SEM_ZERO);
                break;
        }
    }

    private void DefineValoresParaConfigurarLeitores(Inner inner)
    {
        //Configuração Catraca Esquerda ou Direita
        //define os valores para configurar os leitores de acordo com o tipo de inner
        if (inner.DoisLeitores) {
            if (inner.CatInvertida == false) {
                //Direita Selecionado
                inner.ValorLeitor1 = Enumeradores.SOMENTE_ENTRADA;
                inner.ValorLeitor2 = Enumeradores.SOMENTE_SAIDA;
            } else {
                //Esquerda Selecionado
                inner.ValorLeitor1 = Enumeradores.SOMENTE_SAIDA;
                inner.ValorLeitor2 = Enumeradores.SOMENTE_ENTRADA;
            }
        } else {
            if (inner.CatInvertida == false) {
                //Direita Selecionado   
                inner.ValorLeitor1 = Enumeradores.ENTRADA_E_SAIDA;
            } else {
                //Esquerda Selecionado
                inner.ValorLeitor1 = Enumeradores.ENTRADA_E_SAIDA_INVERTIDAS;
            }

            inner.ValorLeitor2 = Enumeradores.DESATIVADO;

        }
    }

    private void MontarHorarios() throws IOException, FileNotFoundException, ClassNotFoundException, SQLException
    {
        //Insere no buffer da DLL horario de acesso    
        byte bTime;
        byte bDay;
        byte bRange;
        byte bHour;
        byte bMin;
        
        List<Horarios> ListaHorarios = Horarios.MontarListaHorarios();

        for (int index = 0; index < ListaHorarios.size(); index++)
        {
            easyInner.InserirHorarioAcesso(ListaHorarios.get(index).horario, ListaHorarios.get(index).dia, 
                                            ListaHorarios.get(index).faixa, ListaHorarios.get(index).hora, 
                                            ListaHorarios.get(index).minuto);
        }
    }

    private void MontarListaPadraoLivre(int QtdDigitos)
    {
        easyInner.DefinirPadraoCartao(1);

        easyInner.DefinirQuantidadeDigitosCartao(QtdDigitos);
        
        try
        {
            List<Usuarios> listaCartao  = DAOUsuarios.consultarUsuarios(0);
            
            /*Finding if the card is included at the listOffLine, it can be 
            Online list too*/
            for (int i=0 ;listaCartao.size() >i; i++) 
            {                
                easyInner.InserirUsuarioListaAcesso(listaCartao.get(i).getUsuario(), 101);
            }
        }
        catch(IOException | SQLException | ClassNotFoundException e)
        {
            System.out.println(e);
        }
    }

    private void MontarListaPadraoTopdata(int QtdDigitos)
    {
        easyInner.DefinirPadraoCartao(0);

        easyInner.DefinirQuantidadeDigitosCartao(QtdDigitos);

        for (int i = 0; i < 5; i++) {
            easyInner.InserirUsuarioListaAcesso(Integer.toString(i), 101);
        }
    }

    private void MontarListaSemDigital(boolean InnerAcesso)
    {
        DAOUsuariosBio AcessoBio = new DAOUsuariosBio();
        List<UsuarioSemDigital> USersSD = null;
        try
        {
            USersSD = AcessoBio.ConsultarUsuarioSemDigital();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (UsuarioSemDigital s : USersSD) {
            if (InnerAcesso)
            {
                easyInner.IncluirUsuarioSemDigitalBioInnerAcesso(s.getCartao());
            }
            else
            {
                easyInner.IncluirUsuarioSemDigitalBio(s.getCartao());
            }
        }
    }

    private int ConfiguraEntradasMudancaOnLine(Inner inner)
    {
        String Configuracao;

        //Habilita Teclado
        Configuracao = (inner.Teclado ? "1" : "0");

        if (!inner.Biometrico) {
            //Código de Barras e Proximidade

            //Dois leitores
            if (inner.DoisLeitores) {
                Configuracao = "010" + //Leitor 2 só saida
                        "001" + //Leitor 1 só entrada
                        Configuracao;
            } else { //Apenas um leitores
                Configuracao = "000" + //Leitor 2 Desativado
                        "011" + //Leitor 1 configurado para Entrada e Saída
                        Configuracao;
            }

            Configuracao = "1" + // Habilitado
                    Configuracao;

            /*
             --------------------------------------------------------------------------------------------------
             |       7        |     6      |   5    |   4    |   3    |    2    |      1     |        0       |
             --------------------------------------------------------------------------------------------------
             | Seta/Reseta    |  Bit 2     |  Bit 1 |  Bit 0 | Bit 2  |  Bit 1  |   Bit 0    |  Teclado       |
             |   config.      | Leitor 2   |        |        |        |         |            |                |
             |   bit-a-bit    |            |        |        |        |         |            |                |
             --------------------------------------------------------------------------------------------------
             | 1 – Habilita   | 000 – Desativa leitor        |  000 - Desativa leitor        | 1 – Habilita   |
             | 0 – Desabilita | 001 - Leitor só entrada      |  001 - Leitor só entrada      | 0 – Desabilita |
             |                | 010 - Leitor só saída        |  010 - Leitor só saída        |                |
             |                | 011 - Leitor Entrada e saída |  011 - Leitor Entrada e saída |                |
             |                | 100 - Leitor Entrada e Saída |  100 - Leitor Entrada e       |                |
             |                |   Invertido                  |   Saída Invertido             |                |
             --------------------------------------------------------------------------------------------------
             */
        } else { //Com Biometria

            Configuracao = "0" + //Bit Fixo
                    "1" + //Habilitado
                    inner.Identificacao + //Identificação
                    inner.Verificacao + //Verificação
                    "0" + //Bit fixo
                    (inner.DoisLeitores ? "11" : "10") + // 11 -> habilita leitor 1 e 2, 10 -> habilita apenas leitor 1
                    Configuracao;

            /*
             ------------------------------------------------------------------------------------------------------------------------
             |    7     |       6       |       5       |       4       |      3       |       2      |      1       |      0       |
             ------------------------------------------------------------------------------------------------------------------------
             | Bit fixo | Seta/Reseta   | Identificação |  Verificação  |   Bit fixo   |   Leitor 1   | Leitor 2     |  Teclado     |
             |   '0'    |    config.    |      Bio      |      Bio      |    Config    |              |              |              |
             |          | bit-a-bit bio |               |               |      L2      |              |              |              |
             |          |               |               |               |     '0'      |              |              |              |
             ------------------------------------------------------------------------------------------------------------------------
             |    0     |  1-Habilita   | 1-Habilita    | 1-Habilita    | 1-Habilita   | 1-Habilita   | 1-Habilita   | 1-Habilita   |
             |          |  0-Desabilita | 0-Desabilita  | 0-Desabilita  | 0-Desabilita | 0-Desabilita | 0-Desabilita | 0-Desabilita |
             ------------------------------------------------------------------------------------------------------------------------
             */
        }

        //Converte Binário para Decimal
        return EasyInnerUtils.BinarioParaDecimal(Configuracao);
    }

    private void MontarBilheteRecebido(Inner inner)
    {
        String sBilheteRecebido = inner.BilheteInner.Cartao.toString();
        int tam;
        
        if (inner.QtdDigitos > sBilheteRecebido.length())
        {      
            tam = sBilheteRecebido.length();
        }
        else
        {
            tam = inner.QtdDigitos;
        }
       
        String sNumCartao = "";
        
        //Se o cartão padrão for topdata, configura os dígitos do cartão como padrão topdata
        if (inner.PadraoCartao == 0) 
        {
            //Padrão Topdata --> Cartão Topdata deve ter sempre 14 dígitos.
                    //5 dígitos5
            sNumCartao = Long.toString(Long.parseLong(sBilheteRecebido.substring(0,tam)));
            sNumCartao = sNumCartao.substring(13, 14) + "" + sNumCartao.substring(4, 8);
            
            //Gravando no Inner o Numero do Cartao formatado
            StringBuilder sbAux = new StringBuilder(sNumCartao);
            inner.BilheteInner.Cartao.delete(0,inner.BilheteInner.Cartao.length());
            inner.BilheteInner.Cartao = new StringBuilder(sNumCartao);

        } 
        else 
        {
           sNumCartao = EasyInnerUtils.remZeroEsquerda(inner.BilheteInner.Cartao.toString());
            //Gravando no Inner o Numero do Cartao formatado
            inner.BilheteInner.Cartao.delete( 0, inner.BilheteInner.Cartao.length());
            inner.BilheteInner.Cartao = new StringBuilder(sNumCartao);
        }
    }

    private boolean LiberaAcesso(String NumCartao)
    {
         try
        {
            List<Usuarios> listaCartao  = DAOUsuarios.consultarUsuarios(0);
            boolean ret                 = false;
            
            /*Finding if the card is included at the listOffLine, it can be 
            Online list too*/
            for (int i=0 ;listaCartao.size() >i; i++) 
            {
                String slCartaoSZero = EasyInnerUtils.remZeroEsquerda(listaCartao.get(i).getUsuario());
                if (slCartaoSZero.equals(NumCartao.trim())) 
                {
                    if(listaCartao.get(i).getFaixa()==101)
                    {
                        ret =  true;
                        break;
                    }
                    else
                    {
                        ret = false;
                        break;
                    }        
                }
            }
            return ret;
        }
        catch(IOException | SQLException | ClassNotFoundException e)
        {
            return false;
        }
    }

    public void HabilitarLadoCatraca(String lado, boolean Esquerda)
    {
        if (lado.equals("Entrada")) {
            //entrada
            if (Esquerda == false) {
                LiberaEntrada = true;
                LiberaEntradaInvertida = false;
            } else {
                LiberaEntradaInvertida = true;
                LiberaEntrada = false;
            }
        }

        if (lado.equals("Saida")) {
            //saída
            if (Esquerda == false) {
                LiberaSaida = true;
                LiberaSaidaInvertida = false;
            } else {
                LiberaSaidaInvertida = true;
                LiberaSaida = false;
            }
        }
    }

    private void ColetarBilhetesInnerAcesso(Inner inner) throws InterruptedException
    {
        int[] Bilhete = new int[8];
        StringBuffer Cartao;
        int nBilhetes;
        int i = Enumeradores.Limpar;
        int QtdeBilhetes;
        int receber[] = new int[2];
        int Ret = 0;
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Coletar bilhete Inner acesso...");
        //Verifica conexao
        nBilhetes = 0;
        QtdeBilhetes = 0;
        easyInner.ReceberQuantidadeBilhetes(inner.Numero, receber);
        QtdeBilhetes = receber[0];

        do {
            if (QtdeBilhetes > 0) {
                do {
                    Thread.sleep(1000);

                    Cartao = new StringBuffer();
                    //Coleta um bilhete Off-Line que está armazenado na memória do Inner
                    Ret = easyInner.ColetarBilhete(inner.Numero, Bilhete, Cartao);
                    if (Ret == Enumeradores.RET_COMANDO_OK) {

                        //Armazena os dados do bilhete no list, pode ser utilizado com
                        //banco de dados ou outro meio de armazenamento compatível
                        frmOnline.AtuaLizarBilhetes("Tipo:" + String.valueOf(Bilhete[0]) + " Cartão:"
                                + Cartao.toString() + " Data:"
                                + (String.valueOf(Bilhete[1]).length() == 1 ? "0" + String.valueOf(Bilhete[1]) : String.valueOf(Bilhete[1])) + "/"
                                + (String.valueOf(Bilhete[2]).length() == 1 ? "0" + String.valueOf(Bilhete[2]) : String.valueOf(Bilhete[2])) + "/"
                                + String.valueOf(Bilhete[3]) + " Hora:"
                                + (String.valueOf(Bilhete[4]).length() == 1 ? "0" + String.valueOf(Bilhete[4]) : String.valueOf(Bilhete[4])) + ":"
                                + (String.valueOf(Bilhete[5]).length() == 1 ? "0" + String.valueOf(Bilhete[5]) : String.valueOf(Bilhete[5])) + ":"
                                + (String.valueOf(Bilhete[6]).length() == 1 ? "0" + String.valueOf(Bilhete[6]) : String.valueOf(Bilhete[6])) + "\n");

                        nBilhetes++;
                        QtdeBilhetes--;
                    }

                } while (QtdeBilhetes > 0);

                frmOnline.AtualizarStatus("Foram coletados " + nBilhetes + " bilhete(s) offline !");
                easyInner.ReceberQuantidadeBilhetes(inner.Numero, receber);
                QtdeBilhetes = receber[0];
            }
        } while (QtdeBilhetes > 0);
    }

    private void ColetarBilhetesInnerNet(Inner inner)
    {
        try {
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Coletar bilhete Inner Net...");
            int Ret = 0;
            int[] Bilhete = new int[8];
            StringBuffer Cartao;
            Long tempo;

            //Exibe no rodapé da janela o estado da maquina.
            frmOnline.AtualizarStatus("Inner " + Integer.toString(inner.Numero) + " Coletando Bilhetes...");

            tempo = System.currentTimeMillis() + 200;
            do {
                Cartao = new StringBuffer();
                //Envia o Comando de Coleta de Bilhetes..
                Ret = easyInner.ColetarBilhete(inner.Numero, Bilhete, Cartao);

                //Zera a contagem de Ping Online.
                inner.CntDoEvents = 0;
                inner.CountPingFail = 0;
                inner.CountRepeatPingOnline = 0;

                //Caso exista bilhete a coletar..
                if (Ret == Enumeradores.RET_COMANDO_OK) {

                    //Recebe hora atual para inicio do PingOnline
                    inner.TempoInicialPingOnLine = (int) System.currentTimeMillis();

                    //Adiciona a lista de bilhetes o Nro bilhete coletado..
                    frmOnline.AtuaLizarBilhetes("Marcações Offline. Inner: " + inner.Numero + " Complemento:"
                            + String.valueOf(Bilhete[0]) + " Data:"
                            + (String.valueOf(Bilhete[1]).length() == 1 ? "0" + String.valueOf(Bilhete[1]) : String.valueOf(Bilhete[1])) + "/"
                            + (String.valueOf(Bilhete[2]).length() == 1 ? "0" + String.valueOf(Bilhete[2]) : String.valueOf(Bilhete[2])) + "/"
                            + String.valueOf(Bilhete[3]) + " Hora:"
                            + (String.valueOf(Bilhete[4]).length() == 1 ? "0" + String.valueOf(Bilhete[4]) : String.valueOf(Bilhete[4])) + ":"
                            + (String.valueOf(Bilhete[5]).length() == 1 ? "0" + String.valueOf(Bilhete[5]) : String.valueOf(Bilhete[5])) + ":"
                            + (String.valueOf(Bilhete[6]).length() == 1 ? "0" + String.valueOf(Bilhete[6]) : String.valueOf(Bilhete[6])) + " "
                            + " Cartão: " + Cartao.toString() + "\n");

                }
            } while (System.currentTimeMillis() < tempo);
            inner.TentativasColeta += 1;
        } catch (Exception ex) {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONECTAR;
        }
    }

    private void PASSO_ESTADO_ENVIAR_LISTA_OFFLINE(Inner inner)
    {
        try{
            int Ret = 0;
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviar lista Off...");
            if (inner.Lista) {

                MontarHorarios();
                easyInner.EnviarHorariosAcesso(inner.Numero);
                //Define a Lista de verificação
                if (inner.PadraoCartao == 1) {
                    MontarListaPadraoLivre(inner.QtdDigitos);
                } 
                else
                {
                    MontarListaPadraoTopdata(inner.QtdDigitos);
                }
                
                //Define qual tipo de lista(controle) de acesso o Inner vai utilizar.
                //Utilizar lista branca (cartões fora da lista tem o acesso negado).
                easyInner.DefinirTipoListaAcesso(1);
                Ret = easyInner.EnviarListaAcesso(inner.Numero);
            } else {
                //Não utilizar a lista de acesso.
                easyInner.DefinirTipoListaAcesso(0);
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_LISTA_SEMDIGITAL;
            }
            if (Ret == Enumeradores.RET_COMANDO_OK)
            {
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_LISTA_SEMDIGITAL;
            } else {
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
            }
        }
        catch(IOException | ClassNotFoundException | SQLException ex){
            //JOptionPane.showMessageDialog(frmOnline, ex);
            System.out.println(ex);
        }
    }

    private void PASSO_ESTADO_ENVIAR_LISTA_SEMDIGITAL(Inner inner)
    {
        try {
            int Ret = 0;
            frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviar lista sem digital...");
            if (inner.ListaBio) {
                //Chama rotina que monta o buffer de cartoes que nao irao precisar da digital
                MontarListaSemDigital(inner.InnerNetAcesso);
                if (inner.InnerNetAcesso)
                {
                    //Envia o buffer com a lista de usuarios sem digital
                    Ret = easyInner.EnviarListaUsuariosSemDigitalBioVariavel(inner.Numero, inner.QtdDigitos);
                }
                else
                {
                    //Envia o buffer com a lista de usuarios sem digital
                    Ret = easyInner.EnviarListaUsuariosSemDigitalBio(inner.Numero);
                }
            }
            if (Ret == Enumeradores.RET_COMANDO_OK){
                if (inner.InnerNetAcesso){
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECEBER_QTD_BILHETES_OFF;
                } else {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_COLETAR_BILHETES;
                }
            } else {
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(frmOnline, ex);
        }
    }

    private void PASSO_ESTADO_RECEBER_QTD_BILHETES_OFF(Inner inner)
    {
        int Ret = 0;
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Receber quantidade bilhetes off...");
        int[] receber = new int[2];
        Ret = easyInner.ReceberQuantidadeBilhetes(inner.Numero, receber);
        if (Ret == Enumeradores.RET_COMANDO_OK)
        {
            inner.BilhetesAReceber = receber[0];
            if (inner.BilhetesAReceber > 0)
            {
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_COLETAR_BILHETES;
            }
            else
            {
                if (inner.Catraca)
                {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_OFFLINE_CATRACA;
                } else {
                    inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_MSG_OFFLINE_COLETOR;
                }
            }
        }
        else
        {
            if (inner.Tentativas++ > 3)
            {
                inner.Tentativas = 0;
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
            }
        }
    }

    private void PASSO_ESTADO_ENVIAR_MSG_OFF_COLETOR(Inner inner)
    {
        int Ret = 0;
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Enviar mensagem off coletor...");
        //intercala entre os horarios de entrada e saida
        byte HorMudEntrada1 = 8;
        byte MinMudEntrada1 = 0;
        byte HorMudSaida1 = 11;
        byte MinMudSaida1 = 0;

        byte HorMudEntrada2 = 14;
        byte MinMudEntrada2 = 0;
        byte HorMudSaida2 = 15;
        byte MinMudSaida2 = 0;

        byte HorMudEntrada3 = 16;
        byte MinMudEntrada3 = 0;
        byte HorMudSaida3 = 18;
        byte MinMudSaida3 = 0;

        easyInner.DefinirMensagemApresentacaoEntrada(1, "Coletor entrada");

        easyInner.DefinirMensagemApresentacaoSaida(1, "Coletor saida");

        easyInner.InserirHorarioMudancaEntrada(HorMudEntrada1, MinMudEntrada1, HorMudEntrada2, MinMudEntrada2, HorMudEntrada3, MinMudEntrada3);

        easyInner.InserirHorarioMudancaSaida(HorMudSaida1, MinMudSaida1, HorMudSaida2, MinMudSaida2, HorMudSaida3, MinMudSaida3);

        Ret = easyInner.EnviarBufferEventosMudancaAuto(inner.Numero);

        if (Ret == Enumeradores.RET_COMANDO_OK)
        {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_ENVIAR_DATA_HORA;
        }
        else
        {
            //caso ele não consiga, tentará enviar três vezes, se não conseguir volta para o passo Reconectar
            if (inner.Tentativas++ >= 3)
            {
                inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_RECONECTAR;
            }
        }
    }

    private boolean MaximoNumeroTentativas()
    {
        //Verifica se o número de tentativas é maior do que 3..
        //MAXIMO_TENTATIVAS_COMUNICACAO
        return intTentativas++ > 2; //Retorna TRUE
    }

    public void PararMaquinaOnline()
    {
        Parar = true;
        frmOnline.LimparVersao();
//        frmOnline.AtualizarBotoes(true);
        //Exibe no rodapé o Fim da execução..
        frmOnline.AtualizarStatus("Maquina parada");

        RetornarEstadoInners(Enumeradores.EstadosInner.ESTADO_CONECTAR, EstadosTeclado.AGUARDANDO_TECLADO);
    }

    private void RetornarEstadoInners(Enumeradores.EstadosInner estadosInner, Enumeradores.EstadosTeclado EstadoTeclado)
    {
        for(Object objInner : ListaInners.values())
        {
            Inner inner = (Inner)objInner;
            inner.EstadoAtual = estadosInner;
            inner.EstadoTeclado = EstadoTeclado;
        }
    }

    public void RemoverInner(int Numero)
    {
        ListaInners.remove(Numero);
    }

    private void PASSO_ESTADO_ACIONAR_RELE2(Inner inner) {
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Estado acionar rele 2");
        //Aciona Bip Curto..
        easyInner.AcionarBipCurto(inner.Numero);
        //Desliga Led Verde
        easyInner.LigarLedVerde(inner.Numero);
        easyInner.AcionarRele2(inner.Numero);
        inner.TempoInicialMensagem = (int)System.currentTimeMillis();
        inner.Tentativas = 0;
        easyInner.EnviarMensagemPadraoOnLine(inner.Numero, 0, "Acesso Liberado!");
        inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_AGUARDA_TEMPO_MENSAGEM;
    }

    private void PASSO_ESTADO_DESLIGAR_RELE(Inner inner) {
        
        frmOnline.AtualizarStatus("Inner " + inner.Numero + " Estado desligar rele");
        //Após passar os 2 segundos volta para o passo enviar mensagem padrão e desliga o rele
        long tempo = System.currentTimeMillis() - inner.TempoInicialMensagem;
        if (tempo >= 2000)
        {
            inner.EstadoAtual = Enumeradores.EstadosInner.ESTADO_CONFIGURAR_ENTRADAS_ONLINE;
            if (inner.BilheteInner.Origem == (int)Enumeradores.ORIGEM_VIA_LEITOR2)
                easyInner.DesabilitarRele2(inner.Numero);
            else
                easyInner.DesabilitarRele1(inner.Numero);
        }
    }
}
