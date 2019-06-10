/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.service;

import com.topdata.EasyInner;
import com.topdata.easyInner.dao.DAOConexao;
import com.topdata.easyInner.dao.DAOUsuariosBio;
import com.topdata.easyInner.entity.Inner;
import com.topdata.easyInner.entity.UsuarioBio;
import com.topdata.easyInner.enumeradores.Enumeradores;
import static com.topdata.easyInner.enumeradores.Enumeradores.RET_COMANDO_OK;
import com.topdata.easyInner.utils.EasyInnerUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jonatas.silva
 */
public class EasyInnerBioService6xx {
    private final EasyInner easyinner;
    private Inner InnerAtual;
    private DAOUsuariosBio AcessoBaseBio;
    
    public EasyInnerBioService6xx(EasyInner easyInner, Inner inner) {
        this.easyinner = easyInner;
        this.InnerAtual = inner;        
        AcessoBaseBio = new DAOUsuariosBio();
    }

    public boolean isConectado() throws InterruptedException {
        long IniConexao;
        long tempo;
        int Retorno;

        easyinner.FecharPortaComunicacao();
        easyinner.DefinirTipoConexao(2);

        //Abre a porta de Conexão conforme a Porta Indicada..
        Retorno = easyinner.AbrirPortaComunicacao(InnerAtual.Porta);

        //Tenta Realizar a Conexão
        if (Retorno == RET_COMANDO_OK) {
            int[] DataHora = new int[6];
            IniConexao = System.currentTimeMillis();
            //Realiza loop enquanto o tempo fim for menor que o tempo atual, e o comando retornado diferente de OK.
            do {
                tempo = System.currentTimeMillis() - IniConexao;
                //verifica se a conexão foi bem sucedida.
                Retorno = easyinner.ReceberRelogio(InnerAtual.Numero, DataHora);
                Thread.sleep(10l);
            } while (Retorno != RET_COMANDO_OK && tempo < 10000);
        }
        return Retorno == 0;
    }

    public String ReceberModeloBio() throws InterruptedException {
        int Ret = -1;
        if(isConectado())
        {
            String modelo = "";
            Ret = easyinner.RequisitarModeloBio(InnerAtual.Numero, InnerAtual.TipoComBio);
            if (Ret == (int)Enumeradores.RET_COMANDO_OK)
            {
                byte[] ModeloBio = new byte[4];
                Ret = easyinner.RespostaModeloBio(InnerAtual.Numero, ModeloBio);
                if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                {
                    modelo = EasyInnerUtils.ASCIIToDecimal(ModeloBio);
                }else if (Ret == Enumeradores.RET_BIO_MODULO_INCORRETO){
                    modelo = "250";
                }
            }
            easyinner.FecharPortaComunicacao();
            return modelo;
        }
        return null;
    }

    public String ReceberVersaoBio() throws InterruptedException {
        int Ret = -1;
        
        if (isConectado())
        {
            String versao = "";
            Ret = easyinner.RequisitarVersaoBio(InnerAtual.Numero, InnerAtual.TipoComBio);
            if (Ret == (int)Enumeradores.RET_COMANDO_OK)
            {
                byte[] VersaoBio = new byte[4];
                Ret = easyinner.RespostaVersaoBio(InnerAtual.Numero, VersaoBio);
                if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                {
                    versao = EasyInnerUtils.ASCIIToDecimal(VersaoBio);
                }else if (Ret == Enumeradores.RET_BIO_MODULO_INCORRETO){
                    versao = "250Erro";
                }
            }
            return versao;
        }
        easyinner.FecharPortaComunicacao();
        return null;
    }

