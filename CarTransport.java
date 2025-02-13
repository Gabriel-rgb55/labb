import java.awt.*;
import java.util.Stack;

public class CarTransport extends Scania implements Ramp {
    private static final int MAX_CAPACITY = 5;
    private Stack<Car> loadedCars;
    private static final double LOADING_DISTANCE = 2.0;

    public CarTransport() {//stack används för att enkelt ta ut senaste fordonet
        super();
        loadedCars = new Stack<>();
    }

    @Override
    public void lowerRamp() {//sänker rampen ifall farten är noll alltså fordonet står still
        if (getCurrentSpeed() == 0) {
            super.lowerRamp();
        } else {
            throw new IllegalStateException("Cannot lower ramp while moving!");
        }
    }

    @Override
    public void raiseRamp() {//höjer rampen ifall fordonet står still
        if (getCurrentSpeed() == 0) {
            super.raiseRamp();
        } else {
            throw new IllegalStateException("Cannot raise ramp while moving!");
        }
    }

    @Override
    public boolean isRampDown() {//checkar om rampen är nere
        return super.isRampDown();
    }


    public void loadCar(Car car) {// lastar på en ny bil ifall alla if satser är okej med det
        if (!isRampDown()) {
            throw new IllegalStateException("Ramp must be down to load a car!");
        }
        if (getCurrentSpeed() != 0) {
            throw new IllegalStateException("Transport must be stationary to load a car!");
        }
        if (car instanceof CarTransport) {
            throw new IllegalArgumentException("Cannot load another car transport!");
        }
        if (calculateDistance(car) > LOADING_DISTANCE) {
            throw new IllegalStateException("Car is too far away to be loaded!");
        }
        if (loadedCars.size() >= MAX_CAPACITY) {
            throw new IllegalStateException("Transport is at full capacity!");
        }

        loadedCars.push(car);// lägger bilen på stacken
        car.setPosition(this.getPosition());
    }

    
    public void unloadCar() {//lastar av en bil från transporten
        if (!isRampDown()) {
            throw new IllegalStateException("Ramp must be down to unload a car!");
        }
        if (getCurrentSpeed() != 0) {
            throw new IllegalStateException("Transport must be stationary to unload a car!");
        }
        if (loadedCars.isEmpty()) {
            throw new IllegalStateException("No cars to unload!");
        }

        Car car = loadedCars.pop();//tar bort senaste från stacken
        car.setPosition(this.getPosition());
    }


    private double calculateDistance(Car car) {
        Point transportPosition = this.getPosition();//transportens position
        Point carPosition = car.getPosition();//bilens position
        return Math.sqrt(Math.pow(transportPosition.x - carPosition.x, 2) +//avståndet mellan dem så att man kan se ifall bilen kan lastas på
                Math.pow(transportPosition.y - carPosition.y, 2));
    }


    public int getNumberOfLoadedCars() {//totala antalet bilar som är lastade på fordonet
        return loadedCars.size();
    }
}