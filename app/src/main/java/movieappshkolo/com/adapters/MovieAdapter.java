package movieappshkolo.com.adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import movieappshkolo.com.R;
import movieappshkolo.com.datamodel.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;

//--------------------------------------------------------------------------------------------------
    //CUSTOM LISTENER
    private Listener listener;
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface Listener{
        void onMovieClick(int position);
    }

//--------------------------------------------------------------------------------------------------

    //ADAPTER CONSTRUCTOR
    public MovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflateView = layoutInflater.inflate(R.layout.single_card_item,parent,false);

        return new MovieViewHolder(inflateView,listener);
    }

//--------------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
//        Movie currentMovie = movieList.get(position);
        Movie currentMovie = movieList.get(holder.getAdapterPosition());
        holder.textViewMovieName.setText(currentMovie.getMovieName());
        holder.textViewMovieYear.setText(currentMovie.getRelease_date());

        String movieImageUrl = currentMovie.getImageUrl();
        Glide.with(context).load(movieImageUrl).into(holder.movieThumbnail);

        //CREATE A DIALOG FOR THE REMOVE BUTTON BEFORE REMOVING THE SELECTED MOVIE
        holder.movieRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(context);
                myAlertBuilder.setTitle(R.string.title);
                myAlertBuilder.setMessage(R.string.message);
                myAlertBuilder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        movieList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                });
                myAlertBuilder.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                myAlertBuilder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
//--------------------------------------------------------------------------------------------------

    //CUSTOM VIEW HOLDER CLASS
    static class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView textViewMovieName,textViewMovieYear;
        ImageView movieThumbnail,movieRemove;
        MovieViewHolder(@NonNull View itemView, final Listener listener) {
            super(itemView);
            textViewMovieName = itemView.findViewById(R.id.name_movieName);
            textViewMovieYear = itemView.findViewById(R.id.year_movieYear);
            movieThumbnail = itemView.findViewById(R.id.movie_thumbnail);
            movieRemove = itemView.findViewById(R.id.removeBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onMovieClick(position);
                        }
                    }
                }
            });
        }
    }


//--------------------------------------------------------------------------------------------------
}