package dk.itsmap.e15.grp4.model;

//Inspiration af Kaspers arnie exercise example
/**
 * Created by Peter-PC on 08-10-2015.
 */
public class FoodLocation {
    private double latitude;
    private double longitude;
    private String name;
    private String description;

    public FoodLocation(double lati, double longi, String nam, String desc)
    {
        latitude = lati;
        longitude = longi;
        name = nam;
        description = desc;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude ()
    {
        return longitude;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
}
