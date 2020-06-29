/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entities.Alquiler;
import entities.AlquilerPK;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import service.AlquilerCliente;
import service.ClientCliente;
import service.LibrosClient;

/**
 *
 * @author Ignacio
 */
@WebServlet(name = "AlquilarLet", urlPatterns = {"/AlquilarLet"})
public class AlquilarLet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ClientCliente clientC = new ClientCliente();
            AlquilerCliente clientA = new AlquilerCliente();
            LibrosClient clientL = new LibrosClient();
            JsonArray arrayCli = clientC.findAll_JSON(JsonArray.class);
            JSONObject user = null;
            for (JsonValue jsonValue : arrayCli) {
                user = new JSONObject(jsonValue.toString());
                if (user.getString("dni").equals(request.getParameter("user"))) {
                    break;
                }
            }
            if (!request.getParameter("user").equals(user.getString("dni")) && !request.getParameter("pass").equals(user.getString("contrasena")) ) {
                        response.sendRedirect("no_user.html");
                }
            JsonArray arrayA = clientA.findAll_JSON(JsonArray.class);
            ArrayList<JsonValue> alquiler = new ArrayList<JsonValue>();
            JSONObject check = null;
            for (JsonValue jsonValues : arrayA) {
                check = new JSONObject(jsonValues.toString());
                if (check.getJSONObject("alquilerPK").getString("cliDNI").equals(user.getString("dni"))) {
                    alquiler.add(jsonValues);
                }
            }
            out.println("<!DOCTYPE html>");
            JsonArray arrayL = clientL.findAll_JSON(JsonArray.class);
            JSONObject objA = null;
            JSONObject objL = null;
            ArrayList<JsonValue> alquilados = new ArrayList<JsonValue>();
            for (JsonValue jsonValue : alquiler) {
                objA = new JSONObject(jsonValue.toString());
                for (JsonValue jsonValue1 : arrayL) {
                    objL = new JSONObject(jsonValue1.toString());
                    if (objL.getString("libID").equals(objA.getJSONObject("alquilerPK").getString("boID"))) {
                        alquilados.add(jsonValue1);
                    }
                }
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlquilerLet</title>");            
            out.println("</head>");
            out.println("<body>");
            JSONObject show = null;
            for (JsonValue alquilado : alquilados) {
                show = new JSONObject(alquilado.toString());
                out.println("<h1>" + show.getString("nombre") + "</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(AlquilarLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AlquilarLet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(AlquilarLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AlquilarLet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
