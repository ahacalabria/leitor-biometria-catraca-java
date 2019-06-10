/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.controller;

import com.topdata.EasyInner;
import com.topdata.easyInner.dao.DAOUsuariosBio;
import com.topdata.easyInner.entity.Inner;
import com.topdata.easyInner.entity.UsuarioBio;
import com.topdata.easyInner.enumeradores.Enumeradores;
import com.topdata.easyInner.service.EasyInnerBioService6xx;
import com.topdata.easyInner.ui.JIFEasyInnerBio6xx;
import com.topdata.easyInner.utils.EasyInnerUtils;
import java.awt.Cursor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jonatas.silva
 */
public class EasyInnerBioController6xx {
    private final JIFEasyInnerBio6xx uiBio;
    private final EasyInner easyInner;
    private final DAOUsuariosBio UsuariosBio;
    private EasyInnerBioService6xx bioService6xx;
    private Inner InnerAtual;
    private List<UsuarioBio> ListaUsuariosBio;

    public EasyInnerBioController6xx(JIFEasyInnerBio6xx innerBio) {
        this.uiBio = innerBio;
        easyInner = new EasyInner();
        UsuariosBio = new DAOUsuariosBio();
    }


    public void CarregarTemplatesBase() {
//        try {
//            DefaultTableModel Templates = (DefaultTableModel) uiBio.jTblUsuariosBase.getModel();
//            
//            Templates.setNumRows(0);
//
//            //templates
//            List<UsuarioBio> Templs = UsuariosBio.ConsultarUsuariosBio(uiBio.jCboTipoModuloBio.getSelectedIndex());
//            for (UsuarioBio Templ : Templs) {
//                String[] tpl = new String[3];
//                tpl[0] = Templ.getCartao();
//                tpl[1] = Templ.getTemplate1();
//                tpl[2] = Templ.getTemplate2();
//                Templates.addRow(tpl);
//            }
//            
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
    }
    
