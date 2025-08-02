package api.models;

public class Atm {
    private String id;
    private String name;
    private String streetType;
    private String street;
    private String house;
    private double atmLatitude;
    private double atmLongitude;
    private String metroStation;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public double getAtmLatitude() {
        return atmLatitude;
    }

    public void setAtmLatitude(double atmLatitude) {
        this.atmLatitude = atmLatitude;
    }

    public double getAtmLongitude() {
        return atmLongitude;
    }

    public void setAtmLongitude(double atmLongitude) {
        this.atmLongitude = atmLongitude;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }
}