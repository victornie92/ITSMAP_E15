package com.example.victor.teamproject.ArtistUtility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.victor.teamproject.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Build like FragmentsArnieMovies MovieAdapter
 */
public class ArtistAdapter extends BaseAdapter {

    Context context;
    ArrayList<Artist> artists;
    Artist artist = null;

    public ArtistAdapter(Context c,ArrayList<Artist> artistList){
        artists = artistList;
        context = c;
    }

    @Override
    public int getCount() {
        if(artists!=null)
            return artists.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        if(artists!=null)
            return artists.get(position);
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Make an inflater so we can inflate the list view.
        if(convertView == null){
            LayoutInflater artistInflator = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = artistInflator.inflate(R.layout.artistlist_artist_element, null);
        }

        //Fill with our information for artists
        artist = artists.get(position);
        if(artist!=null){
            TextView name = (TextView) convertView.findViewById(R.id.artist);
            name.setText(artist.getName());

            TextView describtion = (TextView) convertView.findViewById(R.id.preview);
            describtion.setText(artist.getPreview());

            TextView concertDate = (TextView) convertView.findViewById(R.id.concert);
            concertDate.setText(artist.getConcertDate());

            //Start asynctask to get picture from internet for us
            new ImageTask((ImageView) convertView.findViewById(R.id.artist_image)).execute(artist.getPicURL());
        }
        return convertView;
    }

    //Get image from the internet through an AsyncTask
    private class ImageTask extends AsyncTask<String, Void, Bitmap>{
        ImageView bitmapImage;

        public ImageTask(ImageView image){
            bitmapImage = image;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String URL = params[0];
            Bitmap picture = null;
            //Try to get the picture from provided URL
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

        //Set the picture to received bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            bitmapImage.setImageBitmap(bitmap);
        }
    }
}
