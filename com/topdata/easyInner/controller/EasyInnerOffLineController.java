//******************************************************************************
//A Topdata Sistemas de Automação Ltda não se responsabiliza por qualquer
//tipo de dano que este software possa causar, este exemplo deve ser utilizado
//apenas para demonstrar a comunicação com os equipamentos da linha
//inner e não deve ser alterado, por este motivo ele não deve ser incluso em
//suas aplicações comerciais.
//
//Exemplo Off-Line
//Desenvolvido em Java.
//                                           Topdata Sistemas de Automação Ltda.
//******************************************************************************.
package com.topdata.easyInner.controller;

import com.topdata.EasyInner;
import com.topdata.easyInner.enumeradores.Enumeradores;
import com.topdata.easyInner.service.EasyInnerOffLineService;
import com.topdata.easyInner.ui.JIFEasyInnerOffLine;
import static com.topdata.easyInner.enumeradores.Enumeradores.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author juliano.ezequiel
 */
public class EasyInnerOffLineController {

    private final JIFEasyInnerOffLine uiOffLine;
    private final EasyInner easyInner;
    private final EasyInnerOffLineService offLineService;

    public EasyInnerOffLineController(JIFEasyInnerOffLine easyInnerOffLine) {
        this.uiOffLine = easyInnerOffLine;
        easyInner = new EasyInner();
        offLineService = new EasyInnerOffLineService(easyInner,easyInnerOffLine);
    }

    /**
     * Realiza a coleta dos bilhetes off-line
     */
    public void coletarBilhetes() 
    {

        try 
        {
            List<StringBuffer> BilhetesColetados = new ArrayList<>();
            int numInner = Integer.parseInt(uiOffLine.jTxtNumInner.getText());
            HashMap<String, Object> InfoInner = offLineService.getInfoInner(
                    numInner, Integer.parseInt(uiOffLine.jTxtPorta.getText()),
                    uiOffLine.jCboTipoConexao.getSelectedIndex());

            if (InfoInner.get("ModeloBio") == "Modulo incorreto" || InfoInner.get("VersaoBio") == "Modulo incorreto"){
                uiOffLine.jLblEnvia.setText("Modulo incorreto");
            }
            else if ((boolean) InfoInner.get("InnerAcesso")) {
                BilhetesColetados.addAll(offLineService.coletarBilhetesInnerAcesso(numInner));
            } else {
                BilhetesColetados.addAll(offLineService.coletarBilhetesInnerNet(numInner));
            }

            for (StringBuffer s : BilhetesColetados) {
                uiOffLine.jTxaBilhetes.append(s + "\r\n");
            }
            //Mensagens Status
            uiOffLine.jLblBilhetes.setText("Foram coletados " + BilhetesColetados.size() + " bilhete(s) offline !");
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(uiOffLine, "Erro " + ex);
            easyInner.FecharPortaComunicacao();
        }

    }

