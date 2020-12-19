package fr.juju.googlemaplibrary.networking;

import fr.juju.googlemaplibrary.model.autocomplete.PlaceSearch;
import fr.juju.googlemaplibrary.model.geocode.Result;
import fr.juju.googlemaplibrary.model.nearbysearch.NearbySearchPlace;
import fr.juju.googlemaplibrary.model.place.Place;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlaceService {

    @GET("/maps/api/place/nearbysearch/json")
    Call<NearbySearchPlace> getPlaceListByType(@Query("location") String location, @Query("radius") int radius, @Query("type") String type, @Query("key") String key);

    @GET("/maps/api/place/details/json")
    Call<Place> getPlaceInfo(@Query("place_id") String placeId, @Query("fields") String fields, @Query("key") String key);

    @GET("/maps/api/geocode/json")
    Call<Result> getPlaceGeoCode(@Query("address") String address, @Query("key") String key);

    @GET("/maps/api/place/autocomplete/json")
    Call<PlaceSearch> getPlaceAutoComplete(@Query("input") String input, @Query("types") String types, @Query("location") String location,
                                           @Query("radius") int radius, @Query("strictbounds") String strictbounds, @Query("key") String key, @Query("sessiontoken") String sessiontoken);


}