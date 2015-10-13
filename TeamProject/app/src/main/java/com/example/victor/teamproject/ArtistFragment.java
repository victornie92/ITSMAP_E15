package com.example.victor.teamproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.teamproject.ArtistUtility.Artist;
import com.example.victor.teamproject.DatabaseUtility.DatabaseHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by Anders on 10-10-2015.
 */
public class ArtistFragment extends Fragment {

    private Artist currentArtist;
    private TextView artistName, concertDate, describtion;
    private Button likeBtn;
    private ImageView artistPicture;

    private ArtistInterface selectorInterface;
    private DatabaseHelper dbHelper;

    public ArtistFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artist,container,false);

        artistName = (TextView) view.findViewById(R.id.artist_name);
        concertDate = (TextView) view.findViewById(R.id.date);
        describtion = (TextView) view.findViewById(R.id.describtion);

        artistPicture = (ImageView) view.findViewById(R.id.artist_image);

        updateArtist();


        dbHelper = new DatabaseHelper(getContext(),"ArtistDataBase",4);

        likeBtn = (Button) view.findViewById(R.id.like);

        likeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues dbValues = new ContentValues();
                dbValues.put("artistname",currentArtist.getName());
                dbValues.put("date",currentArtist.getConcertDate());
                db.insert("likedartists",null,dbValues);
                Toast.makeText(getActivity(), "Liked " + currentArtist.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    public void setArtist(Artist artist){
        if(artistName != null && concertDate != null && describtion != null && artistPicture !=null){
            artistName.setText(artist.getName());
            concertDate.setText(artist.getConcertDate());
            describtion.setText(artist.getDescription());

            new ImageTask(artistPicture).execute(artist.getPicURL());
            currentArtist = artist;
        }
    }

    private void updateArtist() {
        if(selectorInterface!=null)
            setArtist(selectorInterface.getCurrentArtist());
    }


    private class ImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bitmapImage;

        public ImageTask(ImageView image){
            bitmapImage = image;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String URL = params[0];
            Bitmap picture = null;
            try{
                InputStream inputStream = new java.net.URL(URL).openStream();
                picture = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return picture;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            bitmapImage.setImageBitmap(bitmap);
        }
    }

}
