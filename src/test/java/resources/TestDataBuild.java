package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(String name,String language,String address){
        // Providing the API data using Getter and setter Pojo classes
        AddPlace p=new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(name);

        List<String> myList=new ArrayList();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);
        Location l=new Location();
        l.setLat(57.23);
        l.setLng(56.89);
        p.setLocation(l);
        return p;
    }
    public String deletePlacePayload(String place_id){
        return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}\r\n";
    }
}
