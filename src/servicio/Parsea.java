/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.sql.Date;

/**
 *
 * @author jorda
 */
public class Parsea {
    public java.sql.Date stringDate(String date){
        return Date.valueOf(date);
    }
    public double stringDouble(String num){
        return Double. parseDouble(num);
    }
}
