package com.example.victor.teamproject.ArtistUtility;

/**
 * Created by Anders on 08-10-2015.
 */
public class Artist {

    private String name;
    private String description;
    private String preview;
    private String vidURL;
    private String picURL;
    private String concertDate;

    public Artist(String artistName, String artistShortDescription, String artistLongDescription, String dateOfConcert, String videoURL, String pictureURL){

        name = artistName;
        preview = artistShortDescription;
        description = artistLongDescription;
        concertDate = dateOfConcert;
        vidURL = videoURL;
        picURL = pictureURL;
    }

    public String getName() {
        return name;
    }

    public String getPreview() {
        return preview;
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
