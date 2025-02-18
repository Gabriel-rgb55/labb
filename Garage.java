import java.util.ArrayList;
import java.util.List;

class Garage<T extends Car> {
    private List<T> vehicles = new ArrayList<>();
    private final int capacity;

    public Garage(int capacity) {
        this.capacity = capacity;
    }

    public void addVehicle(T vehicle) {
        if (vehicles.contains(vehicle)) {
            System.out.println("Detta fordon finns redan i garaget!");
            return;
        }
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

    public int getVehicleCount() { return vehicles.size(); }

    public List<T> getVehicleList() { return vehicles; } // Behövs för att förhindra duplicering
}
