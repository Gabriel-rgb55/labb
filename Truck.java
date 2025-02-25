import java.awt.*;

public abstract class Truck extends Car {
    public Truck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    @Override
    public void startEngine() {
        if (Ramp.getAngle()==0) {
            super.startEngine();
        }
    }
}
