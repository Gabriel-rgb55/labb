import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// General Garage class for handling vehicle storage
class Garage<T extends Car> {
    private List<T> vehicles = new ArrayList<>();
    private final int capacity;

    public Garage(int capacity) {
        this.capacity = capacity;
    }

    public void addVehicle(T vehicle) {
        if (vehicles.size() < capacity) {
            vehicles.add(vehicle);
            System.out.println(vehicle + " har lagts till i garaget.");
        } else {
            System.out.println("Garaget är fullt!");
        }
    }

    public T retrieveVehicle() {
        if (!vehicles.isEmpty()) {
            return vehicles.remove(0);
        }
        System.out.println("Inga fordon i garaget!");
        return null;
    }

    public int getVehicleCount() {
        return vehicles.size();
    }
}

// Refactored CarWorkshop using Garage composition
class CarWorkshop<T extends Car> {
    private Garage<T> garage;

    public CarWorkshop(int capacity) {
        this.garage = new Garage<>(capacity);
    }

    public void addCar(T car) { garage.addVehicle(car); }
    public T retrieveCar() { return garage.retrieveVehicle(); }
    public int getCarCount() { return garage.getVehicleCount(); }
}
