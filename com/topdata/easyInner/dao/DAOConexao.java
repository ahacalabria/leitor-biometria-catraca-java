/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topdata.easyInner.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;;
import java.sql.DriverManager;



/**
 *
 * @author jonatas.silva
 * @author
 */
public class DAOConexao 
{
        private static Connection Conn;

    public static Connection getConn() {
        return Conn;
    }

    public void setConn(Connection Conn) {
        this.Conn = Conn;
    }
    
    private static String pathDatabase;
    
    public static Connection ConectarBase() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        try
        {
            if (Conn == null)
            {
                //Getting the path of the database
                File fi = new File(pathDatabase = "C:/Users/GOVERNO/Documents/Java/NetBeans/ExemplosSDK_Acesso/SDK_Exemplos.mdb");
                FileReader file = null;
                
                //Verifying if the file not exist
                if (fi.exists() == false) 
                {   
                    //Reading the file caminhoDB.txt, it can be cointain the path 
                    file = new FileReader(System.getProperty("user.dir") + "\\caminhoDB.txt");
                   
                    BufferedReader fileReader = new BufferedReader(file);
                    pathDatabase = fileReader.readLine();
                }           
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + pathDatabase;
                Conn = DriverManager.getConnection(database,"","");
            }
            if (Conn.isClosed())
            {
                Conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + pathDatabase,"","");
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
       
        return Conn;
    }   
}
