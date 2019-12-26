package movieappshkolo.com.datamodel;



import android.os.Parcel;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie implements Parcelable {

    //MOVIE MEMBER VARIABLES
    @NonNull
    @PrimaryKey
    private String id;
    private String movieName;
    private String release_date;
    private String imageUrl;
    private String movieGenre;

    @Ignore
    public Movie(@NonNull String id, String movieName, String release_date, String imageUrl, String movieGenre) {
        this.id = id;
        this.movieName = movieName;
        this.release_date = release_date;
        this.imageUrl = imageUrl;
        this.movieGenre = movieGenre;
    }

    public Movie(String movieName, String release_date, String imageUrl, String movieGenre) {
        this.movieName = movieName;
        this.release_date = release_date;
        this.imageUrl = imageUrl;
        this.movieGenre = movieGenre;
    }

    protected Movie(Parcel in) {
        id = in.readString();
        movieName = in.readString();
        release_date = in.readString();
        imageUrl = in.readString();
        movieGenre = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(movieName);
        parcel.writeString(release_date);
        parcel.writeString(imageUrl);
        parcel.writeString(movieGenre);
    }
}