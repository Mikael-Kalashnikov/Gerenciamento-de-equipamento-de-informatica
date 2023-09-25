package br.edu.ufersa.MinhaCasaTech.src.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDaoImp<E> implements BaseDao<E>{
    final static String URL = "jdbc:postgresql:projetopoo";
	final static String USER = "postgres";
	final static String PASS = "netodev";
	static Connection con = null;

	public static Connection getConnection() {
		if(con == null) {
			try {
				con = DriverManager.getConnection(URL,USER,PASS);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		else return con;
	}
	
	public static void closeConnection() {
		if(con !=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}
}
