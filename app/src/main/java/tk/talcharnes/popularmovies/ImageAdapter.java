package tk.talcharnes.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Tal on 2/24/2016.
 */
public class ImageAdapter extends ArrayAdapter<MovieModel> {
    private String[] desc = {
            "http://www.jqueryscript.net/images/jQuery-Ajax-Loading-Overlay-with-Loading-Text-Spinner-Plugin.jpg",
            "http://www.jqueryscript.net/images/jQuery-Ajax-Loading-Overlay-with-Loading-Text-Spinner-Plugin.jpg",
            "http://www.jqueryscript.net/images/jQuery-Ajax-Loading-Overlay-with-Loading-Text-Spinner-Plugin.jpg",
            "http://www.jqueryscript.net/images/jQuery-Ajax-Loading-Overlay-with-Loading-Text-Spinner-Plugin.jpg",
            "http://www.jqueryscript.net/images/jQuery-Ajax-Loading-Overlay-with-Loading-Text-Spinner-Plugin.jpg",
            "http://www.jqueryscript.net/images/jQuery-Ajax-Loading-Overlay-with-Loading-Text-Spinner-Plugin.jpg",
            "http://www.jqueryscript.net/images/jQuery-Ajax-Loading-Overlay-with-Loading-Text-Spinner-Plugin.jpg",
            "http://www.jqueryscript.net/images/jQuery-Ajax-Loading-Overlay-with-Loading-Text-Spinner-Plugin.jpg",
            "http://www.jqueryscript.net/images/jQuery-Ajax-Loading-Overlay-with-Loading-Text-Spinner-Plugin.jpg",
            "http://www.jqueryscript.net/images/jQuery-Ajax-Loading-Overlay-with-Loading-Text-Spinner-Plugin.jpg"
    };
    private String[] imageArray = getAsc();
    private Context mContext;
    //private String[] asc = new String[PostersFragment.getMovieModelListLength()];
    private String[] asc = {};

    public ImageAdapter(Context context, List<MovieModel> objects) {
        super(context, 0, objects);
    }


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieModel movieModel = getItem(position);
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.single_grid_item,parent,false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.posterImage);
        Picasso.with(getContext()).load(movieModel.getPoster_path())
                .placeholder(R.drawable.sample_0)
                .into(imageView);
        return rootView;
    }



    public String[] getAsc() {
        return asc;
    }

}
