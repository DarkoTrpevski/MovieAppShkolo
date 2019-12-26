package movieappshkolo.com.database;

import android.content.Context;

import java.util.List;

import movieappshkolo.com.datamodel.Movie;

public class MovieRepository {

    private static MovieRepository ourInstance;
    public List<Movie> movies;



    public static MovieRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new MovieRepository(context);
        }
        return ourInstance;
    }

    private MovieRepository(Context context) {
    }
}
