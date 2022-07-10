/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.cityproject;

/**
 *
 * @author User
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.sql.*;

final class CityFromCsv implements ICity {
    HashMap<String, Integer> mapCity = null;

    public CityFromCsv(int col, String filepath, String delimiter) {
        mapCity = new HashMap<>();

        String[] data;
        String currentLine;
        BufferedReader br = null;

        try {
            FileReader fr = new FileReader(filepath);

            br = new BufferedReader(fr);
            br.readLine();

            while ((currentLine = br.readLine()) != null) {
                data = currentLine.split(delimiter);

                if (data.length >= col) {
                    add(data[col]);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    @Override
    public void add(String city) {
        // Checking if a key is present
        if (mapCity.containsKey(city)) {
            int count = mapCity.get(city);

            mapCity.replace(city, ++count);
        } else {
            mapCity.put(city, 1);
        }
    }

    @Override
    public void display() {
        System.out.println("Elenco Città dal CSV:");

        if (mapCity != null) {
            if (mapCity.isEmpty()) {
                System.out.println("Nessuna Città trovata");
            } else {
                // Print keys and values
                for (String i : mapCity.keySet()) {
                    System.out.println(i + " => " + mapCity.get(i));
                }
            }
        } else {
            System.out.println("Lettura Città non avvenuta");
        }
    }

    @Override
    public void add(String city, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

final class CityFromDb implements ICity {
    HashMap<String, Integer> mapCity = null;

    public CityFromDb() throws SQLException, ClassNotFoundException {
        mapCity = new HashMap<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        String databaseURL = "jdbc:mysql://localhost:3306/citta";
        String user = "root";
        String pass = "";

        try {
            // load and register JDBC driver for MySQL
//            Class.forName("com.mysql.cj.jdbc.Driver");

            myConn = DriverManager.getConnection(databaseURL, user, pass);

            //Create statement
            myStmt = myConn.createStatement();

            //Execute SQL query
            myRs = myStmt.executeQuery("Select citta, count(*) As cnt From citta Group By citta");

            //Process the result
            while (myRs.next()) {
                add(myRs.getString("citta"), myRs.getInt("cnt"));
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
    public void add(String city, int i) {
        // Checking if a key is present
        if (mapCity.containsKey(city)) {
            int count = mapCity.get(city);

            mapCity.replace(city, ++count + i);
        } else {
            mapCity.put(city, i);
        }
    }

    @Override
    public void display() {
        System.out.println("Elenco Città dal DB:");

        if (mapCity != null) {
            if (mapCity.isEmpty()) {
                System.out.println("Nessuna Città trovata");
            } else {
                // Print keys and values
                for (String i : mapCity.keySet()) {
                    System.out.println(i + " => " + mapCity.get(i));
                }
            }
        } else {
            System.out.println("Lettura Città non avvenuta");
        }
    }

    @Override
    public void add(String city) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

public class CityProject {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CityFromCsv cityCsv = new CityFromCsv(4, "./CSVCitta.csv", ",");
        cityCsv.display();

        System.out.println("");

        CityFromDb cityDb = new CityFromDb();
        cityDb.display();
    }
}
