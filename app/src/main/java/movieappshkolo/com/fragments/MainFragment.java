package movieappshkolo.com.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import movieappshkolo.com.DetailActivity;
import movieappshkolo.com.MainActivity;
import movieappshkolo.com.R;
import movieappshkolo.com.adapters.MovieAdapter;
import movieappshkolo.com.datamodel.Movie;
import movieappshkolo.com.datamodel.MovieRepo;

public class MainFragment extends Fragment {
    private List<Movie> movieList;
    private View inflateView;
    private MovieAdapter movieAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflateView = inflater.inflate(R.layout.fragment_main, container, false);

        checkArgumentsAndPopulateView();
        buildRecyclerView();
        return inflateView;
    }

//--------------------------------------------------------------------------------------------------

    private void checkArgumentsAndPopulateView(){
        MovieRepo movieRepo = new MovieRepo();
        movieList = new ArrayList<>();

        if (getArguments() != null){
            String movieGenre = getArguments().getString(MainActivity.GENRE_KEY);
            for (int i = 0; i < movieRepo.createMovies().size(); i ++){
                Movie currentMovie = movieRepo.createMovies().get(i);
                if (currentMovie.getMovieGenre().equals(movieGenre)){
                    movieList.add(currentMovie);
                }
            }
        }
    }

    private void buildRecyclerView(){
        setHasOptionsMenu(true);
        movieAdapter = new MovieAdapter(movieList, getContext());
        RecyclerView recyclerView = inflateView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(movieAdapter);

        movieAdapter.setListener(new MovieAdapter.Listener() {
            @Override
            public void onMovieClick(int position) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(MainActivity.CURRENT_MOVIE,movieList.get(position));
                startActivity(intent);
            }
        });
    }

//--------------------------------------------------------------------------------------------------
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if (getActivity() != null){
            MenuInflater inflater1 = getActivity().getMenuInflater();
            inflater1.inflate(R.menu.menu,menu);
        }

    }
//--------------------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.list_menu) {
            Collections.sort(movieList, new Comparator<Movie>() {
                @Override
                public int compare(Movie movie1, Movie movie2) {
                    return movie1.getMovieName().compareTo(movie2.getMovieName());
                }
            });
            movieAdapter.notifyDataSetChanged();
        }
        return true;
    }
//--------------------------------------------------------------------------------------------------
}
