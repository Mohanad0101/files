package task5_2;

public class RecordEnumDemo {

    // Part A: Record Temperature with Unit enum
    public record Temperature(double value, Unit unit) {

        // Unit enum for temperature scales
        public enum Unit {
            CELSIUS, FAHRENHEIT, KELVIN
        }

        // Compact constructor with validation
        public Temperature {
            // Convert to Kelvin and check if below absolute zero (0 K)
            double kelvin = convertToKelvin(value, unit);
            if (kelvin < 0) {
                throw new IllegalArgumentException("Temperature cannot be below absolute zero (0 K)");
            }
        }

        // Helper method to convert any temperature to Kelvin
        private double convertToKelvin(double value, Unit unit) {
            switch (unit) {
                case CELSIUS:
                    return value + 273.15;
                case FAHRENHEIT:
                    return (value - 32) * 5/9 + 273.15;
                case KELVIN:
                    return value;
                default:
                    throw new IllegalArgumentException("Unknown unit");
            }
        }

        // Convert temperature to target unit
        public Temperature convertTo(Unit targetUnit) {
            // First convert current value to Kelvin
            double kelvin = convertToKelvin(this.value, this.unit);

            // Then convert from Kelvin to target unit
            double convertedValue;
            switch (targetUnit) {
                case CELSIUS:
                    convertedValue = kelvin - 273.15;
                    break;
                case FAHRENHEIT:
                    convertedValue = (kelvin - 273.15) * 9/5 + 32;
                    break;
                case KELVIN:
                    convertedValue = kelvin;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown target unit");
            }

            return new Temperature(convertedValue, targetUnit);
        }

        // Override toString with proper formatting
        @Override
        public String toString() {
            // Format with 2 decimal places for Fahrenheit (more precision needed)
            // and 1 decimal place for Celsius/Kelvin
            if (unit == Unit.FAHRENHEIT) {
                return String.format("%.2f °%s", value, unit == Unit.KELVIN ? "K" :
                        (unit == Unit.CELSIUS ? "C" : "F")).replace("°F", "°F");
            } else {
                return String.format("%.1f °%s", value, unit == Unit.KELVIN ? "K" :
                        (unit == Unit.CELSIUS ? "C" : "F"));
            }
        }
    }

    // Part B: Enum MathOperation with abstract method
    public enum MathOperation {
        ADD {
            @Override
            public double apply(double a, double b) {
                return a + b;
            }
        },
        SUBTRACT {
            @Override
            public double apply(double a, double b) {
                return a - b;
            }
        },
        MULTIPLY {
            @Override
            public double apply(double a, double b) {
                return a * b;
            }
        },
        DIVIDE {
            @Override
            public double apply(double a, double b) {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            }
        };

        // Abstract method that each constant must implement
        public abstract double apply(double a, double b);
    }

    public static void main(String[] args) {
        System.out.println("=== Temperature Conversions ===");
        Temperature body = new Temperature(36.6, Temperature.Unit.CELSIUS);
        System.out.println("Original: " + body);
        System.out.println("To Fahrenheit: " + body.convertTo(Temperature.Unit.FAHRENHEIT));
        System.out.println("To Kelvin: " + body.convertTo(Temperature.Unit.KELVIN));

        // Test other temperatures
        System.out.println("\n--- Additional Tests ---");
        Temperature freezing = new Temperature(0, Temperature.Unit.CELSIUS);
        System.out.println("Freezing point: " + freezing);
        System.out.println("Freezing in Fahrenheit: " + freezing.convertTo(Temperature.Unit.FAHRENHEIT));

        Temperature boiling = new Temperature(100, Temperature.Unit.CELSIUS);
        System.out.println("Boiling point: " + boiling);
        System.out.println("Boiling in Fahrenheit: " + boiling.convertTo(Temperature.Unit.FAHRENHEIT));

        Temperature absoluteZero = new Temperature(0, Temperature.Unit.KELVIN);
        System.out.println("Absolute zero: " + absoluteZero);

        // Test validation
        System.out.println("\n--- Validation Test ---");
        try {
            Temperature invalid = new Temperature(-10, Temperature.Unit.CELSIUS);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Part B: MathOperation enum
        System.out.println("\n=== Math Operations ===");
        double a = 10, b = 3;
        for (MathOperation op : MathOperation.values()) {
            try {
                System.out.printf("%s(%.1f, %.1f) = %.2f%n",
                        op.name(), a, b, op.apply(a, b));
            } catch (ArithmeticException e) {
                System.out.printf("%s(%.1f, %.1f) = %s%n",
                        op.name(), a, b, e.getMessage());
            }
        }

        // Test division by zero
        System.out.println("\n--- Division by Zero Test ---");
        try {
            MathOperation.DIVIDE.apply(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("DIVIDE(10, 0) = " + e.getMessage());
        }

        // Demonstrate all operations with different values
        System.out.println("\n--- More Math Examples ---");
        double x = 15, y = 4;
        System.out.printf("x = %.1f, y = %.1f%n", x, y);
        for (MathOperation op : MathOperation.values()) {
            try {
                System.out.printf("  %s: %.2f%n", op.name().toLowerCase(), op.apply(x, y));
            } catch (ArithmeticException e) {
                System.out.printf("  %s: %s%n", op.name().toLowerCase(), e.getMessage());
            }
        }
    }
}