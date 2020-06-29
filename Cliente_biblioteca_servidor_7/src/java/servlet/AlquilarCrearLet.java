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
@WebServlet(name = "AlquilarCrearLet", urlPatterns = {"/AlquilarCrearLet"})
public class AlquilarCrearLet extends HttpServlet {

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
            JsonArray array = clientL.findAll_JSON(JsonArray.class);
            JSONObject book = null;
            for (JsonValue jsonValue : array) {
                book = new JSONObject(jsonValue.toString());
                if (book.getString("libID").equals(request.getParameter("book"))) {
                    break;
                }
            }
            //JSONObject user = clientC.find_JSON(JSONObject.class, request.getParameter("user"));
            JsonArray array2 = clientC.findAll_JSON(JsonArray.class);
            JSONObject user = null;
            for (JsonValue jsonValue : array2) {
                user = new JSONObject(jsonValue.toString());
                out.println(user.toString());
                if (user.getString("dni").equals(request.getParameter("user"))) {
                    break;
                }
            }
            out.println(book.getString("nombre"));
            out.println(user.toString());
            if (book.length() == 0 || user.length() == 0) {
                //response.sendRedirect("wrong_alquiler.html");
            }else{
                //try{
                    if (!request.getParameter("user").equals(user.getString("dni")) && !request.getParameter("pass").equals(user.getString("contrasena")) ) {
                        response.sendRedirect("wrong_alquiler.html");
                    }
                    out.println("ANTES");
                    AlquilerPK apk = new AlquilerPK(user.getString("dni"), book.getString("libID"));
                    out.println(apk.getCliDNI());
                    out.println("APK");
                    SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
                    Alquiler al = new Alquiler(apk, simp.parse("2020-05-01"));
                    clientA.create_JSON(al);
                    response.sendRedirect("completa_agregar.html");
                /*}catch(Exception ex){
                    response.sendRedirect("wrong_server.html");
                }*/
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlquilarLet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlquilarLet at " + request.getContextPath() + "</h1>");
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
            Logger.getLogger(AlquilarCrearLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AlquilarCrearLet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AlquilarCrearLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AlquilarCrearLet.class.getName()).log(Level.SEVERE, null, ex);
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
