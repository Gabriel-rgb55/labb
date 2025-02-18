class CarWorkshop<T extends Car> {
    private Garage<T> garage;

    public CarWorkshop(Garage<T> garage) {
        this.garage = garage;
    }

    public void addCar(T car) { garage.addVehicle(car); }
    public T retrieveCar() { return garage.retrieveVehicle(); }
    public int getCarCount() { return garage.getVehicleCount(); }
}
