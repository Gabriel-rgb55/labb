class CarWorkshop<T extends Car> {
    private Garage<T> garage;
    private double x;
    private double y;


    public CarWorkshop(int capacity) {
        this.garage = new Garage<>(capacity);
    }

    protected void setX(double amount) {
        x = amount;
    }

    protected void setY(double amount) {
        y = amount;
    }

    protected double getX(){
        return x;
    }

    protected double getY(){
        return y;
    }

    public void addCar(T car) { garage.addVehicle(car); }
    public T retrieveCar(Volvo240 car) { return garage.retrieveVehicle(); }
    public int getCarCount() { return garage.getVehicleCount(); }
}
