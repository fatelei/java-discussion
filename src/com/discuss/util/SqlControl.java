package com.discuss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlControl {
	//Mysql url
	public static final String mysqlURL = "jdbc:mysql://localhost/discuss?useUnicode=true&characterEncoding=utf-8";
	//User name
	public static final String userName = "root";
	//User password
	public static final String userPassword = "123456";
	
	//db variable
	private Connection conn = null;
	private Statement sta = null;
	private PreparedStatement preSta = null;
	private ResultSet res = null;
		
	//get connetion
	public Connection getDbConnetion(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlURL, userName, userPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("ASd");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//create statement
	public Statement createStatement(){
		if(conn == null){
			getDbConnetion();
		}
		
		try {
			sta = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sta;
	}
	
	//create PreparedStatement
	public PreparedStatement preparedStatement(String sql){
		if(conn == null){
			getDbConnetion();
		}
		
		try {
			preSta = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preSta;
	}
	
	//update
	//error -1	ok ;	effect rows	number
	public int update(String sql) {
		int effectRows = 0;
		if(sta == null){
			createStatement();
		}
		try {
			effectRows = sta.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return effectRows;
	}
	
	//count
	public int getOneInt(String sql){
		if(sta == null){
			createStatement();
		}
		int count = 0;
		try {
			res = sta.executeQuery(sql);
			res.next();
			count = res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeCon();
		return count;
	}
	
	//query
	public ResultSet queryResultSet(String sql){
		if(sta == null){
			createStatement();
		}
		try {
			res =  sta.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//closet
	public boolean closeCon(){
		try {
		if(res != null){
				res.close();
				res = null;
			}
			if(sta != null){
				sta.close();
				sta = null;
			}
			if(preSta != null){
				preSta.close();
				preSta = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
