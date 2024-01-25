public abstract class Shape {
    public abstract double getArea();
}

class Rectangle extends Shape {
    private double recLength;
    private double recHeight;

    public Rectangle(double recLength, double recHeight) {
        this.recLength = recLength;
        this.recHeight = recHeight;
    }

    @Override
    public double getArea() {
        return recLength * recHeight;
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class Triangle extends Shape {
    private double triLength;
    private double triHeight;

    public Triangle(double triLength, double triHeight) {
        this.triHeight = triHeight;
        this.triLength = triLength;
    }

    @Override
    public double getArea() {
        return (triHeight*triLength)/2;
    }
}

// Add more shapes if needed
