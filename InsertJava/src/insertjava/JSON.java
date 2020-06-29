package insertjava;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luicko
 */
public class JSON {
    public String leerJSON(String urlBusqueda){ 
        String response ="";
        try {        
            URL url = new URL(urlBusqueda);
            URLConnection conn= (HttpURLConnection) url.openConnection();
            String line; //variable para leer del buffer de la conexión HTTP String response = ""; //respuesta del servicio web
            BufferedReader rd; //buffer para leer la respuesta del servidor
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                response += line;
            }
            rd.close();
            
        } catch (MalformedURLException ex) {
            System.out.println("ERROR 2");
        } catch (IOException ex) {
            System.out.println("ERROR ");
        }
        return response;
    }
}