    public boolean ConfigurarIdentificacaoVerificacao() throws InterruptedException {
        int Ret = -1;
        boolean configuracao = false;
        if (isConectado())
        {
            Ret = easyinner.RequisitarHabilitarIdentificacaoVerificacao(InnerAtual.Numero, InnerAtual.TipoComBio, InnerAtual.Identificacao, InnerAtual.Verificacao);
            if (Ret == Enumeradores.RET_COMANDO_OK)
            {
                Ret = easyinner.RespostaHabilitarIdentificacaoVerificacao(InnerAtual.Numero);
                if (Ret == Enumeradores.RET_COMANDO_OK)
                {
                    configuracao = true;
                }else{
                    configuracao = false;
                }
            }
        }
        easyinner.FecharPortaComunicacao();
        return configuracao;
    }

    public boolean ConfigurarAjustesBio(byte[] AjustesBio) throws InterruptedException {
        int Ret = -1;
        boolean ajustes = false;
        if (isConectado())
        {
            Ret = easyinner.RequisitarEnviarAjustesBio(InnerAtual.Numero, InnerAtual.TipoComBio, AjustesBio);
            if (Ret == (int)Enumeradores.RET_COMANDO_OK)
            {
                Ret = easyinner.RespostaEnviarAjustesBio(InnerAtual.Numero);
                if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                {
                    ajustes = true;
                }else{
                    ajustes = false;
                }
            }
        }
        easyinner.FecharPortaComunicacao();
        return ajustes;
    }

    public String ReceberQtdUsuariosBio() throws InterruptedException {
        int Ret = -1;
        String QtdUsuarios = "";
        if (isConectado())
        {
            Ret = easyinner.RequisitarQuantidadeUsuariosBio(InnerAtual.Numero, InnerAtual.TipoComBio);
            if (Ret == (byte)Enumeradores.RET_COMANDO_OK)
            {
                int[] Quantidade = new int[1];                
                Ret = easyinner.RespostaQuantidadeUsuariosBio(InnerAtual.Numero, Quantidade);

                if (Ret == (byte)Enumeradores.RET_COMANDO_OK)
                {
                   QtdUsuarios = Integer.toString(Quantidade[0]);
                }else if(Ret == Enumeradores.RET_BIO_MODULO_INCORRETO){
                    QtdUsuarios = "250 Erro";
                }
            }
        }
        easyinner.FecharPortaComunicacao();
        return QtdUsuarios;
    }

    public boolean ExcluirTodosUsuariosBio() throws InterruptedException {
        int Ret = -1;
        boolean excluir = false;
        if (isConectado())
        {
            Ret = easyinner.RequisitarExcluirTodosUsuariosBio(InnerAtual.Numero, InnerAtual.TipoComBio);
            if (Ret == (int)Enumeradores.RET_COMANDO_OK)
            {
                Ret = easyinner.RespostaExcluirTodosUsuariosBio(InnerAtual.Numero);
                if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                {
                    excluir = true;
                }
                else
                {
                    excluir = false;
                }
            }
        }
        easyinner.FecharPortaComunicacao();
        return excluir;
    }

    public int EnviarUsuariosBio(UsuarioBio userEnviar) throws InterruptedException {
        int Ret = -1;
        byte[] Digital1 = null;
        byte[] Digital2 = null;
        if (InnerAtual.TipoComBio == 0)
        {
            Digital1 = EasyInnerUtils.HexToByteArray(userEnviar.getTemplate1(), 404, 808);
            Digital2 = EasyInnerUtils.HexToByteArray(userEnviar.getTemplate2(), 404, 808);
        }
        else
        {
            Digital1 = EasyInnerUtils.HexToByteArray(userEnviar.getTemplate1(), 502, 1004);
            Digital2 = EasyInnerUtils.HexToByteArray(userEnviar.getTemplate2(), 502, 1004);

        }
        Ret = easyinner.EnviarDigitalUsuarioBio(InnerAtual.Numero, InnerAtual.TipoComBio, userEnviar.getCartao(),
                                                Digital1, InnerAtual.DuasDigitais ? Digital2 : null);
        if (Ret == (int)Enumeradores.RET_COMANDO_OK)
        {
            Ret = easyinner.RespostaEnviarDigitalUsuarioBio(InnerAtual.Numero);
        }
        return Ret;
    }

