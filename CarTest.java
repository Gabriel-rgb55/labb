import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

class CarTest {

    private Car volvo;
    private Car saab;
    private Scania scania;
    private CarWorkshop<Volvo240> volvoWorkshop;
    private CarWorkshop<Car> generalWorkshop;
    private Volvo240 volvo1, volvo2;
    private BMW bmw;

    @BeforeEach
    public void setUp() {
        volvo = new Volvo240();
        saab = new Saab95();
        scania = new Scania();
        volvo.startEngine();
        saab.startEngine();

        volvoWorkshop=new CarWorkshop<>(2);
        generalWorkshop=new CarWorkshop<>(3);

        volvo1=new Volvo240();
        volvo2= new Volvo240();
        bmw=new BMW();
    }

    private void testGasIncreasesSpeed(Car car) {
        double initialSpeed = car.getCurrentSpeed();
        car.gas(0.5);
        assertTrue(car.getCurrentSpeed() > initialSpeed, "Gas method did not increase speed as expected for " + car.getClass().getSimpleName());
    }

    @Test
    public void testGasIncreasesSpeed() {
        testGasIncreasesSpeed(volvo);
        testGasIncreasesSpeed(saab);
    }

    private void testBrakeDecreasesSpeed(Car car) {
        car.gas(0.5);
        double speedAfterGas = car.getCurrentSpeed();
        car.brake(0.5);
        assertTrue(car.getCurrentSpeed() < speedAfterGas, "Brake method did not decrease speed as expected for " + car.getClass().getSimpleName());
    }

    @Test
    public void testBrakeDecreasesSpeed() {
        testBrakeDecreasesSpeed(volvo);
        testBrakeDecreasesSpeed(saab);
    }

    private void testSpeedDoesNotExceedEnginePower(Car car) {
        car.gas(1);
        assertTrue(car.getCurrentSpeed() <= car.getEnginePower(), "Speed exceeds engine power for " + car.getClass().getSimpleName());
    }

    @Test
    public void testSpeedDoesNotExceedEnginePower() {
        testSpeedDoesNotExceedEnginePower(volvo);
        testSpeedDoesNotExceedEnginePower(saab);
    }

    private void testBrakeDoesNotIncreaseSpeed(Car car) {
        double initialSpeed = car.getCurrentSpeed();
        car.brake(0.5);
        assertTrue(car.getCurrentSpeed() <= initialSpeed, "Brake method should not increase speed for " + car.getClass().getSimpleName());
    }

    @Test
    public void testBrakeDoesNotIncreaseSpeed() {
        testBrakeDoesNotIncreaseSpeed(volvo);
        testBrakeDoesNotIncreaseSpeed(saab);
    }

    private void testGasDoesNotDecreaseSpeed(Car car) {
        double initialSpeed = car.getCurrentSpeed();
        car.gas(0.5);
        assertTrue(car.getCurrentSpeed() >= initialSpeed, "Gas method should not decrease speed for " + car.getClass().getSimpleName());
    }

    @Test
    public void testGasDoesNotDecreaseSpeed() {
        testGasDoesNotDecreaseSpeed(volvo);
        testGasDoesNotDecreaseSpeed(saab);
    }

    private void testGasValueBoundaries(Car car) {
        assertThrows(IllegalArgumentException.class, () -> car.gas(-0.1), "Gas method should throw exception when amount is negative for " + car.getClass().getSimpleName());
        assertThrows(IllegalArgumentException.class, () -> car.gas(1.5), "Gas method should throw exception when amount is greater than 1 for " + car.getClass().getSimpleName());
    }

    @Test
    public void testGasValueBoundaries() {
        testGasValueBoundaries(volvo);
        testGasValueBoundaries(saab);
    }

    private void testBrakeValueBoundaries(Car car) {
        assertThrows(IllegalArgumentException.class, () -> car.brake(-0.1), "Brake method should throw exception when amount is negative for " + car.getClass().getSimpleName());
        assertThrows(IllegalArgumentException.class, () -> car.brake(1.5), "Brake method should throw exception when amount is greater than 1 for " + car.getClass().getSimpleName());
    }

    @Test
    public void testBrakeValueBoundaries() {
        testBrakeValueBoundaries(volvo);
        testBrakeValueBoundaries(saab);
    }

    private void testTurnLeft(Car car) {
        double initialDirection = car.getDirection();
        car.turnLeft();
        assertTrue(car.getDirection() == (initialDirection + 90) % 360, "Turning left did not update direction correctly for " + car.getClass().getSimpleName());
    }

    @Test
    public void testTurnLeft() {
        testTurnLeft(volvo);
        testTurnLeft(saab);
    }

