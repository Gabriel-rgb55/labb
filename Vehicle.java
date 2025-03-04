import java.awt.*;


public abstract class Vehicle implements Movable {
    private double enginePower;
    private double currentSpeed;
    private double x; // X-coordinate
    private double y; // Y-coordinate
    private double direction; // Direction in degrees (0-359)

    public Vehicle(double enginePower) {
        this.enginePower = enginePower;
        stopEngine();
        this.x = 0;
        this.y = 0;
        this.direction = 0; // Facing east (0 degrees)
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
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

    protected void setX(double amount) {
        x = amount;
    }

    protected void setY(double amount) {
        y = amount;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setDirection(double direction) {
        this.direction = direction % 360; // Säkerställ att riktningen är inom 0-359 grader
    }

    public double getDirection() {
        return direction;
    }

    public Point getPosition() {
        return new Point((int) x, (int) y);
    }

    public void setPosition(Point newPosition) {
        this.x = newPosition.x;
        this.y = newPosition.y;
    }
}
