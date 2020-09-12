package fr.juju.googlemaplibrary.model.nearbysearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearbySearchPlaceResult {

    @SerializedName("place_id")
    @Expose
    private String placeId;

    public String getPlaceId() {
        return placeId;
    }

}
