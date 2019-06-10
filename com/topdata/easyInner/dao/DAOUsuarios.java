/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.dao;


import com.topdata.easyInner.entity.Usuarios;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Eduardo Garcia de Santana
 */
public class DAOUsuarios 
{

    /*QtdUsuarios allows us to choose the numbers of users that we want to 
    select*/
    public static List<Usuarios> consultarUsuarios(int qtdUsuariosLenght) throws IOException, SQLException, FileNotFoundException, ClassNotFoundException
    {
        try
        {
            //It creates a statement, it permets us to execute queries
            Statement stm = DAOConexao.ConectarBase().createStatement(); 
        
            //String query to select all the users 
            String query = "SELECT * FROM ListaOffLine";
        
            //Executing query
            ResultSet rsReader = stm.executeQuery(query);
        
            //List users from ListaOffLine Table
            List<Usuarios> listUsers = new ArrayList<>();
        
            //count users, it's necessary if 
            int count = 0;

            //Reading the Table Usuarios ListaOffline and making a list off users
            while(rsReader.next())
            {
                Usuarios user = new Usuarios();
            
                //adding attributes to the users object
                user.setCodigoUsuario(rsReader.getInt("Codigo"));
                user.setUsuario(rsReader.getString("Cartao"));
                user.setFaixa(rsReader.getInt("Faixa"));
            
                //adding users object into a list 
                listUsers.add(user);
            
                if (qtdUsuariosLenght != 0)
                {
                    count++;
                    if (count == qtdUsuariosLenght)
                    {
                        break;
                    }
                }
            }
        
            DAOConexao.getConn().close();
            return listUsers;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }
}
    
    
    

