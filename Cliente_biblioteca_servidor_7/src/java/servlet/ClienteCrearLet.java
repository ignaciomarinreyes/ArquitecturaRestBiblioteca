/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entities.Clientes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ClientCliente;

/**
 *
 * @author Ignacio
 */
@WebServlet(name = "ClienteCrearLet", urlPatterns = {"/ClienteCrearLet"})
public class ClienteCrearLet extends HttpServlet {

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
        String dni = request.getParameter("dni");
        ClientCliente clienteRest = new ClientCliente();
        PrintWriter out = response.getWriter();
        String JSONcomprobacionDni = clienteRest.find_JSON(String.class, dni);
        if (!JSONcomprobacionDni.isEmpty()) {
            out.println("El dni introducido pertenece a otro cliente, no se ha añadido el cliente.");
            out.println("<p><a href='index.html'>Volver</a></p>");
        } else {
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");
            String contrasena = request.getParameter("contrasena");
            Clientes cliente = new Clientes();
            cliente.setDni(dni);
            cliente.setNombre(nombre);
            cliente.setContrasena(contrasena);
            cliente.setCorreo(correo);
            cliente.setTelefono(telefono);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletAnadirContacto</title>");
            out.println("</head>");
            out.println("<body>");
            clienteRest.create_JSON(cliente);
            out.println("<p>El siguiente contacto ha sido añadido:</p>");
            out.println(dni + " " + nombre + " " + contrasena + " " + correo + " " + telefono);
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
