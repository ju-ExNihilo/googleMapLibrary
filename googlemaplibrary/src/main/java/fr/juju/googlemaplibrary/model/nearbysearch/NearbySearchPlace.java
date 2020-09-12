package fr.juju.googlemaplibrary.model.nearbysearch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearbySearchPlace {

    @SerializedName("results")
    @Expose
    private List<NearbySearchPlaceResult> nearbySearchPlaceResults = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<NearbySearchPlaceResult> getNearbySearchPlaceResults() {
        return nearbySearchPlaceResults;
    }

    public void setNearbySearchPlaceResults(List<NearbySearchPlaceResult> nearbySearchPlaceResults) {
        this.nearbySearchPlaceResults = nearbySearchPlaceResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
