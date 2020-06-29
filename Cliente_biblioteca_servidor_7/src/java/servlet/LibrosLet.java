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
import service.LibrosClient;


/**
 *
 * @author Ignacio
 */
@WebServlet(name = "LibrosLet", urlPatterns = {"/LibrosLet"})
public class LibrosLet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws org.json.JSONException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException, MalformedURLException_Exception, IOException_Exception  {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            GoogleApi goo = new GoogleApi();
            String get = request.getParameter("query");
            LibrosClient client = new LibrosClient();
            JsonArray array = client.findBook_JSON(JsonArray.class, get);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LibrosLet</title>");
            out.println("<style>");
            out.println(".boxItems{\n" +
                        "	display: flex;\n" +
                        "	flex-wrap: wrap;\n" +
                        "	margin-left: -15px;\n" +
                        "	margin-right: -15px;\n" +
                        "	box-sizing: border-box;\n" +
                        "}");
            out.println(".boxItem{\n" +
                        "	box-sizing: border-box;\n" +
                        "	flex-direction: column;\n" +
                        "	position: relative;\n" +
                        "	border: 1px solid rgba(0,0,0,.125);\n" +
                        "	flex: 0 0 25%;\n" +
                        "	max-width: 25%;\n" +
                        "	padding: 15px;\n" +
                        "}");
            out.println(".imgaBox{\n" +
                        "	height: 225px;\n" +
                        "	border-radius: 25px;\n" +
                        "	overflow: hidden;\n" +
                        "}");
            out.println(".contentBox{\n" +
                        "	flex: 1 1 auto;\n" +
                        "	padding: 1.25rem;\n" +
                        "	display: block;\n" +
                        "	box-sizing: border-box;\n" +
                        "}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='boxItems'>");
            for (JsonValue jsonValue : array) {
                out.println("<div class='boxItem'>");
                JSONObject book = new JSONObject(jsonValue.toString());
                out.println("<div class='imgaBox'><img src='" + goo.getImageLink(book.getString("libID")) + "></div>");
                out.println("<div class='contentBox'>" + book.getString("nombre") + "</div>");
                out.println("<div class='contentBox'>" + book.getString("libID") + "</div>");
                out.println("</div>");
            }   
            out.println("</div>");
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
            Logger.getLogger(LibrosLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException_Exception ex) {
            Logger.getLogger(LibrosLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException_Exception ex) {
            Logger.getLogger(LibrosLet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LibrosLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException_Exception ex) {
            Logger.getLogger(LibrosLet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException_Exception ex) {
            Logger.getLogger(LibrosLet.class.getName()).log(Level.SEVERE, null, ex);
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
