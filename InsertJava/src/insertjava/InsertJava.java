/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Luicko
 */
public class InsertJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JSONException {
        JSON json = new JSON();
        JSONObject obj = null;
        MySQL mysql = new MySQL();
        mysql.getConnection();

        String[] urlBusquedas = new String[]{
            "https://www.googleapis.com/books/v1/volumes?q=a",
            "https://www.googleapis.com/books/v1/volumes?q=b",
            "https://www.googleapis.com/books/v1/volumes?q=c",
            "https://www.googleapis.com/books/v1/volumes?q=d",
            "https://www.googleapis.com/books/v1/volumes?q=e",
            "https://www.googleapis.com/books/v1/volumes?q=f",
            "https://www.googleapis.com/books/v1/volumes?q=g",
            "https://www.googleapis.com/books/v1/volumes?q=h",
            "https://www.googleapis.com/books/v1/volumes?q=i",
            "https://www.googleapis.com/books/v1/volumes?q=j",
            "https://www.googleapis.com/books/v1/volumes?q=k",
            "https://www.googleapis.com/books/v1/volumes?q=l",
            "https://www.googleapis.com/books/v1/volumes?q=m",
            "https://www.googleapis.com/books/v1/volumes?q=n"
        };

        for (int i = 0; i < urlBusquedas.length; i++) {
            String res = json.leerJSON(urlBusquedas[i]);
            try {
                obj = new JSONObject(res);
            } catch (JSONException ex) {
                System.out.println("ERROR 4");
            }
            JSONArray array = obj.getJSONArray("items");
            for (int j = 0; j < array.length(); j++) {
                try {
                    JSONObject jsonObject = array.getJSONObject(j);
                    String id = jsonObject.getString("id");
                    String nombre = jsonObject.getJSONObject("volumeInfo").getString("title");
                    String autor = jsonObject.getJSONObject("volumeInfo").getJSONArray("authors").getString(0);
                    String editor = jsonObject.getJSONObject("volumeInfo").getString("publisher");
     
                    String ano = jsonObject.getJSONObject("volumeInfo").getString("publishedDate");
                    if (ano.length() == 4){
                        ano += "-01-01";
                    }
                    //System.out.println(nombre + autor + genero + ano);
                    mysql.insertarLibros(id, nombre, ano, autor, editor);
                } catch (JSONException e) {
                    //e.printStackTrace();
                }
                //mysql.insertarLibros(jsonObject.getString("title"), jsonObject.getString("publishedDate"), jsonObject.getString("authors"), jsonObject.getString("categories"));
            }

        }

    }

}
