/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Ignacio
 */
@WebService(serviceName = "Web_service_soap_json")
public class Web_service_soap_json {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "parser")
    public String parse(@WebParam(name = "id") String id) throws MalformedURLException, IOException {
        String sUrl = "https://www.googleapis.com/books/v1/volumes/" + id;
        URL url = new URL(sUrl);
        URLConnection tc = url.openConnection();
        String rest = new String();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null){
                rest += line;
            }
        }
        return rest;
    }
}
