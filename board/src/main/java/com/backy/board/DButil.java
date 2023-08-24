package com.backy.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButil {
	public static Connection getConnection(){//getConnection 은Connection 을 반환
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.18:1521:xe","userone","userone");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void rC(PreparedStatement ps, Connection con){//resourceClose
		if( ps != null ) {try {ps.close();} catch (SQLException e) {e.printStackTrace();}}
		if( con != null ) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	public static void rC(ResultSet rs, PreparedStatement ps, Connection con){
		if( rs != null ) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		rC(ps, con);//갖다쓸게 ^^
	}
}
