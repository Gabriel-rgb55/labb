import java.awt.*;

public class Saab95 extends PassengerCar {
    private boolean turboOn;

    public Saab95() {
        super(2, 125, Color.red, "Saab95");
        this.turboOn = false;
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }

    @Override
    protected double speedFactor() {
        double turbo = turboOn ? 1.3 : 1.0;
        return getEnginePower() * 0.01 * turbo;
    }
}

