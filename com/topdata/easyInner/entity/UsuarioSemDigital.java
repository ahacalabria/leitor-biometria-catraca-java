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
public class UsuarioSemDigital {
    private int Codigo;
    private String Cartao;    

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
}
