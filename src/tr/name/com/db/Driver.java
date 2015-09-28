package tr.name.com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tr.name.com.ridvan.MainServlet;
import tr.name.com.ridvan.Pins;


public class Driver {
	
	public String rests;
		private Connection connection = null;
		
		public Connection getConnection() throws SQLException, ClassNotFoundException { 
			Class.forName("org.postgresql.Driver");
			if (connection == null  || connection.isClosed()) { 
				 connection = DriverManager.getConnection(
						   "jdbc:postgresql://localhost:5432/postgres","postgres", "123456");
			}
			return connection;
		}
		
		public void createStudent(String query, String rest, String hot, String hos,String pol, String club) throws SQLException, ClassNotFoundException  { 
			Connection connection = this.getConnection();
			String sql = "INSERT INTO results (query, rest,hot, hos,pol, club) VALUES (?, ?, ?, ? ,? ,?)";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, query);
			pst.setString(2, rest);
			pst.setString(3, hot);
			pst.setString(4, hos);
			pst.setString(5, pol);
			pst.setString(6, club);
			pst.executeUpdate();
			
		}

		//veri tasbanýndan verilerin deðiþkenler yardýmý ile gösterildiði kýsým
			
		public Pins resulQuery() throws ClassNotFoundException, SQLException{ 
			Connection connection = this.getConnection();
			String sql = "select * from  results where query=?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, MainServlet.getQuery());
		    ResultSet rs = pst.executeQuery();
		    
			Pins pi=new Pins();
			
			while (rs.next()) { 
				pi.restaurant=rs.getString("rest");
				pi.club=rs.getString("club");
				pi.hospital=rs.getString("hos");
				pi.police=rs.getString("pol");
				pi.hotel=rs.getString("hot");
				
			}
			return pi;
	}
		
		public boolean isExist(String search) throws SQLException, ClassNotFoundException
		{
			
			Connection connection = this.getConnection();
			String sql = "select * from  results where query=?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, search);
		    ResultSet rs = pst.executeQuery();
		   
			return rs.next();
		}
}

