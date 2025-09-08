package api.models.InfoService;

public class TerminalsData {
    private String id;
    private String name;
    private String streetType;
    private String street;
    private String house;
    private Double atmLatitude;
    private Double atmLongitude;
    private String metroStation;

    public TerminalsData(){}

    public TerminalsData(String id, String name, String streetType, String street,
                         String house, Double atmLatitude, Double atmLongitude,
                         String metroStation) {
        this.id = id;
        this.name = name;
        this.streetType = streetType;
        this.street = street;
        this.house = house;
        this.atmLatitude = atmLatitude;
        this.atmLongitude = atmLongitude;
        this.metroStation = metroStation;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreetType() {
        return streetType;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public Double getAtmLatitude() {
        return atmLatitude;
    }

    public Double getAtmLongitude() {
        return atmLongitude;
    }

    public String getMetroStation() {
        return metroStation;
    }

}