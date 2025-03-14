public class TwinPrimeGenerator {

    // Public static method to generate twin primes within a range [min, max]
    public static int generateTwinPrime(int min, int max) {
        // Iterate through the range to find the twin primes
        for (int i = min; i <= max - 2; i++) {
            if (isPrime(i) && isPrime(i + 2)) {
                return i + 2;  // Return the larger prime (i + 2)
            }
        }
        return -1;  // Return -1 if no twin primes found in the range
    }

    // Helper method to check if a number is prime
    private static boolean isPrime(int num) {
        if (num <= 1) return false; // 0 and 1 are not primes
        if (num == 2) return true; // 2 is a prime
        if (num % 2 == 0) return false; // Exclude even numbers greater than 2

        // Check divisibility for odd numbers up to sqrt(num)
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false; // Found a divisor, not prime
        }

        return true;  // Number is prime
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        int min = 95500;
        int max = 96000;
        int twinPrime = generateTwinPrime(min, max);

        if (twinPrime != -1) {
            System.out.println("The larger twin prime in the range [" + min + ", " + max + "] is: " + twinPrime);
        } else {
            System.out.println("No twin primes found in the range [" + min + ", " + max + "]");
        }
    }
}
