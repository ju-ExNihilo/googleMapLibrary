package fr.juju.googlemaplibrary.model.geocode;

public class GeocodePlace {

    private Double lat;
    private Double lng;

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

    @Override
    public String toString() {
        return "GeocodePlace{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
