package tr.name.com.ridvan;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;
import se.walkercrou.places.exception.NoResultsFoundException;
import tr.name.com.db.Driver;


public class Pins  {
	
	GooglePlaces client=new GooglePlaces("AIzaSyD47YX_CCGnO29aiYPB3g4kBcEO8PnYdlo");
	
	public String restaurant="";
	public String hotel="";
	public String club="";
	public String police="";
	public String hospital=""; 
	
	

	
	public GooglePlaces getClient() {
		return client;
	}





	public void setClient(GooglePlaces client) {
		this.client = client;
	}





	public String getRestaurant() {
		return restaurant;
	}





	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}





	public String getHotel() {
		return hotel;
	}





	public void setHotel(String hotel) {
		this.hotel = hotel;
	}





	public String getClub() {
		return club;
	}





	public void setClub(String club) {
		this.club = club;
	}





	public String getPolice() {
		return police;
	}





	public void setPolice(String police) {
		this.police = police;
	}





	public String getHospital() {
		return hospital;
	}





	public void setHospital(String hospital) {
		this.hospital = hospital;
	}





	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
	
	
	
	
	
	public void getRestaurant_(double lati,double lng) throws NoResultsFoundException{
		
	
	Param params= new Param("type=restaurant");
	List<Place> places =client.getNearbyPlaces(lati, lng, 100, 10,params);
	
	for(Place place : places) {
        //System.out.println(place.getName());
	restaurant+="<br>"+place.getName()+" "+place.getVicinity();
	
	 
	
		}
	if (restaurant.equals(""))
		restaurant="hastane bulunamadý";
	System.out.println(restaurant);
	
 
	//Driver ekle=new Driver();
	
	//ekle.createStudent(restaurant, restaurant, hotel, hotel, club);
	
	
	
	}
	
	public void getHotel_(double lati,double lng){
		
		
		Param params= new Param("type=hotel");
		List<Place> places =client.getNearbyPlaces(lati, lng, 100, 10,params);
		
		for(Place place : places) {
	        
		hotel+="<br>"+place.getName()+" "+place.getVicinity();
			}
		}
	
	public void getClub_(double lati,double lng){
		
		
		Param params= new Param("type=night_club");
		List<Place> places =client.getNearbyPlaces(lati, lng, 100, 10,params);
		
		for(Place place : places) {
	        
		club+="<br>"+place.getName()+" "+place.getVicinity();
			}
		}

	public void getPolice_(double lati,double lng){
	
	
	Param params= new Param("type=police");
	List<Place> places =client.getNearbyPlaces(lati, lng, 100, 10,params);
	
	for(Place place : places) {
        
	police+="<br>"+place.getName()+" "+place.getVicinity();
		}
	}

	public void getHospital_(double lati,double lng) throws NoResultsFoundException{
	
	
	Param params= new Param("type=hospital");
	List<Place> places =client.getNearbyPlaces(lati, lng, 100, 10,params);
	
	for(Place place : places) {
        
	hospital+="<br>"+place.getName()+" "+place.getVicinity();
	
		}
	if (hospital.equals(""))
		hospital="hastane bulunamadý";
	}
	
}


