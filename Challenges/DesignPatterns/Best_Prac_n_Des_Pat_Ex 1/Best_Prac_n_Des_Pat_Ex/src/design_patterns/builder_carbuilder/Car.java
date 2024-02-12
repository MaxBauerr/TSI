package design_patterns.builder_carbuilder;

public class Car {
    private String make;
    private String model;
    private int year;
    private float engSize;
    private String fuelType;
    private int seats;

    public String getMake() {return make;}
    public void setMake(String make) {this.make = make;}

    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}

    public String getFuelType() {return fuelType;}
    public void setFuelType(String fuelType) {this.fuelType = fuelType;}

    public int getYear() {return year;}
    public void setYear(int year) {this.year = year;}

    public int getSeats() {return seats;}
    public void setSeats(int seats) {this.seats = seats;}

    public float getEngSize() {return engSize;}
    public void setEngSize(float engSize) {this.engSize = engSize;}

    public void getInfo() {
        String toPrint = "MAKE: " + this.make;
        toPrint += "\nMODEL: " + this.model;
        toPrint += "\nYEAR: " + this.year;
        toPrint += "\nENGINE SIZE: " + this.engSize;
        toPrint += "\nFUEL: " + this.fuelType;
        toPrint += "\nSEATS: " + this.seats;
        System.out.println(toPrint);
    }
}

