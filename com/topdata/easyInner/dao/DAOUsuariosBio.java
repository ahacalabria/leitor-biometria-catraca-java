/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.dao;

import com.topdata.easyInner.entity.UsuarioBio;
import com.topdata.easyInner.entity.UsuarioSemDigital;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonatas.silva
 */
public class DAOUsuariosBio {
            
    public List<UsuarioBio> ConsultarUsuariosBio(int TipoModBio) throws SQLException
    {
        try
        {
            String BaseBio = TipoModBio == 0 ? "UsuariosBio" : "UsuariosBioLC";
            List<UsuarioBio> ListUsers;
            Statement stm = DAOConexao.ConectarBase().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM " + BaseBio + " ORDER BY CDbl(Cartao)");
            ListUsers = new ArrayList();
            while (rs.next())
            {
                UsuarioBio user = new UsuarioBio();
                user.setCartao(rs.getString("Cartao"));
                user.setTemplate1(rs.getString("Template1"));
                user.setTemplate2(rs.getString("Template2"));
                ListUsers.add(user);
            }   
            DAOConexao.getConn().close();
            return ListUsers;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            DAOConexao.getConn().close();
            return null;
        }
    }
    
    public boolean ExisteUsuarioBio(String Usuario, int TipoModBio) throws SQLException, IOException, FileNotFoundException, ClassNotFoundException
    {
        String BaseBio = TipoModBio == 0 ? "UsuariosBio" : "UsuariosBioLC";
        Statement stmt = DAOConexao.ConectarBase().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + BaseBio + " WHERE Cartao = '" + Usuario + "'");
        if (rs.next())
        {
            stmt.close();
            DAOConexao.getConn().close();
            return true;
        }
        stmt.close();
        DAOConexao.getConn().close();
        return false;
    }
    
    public boolean InserirUsuarioBio(UsuarioBio user, int TipoModBio) throws SQLException
    {
        try
        {
            String BaseBio = TipoModBio == 0 ? "UsuariosBio" : "UsuariosBioLC";
            String sql = "INSERT INTO " + BaseBio + "  (Cartao, Template1, Template2) VALUES (?, ?, ?)";
            PreparedStatement stmt = DAOConexao.ConectarBase().prepareStatement(sql);
            stmt.setString(1, user.getCartao());
            stmt.setString(2, user.getTemplate1());
            stmt.setString(3, user.getTemplate2());
            stmt.executeUpdate();
            stmt.close();
            DAOConexao.getConn().close();
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            DAOConexao.getConn().close();
            return false;
        }
    }
    
    public boolean ExcluirUsuarioBio(String Usuario, int TipoModBio) throws SQLException
    {
        try
        {
            String BaseBio = TipoModBio == 0 ? "UsuariosBio" : "UsuariosBioLC";
            String Del = "DELETE FROM " + BaseBio + " Where Cartao = '" + Usuario + "'";
            PreparedStatement stmt = DAOConexao.ConectarBase().prepareStatement(Del);
            stmt.executeUpdate();
            DAOConexao.getConn().close();
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            DAOConexao.getConn().close();
            return false;
        }
    }
    
    public List<UsuarioSemDigital> ConsultarUsuarioSemDigital() throws SQLException
    {
        try
        {
            List<UsuarioSemDigital> ListUsers;
            Statement stm = DAOConexao.ConectarBase().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM ListaSemDigital ORDER BY CDbl(Cartao)");
            ListUsers = new ArrayList();
            while (rs.next())
            {
                UsuarioSemDigital user = new UsuarioSemDigital();
                user.setCartao(rs.getString("Cartao"));
                ListUsers.add(user);
            }   
            stm.close();
            DAOConexao.getConn().close();
            return ListUsers;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            DAOConexao.getConn().close();
            return null;
        }
    }

    public void InserirUsuarioSD(String text) throws IOException, SQLException
    {
        String sql = "INSERT INTO ListaSemDigital (Cartao) VALUES (?)";
        PreparedStatement stmt = null;
        try
        {
            stmt = DAOConexao.ConectarBase().prepareStatement(sql);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(DAOUsuariosBio.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DAOUsuariosBio.class.getName()).log(Level.SEVERE, null, ex);
        }
        stmt.setString(1, text);
        stmt.executeUpdate();
        stmt.close();
        DAOConexao.getConn().close();
    }
}
