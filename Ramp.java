public class Ramp {
    private static double angle; // Anger rampens nuvarande vinkel

    //initial vinkel på 0 grader om inget anges
    public Ramp() {
        this.angle = 0; // Sätt standardvinkel till 0 grader
    }


    public Ramp(double initialAngle) {
        this.angle = initialAngle;
    }


    public void raise(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        angle = Math.min(angle + amount, 90); // Max 90 grader
        System.out.println("Ramp raised by " + amount + " degrees to " + angle + " degrees.");
    }


    public void lower(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        angle = Math.max(angle - amount, 0); // Min 0 grader
        System.out.println("Ramp lowered by " + amount + " degrees to " + angle + " degrees.");
    }

    // Returnera nuvarande vinkel
    public static double getAngle() {
        return angle;
    }

    // Kontrollera om rampen är nedfälld
    public boolean isRampDown() {
        return angle > 0;
    }
}
