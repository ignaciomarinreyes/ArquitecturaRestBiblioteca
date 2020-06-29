/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entities.Libros;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LibrosClient;

/**
 *
 * @author Ignacio
 */
@WebServlet(name = "LibrosCrearLet", urlPatterns = {"/LibrosCrearLet"})
public class LibrosCrearLet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String libID = request.getParameter("libID");
        LibrosClient cliente = new LibrosClient();
        PrintWriter out = response.getWriter();
        String JSONcomprobacionDni = cliente.find_JSON(String.class, libID);
        if (!JSONcomprobacionDni.isEmpty()) {
            out.println("El idLib introducido pertenece a otro libro, no se ha añadido el libro.");
            out.println("<p><a href='index.html'>Volver</a></p>");
        } else {
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("ano");
            String telefono = request.getParameter("autor");
            String editorial = request.getParameter("editorial");
            String cantidad = request.getParameter("cantidad");
            Libros libro = new Libros();
            libro.setLibID(libID);
            libro.setNombre(nombre);
            //libro.setAno(ano);
            //libro.setTelefono(autor);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletAnadirContacto</title>");
            out.println("</head>");
            out.println("<body>");
            //cliente.create_JSON(contacto);
            out.println("<p>El siguiente contacto ha sido añadido:</p>");
            out.println(libID + " " + nombre + " " + email + " " + telefono);
            out.println("<p><a href='index.html'>Volver</a></p>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
