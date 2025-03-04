import java.awt.*;

public abstract class Car extends Vehicle {
    private int nrDoors;
    private Color color;
    private String modelName;

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        super(enginePower);
        this.nrDoors = nrDoors;
        this.color = color;
        this.modelName = modelName;
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected double speedFactor() {
        // Implementera denna metod baserat på bilens specifika egenskaper
        return getEnginePower() * 0.01; // Exempel på speedFactor
    }
}
