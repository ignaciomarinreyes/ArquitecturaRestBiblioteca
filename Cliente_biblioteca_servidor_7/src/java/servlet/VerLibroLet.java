/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import control.GoogleApi;
import control.IOException_Exception;
import control.MalformedURLException_Exception;
import java.io.IOException;
import java.io.PrintWriter;
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
import service.ClientCliente;

import service.LibrosClient;


/**
 *
 * @author Ignacio
 */
@WebServlet(name = "VerLibroLet", urlPatterns = {"/VerLibroLet"})
public class VerLibroLet extends HttpServlet {

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
            throws ServletException, IOException, JSONException, MalformedURLException_Exception, IOException_Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            GoogleApi goo = new GoogleApi();
            LibrosClient cliente = new LibrosClient();       
            JsonArray jsonArray = cliente.findAll_JSON(JsonArray.class);
            JSONObject  json = null;
            for (JsonValue jsonValue : jsonArray) {
                json = new JSONObject(jsonValue.toString());
                if (json.getString("libID").equals(request.getParameter("id"))) {
                    break;
                }
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LibrosLet</title>");
            out.println("<style>");
            out.println("#activitiesTable{\n" +
                        "background-color: #fff;\n" +
                        "margin: auto;\n" +
                        "}");
            out.println(".headerTh{\n" +
                        "	font-family: Montserrat-Medium;\n" +
                        "	font-size: 12px;\n" +
                        "	color: #fff;\n" +
                        "	text-transform: uppercase;\n" +
                        "	background-color: #6c7ae0;\n" +
                        "	padding-top: 24px;\n" +
                        "	padding-bottom: 20px;\n" +
                        "	text-align: middle;\n" +
                        "	line-height: 1.4;\n" +
                        "}");
            out.println(".tdActivities{\n" +
                        "	line-height: 1.4;\n" +
                        "	color: gray;\n" +
                        "	font-size: 15px;\n" +
                        "	font-family: Montserrat-Medium;\n" +
                        "}");
            out.println(".rowActivities{\n" +
                        "	border-bottom: 3px solid #e5e5e5;\n" +
                        "}");
            out.println(".login{\n" +
                        "	text-align: center!important;\n" +
                        "	font-style: -apple-;\n" +
                        "	font-weight: 400;\n" +
                        "}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 class=login>" + json.getString("nombre") + "</h1>");
            out.println("<table id=activitiesTable>");
            out.println("<tr class=rowActivities><th class=headerTh>Nombre</th><td class=tdActivities>" + json.getString("nombre") + "</td><tr>");     
            out.println("<tr class=rowActivities><th class=headerTh>Autor</th><td class=tdActivities>" + json.getString("autor") + "</td><tr>");
            out.println("<tr class=rowActivities><th class=headerTh>Editorial</th><td class=tdActivities>" + json.getString("editorial") + "</td><tr>");
            out.println("<tr class=rowActivities><th class=headerTh>Fecha de publicacion</th><td class=tdActivities>" + json.getString("ano") + "</td><tr>");
            out.println("<tr class=rowActivities><th class=headerTh>Descripcion</th><td class=tdActivities>" + goo.getDescription(json.getString("libID")) + "</td></tr>");
            out.println("<tr class=rowActivities><th class=headerTh>Cantidad</th><td class=tdActivities>" + json.getString("cantidad") + "</td><tr>");
            out.println("</table>");
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
            Logger.getLogger(VerLibroLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException_Exception ex) {
            Logger.getLogger(VerLibroLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException_Exception ex) {
            Logger.getLogger(VerLibroLet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerLibroLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException_Exception ex) {
            Logger.getLogger(VerLibroLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException_Exception ex) {
            Logger.getLogger(VerLibroLet.class.getName()).log(Level.SEVERE, null, ex);
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
