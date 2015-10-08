package com.example.victor.teamproject.artist.models;


import java.util.Date;

public class CompleteArtist {

    private String name, dicribtionLong, picURL, vidURL;
    private Date concertData;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDicribtionLong(String dicribtionLong) {
        this.dicribtionLong = dicribtionLong;
    }

    public String getDicribtionLong() {
        return dicribtionLong;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setVidURL(String vidURL) {
        this.vidURL = vidURL;
    }

    public String getVidURL() {
        return vidURL;
    }

    public void setConcertData(Date concertData) {
        this.concertData = concertData;
    }

    public Date getConcertData() {
        return concertData;
    }

}
