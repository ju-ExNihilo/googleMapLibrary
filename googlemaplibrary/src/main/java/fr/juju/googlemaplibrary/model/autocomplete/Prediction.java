package fr.juju.googlemaplibrary.model.autocomplete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prediction {

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("place_id")
    @Expose
    private String placeId;


    public Prediction() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
