import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car volvo;
    private Car saab;

    @BeforeEach
    public void setUp() {
        volvo = new Volvo240();
        saab = new Saab95();
        volvo.startEngine();
        saab.startEngine();
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
}
