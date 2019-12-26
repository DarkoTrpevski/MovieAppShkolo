package movieappshkolo.com;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import movieappshkolo.com.datamodel.Movie;


public class DetailActivity extends AppCompatActivity {

    ImageView imageViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageViewDetails = findViewById(R.id.movie_image_details);
        initDetails();

    }

    //GETS THE PARCELABLE EXTRA AND ASSIGNS IT TO A VIEW
    public void initDetails(){
        Intent intent = getIntent();
        Movie currentMovie = intent.getParcelableExtra(MainActivity.CURRENT_MOVIE);
        String image;
        if (currentMovie != null) {
            image = currentMovie.getImageUrl();
            Glide.with(this).load(image).into(imageViewDetails);

            String movieName = currentMovie.getMovieName();
            String movieYear = currentMovie.getRelease_date();

            if (getSupportActionBar() != null){
                getSupportActionBar().setTitle(movieName + " " + movieYear);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }
}