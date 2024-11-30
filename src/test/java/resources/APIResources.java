package resources;

public enum APIResources {

    AddPlacesAPI("maps/api/place/add/json"),
    getPlaceAPI("maps/api/place/get/json");

    private String resource;

    APIResources(String resource) {
        this.resource=resource;
    }

    public String getResource(){
        return resource;
    }
}
