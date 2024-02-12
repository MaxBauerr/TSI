package design_patterns.builder_carbuilder;

import design_patterns.builder_example.Computer;

abstract public class CarBuilder {
    protected Car car = new Car();
    public abstract void buildMake();
    public abstract void buildModel();
    public abstract void buildYear();
    public abstract void buildFuel();
    public abstract void buildSeat();
    public abstract void buildEngSize();
    public abstract Computer getResult();
}
