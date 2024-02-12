package design_patterns.builder_carbuilder;

public class CarBuilderDirector {

    public void construct(CarBuilder carBuilder) {
        carBuilder.buildMake();
        carBuilder.buildModel();
    }
}
