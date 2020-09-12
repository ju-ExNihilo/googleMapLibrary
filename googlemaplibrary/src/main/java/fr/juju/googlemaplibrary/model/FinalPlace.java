package fr.juju.googlemaplibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FinalPlace implements Parcelable {

    private String placeId;
    private String name, address, phone, photo, website;
    private double latitude, longitude, rating;
    private List<String> openingHours;
    private long nbrCustomer;


    public FinalPlace() {}


    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<String> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(List<String> openingHours) {
        this.openingHours = openingHours;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getNbrCustomer() {
        return nbrCustomer;
    }

    public void setNbrCustomer(long nbrCustomer) {
        this.nbrCustomer = nbrCustomer;
    }

    @Override
    public String toString() {
        return "Resto{" +
                "placeId='" + placeId + '\n' +
                ", name='" + name + '\n' +
                ", address='" + address + '\n' +
                ", phone='" + phone + '\n' +
                ", photo='" + photo + '\n' +
                ", website='" + website + '\n' +
                ", latitude=" + latitude +'\n'+
                ", longitude=" + longitude +'\n'+
                ", rating=" + rating+'\n' +
                ", openingHours=" + openingHours+'\n' +
                ", nbrCustomer=" + nbrCustomer+'\n' +
                '}';
    }

    /** ***************************** **/
    /** ***** Parcelable Method ***** **/
    /** ***************************** **/

    protected FinalPlace(Parcel in){
        placeId = in.readString();
        name = in.readString();
        address = in.readString();
        phone = in.readString();
        photo = in.readString();
        website = in.readString();
        openingHours = in.createStringArrayList();
        latitude = in.readDouble();
        longitude = in.readDouble();
        nbrCustomer = in.readLong();
        rating = in.readDouble();
    }

    public static final Creator<FinalPlace> CREATOR = new Creator<FinalPlace>() {
        @Override
        public FinalPlace createFromParcel(Parcel in) {
            return new FinalPlace(in);
        }

        @Override
        public FinalPlace[] newArray(int size) {
            return new FinalPlace[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placeId);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(photo);
        dest.writeString(website);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeDouble(rating);
        dest.writeStringList(openingHours);
        dest.writeLong(nbrCustomer);
    }
}
