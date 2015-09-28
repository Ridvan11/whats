package tr.name.com.ridvan;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import NET.webserviceX.www.GlobalWeatherSoapProxy;
import se.walkercrou.places.exception.NoResultsFoundException;
import tr.name.com.db.Driver;
import yazokulu.geocoder.GeoCoderUtil;

public class MainServlet extends HttpServlet{
	String city_;
	String town_;
	String country_;
	double lati=0d;
	double lng=0d;
	private static String query;
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 city_=req.getParameter("city");
			town_=req.getParameter("town");
			country_=req.getParameter("country");
			setQuery(town_+city_+country_);
			 Pins nesne=new Pins();
			 Driver db=new Driver();
			 
			try {
				Map<String, Double> coordinates = GeoCoderUtil.getCoordinates(town_ + " " + city_ + " " + country_);
				 if (coordinates == null) {
				 return;   
				 	}   
				 
				 
				 lati=coordinates.get("lat");
				 lng=coordinates.get("lng");
				 GlobalWeatherSoapProxy gws = new GlobalWeatherSoapProxy();
					
				 
				 try{
					String response = gws.getWeather(city_, country_);
					Document result = nesne.loadXMLFromString(response);
					System.out.println("Temperature:" + 
					result.getElementsByTagName("Temperature").item(0).getTextContent());
					
				 
				req.setAttribute("weather", "Temperature:" + 
						result.getElementsByTagName("Temperature").item(0).getTextContent());
				 //RequestDispatcher view = req.getRequestDispatcher("main.jsp");
		        // view.forward(req, resp); 
				
				
				 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				if(db.isExist(getQuery())==false){
			
			
			
			  
			 try {
				 
				 
				nesne.getRestaurant_(lati, lng);
				nesne.getHospital_(lati, lng);
				db.createStudent(getQuery(), nesne.restaurant, nesne.hospital, nesne.hospital, nesne.hospital, nesne.hospital);
				
				req.setAttribute("resul", nesne.restaurant);
				req.setAttribute("resuClub", nesne.hospital);
				
				
				RequestDispatcher view = req.getRequestDispatcher("main.jsp");
		         view.forward(req, resp); 
				
				
			} catch (ClassNotFoundException | SQLException  |NoResultsFoundException e2) {
				// TODO Auto-generated catch block
				nesne.restaurant="bulamadým";
				nesne.hospital="bulamadým";
				e2.getCause();			}
			 
			 try {
					db.resulQuery();
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			 
			//RequestDispatcher view=req.getRequestDispatcher("main.jsp");
			//view.forward(req, resp);
			
			return;
				}
				else {
					//req.setAttribute("resul", db.resulQuery().club);
					Pins pins = db.resulQuery();
					
					 
					req.setAttribute("resul", pins.getRestaurant());
					req.setAttribute("resuClub", pins.getClub());
					RequestDispatcher view = req.getRequestDispatcher("main.jsp");
			         view.forward(req, resp);
			         return;
					
					
					
				}
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				nesne.restaurant="bulamadým";
				nesne.hospital="bulamadým";
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				nesne.restaurant="bulamadým";
				nesne.hospital="bulamadým";
			}

			
			
			}
	public static String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	 
	}

