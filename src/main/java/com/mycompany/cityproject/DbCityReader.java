/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cityproject;

import java.sql.*;
import java.util.HashMap;

public class DbCityReader extends CityReader {

    public DbCityReader() throws SQLException {
        mapCity = new HashMap<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        String databaseURL = "jdbc:mysql://195.179.236.1:3306/u323045651_citta";
        String user = "u323045651_java";
        String pass = "P3MYEx!,Q!d!";

        String city;

        try {
            // load and register JDBC driver for MySQL
//            Class.forName("com.mysql.cj.jdbc.Driver");

            myConn = DriverManager.getConnection(databaseURL, user, pass);

            //Create statement
            myStmt = myConn.createStatement();

            //Execute SQL query
           myRs = myStmt.executeQuery("Select citta From citta");

            //Process the result
            while ((city = readNewCity(myRs)) != null) {
                hasNewData(city);
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }
        }
    }

    @Override
    public boolean hasNewData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public final String readNewCity(ResultSet myRs) throws SQLException {
        if (myRs.next()) {
           return myRs.getString("citta");
        }

        return null;
    }

    public void display() {
        display("Elenco Città dal DB:");
    }

}
