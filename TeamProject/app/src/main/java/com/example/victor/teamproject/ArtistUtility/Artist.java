package com.example.victor.teamproject.ArtistUtility;

import java.util.Date;

/**
 * Created by Anders on 08-10-2015.
 */
public class Artist {

    private String name;
    private String description;
    private String vidURL;
    private String picURL;
    private String concertDate;

    public Artist(String artistName, String artistDiscribtion, String dateOfConcert, String videoURL, String pictureURL){

        name = artistName;
        description = artistDiscribtion;
        concertDate = dateOfConcert;
        vidURL = videoURL;
        picURL = pictureURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getConcertDate() {
        return concertDate;
    }

    public String getPicURL() {
        return picURL;
    }

    public String getVidURL() {
        return vidURL;
    }
}
