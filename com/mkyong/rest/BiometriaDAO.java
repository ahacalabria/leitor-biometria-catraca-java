/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author GOVERNO-01
 */
public class BiometriaDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static BiometriaDAO instancia;
    private String IPSERVER = "192.168.0.111";
    private String DBNAME = "admin_biometria";
    private String DBUSER = "admin_biometria";
    private String DBPASS = "A9aTDphpNQ";

    public static synchronized BiometriaDAO getInstance(){
        if(instancia==null) instancia = new BiometriaDAO();
        return instancia;
    }
    
    public ArrayList<String> readDataBase() {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://"+this.IPSERVER+":3306/"+this.DBNAME, this.DBUSER, this.DBPASS);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
//             Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("SELECT p.ID, p.post_title, pm.meta_value FROM spb_posts p INNER JOIN spb_postmeta pm ON p.ID = pm.post_id WHERE p.post_type = 'cidadao' AND p.post_status = 'publish' AND pm.meta_key = 'biometria_1'");
            return writeResultSet(resultSet);

            // PreparedStatements can use variables and are more efficient
//            preparedStatement = connect
//                    .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1
//            preparedStatement.setString(1, "Test");
//            preparedStatement.setString(2, "TestEmail");
//            preparedStatement.setString(3, "TestWebpage");
//            preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
//            preparedStatement.setString(5, "TestSummary");
//            preparedStatement.setString(6, "TestComment");
//            preparedStatement.executeUpdate();

//            preparedStatement = connect
//                    .prepareStatement("SELECT * from biometria.cidadao");
//            resultSet = preparedStatement.executeQuery();
//            writeResultSet(resultSet);

            // Remove again the insert comment
//            preparedStatement = connect
//            .prepareStatement("delete from feedback.comments where myuser= ? ; ");
//            preparedStatement.setString(1, "Test");
//            preparedStatement.executeUpdate();

//            resultSet = statement
//            .executeQuery("select * from biometria.cidadao");
//            writeMetaData(resultSet);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close();
        }
        return null;
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private ArrayList<String> writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        ArrayList<String> biometrias = new ArrayList<>();
        int i = 0;
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String nome = resultSet.getString("post_title");
            String biometria = resultSet.getString("meta_value");
//            String summary = resultSet.getString("summary");
//            Date date = resultSet.getDate("datum");
//            String comment = resultSet.getString("comments");
            System.out.println("Nome: " + nome);
            System.out.println("biometria: " + biometria);
            biometrias.add(biometria);
            i++;
//            System.out.println("summary: " + summary);
//            System.out.println("Date: " + date);
//            System.out.println("Comment: " + comment);
        }
        return biometrias;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
    
    public static void main(String[] args) throws Exception {
        BiometriaDAO dao = new BiometriaDAO();
        dao.readDataBase();
    }

}
