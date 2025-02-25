@startuml

interface Movable {
    +move(): void
    +turnLeft(): void
    +turnRight(): void
}

abstract class Vehicle {
    -enginePower: double
    -currentSpeed: double
    -x: double
    -y: double
    -direction: double

    +Vehicle(enginePower: double)
    +getEnginePower(): double
    +getCurrentSpeed(): double
    +startEngine(): void
    +stopEngine(): void
    +gas(amount: double): void
    +brake(amount: double): void
    +getX(): double
    +getY(): double
    +getDirection(): double
}

Vehicle ..|> Movable

class Car {
    -nrDoors: int
    -color: Color
    -modelName: String

    +Car(nrDoors: int, enginePower: double, color: Color, modelName: String)
}

Car --|> Vehicle

abstract class PassengerCar {
    +PassengerCar(nrDoors: int, enginePower: double, color: Color, modelName: String)
}

PassengerCar --|> Car

class Volvo240 {
    -TRIM_FACTOR: double
    +Volvo240()
    +speedFactor(): double
}

Volvo240 --|> PassengerCar

class Saab95 {
    -turboOn: boolean
    +Saab95()
    +setTurboOn(): void
    +setTurboOff(): void
    +speedFactor(): double
}

Saab95 --|> PassengerCar

class Truck {
        +raisePlatform(amount: double): void
            +lowerPlatform(amount: double): void

}

Truck --|> Vehicle

class Scania extends Truck {
    -rampController: RampController

    +Scania()
    +raisePlatform(amount: double): void
    +lowerPlatform(amount: double): void
    +move(): void
    +gas(amount: double): void
    -speedFactor(): double
}




class Ramp {
    -angle: double

    +RampController()
    +raise(amount: double): void
    +lower(amount: double): void
    +getAngle(): double
    +isRampDown(): boolean
}

class CarTransport extends Truck{
    -ramp: Ramp
    -garage: Garage<Vehicle>

    +CarTransport(ramp: Ramp, garage: Garage<Vehicle>)
    +loadVehicle(vehicle: Vehicle): void
    +unloadVehicle(): Vehicle
    +getLoadedVehicleCount(): int
}

Truck --> Ramp : has a
CarTransport --> Garage : has a

class Garage<T extends Vehicle> {
    -vehicles: List<T>
    -capacity: int

    +Garage(capacity: int)
    +addVehicle(vehicle: T): void
    +retrieveVehicle(): T
    +getVehicleCount(): int
    +getVehicleList(): List<T>
}

Garage --> Vehicle : has a

class CarWorkshop<T extends Vehicle> {
    -garage: Garage<T>

    +CarWorkshop(garage: Garage<T>)
    +addCar(car: T): void
    +retrieveCar(): T
    +getCarCount(): int
}

CarWorkshop --> Garage : has a


class CarController {
    -int delay
    -Timer timer
    -CarView frame
    -ArrayList<Car> cars
    -static CarWorkshop<Volvo240> volvo240CarWorkshop
    +void gas(int amount)
    +void brake(int amount)
    +void setTurbo(boolean on)
    +void liftBed()
    +void lowerBed()
    +void startAllCars()
    +void stopAllCars()
    +static void main(String[] args)
}

class CarView {
    -CarControllerInterface carC
    -DrawPanel drawPanel
    -JPanel controlPanel
    -JPanel gasPanel
    -JSpinner gasSpinner
    -int gasAmount
    -JLabel gasLabel
    -JButton gasButton
    -JButton brakeButton
    -JButton turboOnButton
    -JButton turboOffButton
    -JButton liftBedButton
    -JButton lowerBedButton
    -JButton startButton
    -JButton stopButton
    +CarView(String framename, CarControllerInterface cc)
    -void initComponents(String title)
}

class DrawPanel {
    -Point[] carPositions
    -BufferedImage[] carImages
    -String[] carNames
    -String statusMessage
    +void moveit(int carIndex, int x, int y)
    +DrawPanel(int x, int y)
    +void removeCar(Car car)
    +void setStatusMessage(String message)
    +void paintComponent(Graphics g)
}

interface CarControllerInterface {
    +void gas(int amount)
    +void brake(int amount)
    +void setTurbo(boolean on)
    +void liftBed()
    +void lowerBed()
    +void startAllCars()
    +void stopAllCars()
}
CarController --> CarView
CarController --> Car
CarController --> CarWorkshop
CarView --> DrawPanel
CarView --> CarControllerInterface
CarControllerInterface <|.. CarController

@enduml
