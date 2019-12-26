package movieappshkolo.com.database;



import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;

import androidx.room.RoomDatabase;

import movieappshkolo.com.datamodel.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "MovieDatabase.db";
    private static MovieDatabase ourInstance;
    private static final Object SYNC_LOCK = new Object();

    public static MovieDatabase getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (SYNC_LOCK) {
                //ANOTHER CHECK TO SEE IF THE DATABASE IS STILL NULL
                if (ourInstance == null) {
                    ourInstance = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return ourInstance;
    }
}
