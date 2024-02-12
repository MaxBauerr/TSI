package best_practices;

public class QuadrilateralNew {
    int height;
    int width;
    String colour;

    // Default constructor calls the most specific constructor with default values
    public QuadrilateralNew() {
        this(10, 10, "Black");
    }

    // Constructor with one parameter calls the more specific constructor, assuming a square shape
    public QuadrilateralNew(int sideLength) {
        this(sideLength, sideLength, "Black");
    }

    // Constructor with two parameters calls the most specific constructor, providing a default colour
    public QuadrilateralNew(int height, int width) {
        this(height, width, "Black");
    }

    // The most specific constructor initializes all fields
    public QuadrilateralNew(int height, int width, String colour) {
        this.height = height;
        this.width = width;
        this.colour = colour;
    }

    // Return values
    public void PrintData() {
        System.out.println("Height : " + this.height + " | Width : " + this.width + " | Colour : " + this.colour);
    }

}