    /**
     * Realiza a configuração do Inner
     *
     * @throws Exception
     */
    public void enviarConfiguracoes() throws Exception {

        try {
            uiOffLine.jBtnEnviar.setEnabled(false);
            uiOffLine.jBtnReceber.setEnabled(false);
            uiOffLine.jTxaVersao.setText("");
            uiOffLine.jTxaVersao.setForeground(Color.BLACK);
            int retorno;
            int numInner = Integer.parseInt(uiOffLine.jTxtNumInner.getText());

            HashMap<String, Object> InfoInner = offLineService.getInfoInner(
                    numInner, Integer.parseInt(uiOffLine.jTxtPorta.getText()),
                       uiOffLine.jCboTipoConexao.getSelectedIndex());
            if (InfoInner.get("ModeloBio") == "Modulo incorreto" || InfoInner.get("VersaoBio") == "Modulo incorreto"){
                uiOffLine.jTxaVersao.append("Modulo incorreto");
            }
            else{
                uiOffLine.jTxaVersao.append("Linha :" + InfoInner.get("LinhaInner") + "\r\n");
                uiOffLine.jTxaVersao.append("Versao :" + InfoInner.get("VersaoInner"));
            }
 
            
            //Se selecionado Biometria, valida se o equipamento é compatível
            //Configura o tipo de registro que será associado a uma marcação, quando for
            //inserido o dedo no Inner bio sem que o usuário tenha definido se é uma entrada,
            //saída, função...
            if (uiOffLine.jChkBio.isSelected()) {

                if (InfoInner.get("LinhaInner").equals("Inner Acesso")
                        || InfoInner.get("LinhaInner").equals("Inner Bio")) {

                    retorno = offLineService.configurarBio(numInner, (int)InfoInner.get("VersaoAlta"),
                                                            uiOffLine.jChkModuloLC.isSelected() ? 1 : 0,
                                                            (uiOffLine.jChkIdentificacao.isSelected() ? 1 : 0), 
                                                            (uiOffLine.jChkVerificacao.isSelected() ? 1 : 0));

                    if (retorno == RET_COMANDO_OK) {
                        uiOffLine.jLblEnvia.setText("configuração BIO enviada com sucesso!");
                    }
                } else {
                    uiOffLine.jLblEnvia.setText("Equipamento não compatível com biometria!");
                }
            } else {//Desativa
                easyInner.DefinirFuncaoDefaultSensorBiometria(0);
            }

            //Envia lista biometrica
            if (uiOffLine.jChkListaBio.isSelected()) {

                uiOffLine.jLblEnvia.setText("Enviando lista do InnerBio...");
                //Envia o buffer com a lista de usuarios sem digital
                retorno = offLineService.EnviarListaUsuariosSemDigitalBio(Integer.parseInt(uiOffLine.jTxtNumInner.getText()), (boolean)InfoInner.get("InnerAcesso"), Integer.parseInt(uiOffLine.jTxtDigitos.getText()));
                if (retorno == RET_COMANDO_OK) {
                    uiOffLine.jLblEnvia.setText("Lista do InnerBio enviada com sucesso!");
                } else {
                    uiOffLine.jLblEnvia.setText("Erro ao enviar a lista do InnerBio!");
                    uiOffLine.jBtnEnviar.setEnabled(true);
                    uiOffLine.jBtnReceber.setEnabled(true);
                }
            }

            //Envia configurações
            uiOffLine.jLblEnvia.setText("Enviando configurações...");

            montarConfiguracao();

            retorno = offLineService.enviarConfiguracao(numInner);

            if (retorno == RET_COMANDO_OK) {
                uiOffLine.jLblEnvia.setText("Configurações enviadas com sucesso!");
            } else {
                uiOffLine.jLblEnvia.setText("Erro ao enviar as configurações!");
                uiOffLine.jBtnEnviar.setEnabled(true);
                uiOffLine.jBtnReceber.setEnabled(true);
                return;
            }

            //Envia relógio
            //Configura o relógio(data/hora) do Inner.
            if (uiOffLine.jChkRelogio.isSelected()) {
                uiOffLine.jLblEnvia.setText("Enviando relógio...");

                retorno = offLineService.enviarRelogio(numInner);

                if (retorno == RET_COMANDO_OK) {
                    uiOffLine.jLblEnvia.setText("Relógio enviado com sucesso!");
                } else {
                    uiOffLine.jLblEnvia.setText("Erro ao enviar relógio!");
                    uiOffLine.jBtnEnviar.setEnabled(true);
                    uiOffLine.jBtnReceber.setEnabled(true);
                    return;
                }
            }

            //Envia o buffer com todas as mensagens off line configuradas anteriormente,
            //para o Inner.
            if (uiOffLine.jChkMensagem.isSelected()) {
                uiOffLine.jLblEnvia.setText("Enviando mensagem...");

                retorno = offLineService.enviarMensagensOffLine(numInner);

                if (retorno == RET_COMANDO_OK) {
                    uiOffLine.jLblEnvia.setText("Mensagem enviada com sucesso!");
                } else {
                    uiOffLine.jLblEnvia.setText("Erro ao enviar Mensagem!");
                    uiOffLine.jBtnEnviar.setEnabled(true);
                    uiOffLine.jBtnReceber.setEnabled(true);
                    return;
                }
            }

            //Envia o buffer com os horário de sirene cadastrados para o Inner.
            if (uiOffLine.jChkHorariosSirene.isSelected()) {
                uiOffLine.jLblEnvia.setText("Enviando horários sirene...");

                retorno = offLineService.enviarHorariosSirene(numInner);

                if (retorno == RET_COMANDO_OK) {
                    uiOffLine.jLblEnvia.setText("Horários da Sirene enviados com sucesso!");
                } else {
                    uiOffLine.jLblEnvia.setText("Erro ao enviar os horários da sirene!");
                    uiOffLine.jBtnEnviar.setEnabled(true);
                    uiOffLine.jBtnReceber.setEnabled(true);
                    return;
                }
            }

            //Envia para o Inner o buffer com a lista de horários de acesso, após executar
            //o comando o buffer é limpo tomaticamente pela dll
            if (uiOffLine.jChkHorarios.isSelected()) {
                uiOffLine.jLblEnvia.setText("Enviando horários...");

                retorno = offLineService.enviarHorariosAcesso(numInner);

                if (retorno == RET_COMANDO_OK) {
                    uiOffLine.jLblEnvia.setText("Horários enviados com sucesso!");
                } else {
                    uiOffLine.jLblEnvia.setText("Erro ao enviar os horários!");
                    uiOffLine.jBtnEnviar.setEnabled(true);
                    uiOffLine.jBtnReceber.setEnabled(true);
                    return;
                }
            }

            //Envia lista acesso off line
            if (uiOffLine.jChkLista.isSelected()) {
                uiOffLine.jLblEnvia.setText("Enviando lista...");

                retorno = offLineService.enviarListaAcesso(numInner, uiOffLine.jRdbPadraoLivre.isSelected(),
                        uiOffLine.jRdbPadraoTopdata.isSelected());
                if (retorno == RET_COMANDO_OK) {
                    uiOffLine.jLblEnvia.setText("Lista enviada com sucesso!");
                } else {
                    uiOffLine.jLblEnvia.setText("Erro ao enviar lista!");
                    uiOffLine.jBtnEnviar.setEnabled(true);
                    uiOffLine.jBtnReceber.setEnabled(true);
                    return;
                }
            }

            uiOffLine.jBtnEnviar.setEnabled(true);
            uiOffLine.jBtnReceber.setEnabled(true);

            easyInner.FecharPortaComunicacao();

        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(uiOffLine, ex);
        }

    }

