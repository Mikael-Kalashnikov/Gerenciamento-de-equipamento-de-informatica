package br.edu.ufersa.minhacasatech.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseDAOImp<Entity> implements BaseDAO<Entity> {

    static final String URL = "jdbc:postgresql:projetopoo";
    static final String USER = "postgres";
    static final String PASS = "0517";
    static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException ex) {
                Logger.getLogger(BaseDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn = null;
        }
    }
}
