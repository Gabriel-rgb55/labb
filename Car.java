import java.awt.*;

public abstract class Car implements Movable {
    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;

    private double x; // x-position
    private double y; // y-position
    private double direction; // direction i grader (0-359)

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
        this.x = 0;
        this.y = 0;
        this.direction = 0;
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(currentSpeed + speedFactor() * amount, enginePower);
    }

    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(currentSpeed - speedFactor() * amount, 0);
    }

    protected abstract double speedFactor();

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount must be between 0 and 1");
        }
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount must be between 0 and 1");
        }
        decrementSpeed(amount);
    }

    // Movable interface implementation
    @Override
    public void move() {
        double radians = Math.toRadians(direction);
        x += Math.cos(radians) * currentSpeed;
        y += Math.sin(radians) * currentSpeed;
    }

    @Override
    public void turnLeft() {
        direction = (direction + 90) % 360;
    }

    @Override
    public void turnRight() {
        direction = (direction - 90 + 360) % 360;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDirection() {
        return direction;
    }
}
