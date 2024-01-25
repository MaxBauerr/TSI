public class Paint {
    private String color;
    private double sizeInLitres;
    private double costPerContainer;

    // Constructor
    public Paint(String color, double sizeInLitres, double costPerContainer) {
        this.color = color;
        this.sizeInLitres = sizeInLitres;
        this.costPerContainer = costPerContainer;
    }

    // Getters
    public String getColor() {
        return color;
    }

    public double getSizeInLitres() {
        return sizeInLitres;
    }

    public double getCostPerContainer() {
        return costPerContainer;
    }

    // Setters
    public void setColor(String color) {
        this.color = color;
    }

    public void setSizeInLitres(double sizeInLitres) {
        this.sizeInLitres = sizeInLitres;
    }

    public void setCostPerContainer(double costPerContainer) {
        this.costPerContainer = costPerContainer;
    }
}