    /**
     * MONTAR CONFIGURAÇÕES Esta rotina monta o buffer para enviar a
     * configuração do Inner
     */
    private void montarConfiguracao() {

        //Antes de realizar a configuração precisa definir o Padrão do cartão
        //Topdata ou padrão livre.
        if (uiOffLine.jRdbPadraoLivre.isSelected()) {
            easyInner.DefinirPadraoCartao(PADRAO_LIVRE);
        } else {

            easyInner.DefinirPadraoCartao(PADRAO_TOPDATA);
        }

        //Modo de comunicação
        //Configurações para Modo Offline.
        //Prepara o Inner para trabalhar no modo Off-Line, porém essa função ainda
        //não envia essa informação para o equipamento.
        easyInner.ConfigurarInnerOffLine();

        //Quantidade de digitos que o cartao usará
        easyInner.DefinirQuantidadeDigitosCartao(Integer.parseInt(uiOffLine.jTxtDigitos.getText())); //(qtde de digitos)

        //Verificar
        //Acionamentos 1 e 2
        //Configura como irá funcionar o acionamento(rele) 1 e 2 do Inner, e por
        //quanto tempo ele será acionado.
        switch (uiOffLine.jCboEquipamento.getSelectedIndex()) {
            //Coletor
            case Acionamento_Coletor:
                easyInner.ConfigurarAcionamento1(ACIONA_REGISTRO_ENTRADA, 5);
                easyInner.ConfigurarAcionamento2(ACIONA_REGISTRO_SAIDA, 5);
                
                if (uiOffLine.chkDoisLeitores.isSelected()) {
                    easyInner.ConfigurarLeitor1(SOMENTE_ENTRADA);
                    easyInner.ConfigurarLeitor2(SOMENTE_SAIDA);
                } 
                else 
                {
                    easyInner.ConfigurarLeitor1(ENTRADA_E_SAIDA);
                }
                break;

            //Catraca
            case Acionamento_Catraca_Entrada_E_Saida:
                easyInner.ConfigurarAcionamento1(ACIONA_REGISTRO_ENTRADA_OU_SAIDA, 7);
                easyInner.ConfigurarAcionamento2(NAO_UTILIZADO, 0);
                easyInner.ConfigurarLeitor1(ENTRADA_E_SAIDA);
                if (uiOffLine.chkDoisLeitores.isSelected()) 
                {
                     easyInner.ConfigurarLeitor1(ENTRADA_E_SAIDA);
                } 
                else 
                {
                    easyInner.ConfigurarLeitor2(DESATIVADO);
                }
                break;

                    
                case Enumeradores.Acionamento_Catraca_Entrada:
                if(!uiOffLine.optEsquerda.isSelected())
                {
                    easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA, 5); 
                    easyInner.ConfigurarLeitor1(SOMENTE_ENTRADA);
                } 
                else
                {
                    easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_SAIDA, 5);  
                    easyInner.ConfigurarLeitor1(SOMENTE_SAIDA);
                }
                easyInner.ConfigurarLeitor2(DESATIVADO);
                easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                break;

