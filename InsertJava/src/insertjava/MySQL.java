/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Luicko
 */
class MySQL {
    private static Connection con;

    public static void getConnection() {
        if (con == null) {
            String URL = "jdbc:mysql://localhost:3306/libreria";
            try {
                con = DriverManager.getConnection(URL, "root", "poiu4321");
            } catch (SQLException ex) {
                ex.getStackTrace();
                System.out.println("AQUI");
            }
        }
    }

    public static void insertarLibros(String id, String nombre, String ano, String autor, String editor) {
            System.out.println("INSERT INTO Libros(id, nombre, ano, autor, genero) VALUES ('" + id + "','" + nombre + "','" + ano + "','" + autor + "','" + editor  + "');");
            /*stmt.setString(1, nombre);
            stmt.setString(2, ano);
            stmt.setString(3, autor);
            stmt.setString(4, genero);
            stmt.executeUpdate();*/

    }
}
