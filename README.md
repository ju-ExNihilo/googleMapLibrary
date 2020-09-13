## For implementation :

``` 
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
``` 
``` 
  
  dependencies {
	        implementation 'com.github.ju-ExNihilo:googleMapLibrary:0.1.1'
	}
``` 

## Declaration : 



need two argument "LyfecycleOwner" and "Google Map Api Key".

``` 
GooglePlaceRepository googlePlaceRepository = new GooglePlaceRepository(this, "your_google_map_api_key");
``` 

## Methode : 

  
  

### 1) Search methode by type :


need 4 arguments :  

	* String location;
	* int radius;
	* String type; // <a href="https://developers.google.com/places/web-service/supported_types">List type</a>
	* String defaultUrlPicture
	

``` 
googlePlaceRepository.getPlace("43.120541,6.128639", 5000, "restaurant", "none").observe(this, finalPlaces -> {
                    for (FinalPlace finalPlace : finalPlaces){
                        Log.i("DEBUGGG", finalPlace.getName());
                    }
});
``` 


### 2) Search methode by input :


need 4 arguments :   

	* String input;
	* String location;
	* int radius;
	* String type; // <a href="https://developers.google.com/places/web-service/autocomplete#place_types">List type</a>
	* String defaultUrlPicture
	
``` 
googlePlaceRepository.getPlaceFromAutoComplete("pizza", "43.120541,6.128639", 5000, "establishment", "none")
		     .observe(this, finalPlaces -> {
			    for (FinalPlace finalPlace : finalPlaces){
				Log.i("DEBUGGG", finalPlace.getName());
			    }
});
``` 

### 3) Search by id :


need 2 arguments :   

	* String placeId;
	* String defaultUrlPicture
	
``` 
googlePlaceRepository.getPlaceDetailsInfoFromId("SomeId", "none").observe(this, finalPlace -> {
            Log.i("DEBUGGG", finalPlace.getName());
        });
``` 
	