            case Enumeradores.Acionamento_Catraca_Saida:
                if(!uiOffLine.optEsquerda.isSelected())
                {
                    easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_SAIDA, 5);
                    easyInner.ConfigurarLeitor1(SOMENTE_SAIDA);
                } 
                else
                {
                    easyInner.ConfigurarAcionamento1(Enumeradores.ACIONA_REGISTRO_ENTRADA, 5);
                    easyInner.ConfigurarLeitor1(SOMENTE_ENTRADA);
                }
                easyInner.ConfigurarLeitor2(DESATIVADO);
                easyInner.ConfigurarAcionamento2(Enumeradores.NAO_UTILIZADO, 0);
                break; 
                    
            case Acionamento_Catraca_Urna:
                easyInner.ConfigurarAcionamento1(ACIONA_REGISTRO_ENTRADA_OU_SAIDA, 5);
                easyInner.ConfigurarAcionamento2(ACIONA_REGISTRO_SAIDA, 5);
                easyInner.ConfigurarLeitor1(SOMENTE_ENTRADA);
                easyInner.ConfigurarLeitor2(SOMENTE_SAIDA);
                break;

            case Acionamento_Catraca_Saida_Liberada:
                //Se Esquerda Selecionado - Inverte código
                if (uiOffLine.optEsquerda.isSelected()) 
                {
                    easyInner.ConfigurarAcionamento1(CATRACA_ENTRADA_LIBERADA, 5);
                    easyInner.ConfigurarLeitor1(SOMENTE_SAIDA);
                } 
                else 
                {
                    easyInner.ConfigurarAcionamento1(CATRACA_SAIDA_LIBERADA, 5);
                    easyInner.ConfigurarLeitor1(SOMENTE_ENTRADA);
                }
                easyInner.ConfigurarAcionamento2(CONECTADO_SIRENE, 5);
                break;

            case Acionamento_Catraca_Entrada_Liberada:
                //Se Esquerda Selecionado - Inverte código
                if (uiOffLine.optEsquerda.isSelected()) 
                {
                    easyInner.ConfigurarAcionamento1(CATRACA_SAIDA_LIBERADA, 5);
                    easyInner.ConfigurarLeitor1(SOMENTE_ENTRADA);
                } 
                else 
                {
                    easyInner.ConfigurarAcionamento1(CATRACA_ENTRADA_LIBERADA, 5);
                    easyInner.ConfigurarLeitor1(SOMENTE_SAIDA);
                }
                easyInner.ConfigurarAcionamento2(CONECTADO_SIRENE, 5);
                break;

            case Acionamento_Catraca_Liberada_2_Sentidos:
                easyInner.ConfigurarAcionamento1(CATRACA_LIBERADA_DOIS_SENTIDOS, 5);
                /*Pode ser nao utilizado tambem, caso não haja um equipamento conetado
                Sirene*/
                easyInner.ConfigurarAcionamento2(CONECTADO_SIRENE, 5);
                break;

            case Acionamento_Catraca_Sentido_Giro:
                easyInner.ConfigurarAcionamento1(CATRACA_LIBERADA_DOIS_SENTIDOS_MARCACAO_REGISTRO, 5);
                easyInner.ConfigurarAcionamento2(CONECTADO_SIRENE, 5);
                break;
        }

        //Configura o tipo do leitor que o Inner está utilizando, se é um leitor
        //de código de barras, magnético ou proximidade.
        switch (uiOffLine.jCboTipoLeitor.getSelectedIndex()) {
            case CODIGO_DE_BARRAS:
                easyInner.ConfigurarTipoLeitor(CODIGO_DE_BARRAS);
                break;
            case MAGNETICO:
                easyInner.ConfigurarTipoLeitor(MAGNETICO);
                break;
            case PROXIMIDADE_ABATRACK2:
                easyInner.ConfigurarTipoLeitor(PROXIMIDADE_ABATRACK2);
                break;
            case WIEGAND:
                easyInner.ConfigurarTipoLeitor(WIEGAND);
                break;
            case PROXIMIDADE_SMART_CARD:
                easyInner.ConfigurarTipoLeitor(PROXIMIDADE_SMART_CARD);
                break;
            case CODIGO_BARRAS_SERIAL:
                easyInner.ConfigurarTipoLeitor(CODIGO_BARRAS_SERIAL);
                break;
            case WIEGAND_FC_SEM_ZERO:
                easyInner.ConfigurarTipoLeitor(WIEGAND_FC_SEM_ZERO);
                break;
        }

        //Define a quantidade de dígitos dos cartões a serem lidos pelo Inner.
        easyInner.DefinirQuantidadeDigitosCartao(Integer.parseInt(uiOffLine.jTxtDigitos.getText()));

        if (uiOffLine.jChkCartaoMaster.isSelected()) {
            easyInner.DefinirNumeroCartaoMaster(uiOffLine.jTxtCartaoMaster.getText());
        }

        //Habilitar teclado
        //Permite que os dados sejam inseridos no Inner através do teclado do
        //equipamento. Habilitando o parâmetro ecoar, o teclado irá ecoar asteriscos
        //no display do Inner.
        easyInner.HabilitarTeclado((uiOffLine.jChkTeclado.isSelected() ? Opcao_SIM : Opcao_NAO), 0);

        if (uiOffLine.chkDoisLeitores.isSelected())
        {
              //Habilita os leitores wiegand para o primeiro leitor e o segundo leitor
              //do Inner, e configura se o segundo leitor irá exibir as mensagens
              //configuradas.
            easyInner.ConfigurarWiegandDoisLeitores(0, 1);
        }
        
        //ConfigurarLeitor: Configura as operações que o leitor irá executar. Se irá
        //registrar os dados somente como entrada independente do sentido em que o
        //cartão for passado, somente como saída ou como entrada e saída.
