package br.edu.ufersa.minhacasatech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAOImp<Entity> implements BaseDAO<Entity> {
    
    private static final String URL = "jdbc:postgresql:projetopoo";
    private static final String USER = "postgres";
    private static final String PASS = "netodev";
    private static Connection con = null;
    
    public static Connection getConnection() {
	if(con == null) {
	    try {
		con = DriverManager.getConnection(URL,USER,PASS);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return con;
    }
    
    public static void closeConnection() {
	if (con != null) {
	    try {
		con.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }
}
