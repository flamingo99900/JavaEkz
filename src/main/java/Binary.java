public class Binary {
    private char[] bits;
    private boolean isNegative;

    public Binary(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Длинна не может быть меньше 1");
        }
        this.bits = new char[size];
        for (int i = 0; i < size; i++) {
            this.bits[i] = '0';
        }
    }
    public Binary(int decimal, int size) {
        this(size);
        if (Math.pow(2, size)<=decimal) {
            throw new ArithmeticException("Достигнуто максимальное значение");
        }
        isNegative = decimal < 0;
        int absValue = Math.abs(decimal);

        for (int i = 0; i < size; i++) {
            bits[i] = (char) ('0' + (absValue % 2));
            absValue /= 2;
        }

        if (isNegative) {
            applyComplementCode();
        }
    }
    private void invertBits(char[] bitArray) {
        for (int i = 0; i < bitArray.length; i++) {
            bitArray[i] = (bitArray[i] == '0') ? '1' : '0';
        }
    }
    private void addOne(char[] bitArray) {
        boolean carry = true;
        for (int i = 0; i < bitArray.length; i++) {
            if (bitArray[i] == '0') {
                bitArray[i] = '1';
                carry = false;
                break;
            } else {
                bitArray[i] = '0';
            }
        }
        if (carry) {
            throw new ArithmeticException("Достигнуто максимальное значение");
        }
    }
    private void applyComplementCode() {
        invertBits(bits);
        addOne(bits);
    }
    public int toDecimal() {
        char[] copyBits = bits.clone();
        if (isNegative) {
            invertBits(copyBits);
            addOne(copyBits);
        }
        int decimal = 0;
        for (int i = 0; i < copyBits.length; i++) {
            decimal += (copyBits[i] - '0') * (1 << i);
        }

        return isNegative ? -decimal : decimal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = bits.length - 1; i >= 0; i--) {
            sb.append(bits[i]);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        int size = 8;

        Binary binary1 = new Binary(1, size);
        System.out.println("двоичное представление +1: " + binary1);
        System.out.println("десятичное значение: " + binary1.toDecimal());

        Binary binary2 = new Binary(-1, size);
        System.out.println("двоичное представление -1: " + binary2);
        System.out.println("десятичное значение: " + binary2.toDecimal());

        Binary binary3 = new Binary(-128, size);
        System.out.println("двоичное представление -128: " + binary3);
        System.out.println("десятичное значение: " + binary3.toDecimal());

        Binary binary4 = new Binary(205, size);
        System.out.println("двоичное представление +205: " + binary4);
        System.out.println("десятичное значение: " + binary4.toDecimal());

        Binary binary5 = new Binary(52, size);
        System.out.println("двоичное представление +52: " + binary5);
        System.out.println("десятичное значение: " + binary5.toDecimal());
    }
}
