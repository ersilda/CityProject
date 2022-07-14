/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cityproject;

import java.util.HashMap;

/**
 *
 * @author Mauro
 */
public class CityAlgorithm {
    HashMap<String,Integer> _mapCity = new HashMap<String,Integer>();
    
    public void computeCityOccurrences(ICityReader reader){
        while( reader.hasNewData()){
            String city = reader.readNewCity();
            if( _mapCity.containsKey(city)){
                int count = _mapCity.get(city);
                count++;
                _mapCity.put(city,count);
            }
            else
                _mapCity.put(city,1);
                
        }
    }
}
