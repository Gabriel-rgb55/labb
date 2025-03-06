public class Main {
    public static void main(String[] args) {
        CarModel carModel = new CarModel();
        CarView carView = new CarView();

        CarController cc = new CarController(carModel,carView );

        carView.Initialize("CarSim 1.0", cc);

        carModel.addCar(new Volvo240());
        carModel.addCar(new Saab95());
        carModel.addCar(new Scania());
        carModel.addCar(new Volvo240());

        carModel.setCarPositions();


        cc.initTimer();

    }
}
