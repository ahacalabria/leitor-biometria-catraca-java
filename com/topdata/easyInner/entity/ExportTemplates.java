/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.entity;

import com.nitgen.SDK.BSP.NBioBSPJNI;
import com.topdata.easyInner.enumeradores.Enumeradores;
import java.util.HashMap;

/**
 *
 * @author jonatas.silva
 */
public class ExportTemplates {
    private NBioBSPJNI bsp; 
    private NBioBSPJNI.Export Exportar;
    private byte[] Digital;
    
    public ExportTemplates(NBioBSPJNI BspJni, NBioBSPJNI.Export Export,  byte[] Templ)
    {
        bsp = BspJni;
        Exportar = Export;
        Digital = Templ;
    }
    
    public Object ConverterTempl(Enumeradores.TiposTempl TipoTpl, int TipoData)
    {
        HashMap<Enumeradores.TiposTempl, Object> TemplateConversao = new HashMap<>();
        TemplateConversao.put(Enumeradores.TiposTempl.Fir, getTemplConvertidoFIR(Digital));
        TemplateConversao.put(Enumeradores.TiposTempl.TextCode, getTemplConvertidoTextCode(Digital));
        TemplateConversao.put(Enumeradores.TiposTempl.HFir, getTemplConvertidoHFIR(Digital));
        TemplateConversao.put(Enumeradores.TiposTempl.Dados, getTemplConvertidoData(Digital, TipoData));
        return TemplateConversao.get(TipoTpl);
    }

    private Object getTemplConvertidoFIR(byte[] Digital)
    {
        NBioBSPJNI.FIR_HANDLE FIRHandle = bsp.new FIR_HANDLE();
        NBioBSPJNI.FIR FirTempl = bsp.new FIR();
        Exportar.ImportFIR(Digital, 0, 0, FIRHandle);
        bsp.GetFIRFromHandle(FIRHandle, FirTempl);
        return  FirTempl;
    }

    private Object getTemplConvertidoTextCode(byte[] Digital)
    {
        NBioBSPJNI.FIR_HANDLE FIRHandle = bsp.new FIR_HANDLE();
        NBioBSPJNI.FIR_TEXTENCODE TextCode = bsp.new FIR_TEXTENCODE();
        Exportar.ImportFIR(Digital, 404, 7, FIRHandle);
        bsp.GetTextFIRFromHandle(FIRHandle, TextCode);
        return TextCode;
    }

    private Object getTemplConvertidoHFIR(byte[] Digital)
    {
        NBioBSPJNI.FIR_HANDLE FIRHandle = bsp.new FIR_HANDLE();
        Exportar.ImportFIR(Digital, 0, 0, FIRHandle);
        return FIRHandle;
    }
    
    private Object getTemplConvertidoData(byte[] Digital, int TipoData)
    {
        NBioBSPJNI.FIR_HANDLE FIRHandle = bsp.new FIR_HANDLE();
        NBioBSPJNI.Export.DATA ExportData = Exportar.new DATA();
        NBioBSPJNI.INPUT_FIR inputFIR = bsp.new INPUT_FIR();
        Exportar.ImportFIR(Digital, 0, 0, FIRHandle);
        inputFIR.SetFIRHandle(FIRHandle);
        Exportar.ExportFIR(inputFIR, ExportData, TipoData);
        return  ExportData;
    }
    
}
