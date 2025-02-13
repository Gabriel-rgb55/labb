import java.util.ArrayList;
import java.util.List;

public class CarWorkshop<T extends Car> {
    private List<T> cars;// möjliggör så att vi kan göra verkstäder specifika för just en bil
    private final int maxCapacity;// skapar max kapacitet variabeln så att man kan skapa flera verkstäder som kan ta emot olika antal bilar

    public CarWorkshop(int maxCapacity) {//konstruktor när objekt skapas är det dessa värden som används alltså listan med bilar och maxkapacitet för verkstaden.
        this.maxCapacity = maxCapacity;
        this.cars = new ArrayList<>();
    }

    public void addCar(T car) {//lägger till en bil i verkstaden ifall antalet bilar inte överskrider maximala kapaciteten för den verkstaden
        if (cars.size() < maxCapacity) {
            cars.add(car);
            System.out.println(car + " har lagts till i verkstaden.");
        } else {
            System.out.println("Verkstaden är full! Kan inte ta emot fler bilar.");
        }
    }

    public T retrieveCar() {//hämtar ut bilar från verkstaden och ger också ett meddelande ifall verkstaden redan är tom
        if (!cars.isEmpty()) {
            T car = cars.remove(0);
            System.out.println(car + " har hämtats från verkstaden.");
            return car;
        }
        System.out.println("Inga bilar i verkstaden!");
        return null;
    }

    public int getCarCount() {//returnerar antal element som är antal bilar som ligger i listan
        return cars.size();
    }
}