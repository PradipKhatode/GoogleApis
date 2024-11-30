package resources;

public enum APIResources {

    AddPlacesAPI("maps/api/place/add/json");

    private String resource;

    APIResources(String resource) {
        this.resource=resource;
    }

    public String getResource(){
        return resource;
    }
}