    public void FecharPortaComunicacao() {
        easyinner.FecharPortaComunicacao();
    }

    public List<UsuarioBio> ListarUsuariosBio() throws InterruptedException {
        int Ret = -1;
        int NumPacote = 0;
        int[] QtdPacotes = new int[1];
        int[] Tamanho = new int[1];
        List<UsuarioBio> ListaUsuariosBio = null;
        if (isConectado())
        {
            ListaUsuariosBio = new ArrayList();
            while(NumPacote <= QtdPacotes[0]){
                Ret = easyinner.RequisitarListarUsuariosBio(InnerAtual.Numero, InnerAtual.TipoComBio, NumPacote);
                if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                {
                    Ret = easyinner.RespostaListarUsuariosBio(InnerAtual.Numero, QtdPacotes,  Tamanho);
                    if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                    {
                        byte[] ListaRecebida = new byte[Tamanho[0]];
                        Ret = easyinner.ReceberListaPacUsuariosBio(InnerAtual.Numero, ListaRecebida,  Tamanho[0]);
                        if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                        {
                            for(int index = 4; index < ListaRecebida.length; index+=11)
                            {
                                byte[] user = new byte[8];
                                System.arraycopy((Object)ListaRecebida, index, user, 0, 8);
                                String usuario = EasyInnerUtils.BCDToDecimalSub(user, 0, 8);
                                UsuarioBio usuarioPronto = new UsuarioBio();
                                usuarioPronto.setCartao(usuario);
                                ListaUsuariosBio.add(usuarioPronto);
                            }
                        }
                    }else if (Ret == Enumeradores.RET_BIO_MODULO_INCORRETO){
                        ListaUsuariosBio = null;
                    }
                }
                NumPacote++;
            }
            easyinner.FecharPortaComunicacao();
        }
        return ListaUsuariosBio;
    }

    public List<UsuarioBio> ReceberDigitaisBio(List<String> usuariosSelecionados) throws InterruptedException, SQLException, IOException, FileNotFoundException, ClassNotFoundException {
        int Ret = -1;
        int[] TamanhoReceber = new int[1];
        List<UsuarioBio> ListaUsuariosBio = new ArrayList();
        if (isConectado())
        {
            for (int index = 0; index < usuariosSelecionados.size(); index++)
            {
                if (AcessoBaseBio.ExisteUsuarioBio(EasyInnerUtils.remZeroEsquerda(usuariosSelecionados.get(index)), InnerAtual.TipoComBio) == false)
                {
                    Ret = easyinner.RequisitarUsuarioCadastradoBio(InnerAtual.Numero, InnerAtual.TipoComBio, usuariosSelecionados.get(index));
                    if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                    {
                        Ret = easyinner.RespostaUsuarioCadastradoBio(InnerAtual.Numero, TamanhoReceber);
                        if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                        {
                            byte[] Buffertemplate = new byte[TamanhoReceber[0]];
                            Ret = easyinner.ReceberDigitalUsuarioBio(InnerAtual.Numero, Buffertemplate, TamanhoReceber[0]);
                            if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                            {
                                UsuarioBio usuarioBio = new UsuarioBio();
                                usuarioBio.setCartao(EasyInnerUtils.remZeroEsquerda(usuariosSelecionados.get(index)));

                                usuarioBio.setTemplate1(EasyInnerUtils.ArrayByteToHex(Buffertemplate, 68,
                                                                                InnerAtual.TipoComBio == 0 ? 404 : 502));
                                usuarioBio.setTemplate2(TamanhoReceber[0] == 472 || TamanhoReceber[0] == 570 ? usuarioBio.getTemplate1() :
                                                        EasyInnerUtils.ArrayByteToHex(Buffertemplate, InnerAtual.TipoComBio == 0 ? 472 : 570,
                                                            InnerAtual.TipoComBio == 0 ? 404 : 502));
                                ListaUsuariosBio.add(usuarioBio);
                            }
                        }else if (Ret == Enumeradores.RET_BIO_MODULO_INCORRETO){
                            ListaUsuariosBio = null;
                        }
                    }
                }
            }
            easyinner.FecharPortaComunicacao();
        }
        return ListaUsuariosBio;
    }

