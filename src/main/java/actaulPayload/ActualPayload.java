package actaulPayload;

import java.util.ArrayList;

import pojo.addPlace.AddPlacePayload;
import pojo.addPlace.Location;

public class ActualPayload {

	public AddPlacePayload AddPlace( String name, String address, String language) {
		AddPlacePayload addPlacePayload=new AddPlacePayload();
		
		Location location=new Location();
		location.setLat(-20.38598);
		location.setLng(78.54);
		addPlacePayload.setLocation(location);
		
		addPlacePayload.setAccuracy(50);
		addPlacePayload.setAddress(address);
		addPlacePayload.setName(name);
		addPlacePayload.setLanguage(language);
		addPlacePayload.setPhone_number("7846546452");
		addPlacePayload.setWebsite("www.coolmonks.com/");
		
		ArrayList<String> typesList=new ArrayList<String>();
		typesList.add("Shoes");
		typesList.add("Goggles");
		typesList.add("Watches");
		
		addPlacePayload.setTypes(typesList);
		
		return addPlacePayload;
		
	}
}
