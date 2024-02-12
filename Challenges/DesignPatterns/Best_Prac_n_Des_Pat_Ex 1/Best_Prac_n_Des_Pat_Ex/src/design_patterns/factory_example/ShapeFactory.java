package design_patterns.factory_example;

public class ShapeFactory {
        public static Shape createShape(String shapeRequired) {
            if(shapeRequired.equals("Rectangle")) {
                return new Rectangle();
            } else {
                return new Triangle();
            }
        }
}
