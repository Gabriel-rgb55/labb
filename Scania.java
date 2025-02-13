import java.awt.*;

public class Scania extends Car implements Ramp {
    private double flakvinkel; // Flakets aktuella vinkel
    private static final double MAX_ANGLE = 70; // Maximalt tillåten vinkel för flaket
    private static final double MIN_ANGLE = 0; // Minimalt tillåten vinkel för flaket

    public Scania() {
        super(2, 450, Color.RED, "Scania");
        this.flakvinkel = 1; // Flaket börjar i uppfält läge
    }

    @Override
    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            flakvinkel = MIN_ANGLE; // Sänk flaket helt
        } else {
            throw new IllegalStateException("Cannot lower ramp while moving!");
        }
    }

    @Override
    public void raiseRamp() {
        if (getCurrentSpeed() == 0) {
            flakvinkel = MAX_ANGLE; // Höj flaket helt
        } else {
            throw new IllegalStateException("Cannot raise ramp while moving!");
        }
    }

    @Override
    public boolean isRampDown() {
        return flakvinkel == MIN_ANGLE;
    }

    public double getFlakvinkel() {
        return flakvinkel;
    }

    @Override
    protected double speedFactor() {
        return isRampDown() ? 0.1 : 0; // Kan bara köra om flaket är helt uppe
    }
}