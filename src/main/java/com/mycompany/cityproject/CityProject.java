/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.cityproject;

import java.sql.SQLException;

public class CityProject {

    public static void main(String[] args) throws SQLException {
        CityAlgorithm algo = new CityAlgorithm();
        
        FileCityReader fileCityReader = new FileCityReader(".\\CSVCitta.csv");
        algo.computeCityOccurrences(fileCityReader);
        
        DbCityReader dbCityReader = new DbCityReader();
        algo.computeCityOccurrences(dbCityReader);
    }
}
