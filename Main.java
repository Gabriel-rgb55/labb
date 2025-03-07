public class Main {
    public static void main(String[] args) {
        CarModel carModel = new CarModel();
        CarView carView = new CarView();
        CarController cc = new CarController(carModel, carView);

        carModel.addCar(new Volvo240());

        carModel.loadCarImages(); // Ensure this method exists in CarModel
        carView.Initialize("CarSim 1.0", (CarControllerInterface) cc);
        // Attach controller
        cc.initTimer(); // Start the simulation timer
    }
}
