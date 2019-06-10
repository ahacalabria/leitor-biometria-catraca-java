/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.service;

import com.topdata.EasyInner;
import com.topdata.easyInner.enumeradores.Enumeradores;
import com.topdata.easyInner.utils.EasyInnerUtils;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author jonatas.silva
 */
public class BioService {
    public static String ReceberModeloBio6xx(int NumInner, int TipoModBio){
        int ret = -1;
        String Modelo = "";
        ret = EasyInner.RequisitarModeloBio(NumInner, TipoModBio);
        if (ret == Enumeradores.RET_COMANDO_OK)
        {
            byte[] ModeloBio = new byte[4];
            ret = EasyInner.RespostaModeloBio(NumInner, ModeloBio);
            if (ret == Enumeradores.RET_COMANDO_OK)
            {
                Modelo = EasyInnerUtils.ASCIIToDecimal(ModeloBio);
            }
            else if (ret == Enumeradores.RET_BIO_MODULO_INCORRETO){
                Modelo = "Modulo incorreto";
            }
        }
        return Modelo;
    }
    public static String ReceberModeloBioAVer5xx(int NumInner) throws InterruptedException{
        int tentativas = 0;
        int Ret = -1;
        int[] Modelo = new int[4];
        HashMap InfoBio = new HashMap();
        EasyInner.SolicitarModeloBio(NumInner);
        Thread.sleep(100);
        do {
            //Retorna o resultado do comando SolicitarModeloBio, o modelo
            //do Inner Bio é retornado por referência no parâmetro da função.
            Ret = EasyInner.ReceberModeloBio(NumInner, 0, Modelo);
            Thread.sleep(100);
        } while (Ret != Enumeradores.RET_COMANDO_OK && tentativas++ < 20);

        if (Ret == Enumeradores.RET_COMANDO_OK) {
            //Define o modelo do Inner Bio
            switch (Modelo[0]) {
                case 1:
                    InfoBio.put("ModeloBioInner", "Light 100  FIM10");
                    InfoBio.put("Ligth", true);
                    break;
                case 4:
                    InfoBio.put("ModeloBioInner", "1000/4000  FIM01");
                    InfoBio.put("Ligth", false);
                    break;
                case 51:
                    InfoBio.put("ModeloBioInner", "1000/4000  FIM2030");
                    InfoBio.put("Ligth", false);
                    break;
                case 52:
                    InfoBio.put("ModeloBioInner", "1000/4000  FIM2040");
                    InfoBio.put("Ligth", false);
                    break;
                case 48:
                    InfoBio.put("ModeloBioInner", "Light 100  FIM3030");
                    InfoBio.put("Ligth", true);
                    break;
                case 64:
                    InfoBio.put("ModeloBioInner", "Light 100  FIM3040");
                    InfoBio.put("Ligth", true);
                    break;
                case 80:
                    InfoBio.put("ModeloBioInner", "FIM5060");
                    InfoBio.put("Ligth", false);
                    break;
                case 82:
                    InfoBio.put("ModeloBioInner", "FIM5260");
                    InfoBio.put("Ligth", false);
                    break;
                case 83:
                    InfoBio.put("ModeloBioInner", "FIM5360");
                    InfoBio.put("Ligth", true);
                    break;
                case 96:
                    InfoBio.put("ModeloBioInner", "FIM6060");
                    InfoBio.put("Ligth", false);
                    break;
                case 255:
                    InfoBio.put("ModeloBioInner", "Modelo do bio: Desconhecido");
                    InfoBio.put("Ligth", false);
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao solicitar Modelo");
            return null;
        }
        return InfoBio.get("ModeloBioInner").toString();
    }
    public static String ReceberVersaoBio6xx(int NumInner, int TipoModBio){
        int Ret = - 1;
        String versao = "";
        Ret = EasyInner.RequisitarVersaoBio(NumInner, TipoModBio);
        if (Ret == (int)Enumeradores.RET_COMANDO_OK)
        {
            byte[] VersaoBio = new byte[4];
            Ret = EasyInner.RespostaVersaoBio(NumInner, VersaoBio);
            if (Ret == (int)Enumeradores.RET_COMANDO_OK)
            {
                versao = EasyInnerUtils.ASCIIToDecimal(VersaoBio);
            }
            else if(Ret == Enumeradores.RET_BIO_MODULO_INCORRETO){
                versao = "Modulo incorreto";
            } 
        }
        return versao;
    }
    public static String ReceberVersaoBioAVer5xx(int NumInner) throws InterruptedException{
        int Ret = -1;
        int tentativas = 0;
        int[] Versao = new int[4];
        String VersaoBio = "";
        Ret = EasyInner.SolicitarVersaoBio(NumInner);
        Thread.sleep(40);
        do {
            //Retorna o resultado do comando SolicitarVersaoBio, a versão
            //do Inner Bio é retornado por referência nos parâmetros da
            //função.
            Ret = EasyInner.ReceberVersaoBio(NumInner, 0, Versao);
            Thread.sleep(100);

        } while (Ret != Enumeradores.RET_COMANDO_OK && tentativas++ < 30);
        if (Ret == Enumeradores.RET_COMANDO_OK) {
            VersaoBio = Integer.toString(Versao[0] + Versao[1]);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao solicitar Versão Bio");
            return null;
        }
        return VersaoBio;
    }
}
