/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Ignacio
 */
public class GoogleApi {
    public String getImageLink(String id) throws IOException, JSONException, MalformedURLException_Exception, IOException_Exception{
        JSONObject obj = new JSONObject(parser(id));
        return obj.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
    }
    
    public String getDescription(String id) throws IOException, JSONException, MalformedURLException_Exception, IOException_Exception {
        JSONObject obj = new JSONObject(parser(id));
        return obj.getJSONObject("volumeInfo").getString("description");
    }

    private static String parser(java.lang.String id) throws MalformedURLException_Exception, IOException_Exception {
        control.WebServiceSoapJson_Service service = new control.WebServiceSoapJson_Service();
        control.WebServiceSoapJson port = service.getWebServiceSoapJsonPort();
        return port.parser(id);
    }

}
