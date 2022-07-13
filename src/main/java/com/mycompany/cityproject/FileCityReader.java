/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cityproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileCityReader extends CityReader {

    final int COL = 4;
    final String DELIMITER = ",";

    public FileCityReader(String filepath) {
        mapCity = new HashMap<>();

        BufferedReader br = null;
        String city;

        try {
            FileReader fr = new FileReader(filepath);

            br = new BufferedReader(fr);
            br.readLine();

            while ((city = readNewCity(br)) != null) {
                hasNewData(city);
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
    public boolean hasNewData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public final String readNewCity(BufferedReader br) throws IOException {
        String currentLine = br.readLine();

        if (currentLine != null) {
            String[] data = currentLine.split(DELIMITER);

            if (data.length >= COL) {
                return data[COL];
            }
        }

        return null;
    }

    public void display() {
        display("Elenco Città dal CSV:");
    }

}
