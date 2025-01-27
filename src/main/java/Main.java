public class Main {
    public static void main(String[] args) {
        DatabaseSetup.setupDatabase();

        int num1 = 56;
        int num2 = 98;

        // Рекурсивный способ
        int gcdRecursive = GCDRecursive.gcd(num1, num2);
        System.out.println("Рекурсивный НОД: " + gcdRecursive);
        DatabaseSetup.saveGCDResult(num1, num2, gcdRecursive);

        // Нерекурсивный способ
        int gcdIterative = GCDIterative.gcd(num1, num2);
        System.out.println("Нерекурсивный НОД: " + gcdIterative);
        DatabaseSetup.saveGCDResult(num1, num2, gcdIterative);
    }
}