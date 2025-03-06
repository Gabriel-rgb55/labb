import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class CarModel {
    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public Point[] getCarPositions() {
        Point[] positions = new Point[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            positions[i] = new Point((int) cars.get(i).getX(), (int) cars.get(i).getY());
        }
        return positions;
    }

    public void moveCar(int carIndex, int x, int y) {
        if (carIndex >= 0 && carIndex < cars.size()) {
            Car car = cars.get(carIndex);
            car.setX(x);
            car.setY(y);
        }
    }

    public void updateCars(int screenWidth) {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            try {
                car.move();
            } catch (IllegalArgumentException ex) {
                continue;
            }

            // Get the car's position
            int x = (int) Math.round(car.getX());
            int y = (int) Math.round(car.getY());

            // Check if the car hits the screen boundaries
            if (x >= screenWidth - 100 || x <= 0) {
                car.setDirection(car.getDirection() + 180); // Turn around
            }

            // Update the car's position in the model
            moveCar(i, x, y);
        }
    }

    public void checkCollisions(CarWorkshop<Volvo240> workshop, DrawPanel drawPanel) {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (car instanceof Volvo240) {
                double workshopX = workshop.getX();
                double workshopY = workshop.getY();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                // Check for collision with the workshop
                if (x >= workshopX && x <= workshopX + 100 &&
                        y >= workshopY && y <= workshopY + 100) {
                    // Collision detected, load the Volvo into the workshop
                    workshop.addCar((Volvo240) car);
                    removeCar(car); // Remove the car from the model
                    drawPanel.removeCar(i); // Update the draw panel
                    drawPanel.setStatusMessage("Volvo loaded into the workshop!");
                }
            }
        }
    }

    public void setCarPositions() {
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).setX(0);
            cars.get(i).setY(i * 100);
        }
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            try {
                car.gas(gas);
            } catch (IllegalArgumentException ex) {
                continue;
            }
        }
    }

    public void brake(int amount) {
        double brakeForce = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brakeForce);
        }
    }

    public void setTurbo(boolean on) {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                Saab95 saab = (Saab95) car;
                if (on) {
                    saab.setTurboOn();
                } else {
                    saab.setTurboOff();
                }
            }
        }
    }

    public void liftBed() {
        for (Car car : cars) {
            try {
                if (car instanceof Scania) {
                    Scania scania = (Scania) car;
                    scania.raisePlatform(70);
                }
            } catch (IllegalArgumentException ex) {
                continue;
            }
        }
    }

    public void lowerBed() {
        for (Car car : cars) {
            try {
                if (car instanceof Scania) {
                    Scania scania = (Scania) car;
                    scania.lowerPlatform(70);
                }
            } catch (IllegalArgumentException ex) {
                continue;
            }
        }
    }

    public void startAllCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    public void stopAllCars() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }
}
