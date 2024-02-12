package best_practices;

/*
This class has duplicate code in the constructors, making it pretty wet. Refactor the class to use constructor
chaining instead to make the code more DRY. We can call other constructors from a constructor like the following example:

public Square() {
    this(10);
}

public Square(int sideLength) {
    this.sideLength = sideLength;
}

*/
public class Quadrilateral {
    int height;
    int width;
    String colour;

    public Quadrilateral() {
        this.height = 10;
        this.width = 10;
        this.colour = "Black";
    }

    public Quadrilateral(int sideLength) {
        this.height = sideLength;
        this.width = sideLength;
        this.colour = "Black";
    }

    public Quadrilateral(int height, int width) {
        this.height = height;
        this.width = width;
        this.colour = "Black";
    }

    public Quadrilateral(int height, int width, String colour) {
        this.height = height;
        this.width = width;
        this.colour = colour;
    }
}
