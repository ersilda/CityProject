/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cityproject;

import java.util.HashMap; 

public abstract class CityReader implements ICityReader {

    HashMap<String, Integer> mapCity;

    public boolean hasNewData(String city) {
        // Checking if a key is present
        if (mapCity.containsKey(city)) {
            int count = mapCity.get(city);

            mapCity.replace(city, ++count);

            return false;
        }

        mapCity.put(city, 1);

        return true;
    }

    @Override
    public String readNewCity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void display(String msg) {
        System.out.println(msg);

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

}
