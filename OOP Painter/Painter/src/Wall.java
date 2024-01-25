import java.util.ArrayList;
import java.util.List;

public class Wall {
    private double height;
    private double length;
    private List<Shape> nonPaintableShapes;
    private Paint paint; // Field to store paint information

    public Wall(double height, double length) {
        this.height = height;
        this.length = length;
        this.nonPaintableShapes = new ArrayList<>();
        this.paint = null; // Initialize paint as null
    }

    public void addNonPaintableShape(Shape shape) {
        nonPaintableShapes.add(shape);
    }

    public double getPaintableArea() {
        double totalArea = height * length;
        for (Shape shape : nonPaintableShapes) {
            totalArea -= shape.getArea();
        }
        return totalArea;
    }

    // Method to set the paint for the wall
    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    // Optionally, a method to get the paint information
    public Paint getPaint() {
        return paint;
    }

    public void resetNonPaintableShapes() {
        nonPaintableShapes.clear(); // Clear all elements from the nonPaintableShapes list
    }
}
