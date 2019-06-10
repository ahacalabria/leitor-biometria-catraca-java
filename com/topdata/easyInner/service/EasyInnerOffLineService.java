//******************************************************************************
//A Topdata Sistemas de Automação Ltda não se responsabiliza por qualquer
//tipo de dano que este software possa causar, este exemplo deve ser utilizado
//apenas para demonstrar a comunicação com os equipamentos da linha Inner.
//
//Exemplo Biometria
//Desenvolvido em Java.
//                                           Topdata Sistemas de Automação Ltda.
//******************************************************************************
package com.topdata.easyInner.service;

import com.topdata.EasyInner;
import com.topdata.easyInner.dao.DAOUsuariosBio;
import com.topdata.easyInner.entity.Horarios;
import com.topdata.easyInner.entity.UsuarioSemDigital;
import com.topdata.easyInner.enumeradores.Enumeradores;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import static com.topdata.easyInner.enumeradores.Enumeradores.*;
import com.topdata.easyInner.ui.JIFEasyInnerOffLine;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author juliano.ezequiel
 */
public class EasyInnerOffLineService {

    private final JIFEasyInnerOffLine uiOffLine;
    private DAOUsuariosBio UsersBio;
    private final EasyInner easyInner;

    public EasyInnerOffLineService(EasyInner easyInner,JIFEasyInnerOffLine easyInnerOffLine) 
    {
        this.easyInner = easyInner;
        UsersBio = new DAOUsuariosBio();
        uiOffLine = easyInnerOffLine;
    }

    /**
     * MONTAR HORARIO SIRENE Esta rotina monta os horários de toque da sirene e
     * quais dias da semana irão tocar
     *
     * @throws Exception
     */
    public void montarHorariosSirene() throws Exception {
        byte Hora, Minuto, Seg, Ter, Qua, Qui, Sex, Sab, Dom = 0;
        
        Hora = 1;
        Minuto = 0;
        Seg = 1;
        Ter = 1;
        Qua = 1;
        Qui = 1;
        Sex = 1;
        Sab = 1;

            easyInner.InserirHorarioSirene(Hora, Minuto, Seg, Ter, Qua, Qui, Sex, Sab, Dom);
    }

    /**
     * MONTAR MENSAGEM Esta rotina é reponsavel por montar o buffer para o envio
     * de mensagens
     */
    public void montarMensagem() {
        if(!uiOffLine.optEsquerda.isSelected())
        {
                //Mensagem Entrada e Saida Offline Liberado!
            easyInner.DefinirMensagemEntradaOffLine(1, "ENTRADA LIBERADA.");
            easyInner.DefinirMensagemSaidaOffLine(1, "SAIDA LIBERADA.");            
        }
        else
        {
            //Mensagem Entrada e Saida Offline Liberado!
            easyInner.DefinirMensagemEntradaOffLine(1, "SAIDA LIBERADA.");
            easyInner.DefinirMensagemSaidaOffLine(1, "ENTRADA LIBERADA.");
        }
        easyInner.DefinirMensagemPadraoOffLine(1,"    OFF LINE    ");
    }

    /**
     * MONTAR LISTA TOPDATA Monta o buffer para enviar a lista nos inners da
     * linha inner, cartão padrão Topdata
     *
     * @param numInner
     */
    public void montarListaTopdata() {

        //Define qual padrao o Inner vai usar
        easyInner.DefinirPadraoCartao(0);

        //Quantidade de digitos que o cartao usará
        easyInner.DefinirQuantidadeDigitosCartao(14);

        for (int i = 0; i < 5; i++) {
            //Insere usuário da lista no buffer da DLL
            easyInner.InserirUsuarioListaAcesso(Integer.toString(i), 101);
        }

    }

    /**
     * MONTAR LISTA LIVRE Monta o buffer para enviar a lista nos inners da linha
     * inner, cartão padrão livre 14 dígitos
     *
     * @throws Exception
     */
    public void montarListaLivre() throws Exception {

        //Define qual padrão o Inner vai usar
        easyInner.DefinirPadraoCartao(1); //(1 - Padrao Livre(Default))

        easyInner.InserirUsuarioListaAcesso("1", 1);
        easyInner.InserirUsuarioListaAcesso("187", 101);
        easyInner.InserirUsuarioListaAcesso("123456", 101);
        easyInner.InserirUsuarioListaAcesso("27105070", 101);
        easyInner.InserirUsuarioListaAcesso("103086639459", 101);
        easyInner.InserirUsuarioListaAcesso("103086639460", 101);
        easyInner.InserirUsuarioListaAcesso("103086639461", 101);
        easyInner.InserirUsuarioListaAcesso("103086639462", 101);
        easyInner.InserirUsuarioListaAcesso("103086639463", 101);
        easyInner.InserirUsuarioListaAcesso("103086639464", 101);
        easyInner.InserirUsuarioListaAcesso("103086639465", 101);
        easyInner.InserirUsuarioListaAcesso("103086639466", 101);

    }

