import java.awt.*;

public class CarTransport extends Car {
    private Ramp ramp;
    private Garage<PassengerCar> storage;
    private static final int MAX_CAPACITY = 5;

    public CarTransport() {
        super(2, 300, Color.BLUE, "CarTransport");
        this.ramp = new Ramp();
        this.storage = new Garage<>(MAX_CAPACITY);
    }

    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            ramp.lower(10);  // Exempel: sänker rampen med 10 grader
        } else {
            throw new IllegalStateException("Cannot lower ramp while moving!");
        }
    }

    public void raiseRamp() {
        if (getCurrentSpeed() == 0) {
            ramp.raise(10);  // Exempel: höjer rampen med 10 grader
        } else {
            throw new IllegalStateException("Cannot raise ramp while moving!");
        }
    }

    public void loadCar(PassengerCar car) {
        if (storage.getVehicleCount() >= MAX_CAPACITY) {
            throw new IllegalStateException("Transporten är full!");
        }
        if (storage.getVehicleList().contains(car)) {
            throw new IllegalStateException("Bilen är redan på transporten!");
        }
        if (ramp.isRampDown()) {
            storage.addVehicle(car);  // Ladda bilen om rampen är nere
        } else {
            throw new IllegalStateException("Cannot load cars while ramp is up!");
        }
    }

    public PassengerCar unloadCar() {
        if (ramp.isRampDown()) {
            return storage.retrieveVehicle();  // Avlasta bilen om rampen är nere
        } else {
            throw new IllegalStateException("Cannot unload cars while ramp is up!");
        }
    }

    public int getLoadedCarCount() {
        return storage.getVehicleCount();
    }

    @Override
    protected double speedFactor() {
        return ramp.isRampDown() ? 0.1 : 1;  // Justera hastigheten om rampen är nere
    }
}