    private void testTurnRight(Car car) {
        double initialDirection = car.getDirection();
        car.turnRight();
        assertTrue(car.getDirection() == (initialDirection - 90 + 360) % 360, "Turning right did not update direction correctly for " + car.getClass().getSimpleName());
    }

    @Test
    public void testTurnRight() {
        testTurnRight(volvo);
        testTurnRight(saab);
    }

    private void testEngineState(Car car) {
        car.startEngine();
        assertTrue(car.getCurrentSpeed() > 0, "Car engine should start with a small speed for " + car.getClass().getSimpleName());
        car.stopEngine();
        assertEquals(0, car.getCurrentSpeed(), "Car should be stopped after engine is stopped for " + car.getClass().getSimpleName());
    }

    @Test
    public void testEngineState() {
        testEngineState(volvo);
        testEngineState(saab);
    }

    @Test
    public void testRaiseRampWhileStopped() {
        scania.stopEngine(); // Ensure truck is stationary
        scania.raiseRamp();
        assertEquals(70, scania.getFlakvinkel(), "Flakvinkel should be fully raised (70) when truck is stopped");
    }

    @Test
    public void testRaiseRampWhileMoving() {
        scania.startEngine();
        scania.gas(0.5); // Make the truck move
        assertThrows(IllegalStateException.class, scania::raiseRamp, "Should throw IllegalStateException when trying to raise ramp while moving");
    }

    @Test
    public void testLowerRampWhileStopped() {
        scania.stopEngine(); // Ensure truck is stationary
        scania.raiseRamp(); // First, raise the ramp
        scania.lowerRamp();
        assertEquals(0, scania.getFlakvinkel(), "Flakvinkel should be fully lowered (0) when truck is stopped");
    }

    @Test
    public void testLowerRampWhileMoving() {
        scania.startEngine();
        scania.gas(0.5); // Make the truck move
        assertThrows(IllegalStateException.class, scania::lowerRamp, "Should throw IllegalStateException when trying to lower ramp while moving");
    }

    @Test
    public void testSpeedFactorWhenRampIsDown() {
        scania.lowerRamp(); // Ensure the ramp is down
        assertEquals(0.1, scania.speedFactor(), "Speed factor should be 0.1 when the ramp is down");
    }

    @Test
    public void testSpeedFactorWhenRampIsUp() {
        scania.raiseRamp(); // Raise the ramp
        assertEquals(0, scania.speedFactor(), "Speed factor should be 0 when the ramp is up");
    }

    @Test
    public void testAddCarToWorkshop() {
        volvoWorkshop.addCar(volvo1);
        volvoWorkshop.addCar(volvo2);
        assertEquals(2, volvoWorkshop.getCarCount(), "Car count should be 2 after adding two cars.");
    }

    @Test
    public void testWorkshopCapacityLimit() {
        volvoWorkshop.addCar(volvo1);
        volvoWorkshop.addCar(volvo2);
        volvoWorkshop.addCar(new Volvo240()); // Should be rejected
        assertEquals(2, volvoWorkshop.getCarCount(), "Workshop should not exceed max capacity.");
    }

    @Test
    public void testRetrieveCarFromWorkshop() {
        volvoWorkshop.addCar(volvo1);
        volvoWorkshop.addCar(volvo2);

        Volvo240 retrievedCar = volvoWorkshop.retrieveCar();
        assertEquals(volvo1, retrievedCar, "First retrieved car should be the first one added.");
        assertEquals(1, volvoWorkshop.getCarCount(), "Car count should decrease after retrieving a car.");
    }

    @Test
    public void testRetrieveCarFromEmptyWorkshop() {
        assertNull(volvoWorkshop.retrieveCar(), "Retrieving from an empty workshop should return null.");
    }

    @Test
    public void testTypeSafetyInWorkshop() {
        volvoWorkshop.addCar(volvo1);
        // Uncommenting the next line should cause a compile-time error:
        //volvoWorkshop.addCar(bmw);
    }

    @Test
    public void testGeneralWorkshopAcceptsMultipleCarTypes() {
        generalWorkshop.addCar(volvo1);
        generalWorkshop.addCar(bmw);
        assertEquals(2, generalWorkshop.getCarCount(), "General workshop should accept different car types.");
    }

    @Test
    public void testRetrieveAllCars() {
        generalWorkshop.addCar(volvo1);
        generalWorkshop.addCar(bmw);

        generalWorkshop.retrieveCar();
        generalWorkshop.retrieveCar();

        assertEquals(0, generalWorkshop.getCarCount(), "Workshop should be empty after retrieving all cars.");
    }
    }