    /**
     * MONTAR HORARIOS Monta o buffer para enviar os horários de acesso Tabela
     * de horarios numero 1
     *
     * @throws Exception
     */
    public void montarHorarios() throws Exception {

        //Apaga o buffer com a lista de horários de acesso e envia automaticamente
        //para o Inner.
        //Insere no buffer da DLL horario de acesso    
            
        List<Horarios> ListaHorarios = Horarios.MontarListaHorarios();

        for (int index = 0; index < ListaHorarios.size(); index++)
        {
            easyInner.InserirHorarioAcesso(ListaHorarios.get(index).horario, ListaHorarios.get(index).dia, 
                                            ListaHorarios.get(index).faixa, ListaHorarios.get(index).hora, 
                                            ListaHorarios.get(index).minuto);
        }
    }

    /**
     * APENAS PARA O INNER BIO Monta o buffer da lista de cartões dos usuários
     * sem digital no Inner bio
     *
     * @param InnerAcesso
     * @throws Exception
     */
    public void montarBufferListaSemDigital(boolean InnerAcesso) throws Exception {
        List<UsuarioSemDigital> Usuarios = UsersBio.ConsultarUsuarioSemDigital();
        for (UsuarioSemDigital s : Usuarios) {
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

    /**
     * Realiza a conexão com o Inner e recupera todos os dados do equipamento.
     *
     * @param numInner
     * @param porta
     * @param TipoCon
     * @return HachMap contendo as informações do Inner
     * @throws InterruptedException
     */
    public HashMap<String, Object> getInfoInner(int numInner, int porta, int TipoCon) throws InterruptedException {
        int VersaoFW[] = new int[16];
        long Variacao = 0;
        int Ret = -1;
        HashMap<String, Object> InfoInner = new HashMap<>();
        int tentativas = 0;
        //Solicita a versão do firmware do Inner e dados como o Idioma, se é
        //uma versão especial.

        if (conectar(numInner, porta, TipoCon) == RET_COMANDO_OK) {
            do {
                Ret = easyInner.ReceberVersaoFirmware6xx(numInner, VersaoFW);

                Thread.sleep(40);
            } while (Ret != RET_COMANDO_OK && tentativas++ < 30);

            if (Ret == RET_COMANDO_OK) {
                //Define a linha do Inner
                switch (VersaoFW[0]) {
                    case 1:
                        InfoInner.put("LinhaInner", "Inner Plus");
                        InfoInner.put("InnerAcesso", false);
                        break;

                    case 2:
                        InfoInner.put("LinhaInner", "Inner Disk");
                        InfoInner.put("InnerAcesso", false);
                        break;

                    case 3:
                        InfoInner.put("LinhaInner", "Inner Verid");
                        InfoInner.put("InnerAcesso", false);
                        break;

                    case 6:
                        InfoInner.put("LinhaInner", "Inner Bio");
                        InfoInner.put("InnerAcesso", false);
                        break;

                    case 7:
                        InfoInner.put("LinhaInner", "Inner NET");
                        InfoInner.put("InnerAcesso", false);
                        break;

                    case 14:
                        InfoInner.put("LinhaInner", "Inner Acesso");
                        InfoInner.put("InnerAcesso", true);
                        break;
                }
                InfoInner.put("InnerAcessoBite", VersaoFW[6]);
                InfoInner.put("VariacaoInner", Variacao);
                InfoInner.put("VersaoAlta", VersaoFW[3]);
                InfoInner.put("VersaoInner", Integer.toString(VersaoFW[3])
                        + '.' + Integer.toString(VersaoFW[4])
                        + '.' + Integer.toString(VersaoFW[5]));
                InfoInner.put("ModuloBio", VersaoFW[7]);
                //Se for biometria
                if ((VersaoFW[0] == 6) || (VersaoFW[0] == 14 && Boolean.parseBoolean(InfoInner.get("InnerAcesso").toString()) == true)) {
                    
                    if (Integer.parseInt(InfoInner.get("VersaoAlta").toString()) < 6)
                    {
                        InfoInner.put("ModeloBio", BioService.ReceberModeloBioAVer5xx(numInner));
                        InfoInner.put("VersaoBio", BioService.ReceberVersaoBioAVer5xx(numInner));
                    }else{
                        InfoInner.put("ModeloBio", BioService.ReceberModeloBio6xx(numInner, Integer.parseInt(InfoInner.get("ModuloBio").toString())));
                        InfoInner.put("VersaoBio", BioService.ReceberVersaoBio6xx(numInner, Integer.parseInt(InfoInner.get("ModuloBio").toString())));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao solicitar Versão Inner");
                return null;
            }
        }
        return InfoInner;
    }

    /**
     * Realiza a conexao com o Inner.
     *
     * @param numInner
     * @param porta
     * @param TipoCon
     * @return
     * @throws InterruptedException
     */
    public int conectar(int numInner, int porta, int TipoCon) throws InterruptedException {

        long IniConexao;
        long tempo;
        int Retorno;

        easyInner.FecharPortaComunicacao();
        easyInner.DefinirTipoConexao(TipoCon);

        //Abre a porta de Conexão conforme a Porta Indicada..
        Retorno = easyInner.AbrirPortaComunicacao(porta);

        //Tenta Realizar a Conexão
        if (Retorno == RET_COMANDO_OK) {
            IniConexao = System.currentTimeMillis();
            //Realiza loop enquanto o tempo fim for menor que o tempo atual, e o comando retornado diferente de OK.
            do {
                tempo = System.currentTimeMillis() - IniConexao;
                //Tenta abrir a conexão com inner utilizando Ping.
                Retorno = testarConexaoInner(numInner);

                Thread.sleep(10l);

            } while (Retorno != RET_COMANDO_OK && tempo < 10000);
        }
        return Retorno;

    }

    /**
     * Metodo responsável por realizar um comando simples com o equipamento para
     * detectar se esta conectado.
     *
     * @param Inner
     * @return
     */
    private int testarConexaoInner(int Inner) {
        int[] DataHora = new int[6];
        return easyInner.ReceberRelogio(Inner, DataHora);

    }

    /**
     * COLETAR BILHETES Esta rotina efetua a coleta de bilhetes que foram
     * registrados em off-line
     *
     * @param numInner
     * @param TempoColeta
     * @return
     * @throws InterruptedException
     */
    public List<StringBuffer> coletarBilhetesInnerNet(int numInner) throws InterruptedException {
        int[] Bilhete = new int[8];
        StringBuffer Cartao;
        int nBilhetes = 0;
        int Ret = -1;
        List<StringBuffer> BilhetesColetados = new ArrayList<>();
        StringBuffer BilheteColetado = new StringBuffer();
        Long tempoColeta = System.currentTimeMillis() + 15000;

        do {
            //Contador tempo de coleta 15 segundos
            Thread.sleep(100);
            Cartao = new StringBuffer();
            //Coleta um bilhete Off-Line que está armazenado na memória do Inner
            //       Ret = easyInner.ColetarBilhete(numInner, Bilhete, Cartao);
            if (Ret == RET_COMANDO_OK) {

                //Armazena os dados do bilhete no list, pode ser utilizado com
                //banco de dados ou outro meio de armazenamento compatível
                BilheteColetado.append("Tipo:").append(String.valueOf(Bilhete[0]))
                        .append(" Cartão:")
                        .append(Cartao.toString())
                        .append(" Data:")
                        .append(String.valueOf(Bilhete[1]).length() == 1
                                        ? "0" + String.valueOf(Bilhete[1])
                                        : String.valueOf(Bilhete[1]))
                        .append("/")
                        .append(String.valueOf(Bilhete[2]).length() == 1
                                        ? "0" + String.valueOf(Bilhete[2])
                                        : String.valueOf(Bilhete[2]))
                        .append("/")
                        .append(String.valueOf(Bilhete[3]))
                        .append(" Hora:")
                        .append(String.valueOf(Bilhete[4]).length() == 1
                                        ? "0" + String.valueOf(Bilhete[4])
                                        : String.valueOf(Bilhete[4]))
                        .append(":")
                        .append(String.valueOf(Bilhete[5]).length() == 1
                                        ? "0" + String.valueOf(Bilhete[5])
                                        : String.valueOf(Bilhete[5]))
                        .append(":")
                        .append(String.valueOf(Bilhete[6]).length() == 1
                                        ? "0" + String.valueOf(Bilhete[6])
                                        : String.valueOf(Bilhete[6])).append("\n");
                BilhetesColetados.add(BilheteColetado);
                tempoColeta = System.currentTimeMillis() + 15000;
                nBilhetes++;
            }

        } while ((System.currentTimeMillis() <= tempoColeta) || (Ret == RET_COMANDO_OK));

        //Fecha porta comunicação
        easyInner.FecharPortaComunicacao();

        return BilhetesColetados;
    }

    /**
     * COLETAR BILHETES Inner Acesso Esta rotina efetua a coleta de bilhetes que
     * foram registrados em off-line
     *
     * @param numInner
     * @return
     * @throws InterruptedException
     */
    public List<StringBuffer> coletarBilhetesInnerAcesso(int numInner) throws InterruptedException {
        int[] Bilhete = new int[8];

        int Ret = -1;
        int QtdeBilhetes;
        int receber[] = new int[2];
        int nBilhetes = 0;
        List<StringBuffer> BilhetesColetados = new ArrayList<>();
        StringBuffer BilheteColetado = new StringBuffer();
        QtdeBilhetes = 0;
        easyInner.ReceberQuantidadeBilhetes(numInner, receber);
        QtdeBilhetes = receber[0];

        do {
            if (QtdeBilhetes > 0) {
                do {
                    //Thread.sleep(100l);

                    StringBuffer Cartao = new StringBuffer();
                    //Coleta um bilhete Off-Line que está armazenado na memória do Inner
                    Ret = easyInner.ColetarBilhete(numInner, Bilhete, Cartao);
                    if (Ret == RET_COMANDO_OK) {

                        //Armazena os dados do bilhete no list, pode ser utilizado com
                        //banco de dados ou outro meio de armazenamento compatível
                        BilheteColetado.append("Tipo:").append(String.valueOf(Bilhete[0]))
                                .append(" Cartão:")
                                .append(Cartao.toString())
                                .append(" Data:")
                                .append(String.valueOf(Bilhete[1]).length() == 1
                                                ? "0" + String.valueOf(Bilhete[1])
                                                : String.valueOf(Bilhete[1]))
                                .append("/")
                                .append(String.valueOf(Bilhete[2]).length() == 1
                                                ? "0" + String.valueOf(Bilhete[2])
                                                : String.valueOf(Bilhete[2]))
                                .append("/")
                                .append(String.valueOf(Bilhete[3]))
                                .append(" Hora:")
                                .append(String.valueOf(Bilhete[4]).length() == 1
                                                ? "0" + String.valueOf(Bilhete[4])
                                                : String.valueOf(Bilhete[4]))
                                .append(":")
                                .append(String.valueOf(Bilhete[5]).length() == 1
                                                ? "0" + String.valueOf(Bilhete[5])
                                                : String.valueOf(Bilhete[5]))
                                .append(":")
                                .append(String.valueOf(Bilhete[6]).length() == 1
                                                ? "0" + String.valueOf(Bilhete[6])
                                                : String.valueOf(Bilhete[6])).append("\n");

                        nBilhetes++;
                        QtdeBilhetes--;
                        BilhetesColetados.add(BilheteColetado);
                    }

                } while (QtdeBilhetes > 0);

                easyInner.ReceberQuantidadeBilhetes(numInner, receber);
                QtdeBilhetes = receber[0];
            }
        } while (QtdeBilhetes > 0);
        easyInner.FecharPortaComunicacao();
        return BilhetesColetados;
    }

    /**
     * Envia para o Inner as configurações
     *
     * @param numInner
     * @return
     */
    public int enviarConfiguracao(int numInner) {
        return easyInner.EnviarConfiguracoes(numInner);
    }

    /**
     * Envia a lista de usuário sem digital que não serão solicitado a digital
     * quando o Inner estiver com a verificação ativada
     *
     * @param numInner
     * @param InnerAcesso
     * @param QtdDigitos
     * @return
     * @throws InterruptedException
     * @throws Exception
     */
    public int EnviarListaUsuariosSemDigitalBio(int numInner, boolean InnerAcesso, int QtdDigitos) throws InterruptedException, Exception {
        //Chama rotina que monta o buffer de cartoes que nao irao precisar da digital
        montarBufferListaSemDigital(InnerAcesso);
        Thread.sleep(100);
        if (InnerAcesso)
        {
            return easyInner.EnviarListaUsuariosSemDigitalBioVariavel(numInner, QtdDigitos);
        }
        else
        {
            return easyInner.EnviarListaUsuariosSemDigitalBio(numInner);
        }
    }

    /**
     * Realiza a configuração biometrica do Inner off-line
     *
     * @param numInner
     * @param VersaoSup
     * @param ModuloBio
     * @param identificacao
     * @param verificacao
     * @return
     */
    public int configurarBio(int numInner, int VersaoSup, int ModuloBio, int identificacao, int verificacao) {
        int Ret = -1;
        easyInner.DefinirFuncaoDefaultSensorBiometria(10);
        if (VersaoSup < 6){
            easyInner.ConfigurarBio(numInner, identificacao, verificacao);
            Ret = easyInner.ResultadoConfiguracaoBio(numInner, 0);
        }else{
            Ret = easyInner.RequisitarHabilitarIdentificacaoVerificacao(numInner, ModuloBio, identificacao, verificacao);
            if (Ret == Enumeradores.RET_COMANDO_OK)
            {
                Ret = easyInner.RespostaHabilitarIdentificacaoVerificacao(numInner);
            }
        }
        return Ret;
    }

    /**
     * Envia para o Inner a data e hora atual
     *
     * @param numInner
     * @return
     */
    public int enviarRelogio(int numInner) {
        Date Data = new Date();
        //Formato o ano, pega apenas os dois ultimos digitos
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

        //Envia relogio
        return easyInner.EnviarRelogio(numInner, Dia, Mes, Ano, Hora, Minuto, Segundo);
    }

    /**
     * Envia para o Inner as menssagens off-line
     *
     * @param numInner
     * @return
     * @throws InterruptedException
     */
    public int enviarMensagensOffLine(int numInner) throws InterruptedException {

        //Chama rotina de envio de mensagem
        montarMensagem();
        Thread.sleep(100);
        //Envia Buffer com todas as mensagens offline
        return easyInner.EnviarMensagensOffLine(numInner);
    }

    /**
     * Envia para o Inner a lista da serene
     *
     * @param numInner
     * @return
     * @throws InterruptedException
     * @throws Exception
     */
    public int enviarHorariosSirene(int numInner) throws InterruptedException, Exception {
        //Chama rotina que monta os horarios
        montarHorariosSirene();
        Thread.sleep(100);

        return easyInner.EnviarHorariosSirene(numInner);
    }

    /**
     * Envia para o Inner os horarios de acesso
     *
     * @param numInner
     * @return
     * @throws InterruptedException
     * @throws Exception
     */
    public int enviarHorariosAcesso(int numInner) throws InterruptedException, Exception {
        //chama a rotina que monta horarios
        montarHorarios();
        Thread.sleep(100);

        //Envia buffer com lista de horarios de acesso
        return easyInner.EnviarHorariosAcesso(numInner);
    }

    /**
     * Envia lista de acesso para o Inner
     *
     * @param numInner
     * @param livre
     * @param topdata
     * @return
     * @throws Exception
     */
    public int enviarListaAcesso(int numInner, boolean livre, boolean topdata) throws Exception {

        if (topdata) {
            //Chama rotina que monta lista do tipo TOPDATA
            montarListaTopdata();
        } else {  //Chama rotina que monta lista do tipo LIVRE
            montarListaLivre();
        }

        Thread.sleep(100l);

        return easyInner.EnviarListaAcesso(numInner);
    }

    /**
     *
     * @param numInner
     * @return
     */
    public StringBuffer lerContadorGiros(int numInner) {
        byte[] entradas = new byte[4];
        byte[] saidas = new byte[4];
        byte[] desistencias = new byte[4];

        int ret = easyInner.LerContadorGiro(numInner, entradas, saidas, desistencias);
        if (ret != 0) {
            JOptionPane.showMessageDialog(null, ret);
        }

        StringBuffer total = new StringBuffer();
        String totalEntradas = "";
        String totalSaidas = "";
        String totalDesistencias = "";

        for (int i = 0; i < 4; i++) {
            totalEntradas += entradas[i];
            totalSaidas += saidas[i];
            totalDesistencias += desistencias[i];
        }

        total.append("Entradas : ").append(totalEntradas).append(" Saidas : ").append(totalSaidas).append(" Desistencias : ").append(totalDesistencias);

        return total;
    }

    public void zerarContadorGiros(int numInner) {
        byte[] entradas = {0, 0, 0, 0};
        byte[] saidas = {0, 0, 0, 0};
        byte[] desistencias = {0, 0, 0, 0};

        int ret = easyInner.AtribuirContadorGiro(numInner, entradas, saidas, desistencias);
        if (ret == 0) {
            System.out.println(ret);
        }
    }

    /**
     *
     * @param numInner
     */
    public void getSmartCard(int numInner) {
        byte[] cartao = new byte[8];
        byte[] bloco_0 = new byte[8];
        byte[] bloco_1 = new byte[8];
        byte[] bloco_2 = new byte[8];

        easyInner.LerSmartCard(numInner, 1, cartao, bloco_0, bloco_1, bloco_2);

        System.out.println(cartao);
    }

}
