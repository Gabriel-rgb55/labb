@startuml
interface Movable {
    +move(): void
    +turnLeft(): void
    +turnRight(): void
}

abstract class Car {
    - enginePower: double
    - currentSpeed: double
    - x: double
    - y: double
    - direction: double

    + Car(nrDoors: int, enginePower: double, color: Color, modelName: String)
    + getEnginePower(): double
    + getCurrentSpeed(): double
    + startEngine(): void
    + stopEngine(): void
    + gas(amount: double): void
    + brake(amount: double): void
    + move(): void
    + turnLeft(): void
    + turnRight(): void
    + getX(): double
    + getY(): double
    + getDirection(): double
}

Car ..|> Movable

class CarController {
    - delay: int
    - timer: Timer
    - frame: CarView
    - cars: ArrayList<Car>
    - volvoCarWorkshop: CarWorkshop<Volvo240>

    + main(args: String[]): void
    + gas(amount: int): void
    + brake(amount: int): void
    + setTurbo(on: boolean): void
    + liftBed(): void
    + lowerBed(): void
    + startAllCars(): void
    + stopAllCars(): void
}

class TimerListener {
    + actionPerformed(e: ActionEvent): void
}

CarController ..> Car
CarController ..> CarView
CarController ..> CarWorkshop
CarController ..|> ActionListener
CarController - TimerListener

TimerListener ..> ActionEvent

class CarView {
    - carC: CarController
    - drawPanel: DrawPanel
    - controlPanel: JPanel
    - gasPanel: JPanel
    - gasSpinner: JSpinner
    - gasAmount: int
    - gasButton: JButton
    - brakeButton: JButton
    - turboOnButton: JButton
    etc..

    + CarView(frame name: String, cc: CarController)
    - initComponents(title: String): void
}

CarView ..> CarController
CarView ..> DrawPanel



class CarTransport {
    - ramp: Ramp
    - storage: Garage<PassengerCar>
    - MAX_CAPACITY: int

    + CarTransport()
    + lowerRamp(): void
    + raiseRamp(): void
    + loadCar(car: PassengerCar): void
    + unloadCar(): PassengerCar
    + getLoadedCarCount(): int
    - speedFactor(): double
}

CarTransport --|> Car
CarTransport ..> Ramp
CarTransport ..> Garage
CarTransport ..> PassengerCar



class CarWorkshop<T extends Car> {
    - garage: Garage<T>
    - x: double
    - y: double

    + CarWorkshop(garage: Garage<T>)
    + addCar(car: T): void
    + retrieveCar(): T
    + getCarCount(): int
    - getX(): double
    - getY(): double
}

CarWorkshop ..> Garage
CarWorkshop ..> Car : "T extends"



class DrawPanel {
    - carPositions: Point[]
    - carImages: BufferedImage[]
    - carNames: String[]

    + DrawPanel(x: int, y: int)
    + moveit(carIndex: int, x: int, y: int): void
    + removeCar(car: Car): void
    - paintComponent(g: Graphics): void
}


DrawPanel ..> Car




class Garage<T extends Car> {
    - vehicles: List<T>
    - capacity: int

    + Garage(capacity: int)
    + addVehicle(vehicle: T): void
    + retrieveVehicle(): T
    + getVehicleCount(): int
    + getVehicleList(): List<T>
}

Garage ..> Car





abstract class PassengerCar {
    + PassengerCar(nrDoors: int, enginePower: double, color: Color, modelName: String)
}
PassengerCar -|> Car


class Ramp {
    - angle: double
    + Ramp()
    + Ramp(initialAngle: double)
    + raise(amount: double): void
    + lower(amount: double): void
    + getAngle(): double
    + isRampDown(): boolean
}



class Saab95 {
    - turboOn: boolean
    + Saab95()
    + setTurboOn(): void
    + setTurboOff(): void
    - speedFactor(): double
}
Saab95 -|> PassengerCar


class Scania {
    - ramp: Ramp
    + Scania()
    + raisePlatform(amount: double): void
    + lowerPlatform(amount: double): void
    + move(): void
    + gas(amount: double): void
    - speedFactor(): double
}
Scania -|> Truck

class Truck {
    + Truck(nrDoors: int, enginePower: double, color: Color, modelName: String)
    - speedFactor(): double
}
Truck -|> Car

class TruckRamp {
    + TruckRamp(initialAngle: double)
    + raise(amount: double)
    + lower(amount: double)
    + getAngle(): double
    + isRampDown(): boolean
}
TruckRamp -|> Ramp

class Volvo240 {
    - TRIM_FACTOR: double
    + Volvo240()
    + speedFactor(): double
}
Volvo240 -|> PassengerCar
@enduml