/*        if (uiOffLine.chkDoisLeitores.isSelected()) {
            //Configuração Catraca Esquerda ou Direita
            if (uiOffLine.optDireita.isSelected()) {
                //Direita Selecionado
                easyInner.ConfigurarLeitor1(LEITOR1_SOMENTE_ENTRADA);
                easyInner.ConfigurarLeitor2(LEITOR2_SOMENTE_SAIDA);
            } else {
                //Esquerda Selecionado
                easyInner.ConfigurarLeitor1(LEITOR1_SOMENTE_SAIDA);
                easyInner.ConfigurarLeitor2(LEITOR2_SOMENTE_ENTRADA);
            }

            //Habilita os leitores wiegand para o primeiro leitor e o segundo leitor
            //do Inner, e configura se o segundo leitor irá exibir as mensagens
            //configuradas.
            easyInner.ConfigurarWiegandDoisLeitores(DESABILITA, Opcao_SIM);
        } else {
            //Configuração Catraca Esquerda ou Direita
            if (uiOffLine.optDireita.isSelected()) {
                //Direita Selecionado
                easyInner.ConfigurarLeitor1(LEITOR1_ENTRADA_SAIDA);
            } else {
                //Esquerda Selecionado
                easyInner.ConfigurarLeitor1(LEITOR1_SAIDA_ENTRADA);
            }
            easyInner.ConfigurarLeitor2(LEITOR1_DESABILITADO);
        } */

        //Define qual tipo de lista(controle) de acesso o Inner vai utilizar
        if (uiOffLine.jChkLista.isSelected()) {
            easyInner.DefinirTipoListaAcesso(LISTA_BRANCA);
        } else {
            easyInner.DefinirTipoListaAcesso(SEM_LISTA);
        }

        //Configura o Inner para registrar as tentativas de acesso negado.
        easyInner.RegistrarAcessoNegado(Opcao_SIM);

        //Catraca
        //Define qual será o tipo do registro realizado pelo Inner ao aproximar um
        //cartão do tipo proximidade no leitor do Inner, sem que o usuário tenha
        //pressionado a tecla entrada, saída ou função.
        if ((uiOffLine.jCboEquipamento.getSelectedIndex() == Acionamento_Catraca_Entrada_E_Saida)
                || (uiOffLine.jCboEquipamento.getSelectedIndex() == Acionamento_Catraca_Liberada_2_Sentidos)
                || (uiOffLine.jCboEquipamento.getSelectedIndex() == Acionamento_Catraca_Sentido_Giro)) {
            easyInner.DefinirFuncaoDefaultLeitoresProximidade(REGISTRAR_CONFORME_GIRO); // 12 – Libera a catraca nos dois sentidos e registra o bilhete conforme o sentido giro.
        } else {
            if ((uiOffLine.jCboEquipamento.getSelectedIndex() == Acionamento_Catraca_Entrada)
                    || (uiOffLine.jCboEquipamento.getSelectedIndex() == Acionamento_Catraca_Saida_Liberada)) {
                if (uiOffLine.optDireita.isSelected()) {
                    easyInner.DefinirFuncaoDefaultLeitoresProximidade(REGISTRAR_SEMPRE_ENTRADA);  // 10 – Registrar sempre como entrada.
                } else {
                    easyInner.DefinirFuncaoDefaultLeitoresProximidade(REGISTRAR_SEMPRE_SAIDA);  // 11 – Registrar sempre como saída.
                }
            } else {
                if (uiOffLine.optDireita.isSelected()) {
                    easyInner.DefinirFuncaoDefaultLeitoresProximidade(REGISTRAR_SEMPRE_SAIDA);  // 11 – Registrar sempre como saída.
                } else {
                    easyInner.DefinirFuncaoDefaultLeitoresProximidade(REGISTRAR_SEMPRE_ENTRADA);  // 10 – Registrar sempre como entrada.
                }
            }
        }

        //Configura o tipo de registro que será associado a uma marcação, quando
        //for inserido o dedo no Inner bio sem que o usuário tenha definido se é um
        //entrada, saída, função, etc.
        easyInner.DefinirFuncaoDefaultSensorBiometria(uiOffLine.jChkBio.isSelected() ? REGISTRAR_SEMPRE_ENTRADA : FUNCAO_DEFAULT_DESABILITADA);
        //easyInner.ConfigurarBioVariavel(1);
    }

    /**
     * CONECTAR Rotina responsável por efetuar a conexão com o Inner
     *
     * @return
     */
    public int conectar() {

        int Ret = -1;

        try {
            easyInner.FecharPortaComunicacao();
            easyInner.DefinirTipoConexao(uiOffLine.jCboTipoConexao.getSelectedIndex());
            Ret = offLineService.conectar(Integer.parseInt(uiOffLine.jTxtNumInner.getText()),
                    Integer.parseInt(uiOffLine.jTxtPorta.getText()),
                    uiOffLine.jCboTipoConexao.getSelectedIndex());

            //Tenta Realizar a Conexão
            if (Ret == RET_COMANDO_OK) {

                //Caso o retorno seja OK.. volta a função chamadora..
                if (Ret == RET_COMANDO_OK) {
                    uiOffLine.jLblstatus.setText("Conectou ao Inner!");
                } else {
                    //Exibe mensagem de erro para o Usuário..
                    uiOffLine.jLblstatus.setText("Não conectou ao Inner!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Conexão falhou!");
                uiOffLine.jLblstatus.setText("Não conectou ao Inner!");
            }
        } catch (NumberFormatException | InterruptedException | HeadlessException ex) {
            System.out.println(ex.getMessage());
        }
        return Ret;
    }

    public void lerContadorGiros() throws InterruptedException {
        Integer numInner = Integer.parseInt(uiOffLine.jTxtNumInner.getText());
        HashMap<String, Object> InfoInner = offLineService.getInfoInner(
                numInner, Integer.parseInt(uiOffLine.jTxtPorta.getText()),
                uiOffLine.jCboTipoConexao.getSelectedIndex());

        uiOffLine.jTxaBilhetes.append(offLineService.lerContadorGiros(numInner).toString() + "\n\r");
    }

    public void zerarContadorGiros() throws InterruptedException {
        Integer numInner = Integer.parseInt(uiOffLine.jTxtNumInner.getText());
        HashMap<String, Object> InfoInner = offLineService.getInfoInner(
                numInner, Integer.parseInt(uiOffLine.jTxtPorta.getText()),
                uiOffLine.jCboTipoConexao.getSelectedIndex());
        offLineService.zerarContadorGiros(numInner);
    }
}
