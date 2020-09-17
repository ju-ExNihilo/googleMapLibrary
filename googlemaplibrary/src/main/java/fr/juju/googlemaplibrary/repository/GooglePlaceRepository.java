package fr.juju.googlemaplibrary.repository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import fr.juju.googlemaplibrary.model.FinalPlace;
import fr.juju.googlemaplibrary.model.autocomplete.PlaceSearch;
import fr.juju.googlemaplibrary.model.autocomplete.Prediction;
import fr.juju.googlemaplibrary.model.nearbysearch.NearbySearchPlace;
import fr.juju.googlemaplibrary.model.nearbysearch.NearbySearchPlaceResult;
import fr.juju.googlemaplibrary.model.place.Place;
import fr.juju.googlemaplibrary.networking.GooglePlaceService;
import fr.juju.googlemaplibrary.networking.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class GooglePlaceRepository {

    private GooglePlaceService googlePlaceService = RetrofitService.createService(GooglePlaceService.class);
    private static final String FIELD = "name,rating,formatted_phone_number,photo,address_component,url,geometry/location,opening_hours,place_id,website,price_level,vicinity";
    private final MutableLiveData<NearbySearchPlace> finalPlaces = new MutableLiveData<>();
    private final MutableLiveData<FinalPlace> finalPlace = new MutableLiveData<>();
    private final MutableLiveData<PlaceSearch> placeSearch = new MutableLiveData<>();
    private FinalPlace finalGlobalPlace;
    private final List<FinalPlace> finalPlacesArrayList = new ArrayList<>();
    private LifecycleOwner owner;
    private String key;

    public GooglePlaceRepository(LifecycleOwner owner, String key) {
        this.owner = owner;
        this.key = key;
    }

    /** **** Get restaurant from retrofit  **** **/
    private MutableLiveData<NearbySearchPlace> getPlacesFromRetrofit(String location, int radius, String type){
        googlePlaceService.getRestaurants(location, radius,type, key).enqueue(new Callback<NearbySearchPlace>() {
            @Override
            public void onResponse(Call<NearbySearchPlace> call, Response<NearbySearchPlace> response) {
                if (response.isSuccessful()){
                    finalPlaces.setValue(response.body());
                }else {finalPlace.setValue(null);}
            }
            @Override
            public void onFailure(Call<NearbySearchPlace> call, Throwable t) {finalPlace.setValue(null); }
        });
        return finalPlaces;
    }

    /** **** Get restaurant from auto complete search  **** **/
    private MutableLiveData<PlaceSearch> getPrivatePlaceFromAutoComplete(String input, String location, int radius, String type){
        finalPlacesArrayList.clear();
        googlePlaceService.getPlaceAutoComplete(input,type,location, radius,"",key, "1234567890").enqueue(new Callback<PlaceSearch>() {
            @Override
            public void onResponse(Call<PlaceSearch> call, Response<PlaceSearch> response) {
                if (response.isSuccessful()){
                    placeSearch.setValue(response.body());
                }else {placeSearch.setValue(null);}
            }
            @Override
            public void onFailure(Call<PlaceSearch> call, Throwable t) {placeSearch.setValue(null);}
        });
        return placeSearch;
    }

    public MutableLiveData<List<FinalPlace>> getPlaceFromAutoComplete(String input, String location, int radius, String type, String defaultPictureUrl){
        MutableLiveData<List<FinalPlace>> placeFromSearch = new MutableLiveData<>();
        getPrivatePlaceFromAutoComplete(input, location, radius, type).observe(owner, placeSearch -> {
            if (!placeSearch.getPredictions().isEmpty()) {
                final int[] c = {0};
                for (Prediction prediction : placeSearch.getPredictions()) {
                    final int n = placeSearch.getPredictions().size();
                    googlePlaceService.getPlaceInfo(prediction.getPlaceId(), FIELD, key).enqueue(new Callback<Place>() {
                        @Override
                        public void onResponse(Call<Place> call, Response<Place> response) {
                            if (response.isSuccessful()) {
                                addPlace(response.body(), defaultPictureUrl);
                                finalPlacesArrayList.add(finalGlobalPlace);
                                c[0]++;
                                if (c[0] == n) {
                                    placeFromSearch.setValue(finalPlacesArrayList);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Place> call, Throwable t) {
                            placeFromSearch.setValue(null);
                        }
                    });
                }
            }else{
                placeFromSearch.setValue(null);
            }
        });
        return placeFromSearch;
    }

    public MutableLiveData<FinalPlace> getPlaceDetailsInfoFromId(String placeId, String defaultPictureUrl){
        MutableLiveData<FinalPlace> restaurantDetailsInfo = new MutableLiveData<>();
        googlePlaceService.getPlaceInfo(placeId, FIELD, key).enqueue(new Callback<Place>() {
            @Override
            public void onResponse(Call<Place> call, Response<Place> response) {
                if (response.isSuccessful()){
                    addPlace(response.body(), defaultPictureUrl);
                    restaurantDetailsInfo.setValue(finalGlobalPlace);
                }else {
                    restaurantDetailsInfo.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Place> call, Throwable t) {restaurantDetailsInfo.setValue(null);}
        });
        return restaurantDetailsInfo;
    }

    public MutableLiveData<List<FinalPlace>> getPlace(String location, int radius, String type,String defaultPictureUrl){
        MutableLiveData<List<FinalPlace>> finalPlacesList = new MutableLiveData<>();
        finalPlacesArrayList.clear();
        getPlacesFromRetrofit(location, radius, type).observe(owner, nearbySearchPlace -> {
            final int[] c = {0};
            for (NearbySearchPlaceResult restaurants1 : nearbySearchPlace.getNearbySearchPlaceResults()){
                int n = nearbySearchPlace.getNearbySearchPlaceResults().size();
                googlePlaceService.getPlaceInfo(restaurants1.getPlaceId(), FIELD, key).enqueue(new Callback<Place>() {
                    @Override
                    public void onResponse(Call<Place> call, Response<Place> response) {
                        addPlace(response.body(), defaultPictureUrl);
                        finalPlacesArrayList.add(finalGlobalPlace);
                        c[0]++;
                        if (c[0] == n){ finalPlacesList.setValue(finalPlacesArrayList);}
                    }
                    @Override
                    public void onFailure(Call<Place> call, Throwable t) {}
                });
            }
        });
        return finalPlacesList;
    }



    private void addPlace(Place place, String defaultPictureUrl){
        finalGlobalPlace = new FinalPlace();

        finalGlobalPlace.setPlaceId(place.getPlaceResult().getPlaceId());
        if (place.getPlaceResult().getPhotos() != null){
            finalGlobalPlace.setPhoto("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" +
                    place.getPlaceResult().getPhotos().get(0).getPhotoReference()+ "&key=" + key  );

        }else {
            finalGlobalPlace.setPhoto(defaultPictureUrl);
        }

        finalGlobalPlace.setName(place.getPlaceResult().getName());
        finalGlobalPlace.setLatitude(place.getPlaceResult().getGeometry().getLocation().getLat());
        finalGlobalPlace.setLongitude(place.getPlaceResult().getGeometry().getLocation().getLng());

        if (place.getPlaceResult().getVicinity() != null){
            finalGlobalPlace.setAddress(place.getPlaceResult().getVicinity());
        }else {
            finalGlobalPlace.setAddress("address dont set");
        }

        if (place.getPlaceResult().getOpeningHours() != null){
            finalGlobalPlace.setOpeningHours(place.getPlaceResult().getOpeningHours().getWeekdayText());
        }

        if (place.getPlaceResult().getFormattedPhoneNumber() != null){
            finalGlobalPlace.setPhone(place.getPlaceResult().getFormattedPhoneNumber());
        }else {
            finalGlobalPlace.setPhone("Phone dont set");
        }

        if (place.getPlaceResult().getRating() != null){
            finalGlobalPlace.setRating(place.getPlaceResult().getRating());
        }else {
            finalGlobalPlace.setRating(0);
        }

        if (place.getPlaceResult().getWebsite() != null){
            finalGlobalPlace.setWebsite(place.getPlaceResult().getWebsite());
        }else {
            finalGlobalPlace.setWebsite("site dont set");
        }

    }
}
