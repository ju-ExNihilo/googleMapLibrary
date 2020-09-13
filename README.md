<pre>
<code>
For implementation :

<div style="background: black;color:white">
<pre>
<code>
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
  dependencies {
	        implementation 'com.github.ju-ExNihilo:googleMapLibrary:0.1.1'
	}
</code>
</pre>
</div>

#Declaration : 

need two argument "LyfecycleOwner" and "Google Map Api Key".

<pre style="background: black;color:white">
<code>
GooglePlaceRepository googlePlaceRepository = new GooglePlaceRepository(this, "your_google_map_api_key");
</code>
</pre>

Methode : 

1) Search methode by type :

need 4 arguments : 
	-String location;
	-int radius;
	-String type; // <a href="https://developers.google.com/places/web-service/supported_types">List type</a>
	
<pre class="prettyprint">
<code class="language-java">
```googlePlaceRepository.getPlace("43.120541,6.128639", 5000, "restaurant", "none").observe(this, finalPlaces -> {
                    for (FinalPlace finalPlace : finalPlaces){
                        Log.i("DEBUGGG", finalPlace.getName());
                    }
});```
</code>
</pre>

2) Search methode by input :

need 4 arguments : 
	-String input;
	-String location;
	-int radius;
	-String type; // <a href="https://developers.google.com/places/web-service/autocomplete#place_types">List type</a>
	-String defaultUrlPicture
	
<pre class="prettyprint">
<code class="language-java">
googlePlaceRepository.getPlaceFromAutoComplete("pizza", "43.120541,6.128639", 5000, "establishment", "none")
		     .observe(this, finalPlaces -> {
			    for (FinalPlace finalPlace : finalPlaces){
				Log.i("DEBUGGG", finalPlace.getName());
			    }
});
</code>
</pre>

3) Search by id :

need 2 arguments : 
	-String placeId;
	-String defaultUrlPicture
	
<pre class="prettyprint">
<code class="language-java">
googlePlaceRepository.getPlaceDetailsInfoFromId("SomeId", "none").observe(this, finalPlace -> {
            Log.i("DEBUGGG", finalPlace.getName());
        });
</code>
</pre>
</code>
</pre>	
	