    public void ReceberTemplateLeitor() throws SQLException, InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.jTxaCadastroInner.setText("Receber template leitor inner");
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        byte[] TemplateRecebido = bioService6xx.ReceberTemplateLeitor();
        if (TemplateRecebido[0] == 250){
            uiBio.jTxaManutencao.setText("Modulo incorreto");
        }else{
            int resposta = JOptionPane.showConfirmDialog(null, "Template do usuário " + InnerAtual.CartaoTemplateLeitor + " recebido "
                                                    + "\nDeseja gravar este usuário na base?", "Receber Template leitor", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION)
            {
                ListaUsuariosBio = new ArrayList();
                UsuarioBio usuarioBio = new UsuarioBio();
                usuarioBio.setCartao(InnerAtual.CartaoTemplateLeitor);
                usuarioBio.setTemplate1(EasyInnerUtils.ArrayByteToHex(TemplateRecebido, 0, TemplateRecebido.length));
                usuarioBio.setTemplate2(usuarioBio.getTemplate1());
                ListaUsuariosBio.add(usuarioBio);
                GravarUsuariosBaseDados();
            }
        }
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    public void ReceberModeloBio() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.LblStatusConf.setText("Receber modelo bio");
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String modelo = bioService6xx.ReceberModeloBio();
        uiBio.LblStatusConf.setText("250".equals("Modelo bio " + modelo) ? "Erro modulo incoreto" : modelo);
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void ReceberVersaoBio() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.LblStatusConf.setText("Receber versão bio");
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        uiBio.LblStatusConf.setText("Versão bio " + bioService6xx.ReceberVersaoBio());
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void EnviarAjustesBio() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.LblStatusConf.setText("Enviar ajustes biométricos");
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (bioService6xx.ConfigurarAjustesBio(getAjustesBiometricos(InnerAtual.TipoComBio))){
            uiBio.LblStatusConf.setText("Ajustes biométricos enviados");
        }
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void ReceberQtdUsuariosBio() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        uiBio.jTxaManutencao.setText("Recebendo quantidade de usuários cadastrados");
        String qtd = bioService6xx.ReceberQtdUsuariosBio(); 
        uiBio.jTxaManutencao.append("250 Erro".equals("\nQuantidade " + qtd) ? "erro modulo incorreto" : qtd);
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void EnviarDigitaisInner(List<UsuarioBio> ListaUsuariosEnviar) throws InterruptedException {
        getPropriedadesInner();
        int[] Respostas = new int[4];
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        uiBio.jTxaManutencao.setText("Enviar usuários selecionados");
        if (bioService6xx.isConectado()){
            OUTER:
            for (int index = 0; index < ListaUsuariosEnviar.size(); index++) {
                AtualizarRespostas(bioService6xx.EnviarUsuariosBio(ListaUsuariosEnviar.get(index)),
                        Respostas);
                switch (Respostas[3]) {
                    case 1:
                        uiBio.jTxaManutencao.setText("Base cheia. \nEnviados " + Respostas[0]
                                + "\nJa cadastrados " + Respostas[1]
                                + "\nFalha " + Respostas[2]);
                        break OUTER;
                    case 250:
                        uiBio.jTxaManutencao.setText("Modulo incorreto");
                        break;
                    default:
                        uiBio.jTxaManutencao.setText("Enviados " + Respostas[0]
                                + "\nJa cadastrados " + Respostas[1]
                                + "\nFalha " + Respostas[2]);
                        break;
                }
            }
            bioService6xx.FecharPortaComunicacao();
        }
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void ExcluirUsuarioBD() {
        if (uiBio.jTblUsuariosBase.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione um cartão para excluir da base.");
            return;
        }

        try {
            //Usuário encontrado
            int[] selecionados = uiBio.jTblUsuariosBase.getSelectedRows();
            for(int index = 0; index < selecionados.length; index++){
                String UsuarioExc = uiBio.jTblUsuariosBase.getValueAt(selecionados[index], 0).toString();
                uiBio.jTxaManutencao.setText("Excluir " + UsuarioExc);
                UsuariosBio.ExcluirUsuarioBio(UsuarioExc, uiBio.jCboTipoModuloBio.getSelectedIndex());
            }
            CarregarTemplatesBase();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ExcluirTodosUsuariosInner() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        uiBio.jTxaManutencao.setText("Excluir todos usuários bio");
        if (bioService6xx.ExcluirTodosUsuariosBio())
        {
            uiBio.jTxaManutencao.append("\nTodos usuários excluidos");
        }else{
            uiBio.jTxaManutencao.setText("Não foi possivel excluir");
        }
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void ExcluirUsuarioBioInner() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        uiBio.jTxaManutencao.setText("Excluir usuários selecionados");
        List<String> ListaUsuariosExcluir = getUsuariosSelecionados(uiBio.jtblUsuariosInner);
        if (ListaUsuariosExcluir.isEmpty())
        {
            JOptionPane.showConfirmDialog(null, "Nenhum usuário foi selecionado");
            return;
        }
        if (bioService6xx.isConectado())
        {
            int[] Respostas = new int[4];
            for(int index = 0; index < ListaUsuariosExcluir.size(); index++){
                uiBio.jTxaManutencao.setText("Excluir usuário " + ListaUsuariosExcluir.get(index));
                AtualizarRespostas(bioService6xx.ExcluirUsuariosInner(ListaUsuariosExcluir.get(index)), Respostas);
            }
            if (Respostas[3] == 250){
                uiBio.jTxaManutencao.setText("Modulo incorreto");
            }else{
                uiBio.jTxaManutencao.append("Excluidos " + Respostas[0] 
                                    + "\nNão cadastrados " + Respostas[1] 
                                    + "\nFalha " + Respostas[2]);
            }
            bioService6xx.FecharPortaComunicacao();
        }
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void AtualizarListaUsuarios() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        uiBio.jTxaManutencao.setText("Listar usuários bio");
        ListaUsuariosBio = bioService6xx.ListarUsuariosBio();
        if (ListaUsuariosBio == null){
            uiBio.jTxaManutencao.append("Modulo incorreto");
        }
        else{
            PreencherGridUsuariosInner6xx(ListaUsuariosBio);
            uiBio.jTxaManutencao.append("\nTotal de usuários " + ListaUsuariosBio.size());
        }
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void ReceberDigitaisBio() throws SQLException, InterruptedException, IOException, FileNotFoundException, ClassNotFoundException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        uiBio.jTxaManutencao.setText("Receber usuários selecionados");
        ListaUsuariosBio = bioService6xx.ReceberDigitaisBio(getUsuariosSelecionados(uiBio.jtblUsuariosInner));
        if (ListaUsuariosBio == null){
            uiBio.jTxaManutencao.setText("Verifique se o modulo está correto");
        }else{
            uiBio.jTxaManutencao.append("\nGravar usuários na base");
            GravarUsuariosBaseDados();
            uiBio.jTxaManutencao.append("\nTotal de usuários recebidos " + ListaUsuariosBio.size());
            CarregarTemplatesBase();
        }
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void IdentificarDigital() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.jTxaCadastroInner.setText("Identificar digital");
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        uiBio.jTxaCadastroInner.append("\n" + bioService6xx.IdentificarDigital());
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void VerificarCadastro() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.jTxaCadastroInner.setText("Verificar cadastro");
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        int Ret = bioService6xx.VerificarCadastro();
        uiBio.jTxaCadastroInner.append("\n" + RetornarRespostaComando(Ret));
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void ConfigurarIdentificacaoVerificacao() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.LblStatusConf.setText("Configurar identificação e verificação");
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (bioService6xx.ConfigurarIdentificacaoVerificacao()){
            uiBio.LblStatusConf.setText("Identificação e verificação configurado");
        }
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    
    public void VerificarDigital() throws InterruptedException {
        getPropriedadesInner();
        bioService6xx = new EasyInnerBioService6xx(easyInner, InnerAtual);
        uiBio.jTxaCadastroInner.setText("Verificar digital");
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        int Ret = bioService6xx.VerificarDigital();
        uiBio.jTxaCadastroInner.append("\n" + RetornarRespostaComando(Ret));
        uiBio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    
    private String RetornarRespostaComando(int Ret)
    {
        String Resposta = "";
        switch (Ret)
        {
            case Enumeradores.RET_COMANDO_OK:
                Resposta = "Sucesso!";
                break;
            case Enumeradores.RET_BIO_USR_JA_CADASTRADO:
                Resposta = "Usuário já cadastrado";
                break;
            case Enumeradores.RET_BIO_USR_NAO_CADASTRADO:
                Resposta = "Usuário não cadastrado";
                break;
            case Enumeradores.RET_BIO_MODULO_INCORRETO:
                Resposta = "Modulo incorreto";
                break;
            case Enumeradores.RET_BIO_FALHA_CAPTURA:
                Resposta = "Falha";
                break;
        }
        return Resposta;
    }
    
    private void AtualizarRespostas(int Ret, int[] Respostas) {
        switch (Ret)
        {
            case (int)Enumeradores.RET_COMANDO_OK:
                Respostas[0]++;
                break;
            case (int)Enumeradores.RET_BIO_USR_JA_CADASTRADO:
                Respostas[1]++;
                break;
            case (int)Enumeradores.RET_BIO_BASE_CHEIA:
                Respostas[3] = 1;
                break;
            case Enumeradores.RET_BIO_MODULO_INCORRETO:
                Respostas[3] = 250;
            default:
                Respostas[2]++;
                break;
        }
    }
    
    private void getPropriedadesInner() {
        InnerAtual = new Inner();
        InnerAtual.Porta = Integer.parseInt(uiBio.jTxtPorta.getText());
        InnerAtual.TipoComBio = uiBio.jCboTipoModuloBio.getSelectedIndex();
        InnerAtual.Numero = Integer.parseInt(uiBio.jTxtNumInner.getText());
        InnerAtual.PadraoCartao = 0;
        InnerAtual.TipoConexao = 2;
        InnerAtual.QtdDigitos = 16;
        InnerAtual.DuasDigitais = uiBio.jRdbDigital2.isSelected();
        InnerAtual.Identificacao = uiBio.chkHabIdentificacao.isSelected() == true ? 1 : 0;
        InnerAtual.Verificacao = uiBio.chkHabVerificacao.isSelected() == true ? 1 : 0;
        InnerAtual.DedoDuplicado = uiBio.jchkDedoDuplicado.isSelected() == true ? 1 : 0;
        InnerAtual.TimeOutAjustes = Integer.parseInt(uiBio.jTxtTimeoutIdent.getText());
        InnerAtual.NivelLFD = Integer.parseInt(uiBio.jTxtNivelLFD.getText());
        InnerAtual.CartaoTemplateLeitor = uiBio.jTxtUsuarioCadInner.getText();
    }

    private byte[] getAjustesBiometricos(int TipoComBio) {
        byte[] Ajustes = new byte[16];
        if (TipoComBio == 0)
        {
            Ajustes[0] = 2; //ganho
            Ajustes[1] = 40; //brilho
            Ajustes[2] = 20; //contraste
            Ajustes[3] = 8; //seg indet.
            Ajustes[4] = 5; //seg verif.
            Ajustes[5] = 40; //qualidade reg
            Ajustes[6] = 30; //qualidade verif
            Ajustes[7] = 0; //filtro dig latente
            Ajustes[8] = 0; //captura adapt
            Ajustes[9] = 5; //total capturas
            Ajustes[10] = 50; //tempo cap
            Ajustes[11] = (byte)InnerAtual.TimeOutAjustes; //timeout ident
            Ajustes[12] = (byte)InnerAtual.NivelLFD; //nivel lfd
        }
        else
        {
            Ajustes[0] = (byte)InnerAtual.NivelLFD; //seg ident
            Ajustes[1] = (byte)InnerAtual.TimeOutAjustes; //timeout ident
            Ajustes[2] = (byte)InnerAtual.DedoDuplicado;
        }
        return Ajustes;
    }

    private void PreencherGridUsuariosInner6xx(List<UsuarioBio> ListaUsuariosBio) {
        try {
            DefaultTableModel Templates = (DefaultTableModel) uiBio.jtblUsuariosInner.getModel();
            
            Templates.setNumRows(0);
            
            for (UsuarioBio Templ : ListaUsuariosBio) {
                String[] tpl = new String[3];
                tpl[0] = Templ.getCartao();
                tpl[1] = Templ.getTemplate1();
                tpl[2] = Templ.getTemplate2();
                Templates.addRow(tpl);
            }
            uiBio.jtblUsuariosInner.setAutoCreateRowSorter(true);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private List<String> getUsuariosSelecionados(JTable tblSelecionado)
    {
        //Usuário encontrado
        List<String> ListaUsuarios = new ArrayList();
        int[] selecionados = tblSelecionado.getSelectedRows();
        for(int index = 0; index < selecionados.length; index++){
            ListaUsuarios.add(tblSelecionado.getValueAt(selecionados[index], 0).toString());
        }
        return ListaUsuarios;
    }

    private void GravarUsuariosBaseDados() throws SQLException {
        for(int index = 0; index < ListaUsuariosBio.size(); index++)
        {
            UsuariosBio.InserirUsuarioBio(ListaUsuariosBio.get(index), InnerAtual.TipoComBio);
        }
    }
}
