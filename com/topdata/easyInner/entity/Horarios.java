/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo.santana
 */

/* It class is kind of like an enum, it is used to work with list of horarios*/
public class Horarios 
{    
    
    //Kind of like an enum
    public int codigo;
    public byte faixa;
    public byte dia;
    public byte hora;
    public byte minuto;
    public byte horario;
    
    public static List MontarListaHorarios(){
        List<Horarios> ListaHorarios = new ArrayList();
        Horarios VHorario = new Horarios();
        VHorario.horario = 1;
        VHorario.dia = 1;
        VHorario.faixa = 1;
        VHorario.hora = 8;
        VHorario.minuto = 0;
        //Horario 1 dia 1 segunda
        ListaHorarios.add(VHorario); 
        
        VHorario = new Horarios();
        VHorario.horario = 1;
        VHorario.dia = 1;
        VHorario.faixa = 2;
        VHorario.hora = 12;
        VHorario.minuto = 0;
        //Horario 1 dia 1 segunda
        ListaHorarios.add(VHorario);
        
        VHorario = new Horarios();
        VHorario.horario = 1;
        VHorario.dia = 1;
        VHorario.faixa = 3;
        VHorario.hora = 13;
        VHorario.minuto = 0;
        //Horario 1 dia 1 segunda
        ListaHorarios.add(VHorario);
        
        VHorario = new Horarios();
        VHorario.horario = 1;
        VHorario.dia = 1;
        VHorario.faixa = 4;
        VHorario.hora = 18;
        VHorario.minuto = 0;        
        //Horario 1 dia 1 segunda
        ListaHorarios.add(VHorario);
        
        VHorario = new Horarios();
        VHorario.horario = 1;
        VHorario.dia = 2;
        VHorario.faixa = 1;
        VHorario.hora = 8;
        VHorario.minuto = 0;
        //Horario 1 dia 2 segunda
        ListaHorarios.add(VHorario); 
        
        VHorario = new Horarios();
        VHorario.horario = 1;
        VHorario.dia = 2;
        VHorario.faixa = 2;
        VHorario.hora = 12;
        VHorario.minuto = 0;
        //Horario 1 dia 2 segunda
        ListaHorarios.add(VHorario);
        
        VHorario = new Horarios();
        VHorario.horario = 1;
        VHorario.dia = 2;
        VHorario.faixa = 3;
        VHorario.hora = 13;
        VHorario.minuto = 0;
        //Horario 1 dia 2 segunda
        ListaHorarios.add(VHorario);
        
        VHorario = new Horarios();
        VHorario.horario = 1;
        VHorario.dia = 2;
        VHorario.faixa = 4;
        VHorario.hora = 18;
        VHorario.minuto = 0;        
        //Horario 1 dia 2 segunda
        ListaHorarios.add(VHorario);
        return ListaHorarios;
    }
}
