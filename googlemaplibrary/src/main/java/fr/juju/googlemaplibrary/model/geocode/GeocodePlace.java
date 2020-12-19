package fr.juju.googlemaplibrary.model.geocode;

public class GeocodePlace {

    private Double lat;
    private Double lng;
    private String placeId;

    public GeocodePlace() {}

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "GeocodePlace{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", placeId='" + placeId + '\'' +
                '}';
    }
}