    public int ExcluirUsuariosInner(String usuario) {
        int Ret = -1; 
        Ret = easyinner.RequisitarExcluirUsuarioBio(InnerAtual.Numero, InnerAtual.TipoComBio, usuario);
        if (Ret == (int)Enumeradores.RET_COMANDO_OK)
        {
            Ret = easyinner.RespostaExcluirUsuarioBio(InnerAtual.Numero);
        }
        return Ret;
    }

    public String IdentificarDigital() throws InterruptedException {
        int Ret = -1;
        String Resposta = "";
        if (isConectado())
        {
            Ret = easyinner.RequisitarIdentificarUsuarioLeitorBio(InnerAtual.Numero, InnerAtual.TipoComBio);
            if (Ret == (int)Enumeradores.RET_COMANDO_OK)
            {
                Thread.sleep(1000);
                byte[] Usuario = new byte[8];
                Ret = easyinner.RespostaIdentificarUsuarioLeitorBio(InnerAtual.Numero, Usuario);
                Resposta = Ret != 8 ? "Resposta bio " + Ret: "Erro";
                if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                {
                    Resposta = "Usuário: " + EasyInnerUtils.BCDToDecimalSub(Usuario, 0, Usuario.length) + " identificado";
                }
            }
        }
        easyinner.FecharPortaComunicacao();
        return Resposta;
    }

    public byte[] ReceberTemplateLeitor() throws InterruptedException {
        int Ret = -1;
        byte[] BufferTemplate = null;
        if (isConectado())
        {
            Ret = easyinner.RequisitarReceberTemplateLeitorInnerBio(InnerAtual.Numero, InnerAtual.TipoComBio);
            if (Ret == (int)Enumeradores.RET_COMANDO_OK)
            {
                int[] TamanhoReceber = new int[1];
                Ret = easyinner.RespostaReceberTemplateLeitorInnerBio(InnerAtual.Numero, TamanhoReceber);
                if (Ret == (int)Enumeradores.RET_COMANDO_OK)
                {
                    BufferTemplate = new byte[TamanhoReceber[0]];
                    easyinner.ReceberTemplateLeitorInnerBio(InnerAtual.Numero, BufferTemplate, TamanhoReceber[0]);
                }else if(Ret == Enumeradores.RET_BIO_MODULO_INCORRETO){
                    BufferTemplate = new byte[1];
                    BufferTemplate[0] = (byte)250;
                }
            }
            easyinner.FecharPortaComunicacao();
        }
        return BufferTemplate;
    }

    public int VerificarCadastro() throws InterruptedException {
        int Ret = -1;
        if (isConectado())
        {
            Ret = easyinner.RequisitarVerificarCadastroUsuarioBio(InnerAtual.Numero, InnerAtual.TipoComBio, InnerAtual.CartaoTemplateLeitor);
            if (Ret == (int)Enumeradores.RET_COMANDO_OK)
            {
                Ret = easyinner.RespostaVerificarCadastroUsuarioBio(InnerAtual.Numero);
            }
        }
        easyinner.FecharPortaComunicacao();
        return Ret;
    }

    public int VerificarDigital() throws InterruptedException {
        int Ret = -1;
        if (isConectado())
        {
            Ret = easyinner.RequisitarVerificarDigitalBio(InnerAtual.Numero, InnerAtual.TipoComBio, InnerAtual.CartaoTemplateLeitor);
            if (Ret == (int)Enumeradores.RET_COMANDO_OK)
            {
                Ret = easyinner.RespostaVerificarDigitalBio(InnerAtual.Numero);
            }
        }
        easyinner.FecharPortaComunicacao();
        return Ret;
    }
}
