/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.entity;

/**
 *
 * @author jonatas.silva
 */
public class UsuarioBio {
    private int Codigo;
    private String Cartao;
    private String Template1;
    private String Template2;

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getCartao() {
        return Cartao;
    }

    public void setCartao(String Cartao) {
        this.Cartao = Cartao;
    }

    public String getTemplate1() {
        return Template1;
    }

    public void setTemplate1(String Template1) {
        this.Template1 = Template1;
    }

    public String getTemplate2() {
        return Template2;
    }

    public void setTemplate2(String Template2) {
        this.Template2 = Template2;
    }
}
