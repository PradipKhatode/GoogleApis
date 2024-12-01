package stepDefinations;

import io.cucumber.java.Before;
import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeDeletePlaceAPI() throws IOException {
        // This Method will give the placeID if the AddPlaceID testcase is not Executed
        // the PlaceID code should only execute if the Place_Id is null
        if (StepDefination.placeId==null) {
            StepDefination stepDefination=new StepDefination();
            stepDefination.add_place_payload_with("Rahul", "English", "NAshik");
            stepDefination.user_calls_with_http_request("AddPlacesAPI", "POST");
            stepDefination.verify_the_place_id_create_maps_to_using("Rahul", "getPlaceAPI");
        }
    }
}
