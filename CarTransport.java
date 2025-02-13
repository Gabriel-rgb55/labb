import java.awt.*;

class CarTransport extends Car {
    private Ramp ramp = new Ramp();
    private Garage<Car> storage; // Stores multiple cars
    private static final int MAX_CAPACITY = 5;

    public CarTransport() {
        super(2, 300, Color.BLUE, "CarTransport");
        this.storage = new Garage<>(MAX_CAPACITY);
    }

    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            ramp.lower(); // Lower the ramp if the car is stationary
        } else {
            throw new IllegalStateException("Cannot lower ramp while moving!");
        }
    }

    public void raiseRamp() {
        if (getCurrentSpeed() == 0) {
            ramp.raise(); // Raise the ramp if the car is stationary
        } else {
            throw new IllegalStateException("Cannot raise ramp while moving!");
        }
    }

    public void loadCar(Car car) {
        if (ramp.isRampDown()) {
            storage.addVehicle(car); // Load the car only if the ramp is down
        } else {
            throw new IllegalStateException("Cannot load cars while ramp is up!");
        }
    }

    public Car unloadCar() {
        if (ramp.isRampDown()) {
            return storage.retrieveVehicle(); // Unload the car only if the ramp is down
        } else {
            throw new IllegalStateException("Cannot unload cars while ramp is up!");
        }
    }

    public int getLoadedCarCount() {
        return storage.getVehicleCount();
    }

    @Override
    protected double speedFactor() {
        return ramp.isRampDown() ? 0.1 : 1; // Can't move fast with the ramp down
    }
}
